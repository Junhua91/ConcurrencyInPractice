package com.junhua.chapitre5;

import java.io.File;
import java.io.FileFilter;

public class FileFilterTest {

	public void getFiles(String dir){
		
		File directory = new File(dir);
		
		if(!directory.exists()){
			System.out.println(String.format("Directory %s doesn't exist", dir));
			return;
		}
		
		if(!directory.isDirectory()){
			System.out.println(String.format("%s is not a directory ", dir));
			return;
		}
		
		File[]files = directory.listFiles(fileFilter);
		
		for(File file:files){
			System.out.println(file.getName());
		}
		
	}
	
	
	private FileFilter fileFilter = new FileFilter() {
		
		@Override
		public boolean accept(File file) {
			if(file.getName().endsWith(".log"))
				return true;
			else 
			return false;
		}
	};
	
	
	public static void main(String[] args) {
		FileFilterTest outil = new FileFilterTest();
		outil.getFiles("D://test//directory");
		
	}
}
