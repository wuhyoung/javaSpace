package com.wuhyoung.threadPool;

public class MyRuannable implements Runnable {

	public void run() {
		
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName()+"run!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
