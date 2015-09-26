package core.thread;

public class TicketsThread {

	public static void main(String[] args) {
//		MyThread mt1 = new MyThread("窗口1");
//		MyThread mt2 = new MyThread("窗口2");
//		MyThread mt3 = new MyThread("窗口3");
//		
//		mt1.start();
//		mt2.start();
//		mt3.start();
		
		MyRunnable mr = new MyRunnable();
		Thread th1 = new Thread(mr,"窗口1");
		Thread th2 = new Thread(mr,"窗口2");
		Thread th3 = new Thread(mr,"窗口3");
		th1.start();
		th2.start();
		th3.start();
	}
}

class MyThread extends Thread {
	private int ticketsCount = 5;
	private String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while(ticketsCount > 0) {
			ticketsCount--;
			System.out.println(name + "剩余票数:" + ticketsCount);
		}
	}
}


class MyRunnable implements Runnable {
	//Runnable代码可以被多个线程(Thread实例)共享,多个线程处理同一资源
	private int ticketsCount = 5;

	public void run() {
		while(ticketsCount > 0) {
			ticketsCount--;
			System.out.println(Thread.currentThread().getName() + "剩余票数:" + ticketsCount);
		}
	}
}