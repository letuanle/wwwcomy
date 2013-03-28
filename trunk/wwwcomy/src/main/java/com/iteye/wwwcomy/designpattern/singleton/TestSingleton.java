package com.iteye.wwwcomy.designpattern.singleton;

public class TestSingleton {

	private static TestSingleton instance = null;

	public static TestSingleton getInstance() {
		if (instance == null) {
			return new TestSingleton();
		}
		return instance;
	}

	private TestSingleton() {

	}

}
