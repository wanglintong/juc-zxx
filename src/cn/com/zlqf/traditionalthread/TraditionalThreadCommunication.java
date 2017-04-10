package cn.com.zlqf.traditionalthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TraditionalThreadCommunication {
	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=1;i<=50;++i) {
					business.sub(i);
				}
			}
		}).start();
		for(int i=1;i<=50;++i) {
			business.main(i);
		}
	}
}

class Business {
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean bShouldSub = true;
	public void sub(int loop) {
		lock.lock();
		try {
			while(!bShouldSub){
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int i=1;i<=10;++i) {
				System.out.println("sub thread " + "loop:"+loop+" i:"+i);
			}	
			bShouldSub = false;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void main(int loop) {
		lock.lock();
		try {
			while(bShouldSub) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int i=1;i<=20;++i) {
				System.out.println("main thread " + "loop:"+loop+" i:"+i);
			}
			bShouldSub = true;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
