package com.junhua.chapitre5;

import java.util.Arrays;
import java.util.Vector;

public class MainTest {

	private static  Vector<String> list  = new Vector<>(
			Arrays.asList("1","2","3","4","5","6","7","8","9","10"));
	
	public static void main(String[] args) {
		test5_1();
	}
	
	
	public static void test5_1(){
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for (int i = 0; i < 10; i++) {
					System.out.println("get message");
					getLast(list);
				}

			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("delete message");
					deleteLast(list);
				}
			}
		});
		
		thread1.start();
		thread2.start();
	}
	
	public static Object getLast(Vector<String> list){
		int lastIndex = list.size() -1;
		return list.get(lastIndex);
	}
	
	public static void deleteLast(Vector<String> list){
		int lastIndex = list.size() -1;
		list.remove(lastIndex);
	}
	
	
}
