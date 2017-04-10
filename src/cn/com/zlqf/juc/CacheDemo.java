package cn.com.zlqf.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private Map<String,Object> cache = new HashMap<String,Object>();
	public Object getData(String key) {
		rwl.readLock().lock();
		Object obj = null;
		try {
			cache.get(key);
			if(obj==null) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if(obj==null) {
						obj = "ABCD";
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
		return obj;
	}
}
