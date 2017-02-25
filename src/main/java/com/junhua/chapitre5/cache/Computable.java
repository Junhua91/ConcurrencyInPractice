package com.junhua.chapitre5.cache;

public interface Computable<A,V> {
	V compute(A arg) throws InterruptedException;
}
