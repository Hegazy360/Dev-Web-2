package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostsDao;
import models.Comment;
import models.Post;
import models.User;

/**
 * Servlet implementation class PostsController
 */
@WebServlet("/PostsController")
public class PostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostsDao postsDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsController() {
        super();
        postsDao = new PostsDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "/home";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		int groupId = Integer.parseInt(request.getParameter("groupid"));
		
		if(session.getAttribute("user") != null){
		if(action != null && action.equals("newpost")){
			System.out.println(action);
			forward = "/WEB-INF/new-post.jsp";
		}
		else { //post show page
			int postId = Integer.parseInt(request.getParameter("postid"));
			if(postId > 0){
			    forward = "/WEB-INF/show-post.jsp";
				request.setAttribute("pageStyle", "home");
				request.setAttribute("pageTitle", "Post");
				//get post by id
				Post post = postsDao.getPostById(groupId,postId);
				List<Comment> comments = postsDao.getPostComments(postId);

				if(post != null){
					request.setAttribute("post", post);
					request.setAttribute("comments", comments);
				}
				else {
					forward = "/groups?groupid="+groupId;
				}
				
		}
		}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Post post = new Post();
		String forward = "";
		int groupId = Integer.parseInt(request.getParameter("groupid"));

		if(request.getParameter("submitPostForm") != null){ //add new post
			post.setTitle(request.getParameter("title"));
			post.setDescription(request.getParameter("description"));
			post.setContent(request.getParameter("content"));
			post.setAuthor_id(((User) session.getAttribute("user")).getId());
			post.setGroup_id(groupId);
	        postsDao.createPost(post);
			forward = "groups?groupid="+groupId;
		}
		else if(request.getParameter("submitCommentForm") != null) { //add comment to post
			int postId = Integer.parseInt(request.getParameter("postid"));
			Comment comment = new Comment();
			comment.setAuthor_id(((User) session.getAttribute("user")).getId());
			comment.setPost_id(postId);
			comment.setContent(request.getParameter("content"));
			postsDao.createComment(comment);
			forward = "posts?groupid="+groupId+"&postid="+postId;

		}
        response.sendRedirect(forward);
	}

}
