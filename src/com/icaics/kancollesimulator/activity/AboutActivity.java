package com.icaics.kancollesimulator.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.umeng.analytics.MobclickAgent;

public class AboutActivity extends Activity {

	private ReadDatabase readDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// ��ȡ�ؼ�
		TextView textAboutDatabaseVersion = (TextView) findViewById(R.id.textAboutDatabaseVersion);
		TextView textAboutDataSource = (TextView) findViewById(R.id.textAboutDataSource);
		TextView textAboutWeibo = (TextView) findViewById(R.id.textAboutWeibo);
		TextView textAboutPay = (TextView) findViewById(R.id.textAboutPay);
		// �󶨼�����
		textAboutDataSource.setOnClickListener(new btnListenerDataSource());
		textAboutWeibo.setOnClickListener(new btnListenerWeibo());
		textAboutPay.setOnClickListener(new btnListenerPay());
		// ��ʾ���ݿ�汾
		readDatabase = new ReadDatabase();
		textAboutDatabaseVersion.setText(getResources().getString(R.string.textAboutDatabaseVersion) + readDatabase.getDatebaseVersion());

	}

	// ͳ�Ʒ���
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	// ͳ�Ʒ���
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	class btnListenerDataSource implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Uri uri = Uri.parse("http://wikiwiki.jp/kancolle/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	}

	class btnListenerWeibo implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Uri uri = Uri.parse("http://weibo.com/icaics");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	}

	class btnListenerPay implements OnClickListener {
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			cm.setText("yhg000@126.com");
			Toast.makeText(getApplicationContext(), R.string.toastAboutCopied, Toast.LENGTH_SHORT).show();
		}
	}

}
