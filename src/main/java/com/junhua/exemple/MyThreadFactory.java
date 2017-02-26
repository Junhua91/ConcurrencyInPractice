package com.junhua.exemple;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory  {
	
	private String preName;
	
	public MyThreadFactory(String preName) {
		this.preName = preName;
	}



	@Override
	public Thread newThread(Runnable r) {

		Thread thread = new Thread(r);
		thread.setName(preName);
		return thread;
		
	}

}
