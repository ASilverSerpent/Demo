package alibaba.web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Test {

	public void selector(int port) throws IOException{		
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册监听的事件
		while(true){
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key = it.next();
//				if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
				if(key.isAcceptable()){
					ServerSocketChannel ssChannel  = (ServerSocketChannel)key.channel();
					SocketChannel socketChannel = ssChannel.accept();//接收到服务端的请求
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_ACCEPT);
					it.remove();
//				}else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
				}else if(key.isReadable()){
					SocketChannel sc = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(2014);
					while(true){
						buffer.clear();
						int n = sc.read(buffer);//读取数据
						if(n <= 0){
							break;
						}
						buffer.flip();
					}
					it.remove();
				}
			}
		}
	}
}