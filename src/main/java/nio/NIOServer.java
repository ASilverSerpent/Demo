package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	private Selector selector;

	public void initServer(int port) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		selector = Selector.open();
		// 将通道管理器与该通道绑定，并为该通道注册OP_ACCEPT事件
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void listen() throws IOException {
		while (true) {
			// 当注册的事件到达时方法返回；否则该方法会一直阻塞
			selector.select();
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				if (key.isAcceptable()) {
					ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = serverSocketChannel.accept();// 获得和客户端连接的通道
					socketChannel.configureBlocking(false);
					socketChannel.write(ByteBuffer.wrap("accept success; service send msg to client".getBytes("UTF-8")));
					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					String msg = "";
					SocketChannel socketChannel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					while(true){
						buffer.clear();
						int n = socketChannel.read(buffer);
						if(n <= 0){
							break;
						}
						buffer.flip();
						msg += new String(buffer.array(),"UTF-8").trim();
					}
					System.out.println("service recive msg：" + msg);
					ByteBuffer outBuffer = ByteBuffer.wrap("read success; service resend msg to client".getBytes("UTF-8"));
					socketChannel.write(outBuffer);// 将消息回送给客户端
				}
				it.remove();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.initServer(8000);
		server.listen();
	}
}
