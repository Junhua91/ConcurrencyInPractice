package com.junhua.forkjoin;

import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class WeighAnimalAction extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874935311276497380L;

	private int start;
	private int end;
	private Double[] weights;

	public WeighAnimalAction(int start, int end, Double[] weights) {
		super();
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	@Override
	protected void compute() {
		if (end - start <= 3) {
			for (int i = start; i < end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal Weighed: " + weights[i]);
			}
		} else {
			int middle = start + (end-start)/2;
			System.out.println("new task: Start:" + start +"end: "+end);
			invokeAll(new WeighAnimalAction(start, middle, weights), 
					new WeighAnimalAction(middle, end, weights));
			
		}

	}

}
