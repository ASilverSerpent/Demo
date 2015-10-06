package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		boolean flag = true;
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("******服务器启动，等待客户端连接******");
			Socket socket = null;
			while(flag) {
				socket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(socket);
				serverThread.setPriority(3);
				serverThread.start();
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ServerThread extends Thread {
	private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try{
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;
			while((info = br.readLine()) != null) {
				System.out.println("server accept the client info :" + info);
			}
			socket.shutdownInput();
			
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("欢迎您!");
			pw.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
 