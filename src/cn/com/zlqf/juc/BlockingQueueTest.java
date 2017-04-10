package cn.com.zlqf.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		
		
	}
	
	static class Business {
		BlockingQueue<String> bq1 = new ArrayBlockingQueue<String>(1);
		BlockingQueue<String> bq2 = new ArrayBlockingQueue<String>(1);
		{
			try {
				bq2.put("1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
