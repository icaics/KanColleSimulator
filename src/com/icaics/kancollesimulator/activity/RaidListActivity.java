package com.icaics.kancollesimulator.activity;

import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterRaidList;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class RaidListActivity extends Activity {

	private TextView textDataBaseVersion;
	private ListView listView;
	private AdapterRaidList listViewAdapter;
	private List<Map<String, Object>> listItems;

	ReadDatabase readDatabase;
	// ������
	private int orderType = 1;
	// ��������
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_raidlist);
		// ��ʾ���ݿ�汾
		readDatabase = new ReadDatabase();
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());
		// ���������ȡ
		sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		orderType = sharedPreferences.getInt("RaidListOrderType", 1);
		System.out.println("����ID = " + orderType);
		// �����б�����
		listView = (ListView) findViewById(R.id.listRaidList);
		listItems = readDatabase.getRaidListMap(orderType);
		listViewAdapter = new AdapterRaidList(this, listItems);
		listView.setAdapter(listViewAdapter);
		// �����б������
		listView.setOnItemClickListener(new ClickEvent());
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
	
	class ClickEvent implements OnItemClickListener {
		// ListItem������
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			TextView textView = (TextView) view.findViewById(R.id.textRaidNum);
			int clickedID = Integer.parseInt(textView.getText().toString());
			Intent intent = new Intent(RaidListActivity.this, RaidFormationActivity.class);
			intent.putExtra("source", 1);
			intent.putExtra("raidNum", clickedID);
			startActivity(intent);
		}
	}

	// ��ʾActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.raidmap, menu);
		// ����ȡ���������ò˵�ѡ�е�ѡ��
		switch (orderType) {
		case 1:
			menu.findItem(R.id.menuRaidMapOrderByID).setChecked(true);
			break;
		case 2:
			menu.findItem(R.id.menuRaidMapOrderByIDDesc).setChecked(true);
			break;
		case 3:
			menu.findItem(R.id.menuRaidMapOrderByTime).setChecked(true);
			break;
		case 4:
			menu.findItem(R.id.menuRaidMapOrderByTimeDesc).setChecked(true);
			break;
		case 5:
			menu.findItem(R.id.menuRaidMapOrderByOilPerM).setChecked(true);
			break;
		case 6:
			menu.findItem(R.id.menuRaidMapOrderByAmmoPerM).setChecked(true);
			break;
		case 7:
			menu.findItem(R.id.menuRaidMapOrderBySteelPerM).setChecked(true);
			break;
		case 8:
			menu.findItem(R.id.menuRaidMapOrderByAlPerM).setChecked(true);
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
		switch (orderType) {
		case 1:
			menu.findItem(R.id.menuRaidMapOrderByID).setChecked(true);
			break;
		case 2:
			menu.findItem(R.id.menuRaidMapOrderByIDDesc).setChecked(true);
			break;
		case 3:
			menu.findItem(R.id.menuRaidMapOrderByTime).setChecked(true);
			break;
		case 4:
			menu.findItem(R.id.menuRaidMapOrderByTimeDesc).setChecked(true);
			break;
		case 5:
			menu.findItem(R.id.menuRaidMapOrderByOilPerM).setChecked(true);
			break;
		case 6:
			menu.findItem(R.id.menuRaidMapOrderByAmmoPerM).setChecked(true);
			break;
		case 7:
			menu.findItem(R.id.menuRaidMapOrderBySteelPerM).setChecked(true);
			break;
		case 8:
			menu.findItem(R.id.menuRaidMapOrderByAlPerM).setChecked(true);
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
		case R.id.menuRaidMapOrderByID:
			// ��ID����
			orderType = 1;
			reloadRaidList(1);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByIDDesc:
			// ��ID����
			orderType = 2;
			reloadRaidList(2);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByTime:
			// ��ʱ������
			orderType = 3;
			reloadRaidList(3);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByTimeDesc:
			// ��ʱ�䵹��
			orderType = 4;
			reloadRaidList(4);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByOilPerM:
			// ��ȼ��/������
			orderType = 5;
			reloadRaidList(5);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByAmmoPerM:
			// ����ҩ/������
			orderType = 6;
			reloadRaidList(6);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderBySteelPerM:
			// ���ֲ�/������
			orderType = 7;
			reloadRaidList(7);
			item.setChecked(true);
			break;
		case R.id.menuRaidMapOrderByAlPerM:
			// ������/������
			orderType = 8;
			reloadRaidList(8);
			item.setChecked(true);
			break;
		default:
			break;
		}
		System.out.println("����ID = " + orderType);
		// ��������
		sharedPreferences.edit().putInt("RaidListOrderType", orderType).commit();
		return true;
	}

	// ˢ��Զ���б�
	private void reloadRaidList(int orderType) {
		// ����ֱ��listItems=���ϣ��ᵼ���޷�ˢ��
		listItems.clear();
		listItems.addAll(readDatabase.getRaidListMap(orderType));
		listViewAdapter = new AdapterRaidList(this, listItems);
		listView.setAdapter(listViewAdapter);
	}

}