package com.junhua.chapitre5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable {

	private BlockingQueue<File> fileQueue;
	private FileFilter fileFilter;
	private File root;
	
	@Override
	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void crawl(File root) throws InterruptedException{
		File[] entries = root.listFiles(fileFilter);
		if(entries != null){
			for(File entry : entries){
				if(entry.isDirectory()){
					crawl(entry);
				}
				else {
					fileQueue.put(entry);
				}
			}
		}
	}

}
