package com.icaics.kancollesimulator.listactivity;

import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivityShipClassSelect extends Activity {

	private ListView listView;
	ReadDatabase readDatabase;
	private static int clickedTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		readDatabase = new ReadDatabase();

		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, readDatabase.getShipClass()));

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipSelect.getShipName(ListActivityShipClassSelect.this, position + 1, clickedTextView);
			}
		});

		setContentView(listView);
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
	
	// ��дonActivityResult()�����������մӽ��������б��ص����ݣ����ض�λ��Զ����ɽ���
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 99) {
			// List list = data.getExtras().getStringArrayList("pathlist");
			this.setResult(99, data);
			// �����յ����������б�ص�ʱ������Activity�������ؼ�������
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// �û�Զ��������ô�Activityʱʹ��
	public static void getShipClass(Context context, int clickedTextView) {
		Intent intent = new Intent(context, ListActivityShipClassSelect.class);
		ListActivityShipClassSelect.clickedTextView = clickedTextView;
		((Activity) context).startActivityForResult(intent, 1);
	}

}
