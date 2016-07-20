package com.example.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;

public class UserInfoUtils {
	private File file;

	public UserInfoUtils(Context context) {
		super();
		// file = new File(context.getFilesDir().getPath(), "info.txt");
		file = new File(Environment.getExternalStorageDirectory().getPath(), "info.txt");
	}

	public boolean saveInfo(String name, String pwd) {
		String result = name + "##" + pwd;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(result.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public Map<String, String> readInfo() {
		try {
			Map<String, String> maps = new HashMap<String, String>();
			FileInputStream fis = new FileInputStream(file);
			BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
			String content = bufr.readLine();
			System.out.println(content);
			String[] splits = content.split("##");
			String name = splits[0];
			String pwd = splits[1];
			maps.put("name", name);
			maps.put("pwd", pwd);
			return maps;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
