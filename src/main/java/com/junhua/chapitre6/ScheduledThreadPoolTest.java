package com.junhua.chapitre6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledThreadPoolTest {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		
//		executor.scheduleAtFixedRate(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("The time is " + new Date());
//			}
//		}, 0, 1000L, TimeUnit.MILLISECONDS);
		
		executor.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("The time of delay is " + new Date());

			}
		}, 0, 2000, TimeUnit.MILLISECONDS);
	}
}
