package test.apriori;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

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
	private TreeSet<String> itemData;

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

	public MyAproiri(String[][] data, TreeSet<String> itemData, double s,
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
		HashMap<TreeSet<String>, Integer> result = new HashMap<TreeSet<String>, Integer>();
		AproiriUtil.stat(myAproiri.getItemData(), myAproiri.getTranData(),
				result);
		System.out.println("****Step1:****");
		for (TreeSet<String> key : result.keySet())
			System.out.println("key:" + key + " - value:" + result.get(key));
		System.out.println("****Step2:****");
		AproiriUtil.dataSelection(result, 0.1, myAproiri.getTranDataSize());
	}

	/**
	 * 根据输入的事务数据生成原始数据项
	 * 
	 * @param tranData
	 * @return
	 */
	public static TreeSet<String> calcItemData(String[][] tranData) {
		TreeSet<String> result = new TreeSet<String>();
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

	public void setItemData(TreeSet<String> itemData) {
		this.itemData = itemData;
	}

	public TreeSet<String> getItemData() {
		return itemData;
	}

	public int getTranDataSize() {
		return tranDataSize;
	}
}
