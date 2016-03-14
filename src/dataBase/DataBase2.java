package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 数据库操作类
 * 
 * @author ZhangZuofeng
 * 
 */
public class DataBase2 {
	// private Boolean stateChange = false;// 状态是否改变
	private String[] cityName = new String[150];
	private String[] provinceName = new String[34];
	private String appellationName;
	public static String[] searchNameArray = { "模糊查找", "号码", "姓名", "分组", "住址",
			"QQ", "Email", "备注", "性别" };

	public DataBase2() {
	}

	/**
	 * 向CLASSSET表中添加新分类类型，如果已经存在跳过
	 * 
	 * @param newClassName
	 *            新分组类型
	 * @return 是否添加
	 */
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
			needAddArray = StringArrayAppend(needAddArray, newClassName);
			SQLExecute("insert into CLASSSET(CLASS) values('" + newClassName
					+ "')");
			System.out.println("增加的分组:" + newClassName);
		}
		return isSame;
	}

	/**
	 * 向NOCLASSNAME表中添加新的号码类型，如果已经存在将跳过
	 * 
	 * @param newNOClassName
	 *            新号码分组类型
	 * @return 是否添加
	 */
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
			needAddArray = StringArrayAppend(needAddArray, newNOClassName);
			SQLExecute("insert into NOCLASSSET(NOCLASS) values('"
					+ newNOClassName + "')");
			System.out.println("增加新号码分类:" + newNOClassName);
		}
		return isSame;
	}

	/**
	 * 分组表初始化
	 */
	public void groupNameInit() {
		try {
			String SQLorder = "select * from ADDRESSLISTTABLE";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // 加载驱动器
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			Statement stmt = con.createStatement(); // 创建Statement对象
			ResultSet rs = stmt.executeQuery(SQLorder);
			while (rs.next()) {
				String classNameString = rs.getString("CLASS");
				addGroupName(classNameString);
			}
			stmt.close();
			con.close();
		} catch (Exception ex) {
			System.out.println("文件检查过程出错！！！");
		}
		if (getGroupNameArray().length == 0) {
			String[] newClassName = { "家庭", "朋友", "同事", "同学", "VIP" };
			for (int i = 0; i < newClassName.length; i++) {
				SQLExecute("insert into CLASSSET(CLASS) values('"
						+ newClassName[i] + "')");
			}
		}
		System.out.println("分组设置检查已经完成！");
	}

	/**
	 * 从分组表中删除分组
	 * 
	 * @param delClassName
	 *            要删除的分组名称
	 */
	public void deleteGroupName(String delClassName) {
		SQLExecute("delete from CLASSSET where CLASS='" + delClassName + "'");
	}

	/**
	 * 从号码分组表中删除号码分组
	 * 
	 * @param delNOClassName
	 *            需要删除的号码类型
	 */
	public void deleteNoClassName(String delNOClassName) {
		SQLExecute("delete from NOCLASSSET where NOCLASS='" + delNOClassName
				+ "'");
	}

	/**
	 * 根据索引获取分组名称
	 * 
	 * @param i
	 *            分组数组索引
	 * @return 分组名称
	 */
	public String getClassName(int i) {
		String[] s = getGroupNameArray();
		return s[i];
	}

	/**
	 * 获取分组名称数组
	 * 
	 * @return 分组数组
	 */
	public String[] getGroupNameArray() {
		String[] classNameArray = null;
		try {
			String SQLorder = "select * from CLASSSET order by ID+100";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // 加载驱动器
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			Statement stmt = con.createStatement(); // 创建Statement对象
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
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
			System.out.println("获取分组类型出错！！！");
		}
		return classNameArray;
	}

	/**
	 * 根据索引获取号码分组类型名称
	 * 
	 * @param i
	 *            索引
	 * @return 号码分组类型名称
	 */
	public String getNOClassName(int i) {
		String[] s = getPhoneClassNameArray();
		return s[i];
	}

	/**
	 * 获取号码分组数组
	 * 
	 * @return 号码类型分组数组
	 */
	public String[] getPhoneClassNameArray() {
		String[] noClassNameArray = null;
		try {
			String SQLorder = "select * from NOCLASSSET order by ID+100";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // 加载驱动器
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			Statement stmt = con.createStatement(); // 创建Statement对象
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
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
			System.out.println("获取分组类型出错！！！");
		}
		return noClassNameArray;
	}

	/**
	 * 号码类型数组检查
	 */
	public void noClassNameCheck() {
		try {
			String SQLorder = "select * from ADDRESSLISTTABLE";
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // 加载驱动器
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			Statement stmt = con.createStatement(); // 创建Statement对象
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
			System.out.println("文件检查过程出错！！！");
		}
		if (getPhoneClassNameArray().length == 0) {
			String[] newNoClassName = { "手机", "住宅电话", "办公电话", "传真" };
			for (int i = 0; i < newNoClassName.length; i++) {
				SQLExecute("insert into NOCLASSSET(NOCLASS) values('"
						+ newNoClassName[i] + "')");
			}
		}
		System.out.println("分组设置检查已经完成！");
	}

	/**
	 * 判断ID是否存在
	 * 
	 * @param ID
	 *            序列
	 * @return 是否存在
	 */
	public Boolean serialisExist(int ID) {
		int j;
		Boolean flag = false;
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // 加载驱动器
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			stmt = con.createStatement(); // 创建Statement对象
			rs = stmt.executeQuery("SELECT * FROM ADDRESSLISTTABLE ");
			while (rs.next()) {
				j = Integer.parseInt(rs.getString("ID"));
				if (j == ID) {
					flag = true;
					break;
				}
			}
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
			System.out.print("错误！！！");
		}
		return flag;
	}

	/**
	 * 执行sql语句
	 * 
	 * @param SQLOrder
	 *            sql语句
	 */
	public static void SQLExecute(String SQLOrder) {
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(DBDriver); // 加载驱动器
			Connection con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			Statement stmt = con.createStatement(); // 创建Statement对象
			System.out.print(SQLOrder);
			stmt.executeUpdate(SQLOrder);
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
			System.out.println("DataBase SQL语句执行出错！！！");
		}
	}

	/**
	 * 修改分组类型名称
	 * 
	 * @param oldClassName
	 *            旧类型名称
	 * @param newClassName
	 *            新类型名称
	 */
	public void modifyGroupName(String oldClassName, String newClassName) {
		SQLExecute("update CLASSSET set CLASS='" + newClassName
				+ "' where CLASS='" + oldClassName + "'");
		SQLExecute("update ADDRESSLISTTABLE set CLASS='" + newClassName
				+ "' where CLASS='" + oldClassName + "'");
	}

	/**
	 * 修改号码类型名称
	 * 
	 * @param oldClassName
	 *            旧号码类型名称
	 * @param newClassName
	 *            新号码类型名称
	 */
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

	/**
	 * 根据序列获取AddressListData数据
	 * 
	 * @param serial
	 *            序列
	 * @return AddressListData数据
	 */
	public AddressListData getAddressListDataByID(String serial) {
		AddressListData ald = null;
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // 加载驱动器
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			stmt = con.createStatement(); // 创建Statement对象
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
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
		}
		return ald;
	}

	/**
	 * 获取市名数组
	 * 
	 * @param privinceName
	 *            省
	 * @return 市名数组
	 */
	public String[] getCityName(String privinceName) {
		return getSubdivisions("city", privinceName);
	}

	/**
	 * 获取省名数组
	 * 
	 * @return 省名数组
	 */
	public String[] getProvinceName() {
		return getSubdivisions("province", "");
	}

	/**
	 * 获取省级行政单位类型
	 * 
	 * @param s
	 *            省名
	 * @return 省级行政单位类型
	 */
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
			Class.forName(DBDriver); // 加载驱动器
			con = DriverManager.getConnection(new dataBase.DataBaseConnection()
					.getConnectionStrCity()); // 连接数据库
			stmt = con.createStatement(); // 创建Statement对象
			String SQL = null;
			int i = 0;
			if (s1.equals("province")) {
				SQL = "SELECT *  FROM Tb_Province order by ZProvinceType";
				rs = stmt.executeQuery(SQL);
				i = 0;
				while (rs.next()) {
					provinceName[i] = rs.getString("ZName");
					if (provinceName[i].equals("黑龙")) {
						provinceName[i] = "黑龙江";
					} else if (provinceName[i].equals("内蒙")) {
						provinceName[i] = "内蒙古";
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
						appellationName = "省";
						break;
					}
					case 1: {
						appellationName = "市";
						break;
					}
					case 2: {
						appellationName = "市";
						break;
					}
					case 3: {
						appellationName = "自治区";
						break;
					}
					case 4: {
						appellationName = "特区";
						break;
					}
					}
				}
				sc = new String[i];
				System.arraycopy(cityName, 0, sc, 0, i);
			}
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接

		} catch (Exception ex) {
			System.out.print("错误！！！");
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

	/**
	 * 在数组末端添加新元素
	 * 
	 * @param strinfArray
	 *            旧数组
	 * @param s
	 *            新元素
	 * @return 新数组
	 */
	private String[] StringArrayAppend(String[] strinfArray, String s) {
		String[] newStringArray = new String[strinfArray.length + 1];
		newStringArray[0] = s;
		System.arraycopy(strinfArray, 0, newStringArray, 1, strinfArray.length);
		return newStringArray;
	}

	/**
	 * 根据<code>sql</code>命令获取所有的email名称，多余一个使用逗号分开
	 * 
	 * @param sQLString
	 *            sql命令
	 * @return email名称
	 */
	public String getEmailsFromDatabase(String sQLString) {
		String emailStrings = "";
		try {
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // 加载驱动器
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			stmt = con.createStatement(); // 创建Statement对象
			rs = stmt.executeQuery(sQLString);
			while (rs.next()) {
				String email = rs.getString("EMAIL");
				if (!email.equals("")) {
					emailStrings += email;
					emailStrings += ";";
				}
			}
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
		}
		return emailStrings;
	}

	/**
	 * 根据sql指令获取ArrayList<AddressListData>
	 * 
	 * @param SQLOrderString
	 *            sql命令
	 * @return ArrayList<AddressListData>
	 */
	public static ArrayList<AddressListData> getAddressListDatasBySQL(
			String SQLOrderString) {
		ArrayList<AddressListData> al = null;
		AddressListData ald = null;
		System.out.println(SQLOrderString);
		try {
			al = new ArrayList<AddressListData>();
			String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName(DBDriver); // 加载驱动器
			con = DriverManager.getConnection(DataBaseConnection
					.getConnectionStr()); // 连接数据库
			stmt = con.createStatement(); // 创建Statement对象
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
			stmt.close(); // 关闭语句
			con.close(); // 关闭连接
		} catch (Exception ex) {
		}
		return al;
	}

	/**
	 * 将序列为ID的记录的分组类型修改为groupName
	 * 
	 * @param ID
	 *            序列
	 * @param groupName
	 *            新分组名称
	 */
	public static void moveToGroup(int ID, String groupName) {
		String idString = ID + "";
		SQLExecute("update ADDRESSLISTTABLE set [CLASS]='" + groupName
				+ "' where [ID]='" + idString + "'");
	}
}
