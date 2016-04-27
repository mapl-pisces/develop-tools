package com.develop.tools.core.io;


public interface ResourceFinder {
	
	
	
	/**
	 * 资源查找器
	 * @return
	 */
	public Resource findResource(String path, ClassLoader loader);
	
	
	
}
