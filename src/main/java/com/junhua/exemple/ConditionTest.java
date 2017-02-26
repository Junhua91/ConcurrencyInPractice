package com.junhua.exemple;

public class ConditionTest {

	public static void main(String[] args) {
		SharedFiFQueue queue = new SharedFiFQueue(10);
		
		Thread producer = new Producer(queue);
		Thread consumer = new Consumer(queue);
		
		producer.start();
		consumer.start();
	}
}
