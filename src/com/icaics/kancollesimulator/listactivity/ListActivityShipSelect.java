package com.icaics.kancollesimulator.listactivity;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.ToolFunction;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivityShipSelect extends Activity {

	private ListView listView;
	ReadDatabase readDatabase;
	ArrayAdapter<String> adapter;
	private static String strShipClass;
	private static int clickedTextView;

	// �����ʾȫ�����ﻹ�Ǹ�װ����
	private int kai = 0;
	// ������ʾ����
	private SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		readDatabase = new ReadDatabase();

		// ������ʾ����
		sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		kai = sharedPreferences.getInt("ShipSelectKAI", 0);
		System.out.println("��ʾ�������� = " + kai);

		listView = new ListView(this);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, readDatabase.getShipName(strShipClass, kai));
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ��Զ����ɽ��洫������
				Intent intent = new Intent();
				intent.putExtra("shipClass", strShipClass);
				intent.putExtra("kai", kai);
				intent.putExtra("position", position + 1);
				intent.putExtra("clickedTextView", clickedTextView);
				System.out.println(strShipClass);
				System.out.println(position + 1);
				System.out.println(clickedTextView);
				setResult(99, intent);
				finish();
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

	// ���������б���ת���˽���ʱ����
	public static void getShipName(Context context, int position, int clickedTextView) {
		Intent intent = new Intent(context, ListActivityShipSelect.class);
		intent.setAction("com.icaics.kancollesimulator.activity.ListActivityShipSelect");
		// ��ȡ��������
		strShipClass = ToolFunction.shipClassNumToString(position);
		ListActivityShipSelect.clickedTextView = clickedTextView;
		((Activity) context).startActivityForResult(intent, 2);
	}

	// ��ʾActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.shipselect, menu);
		// ����ȡ���������ò˵�ѡ�е�ѡ��
		switch (kai) {
		case 0:
			menu.findItem(R.id.menuShipSelectAll).setChecked(true);
			break;
		case 1:
			menu.findItem(R.id.menuShipSelectKai).setChecked(true);
			break;
		default:
			break;
		}
		return true;
	}

	// ʵʱ�޸�ѡ�еĲ˵���
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onPrepareOptionsMenu(menu);
		switch (kai) {
		case 0:
			menu.findItem(R.id.menuShipSelectAll).setChecked(true);
			break;
		case 1:
			menu.findItem(R.id.menuShipSelectKai).setChecked(true);
			break;
		default:
			break;
		}
		return true;
	}

	// ActionBarMenu����¼�
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menuShipSelectAll:
			// ��ʾȫ������
			kai = 0;
			reloadShipSelectList(0);
			item.setChecked(true);
			break;
		case R.id.menuShipSelectKai:
			// ��ʾ��װ����
			kai = 1;
			reloadShipSelectList(1);
			item.setChecked(true);
			break;
		default:
			break;
		}
		System.out.println("��ʾ�������� = " + kai);
		// ������ʾ����
		sharedPreferences.edit().putInt("ShipSelectKAI", kai).commit();
		return true;
	}

	// ˢ�½����б�
	private void reloadShipSelectList(int kai) {
		adapter.clear();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, readDatabase.getShipName(strShipClass, kai));
		listView.setAdapter(adapter);
	}

}
