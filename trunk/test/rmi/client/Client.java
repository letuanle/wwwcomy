package test.rmi.client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import test.rmi.remote.IRemoteTimer;
import test.rmi.remote.TestRemote;

public class Client {
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			IRemoteTimer t = (IRemoteTimer) Naming
					.lookup("//1.1.7.81/time");
			for (int i = 0; i < 10; i++)
				System.out.println("Perfect time =" + t.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
