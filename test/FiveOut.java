package test;

public class FiveOut {

	public static void main(String[] args) {
		int[] array = new int[8];
		for (int i = 0; i < 8; i++)
			array[i] = i + 1;
		int index = cycleOut(array, 5);
		System.out.println("最后剩下的小孩" + index);
	}

	private static int cycleOut(int[] array, int i) {
		int re = 0;
		int step1 = 1;
		int step2 = 0;
		int last = 0;
		for (int t = 0; t < 10000; t++) {
			if (step2 >= array.length)
				step2 = 0;
			if (array[step2] == -1) {
				step2++;
				continue;
			}
			if(last==array[step2])
				return array[step2];
			if (step1 == i) {
				array[step2] = -1;
				step1 = 0;
			}
			step1++;
			last = array[step2];
			step2++;
		}
//		for (int t = 0; t < 100; t++) {
//			if (array[t] > 0) {
//				re = array[t];
//				System.out.println(re);
//			}
//		}
		return re;
	}

}
