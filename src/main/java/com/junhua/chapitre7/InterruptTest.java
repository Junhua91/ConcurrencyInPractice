package com.junhua.chapitre7;

public class InterruptTest {

	public static void main(String[] args) {
		test1();
	}

	static void test1() {

		try {
			Thread t1 = new MyThread("Thread1");
			t1.start();
			Thread.sleep(1000);
			t1.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class MyThread extends Thread {

		public MyThread(String name) {
			super(name);
		}

		/**
		 * <ul>
		 * <li>If this thread is blocked in an invocation of the wait(),
		 * wait(long), or wait(long, int) methods of the Object class, or of the
		 * join(), join(long), join(long, int), sleep(long), or sleep(long,
		 * int), methods of this class, then its interrupt status will be
		 * cleared and it will receive an InterruptedException.
		 * </ul>
		 */
		// @Override
		// public void run() {
		// int i = 0;
		// while(!isInterrupted()){
		// try {
		// Thread.sleep(300);
		// } catch (InterruptedException e) {
		// System.out.println(Thread.currentThread().getName() +"
		// ("+this.getState()+") catch InterruptedException.");
		// }
		// i++;
		// System.out.println(Thread.currentThread().getName()+"
		// ("+this.getState()+") loop " + i);
		// }
		// }

		@Override
		public void run() {
			int i = 0;
			try {
				while (!isInterrupted()) {

					Thread.sleep(300);
					i++;
					System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
				}
			} catch (InterruptedException e) {
				System.out.println(
						Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
			}
		}
	}
}
