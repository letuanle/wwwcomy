package test.apriori;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Liuxn
 * 
 */
public class AproiriUtil {
	/**
	 * 统计data中item的次数 1st Step
	 * 
	 * @param item
	 * @param data
	 * @param result
	 */
	public static void stat(TreeSet<String> itemData, String[][] tranData,
			HashMap<TreeSet<String>, Integer> result) {
		for (String item : itemData) {
			TreeSet<String> h = new TreeSet<String>();
			h.add(item);
			result.put(h, 0);
		}
		for (int i = 0; i < tranData.length; i++) {
			for (int j = 0; j < tranData[i].length; j++) {
				if (itemData.contains(tranData[i][j])) {
					TreeSet<String> h = new TreeSet<String>();
					h.add(tranData[i][j]);
					result.put(h, result.get(h) + 1);
				}
			}
		}
		// for (String key : result.keySet())
		// System.out.println("key:" + key + " - value:" + result.get(key));
	}

	/**
	 * 按照支持度筛选数据 2nd Step
	 * 
	 * 产生频繁项集
	 * 
	 * @param data
	 * @param support
	 * @param tranDataSize
	 */
	public static void dataSelection(HashMap<TreeSet<String>, Integer> data,
			double support, int tranDataSize) {
		Iterator<TreeSet<String>> i = data.keySet().iterator();
		while (i.hasNext()) {
			TreeSet<String> key = i.next();
			if ((data.get(key).doubleValue() / tranDataSize) < support)
				i.remove();
			else
				System.out.println("key:" + key + " - value:" + data.get(key));
		}
	}

	public static HashMap<TreeSet<String>, Integer> calcNextCandidateData(
			HashMap<TreeSet<String>, Integer> lastResult) {
		HashMap<TreeSet<String>, Integer> result = new HashMap<TreeSet<String>, Integer>();
		Iterator<TreeSet<String>> i = lastResult.keySet().iterator();
		
		while (i.hasNext()) {
			TreeSet<String> key = i.next();
//			result.put(new Tree, value)
		}
		return result;
	}
}
