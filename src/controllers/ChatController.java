package controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Message;
import beans.User;
import chat.ChatServer;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/chat")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final HashMap<Key, Integer> portsMap = new HashMap<Key, Integer>();
	Message messages;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatController() {
		super();
		// TODO Auto-generated constructor stub
		messages = new Message();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "home";
		Integer id1;
		Integer id2;
		ChatServer server = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) { // check if user is signed in
			if (request.getParameter("id1") != null && !request.getParameter("id1").isEmpty()
					&& request.getParameter("id2") != null && !request.getParameter("id2").isEmpty()) { // check
																										// if
																										// both
																										// ids
																										// exist
																										// and
																										// are
																										// not
																										// empty
				id1 = Integer.parseInt(request.getParameter("id1"));
				id2 = Integer.parseInt(request.getParameter("id2"));
				if (id1 != id2) { // all checks okay
					if (id1 == user.getId()) {

						try {
							if (portsMap.get(new Key(id1, id2)) == null && portsMap.get(new Key(id2, id1)) == null) {
								server = new ChatServer(0);
								portsMap.put(new Key(id1, id2), server.getPort());
								portsMap.put(new Key(id2, id1), server.getPort());
								request.setAttribute("serverName", "localhost");
								request.setAttribute("serverPort", server.getPort());

							} else {
								request.setAttribute("serverName", "localhost");
								request.setAttribute("serverPort", portsMap.get(new Key(id1, id2)));
							}
							System.out.println(portsMap.get(new Key(id1, id2)));
							forward = "ChatClient";

						} catch (Exception e) {
							// TODO: handle exception

						}
					}

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
		String forward = "ChatClient";

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	public class Key {

		private final int x;
		private final int y;

		public Key(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Key))
				return false;
			Key key = (Key) o;
			return x == key.x && y == key.y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}

	}
}
