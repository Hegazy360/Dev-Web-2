package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Message;
import beans.User;

@WebServlet("/ChatClient")
public class ChatClient extends HttpServlet implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Socket socket = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private ChatClientThread client = null;
	private ArrayList<Message> messages = null;
	private User user=null;
	public ChatClient() {
		super();
		messages = new ArrayList<Message>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Establishing connection. Please wait ...");
		HttpSession session = request.getSession();
		String forward = "/WEB-INF/chat.jsp";
		request.removeAttribute("messages");
		user = (User) session.getAttribute("user");
		try {
			Socket[][] socketList = (Socket[][]) session.getAttribute("socketList");
			int id1 = Integer.parseInt(request.getParameter("id1"));
			int id2 = Integer.parseInt(request.getParameter("id2"));
			System.out.println(id1);
			System.out.println(id2);
			if (socketList != null) {
				if (socketList[id1][id2] != null) {
					console = new DataInputStream(System.in);
					streamOut = new DataOutputStream(socketList[id1][id2].getOutputStream());
					System.out.println("socket is :" + socketList[id1][id2]);
					System.out.println("TESTINO1");
				} else {
					socket = new Socket((String) request.getAttribute("serverName"),
							(int) request.getAttribute("serverPort"));
					System.out.println("Connected: " + socket);
					console = new DataInputStream(System.in);
					streamOut = new DataOutputStream(socket.getOutputStream());
					client = new ChatClientThread(this, socket);
					session.setAttribute("client", client);
					socketList[id1][id2] = socket;
					session.setAttribute("socketList", socketList);
					System.out.println("TESTINO2");

				}
			} else {
				socketList = new Socket[10][100];
				socket = new Socket((String) request.getAttribute("serverName"),
						(int) request.getAttribute("serverPort"));
				System.out.println("Connected: " + socket);
				console = new DataInputStream(System.in);
				streamOut = new DataOutputStream(socket.getOutputStream());
				client = new ChatClientThread(this, socket);
				session.setAttribute("client", client);
				socketList[id1][id2] = socket;
				System.out.println("socket is :" + socket);
				session.setAttribute("socketList", socketList);
				System.out.println("TESTINO3");
			}

		} catch (UnknownHostException uhe) {
		} catch (IOException ioe) {
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "/WEB-INF/chat.jsp";
		HttpSession session = request.getSession();
//		messages = (ArrayList<Message>) request.getAttribute("messages");
		boolean ajax = "XMLHttpRequest".equals(
                request.getHeader("X-Requested-With"));
		Socket[][] socketList = (Socket[][]) session.getAttribute("socketList");
		int id1 = Integer.parseInt(request.getParameter("id1"));
		int id2 = Integer.parseInt(request.getParameter("id2"));
		streamOut = new DataOutputStream(socket.getOutputStream());
		System.out.println("id1 is :"+id1);
		System.out.println("id2 is :"+id2);
		
		
		if (socketList != null) {
			if (socketList[id1][id2] != null) {
				console = new DataInputStream(System.in);
				streamOut = new DataOutputStream(socketList[id1][id2].getOutputStream());
				System.out.println("socket is :" + socketList[id1][id2]);
				System.out.println("TESTINO1");
				if(ajax){
					System.out.println("Ajax called");
					if(request.getParameter("message") != null){
						send(request.getParameter("message"));
					}
					else {
						send("");
					}
					try {
						TimeUnit.SECONDS.sleep(1);
						request.setAttribute("messages", messages);
						String json = new Gson().toJson(messages);
						System.out.println(json.toString());
						response.setContentType("application/json");
					    response.setCharacterEncoding("UTF-8");
					    response.getWriter().write(json);
						System.out.println("Updated messages list : ");
						displayMessages((ArrayList<Message>) request.getAttribute("messages"));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				else {
					System.out.println("Normal Called");
				}
			} 
		} 
		
		
		
		
		
		
		
		
		

		
//		try {

//					if (request.getParameter("sendMessage") != null) {
//						System.out.println("Message received in post method" + request.getParameter("message"));
//						// client1.send(request.getParameter("message"));
//						send(request.getParameter("message"));
//						try {
//							TimeUnit.SECONDS.sleep(1);
//							request.setAttribute("messages", messages);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//					System.out.println("Updated messages list : ");
//					displayMessages((ArrayList<Message>) request.getAttribute("messages"));
//
//
//		} catch (UnknownHostException uhe) {
//		} catch (IOException ioe) {
//		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	public void send(String msg) {
		try {
			streamOut.writeUTF(msg);
			streamOut.flush();
		} catch (IOException ioe) {
			close();
		}
	}

	public void close() {
		try {
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
		}
		client.close();
		client.stop();
	}

	public void handle(String msg, ArrayList<Message> threadMessages) {
		if(!msg.equals("")){
			Message msgtmp = new Message();
			msgtmp.setContent(msg);
			msgtmp.setName(user.getUname());
			System.out.println("USER NAME IS :"+ user.getUname());
			msgtmp.setDate(new Date());
			threadMessages.add(msgtmp);
		}
		this.messages = threadMessages;
		System.out.println("Updated messages list in handle : ");
		displayMessages(threadMessages);
		System.out.println("-----------------------");

	}

	// public void stop() {
	// if (thread != null) {
	// thread.stop();
	// thread = null;
	// }
	// try {
	// if (console != null)
	// console.close();
	// if (streamOut != null)
	// streamOut.close();
	// if (socket != null)
	// socket.close();
	// } catch (IOException ioe) {
	// System.out.println("Error closing ...");
	// }
	// client.close();
	// client.stop();
	// }

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	public void displayMessages(ArrayList<Message> messages){
		for (Message message : messages) {   
		    System.out.println(message.getContent());
		}
	}
}