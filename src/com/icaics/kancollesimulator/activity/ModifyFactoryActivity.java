package com.icaics.kancollesimulator.activity;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterModifyFactoryList;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.umeng.analytics.MobclickAgent;

public class ModifyFactoryActivity extends Activity {

	private TextView textDataBaseVersion;
	private ListView listView;
	private AdapterModifyFactoryList listViewAdapter;
	private List<Map<String, Object>> listItems;

	ReadDatabase readDatabase;
	// ���ڱ�־
	private String strWeekday = "mon";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifyfactory);
		// ��ʾ���ݿ�汾
		readDatabase = new ReadDatabase();
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());
		// ��ȡ��ǰ����
		strWeekday = getWeekday();
		// �����б�����
		listView = (ListView) findViewById(R.id.listModifyFactory);
		listItems = readDatabase.getModifyFactory(strWeekday);
		listViewAdapter = new AdapterModifyFactoryList(this, listItems);
		listView.setAdapter(listViewAdapter);
	}

	// ��ȡ��ǰ����
	private String getWeekday() {
		String mWeek;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
		//mYear = String.valueOf(calendar.get(Calendar.YEAR));
		//mMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		//mDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		mWeek = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWeek)) {
			mWeek = "sun";
		} else if ("2".equals(mWeek)) {
			mWeek = "mon";
		} else if ("3".equals(mWeek)) {
			mWeek = "tues";
		} else if ("4".equals(mWeek)) {
			mWeek = "wed";
		} else if ("5".equals(mWeek)) {
			mWeek = "thur";
		} else if ("6".equals(mWeek)) {
			mWeek = "fri";
		} else if ("7".equals(mWeek)) {
			mWeek = "sat";
		}
		return mWeek;
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

	// ��ʾActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.modifyfactory, menu);
		// ����ȡ���������ò˵�ѡ�е�ѡ��
		switch (strWeekday) {
		case "mon":
			menu.findItem(R.id.menuModifyFactory1).setChecked(true);
			break;
		case "tues":
			menu.findItem(R.id.menuModifyFactory2).setChecked(true);
			break;
		case "wed":
			menu.findItem(R.id.menuModifyFactory3).setChecked(true);
			break;
		case "thur":
			menu.findItem(R.id.menuModifyFactory4).setChecked(true);
			break;
		case "fri":
			menu.findItem(R.id.menuModifyFactory5).setChecked(true);
			break;
		case "sat":
			menu.findItem(R.id.menuModifyFactory6).setChecked(true);
			break;
		case "sun":
			menu.findItem(R.id.menuModifyFactory7).setChecked(true);
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
		switch (strWeekday) {
		case "mon":
			menu.findItem(R.id.menuModifyFactory1).setChecked(true);
			break;
		case "tues":
			menu.findItem(R.id.menuModifyFactory2).setChecked(true);
			break;
		case "wed":
			menu.findItem(R.id.menuModifyFactory3).setChecked(true);
			break;
		case "thur":
			menu.findItem(R.id.menuModifyFactory4).setChecked(true);
			break;
		case "fir":
			menu.findItem(R.id.menuModifyFactory5).setChecked(true);
			break;
		case "sat":
			menu.findItem(R.id.menuModifyFactory6).setChecked(true);
			break;
		case "sun":
			menu.findItem(R.id.menuModifyFactory7).setChecked(true);
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
		case R.id.menuModifyFactory1:
			strWeekday = "mon";
			reloadModifyList("mon");
			item.setChecked(true);
			break;
		case R.id.menuModifyFactory2:
			strWeekday = "tues";
			reloadModifyList("tues");
			item.setChecked(true);
			break;
		case R.id.menuModifyFactory3:
			strWeekday = "wed";
			reloadModifyList("wed");
			item.setChecked(true);
			break;
		case R.id.menuModifyFactory4:
			strWeekday = "thur";
			reloadModifyList("thur");
			item.setChecked(true);
			break;
		case R.id.menuModifyFactory5:
			strWeekday = "fri";
			reloadModifyList("fri");
			item.setChecked(true);
			break;
		case R.id.menuModifyFactory6:
			strWeekday = "sat";
			reloadModifyList("sat");
			item.setChecked(true);
			break;
		case R.id.menuModifyFactory7:
			strWeekday = "sun";
			reloadModifyList("sun");
			item.setChecked(true);
			break;
		default:
			break;
		}
		System.out.println("���� = " + strWeekday);
		return true;
	}

	// ˢ��Զ���б�
	private void reloadModifyList(String strWeekday) {
		// ����ֱ��listItems=���ϣ��ᵼ���޷�ˢ��
		listItems.clear();
		listItems.addAll(readDatabase.getModifyFactory(strWeekday));
		listViewAdapter = new AdapterModifyFactoryList(this, listItems);
		listView.setAdapter(listViewAdapter);
	}

}
