package com.develop.tools.core.util;

import java.io.Serializable;
import java.util.Iterator;


/**
 * 安全的Iterator(屏蔽remove方法)
 */
public class SecurityIterator<E> implements Iterator<E>, Serializable {
	private static final long serialVersionUID = -4839822139501725940L;
	
	
	private Iterator<E> iterator;
	
	
	
	public SecurityIterator(Iterator<E> iterator) {
		this.iterator = iterator;
	}
	
	
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}
	
	
	public E next() {
		return this.iterator.next();
	}
	
	
	
	public void remove() {
		throw new SecurityException("No modifications are allowed to a security Iterator!");
	}
	
	
}
