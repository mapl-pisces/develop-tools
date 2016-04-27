package com.develop.tools.core.bean;

import com.develop.tools.core.bean.support.DefaultBean;
import com.develop.tools.core.bean.support.DefaultBeanStore;
import com.develop.tools.core.exception.BeanException;
import com.develop.tools.core.lang.Types;
import com.develop.tools.core.util.CommonUtils;


public class BeanManager {
	
	
	private static BeanStore beanStore = new DefaultBeanStore();
	
	
	private BeanManager() {
	}
	
	
	
	/**
	 * 设置Bean对象存储器
	 * @param beanStore: 对象存储器
	 */
	public static void setBeanStore(BeanStore beanStore) {
		BeanManager.beanStore = beanStore;
	}
	
	
	
	/**
	 * 判断bean是否在当前容器中
	 * @param beanClass: Bean类型
	 * @return true=存在		false=不存在
	 */
	public static boolean contains(Class<?> beanClass) {
		return beanStore.containsKey(beanClass.getName());
	}
	
	
	
	/**
	 * 判断bean是否在当前容器中
	 * @param beanName: Bean名称
	 * @return true=存在		false=不存在
	 */
	public static boolean contains(String beanName) {
		return beanStore.containsKey(beanName.trim());
	}
	
	
	/**
	 * 跟据对象类名获取对象描述
	 * @param className: 封装对象类名
	 * @return Bean
	 */
	public static Bean<?> getBean(String className) {
		if(className == null) return null;
		try {
			return getBean(Class.forName(className.trim()));
		}catch(Exception e) {
			throw CommonUtils.transException(e, BeanException.class);
		}
	}
	
	
	/**
	 * 跟据对象类对象获取对象描述
	 * @param c: 封装对象类型
	 * @return Bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> Bean<T> getBean(Class<T> c) {
		if(c == null) return null;
		String className = c.getName();
		Bean<T> bean = (Bean<T>)beanStore.getBean(className);
		if(bean == null) bean = buildBean(c);
		return bean;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private synchronized static <T> Bean<T> buildBean(Class<T> c) {
		String className = c.getName();
		Bean<T> bean = (Bean<T>) beanStore.getBean(className);
		if(bean != null) return bean;
		if(!Types.isBean(c)) throw new BeanException(" is not Bean:'"+c+"'!");
		bean = new DefaultBean<T>(c);
		beanStore.putBean(className, bean);
		return bean;
	}
	
	
	
}
