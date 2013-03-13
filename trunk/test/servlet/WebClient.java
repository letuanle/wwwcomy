package test.servlet;

import java.util.concurrent.ConcurrentHashMap;

public class WebClient {

	private static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
	private static ConcurrentHashMap<String, Object> msgMap = new ConcurrentHashMap<String, Object>();

	static {
		map.put("a", new Object());
		map.put("b", new Object());
	}

	public static ConcurrentHashMap<String, Object> getMap() {
		return map;
	}

	public static void setMap(ConcurrentHashMap<String, Object> map) {
		WebClient.map = map;
	}

	public static ConcurrentHashMap<String, Object> getMsgMap() {
		return msgMap;
	}

	public static void setMsgMap(ConcurrentHashMap<String, Object> msgMap) {
		WebClient.msgMap = msgMap;
	}
}
