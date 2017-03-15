package com.junhua.forkjoin;

import java.io.File;

public class Exemple {

	public static void main(String[] args) {

		String FILE_PATH = "E:/";

		long start = System.currentTimeMillis();

		long size = DirSize.sizeOf(new File(FILE_PATH));

		long taken = System.currentTimeMillis() - start;
		System.out.println(taken + " " + size);
	}
}
