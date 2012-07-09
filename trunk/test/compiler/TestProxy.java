package test.compiler;

public class TestProxy {
	public static void main(String[] args) throws Exception {
		Movable car = new Car();
		InvocationHandler h = new TimeProxy_Y(car);
		Movable m = (Movable) Proxy.newProxyInstance(Movable.class, h);
		m.move();
	}
}
