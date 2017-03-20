package com.junhua.chapitre7;

public class CancelTaskTest {
	
	public static void main(String[] args) {
		
		PrimeGenerator generator = new PrimeGenerator();
		
		new Thread(generator).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			generator.cancel();
			System.out.println("the thread is canceled!!!!");
		}
	}
	
	public static class PrimeGenerator implements Runnable{

		private volatile boolean cancel;
		
		public void cancel(){
			this.cancel = true;
		}
		
		@Override
		public void run() {
			while(!cancel){
				System.out.println("The thread is running...");
			}
		}
		
	}

}
