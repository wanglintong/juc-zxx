package cn.com.zlqf.juc;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
	public static void main(String[] args) {
		final Exchanger<String> ec = new Exchanger<String>();
		Runnable runnable1 = new Runnable() {
			public void run() {
				try {
					System.out.println("线程"+Thread.currentThread().getName()+"带着货物出发了");
					Thread.sleep((long) (Math.random()*10000));
					System.out.println("线程"+Thread.currentThread().getName()+"到达交易地点");
					String money = ec.exchange("tnt");
					System.out.println("交易完成，线程"+Thread.currentThread().getName()+"得到"+ money +"钱");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Runnable runnable2 = new Runnable() {
			public void run() {
				try {
					System.out.println("线程"+Thread.currentThread().getName()+"带着钱出发了");
					Thread.sleep((long) (Math.random()*10000));
					System.out.println("线程"+Thread.currentThread().getName()+"到达交易地点");
					String hy = ec.exchange("300000");
					System.out.println("交易完成，线程"+Thread.currentThread().getName()+"得到"+ hy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(runnable1).start();
		new Thread(runnable2).start();
	}
}
