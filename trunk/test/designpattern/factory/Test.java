package test.designpattern.factory;

import test.designpattern.factory.BroomFactory;
import test.designpattern.factory.VehicleFactory;
import test.designpattern.factory.moveable;

public class Test {

	public static void main(String[] args) {
		// Car c = Car.getInstance();
		// Car ce = Car.getInstance();
		// if (c == ce)
		VehicleFactory factory = new BroomFactory();
		moveable m = factory.create();
		m.run();
	}
}
