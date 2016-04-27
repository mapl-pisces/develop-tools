package com.develop.tools.core.bean.support;

import java.util.Iterator;

import com.develop.tools.core.bean.Property;



public class PropertyValueIterator implements Iterator<Object> {
	
	
	private Iterator<Property> iterator;
	private BeanProxy<?> proxy;
	
	
	
	public PropertyValueIterator(Iterator<Property> iterator, BeanProxy<?> proxy) {
		this.iterator = iterator;
		this.proxy = proxy;
	}
	

	public boolean hasNext() {
		return this.iterator.hasNext();
	}
	
	
	
	public Object next() {
		Property pro = this.iterator.next();
		return this.proxy.get(pro.getName());
	}
	
	
	
	public void remove() {
	}

}
