package com.junhua.chapitre5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	private static final long MAX_NUMER = 300000001;
	private static final long DIVISOR = 3;
	
	public static void main(String[] args) {
		test1();
		
		System.out.println("**************************test2************");
		
		test2();

	}
	
	// tester FutureTask
	static void test1(){
		long timeStart = System.currentTimeMillis();
		FutureTask<Long> task1 = new FutureTask<>(new Calculator(0, MAX_NUMER/2, DIVISOR));
		FutureTask<Long> task2 = new FutureTask<>(new  Calculator(MAX_NUMER/2+1, MAX_NUMER, DIVISOR));
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(task1);
		executor.submit(task2);
		
		long result = 0;
		try {
			result = task1.get();
			result += task2.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		long stopTime = System.currentTimeMillis();
		System.out.println("result is " + result);
		System.out.println(stopTime - timeStart);
	}
	
	static void test2(){
		
		long timeStart = System.currentTimeMillis();
		long result =0;
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<Long> future1;
		Future<Long> future2;
		try {
			future1 = executor.submit(new Calculator(0, MAX_NUMER/2, DIVISOR));
			result = future1.get();
			future2 = executor.submit(new Calculator(MAX_NUMER/2+1, MAX_NUMER, DIVISOR));
			result += future2.get();
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		System.out.println("result is " + result);
		long stopTime = System.currentTimeMillis();
		System.out.println(stopTime - timeStart);
	}
}
