package com.junhua.chapitre5;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Whenever a thread arrives at the exchange point, it must wait for the other 
 * thread to arrive. When the other pairing thread arrives the two threads proceed 
 * to exchange their objects.
 *
 */
public class ExchangerTest {

	private Exchanger<String> exchange = new Exchanger<String>();
	
	public ExchangerTest(Exchanger<String> exchange) {
		this.exchange = exchange;
	}

	private class Producer implements Runnable{
		private String message;
		@Override
		public void run() {
			System.out.println("Producer starts");
			try {
//				Thread.sleep(10000);
				System.out.println("exchanging message...");
				message = exchange.exchange("producer's message",4,TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + message);
			} catch (InterruptedException | TimeoutException e) {
				e.printStackTrace();
			}
			System.out.println("Message exchanged");
		}
	}
	
	private class Customer implements Runnable {

		private String message2;
		
		@Override
		public void run() {
			System.out.println("Customer starts");
			try {
				Thread.sleep(8000);
				System.out.println("exchanging message...");
				message2= exchange.exchange("customer's message",4,TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + message2);
			} catch (InterruptedException | TimeoutException e) {
				e.printStackTrace();
			}
			System.out.println("Message exchanged");
		}
	}
	
	private void start(){
		new Thread(new Producer(),"Thread Producer ").start();
		new Thread(new Customer(),"Thread Customer ").start();
	}
	
	public static void main(String[] args) {
		Exchanger<String>exchanger = new Exchanger<>();
		
		ExchangerTest test = new ExchangerTest(exchanger);
		test.start();
	}
	
}
