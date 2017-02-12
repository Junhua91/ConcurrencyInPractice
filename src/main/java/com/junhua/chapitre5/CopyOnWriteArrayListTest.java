package com.junhua.chapitre5;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *1. 	its very efficient if you have a List where Iteration outnumber mutation 
 *		e.g you mostly need to iterate the ArrayList and don't modify it too often.
 
 *2. 	Iterator of CopyOnWriteArrayList is fail-safe and doesn't throw ConcurrentModificationException 
 *		even if underlying CopyOnWriteArrayList is modified once Iteration begins because Iterator is 
 *		operating on separate copy of ArrayList.
 *
 *3. 	 no further synchronization is required when accessing it
 *
 *4. 	the copy-on-write collections are reasonable to use only when iteration is far more common 
 *		than modification
 *
 *
 */
public class CopyOnWriteArrayListTest {
	
	private static final Integer NUM_OF_THREADS = 3;

	public static void main(String[] args) {
		 // Create ExecutorService using the newFixedThreadPool() method
        // of the Executors class.
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

        // Create an array to store IterateMe objects.
        IterateMe[] iterateMes = new IterateMe[NUM_OF_THREADS];
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            iterateMes[i] = new IterateMe("Thread-" + i, false);
        }


        // "for" variant uses internally an Iterator
        for (String name : IterateMe.getNameList()) {
            System.out.println(name);
            
        }
        System.out.println("**********");

        // Execute Thread
        executorService.submit(iterateMes[0]);

        // Costly operation - A new copy of the collection is created
        IterateMe.getNameList().addIfAbsent("Oliver");

        // Execute Thread
        iterateMes[1].setGoToSleep(true);
        executorService.submit(iterateMes[1]);

        // Costly operation - A new copy of the collection is created
        IterateMe.getNameList().remove("Lex");

        // Execute Thread
        executorService.submit(iterateMes[2]);

        // Try to remove an element using Iterator methods
        // This is NOT supported by CopyOnWriteArrayList's Iterator
        Iterator<String> it = IterateMe.getNameList().iterator();
        while (it.hasNext()) {
            try {
                it.remove();
            } catch (UnsupportedOperationException uoe) {
                uoe.printStackTrace(System.err);

                break;
            }
        }

        // Shutdown ExecutionService
        executorService.shutdown();
	}
}
