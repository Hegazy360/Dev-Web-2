package controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bcrypt.BCrypt;
import beans.User;
import models.UserDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1978083969201222050L;
	private UserDao dao;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        dao = new UserDao();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "/home";
		String action = request.getParameter("action");
//		dao.addFriend(2, 1);

		if(action != null && action.equals("new")){
			System.out.println("OK");
			forward = "/WEB-INF/signup.jsp";
		}
		else if(action != null && action.equals("signin")){
			forward = "/WEB-INF/signin.jsp";
		}
		else {
			String userId = request.getParameter("userid");
			if(userId != null){
				User user = new User();
				user = dao.getById(Integer.parseInt(userId));
				request.setAttribute("currentUser", user);
				forward = "/WEB-INF/profile.jsp";
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
		User user = new User();
		if(request.getParameter("submitSignUpForm") != null){
			String hashedPassword = BCrypt.hashpw(request.getParameter("pass"), BCrypt.gensalt());

			user.setUname(request.getParameter("uname"));
			user.setPassword(hashedPassword);
			user.setEmail(request.getParameter("email"));
	        dao.addUser(user);
	        if(user != null){
	            session.setAttribute("user", user);
	        }
		}
		else if(request.getParameter("submitSignInForm") != null) {
			System.out.println("inPostAjax");
			System.out.println("SignIn");
			user = dao.signIn(request.getParameter("email"),request.getParameter("password"));
	        if(user != null){
	            session.setAttribute("user", user);
	        }
		}
		else if(request.getParameter("submitFriendRequestForm") != null) {
			dao.addFriend(Integer.parseInt(request.getParameter("userid")),((User)session.getAttribute("user")).getId());
		}

        response.sendRedirect("home");
        
        
	}



}
