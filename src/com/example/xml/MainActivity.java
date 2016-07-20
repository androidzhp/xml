package com.example.xml;

import java.io.File;
import java.text.Format;
import java.util.Map;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String tag = MainActivity.class.getName();
	private EditText et_name;
	private EditText et_passwd;
	private CheckBox cb_ischeck;
	private UserInfoUtils userInfoUtils;
	private String fromatAllSize;
	private String fromatFreeSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_name = (EditText) findViewById(R.id.et_username);
		et_passwd = (EditText) findViewById(R.id.et_password);
		cb_ischeck = (CheckBox) findViewById(R.id.cb_ischeck);
		userInfoUtils = new UserInfoUtils(this);
		Map<String, String> maps = userInfoUtils.readInfo();
		if (maps != null) {
			String name = maps.get("name");
			String pwd = maps.get("pwd");
			if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
			} else {
				et_name.setText(name);
				et_passwd.setText(pwd);
				cb_ischeck.setChecked(true);
			}
		}
		EditText et1 = (EditText) findViewById(R.id.et_1);
		EditText et2 = (EditText) findViewById(R.id.et_2);
		getExternalStorageSize();
		et1.setText("总共内存大小为" + fromatAllSize);
		et2.setText("可用内存大小为" + fromatFreeSize);
	}

	private void getExternalStorageSize() {
		File file = Environment.getExternalStorageDirectory();
		long allSize = file.getTotalSpace();
		long freeSize = file.getFreeSpace();
		fromatAllSize = Formatter.formatFileSize(this, allSize);
		fromatFreeSize = Formatter.formatFileSize(this, freeSize);
	}

	public void onClick(View v) {
		String name = et_name.getText().toString().trim();
		String pwd = et_passwd.getText().toString().trim();

		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
		} else {
			if (cb_ischeck.isChecked()) {
				boolean result = userInfoUtils.saveInfo(name, pwd);
				if (result) {

					Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();

				}
			} else {
				Toast.makeText(this, "请选择保存用户名和密码", Toast.LENGTH_SHORT).show();

			}
		}
	}

}
