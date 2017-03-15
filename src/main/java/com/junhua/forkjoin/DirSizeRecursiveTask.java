package com.junhua.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class DirSizeRecursiveTask extends RecursiveTask<Long>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File file;
	
	public DirSizeRecursiveTask(File file) {
		this.file = Objects.requireNonNull(file);	
	}

	@Override
	protected Long compute() {
		if(file.isFile()){
			return file.length();
		}
		
		List<DirSizeRecursiveTask> tasks = new ArrayList<>();
		
		File[] childs = file.listFiles();
		
		if(childs != null){
			for(File f: childs){
				DirSizeRecursiveTask task = new DirSizeRecursiveTask(f);
				tasks.add(task);
				task.fork();
			}
		}
	
		long size = 0;
		for(DirSizeRecursiveTask task : tasks){
			size += task.join();
		}
		
		return size;
	}

}
