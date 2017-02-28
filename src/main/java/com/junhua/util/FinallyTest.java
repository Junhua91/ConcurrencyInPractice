package com.junhua.util;

/**
 *    <ul>
 *    <li>Code placed in a finally block must be executed whatever occurs within the try block
 *    <li>if code in the try block tries to return a value or throw an exception the item is placed 'on the shelf' till the finally block can execute
 *    <li> the finally block has (by definition) a high priority it can return or throw whatever it likes. In which case anything left 'on the shelf' is discarded.
 *    <li>The only exception to this is if the VM shuts down completely during the try block e.g. by 'System.exit'
 *    </ul>
 * @author jdeng
 *
 */

public class FinallyTest {

	public static void main(String[] args) {
		System.out.println(testFinally());
	}
	
	static int testFinally(){
		int i = 0;
	    try {
	        i = 2;
	        return i;
	    } finally {
	    	i = 12;
	    	System.out.println("finally trumps return.");
	    }
	}
}
