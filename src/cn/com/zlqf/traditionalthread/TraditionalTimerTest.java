package cn.com.zlqf.traditionalthread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("bombing!");
			}
		}, 5000,3000);
		while(true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
