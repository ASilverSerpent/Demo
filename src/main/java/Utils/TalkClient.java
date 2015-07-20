package Utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TalkClient {

	public static void main(String[] args) {
		try {

			Socket socket = new Socket("127.0.0.1", 8888);
//			Socket socket = new Socket("134.248.104.32", 10000);
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			String readline;
			readline = sin.readLine(); // 从系统标准输入读入一字符串
	
			
				os.println(readline);
				// 将从系统标准输入读入的字符串输出到Server
				os.flush();
				// 刷新输出流，使Server马上收到该字符串
				System.out.println("Client:" + readline);
				// 在系统标准输出上打印读入的字符串
				System.out.println("Server:" + is.readLine());

	
			os.close(); // 关闭Socket输出流
			is.close(); // 关闭Socket输入流
			socket.close(); // 关闭Socket
		} catch (Exception e) {
			System.out.println("Error" + e); // 出错，则打印出错信息

		}
	}
}
