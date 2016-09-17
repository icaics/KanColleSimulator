package com.icaics.kancollesimulator.utilty;

import java.util.Map;
import com.icaics.kancollesimulator.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToolFunction {

	private static int bb = 0, bbv = 0, cv = 0, cvl = 0, av = 0, ca = 0, cav = 0;
	private static int cl = 0, clt = 0, dd = 0, ss = 0, ssv = 0, other = 0;

	// ���ݿ��ַ
	public static String dbName = "data.s3db";
	@SuppressLint("SdCardPath")
	public static String DATABASE_PATH = "/data/data/com.icaics.kancollesimulator/databases/";

	// ����ƽ̨KEY
	public static String WXAPPID = "wx6614acc2cdd96a2b";
	public static String WXAPPSECRET = "c779ac870d865a8b4d20c54c832105a9";
	public static String QQAPPID = "1103496276";
	public static String QQAPPKEY = "XLeyreOFi4S0Vefo";

	// ����ͼƬ����λ��
	public static String EXPSHARE = "sdcard/KanColleSimulatorExp.png";
	public static String FORMATIONSHARE = "sdcard/KanColleSimulatorFormation.png";

	// TODO ����7 to "shipcav" ���ڴ���ӽ�������ҳ���͸� ������ �����б�ҳ�����֣�ת��Ϊ���ݿ��е��ֶ����ڲ���
	public static String shipClassNumToString(int shipClassNum) {
		String shipClassString = "";
		switch (shipClassNum) {
		case 1:
			shipClassString = "shipbb";
			break;
		case 2:
			shipClassString = "shipbbv";
			break;
		case 3:
			shipClassString = "shipcv";
			break;
		case 4:
			shipClassString = "shipcvl";
			break;
		case 5:
			shipClassString = "shipav";
			break;
		case 6:
			shipClassString = "shipca";
			break;
		case 7:
			shipClassString = "shipcav";
			break;
		case 8:
			shipClassString = "shipcl";
			break;
		case 9:
			shipClassString = "shipclt";
			break;
		case 10:
			shipClassString = "shipdd";
			break;
		case 11:
			shipClassString = "shipss";
			break;
		case 12:
			shipClassString = "shipssv";
			break;
		case 13:
			shipClassString = "shipother";
			break;
		default:
			shipClassString = "shipother";
		}
		return shipClassString;
	}

	// TODO ����BB to "shipbb" ����Զ����ɽ�����Ѵ��ɴ���ñ������ʱ��ֻ�ܻ�ý���ID��ȡ�������Ǹ����е����
	// ��ý���ID - ����ID���type��BB�� - ����BB������ת��Ϊ"shipbb" -
	// ͨ��shipbbʹ��getShipDetail(String shipClass, int shipPosition)
	public static String shipClassToTableName(String shipClass) {
		String tableName = "shipother";
		switch (shipClass) {
		case "BB":
			tableName = "shipbb";
			break;
		case "BBV":
			tableName = "shipbbv";
			break;
		case "CV":
			tableName = "shipcv";
			break;
		case "CVL":
			tableName = "shipcvl";
			break;
		case "AV":
			tableName = "shipav";
			break;
		case "CA":
			tableName = "shipca";
			break;
		case "CAV":
			tableName = "shipcav";
			break;
		case "CL":
			tableName = "shipcl";
			break;
		case "CLT":
			tableName = "shipclt";
			break;
		case "DD":
			tableName = "shipdd";
			break;
		case "SS":
			tableName = "shipss";
			break;
		case "SSV":
			tableName = "shipbb";
			break;
		case "OTHER":
			tableName = "shipother";
			break;
		default:
			tableName = "shipother";
			break;
		}
		return tableName;
	}

	// TODO �ж�װ�������Ƿ�Ϊ���ػ����Ƿ���ʾ��������
	public static boolean isAircraft(int equipType) {
		if ((equipType >= 8 && equipType <= 13) || equipType == 23 || equipType == 24 || equipType == 36 || equipType == 39) {
			return true;
		} else {
			return false;
		}
	}

	// TODO ����װ��ͼƬ
	public static void setEquipImg(int equipType, ImageView imgEquip) {
		switch (equipType) {
		case 1:
			imgEquip.setImageResource(R.drawable.equip1);
			break;
		case 2:
			imgEquip.setImageResource(R.drawable.equip2);
			break;
		case 3:
			imgEquip.setImageResource(R.drawable.equip3);
			break;
		case 4:
			imgEquip.setImageResource(R.drawable.equip4);
			break;
		case 5:
			imgEquip.setImageResource(R.drawable.equip2);
			break;
		case 6:
			imgEquip.setImageResource(R.drawable.equip6);
			break;
		case 7:
			imgEquip.setImageResource(R.drawable.equip7);
			break;
		case 8:
			imgEquip.setImageResource(R.drawable.equip8);
			break;
		case 9:
			imgEquip.setImageResource(R.drawable.equip9);
			break;
		case 10:
			imgEquip.setImageResource(R.drawable.equip10);
			break;
		case 11:
			imgEquip.setImageResource(R.drawable.equip11);
			break;
		case 12:
			imgEquip.setImageResource(R.drawable.equip12);
			break;
		case 13:
			imgEquip.setImageResource(R.drawable.equip12);
			break;
		case 14:
			imgEquip.setImageResource(R.drawable.equip14);
			break;
		case 15:
			imgEquip.setImageResource(R.drawable.equip15);
			break;
		case 16:
			imgEquip.setImageResource(R.drawable.equip16);
			break;
		case 17:
			imgEquip.setImageResource(R.drawable.equip17);
			break;
		case 18:
			imgEquip.setImageResource(R.drawable.equip18);
			break;
		case 19:
			imgEquip.setImageResource(R.drawable.equip19);
			break;
		case 20:
			imgEquip.setImageResource(R.drawable.equip20);
			break;
		case 21:
			imgEquip.setImageResource(R.drawable.equip7);
			break;
		case 22:
			imgEquip.setImageResource(R.drawable.equip22);
			break;
		case 23:
			imgEquip.setImageResource(R.drawable.equip23);
			break;
		case 24:
			imgEquip.setImageResource(R.drawable.equip24);
			break;
		case 25:
			imgEquip.setImageResource(R.drawable.equip25);
			break;
		case 26:
			imgEquip.setImageResource(R.drawable.equip26);
			break;
		case 27:
			imgEquip.setImageResource(R.drawable.equip27);
			break;
		case 28:
			imgEquip.setImageResource(R.drawable.equip28);
			break;
		case 29:
			imgEquip.setImageResource(R.drawable.equip29);
			break;
		case 30:
			imgEquip.setImageResource(R.drawable.equip30);
			break;
		case 31:
			imgEquip.setImageResource(R.drawable.equip31);
			break;
		case 32:
			imgEquip.setImageResource(R.drawable.equip32);
			break;
		case 33:
			imgEquip.setImageResource(R.drawable.equip33);
			break;
		case 34:
			imgEquip.setImageResource(R.drawable.equip34);
			break;
		case 35:
			imgEquip.setImageResource(R.drawable.equip35);
			break;
		case 36:
			imgEquip.setImageResource(R.drawable.equip36);
			break;
		case 37:
			imgEquip.setImageResource(R.drawable.equip37);
			break;
		case 38:
			imgEquip.setImageResource(R.drawable.equip38);
			break;
		case 39:
			imgEquip.setImageResource(R.drawable.equip12);
			break;
		case 40:
			imgEquip.setImageResource(R.drawable.equip40);
			break;
		default:
			imgEquip.setImageResource(R.drawable.equip0);
			break;
		}
	}

	// TODO ����װ��ͼƬ
	public static int getEquipImg(int equipType) {
		switch (equipType) {
		case 1:
			return R.drawable.equip1;
		case 2:
			return R.drawable.equip2;
		case 3:
			return R.drawable.equip3;
		case 4:
			return R.drawable.equip4;
		case 5:
			return R.drawable.equip2;
		case 6:
			return R.drawable.equip6;
		case 7:
			return R.drawable.equip7;
		case 8:
			return R.drawable.equip8;
		case 9:
			return R.drawable.equip9;
		case 10:
			return R.drawable.equip10;
		case 11:
			return R.drawable.equip11;
		case 12:
			return R.drawable.equip12;
		case 13:
			return R.drawable.equip12;
		case 14:
			return R.drawable.equip14;
		case 15:
			return R.drawable.equip15;
		case 16:
			return R.drawable.equip16;
		case 17:
			return R.drawable.equip17;
		case 18:
			return R.drawable.equip18;
		case 19:
			return R.drawable.equip19;
		case 20:
			return R.drawable.equip20;
		case 21:
			return R.drawable.equip7;
		case 22:
			return R.drawable.equip22;
		case 23:
			return R.drawable.equip23;
		case 24:
			return R.drawable.equip24;
		case 25:
			return R.drawable.equip25;
		case 26:
			return R.drawable.equip26;
		case 27:
			return R.drawable.equip27;
		case 28:
			return R.drawable.equip28;
		case 29:
			return R.drawable.equip29;
		case 30:
			return R.drawable.equip30;
		case 31:
			return R.drawable.equip31;
		case 32:
			return R.drawable.equip32;
		case 33:
			return R.drawable.equip33;
		case 34:
			return R.drawable.equip34;
		case 35:
			return R.drawable.equip35;
		case 36:
			return R.drawable.equip36;
		case 37:
			return R.drawable.equip37;
		case 38:
			return R.drawable.equip38;
		case 39:
			return R.drawable.equip12;
		case 40:
			return R.drawable.equip40;
		default:
			return R.drawable.equip0;
		}
	}
	
	// TODO ���ý������ȼ���������Եĺ���
	public static void setShipNameAndLevelEnable(TextView textShipName, EditText editShipLv, Boolean enable) {
		if (enable) {
			textShipName.setEnabled(true);
			editShipLv.setText("0");
			editShipLv.setEnabled(true);
			editShipLv.requestFocus();
			// editShipLv.selectAll();
			// InputMethodManager imm = (InputMethodManager)
			// editShipLv.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			// imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
		} else {
			textShipName.setEnabled(false);
			editShipLv.setEnabled(false);
			editShipLv.setText("");
		}
	}

	// TODO ����װ���ɼ��Եĺ���
	public static void setEquipTextViewVisibility(int shipSlot, TextView textEquip1, TextView textEquip2, TextView textEquip3, TextView textEquip4) {
		switch (shipSlot) {
		case 0:
			textEquip1.setVisibility(View.INVISIBLE);
			textEquip2.setVisibility(View.INVISIBLE);
			textEquip3.setVisibility(View.INVISIBLE);
			textEquip4.setVisibility(View.INVISIBLE);
			break;
		case 1:
			textEquip1.setVisibility(View.VISIBLE);
			textEquip2.setVisibility(View.INVISIBLE);
			textEquip3.setVisibility(View.INVISIBLE);
			textEquip4.setVisibility(View.INVISIBLE);
			break;
		case 2:
			textEquip1.setVisibility(View.VISIBLE);
			textEquip2.setVisibility(View.VISIBLE);
			textEquip3.setVisibility(View.INVISIBLE);
			textEquip4.setVisibility(View.INVISIBLE);
			break;
		case 3:
			textEquip1.setVisibility(View.VISIBLE);
			textEquip2.setVisibility(View.VISIBLE);
			textEquip3.setVisibility(View.VISIBLE);
			textEquip4.setVisibility(View.INVISIBLE);
			break;
		case 4:
			textEquip1.setVisibility(View.VISIBLE);
			textEquip2.setVisibility(View.VISIBLE);
			textEquip3.setVisibility(View.VISIBLE);
			textEquip4.setVisibility(View.VISIBLE);
			break;
		default:
			textEquip1.setVisibility(View.INVISIBLE);
			textEquip2.setVisibility(View.INVISIBLE);
			textEquip3.setVisibility(View.INVISIBLE);
			textEquip4.setVisibility(View.INVISIBLE);
			break;
		}
	}

	// TODO �����������ʼ��װ��ͼ
	public static void clearEquipImage(ImageView imgEquip1, ImageView imgEquip2, ImageView imgEquip3, ImageView imgEquip4) {
		imgEquip1.setImageResource(R.drawable.equip0);
		imgEquip2.setImageResource(R.drawable.equip0);
		imgEquip3.setImageResource(R.drawable.equip0);
		imgEquip4.setImageResource(R.drawable.equip0);
	}

	// TODO ����װ��ͼ�ɼ��Եĺ���
	public static void setEquipImageViewVisibility(int shipSlot, ImageView imgEquip1, ImageView imgEquip2, ImageView imgEquip3, ImageView imgEquip4) {
		switch (shipSlot) {
		case 0:
			imgEquip1.setVisibility(View.INVISIBLE);
			imgEquip2.setVisibility(View.INVISIBLE);
			imgEquip3.setVisibility(View.INVISIBLE);
			imgEquip4.setVisibility(View.INVISIBLE);
			break;
		case 1:
			imgEquip1.setVisibility(View.VISIBLE);
			imgEquip2.setVisibility(View.INVISIBLE);
			imgEquip3.setVisibility(View.INVISIBLE);
			imgEquip4.setVisibility(View.INVISIBLE);
			break;
		case 2:
			imgEquip1.setVisibility(View.VISIBLE);
			imgEquip2.setVisibility(View.VISIBLE);
			imgEquip3.setVisibility(View.INVISIBLE);
			imgEquip4.setVisibility(View.INVISIBLE);
			break;
		case 3:
			imgEquip1.setVisibility(View.VISIBLE);
			imgEquip2.setVisibility(View.VISIBLE);
			imgEquip3.setVisibility(View.VISIBLE);
			imgEquip4.setVisibility(View.INVISIBLE);
			break;
		case 4:
			imgEquip1.setVisibility(View.VISIBLE);
			imgEquip2.setVisibility(View.VISIBLE);
			imgEquip3.setVisibility(View.VISIBLE);
			imgEquip4.setVisibility(View.VISIBLE);
			break;
		default:
			imgEquip1.setVisibility(View.INVISIBLE);
			imgEquip2.setVisibility(View.INVISIBLE);
			imgEquip3.setVisibility(View.INVISIBLE);
			imgEquip4.setVisibility(View.INVISIBLE);
			break;
		}
	}

	// TODO ����Slot�ж��·�װ�����Ƿ���õĺ���
	public static void setEquipTextViewEnable(int shipSlot, TextView textEquip1, TextView textEquip2, TextView textEquip3, TextView textEquip4) {
		switch (shipSlot) {
		case 0:
			textEquip1.setEnabled(false);
			textEquip2.setEnabled(false);
			textEquip3.setEnabled(false);
			textEquip4.setEnabled(false);
			break;
		case 1:
			textEquip1.setEnabled(true);
			textEquip2.setEnabled(false);
			textEquip3.setEnabled(false);
			textEquip4.setEnabled(false);
			break;
		case 2:
			textEquip1.setEnabled(true);
			textEquip2.setEnabled(true);
			textEquip3.setEnabled(false);
			textEquip4.setEnabled(false);
			break;
		case 3:
			textEquip1.setEnabled(true);
			textEquip2.setEnabled(true);
			textEquip3.setEnabled(true);
			textEquip4.setEnabled(false);
			break;
		case 4:
			textEquip1.setEnabled(true);
			textEquip2.setEnabled(true);
			textEquip3.setEnabled(true);
			textEquip4.setEnabled(true);
			break;
		default:
			textEquip1.setEnabled(false);
			textEquip2.setEnabled(false);
			textEquip3.setEnabled(false);
			textEquip4.setEnabled(false);
			break;
		}
	}

	// TODO ��հ�ť��ʼ���ؼ�״̬
	public static void clearShipWiget(TextView textClear, TextView textShipName, EditText editShipLv, ImageView imgEquip1, TextView textEquip1, ImageView imgEquip2, TextView textEquip2,
			ImageView imgEquip3, TextView textEquip3, ImageView imgEquip4, TextView textEquip4, Map<String, Object> shipDetail, Map<String, Object> equip1, Map<String, Object> equip2,
			Map<String, Object> equip3, Map<String, Object> equip4) {
		textShipName.setText(R.string.textPleaseSelect);
		imgEquip1.setImageBitmap(null);
		imgEquip2.setImageBitmap(null);
		imgEquip3.setImageBitmap(null);
		imgEquip4.setImageBitmap(null);
		editShipLv.setText("");
		editShipLv.clearFocus();
		editShipLv.setEnabled(false);
		setEquipImageViewVisibility(0, imgEquip1, imgEquip2, imgEquip3, imgEquip4);
		textEquip1.setText("");
		textEquip2.setText("");
		textEquip3.setText("");
		textEquip4.setText("");
		setEquipTextViewEnable(0, textEquip1, textEquip2, textEquip3, textEquip4);
		setEquipTextViewVisibility(4, textEquip1, textEquip2, textEquip3, textEquip4);
		// if (shipDetail.size() != 0) {
		shipDetail.clear();
		// }
		equip1.clear();
		equip2.clear();
		equip3.clear();
		equip4.clear();
		textClear.setVisibility(View.INVISIBLE);
	}

	// TODO �ж�Զ������Ƿ����Ҫ��
	public static boolean isFormationMeetsRaidRequire(Map<String, Object> raidDetail, Map<String, Object> shipDetail1, Map<String, Object> shipDetail2, Map<String, Object> shipDetail3,
			Map<String, Object> shipDetail4, Map<String, Object> shipDetail5, Map<String, Object> shipDetail6) {
		int raidNum;
		int shipNumNeeded, shipNum = 0;
		int bbNeeded, bbvNeeded, cvNeeded, cvlNeeded, avNeeded, caNeeded, cavNeeded;
		int clNeeded, cltNeeded, ddNeeded, ssNeeded, ssvNeeded, otherNeeded;
		raidNum = (int) raidDetail.get("id");
		// �������Ҫ��
		shipNumNeeded = (int) raidDetail.get("shipnum");
		bbNeeded = (int) raidDetail.get("bb");
		bbvNeeded = (int) raidDetail.get("bbv");
		cvNeeded = (int) raidDetail.get("cv");
		cvlNeeded = (int) raidDetail.get("cvl");
		avNeeded = (int) raidDetail.get("av");
		caNeeded = (int) raidDetail.get("ca");
		cavNeeded = (int) raidDetail.get("cav");
		clNeeded = (int) raidDetail.get("cl");
		cltNeeded = (int) raidDetail.get("clt");
		ddNeeded = (int) raidDetail.get("dd");
		ssNeeded = (int) raidDetail.get("ss");
		ssvNeeded = (int) raidDetail.get("ssv");
		otherNeeded = (int) raidDetail.get("other");
		// ����ϴεĸ������ֵĸ�����¼
		bb = bbv = cv = cvl = av = ca = cav = cl = clt = dd = ss = ssv = other = 0;
		// ��ñ������
		if (shipDetail1.size() != 0) {
			shipNum = 1;
			System.out.println("ship1.size != 0");
			countRaidFormation(shipDetail1, bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other);
		}
		if (shipDetail2.size() != 0) {
			shipNum = 2;
			System.out.println("ship2.size != 0");
			countRaidFormation(shipDetail2, bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other);
		}
		if (shipDetail3.size() != 0) {
			shipNum = 3;
			System.out.println("ship3.size != 0");
			countRaidFormation(shipDetail3, bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other);
		}
		if (shipDetail4.size() != 0) {
			shipNum = 4;
			System.out.println("ship4.size != 0");
			countRaidFormation(shipDetail4, bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other);
		}
		if (shipDetail5.size() != 0) {
			shipNum = 5;
			System.out.println("ship5.size != 0");
			countRaidFormation(shipDetail5, bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other);
		}
		if (shipDetail6.size() != 0) {
			shipNum = 6;
			System.out.println("ship6.size != 0");
			countRaidFormation(shipDetail6, bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other);
		}
		// �жϽ��ӱ���Ƿ����Ҫ��
		// ��ĸ��ĸǱĸ���
		if (raidNum == 15 || raidNum == 18 || raidNum == 26 || raidNum == 35) {
			if (bb >= bbNeeded && bbv >= bbvNeeded && (cv + cvl + av) >= cvNeeded && ca >= caNeeded && cav >= cavNeeded && cl >= clNeeded && clt >= cltNeeded && dd >= ddNeeded && ss >= ssNeeded
					&& ssv >= ssvNeeded && other >= otherNeeded && shipNum >= shipNumNeeded) {
				return true;
			} else {
				return false;
			}
		}
		// Ǳͧ��Ǳĸ���
		else if (raidNum == 20 || (raidNum >= 27 && raidNum <= 31)) {
			if (bb >= bbNeeded && bbv >= bbvNeeded && (cv + cvl + av) >= cvNeeded && ca >= caNeeded && cav >= cavNeeded && cl >= clNeeded && clt >= cltNeeded && dd >= ddNeeded
					&& (ss + ssv) >= ssNeeded && other >= otherNeeded && shipNum >= shipNumNeeded) {
				return true;
			} else {
				return false;
			}
		}
		// �޽��ֻ�ϵ�Զ��
		else {
			if (bb >= bbNeeded && bbv >= bbvNeeded && cv >= cvNeeded && cvl >= cvlNeeded && av >= avNeeded && ca >= caNeeded && cav >= cavNeeded && cl >= clNeeded && clt >= cltNeeded
					&& dd >= ddNeeded && ss >= ssNeeded && ssv >= ssvNeeded && other >= otherNeeded && shipNum >= shipNumNeeded) {
				return true;
			} else {
				return false;
			}
		}

	}

	// �ж�Զ������Ƿ����Ҫ�� �м��㽢�������õĺ���
	private static void countRaidFormation(Map<String, Object> shipDetail, int bb, int bbv, int cv, int cvl, int av, int ca, int cav, int cl, int clt, int dd, int ss, int ssv, int other) {
		switch (shipDetail.get("class").toString()) {
		case "BB":
			ToolFunction.bb += 1;
			break;
		case "BBV":
			ToolFunction.bbv += 1;
			break;
		case "CV":
			ToolFunction.cv += 1;
			break;
		case "CVL":
			ToolFunction.cvl += 1;
			break;
		case "AV":
			ToolFunction.av += 1;
			break;
		case "CA":
			ToolFunction.ca += 1;
			break;
		case "CAV":
			ToolFunction.cav += 1;
			break;
		case "CL":
			ToolFunction.cl += 1;
			break;
		case "CLT":
			ToolFunction.clt += 1;
			break;
		case "DD":
			ToolFunction.dd += 1;
			break;
		case "SS":
			ToolFunction.ss += 1;
			break;
		case "SSV":
			ToolFunction.ssv += 1;
			break;
		case "OTHER":
			ToolFunction.other += 1;
			break;
		default:
			break;
		}
		System.out.println("BB" + ToolFunction.bb + " BBV" + ToolFunction.bbv + " CV" + ToolFunction.cv + " CVL" + ToolFunction.cvl + " AV" + ToolFunction.av + " CA" + ToolFunction.ca + " CAV"
				+ ToolFunction.cav + " CL" + ToolFunction.cl + " CLT" + ToolFunction.clt + " DD" + ToolFunction.dd + " SS" + ToolFunction.ss + " SSV" + ToolFunction.ssv + " OTHER"
				+ ToolFunction.other);
	}

	// TODO �����ƿ�ֵ
	public static int countAA(int antiAir, int slot) {
		int aa;
		aa = (int) (antiAir * Math.sqrt(slot));
		return aa;
	}

	// TODO ����
	public static void shareSNS(UMSocialService mController, final Activity activity, String str, int type) {

		mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN, SHARE_MEDIA.QZONE, SHARE_MEDIA.TENCENT);

		// ΢�ŷ���ص�
		SnsPostListener mSnsPostListener = new SnsPostListener() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
			}

			@Override
			public void onComplete(SHARE_MEDIA platform, int stCode, SocializeEntity entity) {
				if (stCode == 200) {
					// ����ɹ�
					Toast.makeText(activity, activity.getResources().getString(R.string.toastShareOK), Toast.LENGTH_SHORT).show();
				} else {
					// ����ʧ��
					Toast.makeText(activity, activity.getResources().getString(R.string.toastShareOK) + stCode, Toast.LENGTH_SHORT).show();
				}
			}
		};
		mController.registerListener(mSnsPostListener);

		// ׼������ͼƬ
		UMImage umImage = null;
		if (type == 1) {
			umImage = new UMImage(activity.getApplication(), ToolFunction.EXPSHARE);
		} else {
			umImage = new UMImage(activity.getApplication(), ToolFunction.FORMATIONSHARE);
		}

		// ���б������QQ���� ==========
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, "1103496276", "XLeyreOFi4S0Vefo");
		qqSsoHandler.addToSocialSDK();
		// ����QQ��������
		QQShareContent qqShareContent = new QQShareContent();
		// ���÷���title
		qqShareContent.setTitle(activity.getResources().getString(R.string.shareTitle));
		// ���÷���ͼƬ
		qqShareContent.setShareImage(umImage);
		// ���÷���QQ�Ķ�ý������
		mController.setShareMedia(qqShareContent);

		// ��������SSO handler ==========
		SinaShareContent sinaContent = new SinaShareContent();
		// ��������SSO handler
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// ���÷�������΢������������
		sinaContent.setShareContent(str);
		// ���÷�������΢����ͼƬ
		sinaContent.setShareImage(umImage);
		// ���÷�������΢���Ķ�ý������
		mController.setShareMedia(sinaContent);

		// ���΢��ƽ̨ ==========
		UMWXHandler wxHandler = new UMWXHandler(activity, ToolFunction.WXAPPID, ToolFunction.WXAPPSECRET);
		wxHandler.addToSocialSDK();
		// ���΢������Ȧ
		UMWXHandler wxCircleHandler = new UMWXHandler(activity, ToolFunction.WXAPPID, ToolFunction.WXAPPSECRET);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		// ����΢�ź��ѷ�������
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		// ���÷���ͼƬ
		weixinContent.setShareImage(umImage);
		mController.setShareMedia(weixinContent);
		// ����΢������Ȧ��������
		CircleShareContent circleMedia = new CircleShareContent();
		// ���÷���ͼƬ
		circleMedia.setShareImage(umImage);
		mController.setShareMedia(circleMedia);

		// �Ƿ�ֻ���ѵ�¼�û����ܴ򿪷���ѡ��ҳ
		mController.openShare(activity, false);
	}

}
