package com.icaics.kancollesimulator.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.ScreenShot;
import com.icaics.kancollesimulator.utilty.ToolFunction;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

public class ExpCalculatorActivity extends Activity {

	private ReadDatabase readDatabase;

	private TextView textDataBaseVersion;
	private EditText editStartLevelInput, editStartExpInput, editEndLevelInput;
	private CheckBox chkIsFlag, chkIsMvp;
	private TextView textExpNeeded, textAttackNeeded;
	private Button btnShareExp, btnCalculateExp;
	private Spinner spnExpSelect, spnSAWin;
	private int startLevel, startExp, endLevel;
	private int exp;
	private int result;
	private float expSAWin = 1.2f;
	private int saSelected;
	private int finalExp;
	// ����ѡ��
	private int expSelected, mapExp = 384;
	private SharedPreferences sharedPreferences;

	final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expcalculator);

		readDatabase = new ReadDatabase();
		// ��ȡ�ؼ�
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		editStartLevelInput = (EditText) findViewById(R.id.editStartLevelInput);
		editStartExpInput = (EditText) findViewById(R.id.editStartExpInput);
		editEndLevelInput = (EditText) findViewById(R.id.editEndLevelInput);
		chkIsFlag = (CheckBox) findViewById(R.id.chkIsFlag);
		chkIsMvp = (CheckBox) findViewById(R.id.chkIsMvp);
		textExpNeeded = (TextView) findViewById(R.id.textExpNeeded);
		textAttackNeeded = (TextView) findViewById(R.id.textAttackNeeded);
		btnShareExp = (Button) findViewById(R.id.btnShareExp);
		btnCalculateExp = (Button) findViewById(R.id.btnCalculateExp);
		spnExpSelect = (Spinner) findViewById(R.id.spnExpSelect);
		spnSAWin = (Spinner) findViewById(R.id.spnSAWin);
		// �󶨰�ť������
		btnShareExp.setOnClickListener(new btnListenerShareExp());
		btnCalculateExp.setOnClickListener(new btnListenerCalculateExp());
		// ������ʼ��
		editStartLevelInput.setText("1");
		editStartExpInput.setText("100");
		editEndLevelInput.setText("30");
		// ��ʾ���ݿ�汾
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());
		// ���������ȡ
		sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		// ��һ�ο���APPĬ��ѡ��3-2A ��0��ʼ
		expSelected = sharedPreferences.getInt("ExpSelected", 11);
		System.out.println("ѡ��ĵ�ͼ = " + expSelected);
		// ��һ��Ĭ��ΪSʤ
		saSelected = sharedPreferences.getInt("ExpSAWin", 0);
		System.out.println("SAʤ = " + saSelected);

		// ��ͼ�����б������
		spnExpSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				expSelected = position;
				// ���ݿ��1��ʼ���˵�λ�ô�0��ʼ��+1����
				mapExp = readDatabase.getMapExp(expSelected + 1);
				// 1-5/5-5�ʵ�
				if (expSelected == 4) {
					Toast.makeText(getApplicationContext(), R.string.toastExp15, Toast.LENGTH_SHORT).show();
				} else if (expSelected == 23) {
					Toast.makeText(getApplicationContext(), R.string.toastExp55, Toast.LENGTH_SHORT).show();
				}
				// ����ѡ��ĵ�ͼ
				sharedPreferences.edit().putInt("ExpSelected", expSelected).commit();
				System.out.println("ѡ��ĵ�ͼ = " + expSelected);
				// ����Ѿ������ֵ����ֹ����û����ͷ���������˵�������������ѽڵ����
				resultInitialize();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		// ���õ�ͼ�����б�Ĭ��ֵ ��0��ʼ
		spnExpSelect.setSelection(expSelected);

		// SAʤ�����б������
		spnSAWin.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				saSelected = position;
				switch (position) {
				case 0:
					expSAWin = 1.2f;
					break;
				case 1:
					expSAWin = 1;
					break;
				default:
					break;
				}// ����ѡ��ĵ�ͼ
				sharedPreferences.edit().putInt("ExpSAWin", saSelected).commit();
				System.out.println("ѡ���SAʤ = " + saSelected);
				// ����Ѿ������ֵ����ֹ����û����ͷ���������˵�������������ѽڵ����
				resultInitialize();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		// ����SAʤ�����б�Ĭ��ֵ ��Sʤ��ʼ
		spnSAWin.setSelection(saSelected);

		// �콢��ѡ��仯������
		chkIsFlag.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				resultInitialize();
			}
		});

		// MVP��ѡ��仯������
		chkIsMvp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				resultInitialize();
			}
		});
		
		//�����ı������ݸı�ʱ��ս��
		editStartLevelInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				resultInitialize();
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		editEndLevelInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				resultInitialize();
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		editStartExpInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				resultInitialize();
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

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
	
	class btnListenerShareExp implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String strShare = getResources().getString(R.string.shareExp1) + spnExpSelect.getSelectedItem().toString() + getResources().getString(R.string.shareExp2)
					+ Integer.parseInt(editStartLevelInput.getText().toString()) + getResources().getString(R.string.shareExp3) + Integer.parseInt(editStartExpInput.getText().toString())
					+ getResources().getString(R.string.shareExp4) + Integer.parseInt(editEndLevelInput.getText().toString()) + getResources().getString(R.string.shareExp5) + exp
					+ getResources().getString(R.string.shareExp6) + result + getResources().getString(R.string.shareExp7);
			// ��Ļ��ͼ
			ScreenShot.shoot(ExpCalculatorActivity.this, ToolFunction.EXPSHARE, 1, 0);
			if (exp != 0 && result != 0) {
				// ����
				ToolFunction.shareSNS(mController, ExpCalculatorActivity.this, strShare, 1);
				// TODO ��Ҫ�ص�
			} else {
				Toast.makeText(getApplicationContext(), R.string.toastExpShareFailed, Toast.LENGTH_SHORT).show();
			}
		}
	}

	class btnListenerCalculateExp implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (editStartLevelInput.getText().length() == 0 || editStartExpInput.getText().length() == 0 || editEndLevelInput.getText().length() == 0) {
				textExpNeeded.setText("0");
				textAttackNeeded.setText("0");
				Toast.makeText(getApplicationContext(), R.string.toastExpEmpty, Toast.LENGTH_SHORT).show();
			} else {
				// �����ǿ�ʱ��ʼ����
				startLevel = Integer.parseInt(editStartLevelInput.getText().toString());
				startExp = Integer.parseInt(editStartExpInput.getText().toString());
				endLevel = Integer.parseInt(editEndLevelInput.getText().toString());

				if (startLevel == 150 & endLevel == 150) {
					textExpNeeded.setText(R.string.toastExpLevelTop);
					textAttackNeeded.setText(R.string.toastExpLevelTopTextView);
				} else if (startLevel >= endLevel || startLevel <= 0 || endLevel <= 0 || startLevel > 150 || endLevel > 150) {
					textExpNeeded.setText("0");
					textAttackNeeded.setText("0");
					Toast.makeText(getApplicationContext(), R.string.toastExpLevelError, Toast.LENGTH_SHORT).show();
				} else if (startLevel == 99 & endLevel == 100) {
					textExpNeeded.setText(R.string.toastExpMarry);
					textAttackNeeded.setText(R.string.toastExpMarryTextView);
				} else if (startExp > Integer.parseInt(readDatabase.getDatabaseCell("exp", "exp", "level", Integer.toString(endLevel)))) {
					textExpNeeded.setText("0");
					textAttackNeeded.setText("0");
					Toast.makeText(getApplicationContext(), R.string.toastExpExpError, Toast.LENGTH_SHORT).show();
				} else {
					// ���㾭�鼰����
					exp = readDatabase.getExpSum(startLevel, startExp, endLevel) + startExp;

					if (chkIsFlag.isChecked() & chkIsMvp.isChecked()) {
						finalExp = (int) (mapExp * 2 * 1.5f * expSAWin);
					} else if (chkIsFlag.isChecked() & !chkIsMvp.isChecked()) {
						finalExp = (int) (mapExp * 1.5f * expSAWin);
					} else if (!chkIsFlag.isChecked() & chkIsMvp.isChecked()) {
						finalExp = (int) (mapExp * 2 * expSAWin);
					} else {
						finalExp = (int) (mapExp * expSAWin);
					}

					result = (int) (exp / finalExp);
					System.out.println("��ͼ�������� = " + mapExp + "�����ξ��� = " + finalExp);

					if (result == 0) {
						result += 1;
					}
					// ��ʾ���
					textExpNeeded.setText(String.valueOf(exp));
					textAttackNeeded.setText(String.valueOf(result));
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** ʹ��SSO��Ȩ����������´��� **/
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	// ��ʼ�����鼰������
	private void resultInitialize() {
		exp = 0;
		result = 0;
		textExpNeeded.setText(String.valueOf(exp));
		textAttackNeeded.setText(String.valueOf(result));
	}

}
