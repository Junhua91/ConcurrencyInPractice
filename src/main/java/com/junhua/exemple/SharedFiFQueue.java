package com.junhua.exemple;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFQueue {

	private Object[] elems = null;
	private int current = 0;
	private int placeIndex = 0;
	private int removeIndex = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition isEmpty = lock.newCondition();
	private Condition isFull = lock.newCondition();
	
	public SharedFiFQueue(int capacity) {
		this.elems = new Object[capacity];
	}
	
	public void add(Object elem){
		lock.lock();
		
		while (current >= elems.length){
			try {
				isFull.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		elems[placeIndex] = elem;
		++current;
		placeIndex = (placeIndex+1) % elems.length;
		isEmpty.signal();
		lock.unlock();
	}

	public Object remove(){
		Object elem = null;
		lock.lock();
		while(current<=0)
			try {
				isEmpty.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		elem = elems[removeIndex];
		--current;
		removeIndex = (removeIndex+1) % elems.length;

		isFull.signal();
		
		lock.unlock();
		return elem;
	}
	
}
