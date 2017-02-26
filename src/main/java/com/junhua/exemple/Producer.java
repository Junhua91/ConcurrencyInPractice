package com.junhua.exemple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Producer extends Thread {
	
	private static String FILENAME = "D://test//input.txt";
	private SharedFiFQueue queue;
	
	public Producer(SharedFiFQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		BufferedReader rd = null;
		
		try {
			rd = new BufferedReader(new FileReader(FILENAME));
			String inputLine = null;
			while((inputLine = rd.readLine())!=null){
				String[] inputWords = inputLine.split(" ");
				for(String inputWord : inputWords){
					queue.add(inputWord);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(rd!=null)
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	
	}
	
	
}
