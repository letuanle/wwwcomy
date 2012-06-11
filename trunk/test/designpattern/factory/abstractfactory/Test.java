package test.designpattern.factory.abstractfactory;

public class Test {

	public static void main(String[] args) {
		// Car c = new Car();
		// c.run();
		// AK47 ak = new AK47();
		// ak.shoot();
		// Food food = new Food();
		// food.printName();
		DefaultFactory factory = new DefaultFactory();
		Car c = factory.createCar();
	}
}
