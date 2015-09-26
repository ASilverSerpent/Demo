package core.thread;

public class DaemonThreadDemo {

}

class DaemonThread implements Runnable {
	public void run() {
		System.out.println("进入守护线程" + Thread.currentThread().getName());
		writeToFile();
		System.out.println("退出守护线程" + Thread.currentThread().getName());
	}
	
	private void writeToFile() {
//		File file
	}
}