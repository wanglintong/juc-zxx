package cn.com.zlqf.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {
	public static void main(String[] args) {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		for(int i=1 ; i<=50 ; ++i) {
			final int task = i;
			pool.execute(new Runnable(){
				@Override
				public void run() {
					for(int i=1;i<=10;++i) {
						System.out.println(Thread.currentThread().getName() + " task:" + task + " loop:" + i);				
					}
				}
			});	
		}
		System.out.println(pool.getQueue().size());
	}
}
