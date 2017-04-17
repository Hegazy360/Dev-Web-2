package controllers;

import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bcrypt.BCrypt;
import dao.UserDao;
import models.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
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
				request.setAttribute("user", user);
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
			String hashedPassword = BCrypt.hashpw(encryptPassword(request.getParameter("pass")), BCrypt.gensalt());

			user.setUname(request.getParameter("uname"));
			user.setPassword(hashedPassword);
			user.setEmail(request.getParameter("email"));
	        dao.addUser(user);
		}
		else if(request.getParameter("submitSignInForm") != null) {
			System.out.println("SignIn");
			user = dao.signIn(request.getParameter("email"),request.getParameter("password"));
		}
		
        if(user != null){
            session.setAttribute("user", user);
        }
        response.sendRedirect("home");
        
        
	}

	private String encryptPassword(String password) {
		
		return password;
	}

}
