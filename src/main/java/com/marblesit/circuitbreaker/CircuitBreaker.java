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
	//超时时间
	int timeoutMilliseconds() default 1000;
    //线程隔离 or信号量隔离
	boolean useThreads() default true;
	//失败回调方法
	String fallback() default "";
}
