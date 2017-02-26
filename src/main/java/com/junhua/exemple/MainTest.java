package com.junhua.exemple;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {

	private static int state = 1;
	private static int n = 1;

	public static void main(String[] args) throws InterruptedException {
		test2();
	}

	static void test1() throws InterruptedException {
		long start = System.currentTimeMillis();
		MainTest test = new MainTest();

		Runnable task1 = new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 5; j++) {
					synchronized (test) {
						while (state != 1) {
							try {
								test.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + " " + n++);
						}
						state = 2;
						test.notifyAll();
					}
				}
			}
		};

		Runnable task2 = new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 5; j++) {
					synchronized (test) {
						while (state != 2) {
							try {
								test.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + " " + n++);
						}
						state = 3;
						test.notifyAll();
					}
				}
			}
		};

		Runnable task3 = new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 5; j++) {
					synchronized (test) {
						while (state != 3) {
							try {
								test.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + " " + n++);
						}
						state = 1;
						test.notifyAll();
					}
				}
			}
		};
		//
		// executor.submit(task1);
		// executor.submit(task2);
		// executor.submit(task3);

		Thread thread1 = new Thread(task1, "Thread1");
		Thread thread2 = new Thread(task2, "Thread2");
		Thread thread3 = new Thread(task3, "Thread3");
		thread1.start();
		thread2.start();
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();
		System.out.println(System.currentTimeMillis() - start);
	}

	static void test2() throws InterruptedException {

		long start = System.currentTimeMillis();
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();

		Runnable task1 = new Runnable() {

			@Override
			public void run() {

				for (int j = 0; j < 5; j++) {
					try {
						lock.lock();
						while (state != 1) {
							try {
								condition1.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + " " + n++);
						}
						state = 2;
						condition2.signal();
					} finally {
						lock.unlock();
					}
				}
			}
		};

		Runnable task2  = new Runnable() {

			@Override
			public void run() {

				for (int j = 0; j < 5; j++) {
					try {
						lock.lock();
						while (state != 2) {
							try {
								condition2.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + " " + n++);
						}
						state = 3;
						condition3.signal();
					} finally {
						lock.unlock();
					}
				}
			}
		};

		Runnable task3  = new Runnable() {

			@Override
			public void run() {

				for (int j = 0; j < 5; j++) {
					try {
						lock.lock();
						while (state != 3) {
							try {
								condition3.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + " " + n++);
						}
						state = 1;
						condition1.signal();
					} finally {
						lock.unlock();
					}
				}
			}
		};
		
		
		Thread thread1 = new Thread(task1, "Thread1");
		Thread thread2 = new Thread(task2, "Thread2");
		Thread thread3 = new Thread(task3, "Thread3");
		thread1.start();
		thread2.start();
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();
		
		System.out.println(System.currentTimeMillis() - start);
	}
	
	static void test3(){
		
	}
}
