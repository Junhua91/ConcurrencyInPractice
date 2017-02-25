package com.junhua.chapitre5.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorier2 <A,V>implements Computable<A, V>{

	private final Map<A,Future<V>> cache = new ConcurrentHashMap<>();
	private final Computable<A, V> c;
	
	
	public Memorier2(Computable<A, V> c) {
		this.c = c;
	}

	public  V compute(A arg) throws InterruptedException{
		 Future<V> result = cache.get(arg);
		
		if(result == null){
			Callable<V> call = new Callable<V>() {
				@Override
				public V call() throws Exception {
					return c.compute(arg);
				}
			};
			FutureTask<V> task = new FutureTask<>(call);
			cache.put(arg, task);
			result = task;
			task.run();
		}
		try {
			return result.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}

