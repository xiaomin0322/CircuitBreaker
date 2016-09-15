package com.marblesit.circuitbreaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@org.springframework.stereotype.Service("hystrixCommandServiceImpl")
public class HystrixCommandServiceImpl implements Service {

	public static final int TEST_TIMEOUT = 2000;

	@Override
	@HystrixCommand()
	public String get(String str) {
		return str;
	}

	@Override
	@HystrixCommand
	public String throwException() throws MyException {
		throw new MyException();
	}

	@Override
	@HystrixCommand(commandProperties={@HystrixProperty(name="executionTimeoutInMilliseconds",value=TEST_TIMEOUT+"")})
	public String withTimeout(String str) {
		try {
			Thread.sleep(2 * TEST_TIMEOUT);
		} catch (InterruptedException e) {}
		return str;
	}

	@Override
	@HystrixCommand(commandProperties={@HystrixProperty(name="executionTimeoutInMilliseconds",value="0")})
	public String withZeroTimeout(String str) {
		try {
			Thread.sleep(2 * TEST_TIMEOUT);
		} catch (InterruptedException e) {}
		return str;
	}


	@Override
	//executionIsolationStrategy
	@HystrixCommand(commandProperties={@HystrixProperty(name="executionIsolationStrategy",value="THREAD")})
	public int getThreadId() {
		return Thread.currentThread().hashCode();
	}

	@Override
	@HystrixCommand(commandProperties={@HystrixProperty(name="executionIsolationStrategy.",value="SEMAPHORE")})
	public int getNonThreadedThreadThreadId() {
		return Thread.currentThread().hashCode();
	}

	@Override
	@HystrixCommand(fallbackMethod="fallback")
	public String exceptionWithFallback(String s) {
		throw new MyRuntimeException();
	}

	public String fallback(String s) {
		return s;
	}

	@Override
	@HystrixCommand(fallbackMethod="fallbackWithException")
	public Throwable exceptionWithFallbackIncludingException(String testStr) {
		throw new MyRuntimeException();
	}

	public Throwable fallbackWithException(String testStr, Throwable t) {
		return t;
	}
}
