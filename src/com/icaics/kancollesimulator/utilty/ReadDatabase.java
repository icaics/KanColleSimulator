package com.icaics.kancollesimulator.utilty;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReadDatabase {
	// �������Ͷ�Ӧ���û��¼�����ݿ⣨ʹ���ֶ�������ѯ�����������ӽ����ݿ�
	private SQLiteDatabase data;
	private WriteDatabase writeDatabase;
	private static String DATABASE = "data/data/com.icaics.kancollesimulator/databases/data.s3db";
	private static String FORMATION = "data/data/com.icaics.kancollesimulator/databases/formation.db";
	private Cursor cursorCell, cursorCellCodeEx, cursorDatabaseVersion, cursorExpSum;
	private Cursor cursorCloumn, cursorCloumnDesc;
	private Cursor cursorRaidMap;
	private Cursor cursorEquip;
	private Cursor cursorShipName;
	//private Cursor cursorDataCount;
	private Cursor cursorShipDetail;
	private Cursor cursorEquipName;
	// cursorEquipDetail����������װ������ĺ����У�������һ����������ͬ��
	private Cursor cursorEquipDetail;
	private Cursor cursorRaidDetail;
	private Cursor cursorFormationRaid;
	private Cursor cursorFormationAttack;
	private Cursor cursorRaidFormationDetail;
	private Cursor cursorAttackFormationDetail;
	private Cursor cursorAttackMap;
	private Cursor cursorAttackMapDetail;
	private Cursor cursorMapExp;
	private Cursor cursorModifyFactory;
	private String strDatabaseVersion;
	private String string;
	private ArrayList<String> strArray = new ArrayList<String>();
	private int exp, stringint;
	//private int dataCount;

	// TODO �ַ�������ת����UTF8-GBK��
	private String codeExchangeString(Cursor cursor, String stringInput) throws UnsupportedEncodingException {
		byte[] exChange;
		String tmpString;
		String strExchanged;
		exChange = cursor.getBlob(cursor.getColumnIndex(stringInput));
		tmpString = new String(exChange, "GBK");
		strExchanged = tmpString.substring(0, tmpString.length() - 1);
		return strExchanged;
	}

	// TODO ��ȡ���ݿ�汾
	public String getDatebaseVersion() {
		try {
			strDatabaseVersion = "";
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT date FROM version WHERE version = 20";
			System.out.println(sql);
			cursorDatabaseVersion = data.rawQuery(sql, null);
			cursorDatabaseVersion.moveToFirst();
			strDatabaseVersion = cursorDatabaseVersion.getString(cursorDatabaseVersion.getColumnIndex("date"));
			System.out.println(strDatabaseVersion);
		} catch (Exception e) {
			//
		} finally {
			cursorDatabaseVersion.close();
			data.close();
		}
		return strDatabaseVersion;
	}

	// TODO �������ݿ��¼���������ڽ�������ѡ���б���ʾ�����ж��٣���ɸѡ��װ���﹦�ܵļ�����ʱ����
	/*
	private int getDataCount(String table) {
		try {
			dataCount = 0;
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT count(*) FROM " + table;
			System.out.println(sql);
			cursorDataCount = data.rawQuery(sql, null);
			cursorDataCount.moveToFirst();
			dataCount = Integer.parseInt(cursorDataCount.getString(cursorDataCount.getColumnIndex("count(*)")));
			System.out.println(dataCount);
		} catch (Exception e) {
			//
		} finally {
			cursorDataCount.close();
			data.close();
		}
		return dataCount;
	}
	*/

	// TODO ��ȡָ����Ԫ�����ݣ������ַ��� ��ת�룩
	public String getDatabaseCell(String select, String from, String where, String target) {
		try {
			string = "";
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + target;
			System.out.println(sql);
			cursorCell = data.rawQuery(sql, null);
			cursorCell.moveToFirst();
			string = cursorCell.getString(cursorCell.getColumnIndex(select));
			System.out.println(string);
		} catch (Exception e) {
			//
		} finally {
			cursorCell.close();
			data.close();
		}
		return string;
	}

	// TODO ��ȡָ����Ԫ�����ݣ������ַ��� ת�룩
	public String getDatabaseCellCodeEx(String select, String from, String where, String target) {
		try {
			string = "";
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + target;
			System.out.println(sql);
			cursorCellCodeEx = data.rawQuery(sql, null);
			cursorCellCodeEx.moveToFirst();
			string = codeExchangeString(cursorCellCodeEx, select);
			System.out.println(string);
		} catch (Exception e) {
			//
		} finally {
			cursorCellCodeEx.close();
			data.close();
		}
		return string;
	}

	// TODO ��ȡָ����Ԫ�����ݣ��������ͣ�
	public int getDatabaseCellInt(String select, String from, String where, String target) {
		try {
			stringint = 0;
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " = " + target;
			System.out.println(sql);
			cursorCell = data.rawQuery(sql, null);
			cursorCell.moveToFirst();
			stringint = cursorCell.getInt(cursorCell.getColumnIndex(select));
			System.out.println(stringint);
		} catch (Exception e) {
			//
		} finally {
			cursorCell.close();
			data.close();
		}
		return stringint;
	}

	// TODO ��ȡָ�������ݣ������ַ������� ת�룩
	public ArrayList<String> getDatebaseColumn(String select, String from, String where, String between, String and, String orderBy) {
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " BETWEEN " + between + " AND " + and + " ORDER BY " + orderBy;
			System.out.println(sql);
			cursorCloumn = data.rawQuery(sql, null);
			// cursorForCell.moveToFirst();
			while (cursorCloumn.moveToNext()) {
				strArray.add(new String(string = codeExchangeString(cursorCloumn, select)));
			}
			System.out.println(strArray);
		} catch (Exception e) {
			//
		} finally {
			cursorCloumn.close();
			data.close();
		}
		return strArray;
	}

	// TODO ��ȡָ�������ݣ������ַ������� ת�� ����
	public ArrayList<String> getDatebaseColumnDesc(String select, String from, String where, String between, String and, String orderBy) {
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT " + select + " FROM " + from + " WHERE " + where + " BETWEEN " + between + " AND " + and + " ORDER BY " + orderBy + " DESC";
			System.out.println(sql);
			cursorCloumnDesc = data.rawQuery(sql, null);
			// cursorForCell.moveToFirst();
			while (cursorCloumnDesc.moveToNext()) {
				strArray.add(new String(string = codeExchangeString(cursorCloumnDesc, select)));
			}
			System.out.println(strArray);
		} catch (Exception e) {
			//
		} finally {
			cursorCloumnDesc.close();
			data.close();
		}
		return strArray;
	}

	// TODO ��ȡ3-2A����ֵ����
	public int getExpSum(int startLevel, int startExp, int endLevel) {
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			exp = 0;
			startLevel += 2;
			String sql = "SELECT exp FROM exp WHERE level BETWEEN " + startLevel + " AND " + endLevel;
			System.out.println(sql);
			cursorExpSum = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorExpSum.moveToNext()) {
				exp += cursorExpSum.getInt(cursorExpSum.getColumnIndex("exp"));
			}
			System.out.println(exp);
		} catch (Exception e) {
			//
		} finally {
			cursorExpSum.close();
			data.close();
		}
		return exp;
	}

	// TODO ��ȡԶ���б�Ԫ��
	public List<Map<String, Object>> getRaidListMap(int orderType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid";
			switch (orderType) {
			case 1:
				sql = "SELECT * FROM raid ORDER BY id";
				break;
			case 2:
				sql = "SELECT * FROM raid ORDER BY id DESC";
				break;
			case 3:
				sql = "SELECT * FROM raid ORDER BY time";
				break;
			case 4:
				sql = "SELECT * FROM raid ORDER BY time DESC";
				break;
			case 5:
				sql = "SELECT * FROM raid ORDER BY oilperm DESC";
				break;
			case 6:
				sql = "SELECT * FROM raid ORDER BY ammoperm DESC";
				break;
			case 7:
				sql = "SELECT * FROM raid ORDER BY steelperm DESC";
				break;
			case 8:
				sql = "SELECT * FROM raid ORDER BY alperm DESC";
				break;
			default:
				break;
			}
			System.out.println(sql);
			cursorRaidMap = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorRaidMap.moveToNext()) {
				int time;
				String strReward = "";
				String strFleetLv;
				String strTime;

				// ����ʱ���ַ���
				time = cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("time"));
				strTime = String.valueOf(time / 60) + ":" + String.valueOf(time % 60) + ":0";
				// System.out.println(strTime);

				// ���������ַ���
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("oil")) != 0) {
					strReward += "ȼ�� " + (cursorRaidMap.getString(cursorRaidMap.getColumnIndex("oil")) + "   ");
				}
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("ammo")) != 0) {
					strReward += "��ҩ " + (cursorRaidMap.getString(cursorRaidMap.getColumnIndex("ammo")) + "   ");
				}
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("steel")) != 0) {
					strReward += "�ֲ� " + (cursorRaidMap.getString(cursorRaidMap.getColumnIndex("steel")) + "   ");
				}
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("al")) != 0) {
					strReward += "���� " + cursorRaidMap.getString(cursorRaidMap.getColumnIndex("al"));
				}
				// �����޽��ӵȼ�Ҫ�����
				if (cursorRaidMap.getInt(cursorRaidMap.getColumnIndex("fleetlv")) == 0) {
					strFleetLv = "0";
				} else {
					strFleetLv = cursorRaidMap.getString(cursorRaidMap.getColumnIndex("fleetlv"));
				}
				// ����HashMap
				map = new HashMap<String, Object>();
				map.put("id", cursorRaidMap.getString(cursorRaidMap.getColumnIndex("id")));
				map.put("name", codeExchangeString(cursorRaidMap, "name"));
				map.put("flagLv", "Lv" + cursorRaidMap.getString(cursorRaidMap.getColumnIndex("flaglv")));
				map.put("fleetLv", "Lv" + strFleetLv);
				map.put("time", strTime);
				map.put("shipRequire", codeExchangeString(cursorRaidMap, "require"));
				map.put("reward", strReward);
				list.add(map);
			}
			System.out.println(list);
		} catch (Exception e) {
			//
		} finally {
			cursorRaidMap.close();
			data.close();
		}
		return list;

	}

	// TODO ����Զ������
	public Map<String, Object> getRaidDetail(int raidNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id, flagLvRequire, fleetLvRequire, shipNum;
		int bb, bbv, cv, cvl, av, ca, cav, cl, clt, dd, ss, ssv, other;
		int equipNum;
		String nameCodeEx, requireCodeEx;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid WHERE id = " + raidNum;
			System.out.println(sql);
			cursorRaidDetail = data.rawQuery(sql, null);
			cursorRaidDetail.moveToFirst();
			id = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("id"));
			// ���Ƽ�Ҫ����
			nameCodeEx = codeExchangeString(cursorRaidDetail, "name");
			requireCodeEx = codeExchangeString(cursorRaidDetail, "require");
			flagLvRequire = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("flaglv"));
			fleetLvRequire = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("fleetlv"));
			shipNum = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("shipnum"));
			// ���轢��������Ϣ
			bb = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("bb"));
			bbv = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("bbv"));
			cv = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cv"));
			cvl = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cvl"));
			av = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("av"));
			ca = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("ca"));
			cav = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cav"));
			cl = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("cl"));
			clt = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("clt"));
			dd = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("dd"));
			ss = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("ss"));
			ssv = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("ssv"));
			other = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("other"));
			equipNum = cursorRaidDetail.getInt(cursorRaidDetail.getColumnIndex("equip"));
			// װ������
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("flaglv", flagLvRequire);
			map.put("fleetlv", fleetLvRequire);
			map.put("require", requireCodeEx);
			map.put("shipnum", shipNum);
			map.put("bb", bb);
			map.put("bbv", bbv);
			map.put("cv", cv);
			map.put("cvl", cvl);
			map.put("av", av);
			map.put("ca", ca);
			map.put("cav", cav);
			map.put("cl", cl);
			map.put("clt", clt);
			map.put("dd", dd);
			map.put("ss", ss);
			map.put("ssv", ssv);
			map.put("other", other);
			map.put("equipnum", equipNum);

			System.out.println(map);
		} catch (Exception e) {
			//
		} finally {
			cursorRaidDetail.close();
			data.close();
		}
		return map;
	}

	// TODO ���ؽ�������
	public List<String> getShipClass() {
		List<String> list = new ArrayList<String>();
		list.add("  01.��Ş (BB)");
		list.add("  02.���Ց�Ş (BBV)");
		list.add("  03.��Ҏ��ĸ (CV)");
		list.add("  04.�X��ĸ (CVL)");
		list.add("  05.ˮ�ϙCĸŞ (AV)");
		list.add("  06.��Ѳ��Ş (CA)");
		list.add("  07.����Ѳ��Ş (CAV)");
		list.add("  08.�XѲ��Ş (CL)");
		list.add("  09.����װѲ��Ş (CLT)");
		list.add("  10.�l��Ş (DD)");
		list.add("  11.ǱˮŞ (SS)");
		list.add("  12.Ǳˮ��ĸ (SSV)");
		list.add("  13.���� (OTHERS)");
		return list;
	}

	// TODO �����б���ͼ��ʹ�õĽ������ƺϼ�
	public List<String> getShipName(String shipClass, int kai) {
		List<String> list = new ArrayList<String>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass;
			if (kai == 1) {
				sql =  "SELECT * FROM " + shipClass + " WHERE kai = " + kai;
			}
			System.out.println(sql);
			cursorShipName = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorShipName.moveToNext()) {
				String strShipName = "NULL";
				String nameCodeEx, speed;
				float num;
				int slots, slot1, slot2, slot3, slot4;

				// ��������
				nameCodeEx = codeExchangeString(cursorShipName, "name");
				// ����
				num = cursorShipName.getFloat(cursorShipName.getColumnIndex("id"));
				slots = cursorShipName.getInt(cursorShipName.getColumnIndex("slot"));
				slot1 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot1"));
				slot2 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot2"));
				slot3 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot3"));
				slot4 = cursorShipName.getInt(cursorShipName.getColumnIndex("slot4"));
				// �ж��ٶ�
				if (cursorShipName.getInt(cursorShipName.getColumnIndex("speed")) == 2) {
					speed = "����";
				} else {
					speed = "����";
				}
				// ���������ַ���
				strShipName = "  " + num + " " + nameCodeEx + " " + speed + "  " + slots + "��(" + slot1 + "." + slot2 + "." + slot3 + "." + slot4 + ")";
				System.out.println(strShipName);
				// �������
				list.add(strShipName);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorShipName.close();
			data.close();
		}

		return list;
	}

	// TODO Զ����ɽ�����ݻص���ý�������
	public Map<String, Object> getShipDetail(String shipClass, int shipPosition, int kai) {
		Map<String, Object> map = new HashMap<String, Object>();
		float id;
		int slot, slot1, slot2, slot3, slot4, speed;
		String nameCodeEx, className;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass;
			if (kai == 1) {
				sql =  "SELECT * FROM " + shipClass + " WHERE kai = " + kai;
			}
			System.out.println(sql);
			cursorShipDetail = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			cursorShipDetail.move(shipPosition);
			id = cursorShipDetail.getFloat(cursorShipDetail.getColumnIndex("id"));
			className = cursorShipDetail.getString(cursorShipDetail.getColumnIndex("class"));
			slot = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot"));
			slot1 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot1"));
			slot2 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot2"));
			slot3 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot3"));
			slot4 = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("slot4"));
			speed = cursorShipDetail.getInt(cursorShipDetail.getColumnIndex("speed"));
			// ��������
			nameCodeEx = codeExchangeString(cursorShipDetail, "name");
			// װ������
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("class", className);
			map.put("slot", slot);
			map.put("slot1", slot1);
			map.put("slot2", slot2);
			map.put("slot3", slot3);
			map.put("slot4", slot4);
			map.put("speed", speed);

			System.out.println(map);
		} catch (Exception e) {
			//
		} finally {
			cursorShipDetail.close();
			data.close();
		}
		return map;
	}

	// TODO ��ȡװ���б�����
	public List<Map<String, Object>> getEquipListMap(String shipClass, int shipSpeed) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "";
			if (shipClass.equals("BB") && shipSpeed == 1) {
				// ����BB
				sql = "SELECT * FROM equip WHERE BB = 1 ORDER BY type";
			} else if (shipClass.equals("BB") && shipSpeed == 2) {
				// ����BB
				sql = "SELECT * FROM equip WHERE BBHS = 1 ORDER BY type";
			} else {
				sql = "SELECT * FROM equip WHERE " + shipClass + " = 1 ORDER BY type";
			}
			System.out.println(sql);
			cursorEquip = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorEquip.moveToNext()) {
				String strEquipName = "NULL";
				String nameCodeEx;
				int num, type;

				// װ�����ƴ���
				nameCodeEx = codeExchangeString(cursorEquip, "name");
				num = cursorEquip.getInt(cursorEquip.getColumnIndex("id"));
				// ���������ַ���
				strEquipName = "  " + num + "  -  " + nameCodeEx;
				// װ�����ͣ�������ʾͼ�꣩
				type = cursorEquip.getInt(cursorEquip.getColumnIndex("type"));
				// ����HashMap
				map = new HashMap<String, Object>();
				map.put("type", type);
				map.put("name", strEquipName);
				list.add(map);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorEquip.close();
			data.close();
		}
		return list;

	}

	// TODO �����б���ͼ��ʹ�õ�װ�����ƺϼ���������
	public List<String> getEquipName(String shipClass) {
		List<String> list = new ArrayList<String>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM equip WHERE " + shipClass + " = 1 ORDER BY type";
			System.out.println(sql);
			cursorEquipName = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorEquipName.moveToNext()) {
				String strEquipName = "NULL";
				String nameCodeEx;
				int num;

				// װ�����Ƽ����ʹ���
				nameCodeEx = codeExchangeString(cursorEquipName, "name");
				// ����
				num = cursorEquipName.getInt(cursorEquipName.getColumnIndex("id"));
				// ���������ַ���
				strEquipName = "  " + num + "  -  " + nameCodeEx;
				System.out.println(strEquipName);
				// �������
				list.add(strEquipName);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorEquipName.close();
			data.close();
		}
		return list;
	}

	// TODO Զ����ɽ�����ݻص����װ������
	public Map<String, Object> getEquipDetail(String shipClass, int equipPosition, int shipSpeed) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id;
		String nameCodeEx;
		int type, antiair, search;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "";
			if (shipClass.equals("BB") && shipSpeed == 1) {
				// ����BB
				sql = "SELECT * FROM equip WHERE BB = 1 ORDER BY type";
			} else if (shipClass.equals("BB") && shipSpeed == 2) {
				// ����BB
				sql = "SELECT * FROM equip WHERE BBHS = 1 ORDER BY type";
			} else {
				sql = "SELECT * FROM equip WHERE " + shipClass + " = 1 ORDER BY type";
			}
			System.out.println(sql);
			cursorEquipDetail = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			cursorEquipDetail.move(equipPosition);
			id = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("id"));
			type = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("type"));
			antiair = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("antiair"));
			search = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("search"));
			// װ���������ʹ���
			nameCodeEx = codeExchangeString(cursorEquipDetail, "name");
			// װ�����ݣ��ɼ������װ�����飩
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("type", type);
			map.put("antiair", antiair);
			map.put("search", search);

			System.out.println(map);

		} catch (Exception e) {
			//
		} finally {
			cursorEquipDetail.close();
			data.close();
		}
		return map;
	}

	// TODO ����Զ�����ö�ȡ�б�Ԫ��
	public List<Map<String, Object>> getFormationRaidListMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			// �ж��Ƿ����Զ���������ݿ�
			writeDatabase = new WriteDatabase();
			// ����Ƿ����Զ��������
			if (!writeDatabase.isRaidTableExist("raid")) {
				writeDatabase.createTable(data, "raid", 1);
			}

			String sql = "SELECT * FROM raid";
			System.out.println(sql);
			cursorFormationRaid = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorFormationRaid.moveToNext()) {
				// ����˵����id-��Ӧ��Զ��ID-�ֶ���raidid��savedID-�ڱ��еĵ���ID-�ֶ���id
				int id, intRaidTime, savedID;
				String raidSavedName, raidIDName, strRaidTime;
				float ship1ID, ship2ID, ship3ID, ship4ID, ship5ID, ship6ID;
				String ship1Class, ship2Class, ship3Class, ship4Class, ship5Class, ship6Class;
				String ship1Name = null, ship2Name = null, ship3Name = null, ship4Name = null, ship5Name = null, ship6Name = null;

				// ��ñ���Ķ�ӦԶ��ID
				id = cursorFormationRaid.getInt(cursorFormationRaid.getColumnIndex("raidid"));
				// ���Զ��ID�������ַ���
				raidIDName = "Զ��" + id + " " + getRaidName(id);
				// ��ö�ӦԶ��ʱ���ַ�������Ҫ��Զ����ʱ���ܣ�����Ͷ�ӦListItem�ж�Ӧ��ʱ����ֿ���
				intRaidTime = getRaidTime(id);
				strRaidTime = String.valueOf(intRaidTime / 60) + ":" + String.valueOf(intRaidTime % 60) + ":0";
				// ����������
				raidSavedName = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("savedname"));
				System.out.println("Զ�����ñ�������Ϊ��" + raidSavedName);
				// ��ü�¼�ڱ��е�ID
				savedID = cursorFormationRaid.getInt(cursorFormationRaid.getColumnIndex("id"));
				// ��ö�Ӧ�ı�ɽ���ID
				ship1ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship1"));
				ship2ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship2"));
				ship3ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship3"));
				ship4ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship4"));
				ship5ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship5"));
				ship6ID = cursorFormationRaid.getFloat(cursorFormationRaid.getColumnIndex("ship6"));
				// ��ö�Ӧ�ı�ɽ�������
				if (ship1ID != 0f) {
					ship1Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship1class"));
					ship1Name = getShipName(ship1Class, ship1ID);
				}
				if (ship2ID != 0f) {
					ship2Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship2class"));
					ship2Name = getShipName(ship2Class, ship2ID);
				}
				if (ship3ID != 0f) {
					ship3Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship3class"));
					ship3Name = getShipName(ship3Class, ship3ID);
				}
				if (ship4ID != 0f) {
					ship4Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship4class"));
					ship4Name = getShipName(ship4Class, ship4ID);
				}
				if (ship5ID != 0f) {
					ship5Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship5class"));
					ship5Name = getShipName(ship5Class, ship5ID);
				}
				if (ship6ID != 0f) {
					ship6Class = cursorFormationRaid.getString(cursorFormationRaid.getColumnIndex("ship6class"));
					ship6Name = getShipName(ship6Class, ship6ID);
				}

				// ����HashMap
				map = new HashMap<String, Object>();
				map.put("idname", raidIDName);
				map.put("time", strRaidTime);
				map.put("savedid", savedID);
				map.put("savedname", raidSavedName);
				map.put("ship1", ship1Name);
				map.put("ship2", ship2Name);
				map.put("ship3", ship3Name);
				map.put("ship4", ship4Name);
				map.put("ship5", ship5Name);
				map.put("ship6", ship6Name);
				list.add(map);
			}

			System.out.println(list);

		} catch (Exception e) {
			//
		} finally {
			cursorFormationRaid.close();
			data.close();
		}

		return list;
	}

	// TODO ���س������ö�ȡ�б�Ԫ��
	public List<Map<String, Object>> getFormationAttackListMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			// �ж��Ƿ����Զ���������ݿ�
			writeDatabase = new WriteDatabase();
			// ����Ƿ����Զ��������
			if (!writeDatabase.isRaidTableExist("attack")) {
				writeDatabase.createTable(data, "attack", 2);
			}

			String sql = "SELECT * FROM attack";
			System.out.println(sql);
			cursorFormationAttack = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorFormationAttack.moveToNext()) {
				// ����˵����id-��Ӧ�ĳ���ID-�ֶ���attackid��savedID-�ڱ��еĵ���ID-�ֶ���id
				int id, savedID;
				String attackSavedName, attackIDName;
				float ship1ID, ship2ID, ship3ID, ship4ID, ship5ID, ship6ID;
				String ship1Class, ship2Class, ship3Class, ship4Class, ship5Class, ship6Class;
				String ship1Name = null, ship2Name = null, ship3Name = null, ship4Name = null, ship5Name = null, ship6Name = null;

				// ��ñ���Ķ�Ӧ����ID
				id = cursorFormationAttack.getInt(cursorFormationAttack.getColumnIndex("attackid"));
				// ��ó���ID�������ַ���
				attackIDName = getAttackName(id);
				// ����������
				attackSavedName = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("savedname"));
				System.out.println("�������ñ�������Ϊ��" + attackSavedName);
				// ��ü�¼�ڱ��е�ID
				savedID = cursorFormationAttack.getInt(cursorFormationAttack.getColumnIndex("id"));
				// ��ö�Ӧ�ı�ɽ���ID
				ship1ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship1"));
				ship2ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship2"));
				ship3ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship3"));
				ship4ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship4"));
				ship5ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship5"));
				ship6ID = cursorFormationAttack.getFloat(cursorFormationAttack.getColumnIndex("ship6"));
				// ��ö�Ӧ�ı�ɽ�������
				if (ship1ID != 0f) {
					ship1Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship1class"));
					ship1Name = getShipName(ship1Class, ship1ID);
				}
				if (ship2ID != 0f) {
					ship2Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship2class"));
					ship2Name = getShipName(ship2Class, ship2ID);
				}
				if (ship3ID != 0f) {
					ship3Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship3class"));
					ship3Name = getShipName(ship3Class, ship3ID);
				}
				if (ship4ID != 0f) {
					ship4Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship4class"));
					ship4Name = getShipName(ship4Class, ship4ID);
				}
				if (ship5ID != 0f) {
					ship5Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship5class"));
					ship5Name = getShipName(ship5Class, ship5ID);
				}
				if (ship6ID != 0f) {
					ship6Class = cursorFormationAttack.getString(cursorFormationAttack.getColumnIndex("ship6class"));
					ship6Name = getShipName(ship6Class, ship6ID);
				}

				// ����HashMap
				map = new HashMap<String, Object>();
				map.put("idname", attackIDName);
				map.put("savedid", savedID);
				map.put("savedname", attackSavedName);
				map.put("ship1", ship1Name);
				map.put("ship2", ship2Name);
				map.put("ship3", ship3Name);
				map.put("ship4", ship4Name);
				map.put("ship5", ship5Name);
				map.put("ship6", ship6Name);
				list.add(map);
			}

			System.out.println(list);

		} catch (Exception e) {
			//
		} finally {
			cursorFormationAttack.close();
			data.close();
		}

		return list;
	}

	// TODO ����Ϊ��ȡԶ�����ö�ȡ�б� ��ȡ��ӦԶ����ʱ��
	private int getRaidTime(int raidID) {
		int time = 0;
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid WHERE id = " + raidID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			time = cursor.getInt(cursor.getColumnIndex("time"));
			System.out.println("Զ��ʱ�䣨min����" + time);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return time;
	}

	// TODO ����Ϊ��ȡԶ�����ö�ȡ�б� ��ȡ��ӦԶ������
	private String getRaidName(int raidID) {
		String nameCodeEx = "";
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM raid WHERE id = " + raidID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			nameCodeEx = codeExchangeString(cursor, "name");
			System.out.println(nameCodeEx);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return nameCodeEx;
	}

	// TODO ����Ϊ��ȡ�������ö�ȡ�б� ��ȡ��Ӧ��������
	private String getAttackName(int attackMapID) {
		String nameCodeEx = "";
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM map WHERE id = " + attackMapID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			nameCodeEx = codeExchangeString(cursor, "name");
			System.out.println(nameCodeEx);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return nameCodeEx;
	}

	// TODO ����Ϊ��ȡԶ�����ö�ȡ�б� ��ȡ��Ӧ��ɽ�������
	private String getShipName(String shipClass, float shipID) {
		String nameCodeEx = "";
		SQLiteDatabase data = null;
		Cursor cursor = null;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass + " WHERE id = " + shipID;
			System.out.println(sql);
			cursor = data.rawQuery(sql, null);
			cursor.moveToFirst();
			nameCodeEx = codeExchangeString(cursor, "name");
			System.out.println(nameCodeEx);
		} catch (Exception e) {
			//
		} finally {
			cursor.close();
			data.close();
		}
		return nameCodeEx;
	}

	// TODO Զ����ɽ���ͨ���Ѵ�Զ��������ƻ��Զ����Ϣ
	public Map<String, Object> getRaidFormationDetail(int savedID) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int id;
			String savedName;
			float ship1, ship2, ship3, ship4, ship5, ship6;
			String ship1class, ship2class, ship3class, ship4class, ship5class, ship6class;
			int ship1lv, ship2lv, ship3lv, ship4lv, ship5lv, ship6lv;
			int equip11, equip12, equip13, equip14;
			int equip21, equip22, equip23, equip24;
			int equip31, equip32, equip33, equip34;
			int equip41, equip42, equip43, equip44;
			int equip51, equip52, equip53, equip54;
			int equip61, equip62, equip63, equip64;

			// ����Ƿ����Զ�����������˴����ü�飬��Ϊ�Ǵ��Ѵ�Զ���б�����ģ��϶����������ģ�
			// if (!writeDatabase.isRaidTableExist("raid")) {
			// writeDatabase.createTable(data, "raid", 1);
			// }

			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			String sql = "SELECT * FROM raid WHERE id = " + savedID;
			System.out.println(sql);
			cursorRaidFormationDetail = data.rawQuery(sql, null);
			cursorRaidFormationDetail.moveToFirst();
			// ����Ѵ�Զ����ϸ��Ӧ��Զ��ID
			// ˵����Զ��ID�ڱ����ֶ�Ϊraidid��idΪ����ID��������ȡ��ʱ��Ϊ��id��
			id = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("raidid"));
			savedName = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("savedname"));
			// ��ý���ID
			ship1 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship1"));
			ship2 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship2"));
			ship3 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship3"));
			ship4 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship4"));
			ship5 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship5"));
			ship6 = cursorRaidFormationDetail.getFloat(cursorRaidFormationDetail.getColumnIndex("ship6"));
			// ��ý���Class
			ship1class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship1class"));
			ship2class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship2class"));
			ship3class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship3class"));
			ship4class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship4class"));
			ship5class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship5class"));
			ship6class = cursorRaidFormationDetail.getString(cursorRaidFormationDetail.getColumnIndex("ship6class"));
			// ��ý����ȼ�
			ship1lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship1lv"));
			ship2lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship2lv"));
			ship3lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship3lv"));
			ship4lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship4lv"));
			ship5lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship5lv"));
			ship6lv = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("ship6lv"));
			// ���װ��ID
			equip11 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip11"));
			equip12 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip12"));
			equip13 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip13"));
			equip14 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip14"));
			equip21 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip21"));
			equip22 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip22"));
			equip23 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip23"));
			equip24 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip24"));
			equip31 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip31"));
			equip32 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip32"));
			equip33 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip33"));
			equip34 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip34"));
			equip41 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip41"));
			equip42 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip42"));
			equip43 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip43"));
			equip44 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip44"));
			equip51 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip51"));
			equip52 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip52"));
			equip53 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip53"));
			equip54 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip54"));
			equip61 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip61"));
			equip62 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip62"));
			equip63 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip63"));
			equip64 = cursorRaidFormationDetail.getInt(cursorRaidFormationDetail.getColumnIndex("equip64"));

			// װ������
			map.put("savedid", savedID);
			map.put("savedname", savedName);
			map.put("id", id);
			map.put("ship1", ship1);
			map.put("ship2", ship2);
			map.put("ship3", ship3);
			map.put("ship4", ship4);
			map.put("ship5", ship5);
			map.put("ship6", ship6);
			map.put("ship1class", ship1class);
			map.put("ship2class", ship2class);
			map.put("ship3class", ship3class);
			map.put("ship4class", ship4class);
			map.put("ship5class", ship5class);
			map.put("ship6class", ship6class);
			map.put("ship1lv", ship1lv);
			map.put("ship2lv", ship2lv);
			map.put("ship3lv", ship3lv);
			map.put("ship4lv", ship4lv);
			map.put("ship5lv", ship5lv);
			map.put("ship6lv", ship6lv);
			map.put("equip11", equip11);
			map.put("equip12", equip12);
			map.put("equip13", equip13);
			map.put("equip14", equip14);
			map.put("equip21", equip21);
			map.put("equip22", equip22);
			map.put("equip23", equip23);
			map.put("equip24", equip24);
			map.put("equip31", equip31);
			map.put("equip32", equip32);
			map.put("equip33", equip33);
			map.put("equip34", equip34);
			map.put("equip41", equip41);
			map.put("equip42", equip42);
			map.put("equip43", equip43);
			map.put("equip44", equip44);
			map.put("equip51", equip51);
			map.put("equip52", equip52);
			map.put("equip53", equip53);
			map.put("equip54", equip54);
			map.put("equip61", equip61);
			map.put("equip62", equip62);
			map.put("equip63", equip63);
			map.put("equip64", equip64);

		} catch (Exception e) {
			//
		} finally {
			cursorRaidFormationDetail.close();
			data.close();
		}
		return map;
	}

	// TODO Զ����ɽ���ͨ���Ѵ�Զ��������ƻ�ó�����Ϣ
	public Map<String, Object> getAttackFormationDetail(int savedID) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int id;
			String savedName;
			float ship1, ship2, ship3, ship4, ship5, ship6;
			String ship1class, ship2class, ship3class, ship4class, ship5class, ship6class;
			int ship1lv, ship2lv, ship3lv, ship4lv, ship5lv, ship6lv;
			int equip11, equip12, equip13, equip14;
			int equip21, equip22, equip23, equip24;
			int equip31, equip32, equip33, equip34;
			int equip41, equip42, equip43, equip44;
			int equip51, equip52, equip53, equip54;
			int equip61, equip62, equip63, equip64;

			// ����Ƿ���ڳ������������˴����ü�飬��Ϊ�Ǵ��Ѵ�����б�����ģ��϶����������ģ�
			// if (!writeDatabase.isRaidTableExist("attack")) {
			// writeDatabase.createTable(data, "attack", 2);
			// }

			data = SQLiteDatabase.openOrCreateDatabase(FORMATION, null);
			String sql = "SELECT * FROM attack WHERE id = " + savedID;
			System.out.println(sql);
			cursorAttackFormationDetail = data.rawQuery(sql, null);
			cursorAttackFormationDetail.moveToFirst();
			// ����Ѵ������ϸ��Ӧ��Զ��ID
			// ˵����������ͼID�ڱ����ֶ�Ϊattackid��idΪ����ID��������ȡ��ʱ��Ϊ��id��
			id = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("attackid"));
			savedName = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("savedname"));
			// ��ý���ID
			ship1 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship1"));
			ship2 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship2"));
			ship3 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship3"));
			ship4 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship4"));
			ship5 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship5"));
			ship6 = cursorAttackFormationDetail.getFloat(cursorAttackFormationDetail.getColumnIndex("ship6"));
			// ��ý���Class
			ship1class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship1class"));
			ship2class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship2class"));
			ship3class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship3class"));
			ship4class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship4class"));
			ship5class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship5class"));
			ship6class = cursorAttackFormationDetail.getString(cursorAttackFormationDetail.getColumnIndex("ship6class"));
			// ��ý����ȼ�
			ship1lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship1lv"));
			ship2lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship2lv"));
			ship3lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship3lv"));
			ship4lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship4lv"));
			ship5lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship5lv"));
			ship6lv = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("ship6lv"));
			// ���װ��ID
			equip11 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip11"));
			equip12 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip12"));
			equip13 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip13"));
			equip14 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip14"));
			equip21 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip21"));
			equip22 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip22"));
			equip23 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip23"));
			equip24 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip24"));
			equip31 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip31"));
			equip32 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip32"));
			equip33 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip33"));
			equip34 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip34"));
			equip41 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip41"));
			equip42 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip42"));
			equip43 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip43"));
			equip44 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip44"));
			equip51 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip51"));
			equip52 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip52"));
			equip53 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip53"));
			equip54 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip54"));
			equip61 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip61"));
			equip62 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip62"));
			equip63 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip63"));
			equip64 = cursorAttackFormationDetail.getInt(cursorAttackFormationDetail.getColumnIndex("equip64"));

			// װ������
			map.put("savedid", savedID);
			map.put("savedname", savedName);
			map.put("id", id);
			map.put("ship1", ship1);
			map.put("ship2", ship2);
			map.put("ship3", ship3);
			map.put("ship4", ship4);
			map.put("ship5", ship5);
			map.put("ship6", ship6);
			map.put("ship1class", ship1class);
			map.put("ship2class", ship2class);
			map.put("ship3class", ship3class);
			map.put("ship4class", ship4class);
			map.put("ship5class", ship5class);
			map.put("ship6class", ship6class);
			map.put("ship1lv", ship1lv);
			map.put("ship2lv", ship2lv);
			map.put("ship3lv", ship3lv);
			map.put("ship4lv", ship4lv);
			map.put("ship5lv", ship5lv);
			map.put("ship6lv", ship6lv);
			map.put("equip11", equip11);
			map.put("equip12", equip12);
			map.put("equip13", equip13);
			map.put("equip14", equip14);
			map.put("equip21", equip21);
			map.put("equip22", equip22);
			map.put("equip23", equip23);
			map.put("equip24", equip24);
			map.put("equip31", equip31);
			map.put("equip32", equip32);
			map.put("equip33", equip33);
			map.put("equip34", equip34);
			map.put("equip41", equip41);
			map.put("equip42", equip42);
			map.put("equip43", equip43);
			map.put("equip44", equip44);
			map.put("equip51", equip51);
			map.put("equip52", equip52);
			map.put("equip53", equip53);
			map.put("equip54", equip54);
			map.put("equip61", equip61);
			map.put("equip62", equip62);
			map.put("equip63", equip63);
			map.put("equip64", equip64);

		} catch (Exception e) {
			//
		} finally {
			cursorAttackFormationDetail.close();
			data.close();
		}
		return map;
	}

	// TODO �����ϣ������������Ѿ����Ѵ�Զ�����õ����ݿ�������ֶα��桿
	/**
	 * public String getTableName(float shipID) { String tableName = "";
	 * SQLiteDatabase dataTableName = null; Cursor cursorTableName = null;
	 * String shipClass; try { dataTableName =
	 * SQLiteDatabase.openOrCreateDatabase(DATABASE, null); String sql =
	 * "SELECT * FROM shipbb WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipbbv WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcv WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcvl WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipav WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipca WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcav WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipcl WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipclt WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipdd WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipss WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipssv WHERE id = " + shipID + " UNION " +
	 * "SELECT * FROM shipother WHERE id = " + shipID; //��
	 * getFormationRaidListMap() �л��ѯ6�Σ�Ϊ����ˢ����ע�͵�������һ���̶���ʾ
	 * //System.out.println(sql); System.out.println("���ϲ�ѯ��������(class)6��");
	 * cursorTableName = dataTableName.rawQuery(sql, null);
	 * cursorTableName.moveToFirst(); shipClass =
	 * cursorTableName.getString(cursorTableName.getColumnIndex("class"));
	 * System.out.println("shipClass = " + shipClass); tableName =
	 * ToolFunction.shipClassToTableName(shipClass);
	 * System.out.println("tableName = " + tableName); } catch (Exception e) {
	 * // } finally { cursorTableName.close(); dataTableName.close(); } return
	 * tableName; }
	 **/

	// TODO ͨ������ID����ڶ�Ӧ�������ͱ��е�λ�ã�Ϊ��getShipDetail(shipClassTableName, shipPosition)��
	public int getShipPositionInTable(String shipClass, float shipID) {
		int position = 0;
		SQLiteDatabase dataShipPositionInTable = null;
		Cursor cursorShipPositionInTable = null;
		try {
			// ��ѯ����
			dataShipPositionInTable = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM " + shipClass;
			System.out.println(sql);
			cursorShipPositionInTable = dataShipPositionInTable.rawQuery(sql, null);
			while (cursorShipPositionInTable.moveToNext()) {
				if (cursorShipPositionInTable.getFloat(cursorShipPositionInTable.getColumnIndex("id")) == shipID) {
					position = cursorShipPositionInTable.getPosition();
					System.out.println("������" + shipClass + "�е�λ����" + position);
					break;
				}
			}
		} catch (Exception e) {
			//
		} finally {
			cursorShipPositionInTable.close();
			dataShipPositionInTable.close();
		}
		// +1����cursor��0��ʼ
		return position + 1;
	}

	// TODO ��ɽ�������Ѵ���Ϣ��ȡװ������
	public Map<String, Object> getEquipDetail(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String nameCodeEx;
		int type, antiair, search;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM equip WHERE id = " + id;
			System.out.println(sql);
			cursorEquipDetail = data.rawQuery(sql, null);
			cursorEquipDetail.moveToFirst();
			type = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("type"));
			antiair = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("antiair"));
			search = cursorEquipDetail.getInt(cursorEquipDetail.getColumnIndex("search"));
			// װ���������ʹ���
			nameCodeEx = codeExchangeString(cursorEquipDetail, "name");
			// װ�����ݣ��ɼ������װ�����飩
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("type", type);
			map.put("antiair", antiair);
			map.put("search", search);

			System.out.println(map);

		} catch (Exception e) {
			//
		} finally {
			cursorEquipDetail.close();
			data.close();
		}
		return map;
	}

	// TODO ��ȡ������ͼ�б�Ԫ��
	public List<Map<String, Object>> getAttackMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM map ORDER BY id";
			System.out.println(sql);
			cursorAttackMap = data.rawQuery(sql, null);
			// cursor.moveToFirst();
			while (cursorAttackMap.moveToNext()) {
				String nameCodeEx;
				int num;
				// ��Ƿָ���������
				int type = 0;
				// ��ȡID
				num = cursorAttackMap.getInt(cursorAttackMap.getColumnIndex("id"));
				// �ж��Ƿָ������ǵ�ͼ����
				switch (num) {
				case 1:
					type = 0;
					nameCodeEx = "ͨ�ú���";
					break;
				case 3:
					type = 0;
					nameCodeEx = "��ظ�����";
					break;
				case 10:
					type = 0;
					nameCodeEx = "�����T�u����";
					break;
				case 17:
					type = 0;
					nameCodeEx = "��������";
					break;
				case 24:
					type = 0;
					nameCodeEx = "��������";
					break;
				case 31:
					type = 0;
					nameCodeEx = "�Ϸ�����";
					break;
				case 38:
					type = 0;
					nameCodeEx = "�в�����";
					break;
				default:
					type = 1;
					// ��ͼ���ƴ���
					nameCodeEx = codeExchangeString(cursorAttackMap, "name");
					break;
				}
				System.out.println(nameCodeEx);
				// ����HashMap
				map = new HashMap<String, Object>();
				map.put("id", num);
				map.put("type", type);
				map.put("name", nameCodeEx);

				list.add(map);
			}
		} catch (Exception e) {
			//
		} finally {
			cursorAttackMap.close();
			data.close();
		}

		return list;
	}

	// TODO ���س�������
	public Map<String, Object> getAttackMapDetail(int attackMapNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id, airControlRequire;
		String nameCodeEx;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM map WHERE id = " + attackMapNum;
			System.out.println(sql);
			cursorAttackMapDetail = data.rawQuery(sql, null);
			cursorAttackMapDetail.moveToFirst();
			id = cursorAttackMapDetail.getInt(cursorAttackMapDetail.getColumnIndex("id"));
			// ���Ƽ�Ҫ����
			nameCodeEx = codeExchangeString(cursorAttackMapDetail, "name");
			airControlRequire = cursorAttackMapDetail.getInt(cursorAttackMapDetail.getColumnIndex("aircontrol"));
			// װ������
			map.put("id", id);
			map.put("name", nameCodeEx);
			map.put("aircontrol", airControlRequire);

			System.out.println(map);
		} catch (Exception e) {
			//
		} finally {
			cursorAttackMapDetail.close();
			data.close();
		}
		return map;
	}

	// TODO ��ȡ�����������ڼ���
	public int getMapExp(int position) {
		int mapexp = 0;
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT mapexp FROM mapexp WHERE id = " + position;
			System.out.println(sql);
			cursorMapExp = data.rawQuery(sql, null);
			cursorMapExp.moveToFirst();
			mapexp = cursorMapExp.getInt(cursorMapExp.getColumnIndex("mapexp"));
		} catch (Exception e) {
			//
		} finally {
			cursorMapExp.close();
			data.close();
		}
		return mapexp;
	}

	// TODO ��ȡָ�����ڸ�������
	public List<Map<String, Object>> getModifyFactory(String weekday) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			data = SQLiteDatabase.openOrCreateDatabase(DATABASE, null);
			String sql = "SELECT * FROM modify_factory WHERE " + weekday + " <> 'null'";
			System.out.println(sql);
			cursorModifyFactory = data.rawQuery(sql, null);
			// ���ݴ���
			while (cursorModifyFactory.moveToNext()) {
				int type;
				String equip, content;
				type = cursorModifyFactory.getInt(cursorModifyFactory.getColumnIndex("type"));
				equip = codeExchangeString(cursorModifyFactory, "equip");
				content = "���Ž���" + codeExchangeString(cursorModifyFactory, weekday);
				// ����HashMap
				map = new HashMap<String, Object>();
				// װ������
				map.put("type", type);
				map.put("equip", equip);
				map.put("content", content);
				list.add(map);
			}
			System.out.println(list);
		} catch (Exception e) {
			//
		} finally {
			cursorModifyFactory.close();
			data.close();
		}
		return list;
	}

}
