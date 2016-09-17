package com.icaics.kancollesimulator.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.icaics.kancollesimulator.R;
import com.icaics.kancollesimulator.listactivity.ListActivityShipClassSelect;
import com.icaics.kancollesimulator.utilty.ReadDatabase;
import com.icaics.kancollesimulator.utilty.ScreenShot;
import com.icaics.kancollesimulator.utilty.ToolFunction;
import com.icaics.kancollesimulator.utilty.WriteDatabase;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

public class AttackFormationActivity extends Activity {

	private TextView textDataBaseVersion;
	private TextView textAttackFleetLv, textAttackFormation, textAirControlNeedDisplay, textFleetAntiAirText, textFleetAntiAirDisplay;
	// private TextView textFleetSearchDisplay;
	// ���ӿؼ�����
	private ImageView imgEquip11, imgEquip12, imgEquip13, imgEquip14;
	private TextView textClear1, textShipName1, textEquip11, textEquip12, textEquip13, textEquip14;
	private EditText editShipLv1;
	private ImageView imgEquip21, imgEquip22, imgEquip23, imgEquip24;
	private TextView textClear2, textShipName2, textEquip21, textEquip22, textEquip23, textEquip24;
	private EditText editShipLv2;
	private ImageView imgEquip31, imgEquip32, imgEquip33, imgEquip34;
	private TextView textClear3, textShipName3, textEquip31, textEquip32, textEquip33, textEquip34;
	private EditText editShipLv3;
	private ImageView imgEquip41, imgEquip42, imgEquip43, imgEquip44;
	private TextView textClear4, textShipName4, textEquip41, textEquip42, textEquip43, textEquip44;
	private EditText editShipLv4;
	private ImageView imgEquip51, imgEquip52, imgEquip53, imgEquip54;
	private TextView textClear5, textShipName5, textEquip51, textEquip52, textEquip53, textEquip54;
	private EditText editShipLv5;
	private ImageView imgEquip61, imgEquip62, imgEquip63, imgEquip64;
	private TextView textClear6, textShipName6, textEquip61, textEquip62, textEquip63, textEquip64;
	private EditText editShipLv6;
	// �������水ť
	private Button btnShare, btnSaveFormation;
	// ������������
	private Map<String, Object> attackMapDetail = new HashMap<String, Object>();
	// �����������
	private Map<String, Object> attackFormationDetail = new HashMap<String, Object>();
	// ������װ���������ݼ���
	public static Map<String, Object> shipDetail1 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail11 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail12 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail13 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail14 = new HashMap<String, Object>();
	// 2
	public static Map<String, Object> shipDetail2 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail21 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail22 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail23 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail24 = new HashMap<String, Object>();
	// 3
	public static Map<String, Object> shipDetail3 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail31 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail32 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail33 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail34 = new HashMap<String, Object>();
	// 4
	public static Map<String, Object> shipDetail4 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail41 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail42 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail43 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail44 = new HashMap<String, Object>();
	// 5
	public static Map<String, Object> shipDetail5 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail51 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail52 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail53 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail54 = new HashMap<String, Object>();
	// 6
	public static Map<String, Object> shipDetail6 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail61 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail62 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail63 = new HashMap<String, Object>();
	public static Map<String, Object> equipDetail64 = new HashMap<String, Object>();
	// ������װ����ϸ���ݱ���
	private String ship1Class;
	private int ship1Slot, ship1Slot1, ship1Slot2, ship1Slot3, ship1Slot4;
	// ���Ž�
	private String ship2Class;
	private int ship2Slot, ship2Slot1, ship2Slot2, ship2Slot3, ship2Slot4;
	// ���Ž�
	private String ship3Class;
	private int ship3Slot, ship3Slot1, ship3Slot2, ship3Slot3, ship3Slot4;
	// �ĺŽ�
	private String ship4Class;
	private int ship4Slot, ship4Slot1, ship4Slot2, ship4Slot3, ship4Slot4;
	// ��Ž�
	private String ship5Class;
	private int ship5Slot, ship5Slot1, ship5Slot2, ship5Slot3, ship5Slot4;
	// ���Ž�
	private String ship6Class;
	private int ship6Slot, ship6Slot1, ship6Slot2, ship6Slot3, ship6Slot4;
	// װ���ƿռ�����
	private int equip11AntiAir, equip12AntiAir, equip13AntiAir, equip14AntiAir;
	private int equip21AntiAir, equip22AntiAir, equip23AntiAir, equip24AntiAir;
	private int equip31AntiAir, equip32AntiAir, equip33AntiAir, equip34AntiAir;
	private int equip41AntiAir, equip42AntiAir, equip43AntiAir, equip44AntiAir;
	private int equip51AntiAir, equip52AntiAir, equip53AntiAir, equip54AntiAir;
	private int equip61AntiAir, equip62AntiAir, equip63AntiAir, equip64AntiAir;
	// private int equip11Search, equip12Search, equip13Search, equip14Search;
	// private int equip21Search, equip22Search, equip23Search, equip24Search;
	// private int equip31Search, equip32Search, equip33Search, equip34Search;
	// private int equip41Search, equip42Search, equip43Search, equip44Search;
	// ���ӱ�ɡ��ȼ����ƿռ���
	private String strAttackFormation;
	private int intFleetLv = 0;
	private int intShipLv1 = 0, intShipLv2 = 0, intShipLv3 = 0, intShipLv4 = 0, intShipLv5 = 0, intShipLv6 = 0;
	private int intFleetAntiAir = 0;
	// private int intFleetSearch = 0;
	private int intShipCount = 0;
	// ����
	ReadDatabase readDatabase;
	WriteDatabase writeDatabase;
	private int attackMapNum;
	private String attackMapName;
	private int fleetAntiAirRequire;
	// �����Ѵ������ɽ����������
	private int savedID;
	private String savedName = "";
	// ����
	final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
	// �����ȱ��
	private SharedPreferences sharedPreferences;
	private int isAce = 0;

	// TODO onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attackformation);

		// ���ݿ��ʼ��
		readDatabase = new ReadDatabase();
		writeDatabase = new WriteDatabase();

		// �ؼ���ʼ��
		textDataBaseVersion = (TextView) findViewById(R.id.textDataBaseVersion);
		textAirControlNeedDisplay = (TextView) findViewById(R.id.textAirControlRequireDisplay);
		textFleetAntiAirText = (TextView)findViewById(R.id.textFleetAntiAirText);
		textFleetAntiAirDisplay = (TextView) findViewById(R.id.textFleetAntiAirDisplay);
		// textFleetSearchDisplay =
		// (TextView)findViewById(R.id.textFleetSearchDisplay);
		textAttackFleetLv = (TextView) findViewById(R.id.textAttackFleetLv);
		textAttackFormation = (TextView) findViewById(R.id.textAttackFormation);
		btnShare = (Button) findViewById(R.id.btnShare);
		btnSaveFormation = (Button) findViewById(R.id.btnSaveFormation);
		// ���ӿؼ���ʼ��
		shipTextViewInitialize();
		// �������ȼ������Գ�ʼ��
		editShipLv1.setEnabled(false);
		ToolFunction.setShipNameAndLevelEnable(textShipName2, editShipLv2, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName3, editShipLv3, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName4, editShipLv4, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName5, editShipLv5, false);
		ToolFunction.setShipNameAndLevelEnable(textShipName6, editShipLv6, false);
		// װ�����ֿ����Գ�ʼ��
		ToolFunction.setEquipTextViewEnable(0, textEquip11, textEquip12, textEquip13, textEquip14);
		ToolFunction.setEquipTextViewEnable(0, textEquip21, textEquip22, textEquip23, textEquip24);
		ToolFunction.setEquipTextViewEnable(0, textEquip31, textEquip32, textEquip33, textEquip34);
		ToolFunction.setEquipTextViewEnable(0, textEquip41, textEquip42, textEquip43, textEquip44);
		ToolFunction.setEquipTextViewEnable(0, textEquip51, textEquip52, textEquip53, textEquip54);
		ToolFunction.setEquipTextViewEnable(0, textEquip61, textEquip62, textEquip63, textEquip64);
		// ��ʾ���ݿ�汾
		textDataBaseVersion.setText(readDatabase.getDatebaseVersion());

		// ��ս������HashMap
		clearHashMap();
		// ��ȡ����������
		sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		isAce = sharedPreferences.getInt("AttackFormationIsAce", 0);
		if (isAce == 1) {
			textFleetAntiAirText.setTextColor(Color.parseColor("#ED8600"));
		}
		else {
			textFleetAntiAirText.setTextColor(Color.parseColor("#FFFFFF"));
		}
		System.out.println("�Ƿ���������� = " + isAce);

		// ���մ���Intent����
		if (this.getIntent() != null) {
			Intent intent = this.getIntent();
			// �ж�������Դ
			int source = intent.getExtras().getInt("source");
			System.out.println("Intent����" + source);
			switch (source) {
			case 1:
				// ���Գ����б�
				attackMapNum = intent.getExtras().getInt("attackMapNum");
				System.out.println("��������ID" + attackMapNum);
				break;
			case 2:
				// �����Ѵ��������б�
				savedID = intent.getExtras().getInt("savedid");
				System.out.println("������ɱ����ID = " + savedID);
				// ֻ����ȡ�Ѵ��ɶ�Ӧ�ĳ�������ID
				attackFormationDetail = readDatabase.getAttackFormationDetail(savedID);
				attackMapNum = (int) attackFormationDetail.get("id");
				System.out.println("������ID��" + attackMapNum);
				// �����������Activity�е�attackDetail�������ݣ����滹��һ�Σ���ˢ��������
				attackMapDetail = readDatabase.getAttackMapDetail(attackMapNum);
				// ��ȡ�Ѵ������ɸ���ǰActivity��ر���д������
				setDataFromAttackDetail();
				// ���㽢������
				countShip();
				break;
			default:
				break;
			}
		}

		// ��ȡ�������������Ϣ
		attackMapDetail = readDatabase.getAttackMapDetail(attackMapNum);
		attackMapName = attackMapDetail.get("name").toString();
		fleetAntiAirRequire = (int) attackMapDetail.get("aircontrol");
		// ��ʾ��ͼ�����ƿ�ֵ
		textAirControlNeedDisplay.setText(String.valueOf(fleetAntiAirRequire) + "/" + String.valueOf((int)(fleetAntiAirRequire * 1.5)) + "/" + String.valueOf(fleetAntiAirRequire * 3));

		// ����Activity����
		this.setTitle(attackMapName);

		// ���ý��ӿؼ�������
		setShip1ClickListener();
		setShip2ClickListener();
		setShip3ClickListener();
		setShip4ClickListener();
		setShip5ClickListener();
		setShip6ClickListener();
		// ����ʵʱ�ȼ����������
		setEditTextChangeListener();

		// ˢ���ƿ��ж�
		isFleetAirAntiOK();

