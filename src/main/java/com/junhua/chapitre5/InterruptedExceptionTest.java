package com.junhua.chapitre5;

public class InterruptedExceptionTest {

	public static void main(String[] args) {
		Thread thread = new SampleThread();
		
		thread.start();
		thread.interrupt();
	}
	
}
