package cn.com.zlqf.traditionalthread;

public class TraditionalThreadTest {
	public static void main(String[] args) {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		};
		thread.start();
		Thread thread2 = new Thread(new Runnable(){
			@Override
			public void run() {
				
			}			
		});
		thread2.start();
	}
}
