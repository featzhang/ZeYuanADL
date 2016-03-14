package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {

	// private int countSerial = 0;// ��¼����
	private int maxSerial = 0;// ���ļ�¼���
	private int minSerial = 0;// ��С�ļ�¼���
	// private Boolean stateChange = false;// ״̬�Ƿ�ı�
	private String[] cityName = new String[150];
	private String[] provinceName = new String[34];
	private String appellationName;
	public static String[] searchNameArray = { "ģ������", "����", "����", "����", "סַ",
			"QQ", "Email", "��ע", "�Ա�" };

	public DataBase() {
		serialInit();
	}

	public boolean addGroupName(String newClassName) {

		String[] classNameArray = getGroupNameArray();
		boolean isSame = false;
		String[] needAddArray = new String[1];
		needAddArray[0] = "";
		for (int i = 0; i < classNameArray.length; i++) {
			if (classNameArray[i].equals(newClassName)) {
				isSame = true;
				break;
			}
		}
		if (!isSame) {
			for (int i = 0; i < needAddArray.length; i++) {
				if (needAddArray[i].equals(newClassName)) {
					isSame = true;
					break;
				}
			}
		}
		if (!isSame) {
			needAddArray = StringApend(needAddArray, newClassName);
			SQLExecute("insert into CLASSSET(CLASS) values('" + newClassName
					+ "')");
			System.out.println("���ӵķ���:" + newClassName);
		}
		return isSame;
	}

	public boolean addNoClassName(String newNOClassName) {
		String[] noClassNameArray = getPhoneClassNameArray();
		boolean isSame = false;
		String[] needAddArray = new String[1];
		needAddArray[0] = "";
		for (int i = 0; i < noClassNameArray.length; i++) {
			if (noClassNameArray[i].equals(newNOClassName)) {
				isSame = true;
				break;
			}
		}
		if (!isSame) {
			for (int i = 0; i < needAddArray.length; i++) {
				if (needAddArray[i].equals(newNOClassName)) {
					isSame = true;
					break;
				}
			}
		}
		if (!isSame) {
			needAddArray = StringApend(needAddArray, newNOClassName);
			SQLExecute("insert into NOCLASSSET(NOCLASS) values('"
					+ newNOClassName + "')");
			System.out.println("�����º������:" + newNOClassName);
		}
		return isSame;
	}

	public void groupNameInit() {
		try {
			String SQLorder = "select * from ADDRESSLISTTABLE";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // ����������
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			Statement stmt = con.createStatement(); // ����Statement����
			ResultSet rs = stmt.executeQuery(SQLorder);
			while (rs.next()) {
				String classNameString = rs.getString("CLASS");
				addGroupName(classNameString);
			}
			stmt.close();
			con.close();
		} catch (Exception ex) {
			System.out.println("�ļ������̳�������");
		}
		if (getGroupNameArray().length == 0) {
			String[] newClassName = { "��ͥ", "����", "ͬ��", "ͬѧ", "VIP" };
			for (int i = 0; i < newClassName.length; i++) {
				SQLExecute("insert into CLASSSET(CLASS) values('"
						+ newClassName[i] + "')");
			}
		}
		System.out.println("�������ü���Ѿ���ɣ�");
	}

	public void deleteGroupName(String delClassName) {
		SQLExecute("delete from CLASSSET where CLASS='" + delClassName + "'");
	}

	public void deleteNoClassName(String delClassName) {
		SQLExecute("delete from NOCLASSSET where NOCLASS='" + delClassName
				+ "'");
	}

	public String getClassName(int i) {
		String[] s = getGroupNameArray();
		return s[i];
	}

	public String[] getGroupNameArray() {
		String[] classNameArray = null;
		try {
			String SQLorder = "select * from CLASSSET order by ID+100";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // ����������
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			Statement stmt = con.createStatement(); // ����Statement����
			ResultSet rs = stmt.executeQuery(SQLorder);
			int i = 0;
			while (rs.next()) {
				i++;
			}
			classNameArray = new String[i];
			rs = stmt.executeQuery(SQLorder);
			i = 0;
			while (rs.next()) {
				classNameArray[i] = rs.getString("CLASS").trim();
				i++;
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
			System.out.println("��ȡ�������ͳ�������");
		}
		return classNameArray;
	}

	public int getMaxSerial() {
		return maxSerial;
	}

	public int getMinSerial() {
		return minSerial;
	}

	public String getNOClassName(int i) {
		String[] s = getPhoneClassNameArray();
		return s[i];
	}

	public String[] getPhoneClassNameArray() {
		String[] noClassNameArray = null;
		try {
			String SQLorder = "select * from NOCLASSSET order by ID+100";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // ����������
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			Statement stmt = con.createStatement(); // ����Statement����
			ResultSet rs = stmt.executeQuery(SQLorder);
			int i = 0;
			while (rs.next()) {
				i++;
			}
			noClassNameArray = new String[i];
			rs = stmt.executeQuery(SQLorder);
			i = 0;
			while (rs.next()) {
				noClassNameArray[i] = rs.getString("NOCLASS").trim();
				i++;
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
			System.out.println("��ȡ�������ͳ�������");
		}
		return noClassNameArray;
	}

	public void noClassNameCheck() {
		try {
			String SQLorder = "select * from ADDRESSLISTTABLE";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // ����������
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			Statement stmt = con.createStatement(); // ����Statement����
			ResultSet rs = stmt.executeQuery(SQLorder);
			while (rs.next()) {
				String no1ClassNameString = rs.getString("NO1CLASS");
				addNoClassName(no1ClassNameString);
				String no2ClassNameString = rs.getString("NO2CLASS");
				addNoClassName(no2ClassNameString);
				String no3ClassNameString = rs.getString("NO3CLASS");
				addNoClassName(no3ClassNameString);
			}
			stmt.close();
			con.close();
		} catch (Exception ex) {
			System.out.println("�ļ������̳�������");
		}
		if (getPhoneClassNameArray().length == 0) {
			String[] newNoClassName = { "�ֻ�", "סլ�绰", "�칫�绰", "����" };
			for (int i = 0; i < newNoClassName.length; i++) {
				SQLExecute("insert into NOCLASSSET(NOCLASS) values('"
						+ newNoClassName[i] + "')");
			}
		}
		System.out.println("�������ü���Ѿ���ɣ�");
	}

	private void serialInit() {// ��ȡ������С��ID��������Ŀ
		int i = 0;
		int j;
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // ����������
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			stmt = con.createStatement(); // ����Statement����
			rs = stmt.executeQuery("SELECT * FROM ADDRESSLISTTABLE ");
			while (rs.next()) {
				i++;
				j = Integer.parseInt(rs.getString("ID"));
				maxSerial = maxSerial > j ? maxSerial : j;
				minSerial = minSerial < j ? minSerial : j;
			}
			// countSerial = i;
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
			System.out.print("���󣡣���");
		}
	}

	public Boolean serialisExist(int ID) {
		int j;
		Boolean flag = false;
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // ����������
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			stmt = con.createStatement(); // ����Statement����
			rs = stmt.executeQuery("SELECT * FROM ADDRESSLISTTABLE ");
			while (rs.next()) {
				j = Integer.parseInt(rs.getString("ID"));
				if (j == ID) {
					flag = true;
					break;
				}
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
			System.out.print("���󣡣���");
		}
		return flag;
	}

	public static void SQLExecute(String SQLOrder) {
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // ����������
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			Statement stmt = con.createStatement(); // ����Statement����
			System.out.print(SQLOrder);
			stmt.executeUpdate(SQLOrder);
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
			System.out.println("DataBase SQL���ִ�г�������");
		}
	}

	private String[] StringApend(String[] oldArray, String s) {
		int arrayLength = oldArray.length + 1;
		String[] newArray = new String[arrayLength];
		int i;
		for (i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
		newArray[i] = s;
		return newArray;
	}

	public void modifyGroupName(String oldClassName, String newClassName) {
		SQLExecute("update CLASSSET set CLASS='" + newClassName
				+ "' where CLASS='" + oldClassName + "'");
		SQLExecute("update ADDRESSLISTTABLE set CLASS='" + newClassName
				+ "' where CLASS='" + oldClassName + "'");
	}

	public void modifyNoClassName(String oldClassName, String newClassName) {
		SQLExecute("update NOCLASSSET set NOCLASS='" + newClassName
				+ "' where NOCLASS='" + oldClassName + "'");
		SQLExecute("update ADDRESSLISTTABLE set NO1CLASS='" + newClassName
				+ "' where NO1CLASS='" + oldClassName + "'");
		SQLExecute("update ADDRESSLISTTABLE set NO2CLASS='" + newClassName
				+ "' where NO2CLASS='" + oldClassName + "'");
		SQLExecute("update ADDRESSLISTTABLE set NO3CLASS='" + newClassName
				+ "' where NO3CLASS='" + oldClassName + "'");
	}

	public AddressListData getAddressListDataByID(String serial) {
		AddressListData ald = null;
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // ����������
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			stmt = con.createStatement(); // ����Statement����
			rs = stmt
					.executeQuery("SELECT * FROM ADDRESSLISTTABLE where [ID]='"
							+ serial + "'");
			while (rs.next()) {
				String name = rs.getString("NAME");
				String sex = rs.getString("SEX");
				String xclass = rs.getString("CLASS");
				String NO1 = rs.getString("NO1");
				String NO1Class = rs.getString("NO1CLASS");
				String NO2 = rs.getString("NO2");
				String NO2Class = rs.getString("NO2CLASS");
				String NO3 = rs.getString("NO3");
				String NO3Class = rs.getString("NO3CLASS");
				String address = rs.getString("ADDRESS");
				String hometown = rs.getString("HOMETOWN");
				String qq = rs.getString("QQ");
				String email = rs.getString("EMAIL");
				String remarks = rs.getString("REMARKS");
				String birthdayCalendarString = String.valueOf(rs
						.getString("Birthday"));
				String birthdayLunarString = String.valueOf(rs
						.getString("BirthdayLunar"));

				ald = new AddressListData(qq, name, sex, xclass, NO1, NO1Class,
						NO2, NO2Class, NO3, NO3Class, address, hometown, qq,
						email, remarks, birthdayCalendarString,
						birthdayLunarString);
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
		}
		return ald;
	}

	public String[] getCityName(String privinceName) {
		return getSubdivisions("city", privinceName);
	}

	public String[] getProvinceName() {
		return getSubdivisions("province", "");
	}

	public String getAppellationName(String s) {
		return getSubdivisions("appellation", s)[0];
	}

	private String[] getSubdivisions(String s1, String s2) {
		String[] sp = null;
		String[] sc = null;
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // ����������
			con = DriverManager.getConnection(new dataBase.DataBaseConnection()
					.getConnectionStrCity()); // �������ݿ�
			stmt = con.createStatement(); // ����Statement����
			String SQL = null;
			int i = 0;
			if (s1.equals("province")) {
				SQL = "SELECT *  FROM Tb_Province order by ZProvinceType";
				rs = stmt.executeQuery(SQL);
				i = 0;
				while (rs.next()) {
					provinceName[i] = rs.getString("ZName");
					if (provinceName[i].equals("����")) {
						provinceName[i] = "������";
					} else if (provinceName[i].equals("����")) {
						provinceName[i] = "���ɹ�";
					}
					i++;
				}
				sp = new String[i];
				System.arraycopy(provinceName, 0, sp, 0, i);
				sp = StringArrayAppend(sp, "");

			} else if (s1.equals("city")) {

				SQL = "SELECT Tb_Province.ZName AS PROVINCE,Tb_City.ZName AS CITY  FROM Tb_Province,Tb_City WHERE Tb_Province.ZID=Tb_City.ZUP AND Tb_Province.ZName='"
						+ s2 + "'";/* order by Tb_City.ZPostCode */
				rs = stmt.executeQuery(SQL);
				// System.out.println(SQL);
				i = 0;
				while (rs.next()) {
					cityName[i++] = rs.getString("CITY");
				}
				sc = new String[i];
				System.arraycopy(cityName, 0, sc, 0, i);
				sc = StringArrayAppend(sc, "");
			} else if (s1.equals("appellation")) {

				SQL = "SELECT *  FROM Tb_Province WHERE  ZName='" + s2 + "'";
				rs = stmt.executeQuery(SQL);
				// System.out.println(SQL);
				int appellationInt;

				while (rs.next()) {
					appellationInt = rs.getInt("ZProvinceType");
					switch (appellationInt) {
					case 0: {
						appellationName = "ʡ";
						break;
					}
					case 1: {
						appellationName = "��";
						break;
					}
					case 2: {
						appellationName = "��";
						break;
					}
					case 3: {
						appellationName = "������";
						break;
					}
					case 4: {
						appellationName = "����";
						break;
					}
					}
				}
				sc = new String[i];
				System.arraycopy(cityName, 0, sc, 0, i);
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����

		} catch (Exception ex) {
			System.out.print("���󣡣���");
		}
		// System.out.print(s1);
		if (s1.equals("province")) {
			return sp;
		} else if (s1.equals("city")) {
			return sc;
		} else if (s1.equals("appellation")) {
			String[] ss = new String[1];
			ss[0] = appellationName;
			return ss;
		}
		return sp;
	}

	private String[] StringArrayAppend(String[] strinfArray, String s) {
		String[] newStringArray = new String[strinfArray.length + 1];
		newStringArray[0] = s;
		System.arraycopy(strinfArray, 0, newStringArray, 1, strinfArray.length);
		return newStringArray;
	}

	public String getEmailsFromDatabase(String sQLString) {
		String emailStrings = "";
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // ����������
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			stmt = con.createStatement(); // ����Statement����
			rs = stmt.executeQuery(sQLString);
			while (rs.next()) {
				String email = rs.getString("EMAIL");
				if (!email.equals("")) {
					emailStrings += email;
					emailStrings += ";";
				}
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
		}
		return emailStrings;
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList getAddressListDatasBySQL(String SQLOrderString) {
		ArrayList<AddressListData> al = null;
		AddressListData ald = null;
		System.out.println(SQLOrderString);
		try {
			al = new ArrayList<AddressListData>();
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // ����������
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // �������ݿ�
			stmt = con.createStatement(); // ����Statement����
			rs = stmt.executeQuery(SQLOrderString);
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String sex = rs.getString("SEX");
				String xclass = rs.getString("CLASS");
				String NO1 = rs.getString("NO1");
				String NO1Class = rs.getString("NO1CLASS");
				String NO2 = rs.getString("NO2");
				String NO2Class = rs.getString("NO2CLASS");
				String NO3 = rs.getString("NO3");
				String NO3Class = rs.getString("NO3CLASS");
				String address = rs.getString("ADDRESS");
				String hometown = rs.getString("HOMETOWN");
				String qq = rs.getString("QQ");
				String email = rs.getString("EMAIL");
				String remarks = rs.getString("REMARKS");
				String birthdayCalendarString = String.valueOf(rs
						.getString("Birthday"));
				String birthdayLunarString = String.valueOf(rs
						.getString("BirthdayLunar"));
				ald = new AddressListData(id, name, sex, xclass, NO1, NO1Class,
						NO2, NO2Class, NO3, NO3Class, address, hometown, qq,
						email, remarks, birthdayCalendarString,
						birthdayLunarString);
				System.out.println(ald.toString());
				al.add(ald);
			}
			stmt.close(); // �ر����
			con.close(); // �ر�����
		} catch (Exception ex) {
		}
		return al;
	}

	public static void moveToGroup(int ID, String groupName) {
		String idString = ID + "";
		SQLExecute("update ADDRESSLISTTABLE set [CLASS]='" + groupName
				+ "' where [ID]='" + idString + "'");
	}
}
