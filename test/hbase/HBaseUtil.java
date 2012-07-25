package test.hbase;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseUtil {

	private static Configuration conf = null;

	static {
		Configuration HBASE_CONFIG = new Configuration();
		// 与hbase/conf/hbase-site.xml中hbase.zookeeper.quorum配置的值相同
		HBASE_CONFIG.set("hbase.zookeeper.quorum", "1.1.7.66");
		// 与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同
		HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
		conf = HBaseConfiguration.create(HBASE_CONFIG);
	}

	public static void main(String[] agrs) throws Exception {
		// Env env = new Env("clientid", "test");
		// putObject("test1", "row1", env);
		// Env env2 = (Env) getObject("test1", "row1");
		// System.out.println(env2);
		// Student s = testGetStudentObject("studentObj", "row2");

		// System.out.println("Start output:");
		// System.out.println("id=" + s.getId());
		// System.out.println("name=" + s.getName());
		// System.out.println("gender=" + s.isGender());
		// System.out.println("Read complete");
		// testStudentString();
	}

	public static void putObject(String tableName, String rowKey,
			Serializable obj) {
		try {
			if(conf==null){
				Configuration HBASE_CONFIG = new Configuration();
				// 与hbase/conf/hbase-site.xml中hbase.zookeeper.quorum配置的值相同
				HBASE_CONFIG.set("hbase.zookeeper.quorum", "1.1.7.66");
				// 与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同
				HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
				conf = HBaseConfiguration.create(HBASE_CONFIG);
			}
			String[] familys = { "columnFamily" };
			creatTable(tableName, familys);
			HTable table = null;
			try {
				table = new HTable(conf, tableName);
				Put put = new Put(Bytes.toBytes(rowKey));
				put.add(Bytes.toBytes("columnFamily"), Bytes.toBytes("ENV"),
						TestBytes.changeToBytes(obj));
				table.put(put);
				System.out.println("insert recored name1 to table " + tableName
						+ " ok.");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (table != null)
					table.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Serializable getObject(String tableName, String rowKey)
			throws Exception {
		HTable table = null;
		Serializable tmp = null;
		try {
			table = new HTable(conf, tableName);
			Get get = new Get(rowKey.getBytes());
			Result rs = table.get(get);
			for (KeyValue kv : rs.raw()) {
				System.out.print(new String(kv.getRow()) + " ");
				System.out.print(new String(kv.getFamily()) + ":");
				System.out.print(new String(kv.getQualifier()) + " ");
				System.out.print(kv.getTimestamp() + " ");
				tmp = TestBytes.changeToObject(kv.getValue());
				System.out.println(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			table.close();
		}
		return tmp;
	}

	public static void creatTable(String tableName, String[] familys)
			throws Exception {
		HBaseAdmin admin = new HBaseAdmin(conf);
		if (admin.tableExists(tableName)) {
			System.out.println("table already exists!");
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);
			for (int i = 0; i < familys.length; i++) {
				tableDesc.addFamily(new HColumnDescriptor(familys[i]));
			}
			admin.createTable(tableDesc);
			System.out.println("create table " + tableName + " ok.");
		}
	}

	public static void deleteTable(String tableName) throws Exception {
		try {
			HBaseAdmin admin = new HBaseAdmin(conf);
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
			System.out.println("delete table " + tableName + " ok.");
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		}
	}

	public static void addRecord(String tableName, String rowKey,
			String family, String qualifier, String value) throws Exception {
		try {
			HTable table = new HTable(conf, tableName);
			Put put = new Put(Bytes.toBytes(rowKey));
			put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier),
					Bytes.toBytes(value));
			table.put(put);
			System.out.println("insert recored " + rowKey + " to table "
					+ tableName + " ok.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void delRecord(String tableName, String rowKey)
			throws IOException {
		HTable table = new HTable(conf, tableName);
		List list = new ArrayList();
		Delete del = new Delete(rowKey.getBytes());
		list.add(del);
		table.delete(list);
		System.out.println("del recored " + rowKey + " ok.");
	}

	public static void getOneRecord(String tableName, String rowKey)
			throws IOException {
		HTable table = new HTable(conf, tableName);
		Get get = new Get(rowKey.getBytes());
		Result rs = table.get(get);
		for (KeyValue kv : rs.raw()) {
			System.out.print(new String(kv.getRow()) + " ");
			System.out.print(new String(kv.getFamily()) + ":");
			System.out.print(new String(kv.getQualifier()) + " ");
			System.out.print(kv.getTimestamp() + " ");
			System.out.println(new String(kv.getValue()));
		}
	}

	public static void getAllRecord(String tableName) {
		try {
			HTable table = new HTable(conf, tableName);
			Scan s = new Scan();
			ResultScanner ss = table.getScanner(s);
			for (Result r : ss) {
				for (KeyValue kv : r.raw()) {
					System.out.print(new String(kv.getRow()) + " ");
					System.out.print(new String(kv.getFamily()) + ":");
					System.out.print(new String(kv.getQualifier()) + " ");
					System.out.print(kv.getTimestamp() + " ");
					System.out.println(new String(kv.getValue()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
