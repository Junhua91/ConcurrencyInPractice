package com.junhua.chapitre5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class IterateMe implements Runnable{

	private static final CopyOnWriteArrayList<String> nameList = new 
			CopyOnWriteArrayList<>(new String[] { "Peter",
            "Bruce", "Clark", "Barry", "Lex" });
	
    private String       threadName;
    private boolean      goToSleep;

    public IterateMe() {}

    public IterateMe(String threadName, boolean goToSleep) {
        this.threadName = threadName;
        this.goToSleep  = goToSleep;
    }

    public static CopyOnWriteArrayList<String> getNameList() {
        return nameList;
    }

    
    public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public void setGoToSleep(boolean goToSleep) {
        this.goToSleep = goToSleep;
    }

    @Override
    public void run() {
        if (this.goToSleep) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ie) {
            }
        }

        Iterator<String> it = nameList.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("**********");
    }
	
}
