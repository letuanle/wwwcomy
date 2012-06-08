package test.apriori;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

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
	private HashSet<String> itemData;

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

	public MyAproiri(String[][] data, HashSet<String> itemData, double s,
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

		String[][] data = new String[][] { { "1", "2", "5" }, { "2", "4" },
				{ "2", "3" }, { "1", "2", "4" }, { "1", "3" }, { "2", "3" },
				{ "1", "3" }, { "1", "2", "3", "5" }, { "1", "2", "3" } };
		MyAproiri myAproiri = new MyAproiri(data);
		System.out.println(myAproiri.getItemData());
		// System.out.println(myAproiri.getTranData());
		System.out.println(myAproiri.getTranDataSize());
		// 1st Step
		HashMap<HashSet<String>, Integer> result = new HashMap<HashSet<String>, Integer>();
		AproiriUtil.stat(myAproiri.getItemData(), myAproiri.getTranData(),
				result);
		System.out.println("****Step1:****");
		for (HashSet<String> key : result.keySet())
			System.out.println("key:" + key + " - value:" + result.get(key));
		System.out.println("****Step2:****");
		HashMap<HashSet<String>, Integer> result1 = AproiriUtil.dataSelection(
				result, 0.08, myAproiri.getTranDataSize());
		System.out.println(result1);
		System.out.println("****Step3:****");
		HashMap<HashSet<String>, Integer> result2 = AproiriUtil
				.calcNextCandidateData(result1, 2);
		System.out.println(result2);
		// AproiriUtil.calcNextCandidateData(
		// AproiriUtil.calcNextCandidateData(result, 2), 3);
	}

	/**
	 * 根据输入的事务数据生成原始数据项
	 * 
	 * @param tranData
	 * @return
	 */
	public static HashSet<String> calcItemData(String[][] tranData) {
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

	public void setItemData(HashSet<String> itemData) {
		this.itemData = itemData;
	}

	public HashSet<String> getItemData() {
		return itemData;
	}

	public int getTranDataSize() {
		return tranDataSize;
	}
}
