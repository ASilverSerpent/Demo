package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8888);
			OutputStream os =  socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("userName: admin; password:000000");
			pw.flush();
			socket.shutdownOutput();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String info = null;
			while((info = br.readLine()) != null) {
				System.out.println("client accept the server info:" + info);
			}
//			br.close();
//			pw.close();
//			os.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
