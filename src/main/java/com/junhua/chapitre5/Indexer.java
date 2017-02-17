package com.junhua.chapitre5;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {

	private BlockingQueue<File> queue;
	
	public Indexer(BlockingQueue<File> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println(queue.take().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
