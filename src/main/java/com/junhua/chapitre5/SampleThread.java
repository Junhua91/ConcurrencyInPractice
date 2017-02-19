package com.junhua.chapitre5;


public class SampleThread extends Thread {

	public SampleThread() {
		System.out.println(SampleThread.class + "was created !!! ");
	}

	@Override
	public void run() {
		while (true) {
			if (Thread.interrupted()){
				System.out.println(Thread.interrupted());
				break;
			}
			System.out.println("run");
		}
	}

}
