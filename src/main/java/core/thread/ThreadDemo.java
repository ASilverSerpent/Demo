package core.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDemo {

	public static void main(String[] args) throws Exception {
//		test1();
		
//		ExecutorService exec = Executors.newCachedThreadPool();
//		MyRunnable mr = new MyRunnable();
//		for(int i = 0; i < 5; i++) {
//			exec.execute(mr);
//		}
//		exec.shutdown();//禁止在这个Executor中添加新的任务

		int taskSize = 5;
		//创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。
		//如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
//		ExecutorService pool = Executors.newCachedThreadPool();
		//创建固定数目线程的线程池
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		//创建一个单线程化的Executor。
//		ExecutorService pool = Executors.newSingleThreadExecutor();
		//创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。
//		ExecutorService pool = Executors.newScheduledThreadPool(int corePoolSize);
		
		List<Future> list = new ArrayList<Future>();
		for (int i = 0; i < taskSize; i++) {
			Callable c = new MyCallable(i + "");
			//ExecutoreService提供了submit()方法，传递一个Runnable，或Callable，返回Future。
			Future f = pool.submit(c);
			list.add(f);
		}
		pool.shutdown();
		
		//获取所有并发任务的运行结果
		for(Future f:list){
			//如果Executor后台线程池还没有完成Callable的计算，调用返回Future对象的get()方法，会阻塞直到计算完成。
			System.out.println("......" + f.get().toString());
		}
	}
}

class MyCallable implements Callable<Object> {
	private String taskNum;
	
	MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}
	
	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp = new Date();
		Thread.sleep(1000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回结果,当前任务时间[" + time + "毫秒]";
	}
}