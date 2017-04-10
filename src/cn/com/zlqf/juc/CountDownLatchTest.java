package cn.com.zlqf.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
	public static void main(String[] args) {
		//CountDownLatch 减到0才会继续往下执行
		final CountDownLatch go = new CountDownLatch(1);
		final CountDownLatch stop = new CountDownLatch(3);
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0 ; i<3 ; ++i) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("线程"+Thread.currentThread().getName()+"准备接受命令");
						go.await();	
						System.out.println("线程"+Thread.currentThread().getName()+"接受命令,开始执行");
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"执行完毕,返回处理结果");
						stop.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		try {
			Thread.sleep(2000);
			go.countDown();
			stop.await();
			System.out.println("任务全部执行完毕...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
