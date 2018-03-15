package com.wuhyoung.threadPoolFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhyoung
 *
 */
public class ThreadPoolManager {
	
	
	private static final int SIZE_CORE_POOL = 15;
	
	private static final int SIZE_MAX_POOL = 20;
	
	private static ThreadPoolExecutor mThreadPool 
	= new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, 30000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(200));
	
	private static ThreadPoolExecutor sThreadPool 
	= new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
	
	
	
	private static ThreadPoolManager sThreadPoolManager = new ThreadPoolManager();
	

	
	
	/**
	 * SIZE_CORE_POOL 核心线程数量 使用linkedQue 队列阻塞线程的时候 只会根据核心线程数来控制线程数量，不会调整线程数到max的线程数量·
	 * 
	 * SIZE_MAX_POOL 只有在是使用  SynchronousQueue 的时候 核心线程不能够满足现在的线程，会调整到直到是最大线程数量
	 * 
	 * 30000L, TimeUnit.MILLISECONDS, 空闲线程等待时间和单位 核心线程的等待时间
	 *  
	 */
	
	
	//单例
	public static ThreadPoolManager newInstance(){
		
		if(sThreadPoolManager ==null){
			synchronized (ThreadPoolManager.class) {
				if(sThreadPoolManager==null){
					sThreadPoolManager =new ThreadPoolManager();
				}
			}
		}
		return sThreadPoolManager;
	}
	
	

	
	private ThreadPoolManager(){
		mThreadPool.allowCoreThreadTimeOut(true);
	}
	
	public void perpare(){
		if(mThreadPool.isShutdown() && !mThreadPool.prestartCoreThread()){
			int startThread = mThreadPool.prestartAllCoreThreads();
			System.out.println("线程初始化成功 初始化数量 ： "+startThread);
		}
		
	}
	
	
	
	
	/**
	 * 线程池中添加任务
	 * @param task
	 * @param type
	 */
	public void addExecuteTask(Runnable task,int type){
		
		if(task!=null){
			if(type == 0){
				sThreadPool.execute(task);
			}else{
				mThreadPool.submit(task);
			}
			
		}
		
	}
	
	
	
	/**
	 * 判断是否是最后一个任务
	 * 
	 * @return
	 */
	public boolean isTaskEnd(){
		
		if(mThreadPool.getActiveCount()==0){
			
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * 缓存大小
	 * @return
	 */
	public int getQueue(){
		
		return mThreadPool.getQueue().size();
		
		
	}
		
	
	/**
	 * 获取线程数量
	 * @return
	 */
	public int getPoolSize(){
		
		return mThreadPool.getPoolSize();
		
	}

	
	/**
	 * 获取已完成的任务数量
	 * @return
	 */
	public long getCompletedTaskCount(){
		
		return mThreadPool.getCompletedTaskCount();
		
	}
	
	
	/**
	 * 关闭线程
	 */
	public void shutdown(){
		
		mThreadPool.shutdown();
		
	}
	
	
	
	public <T> Future<T> addExecuteCall(Callable<T> task){
		
		if(task!=null){
			return mThreadPool.submit(task);
		}else{
			return null;
		}
		
		
	}
	
	

}
