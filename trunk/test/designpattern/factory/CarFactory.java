package test.designpattern.factory;

import test.designpattern.factory.abstractfactory.Car;

public class CarFactory extends VehicleFactory {
	public moveable create() {
		return new Car();
	}
}
