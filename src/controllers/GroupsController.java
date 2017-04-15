package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupsDao;
import models.Group;
import models.User;

/**
 * Servlet implementation class GroupsController
 */
@WebServlet("/GroupsController")
public class GroupsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GroupsDao dao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupsController() {
        super();
        // TODO Auto-generated constructor stub
        dao = new GroupsDao();

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
	        dao.createGroup(group);
		}
        response.sendRedirect("home");
	}

}
