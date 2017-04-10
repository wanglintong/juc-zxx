package cn.com.zlqf.juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableAndFutureTest {
	public static void main(String[] args) throws Exception{
		/*ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>(){
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "haha";
			}
		});
		System.out.println("等待结果");
		System.out.println(future.get());//阻塞在此处等待结果
		//指定时间没有结果返回抛出TimeOutException
		//System.out.println(future.get(1,TimeUnit.SECONDS));
		System.out.println("测试是否阻塞");*/
		ThreadPoolExecutor pool =  (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Integer> service = new ExecutorCompletionService<Integer>(pool);
		for(int i=0 ; i<10 ; ++i) {
			final int seq = i;
			service.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		for(int i=0 ; i<10 ; ++i) {
			System.out.println(service.take().get());
		}
	}
}
