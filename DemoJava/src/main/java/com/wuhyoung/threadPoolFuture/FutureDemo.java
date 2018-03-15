package com.wuhyoung.threadPoolFuture;

import org.junit.Test;

public class FutureDemo {

	/**
	 * 
	 * Runnable Thread 和线程池一般是没有返回值得
	 * 
	 * 
	 * */

	@Test
	public void futureTest() {

		ThreadPoolManager poolExecutor = ThreadPoolManager.newInstance();

		poolExecutor.addExecuteTask(new Runnable() {

			public void run() {
				System.out.println(Thread.currentThread());
			}
		}, 1);

	}

}
