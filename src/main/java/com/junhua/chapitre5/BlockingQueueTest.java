package com.junhua.chapitre5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class BlockingQueueTest {

	public static void main(String[] args) {
		startIndexer(new File("D://usb_copie//gp"));
		
	}
	
	public static void startIndexer(File path){
		
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>();
		FileFilter fileFilter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return true;
			}
		};
		
		new Thread(new FileCrawler(queue, fileFilter, path)).start();
		
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		
		threadPool.submit(new Indexer(queue));
		threadPool.submit(new Indexer(queue));
		threadPool.submit(new Indexer(queue));
		threadPool.submit(new Indexer(queue));
		threadPool.submit(new Indexer(queue));
		
		threadPool.shutdown();
		
		
	}
}
