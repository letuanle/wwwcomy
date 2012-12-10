package test.json;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import test.lxn.utils.TypeConvert;
import test.lxn.utils.VarUtil;

public class stringTest {
	static {
		System.out.println(234);
	}

	public static void main(String[] args) throws Exception {
		String a= new String("a");
		String c= new String("a");
		String b="a";
		System.out.println(a==b);
		System.out.println(a.equals(b));
		System.out.println(a=="a");
		System.out.println(a==c);
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
		// Pattern pattern =
		// Pattern.compile(".*#\\d{2}");
		// System.out.println(("P_145#25").matches(".*#\\d{2}"));
		// String a = "123,";
		// System.out.println(a.substring(0, a.length() - 1));
		// test2();
	}

	private static void test2() {
		double k = 0;
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 10000; j++) {
				k += j / (i + Math.random());
				if (i == 5000 && j == 7000){
					System.out.println(k);
					return;
				}
			}
		}
	}

	public static void testDate() {
		String re = "";
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String month = date.substring(4, 6);
		String year = date.substring(0, 4);
		if (VarUtil.toInt(date.substring(6)) > 20) {
			if (month.equals("12"))
				re = "" + (VarUtil.toInt(year) + 1) + "01";
			else {
				month = "" + (VarUtil.toInt(month) + 1);
				month = month.length() == 1 ? ("0" + month) : month;
				re = year + month;
			}
		} else
			re = year + month;
		System.out.println(re);
	}

	public static void test1() {
		System.out.println(111);
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
