package com.junhua.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class WeightAnimalTask extends RecursiveTask<Double>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5252073231237697246L;

	private int start;
	private int end;
	private Double[] weights;

	public WeightAnimalTask(int start, int end, Double[] weights) {
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	
	@Override
	protected Double compute() {
		
		if (end - start <= 3) {
			double sum = 0;
			for (int i = start; i < end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal Weighed: " + weights[i]);
				sum+=weights[i];
			}
			return sum;
		} else {
			int middle = start + (end-start)/2;
			System.out.println("new task: Start:" + start +"end: "+end);
			
			ForkJoinTask<Double> task = new WeightAnimalTask(start, middle, weights);
			task.fork();
			return new WeightAnimalTask(middle, end, weights).compute()+task.join();
		}
	
	}

	
}
