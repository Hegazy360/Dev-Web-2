package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bcrypt.BCrypt;
import beans.User;
import models.UserDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
@MultipartConfig
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
		else if(action != null && action.equals("edit")){
			int postId = Integer.parseInt(request.getParameter("userid"));
			if (postId > 0) {
				forward = "/WEB-INF/user-edit.jsp";
				User user = dao.getById(postId);
				if (user != null) {
					request.setAttribute("currentUser", user);
				}
			} else {
				forward = "/home";
			}
		}
		else {
			String userId = request.getParameter("userid");
			if(userId != null){
				User user = new User();
				user = dao.getById(Integer.parseInt(userId));
				request.setAttribute("currentUser", user);
				request.setAttribute("pageStyle", "profile");
				request.setAttribute("pageTitle", "My profile");
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
			Part filePart = request.getPart("file");
			File file;
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String extension = "";
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
			    extension = fileName.substring(i+1);
			}
			//TO DO : IF FOLDER DOESN"T EXIST,CREATE IT (Only needed once so,might just create it manually)
			File uploads = new File(request.getServletContext().getRealPath("uploads"));
			System.out.println(request.getServletContext().getRealPath("uploads"));
			
			user.setUname(request.getParameter("uname"));
			if(!request.getParameter("pass").equals("")){
				user.setPassword(hashedPassword);
			}
			user.setEmail(request.getParameter("email"));
			user.setImage(extension);

			if(request.getParameter("userid") == null){
		        int userId = dao.addUser(user);
		        user.setId(userId);
				file = new File(uploads,userId+"."+extension);
			}
			else {
				if(!extension.equals("")){
					user.setImage("."+extension);
				}
				else {
					System.out.println("herer");
					user.setImage(((User) session.getAttribute("user")).getImage());
				}
				int userId = Integer.parseInt(request.getParameter("userid"));
				user.setId(userId);
				dao.updateUser(user);
				file = new File(uploads,userId+"."+extension);
			}

			try (InputStream fileContent = filePart.getInputStream();) {
			    Files.copy(fileContent, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			}
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