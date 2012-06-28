package test.json;

import java.io.FileWriter;
import java.nio.charset.Charset;

public class stringTest {

	public static void main(String[] args) throws Exception {
		// String a,b,c;
		// Pattern pattern =
		// Pattern.compile("^(\\d+\\.?\\d?)\\*(\\d+\\.?\\d?)\\*(\\d+\\.?\\d?).*$");
		// Matcher matcher = pattern.matcher("23*23.5*23.5cm");
		// while(matcher.find()){
		// a=matcher.group(1);
		// b=matcher.group(2);
		// c=matcher.group(3);
		// System.out.println(a+","+b+","+c);
		// System.out.println(Double.valueOf(a)*Double.valueOf(b)*Double.valueOf(c));
		//
		// }

		// JButton bt = new JButton("test");
		// bt.getUI();
		// bt.getModel();
		// int i = Integer.MIN_VALUE;
		// if (i < 0 && Math.abs(i) == i)
		// System.out.println(Math.abs(i) + "It is " + i);

		System.out.println(Charset.defaultCharset().name());
	}

	public static void test() {
		String a1 = "abc";
		String a2 = "a" + "bc";
		String a3 = "a" + "b" + "c";
		String b1 = new String("abc");
		String b2 = new String("abc");
		String b3 = new String("a") + new String("bc");
		System.out.println(a1 == a2);
		System.out.println(a2 == a3);
		System.out.println(a1 == a3);

		System.out.println(a1 == b1);

		System.out.println("lai B de ");
		System.out.println(b1 == b2);
		System.out.println(b1 == b3);
		System.out.println(b3 == b2);
	}

	public static void testFile() {
		FileWriter testFile = null;
		try {
			testFile = new FileWriter("d:/testFile.test");
			testFile.write("test content");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				testFile.flush();
				testFile.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

class bb {
	public String a;

	public bb() {
		this.a = "123";
	}
}
