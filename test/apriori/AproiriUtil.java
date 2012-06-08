package test.apriori;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

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
	public static void stat(HashSet<String> itemData, String[][] tranData,
			HashMap<HashSet<String>, Integer> result) {
		for (String item : itemData) {
			HashSet<String> h = new HashSet<String>();
			h.add(item);
			result.put(h, 0);
		}
		for (int i = 0; i < tranData.length; i++) {
			for (int j = 0; j < tranData[i].length; j++) {
				if (itemData.contains(tranData[i][j])) {
					HashSet<String> h = new HashSet<String>();
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
	public static HashMap<HashSet<String>, Integer> dataSelection(
			HashMap<HashSet<String>, Integer> data, double support,
			int tranDataSize) {
		Iterator<HashSet<String>> i = data.keySet().iterator();
		while (i.hasNext()) {
			HashSet<String> key = i.next();
			if ((data.get(key).doubleValue() / tranDataSize) < support)
				i.remove();
			// else
			// System.out.println("key:" + key + " - value:" + data.get(key));
		}
		return data;
	}

	public static HashMap<HashSet<String>, Integer> calcNextCandidateData(
			HashMap<HashSet<String>, Integer> lastResult, int time) {
		HashMap<HashSet<String>, Integer> result = new HashMap<HashSet<String>, Integer>();
		Set<HashSet<String>> s = lastResult.keySet();
		Set<HashSet<String>> tmpSet = new HashSet<HashSet<String>>();

		Iterator<HashSet<String>> i = s.iterator();
		while (i.hasNext()) {
			HashSet<String> a = i.next();
			Iterator<HashSet<String>> j = s.iterator();
			while (j.hasNext()) {
				HashSet<String> inner = j.next();
				HashSet<String> tAll = new HashSet<String>(
						CollectionUtils.union(a, inner));
				HashSet<String> yihuo = new HashSet<String>(
						CollectionUtils.disjunction(a, inner));
				if (tAll.size() == time
						&& (tAll.size() == 2 || lastResult.containsKey(yihuo))) {
					result.put(tAll, 0);
					tmpSet.add(tAll);
				}
			}
			i.remove();
		}
		// System.out.println(result);
		return result;
	}
}
