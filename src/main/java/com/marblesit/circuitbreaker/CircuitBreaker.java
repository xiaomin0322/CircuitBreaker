package com.marblesit.circuitbreaker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CircuitBreaker {
	//CommandGroupKey
	String value() default "";
	//CommandGroupKey
	String name() default "";
	//��ʱʱ��
	int timeoutMilliseconds() default 1000;
    //�̸߳��� or�ź�������
	boolean useThreads() default true;
	//ʧ�ܻص�����
	String fallback() default "";
}
