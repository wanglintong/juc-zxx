package cn.com.zlqf.traditionalthread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TraditionalThreadSync {
	public static void main(String[] args) {
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					outputer.output("wanglintong");
				}
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					outputer.output("tangshihao");
				}
			}
		}).start();
	}
}

class Outputer {
	Lock lock = new ReentrantLock();
	public void output(String str) {
		lock.lock();
		try {
			char[] cs = str.toCharArray();
			for(int i=0 ; i<cs.length ; ++i) {
				System.out.print(cs[i]);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
