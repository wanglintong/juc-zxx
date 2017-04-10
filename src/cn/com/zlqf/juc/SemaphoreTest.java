package cn.com.zlqf.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		//使用Semaphore实现互斥锁
		final Semaphore sp = new Semaphore(1);
		for(int i=0 ; i<10 ; ++i) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						sp.acquire();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "进入");
					Long sleepTime = null;
					try {
						sleepTime = (long) (Math.random()*10000);
						Thread.sleep(sleepTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("线程执行时间" + sleepTime + "," + Thread.currentThread().getName() + "自己离开");
					sp.release();
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
