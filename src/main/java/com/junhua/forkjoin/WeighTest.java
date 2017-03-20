package com.junhua.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class WeighTest {

	public static void main(String[] args) {
		test1();
	}
	
	static void test1(){
		Double[] weights = new Double[10];
		
		
		ForkJoinTask<?>task = new WeighAnimalAction(0, weights.length, weights);
		ForkJoinPool pool = new ForkJoinPool();
		Object o = pool.invoke(task);
		System.out.println(o);
		
//		new ForkJoinPool().invoke(new WeighAnimalAction(0, weights.length, weights));
//		
//		Arrays.asList(weights).stream().forEach(d->System.out.println(d.intValue()+ " "));
	}
	
	static void test2(){
		Double[] weights = new Double[10];
		Double sum = new ForkJoinPool().invoke(new WeightAnimalTask(0, weights.length, weights));
	
		System.out.println(sum);
	}
}
