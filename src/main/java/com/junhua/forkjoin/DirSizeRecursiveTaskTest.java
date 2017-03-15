package com.junhua.forkjoin;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class DirSizeRecursiveTaskTest {

	private DirSizeRecursiveTaskTest(){
		
	}
	
	public static long size(File file){
		ForkJoinPool pool = new ForkJoinPool();
		
		try{
			return pool.invoke(new DirSizeRecursiveTask(file));
		}finally{
			pool.shutdown();
		}
	}
	
	public static void main(String[] args) {

		String FILE_PATH = "E:/";

		long start = System.currentTimeMillis();

		long size = DirSizeRecursiveTaskTest.size(new File(FILE_PATH));

		long taken = System.currentTimeMillis() - start;
		System.out.println(taken + " " + size);
		
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
