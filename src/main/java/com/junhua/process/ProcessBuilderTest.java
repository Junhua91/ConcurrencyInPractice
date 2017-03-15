package com.junhua.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessBuilderTest {
	
	
	public static void main(String[] args) {
		ProcessBuilder pb= new ProcessBuilder("echo","This is ProcessBuilder exemple");
		try {
			Process process = pb.start();
			int errCode = process.waitFor();
			System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
			System.out.println("Echo Output:\n" + output(process.getInputStream())); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static String output(InputStream in) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		
		try {
		br = new BufferedReader(new InputStreamReader(in));
		String line = null;
			while((line=br.readLine())!=null){
				sb.append(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			br.close();
		}
		return sb.toString();
	}
}
