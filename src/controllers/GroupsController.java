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

import beans.Group;
import beans.Post;
import beans.User;
import models.GroupsDao;
import models.PostsDao;

/**
 * Servlet implementation class GroupsController
 */
@WebServlet("/groups")
public class GroupsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GroupsDao groupsDao;
    private PostsDao postsDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupsController() {
        super();
        // TODO Auto-generated constructor stub
        groupsDao = new GroupsDao();
        postsDao = new PostsDao();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "";
		String action = request.getParameter("action");
		if(action != null && action.equals("newgroup")){
			System.out.println(action);
			forward = "/WEB-INF/new-group.jsp";
		}
		else { //group main page
			int groupId = Integer.parseInt(request.getParameter("groupid"));
			if(groupId > 0){
				    forward = "/WEB-INF/show-group.jsp";
					request.setAttribute("pageStyle", "home");
					request.setAttribute("pageTitle", "Group");
					//get group by id
					Group group = groupsDao.getGroupById(groupId);
					if(group != null){
						request.setAttribute("group", group);
						//get all posts for this group
						List<Post> posts = postsDao.getAllPosts(groupId);
						request.setAttribute("posts", posts);	
					}
					else {
						forward = "/home";
					}
					
			}
			else {
				forward = "/home";
			}
		  
		}

		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Group group = new Group();
		if(request.getParameter("submitGroupForm") != null){
			group.setTitle(request.getParameter("title"));
			group.setDescription(request.getParameter("description"));
			group.setCreator_id(((User) session.getAttribute("user")).getId());
	        groupsDao.createGroup(group);
		}
        response.sendRedirect("home");
	}

}
