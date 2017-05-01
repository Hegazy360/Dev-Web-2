package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Messages;
import chat.ChatClient;
import chat.ChatServer;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/chat")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChatServer server = null;
	ChatClient client1 = null;
	ChatClient client2 = null;
	Messages messages = new Messages();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "/home";
		int id1;
		int id2;

		if (request.getParameter("id1") != null && !request.getParameter("id1").isEmpty()
				&& request.getParameter("id2") != null && !request.getParameter("id2").isEmpty()) {
			id1 = Integer.parseInt(request.getParameter("id1"));
			id2 = Integer.parseInt(request.getParameter("id2"));
			if (id1 != id2) { //all checks okay
				try {
					server = new ChatServer(55555);
				} catch (Exception e) {
					// TODO: handle exception
				}
//				client1 = new ChatClient("localhost",55555);

				forward = "/WEB-INF/chat.jsp";
			}
		}

		request.setAttribute("pageTitle", "Group");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("sendMessage") != null){
			System.out.println(request.getParameter("message"));
//			client1.send(request.getParameter("message"));
			messages.setContent(request.getParameter("messages")+request.getParameter("message"));
				
			request.setAttribute("messages",messages);
		}
	}

}
