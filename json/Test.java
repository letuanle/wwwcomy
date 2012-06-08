package test.json;

public class Test {
	public static void main(String[] args) {
		boolean b = true ? false : true == true ? false : true;
		System.out.println(b);
		System.out.println(" ".matches("[^abc]"));
	}
}

class Parent {
	public static String getName() {
		return "Parent";
	}
}

class Child extends Parent {
	public static String getName() {
		return "Child";
	}
}