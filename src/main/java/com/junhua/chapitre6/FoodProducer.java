package com.junhua.chapitre6;

import java.util.Date;
import java.util.concurrent.Callable;

public class FoodProducer implements Callable<FoodPlate> {

	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public FoodProducer(String name) {
		this.name = name;
	}
	
	@Override
	public FoodPlate call() throws Exception {
		System.out.println("Current staff at work is " + name + " at " + new Date());
		Thread.sleep(5000);
		return new FoodPlate(true, true, true, name);
	
	}

}
