package test.designpattern.factory;

public class BroomFactory extends VehicleFactory {

	@Override
	moveable create() {
		return new Broom();
	}

}
