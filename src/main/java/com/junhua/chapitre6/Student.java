package com.junhua.chapitre6;

import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Student implements Runnable{

	private String stuName;
	private CompletionService<FoodPlate> completionService;
	
	
	public Student(String stuName, CompletionService<FoodPlate> completionService) {
		this.stuName = stuName;
		this.completionService = completionService;
	}


	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public CompletionService<FoodPlate> getCompletionService() {
		return completionService;
	}

	public void setCompletionService(CompletionService<FoodPlate> completionService) {
		this.completionService = completionService;
	}


	
	@Override
	public void run() {
		System.out.println("student is waiting for the food at " + new Date());
		Future<FoodPlate> fp = null; 
		try {
			fp = completionService.take();
			System.out.println(stuName + " got food plat created by " + 
			fp.get(100L,TimeUnit.MILLISECONDS).getFoodPlateCreatedBy());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("time out"); 
			fp.cancel(true);
		}
		
		System.out.println("Exiting Run()");
	}
}
