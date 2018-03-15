package com.wuhyoung.threadPool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorDemo {
	
	
	@Test
	public void  testRun() throws  Exception{
		
		int corePoolSize = 3; //线程池中所保存的线程数
		int maximumPoolSize = 6; // 线程池允许的最大线程数
		long keepAliveTime = 30000L; // 当线程数大于核心时，此为种植前多余的空闲线程等待新任务的最长时间
		TimeUnit unit =  TimeUnit.MILLISECONDS; //keepAliveTime 的时间单位
		LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(); // 阻塞时的队列
		Runnable myRunnable = new MyRuannable();

		
		ThreadPoolExecutor executor 
		= new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		
		ThreadPoolExecutor executor2 = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

		
//		testThread(executor, myRunnable);
		testThread(executor2, myRunnable);
	}

	private void testThread(ThreadPoolExecutor executor, Runnable myRunnable)
			throws InterruptedException {
		
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---先开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---再开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		Thread.sleep(8000);
		System.out.println("----8秒之后----");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		
	}

}
