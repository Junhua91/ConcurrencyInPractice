package com.junhua.forkjoin;

import java.io.File;

public class DirSize {

	public static long sizeOf(final File file){
		long size = 0;
		
		if(file.isFile()){
			size = file.length();
		}else{
			File[] child = file.listFiles();
			if(child != null){
				for(File f: child){
					size += DirSize.sizeOf(f);
				}
			}
		}
		return size;
	}
}
