package com.junhua.chapitre5;

import java.util.concurrent.Callable;

public class Calculator implements Callable<Long>{

	private long first;
	private long last;
	private long divisor;
	
	public Calculator(long first, long last, long divisor) {
		this.first = first;
		this.last = last;
		this.divisor = divisor;
	}

	@Override
	public Long call() throws Exception {
		return calculate(first, last, divisor) ; 
	}
	
	
	private Long calculate(long first, long last, long divisor){
		long amout = 0;
		
		for(long i=first;i<last;i++){
			if(i%divisor == 0)
				amout++;
		}
		
		return amout;
	}
}
