package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Comment;
import beans.Post;
import beans.User;
import models.PostsDao;

/**
 * Servlet implementation class PostsController
 */
@WebServlet("/posts")
public class PostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostsDao postsDao;
	static final HashMap<Integer, Semaphore> postLockMap = new HashMap<Integer, Semaphore>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostsController() {
		super();
		postsDao = new PostsDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "/home";
		request.setAttribute("pageStyle", "posts");
		request.setAttribute("pageTitle", "Posts");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		int groupId = Integer.parseInt(request.getParameter("groupid"));

		if (action != null && action.equals("newpost")) {
			if (currentUser != null)
				forward = "/WEB-INF/new-post.jsp";
		} else if (action != null && action.equals("edit")) {
			System.out.println(postLockMap.toString());
			if (currentUser != null) {
				int postId = Integer.parseInt(request.getParameter("postid"));
				if (postId > 0) {
					Semaphore postLock = postLockMap.get(postId);
					if (postLock == null) {
						postLock = new Semaphore(1);
						postLockMap.put(postId, postLock);
					}
					synchronized (postLock) {
						try {
							postLock.acquire();
							System.out.println("editing post "+postId);
							forward = "/WEB-INF/post-edit.jsp";
							Post post = postsDao.getPostById(groupId, postId);
							if (post != null) {
								request.setAttribute("post", post);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}

			}
		} else if (action != null && action.equals("delete")) {
			if (currentUser != null) {
				int postId = Integer.parseInt(request.getParameter("postid"));
				if (postId > 0) {
					postsDao.deletePost(postId);
					forward = "/groups?groupid=" + groupId;
				}
			}
		} else { // post show page
			int postId = Integer.parseInt(request.getParameter("postid"));
			if (postId > 0) {
				forward = "/WEB-INF/show-post.jsp";
				// get post by id
				Post post = postsDao.getPostById(groupId, postId);
				List<Comment> comments = postsDao.getPostComments(postId);

				if (post != null) {
					request.setAttribute("post", post);
					request.setAttribute("comments", comments);
				} else {
					forward = "/groups?groupid=" + groupId;
				}

			}
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Post post = new Post();
		String forward = "";
		int groupId = Integer.parseInt(request.getParameter("groupid"));

		if (request.getParameter("submitPostForm") != null) { // add new post
			post.setTitle(request.getParameter("title"));
			post.setDescription(request.getParameter("description"));
			post.setContent(request.getParameter("content"));
			post.setAuthor_id(((User) session.getAttribute("user")).getId());
			post.setGroup_id(groupId);
			postsDao.createPost(post);
			forward = "groups?groupid=" + groupId;
		} else if (request.getParameter("submitCommentForm") != null) { // add
																		// comment
																		// to
																		// post
			int postId = Integer.parseInt(request.getParameter("postid"));
			Comment comment = new Comment();
			comment.setAuthor_id(((User) session.getAttribute("user")).getId());
			comment.setPost_id(postId);
			comment.setContent(request.getParameter("content"));
			postsDao.createComment(comment);
			forward = "posts?groupid=" + groupId + "&postid=" + postId;

		} else if (request.getParameter("submitEditPostForm") != null) { // add
																			// new
																			// post
			int postId = Integer.parseInt(request.getParameter("postid"));
			Semaphore postLock = postLockMap.get(postId);
			System.out.println(postLock.toString());
			//should probably add timeout to ensure edit is saved
			postLock.release();

			
			post.setTitle(request.getParameter("title"));
			post.setDescription(request.getParameter("description"));
			post.setContent(request.getParameter("content"));
			post.setId(postId);
			postsDao.updatePost(post);
			forward = "posts?groupid=" + groupId + "&postid=" + postId;
		}
		response.sendRedirect(forward);
	}

}