		// ����ť������
		btnShare.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (intShipCount != 0) {
					// ׼���ַ�
					String strShareString = getResources().getString(R.string.shareFormation1) + attackMapName + getResources().getString(R.string.shareFormation2);
					if (shipDetail1.size() != 0) {
						strShareString += shipDetail1.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail2.size() != 0) {
						strShareString += shipDetail2.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail3.size() != 0) {
						strShareString += shipDetail3.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail4.size() != 0) {
						strShareString += shipDetail4.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail5.size() != 0) {
						strShareString += shipDetail5.get("name") + getResources().getString(R.string.shareFormation3);
					}
					if (shipDetail6.size() != 0) {
						strShareString += shipDetail6.get("name");
					}
					// ��Ļ��ͼ null����ΪͼƬ�洢·������ɽ���·����Shot����ָ��
					ScreenShot.shoot(AttackFormationActivity.this, null, 2, intShipCount);
					// ����
					ToolFunction.shareSNS(mController, AttackFormationActivity.this, strShareString, 2);
				} else {
					Toast.makeText(getApplicationContext(), R.string.toastOneShipLeast, Toast.LENGTH_SHORT).show();
				}
			}
		});

		// TODO �������ð�ť������
		btnSaveFormation.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (intShipCount != 0) {
					AlertDialog.Builder builder = new AlertDialog.Builder(AttackFormationActivity.this);
					builder.setTitle(R.string.textEnterAttackFormationName);
					// builder.setIcon(android.R.drawable.ic_dialog_info);
					final EditText editText = new EditText(AttackFormationActivity.this);
					builder.setView(editText);
					// �����Ѵ���������
					if (savedName.length() != 0) {
						editText.setText(savedName);
					} else {
						editText.setText(R.string.textNewFormation);
					}
					editText.requestFocus();
					editText.selectAll();
					// ���Ʊ�����������
					editText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
					// ȷ����ť������
					builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ���潢������
							boolean saveResult = writeDatabase.saveAttackFormation((int) attackMapDetail.get("id"), editText.getText().toString(), intShipLv1, intShipLv2, intShipLv3, intShipLv4,
									intShipLv5, intShipLv6);
							if (saveResult) {
								Toast.makeText(AttackFormationActivity.this, R.string.toastSaveSucceed, Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(AttackFormationActivity.this, R.string.toastSaveFailed, Toast.LENGTH_SHORT).show();
							}
							dialog.dismiss();
							// AttackMapFormationActivity.this.finish();
						}
					});

					// ȡ����ť������
					builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							// AttackMapFormationActivity.this.finish();
						}
					});
					// builder.create();
					builder.show();
				} else {
					Toast.makeText(getApplicationContext(), R.string.toastOneShipLeast, Toast.LENGTH_SHORT).show();
				}
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

	// ��ʾActionBarMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.attackformation, menu);
		// ����ȡ���������ò˵�ѡ�е�ѡ��
		switch (isAce) {
		case 0:
			menu.findItem(R.id.menuAttackFormationAirNormal).setChecked(true);
			break;
		case 1:
			menu.findItem(R.id.menuAttackFormationAirAce).setChecked(true);
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
		switch (isAce) {
		case 0:
			menu.findItem(R.id.menuAttackFormationAirNormal).setChecked(true);
			break;
		case 1:
			menu.findItem(R.id.menuAttackFormationAirAce).setChecked(true);
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
		case R.id.menuAttackFormationAirNormal:
			// ������������
			isAce = 0;
			textFleetAntiAirText.setTextColor(Color.parseColor("#FFFFFF"));
			isFleetAirAntiOK();
			item.setChecked(true);
			break;
		case R.id.menuAttackFormationAirAce:
			// ����������
			isAce = 1;
			textFleetAntiAirText.setTextColor(Color.parseColor("#ED8600"));
			isFleetAirAntiOK();
			item.setChecked(true);
			break;
		default:
			break;
		}
		System.out.println("�Ƿ���������� = " + isAce);
		// ������ʾ����
		sharedPreferences.edit().putInt("AttackFormationIsAce", isAce).commit();
		return true;
	}
	
	// TODO �ص����������� װ������ʾ�ȣ�
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		/** ʹ��SSO��Ȩ����������´��� **/
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}

		// ����ֵΪ99 ��������Ϣ�ص�����
		if (resultCode == 99) {
			// ��ȡIntent����
			String shipClassTableName = data.getExtras().getString("shipClass");
			int kai = data.getExtras().getInt("kai");
			int shipPosition = data.getExtras().getInt("position");
			int clickedTextView = data.getExtras().getInt("clickedTextView");
			System.out.println(shipClassTableName);

			// ����֮ǰ����Ĳ�ͬTextViewȷ������������
			switch (clickedTextView) {
			case 1:
				// ��ý�����ϸ�ϼ�
				shipDetail1 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship1Display();
				clearEquipProperties(1);
				break;
			case 2:
				// ��ý�����ϸ�ϼ�
				shipDetail2 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship2Display();
				clearEquipProperties(2);
				break;
			case 3:
				// ��ý�����ϸ�ϼ�
				shipDetail3 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship3Display();
				clearEquipProperties(3);
				break;
			case 4:
				// ��ý�����ϸ�ϼ�
				shipDetail4 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship4Display();
				clearEquipProperties(4);
				break;
			case 5:
				// ��ý�����ϸ�ϼ�
				shipDetail5 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship5Display();
				clearEquipProperties(5);
				break;
			case 6:
				// ��ý�����ϸ�ϼ�
				shipDetail6 = readDatabase.getShipDetail(shipClassTableName, shipPosition, kai);
				ship6Display();
				clearEquipProperties(6);
				break;
			default:
				break;
			}

			// �յ������ص������¼��㽢�ӱ��
			listAttackFormation();
			// ���¼��㽢���ƿ�ֵ
			isFleetAirAntiOK();
			// ���㽢������
			countShip();

		}

		// TODO ��һ������һװ���ۻص�����
		if (resultCode == 11) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail11 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip11AntiAir = ToolFunction.countAA((int) equipDetail11.get("antiair"), ship1Slot1);
			// equip11Search = (int) equipDetail11.get("search");
			equip11Display();
			isFleetAirAntiOK();
		}
		// ��һ�����ڶ�װ���ۻص�����
		if (resultCode == 12) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail12 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip12AntiAir = ToolFunction.countAA((int) equipDetail12.get("antiair"), ship1Slot2);
			// equip12Search = (int) equipDetail12.get("search");
			equip12Display();
			isFleetAirAntiOK();
		}
		// ��һ��������װ���ۻص�����
		if (resultCode == 13) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail13 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip13AntiAir = ToolFunction.countAA((int) equipDetail13.get("antiair"), ship1Slot3);
			// equip13Search = (int) equipDetail13.get("search");
			equip13Display();
			isFleetAirAntiOK();
		}
		// ��һ��������װ���ۻص�����
		if (resultCode == 14) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail14 = readDatabase.getEquipDetail(ship1Class, equipPosition, (int) shipDetail1.get("speed"));
			equip14AntiAir = ToolFunction.countAA((int) equipDetail14.get("antiair"), ship1Slot4);
			// equip14Search = (int) equipDetail14.get("search");
			equip14Display();
			isFleetAirAntiOK();
		}

		// TODO �ڶ�������һװ���ۻص�����
		if (resultCode == 21) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail21 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip21AntiAir = ToolFunction.countAA((int) equipDetail21.get("antiair"), ship2Slot1);
			// equip21Search = (int) equipDetail21.get("search");
			equip21Display();
			isFleetAirAntiOK();
		}
		// �ڶ������ڶ�װ���ۻص�����
		if (resultCode == 22) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail22 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip22AntiAir = ToolFunction.countAA((int) equipDetail22.get("antiair"), ship2Slot2);
			// equip22Search = (int) equipDetail22.get("search");
			equip22Display();
			isFleetAirAntiOK();
		}
		// �ڶ���������װ���ۻص�����
		if (resultCode == 23) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail23 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip23AntiAir = ToolFunction.countAA((int) equipDetail23.get("antiair"), ship2Slot3);
			// equip23Search = (int) equipDetail23.get("search");
			equip23Display();
			isFleetAirAntiOK();
		}
		// �ڶ���������װ���ۻص�����
		if (resultCode == 24) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail24 = readDatabase.getEquipDetail(ship2Class, equipPosition, (int) shipDetail2.get("speed"));
			equip24AntiAir = ToolFunction.countAA((int) equipDetail24.get("antiair"), ship2Slot4);
			// equip24Search = (int) equipDetail24.get("search");
			equip24Display();
			isFleetAirAntiOK();
		}

		// TODO ����������һװ���ۻص�����
		if (resultCode == 31) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail31 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip31AntiAir = ToolFunction.countAA((int) equipDetail31.get("antiair"), ship3Slot1);
			// equip31Search = (int) equipDetail31.get("search");
			equip31Display();
			isFleetAirAntiOK();
		}
		// ���������ڶ�װ���ۻص�����
		if (resultCode == 32) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail32 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip32AntiAir = ToolFunction.countAA((int) equipDetail32.get("antiair"), ship3Slot2);
			// equip32Search = (int) equipDetail32.get("search");
			equip32Display();
			isFleetAirAntiOK();
		}
		// ������������װ���ۻص�����
		if (resultCode == 33) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail33 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip33AntiAir = ToolFunction.countAA((int) equipDetail33.get("antiair"), ship3Slot3);
			// equip33Search = (int) equipDetail33.get("search");
			equip33Display();
			isFleetAirAntiOK();
		}
		// ������������װ���ۻص�����
		if (resultCode == 34) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail34 = readDatabase.getEquipDetail(ship3Class, equipPosition, (int) shipDetail3.get("speed"));
			equip34AntiAir = ToolFunction.countAA((int) equipDetail34.get("antiair"), ship3Slot4);
			// equip34Search = (int) equipDetail34.get("search");
			equip34Display();
			isFleetAirAntiOK();
		}

		// TODO ���Ľ�����һװ���ۻص�����
		if (resultCode == 41) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail41 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip41AntiAir = ToolFunction.countAA((int) equipDetail41.get("antiair"), ship4Slot1);
			// equip41Search = (int) equipDetail41.get("search");
			equip41Display();
			isFleetAirAntiOK();
		}
		// ���Ľ����ڶ�װ���ۻص�����
		if (resultCode == 42) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail42 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip42AntiAir = ToolFunction.countAA((int) equipDetail42.get("antiair"), ship4Slot2);
			// equip42Search = (int) equipDetail42.get("search");
			equip42Display();
			isFleetAirAntiOK();
		}
		// ���Ľ�������װ���ۻص�����
		if (resultCode == 43) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail43 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip43AntiAir = ToolFunction.countAA((int) equipDetail43.get("antiair"), ship4Slot3);
			// equip43Search = (int) equipDetail43.get("search");
			equip43Display();
			isFleetAirAntiOK();
		}
		// ���Ľ�������װ���ۻص�����
		if (resultCode == 44) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail44 = readDatabase.getEquipDetail(ship4Class, equipPosition, (int) shipDetail4.get("speed"));
			equip44AntiAir = ToolFunction.countAA((int) equipDetail44.get("antiair"), ship4Slot4);
			// equip44Search = (int) equipDetail44.get("search");
			equip44Display();
			isFleetAirAntiOK();
		}

		// TODO ���形����һװ���ۻص�����
		if (resultCode == 51) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail51 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip51AntiAir = ToolFunction.countAA((int) equipDetail51.get("antiair"), ship5Slot1);
			// equip51Search = (int) equipDetail51.get("search");
			equip51Display();
			isFleetAirAntiOK();
		}
		// ���形���ڶ�װ���ۻص�����
		if (resultCode == 52) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail52 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip52AntiAir = ToolFunction.countAA((int) equipDetail52.get("antiair"), ship5Slot2);
			// equip52Search = (int) equipDetail52.get("search");
			equip52Display();
			isFleetAirAntiOK();
		}
		// ���形������װ���ۻص�����
		if (resultCode == 53) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail53 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip53AntiAir = ToolFunction.countAA((int) equipDetail53.get("antiair"), ship5Slot3);
			// equip53Search = (int) equipDetail53.get("search");
			equip53Display();
			isFleetAirAntiOK();
		}
		// ���形������װ���ۻص�����
		if (resultCode == 54) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail54 = readDatabase.getEquipDetail(ship5Class, equipPosition, (int) shipDetail5.get("speed"));
			equip54AntiAir = ToolFunction.countAA((int) equipDetail54.get("antiair"), ship5Slot4);
			// equip54Search = (int) equipDetail54.get("search");
			equip54Display();
			isFleetAirAntiOK();
		}

		// TODO ����������һװ���ۻص�����
		if (resultCode == 61) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail61 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip61AntiAir = ToolFunction.countAA((int) equipDetail61.get("antiair"), ship6Slot1);
			// equip61Search = (int) equipDetail61.get("search");
			equip61Display();
			isFleetAirAntiOK();
		}
		// ���������ڶ�װ���ۻص�����
		if (resultCode == 62) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail62 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip62AntiAir = ToolFunction.countAA((int) equipDetail62.get("antiair"), ship6Slot2);
			// equip62Search = (int) equipDetail62.get("search");
			equip62Display();
			isFleetAirAntiOK();
		}
		// ������������װ���ۻص�����
		if (resultCode == 63) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail63 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip63AntiAir = ToolFunction.countAA((int) equipDetail63.get("antiair"), ship6Slot3);
			// equip63Search = (int) equipDetail63.get("search");
			equip63Display();
			isFleetAirAntiOK();
		}
		// ������������װ���ۻص�����
		if (resultCode == 64) {
			int equipPosition = data.getExtras().getInt("position");
			equipDetail64 = readDatabase.getEquipDetail(ship6Class, equipPosition, (int) shipDetail6.get("speed"));
			equip64AntiAir = ToolFunction.countAA((int) equipDetail64.get("antiair"), ship6Slot4);
			// equip64Search = (int) equipDetail64.get("search");
			equip64Display();
			isFleetAirAntiOK();
		}

		// ����ı��򽹵��ֹ��Ļ����
		editShipLv1.clearFocus();
		editShipLv2.clearFocus();
		editShipLv3.clearFocus();
		editShipLv4.clearFocus();
		editShipLv5.clearFocus();
		editShipLv6.clearFocus();
	}

	private void equip64Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail64.get("type"))) {
			textEquip64.setText(String.valueOf(ship6Slot4) + "  " + (String) equipDetail64.get("name"));
		} else {
			textEquip64.setText((String) equipDetail64.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail64.get("type"), imgEquip64);
	}

	private void equip63Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail63.get("type"))) {
			textEquip63.setText(String.valueOf(ship6Slot3) + "  " + (String) equipDetail63.get("name"));
		} else {
			textEquip63.setText((String) equipDetail63.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail63.get("type"), imgEquip63);
	}

	private void equip62Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail62.get("type"))) {
			textEquip62.setText(String.valueOf(ship6Slot2) + "  " + (String) equipDetail62.get("name"));
		} else {
			textEquip62.setText((String) equipDetail62.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail62.get("type"), imgEquip62);
	}

	private void equip61Display() {
		// �����Ƿ�Ϊ���ػ��ж��Ƿ���ʾ������
		if (ToolFunction.isAircraft((Integer) equipDetail61.get("type"))) {
			textEquip61.setText(String.valueOf(ship6Slot1) + "  " + (String) equipDetail61.get("name"));
		} else {
			textEquip61.setText((String) equipDetail61.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail61.get("type"), imgEquip61);
	}

	private void equip54Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail54.get("type"))) {
			textEquip54.setText(String.valueOf(ship5Slot4) + "  " + (String) equipDetail54.get("name"));
		} else {
			textEquip54.setText((String) equipDetail54.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail54.get("type"), imgEquip54);
	}

	private void equip53Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail53.get("type"))) {
			textEquip53.setText(String.valueOf(ship5Slot3) + "  " + (String) equipDetail53.get("name"));
		} else {
			textEquip53.setText((String) equipDetail53.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail53.get("type"), imgEquip53);
	}

	private void equip52Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail52.get("type"))) {
			textEquip52.setText(String.valueOf(ship5Slot2) + "  " + (String) equipDetail52.get("name"));
		} else {
			textEquip52.setText((String) equipDetail52.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail52.get("type"), imgEquip52);
	}

	private void equip51Display() {
		// �����Ƿ�Ϊ���ػ��ж��Ƿ���ʾ������
		if (ToolFunction.isAircraft((Integer) equipDetail51.get("type"))) {
			textEquip51.setText(String.valueOf(ship5Slot1) + "  " + (String) equipDetail51.get("name"));
		} else {
			textEquip51.setText((String) equipDetail51.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail51.get("type"), imgEquip51);
	}

	private void equip44Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail44.get("type"))) {
			textEquip44.setText(String.valueOf(ship4Slot4) + "  " + (String) equipDetail44.get("name"));
		} else {
			textEquip44.setText((String) equipDetail44.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail44.get("type"), imgEquip44);
	}

	private void equip43Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail43.get("type"))) {
			textEquip43.setText(String.valueOf(ship4Slot3) + "  " + (String) equipDetail43.get("name"));
		} else {
			textEquip43.setText((String) equipDetail43.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail43.get("type"), imgEquip43);
	}

	private void equip42Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail42.get("type"))) {
			textEquip42.setText(String.valueOf(ship4Slot2) + "  " + (String) equipDetail42.get("name"));
		} else {
			textEquip42.setText((String) equipDetail42.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail42.get("type"), imgEquip42);
	}

	private void equip41Display() {
		// �����Ƿ�Ϊ���ػ��ж��Ƿ���ʾ������
		if (ToolFunction.isAircraft((Integer) equipDetail41.get("type"))) {
			textEquip41.setText(String.valueOf(ship4Slot1) + "  " + (String) equipDetail41.get("name"));
		} else {
			textEquip41.setText((String) equipDetail41.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail41.get("type"), imgEquip41);
	}

	private void equip34Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail34.get("type"))) {
			textEquip34.setText(String.valueOf(ship3Slot4) + "  " + (String) equipDetail34.get("name"));
		} else {
			textEquip34.setText((String) equipDetail34.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail34.get("type"), imgEquip34);
	}

	private void equip33Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail33.get("type"))) {
			textEquip33.setText(String.valueOf(ship3Slot3) + "  " + (String) equipDetail33.get("name"));
		} else {
			textEquip33.setText((String) equipDetail33.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail33.get("type"), imgEquip33);
	}

	private void equip32Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail32.get("type"))) {
			textEquip32.setText(String.valueOf(ship3Slot2) + "  " + (String) equipDetail32.get("name"));
		} else {
			textEquip32.setText((String) equipDetail32.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail32.get("type"), imgEquip32);
	}

	private void equip31Display() {
		// �����Ƿ�Ϊ���ػ��ж��Ƿ���ʾ������
		if (ToolFunction.isAircraft((Integer) equipDetail31.get("type"))) {
			textEquip31.setText(String.valueOf(ship3Slot1) + "  " + (String) equipDetail31.get("name"));
		} else {
			textEquip31.setText((String) equipDetail31.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail31.get("type"), imgEquip31);
	}

	private void equip24Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail24.get("type"))) {
			textEquip24.setText(String.valueOf(ship2Slot4) + "  " + (String) equipDetail24.get("name"));
		} else {
			textEquip24.setText((String) equipDetail24.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail24.get("type"), imgEquip24);
	}

	private void equip23Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail23.get("type"))) {
			textEquip23.setText(String.valueOf(ship2Slot3) + "  " + (String) equipDetail23.get("name"));
		} else {
			textEquip23.setText((String) equipDetail23.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail23.get("type"), imgEquip23);
	}

	private void equip22Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail22.get("type"))) {
			textEquip22.setText(String.valueOf(ship2Slot2) + "  " + (String) equipDetail22.get("name"));
		} else {
			textEquip22.setText((String) equipDetail22.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail22.get("type"), imgEquip22);
	}

	private void equip21Display() {
		// �����Ƿ�Ϊ���ػ��ж��Ƿ���ʾ������
		if (ToolFunction.isAircraft((Integer) equipDetail21.get("type"))) {
			textEquip21.setText(String.valueOf(ship2Slot1) + "  " + (String) equipDetail21.get("name"));
		} else {
			textEquip21.setText((String) equipDetail21.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail21.get("type"), imgEquip21);
	}

	private void equip14Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail14.get("type"))) {
			textEquip14.setText(String.valueOf(ship1Slot4) + "  " + (String) equipDetail14.get("name"));
		} else {
			textEquip14.setText((String) equipDetail14.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail14.get("type"), imgEquip14);
	}

	private void equip13Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail13.get("type"))) {
			textEquip13.setText(String.valueOf(ship1Slot3) + "  " + (String) equipDetail13.get("name"));
		} else {
			textEquip13.setText((String) equipDetail13.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail13.get("type"), imgEquip13);
	}

	private void equip12Display() {
		if (ToolFunction.isAircraft((Integer) equipDetail12.get("type"))) {
			textEquip12.setText(String.valueOf(ship1Slot2) + "  " + (String) equipDetail12.get("name"));
		} else {
			textEquip12.setText((String) equipDetail12.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail12.get("type"), imgEquip12);
	}

	private void equip11Display() {
		// �����Ƿ�Ϊ���ػ��ж��Ƿ���ʾ������
		if (ToolFunction.isAircraft((Integer) equipDetail11.get("type"))) {
			textEquip11.setText(String.valueOf(ship1Slot1) + "  " + (String) equipDetail11.get("name"));
		} else {
			textEquip11.setText((String) equipDetail11.get("name"));
		}
		// ��ʾװ��ͼƬ
		ToolFunction.setEquipImg((Integer) equipDetail11.get("type"), imgEquip11);
	}

	private void ship6Display() {
		// �ӽ�����������ȡ��������
		ship6Class = (String) shipDetail6.get("class");
		ship6Slot = (Integer) shipDetail6.get("slot");
		ship6Slot1 = (Integer) shipDetail6.get("slot1");
		ship6Slot2 = (Integer) shipDetail6.get("slot2");
		ship6Slot3 = (Integer) shipDetail6.get("slot3");
		ship6Slot4 = (Integer) shipDetail6.get("slot4");
		// ���ݽ���Slot�ж��·�װ�����Ƿ����
		textShipName6.setText((String) shipDetail6.get("name"));
		textEquip61.setText(String.valueOf(ship6Slot1));
		textEquip62.setText(String.valueOf(ship6Slot2));
		textEquip63.setText(String.valueOf(ship6Slot3));
		textEquip64.setText(String.valueOf(ship6Slot4));
		ToolFunction.clearEquipImage(imgEquip61, imgEquip62, imgEquip63, imgEquip64);
		ToolFunction.setEquipImageViewVisibility(ship6Slot, imgEquip61, imgEquip62, imgEquip63, imgEquip64);
		// ɾ����ť����
		textClear6.setVisibility(View.VISIBLE);
		textClear5.setVisibility(View.INVISIBLE);
		ToolFunction.setEquipTextViewVisibility(ship6Slot, textEquip61, textEquip62, textEquip63, textEquip64);
		// ���ý����ؼ�
		ToolFunction.setEquipTextViewEnable(ship6Slot, textEquip61, textEquip62, textEquip63, textEquip64);
		ToolFunction.setShipNameAndLevelEnable(textShipName6, editShipLv6, true);
	}

	private void ship5Display() {
		// �ӽ�����������ȡ��������
		ship5Class = (String) shipDetail5.get("class");
		ship5Slot = (Integer) shipDetail5.get("slot");
		ship5Slot1 = (Integer) shipDetail5.get("slot1");
		ship5Slot2 = (Integer) shipDetail5.get("slot2");
		ship5Slot3 = (Integer) shipDetail5.get("slot3");
		ship5Slot4 = (Integer) shipDetail5.get("slot4");
		// ���ݽ���Slot�ж��·�װ�����Ƿ����
		textShipName5.setText((String) shipDetail5.get("name"));
		textEquip51.setText(String.valueOf(ship5Slot1));
		textEquip52.setText(String.valueOf(ship5Slot2));
		textEquip53.setText(String.valueOf(ship5Slot3));
		textEquip54.setText(String.valueOf(ship5Slot4));
		// ����װ��ͼƬ
		ToolFunction.clearEquipImage(imgEquip51, imgEquip52, imgEquip53, imgEquip54);
		// ����װ������
		ToolFunction.setEquipImageViewVisibility(ship5Slot, imgEquip51, imgEquip52, imgEquip53, imgEquip54);
		ToolFunction.setEquipTextViewVisibility(ship5Slot, textEquip51, textEquip52, textEquip53, textEquip54);
		if (shipDetail6.size() == 0) {
			// ɾ����ť����
			textClear5.setVisibility(View.VISIBLE);
			textClear4.setVisibility(View.INVISIBLE);
			// �������Ƽ��ȼ�
			ToolFunction.setShipNameAndLevelEnable(textShipName6, editShipLv5, true);
			textShipName6.setText(R.string.textPleaseSelect);
		}
		// ���ý�������װ���ؼ�
		ToolFunction.setEquipTextViewEnable(ship5Slot, textEquip51, textEquip52, textEquip53, textEquip54);
	}

	private void ship4Display() {
		// �ӽ�����������ȡ��������
		ship4Class = (String) shipDetail4.get("class");
		ship4Slot = (Integer) shipDetail4.get("slot");
		ship4Slot1 = (Integer) shipDetail4.get("slot1");
		ship4Slot2 = (Integer) shipDetail4.get("slot2");
		ship4Slot3 = (Integer) shipDetail4.get("slot3");
		ship4Slot4 = (Integer) shipDetail4.get("slot4");
		// ���ݽ���Slot�ж��·�װ�����Ƿ����
		textShipName4.setText((String) shipDetail4.get("name"));
		textEquip41.setText(String.valueOf(ship4Slot1));
		textEquip42.setText(String.valueOf(ship4Slot2));
		textEquip43.setText(String.valueOf(ship4Slot3));
		textEquip44.setText(String.valueOf(ship4Slot4));
		// ����װ��ͼƬ
		ToolFunction.clearEquipImage(imgEquip41, imgEquip42, imgEquip43, imgEquip44);
		ToolFunction.setEquipImageViewVisibility(ship4Slot, imgEquip41, imgEquip42, imgEquip43, imgEquip44);
		// ����װ������
		ToolFunction.setEquipTextViewVisibility(ship4Slot, textEquip41, textEquip42, textEquip43, textEquip44);
		if (shipDetail5.size() == 0) {
			// ɾ����ť����
			textClear4.setVisibility(View.VISIBLE);
			textClear3.setVisibility(View.INVISIBLE);
			// �������Ƽ��ȼ�
			ToolFunction.setShipNameAndLevelEnable(textShipName5, editShipLv4, true);
			textShipName5.setText(R.string.textPleaseSelect);
		}
		// ���ý�������װ���ؼ�
		ToolFunction.setEquipTextViewEnable(ship4Slot, textEquip41, textEquip42, textEquip43, textEquip44);
	}

	private void ship3Display() {
		// �ӽ�����������ȡ��������
		ship3Class = (String) shipDetail3.get("class");
		ship3Slot = (Integer) shipDetail3.get("slot");
		ship3Slot1 = (Integer) shipDetail3.get("slot1");
		ship3Slot2 = (Integer) shipDetail3.get("slot2");
		ship3Slot3 = (Integer) shipDetail3.get("slot3");
		ship3Slot4 = (Integer) shipDetail3.get("slot4");
		// ���ݽ���Slot�ж��·�װ�����Ƿ����
		textShipName3.setText((String) shipDetail3.get("name"));
		textEquip31.setText(String.valueOf(ship3Slot1));
		textEquip32.setText(String.valueOf(ship3Slot2));
		textEquip33.setText(String.valueOf(ship3Slot3));
		textEquip34.setText(String.valueOf(ship3Slot4));
		// ����װ��ͼƬ
		ToolFunction.clearEquipImage(imgEquip31, imgEquip32, imgEquip33, imgEquip34);
		ToolFunction.setEquipImageViewVisibility(ship3Slot, imgEquip31, imgEquip32, imgEquip33, imgEquip34);
		// ����װ������
		ToolFunction.setEquipTextViewVisibility(ship3Slot, textEquip31, textEquip32, textEquip33, textEquip34);
		if (shipDetail4.size() == 0) {
			// ɾ����ť����
			textClear3.setVisibility(View.VISIBLE);
			textClear2.setVisibility(View.INVISIBLE);
			// �������Ƽ��ȼ�
			ToolFunction.setShipNameAndLevelEnable(textShipName4, editShipLv3, true);
			textShipName4.setText(R.string.textPleaseSelect);
		}
		// ���ý�������װ���ؼ�
		ToolFunction.setEquipTextViewEnable(ship3Slot, textEquip31, textEquip32, textEquip33, textEquip34);
	}

	private void ship2Display() {
		// �ӽ�����������ȡ��������
		ship2Class = (String) shipDetail2.get("class");
		ship2Slot = (Integer) shipDetail2.get("slot");
		ship2Slot1 = (Integer) shipDetail2.get("slot1");
		ship2Slot2 = (Integer) shipDetail2.get("slot2");
		ship2Slot3 = (Integer) shipDetail2.get("slot3");
		ship2Slot4 = (Integer) shipDetail2.get("slot4");
		// ���ݽ���Slot�ж��·�װ�����Ƿ����
		textShipName2.setText((String) shipDetail2.get("name"));
		textEquip21.setText(String.valueOf(ship2Slot1));
		textEquip22.setText(String.valueOf(ship2Slot2));
		textEquip23.setText(String.valueOf(ship2Slot3));
		textEquip24.setText(String.valueOf(ship2Slot4));
		// ����װ��ͼƬ
		ToolFunction.clearEquipImage(imgEquip21, imgEquip22, imgEquip23, imgEquip24);
		ToolFunction.setEquipImageViewVisibility(ship2Slot, imgEquip21, imgEquip22, imgEquip23, imgEquip24);
		// ����װ������
		ToolFunction.setEquipTextViewVisibility(ship2Slot, textEquip21, textEquip22, textEquip23, textEquip24);
		if (shipDetail3.size() == 0) {
			// ɾ����ť����
			textClear2.setVisibility(View.VISIBLE);
			textClear1.setVisibility(View.INVISIBLE);
			// �������Ƽ��ȼ�
			ToolFunction.setShipNameAndLevelEnable(textShipName3, editShipLv2, true);
			textShipName3.setText(R.string.textPleaseSelect);
		}
		// ���ý�������װ���ؼ�
		ToolFunction.setEquipTextViewEnable(ship2Slot, textEquip21, textEquip22, textEquip23, textEquip24);
	}

	private void ship1Display() {
		// �ӽ�����������ȡ��������
		ship1Class = (String) shipDetail1.get("class");
		ship1Slot = (Integer) shipDetail1.get("slot");
		ship1Slot1 = (Integer) shipDetail1.get("slot1");
		ship1Slot2 = (Integer) shipDetail1.get("slot2");
		ship1Slot3 = (Integer) shipDetail1.get("slot3");
		ship1Slot4 = (Integer) shipDetail1.get("slot4");
		// ���ݽ���Slot�ж��·�װ�����Ƿ����
		textShipName1.setText((String) shipDetail1.get("name"));
		textEquip11.setText(String.valueOf(ship1Slot1));
		textEquip12.setText(String.valueOf(ship1Slot2));
		textEquip13.setText(String.valueOf(ship1Slot3));
		textEquip14.setText(String.valueOf(ship1Slot4));
		// ����װ��ͼƬ
		ToolFunction.clearEquipImage(imgEquip11, imgEquip12, imgEquip13, imgEquip14);
		ToolFunction.setEquipImageViewVisibility(ship1Slot, imgEquip11, imgEquip12, imgEquip13, imgEquip14);
		// ����װ������
		ToolFunction.setEquipTextViewVisibility(ship1Slot, textEquip11, textEquip12, textEquip13, textEquip14);
		if (shipDetail2.size() == 0) {
			// ����ɾ����ť
			textClear1.setVisibility(View.VISIBLE);
			// �������Ƽ��ȼ�
			ToolFunction.setShipNameAndLevelEnable(textShipName2, editShipLv1, true);
			textShipName2.setText(R.string.textPleaseSelect);
		}
		// ���ý�������װ���ؼ�
		ToolFunction.setEquipTextViewEnable(ship1Slot, textEquip11, textEquip12, textEquip13, textEquip14);
	}

	// TODO ����������HashMap
	private void clearHashMap() {
		shipDetail1.clear();
		equipDetail11.clear();
		equipDetail12.clear();
		equipDetail13.clear();
		equipDetail14.clear();
		shipDetail2.clear();
		equipDetail21.clear();
		equipDetail22.clear();
		equipDetail23.clear();
		equipDetail24.clear();
		shipDetail3.clear();
		equipDetail31.clear();
		equipDetail32.clear();
		equipDetail33.clear();
		equipDetail34.clear();
		shipDetail4.clear();
		equipDetail41.clear();
		equipDetail42.clear();
		equipDetail43.clear();
		equipDetail44.clear();
		shipDetail5.clear();
		equipDetail51.clear();
		equipDetail52.clear();
		equipDetail53.clear();
		equipDetail54.clear();
		shipDetail6.clear();
		equipDetail61.clear();
		equipDetail62.clear();
		equipDetail63.clear();
		equipDetail64.clear();
	}

	// TODO ���ӿؼ���ʼ��
	private void shipTextViewInitialize() {
		// һ�Ž��ؼ�
		textClear1 = (TextView) findViewById(R.id.textClear1);
		textShipName1 = (TextView) findViewById(R.id.textShipName1);
		editShipLv1 = (EditText) findViewById(R.id.editShipLv1);
		textEquip11 = (TextView) findViewById(R.id.textEquip11);
		textEquip12 = (TextView) findViewById(R.id.textEquip12);
		textEquip13 = (TextView) findViewById(R.id.textEquip13);
		textEquip14 = (TextView) findViewById(R.id.textEquip14);
		imgEquip11 = (ImageView) findViewById(R.id.imgEquip11);
		imgEquip12 = (ImageView) findViewById(R.id.imgEquip12);
		imgEquip13 = (ImageView) findViewById(R.id.imgEquip13);
		imgEquip14 = (ImageView) findViewById(R.id.imgEquip14);
		// ���Ž��ؼ�
		textClear2 = (TextView) findViewById(R.id.textClear2);
		textShipName2 = (TextView) findViewById(R.id.textShipName2);
		editShipLv2 = (EditText) findViewById(R.id.editShipLv2);
		textEquip21 = (TextView) findViewById(R.id.textEquip21);
		textEquip22 = (TextView) findViewById(R.id.textEquip22);
		textEquip23 = (TextView) findViewById(R.id.textEquip23);
		textEquip24 = (TextView) findViewById(R.id.textEquip24);
		imgEquip21 = (ImageView) findViewById(R.id.imgEquip21);
		imgEquip22 = (ImageView) findViewById(R.id.imgEquip22);
		imgEquip23 = (ImageView) findViewById(R.id.imgEquip23);
		imgEquip24 = (ImageView) findViewById(R.id.imgEquip24);
		// ���Ž��ؼ�
		textClear3 = (TextView) findViewById(R.id.textClear3);
		textShipName3 = (TextView) findViewById(R.id.textShipName3);
		editShipLv3 = (EditText) findViewById(R.id.editShipLv3);
		textEquip31 = (TextView) findViewById(R.id.textEquip31);
		textEquip32 = (TextView) findViewById(R.id.textEquip32);
		textEquip33 = (TextView) findViewById(R.id.textEquip33);
		textEquip34 = (TextView) findViewById(R.id.textEquip34);
		imgEquip31 = (ImageView) findViewById(R.id.imgEquip31);
		imgEquip32 = (ImageView) findViewById(R.id.imgEquip32);
		imgEquip33 = (ImageView) findViewById(R.id.imgEquip33);
		imgEquip34 = (ImageView) findViewById(R.id.imgEquip34);
		// �ĺŽ��ؼ�
		textClear4 = (TextView) findViewById(R.id.textClear4);
		textShipName4 = (TextView) findViewById(R.id.textShipName4);
		editShipLv4 = (EditText) findViewById(R.id.editShipLv4);
		textEquip41 = (TextView) findViewById(R.id.textEquip41);
		textEquip42 = (TextView) findViewById(R.id.textEquip42);
		textEquip43 = (TextView) findViewById(R.id.textEquip43);
		textEquip44 = (TextView) findViewById(R.id.textEquip44);
		imgEquip41 = (ImageView) findViewById(R.id.imgEquip41);
		imgEquip42 = (ImageView) findViewById(R.id.imgEquip42);
		imgEquip43 = (ImageView) findViewById(R.id.imgEquip43);
		imgEquip44 = (ImageView) findViewById(R.id.imgEquip44);
		// ��Ž��ؼ�
		textClear5 = (TextView) findViewById(R.id.textClear5);
		textShipName5 = (TextView) findViewById(R.id.textShipName5);
		editShipLv5 = (EditText) findViewById(R.id.editShipLv5);
		textEquip51 = (TextView) findViewById(R.id.textEquip51);
		textEquip52 = (TextView) findViewById(R.id.textEquip52);
		textEquip53 = (TextView) findViewById(R.id.textEquip53);
		textEquip54 = (TextView) findViewById(R.id.textEquip54);
		imgEquip51 = (ImageView) findViewById(R.id.imgEquip51);
		imgEquip52 = (ImageView) findViewById(R.id.imgEquip52);
		imgEquip53 = (ImageView) findViewById(R.id.imgEquip53);
		imgEquip54 = (ImageView) findViewById(R.id.imgEquip54);
		// ���Ž��ؼ�
		textClear6 = (TextView) findViewById(R.id.textClear6);
		textShipName6 = (TextView) findViewById(R.id.textShipName6);
		editShipLv6 = (EditText) findViewById(R.id.editShipLv6);
		textEquip61 = (TextView) findViewById(R.id.textEquip61);
		textEquip62 = (TextView) findViewById(R.id.textEquip62);
		textEquip63 = (TextView) findViewById(R.id.textEquip63);
		textEquip64 = (TextView) findViewById(R.id.textEquip64);
		imgEquip61 = (ImageView) findViewById(R.id.imgEquip61);
		imgEquip62 = (ImageView) findViewById(R.id.imgEquip62);
		imgEquip63 = (ImageView) findViewById(R.id.imgEquip63);
		imgEquip64 = (ImageView) findViewById(R.id.imgEquip64);
	}

	// TODO ����һ�Ž��ؼ�������
	private void setShip1ClickListener() {
		// ����һ�Ž���������¼�
		textShipName1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipClassSelect.getShipClass(AttackFormationActivity.this, 1);
			}
		});
		// һ�Ž���յ��
		textClear1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear1, textShipName1, editShipLv1, imgEquip11, textEquip11, imgEquip12, textEquip12, imgEquip13, textEquip13, imgEquip14, textEquip14, shipDetail1,
						equipDetail11, equipDetail12, equipDetail13, equipDetail14);
				textShipName2.setEnabled(false);
				textShipName2.setText("");
				// ���װ���ƿ�����ֵ
				clearEquipProperties(1);
				// ˢ���ƿ�ֵ
				isFleetAirAntiOK();
				// ɾ�����������¼��㽢�ӱ��
				listAttackFormation();
				// ���㽢������
				countShip();
			}
		});
		// һ�Ž�װ����1���
		textEquip11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 11);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 11);
			}
		});
		// һ�Ž�װ����2���
		textEquip12.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 12);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 12);
			}
		});
		// һ�Ž�װ����3���
		textEquip13.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 13);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 13);
			}
		});
		// һ�Ž�װ����4���
		textEquip14.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail1.get("class"));
				intent.putExtra("speed", (int) shipDetail1.get("speed"));
				intent.putExtra("clickedTextView", 14);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 14);
			}
		});
	}

	// TODO ���ö��Ž��ؼ�������
	private void setShip2ClickListener() {
		// ���ö��Ž���������¼�
		textShipName2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipClassSelect.getShipClass(AttackFormationActivity.this, 2);
			}
		});
		// ���Ž���յ��
		textClear2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear2, textShipName2, editShipLv2, imgEquip21, textEquip21, imgEquip22, textEquip22, imgEquip23, textEquip23, imgEquip24, textEquip24, shipDetail2,
						equipDetail21, equipDetail22, equipDetail23, equipDetail24);
				textClear1.setVisibility(View.VISIBLE);
				textShipName3.setEnabled(false);
				textShipName3.setText("");
				// ���װ���ƿ�����ֵ
				clearEquipProperties(2);
				// ˢ���ƿ�ֵ
				isFleetAirAntiOK();
				// ɾ�����������¼��㽢�ӱ��
				listAttackFormation();
				// ���㽢������
				countShip();
			}
		});
		// ���Ž�װ����1���
		textEquip21.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 21);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 21);
			}
		});
		// ���Ž�װ����2���
		textEquip22.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 22);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 22);
			}
		});
		// ���Ž�װ����3���
		textEquip23.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 23);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 23);
			}
		});
		// ���Ž�װ����4���
		textEquip24.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail2.get("class"));
				intent.putExtra("speed", (int) shipDetail2.get("speed"));
				intent.putExtra("clickedTextView", 24);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 24);
			}
		});
	}

	// TODO �������Ž��ؼ�������
	private void setShip3ClickListener() {
		// �������Ž���������¼�
		textShipName3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipClassSelect.getShipClass(AttackFormationActivity.this, 3);
			}
		});
		// ���Ž���յ��
		textClear3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear3, textShipName3, editShipLv3, imgEquip31, textEquip31, imgEquip32, textEquip32, imgEquip33, textEquip33, imgEquip34, textEquip34, shipDetail3,
						equipDetail31, equipDetail32, equipDetail33, equipDetail34);
				textClear2.setVisibility(View.VISIBLE);
				textShipName4.setEnabled(false);
				textShipName4.setText("");
				// ���װ���ƿ�����ֵ
				clearEquipProperties(3);
				// ˢ���ƿ�ֵ
				isFleetAirAntiOK();
				// ɾ�����������¼��㽢�ӱ��
				listAttackFormation();
				// ���㽢������
				countShip();
			}
		});
		// ���Ž�װ����1���
		textEquip31.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 31);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 31);
			}
		});
		// ���Ž�װ����2���
		textEquip32.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 32);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 32);
			}
		});
		// ���Ž�װ����3���
		textEquip33.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 33);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 33);
			}
		});
		// ���Ž�װ����4���
		textEquip34.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail3.get("class"));
				intent.putExtra("speed", (int) shipDetail3.get("speed"));
				intent.putExtra("clickedTextView", 34);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 34);
			}
		});
	}

	// TODO �����ĺŽ��ؼ�������
	private void setShip4ClickListener() {
		// �����ĺŽ���������¼�
		textShipName4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipClassSelect.getShipClass(AttackFormationActivity.this, 4);
			}
		});
		// �ĺŽ���յ��
		textClear4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear4, textShipName4, editShipLv4, imgEquip41, textEquip41, imgEquip42, textEquip42, imgEquip43, textEquip43, imgEquip44, textEquip44, shipDetail4,
						equipDetail41, equipDetail42, equipDetail43, equipDetail44);
				textClear3.setVisibility(View.VISIBLE);
				textShipName5.setEnabled(false);
				textShipName5.setText("");
				// ���װ���ƿ�����ֵ
				clearEquipProperties(4);
				// ˢ���ƿ�ֵ
				isFleetAirAntiOK();
				// ɾ�����������¼��㽢�ӱ��
				listAttackFormation();
				// ���㽢������
				countShip();
			}
		});
		// �ĺŽ�װ����1���
		textEquip41.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 41);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 41);
			}
		});
		// �ĺŽ�װ����2���
		textEquip42.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 42);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 42);
			}
		});
		// �ĺŽ�װ����3���
		textEquip43.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 43);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 43);
			}
		});
		// �ĺŽ�װ����4���
		textEquip44.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail4.get("class"));
				intent.putExtra("speed", (int) shipDetail4.get("speed"));
				intent.putExtra("clickedTextView", 44);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 44);
			}
		});
	}

	// TODO ������Ž��ؼ�������
	private void setShip5ClickListener() {
		// ������Ž���������¼�
		textShipName5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipClassSelect.getShipClass(AttackFormationActivity.this, 5);
			}
		});
		// ��Ž���յ��
		textClear5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear5, textShipName5, editShipLv5, imgEquip51, textEquip51, imgEquip52, textEquip52, imgEquip53, textEquip53, imgEquip54, textEquip54, shipDetail5,
						equipDetail51, equipDetail52, equipDetail53, equipDetail54);
				textClear4.setVisibility(View.VISIBLE);
				textShipName6.setEnabled(false);
				textShipName6.setText("");
				// ���װ���ƿ�����ֵ
				clearEquipProperties(5);
				// ˢ���ƿ�ֵ
				isFleetAirAntiOK();
				// ɾ�����������¼��㽢�ӱ��
				listAttackFormation();
				// ���㽢������
				countShip();
			}
		});
		// ��Ž�װ����1���
		textEquip51.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 51);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 51);
			}
		});
		// ��Ž�װ����2���
		textEquip52.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 52);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 52);
			}
		});
		// ��Ž�װ����3���
		textEquip53.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 53);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 53);
			}
		});
		// ��Ž�װ����4���
		textEquip54.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail5.get("class"));
				intent.putExtra("speed", (int) shipDetail5.get("speed"));
				intent.putExtra("clickedTextView", 54);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 54);
			}
		});
	}

	// TODO �������Ž��ؼ�������
	private void setShip6ClickListener() {
		// �������Ž���������¼�
		textShipName6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO ���ý��������б��еľ�̬�������������������б�Activity
				ListActivityShipClassSelect.getShipClass(AttackFormationActivity.this, 6);
			}
		});
		// ���Ž���յ��
		textClear6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToolFunction.clearShipWiget(textClear6, textShipName6, editShipLv6, imgEquip61, textEquip61, imgEquip62, textEquip62, imgEquip63, textEquip63, imgEquip64, textEquip64, shipDetail6,
						equipDetail61, equipDetail62, equipDetail63, equipDetail64);
				textClear5.setVisibility(View.VISIBLE);
				// ���װ���ƿ�����ֵ
				clearEquipProperties(6);
				// ˢ���ƿ�ֵ
				isFleetAirAntiOK();
				// ɾ�����������¼��㽢�ӱ��
				listAttackFormation();
				// ���㽢������
				countShip();
			}
		});
		// ���Ž�װ����1���
		textEquip61.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 61);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 61);
			}
		});
		// ���Ž�װ����2���
		textEquip62.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 62);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 62);
			}
		});
		// ���Ž�װ����3���
		textEquip63.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 63);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 63);
			}
		});
		// ���Ž�װ����4���
		textEquip64.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("shipClass", (String) shipDetail6.get("class"));
				intent.putExtra("speed", (int) shipDetail6.get("speed"));
				intent.putExtra("clickedTextView", 64);
				intent.setClass(AttackFormationActivity.this, EquipSelectActivity.class);
				startActivityForResult(intent, 64);
			}
		});
	}

	// TODO ʵʱ�ȼ�������
	private void setEditTextChangeListener() {

		editShipLv1.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv1.getText())) {
					intShipLv1 = Integer.parseInt(editShipLv1.getText().toString());
				} else {
					intShipLv1 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textAttackFleetLv.setText(String.valueOf(intFleetLv));
			}
		});

		editShipLv2.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv2.getText())) {
					intShipLv2 = Integer.parseInt(editShipLv2.getText().toString());
				} else {
					intShipLv2 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textAttackFleetLv.setText(String.valueOf(intFleetLv));
			}
		});

		editShipLv3.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv3.getText())) {
					intShipLv3 = Integer.parseInt(editShipLv3.getText().toString());
				} else {
					intShipLv3 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textAttackFleetLv.setText(String.valueOf(intFleetLv));
			}
		});

		editShipLv4.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv4.getText())) {
					intShipLv4 = Integer.parseInt(editShipLv4.getText().toString());
				} else {
					intShipLv4 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textAttackFleetLv.setText(String.valueOf(intFleetLv));
			}
		});

		editShipLv5.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv5.getText())) {
					intShipLv5 = Integer.parseInt(editShipLv5.getText().toString());
				} else {
					intShipLv5 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textAttackFleetLv.setText(String.valueOf(intFleetLv));
			}
		});

		editShipLv6.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(editShipLv6.getText())) {
					intShipLv6 = Integer.parseInt(editShipLv6.getText().toString());
				} else {
					intShipLv6 = 0;
				}
				intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
				textAttackFleetLv.setText(String.valueOf(intFleetLv));
			}
		});

	}

	// TODO �յ������ص������¼��㽢�ӱ�ɣ��ƶ����������У�
	private void listAttackFormation() {
		strAttackFormation = "";
		if (shipDetail1.get("class") != null) {
			strAttackFormation += ((String) shipDetail1.get("class") + " ");
		}
		if (shipDetail2.get("class") != null) {
			strAttackFormation += ((String) shipDetail2.get("class") + " ");
		}
		if (shipDetail3.get("class") != null) {
			strAttackFormation += ((String) shipDetail3.get("class") + " ");
		}
		if (shipDetail4.get("class") != null) {
			strAttackFormation += ((String) shipDetail4.get("class") + " ");
		}
		if (shipDetail5.get("class") != null) {
			strAttackFormation += ((String) shipDetail5.get("class") + " ");
		}
		if (shipDetail6.get("class") != null) {
			strAttackFormation += ((String) shipDetail6.get("class") + " ");
		}
		textAttackFormation.setText(strAttackFormation);
	}

	// TODO ��⽢���ƿգ��ƶ����������У�
	private void isFleetAirAntiOK() {
		intFleetAntiAir = 0;
		// �ж�װ������Ϊ�ɻ���������ƿ�ֵ
		if (equipDetail11.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail11.get("type") == 8 || (int) equipDetail11.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip11AntiAir + 25);
				}
				else if ((int) equipDetail11.get("type") == 9 || (int) equipDetail11.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip11AntiAir + 3);
				}
				else if ((int) equipDetail11.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip11AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail11.size() != 0 && ((int) equipDetail11.get("type") >= 8 && (int) equipDetail11.get("type") <= 10 || (int) equipDetail11.get("type") == 13)) {
					intFleetAntiAir += equip11AntiAir;
				}
			}
		}
		//
		if (equipDetail12.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail12.get("type") == 8 || (int) equipDetail12.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip12AntiAir + 25);
				}
				else if ((int) equipDetail12.get("type") == 9 || (int) equipDetail12.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip12AntiAir + 3);
				}
				else if ((int) equipDetail12.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip12AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail12.size() != 0 && ((int) equipDetail12.get("type") >= 8 && (int) equipDetail12.get("type") <= 10 || (int) equipDetail12.get("type") == 13)) {
					intFleetAntiAir += equip12AntiAir;
				}
			}
		}
		//
		if (equipDetail13.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail13.get("type") == 8 || (int) equipDetail13.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip13AntiAir + 25);
				}
				else if ((int) equipDetail13.get("type") == 9 || (int) equipDetail13.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip13AntiAir + 3);
				}
				else if ((int) equipDetail13.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip13AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail13.size() != 0 && ((int) equipDetail13.get("type") >= 8 && (int) equipDetail13.get("type") <= 10 || (int) equipDetail13.get("type") == 13)) {
					intFleetAntiAir += equip13AntiAir;
				}
			}
		}
		//
		if (equipDetail14.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail14.get("type") == 8 || (int) equipDetail14.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip14AntiAir + 25);
				}
				else if ((int) equipDetail14.get("type") == 9 || (int) equipDetail14.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip14AntiAir + 3);
				}
				else if ((int) equipDetail14.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip14AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail14.size() != 0 && ((int) equipDetail14.get("type") >= 8 && (int) equipDetail14.get("type") <= 10 || (int) equipDetail14.get("type") == 13)) {
					intFleetAntiAir += equip14AntiAir;
				}
			}
		}
		//
		if (equipDetail21.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail21.get("type") == 8 || (int) equipDetail21.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip21AntiAir + 25);
				}
				else if ((int) equipDetail21.get("type") == 9 || (int) equipDetail21.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip21AntiAir + 3);
				}
				else if ((int) equipDetail21.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip21AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail21.size() != 0 && ((int) equipDetail21.get("type") >= 8 && (int) equipDetail21.get("type") <= 10 || (int) equipDetail21.get("type") == 13)) {
					intFleetAntiAir += equip21AntiAir;
				}
			}
		}
		//
		if (equipDetail22.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail22.get("type") == 8 || (int) equipDetail22.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip22AntiAir + 25);
				}
				else if ((int) equipDetail22.get("type") == 9 || (int) equipDetail22.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip22AntiAir + 3);
				}
				else if ((int) equipDetail22.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip22AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail22.size() != 0 && ((int) equipDetail22.get("type") >= 8 && (int) equipDetail22.get("type") <= 10 || (int) equipDetail22.get("type") == 13)) {
					intFleetAntiAir += equip22AntiAir;
				}
			}
		}
		//
		if (equipDetail23.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail23.get("type") == 8 || (int) equipDetail23.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip23AntiAir + 25);
				}
				else if ((int) equipDetail23.get("type") == 9 || (int) equipDetail23.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip23AntiAir + 3);
				}
				else if ((int) equipDetail23.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip23AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail23.size() != 0 && ((int) equipDetail23.get("type") >= 8 && (int) equipDetail23.get("type") <= 10 || (int) equipDetail23.get("type") == 13)) {
					intFleetAntiAir += equip23AntiAir;
				}
			}
		}
		//
		if (equipDetail24.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail24.get("type") == 8 || (int) equipDetail24.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip24AntiAir + 25);
				}
				else if ((int) equipDetail24.get("type") == 9 || (int) equipDetail24.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip24AntiAir + 3);
				}
				else if ((int) equipDetail24.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip24AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail24.size() != 0 && ((int) equipDetail24.get("type") >= 8 && (int) equipDetail24.get("type") <= 10 || (int) equipDetail24.get("type") == 13)) {
					intFleetAntiAir += equip24AntiAir;
				}
			}
		}
		//
		if (equipDetail31.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail31.get("type") == 8 || (int) equipDetail31.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip31AntiAir + 25);
				}
				else if ((int) equipDetail31.get("type") == 9 || (int) equipDetail31.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip31AntiAir + 3);
				}
				else if ((int) equipDetail31.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip31AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail31.size() != 0 && ((int) equipDetail31.get("type") >= 8 && (int) equipDetail31.get("type") <= 10 || (int) equipDetail31.get("type") == 13)) {
					intFleetAntiAir += equip31AntiAir;
				}
			}
		}
		//
		if (equipDetail32.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail32.get("type") == 8 || (int) equipDetail32.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip32AntiAir + 25);
				}
				else if ((int) equipDetail32.get("type") == 9 || (int) equipDetail32.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip32AntiAir + 3);
				}
				else if ((int) equipDetail32.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip32AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail32.size() != 0 && ((int) equipDetail32.get("type") >= 8 && (int) equipDetail32.get("type") <= 10 || (int) equipDetail32.get("type") == 13)) {
					intFleetAntiAir += equip32AntiAir;
				}
			}
		}
		//
		if (equipDetail33.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail33.get("type") == 8 || (int) equipDetail33.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip33AntiAir + 25);
				}
				else if ((int) equipDetail33.get("type") == 9 || (int) equipDetail33.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip33AntiAir + 3);
				}
				else if ((int) equipDetail33.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip33AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail33.size() != 0 && ((int) equipDetail33.get("type") >= 8 && (int) equipDetail33.get("type") <= 10 || (int) equipDetail33.get("type") == 13)) {
					intFleetAntiAir += equip33AntiAir;
				}
			}
		}
		//
		if (equipDetail34.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail34.get("type") == 8 || (int) equipDetail34.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip34AntiAir + 25);
				}
				else if ((int) equipDetail34.get("type") == 9 || (int) equipDetail34.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip34AntiAir + 3);
				}
				else if ((int) equipDetail34.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip34AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail34.size() != 0 && ((int) equipDetail34.get("type") >= 8 && (int) equipDetail34.get("type") <= 10 || (int) equipDetail34.get("type") == 13)) {
					intFleetAntiAir += equip34AntiAir;
				}
			}
		}
		//
		if (equipDetail41.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail41.get("type") == 8 || (int) equipDetail41.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip41AntiAir + 25);
				}
				else if ((int) equipDetail41.get("type") == 9 || (int) equipDetail41.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip41AntiAir + 3);
				}
				else if ((int) equipDetail41.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip41AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail41.size() != 0 && ((int) equipDetail41.get("type") >= 8 && (int) equipDetail41.get("type") <= 10 || (int) equipDetail41.get("type") == 13)) {
					intFleetAntiAir += equip41AntiAir;
				}
			}
		}
		//
		if (equipDetail42.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail42.get("type") == 8 || (int) equipDetail42.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip42AntiAir + 25);
				}
				else if ((int) equipDetail42.get("type") == 9 || (int) equipDetail42.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip42AntiAir + 3);
				}
				else if ((int) equipDetail42.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip42AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail42.size() != 0 && ((int) equipDetail42.get("type") >= 8 && (int) equipDetail42.get("type") <= 10 || (int) equipDetail42.get("type") == 13)) {
					intFleetAntiAir += equip42AntiAir;
				}
			}
		}
		//
		if (equipDetail43.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail43.get("type") == 8 || (int) equipDetail43.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip43AntiAir + 25);
				}
				else if ((int) equipDetail43.get("type") == 9 || (int) equipDetail43.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip43AntiAir + 3);
				}
				else if ((int) equipDetail43.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip43AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail43.size() != 0 && ((int) equipDetail43.get("type") >= 8 && (int) equipDetail43.get("type") <= 10 || (int) equipDetail43.get("type") == 13)) {
					intFleetAntiAir += equip43AntiAir;
				}
			}
		}
		//
		if (equipDetail44.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail44.get("type") == 8 || (int) equipDetail44.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip44AntiAir + 25);
				}
				else if ((int) equipDetail44.get("type") == 9 || (int) equipDetail44.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip44AntiAir + 3);
				}
				else if ((int) equipDetail44.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip44AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail44.size() != 0 && ((int) equipDetail44.get("type") >= 8 && (int) equipDetail44.get("type") <= 10 || (int) equipDetail44.get("type") == 13)) {
					intFleetAntiAir += equip44AntiAir;
				}
			}
		}
		//
		if (equipDetail51.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail51.get("type") == 8 || (int) equipDetail51.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip51AntiAir + 25);
				}
				else if ((int) equipDetail51.get("type") == 9 || (int) equipDetail51.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip51AntiAir + 3);
				}
				else if ((int) equipDetail51.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip51AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail51.size() != 0 && ((int) equipDetail51.get("type") >= 8 && (int) equipDetail51.get("type") <= 10 || (int) equipDetail51.get("type") == 13)) {
					intFleetAntiAir += equip51AntiAir;
				}
			}
		}
		//
		if (equipDetail52.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail52.get("type") == 8 || (int) equipDetail52.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip52AntiAir + 25);
				}
				else if ((int) equipDetail52.get("type") == 9 || (int) equipDetail52.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip52AntiAir + 3);
				}
				else if ((int) equipDetail52.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip52AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail52.size() != 0 && ((int) equipDetail52.get("type") >= 8 && (int) equipDetail52.get("type") <= 10 || (int) equipDetail52.get("type") == 13)) {
					intFleetAntiAir += equip52AntiAir;
				}
			}
		}
		//
		if (equipDetail53.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail53.get("type") == 8 || (int) equipDetail53.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip53AntiAir + 25);
				}
				else if ((int) equipDetail53.get("type") == 9 || (int) equipDetail53.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip53AntiAir + 3);
				}
				else if ((int) equipDetail53.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip53AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail53.size() != 0 && ((int) equipDetail53.get("type") >= 8 && (int) equipDetail53.get("type") <= 10 || (int) equipDetail53.get("type") == 13)) {
					intFleetAntiAir += equip53AntiAir;
				}
			}
		}
		//
		if (equipDetail54.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail54.get("type") == 8 || (int) equipDetail54.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip54AntiAir + 25);
				}
				else if ((int) equipDetail54.get("type") == 9 || (int) equipDetail54.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip54AntiAir + 3);
				}
				else if ((int) equipDetail54.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip54AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail54.size() != 0 && ((int) equipDetail54.get("type") >= 8 && (int) equipDetail54.get("type") <= 10 || (int) equipDetail54.get("type") == 13)) {
					intFleetAntiAir += equip54AntiAir;
				}
			}
		}
		//
		if (equipDetail61.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail61.get("type") == 8 || (int) equipDetail51.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip61AntiAir + 25);
				}
				else if ((int) equipDetail61.get("type") == 9 || (int) equipDetail61.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip61AntiAir + 3);
				}
				else if ((int) equipDetail61.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip61AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail61.size() != 0 && ((int) equipDetail61.get("type") >= 8 && (int) equipDetail61.get("type") <= 10 || (int) equipDetail61.get("type") == 13)) {
					intFleetAntiAir += equip61AntiAir;
				}
			}
		}
		//
		if (equipDetail62.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail62.get("type") == 8 || (int) equipDetail62.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip62AntiAir + 25);
				}
				else if ((int) equipDetail62.get("type") == 9 || (int) equipDetail62.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip62AntiAir + 3);
				}
				else if ((int) equipDetail62.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip62AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail62.size() != 0 && ((int) equipDetail62.get("type") >= 8 && (int) equipDetail62.get("type") <= 10 || (int) equipDetail62.get("type") == 13)) {
					intFleetAntiAir += equip62AntiAir;
				}
			}
		}
		//
		if (equipDetail63.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail63.get("type") == 8 || (int) equipDetail63.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip63AntiAir + 25);
				}
				else if ((int) equipDetail63.get("type") == 9 || (int) equipDetail63.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip63AntiAir + 3);
				}
				else if ((int) equipDetail63.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip63AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail63.size() != 0 && ((int) equipDetail63.get("type") >= 8 && (int) equipDetail63.get("type") <= 10 || (int) equipDetail63.get("type") == 13)) {
					intFleetAntiAir += equip63AntiAir;
				}
			}
		}
		//
		if (equipDetail64.size() != 0) {
			if (isAce == 1) {
				// ����������
				if ((int) equipDetail64.get("type") == 8 || (int) equipDetail64.get("type") == 39) {
					// ��ս+25
					intFleetAntiAir += (equip64AntiAir + 25);
				}
				else if ((int) equipDetail64.get("type") == 9 || (int) equipDetail64.get("type") == 10) {
					// ��������+3
					intFleetAntiAir += (equip64AntiAir + 3);
				}
				else if ((int) equipDetail64.get("type") == 13) {
					// ˮ��+9
					intFleetAntiAir += (equip64AntiAir + 9);
				}
			}
			else {
				// ������������
				if (equipDetail64.size() != 0 && ((int) equipDetail64.get("type") >= 8 && (int) equipDetail64.get("type") <= 10 || (int) equipDetail64.get("type") == 13)) {
					intFleetAntiAir += equip64AntiAir;
				}
			}
		}
		// �ƿ��ж�
		if (intFleetAntiAir < fleetAntiAirRequire * 1.5) {
			// �ƿվ���/ɥʧ
			textFleetAntiAirDisplay.setTextColor(Color.parseColor("#CC0000"));
		} else if (intFleetAntiAir >= fleetAntiAirRequire * 1.5 && intFleetAntiAir < fleetAntiAirRequire * 3) {
			// ��������
			textFleetAntiAirDisplay.setTextColor(Color.parseColor("#ED8600"));
		} else if (intFleetAntiAir >= fleetAntiAirRequire * 3) {
			// �ƿ�ȷ��
			textFleetAntiAirDisplay.setTextColor(Color.parseColor("#009900"));
		}

		textFleetAntiAirDisplay.setText(String.valueOf(intFleetAntiAir));
	}

	// TODO ��ȡ�Ѵ������ɸ���ǰActivity��ر���д������
	private void setDataFromAttackDetail() {
		savedName = attackFormationDetail.get("savedname").toString();
		// ��������
		float ship1 = (float) attackFormationDetail.get("ship1");
		float ship2 = (float) attackFormationDetail.get("ship2");
		float ship3 = (float) attackFormationDetail.get("ship3");
		float ship4 = (float) attackFormationDetail.get("ship4");
		float ship5 = (float) attackFormationDetail.get("ship5");
		float ship6 = (float) attackFormationDetail.get("ship6");
		// ����ID��Ϊ0ʱ�Ų�ѯ������ϸ
		if (ship1 != 0f) {
			String shipClassString = attackFormationDetail.get("ship1class").toString();
			shipDetail1 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship1), 0);
			ship1Display();
			intShipLv1 = (int) attackFormationDetail.get("ship1lv");
			editShipLv1.setText(String.valueOf(intShipLv1));
			textShipName2.setEnabled(true);
		}
		if (ship2 != 0f) {
			String shipClassString = attackFormationDetail.get("ship2class").toString();
			shipDetail2 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship2), 0);
			ship2Display();
			intShipLv2 = (int) attackFormationDetail.get("ship2lv");
			editShipLv2.setText(String.valueOf(intShipLv2));
			textShipName3.setEnabled(true);
		}
		if (ship3 != 0f) {
			String shipClassString = attackFormationDetail.get("ship3class").toString();
			shipDetail3 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship3), 0);
			ship3Display();
			intShipLv3 = (int) attackFormationDetail.get("ship3lv");
			editShipLv3.setText(String.valueOf(intShipLv3));
			textShipName4.setEnabled(true);
		}
		if (ship4 != 0f) {
			String shipClassString = attackFormationDetail.get("ship4class").toString();
			shipDetail4 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship4), 0);
			ship4Display();
			intShipLv4 = (int) attackFormationDetail.get("ship4lv");
			editShipLv4.setText(String.valueOf(intShipLv4));
			textShipName5.setEnabled(true);
		}
		if (ship5 != 0f) {
			String shipClassString = attackFormationDetail.get("ship5class").toString();
			shipDetail5 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship5), 0);
			ship5Display();
			intShipLv5 = (int) attackFormationDetail.get("ship5lv");
			editShipLv5.setText(String.valueOf(intShipLv5));
			textShipName6.setEnabled(true);
		}
		if (ship6 != 0f) {
			String shipClassString = attackFormationDetail.get("ship6class").toString();
			shipDetail6 = readDatabase.getShipDetail(shipClassString, readDatabase.getShipPositionInTable(shipClassString, ship6), 0);
			ship6Display();
			intShipLv6 = (int) attackFormationDetail.get("ship6lv");
			editShipLv6.setText(String.valueOf(intShipLv6));
		}
		// װ������
		if ((int) attackFormationDetail.get("equip11") != 0) {
			equipDetail11 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip11"));
		}
		if ((int) attackFormationDetail.get("equip12") != 0) {
			equipDetail12 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip12"));
		}
		if ((int) attackFormationDetail.get("equip13") != 0) {
			equipDetail13 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip13"));
		}
		if ((int) attackFormationDetail.get("equip14") != 0) {
			equipDetail14 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip14"));
		}
		if ((int) attackFormationDetail.get("equip21") != 0) {
			equipDetail21 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip21"));
		}
		if ((int) attackFormationDetail.get("equip22") != 0) {
			equipDetail22 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip22"));
		}
		if ((int) attackFormationDetail.get("equip23") != 0) {
			equipDetail23 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip23"));
		}
		if ((int) attackFormationDetail.get("equip24") != 0) {
			equipDetail24 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip24"));
		}
		if ((int) attackFormationDetail.get("equip31") != 0) {
			equipDetail31 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip31"));
		}
		if ((int) attackFormationDetail.get("equip32") != 0) {
			equipDetail32 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip32"));
		}
		if ((int) attackFormationDetail.get("equip33") != 0) {
			equipDetail33 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip33"));
		}
		if ((int) attackFormationDetail.get("equip34") != 0) {
			equipDetail34 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip34"));
		}
		if ((int) attackFormationDetail.get("equip41") != 0) {
			equipDetail41 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip41"));
		}
		if ((int) attackFormationDetail.get("equip42") != 0) {
			equipDetail42 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip42"));
		}
		if ((int) attackFormationDetail.get("equip43") != 0) {
			equipDetail43 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip43"));
		}
		if ((int) attackFormationDetail.get("equip44") != 0) {
			equipDetail44 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip44"));
		}
		if ((int) attackFormationDetail.get("equip51") != 0) {
			equipDetail51 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip51"));
		}
		if ((int) attackFormationDetail.get("equip52") != 0) {
			equipDetail52 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip52"));
		}
		if ((int) attackFormationDetail.get("equip53") != 0) {
			equipDetail53 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip53"));
		}
		if ((int) attackFormationDetail.get("equip54") != 0) {
			equipDetail54 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip54"));
		}
		if ((int) attackFormationDetail.get("equip61") != 0) {
			equipDetail61 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip61"));
		}
		if ((int) attackFormationDetail.get("equip62") != 0) {
			equipDetail62 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip62"));
		}
		if ((int) attackFormationDetail.get("equip63") != 0) {
			equipDetail63 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip63"));
		}
		if ((int) attackFormationDetail.get("equip64") != 0) {
			equipDetail64 = readDatabase.getEquipDetail((int) attackFormationDetail.get("equip64"));
		}
		// 1
		if (equipDetail11.size() != 0) {
			equip11AntiAir = ToolFunction.countAA((int) equipDetail11.get("antiair"), ship1Slot1);
			equip11Display();
		}
		if (equipDetail12.size() != 0) {
			equip12AntiAir = ToolFunction.countAA((int) equipDetail12.get("antiair"), ship1Slot2);
			equip12Display();
		}
		if (equipDetail13.size() != 0) {
			equip13AntiAir = ToolFunction.countAA((int) equipDetail13.get("antiair"), ship1Slot3);
			equip13Display();
		}
		if (equipDetail14.size() != 0) {
			equip14AntiAir = ToolFunction.countAA((int) equipDetail14.get("antiair"), ship1Slot4);
			equip14Display();
		}
		// 2
		if (equipDetail21.size() != 0) {
			equip21AntiAir = ToolFunction.countAA((int) equipDetail21.get("antiair"), ship2Slot1);
			equip21Display();
		}
		if (equipDetail22.size() != 0) {
			equip22AntiAir = ToolFunction.countAA((int) equipDetail22.get("antiair"), ship2Slot2);
			equip22Display();
		}
		if (equipDetail23.size() != 0) {
			equip23AntiAir = ToolFunction.countAA((int) equipDetail23.get("antiair"), ship2Slot3);
			equip23Display();
		}
		if (equipDetail24.size() != 0) {
			equip24AntiAir = ToolFunction.countAA((int) equipDetail24.get("antiair"), ship2Slot4);
			equip24Display();
		}
		// 3
		if (equipDetail31.size() != 0) {
			equip31AntiAir = ToolFunction.countAA((int) equipDetail31.get("antiair"), ship3Slot1);
			equip31Display();
		}
		if (equipDetail32.size() != 0) {
			equip32AntiAir = ToolFunction.countAA((int) equipDetail32.get("antiair"), ship3Slot2);
			equip32Display();
		}
		if (equipDetail33.size() != 0) {
			equip33AntiAir = ToolFunction.countAA((int) equipDetail33.get("antiair"), ship3Slot3);
			equip33Display();
		}
		if (equipDetail34.size() != 0) {
			equip34AntiAir = ToolFunction.countAA((int) equipDetail34.get("antiair"), ship3Slot4);
			equip34Display();
		}
		// 4
		if (equipDetail41.size() != 0) {
			equip41AntiAir = ToolFunction.countAA((int) equipDetail41.get("antiair"), ship4Slot1);
			equip41Display();
		}
		if (equipDetail42.size() != 0) {
			equip42AntiAir = ToolFunction.countAA((int) equipDetail42.get("antiair"), ship4Slot2);
			equip42Display();
		}
		if (equipDetail43.size() != 0) {
			equip43AntiAir = ToolFunction.countAA((int) equipDetail43.get("antiair"), ship4Slot3);
			equip43Display();
		}
		if (equipDetail44.size() != 0) {
			equip44AntiAir = ToolFunction.countAA((int) equipDetail44.get("antiair"), ship4Slot4);
			equip44Display();
		}
		// 5
		if (equipDetail51.size() != 0) {
			equip51AntiAir = ToolFunction.countAA((int) equipDetail51.get("antiair"), ship5Slot1);
			equip51Display();
		}
		if (equipDetail52.size() != 0) {
			equip52AntiAir = ToolFunction.countAA((int) equipDetail52.get("antiair"), ship5Slot2);
			equip52Display();
		}
		if (equipDetail53.size() != 0) {
			equip53AntiAir = ToolFunction.countAA((int) equipDetail53.get("antiair"), ship5Slot3);
			equip53Display();
		}
		if (equipDetail54.size() != 0) {
			equip54AntiAir = ToolFunction.countAA((int) equipDetail54.get("antiair"), ship5Slot4);
			equip54Display();
		}
		// 6
		if (equipDetail61.size() != 0) {
			equip61AntiAir = ToolFunction.countAA((int) equipDetail61.get("antiair"), ship6Slot1);
			equip61Display();
		}
		if (equipDetail62.size() != 0) {
			equip62AntiAir = ToolFunction.countAA((int) equipDetail62.get("antiair"), ship6Slot2);
			equip62Display();
		}
		if (equipDetail63.size() != 0) {
			equip63AntiAir = ToolFunction.countAA((int) equipDetail63.get("antiair"), ship6Slot3);
			equip63Display();
		}
		if (equipDetail64.size() != 0) {
			equip64AntiAir = ToolFunction.countAA((int) equipDetail64.get("antiair"), ship6Slot4);
			equip64Display();
		}
		// ���Ѵ��ɽ������ʱ���¼��㽢�ӱ��
		listAttackFormation();
		// ��ȡ���ӵȼ�
		intFleetLv = intShipLv1 + intShipLv2 + intShipLv3 + intShipLv4 + intShipLv5 + intShipLv6;
		textAttackFleetLv.setText(String.valueOf(intFleetLv));
		// ��⽢���ƿ��Ƿ����Ҫ��
		// �����ظ���ȡ��ͼ�����ƿ�ֵ����Ϊ���Ѵ�����б�����������ڻ�õ�ͼ��ϸ��Ϣ֮ǰ��
		fleetAntiAirRequire = (int) attackMapDetail.get("aircontrol");
		isFleetAirAntiOK();
		// ����ı��򽹵��ֹ��Ļ����
		editShipLv2.clearFocus();
		editShipLv3.clearFocus();
		editShipLv4.clearFocus();
		editShipLv5.clearFocus();
		editShipLv6.clearFocus();
		// ����������
		editShipLv1.requestFocus();
		editShipLv1.clearFocus();
	}

	// TODO ���װ����ֵ
	private void clearEquipProperties(int groupNum) {
		switch (groupNum) {
		case 1:
			equip11AntiAir = 0;
			equip12AntiAir = 0;
			equip13AntiAir = 0;
			equip14AntiAir = 0;
			// equip11Search = 0;
			// equip12Search = 0;
			// equip13Search = 0;
			// equip14Search = 0;
			break;
		case 2:
			equip21AntiAir = 0;
			equip22AntiAir = 0;
			equip23AntiAir = 0;
			equip24AntiAir = 0;
			// equip21Search = 0;
			// equip22Search = 0;
			// equip23Search = 0;
			// equip24Search = 0;
			break;
		case 3:
			equip31AntiAir = 0;
			equip32AntiAir = 0;
			equip33AntiAir = 0;
			equip34AntiAir = 0;
			// equip31Search = 0;
			// equip32Search = 0;
			// equip33Search = 0;
			// equip34Search = 0;
			break;
		case 4:
			equip41AntiAir = 0;
			equip42AntiAir = 0;
			equip43AntiAir = 0;
			equip44AntiAir = 0;
			// equip41Search = 0;
			// equip42Search = 0;
			// equip43Search = 0;
			// equip44Search = 0;
			break;
		case 5:
			equip51AntiAir = 0;
			equip52AntiAir = 0;
			equip53AntiAir = 0;
			equip54AntiAir = 0;
			// equip51Search = 0;
			// equip52Search = 0;
			// equip53Search = 0;
			// equip54Search = 0;
			break;
		case 6:
			equip61AntiAir = 0;
			equip62AntiAir = 0;
			equip63AntiAir = 0;
			equip64AntiAir = 0;
			// equip61Search = 0;
			// equip62Search = 0;
			// equip63Search = 0;
			// equip64Search = 0;
			break;
		default:
			break;
		}
	}

	// TODO ���㽢������
	private void countShip() {
		intShipCount = 0;
		if (shipDetail1.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail2.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail3.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail4.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail5.size() != 0) {
			intShipCount += 1;
		}
		if (shipDetail6.size() != 0) {
			intShipCount += 1;
		}
	}

}
