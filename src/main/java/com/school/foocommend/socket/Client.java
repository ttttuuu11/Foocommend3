package com.school.foocommend.socket;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.net.Socket;

public class Client {
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	Socket socket = null;
	String serverIp = "localhost";
	int serverPort = 9999;
	String storeMenu;
	List<Map<String, Object>> result;

	public Client(String storeMenu) {
		this.storeMenu = storeMenu;

		try {
			// 서버 연결
			socket = new Socket(serverIp, serverPort); // socket(),connect();
			log.info("서버에 연결되었습니다.");
			log.info(serverIp + " : " + serverPort);

			RecommendDataSender recommendDataSender = new RecommendDataSender(socket, storeMenu);
			recommendDataSender.start();
			recommendDataSender.join();
			result = recommendDataSender.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}// catch
	}
	
	public List<Map<String, Object>> getResult() {
		return result;
	}

}