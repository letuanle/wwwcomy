package test.designpattern.factory;

import test.designpattern.factory.moveable;

public class Car implements moveable {

	private static Car instance;

	public Car() {
	}

	public static Car getInstance() {
		if (instance == null)
			instance = new Car();
		return instance;
	}

	public void run() {
		System.out.println("The car is runnning...");
	}

}
