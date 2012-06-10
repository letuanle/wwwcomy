package test.apriori;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

/**
 * @author Liuxn
 * 
 */
public class MyAproiri {

	/**
	 * 事务数据
	 */
	private String[][] tranData;

	/**
	 * 数据总量
	 */
	private int tranDataSize = 0;

	/**
	 * 原始数据项,事务数据tranData应当由itemData构成
	 */
	private Set<HashSet<String>> itemData;

	/**
	 * 支持度
	 */
	private double support;

	/**
	 * 置信度
	 */
	private double confidence;

	TreeMap<String, Integer> candidateSet = new TreeMap<String, Integer>();

	public MyAproiri(String[][] data) {
		this(data, 0.2, 0.6);
	}

	public MyAproiri(String[][] data, double s, double c) {
		this(data, calcItemData(data), s, c);
	}

	public MyAproiri(String[][] data, Set<HashSet<String>> itemData, double s,
			double c) {
		if (s > 1)
			s = 1.0;
		if (c > 1)
			c = 1.0;
		this.tranData = data;
		for (String[] a : data) {
			tranDataSize += a.length;
		}
		this.itemData = itemData;
		this.support = s;
		this.confidence = c;
	}

	public static void main(String[] args) {
		Monitor test = MonitorFactory.start("test1");
		test.start();
		String[][] data = new String[][] { { "1", "2", "5" }, { "2", "4" },
				{ "2", "3" }, { "1", "2", "4" }, { "1", "3" }, { "2", "3" },
				{ "1", "3" }, { "1", "2", "3", "5" }, { "1", "2", "3" } };
		MyAproiri myAproiri = new MyAproiri(data);
		System.out.println(myAproiri.getItemData());
		System.out.println(myAproiri.getTranDataSize());
		System.out.println("****Step1:****");
		HashMap<HashSet<String>, Integer> result = AproiriUtil.stat(
				myAproiri.getItemData(), myAproiri.getTranData());
		System.out.println(result);
		System.out.println("****Step2:****");
		HashMap<HashSet<String>, Integer> result1 = AproiriUtil.dataSelection(
				result, 0.08, myAproiri.getTranDataSize());
		System.out.println(result1);
		System.out.println("****Step3:****");
		HashMap<HashSet<String>, Integer> result2 = AproiriUtil
				.calcNextCandidateData(result1, 2);
		System.out.println(result2);
		System.out.println("****Step4:****");
		HashMap<HashSet<String>, Integer> result3 = AproiriUtil.stat(
				result2.keySet(), myAproiri.getTranData());
		System.out.println(result3);
		System.out.println("****Step5:****");
		HashMap<HashSet<String>, Integer> result4 = AproiriUtil.dataSelection(
				result3, 0.08, myAproiri.getTranDataSize());
		System.out.println(result4);
		System.out.println("****Step6:****");
		HashMap<HashSet<String>, Integer> result5 = AproiriUtil
				.calcNextCandidateData(result4, 3);
		System.out.println(result5);
		System.out.println("****Step7:****");
		HashMap<HashSet<String>, Integer> result6 = AproiriUtil.stat(
				result5.keySet(), myAproiri.getTranData());
		System.out.println(result6);
		System.out.println("****Step8:****");
		HashMap<HashSet<String>, Integer> result7 = AproiriUtil.dataSelection(
				result6, 0.08, myAproiri.getTranDataSize());
		System.out.println(result7);
		System.out.println("****Step9:****");
		HashMap<HashSet<String>, Integer> result8 = AproiriUtil
				.calcNextCandidateData(result7, 3);
		System.out.println(result8);

		test.stop();
		System.out.println("共耗时: " + test);
	}

	/**
	 * 根据输入的事务数据生成原始数据项
	 * 
	 * @param tranData
	 * @return
	 */
	public static Set<HashSet<String>> calcItemData(String[][] tranData) {
		HashSet<String> tmp = new HashSet<String>();
		Set<HashSet<String>> result = new HashSet<HashSet<String>>();
		for (String a[] : tranData) {
			for (String c : a)
				tmp.add(String.valueOf(c));
		}
		Iterator<String> i = tmp.iterator();
		while (i.hasNext()) {
			HashSet<String> h = new HashSet<String>();
			h.add(i.next());
			result.add(h);
		}
		return result;
	}

	/**
	 * 统计出现次数
	 * 
	 * @param tranData
	 * @return
	 */
	public static HashSet<String> gatherItemTimes(String[][] tranData,
			Set<HashSet<String>> keys) {
		HashSet<String> result = new HashSet<String>();
		for (String a[] : tranData) {
			for (String c : a)
				result.add(String.valueOf(c));
		}
		return result;
	}

	public String[][] getTranData() {
		return tranData;
	}

	public void setTranData(String[][] tranData) {
		this.tranData = tranData;
	}

	public double getSupport() {
		return support;
	}

	public void setSupport(double support) {
		this.support = support;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public void setItemData(Set<HashSet<String>> itemData) {
		this.itemData = itemData;
	}

	public Set<HashSet<String>> getItemData() {
		return itemData;
	}

	public int getTranDataSize() {
		return tranDataSize;
	}
}
