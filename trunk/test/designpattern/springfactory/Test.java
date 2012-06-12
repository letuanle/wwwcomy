package test.designpattern.springfactory;

import java.io.IOException;
import java.util.Properties;

import test.designpattern.springfactory.moveable;

public class Test {

	public static void main(String[] args) {
		Properties p = new Properties();
		try {
			p.load(Test.class.getClassLoader().getResourceAsStream(
					"test/designpattern/springfactory/spring.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = p.getProperty("VehicleType");
		try {
			Object o = Class.forName(type).newInstance();
			((moveable) o).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(type);
		// moveable m = new Car();
		// m.run();
	}
}
