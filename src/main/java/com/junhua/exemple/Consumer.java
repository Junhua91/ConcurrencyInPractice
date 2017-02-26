package com.junhua.exemple;

import java.util.HashSet;
import java.util.Set;

public class Consumer extends Thread {

	private Set<Object> seenObjects = new HashSet<>();
	private int total = 0;
	private SharedFiFQueue queue;

	public Consumer(SharedFiFQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		
		do{
		Object obj = queue.remove();
		if(obj == null)
			break;
		if(!seenObjects.contains(obj)){
			++ total;
			seenObjects.add(obj);
		}
		System.out.println("[Consumer] Read the element: " + obj.toString());
		}while(true);
		 System.out.println("\n[Consumer] " + total + " distinct words have been read...");
	}

}
