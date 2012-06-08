package test.procus;

import java.util.*;

public class WareHouse {
	private int max = 100;
	private List warehouse = new LinkedList();

	public int getSize() {
		return warehouse.size();
	}

	public synchronized boolean put(Object o) {
		if (getSize() > max)
			return false;
		else {
			warehouse.add(o);
			notifyAll();
		}
		return true;
	}
}
