package cn.com.zlqf.traditionalthread;

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
	private boolean bShouldSub = true;
	public synchronized void sub(int loop) {
		while(!bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=1;i<=10;++i) {
			System.out.println("sub thread " + "loop:"+loop+" i:"+i);
		}	
		bShouldSub = false;
		this.notifyAll();
	}
	
	public synchronized void main(int loop) {
		while(bShouldSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=1;i<=20;++i) {
			System.out.println("main thread " + "loop:"+loop+" i:"+i);
		}
		bShouldSub = true;
		this.notifyAll();
	}
}
