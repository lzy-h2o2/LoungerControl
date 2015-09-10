package com.lzy.loungercontrol.dosremote;

import java.io.IOException;

public class DosRemotePc {
	public void act(String msg) {
		String command = msg.substring(1, msg.length());
		System.out.println(command);
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}