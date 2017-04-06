package cn.com.zlqf.traditionalthread;

public class ThreadLocalTest {
	
}

class MyThreadScopeData {
	private MyThreadScopeData(){};
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
	public static MyThreadScopeData getInstance() {
		MyThreadScopeData instance = map.get();
		if(instance == null) {
			 instance = new MyThreadScopeData();
			 map.set(instance);
		}
		return instance;
	}
}
