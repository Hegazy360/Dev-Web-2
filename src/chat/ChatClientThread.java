package chat;

import java.net.*;
import java.util.ArrayList;

import beans.Message;

import java.io.*;

public class ChatClientThread extends Thread {
	private Socket socket = null;
	private ChatClient client = null;
	private DataInputStream streamIn = null;
//	private Message messages = null;
	private ArrayList<Message> messages = null;

	public ChatClientThread(ChatClient _client, Socket _socket) {
		client = _client;
		socket = _socket;
//		messages = new Message();
		messages = new ArrayList<Message>();
		open();
		start();
	}

	public void open() {
		try {
			streamIn = new DataInputStream(socket.getInputStream());
		} catch (IOException ioe) {
			System.out.println("Error getting input stream: " + ioe);
//			client.stop(client);
		}
	}

	public void close() {
		try {
			if (streamIn != null)
				streamIn.close();
		} catch (IOException ioe) {
			System.out.println("Error closing input stream: " + ioe);
		}
	}

	public void run() {
		while (true) {
			try {
				client.handle(streamIn.readUTF(), getMessages());
//				System.out.println("MESSAGES IN THREAD IS = "+getMessages().toString());
			} catch (IOException ioe) {
				System.out.println("Listening error: " + ioe.getMessage());
//				client.stop(client);
			}
		}
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
}
