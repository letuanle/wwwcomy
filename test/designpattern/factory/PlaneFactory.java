package test.designpattern.factory;

public class PlaneFactory extends VehicleFactory {
	@Override
	moveable create() {
		return new Plane();
	}
}
