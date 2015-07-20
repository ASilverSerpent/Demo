package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	private Selector selector;

	public void initClient(String ip, int port) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		selector = Selector.open();
		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen()方法中调用channel.finishConnect()才能完成连接
		channel.connect(new InetSocketAddress(ip, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	public void listen() throws IOException {
		while (true) {
			selector.select();
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					// 如果正在连接，则完成连接
					if (channel.isConnectionPending()) {
						channel.finishConnect();
					}
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap("connect success; client send msg to service".getBytes("UTF-8")));
					// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
					channel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {// 获得可读的事件
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
					System.out.println("client recive msg：" + msg);
					ByteBuffer outBuffer = ByteBuffer.wrap("read success; client resend msg to service".getBytes("UTF-8"));
					socketChannel.write(outBuffer);
				}
				it.remove();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient();
		client.initClient("localhost", 8000);
		client.listen();
	}

}
