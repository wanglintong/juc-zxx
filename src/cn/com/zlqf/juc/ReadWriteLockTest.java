package cn.com.zlqf.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	
}	

class Queue {
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public void get() {
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " ready to read");
			Thread.sleep((long)Math.random()*1000);
			System.out.println(Thread.currentThread().getName() + " has read data : " + data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	public void put(Object data) {
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " ready to write");
			Thread.sleep((long)Math.random()*1000);
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " has write data : " + data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}

}
