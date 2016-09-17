package com.icaics.kancollesimulator.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.adapter.AdapterFormationRaid;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.WriteDatabase;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class FormationRaidActivity extends Activity {

	private TextView textDataBaseVersion;
	private ListView listView;
	private AdapterFormationRaid listViewAdapter;
	private List<Map<String, Object>> listItems;
	private TextView textNoData;

	ReadDatabase readDatabase;
	WriteDatabase writeDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formationraid);

		readDatabase = new ReadDatabase();
		writeDatabase = new WriteDatabase();
		// ��ȡ�ؼ�
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());
		// ��ȡ����
		listView = (ListView) findViewById(R.id.listFormationRaid);
		listItems = new ArrayList<Map<String, Object>>();
		// ���ÿհ���ͼ
		textNoData = (TextView) findViewById(R.id.textNoData);
		listView.setEmptyView(textNoData);
		// �󶨼�����
		listView.setOnItemClickListener(new ClickEvent());
		listView.setOnItemLongClickListener(new LongClickEvent());

	}

	// ͳ�Ʒ���
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onResume() {
		// TODO ���ڴ��Ѵ��б�������б��Ժ������޸ı������µģ���������ʱˢ��
		super.onResume();
		// ���������״ν���ʹӱ�ɽ������ʱ����ˢ�£�����ֱ��listItems=���ϣ��ᵼ���޷�ˢ�£�
		listItems.clear();
		listItems.addAll(readDatabase.getFormationRaidListMap());
		listViewAdapter = new AdapterFormationRaid(this, listItems);
		listView.setAdapter(listViewAdapter);
		// ͳ�Ʒ���
		MobclickAgent.onResume(this);
	}

	class ClickEvent implements OnItemClickListener {
		// ListItem������
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			int savedID = (int) listItems.get(position).get("savedid");
			Intent intent = new Intent(FormationRaidActivity.this, RaidFormationActivity.class);
			intent.putExtra("source", 2);
			intent.putExtra("savedid", savedID);
			startActivity(intent);
		}
	}

	class LongClickEvent implements OnItemLongClickListener {
		// ListItem����������
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(FormationRaidActivity.this);
			builder.setTitle(R.string.textDeleteRaidFormation);
			// ȷ����ť������
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ɾ��������¼
					int id = (int) listItems.get(position).get("savedid");
					if (writeDatabase.deleteFormation(id, 1)) {
						Toast.makeText(getApplicationContext(), R.string.deleteSucceed, Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), R.string.deleteFailed, Toast.LENGTH_SHORT).show();
					}
					// ˢ���б�
					listItems.clear();
					listItems.addAll(readDatabase.getFormationRaidListMap());
					listViewAdapter.notifyDataSetChanged();
					dialog.dismiss();
				}
			});
			// ȡ����ť������
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.show();
			// return false;
			return true;
		}
	}

}
