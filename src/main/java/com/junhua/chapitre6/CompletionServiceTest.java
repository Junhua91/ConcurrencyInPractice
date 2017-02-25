package com.junhua.chapitre6;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {

	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService<FoodPlate> service = new ExecutorCompletionService<FoodPlate>(executor);
		
		for(int i =1;i<10;i++){
			service.submit(new FoodProducer("Producer-" + i));
		}
		
		for(int i =1;i<10;i++){
			new Thread(new Student("Stu" + i, service)).start();
		}
		
	}
}