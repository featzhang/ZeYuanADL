package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * 系统配置文件管理类
 * 
 * @author 张作峰
 */
public class ConfigManager {

	public static final String ICON_VIEW = "icon";
	public static final String LIST_VIEW = "list";
	private static Properties localProperties = new Properties();
	private static File propertiesFile = null;

	public static final String DATABASENEEDINIT = "DataBaseNeedInit";
	public static final String DEFAULTMAILENABLE = "DefaultMailenable";
	public static final String DEFAULTMAILSERVERENABLE = "DefaultMailServerenable";
	public static final String DISABLE = "DISABLE";
	public static final String EMAILADDRESS = "EmailAddress";
	public static final String EMAILPASSWORD = "EmailPassword";
	public static final String EMAILSMTPHOST = "EmailSMTPHost";
	public static final String ENABLE = "ENABLE";
	public static final String MainTabbedPaneSelected = "TabbedSelected";
	public static final String PASSWORD = "Password";
	public static final String PASSWORDENABLE = "Passwordable";
	public static final String UILookAndFeelNAME = "UILookAndFeel";
	public static final String USERNAME = "UserName";
	public static final String USERWORKPLACE = "UserWorkplace";
	public static final String USETIMES = "UseTimes";
	public static final String ViewSIGNAL = "View";

	public boolean getDatabaseNeedInit() {
		reloadProperties();
		return ENABLE.equals(localProperties.getProperty(DATABASENEEDINIT));
	}

	public boolean getDefaultMailEnable() {
		reloadProperties();
		return ENABLE.equals(localProperties.getProperty(DEFAULTMAILENABLE));
	}

	public boolean getDefaultMailServerEnable() {
		reloadProperties();
		return ENABLE.equals(localProperties
				.getProperty(DEFAULTMAILSERVERENABLE));
	}

	public String getEmailAddressString() {
		reloadProperties();
		return localProperties.getProperty(EMAILADDRESS);
	}

	public String getEmailPasswordString() {
		reloadProperties();
		return localProperties.getProperty(EMAILPASSWORD);
	}

	public String getEmailSMTPHostString() {
		reloadProperties();
		return localProperties.getProperty(EMAILSMTPHOST);
	}

	public int getMainTabbedPaneSelectedIndex() {
		reloadProperties();
		String string = localProperties.getProperty(MainTabbedPaneSelected);
		return Integer.parseInt(string);
	}

	public String getpassword() {
		reloadProperties();
		return localProperties.getProperty(PASSWORD);
	}

	public boolean getPasswordEnable() {
		reloadProperties();
		return ENABLE.equals(localProperties.getProperty(PASSWORDENABLE));
	}

	public String getLookAndFeelClassName() {
		reloadProperties();
		return localProperties.getProperty(UILookAndFeelNAME);
	}

	public String getUserName() {
		reloadProperties();
		return localProperties.getProperty(USERNAME);
	}

	public String getUserWorkPlace() {
		reloadProperties();
		return localProperties.getProperty(USERWORKPLACE);
	}

	public int getUseTimes() {
		reloadProperties();
		String string = localProperties.getProperty(USETIMES);
		return Integer.parseInt(string);
	}

	public String getViewName() {
		reloadProperties();
		return localProperties.getProperty(ViewSIGNAL);
	}

	public void reloadProperties() {
		if (propertiesFile == null) {
			propertiesFile = new File("systemset.ini");
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(
					propertiesFile);
			localProperties.load(fileInputStream);
		} catch (Exception e) {
		}
	}

	public void saveProperties() {
		if (localProperties == null) {
			return;
		}

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					propertiesFile);
			localProperties.store(fileOutputStream, "Zeyuan AddressList 0.2.1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean systemFileCheck() {
		if (propertiesFile == null) {
			propertiesFile = new File("systemset.ini");
		}
		if (propertiesFile.exists()) {
			return true;
		} else {
			int i = JOptionPane.showConfirmDialog(null,
					"配置文件丢失，是否重新建立！\n所有的配置将初始化！", "警告！",
					JOptionPane.YES_NO_OPTION);
			if (i == 0) {
				Properties newProperties = new Properties();
				newProperties.setProperty(USETIMES, "0");
				newProperties.setProperty(DATABASENEEDINIT, "0");
				newProperties.setProperty(USERNAME, "0");
				newProperties.setProperty(USERWORKPLACE, "0");
				newProperties.setProperty(PASSWORDENABLE, DISABLE);
				newProperties.setProperty(PASSWORD, "9008");
				newProperties.setProperty(UILookAndFeelNAME,
						javax.swing.UIManager.getSystemLookAndFeelClassName());
				newProperties.setProperty(DEFAULTMAILSERVERENABLE, DISABLE);
				newProperties.setProperty(DEFAULTMAILENABLE, ENABLE);
				newProperties.setProperty(EMAILADDRESS, "0");
				newProperties.setProperty(EMAILPASSWORD, "0");
				newProperties.setProperty(EMAILPASSWORD, "0");
				newProperties.setProperty(EMAILSMTPHOST, "smtp.163.com");
				newProperties.setProperty(ViewSIGNAL, LIST_VIEW);
				newProperties.setProperty(MainTabbedPaneSelected, "0");
				try {
					propertiesFile.createNewFile();
					FileOutputStream fileOutputStream = new FileOutputStream(
							propertiesFile);
					newProperties.store(fileOutputStream,
							"Zeyuan AddressList 0.2.1");
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return false;
			}
		}
	}

	private void setProperty(String key, String value) {
		localProperties.setProperty(key, value);
		saveProperties();
	}

	private void setProperty(String key, int value) {
		reloadProperties();
		localProperties.setProperty(key, "" + value);
		saveProperties();
	}

	private void setProperty(String key, Boolean value) {

		String valueString = value ? ENABLE : DISABLE;
		localProperties.setProperty(key, valueString);
		saveProperties();
	}

	public void setUserNameString(String userNameNewString) {
		localProperties.setProperty(USERNAME, userNameNewString);
		saveProperties();
	}

	public void setUserWorkplace(String userWorkSpaceNewString) {
		setProperty(USERWORKPLACE, userWorkSpaceNewString);
	}

	public void setDefaultMailServerEnable(boolean defaultMailServerEnable) {
		setProperty(DEFAULTMAILSERVERENABLE, defaultMailServerEnable);
	}

	public void setDefaultMailEnable(boolean defaultMailEnable) {
		setProperty(DEFAULTMAILENABLE, defaultMailEnable);
	}

	public void setEmailAddressString(String emailAddressString) {
		setProperty(EMAILADDRESS, emailAddressString);
	}

	public void setEmailSMTPHostString(String emailSMtpHostString) {
		setProperty(EMAILSMTPHOST, emailSMtpHostString);
	}

	public void setEmailPasswordString(String emailPasswordString) {
		setProperty(EMAILPASSWORD, emailPasswordString);
	}

	public void setUILookAndFeelNameString(String classNameString) {
		setProperty(UILookAndFeelNAME, classNameString);
	}

	public void setMainTabbedPaneSelectedIndex(int mainTabbedPanelSelectedIndex) {
		setProperty(MainTabbedPaneSelected, mainTabbedPanelSelectedIndex);
	}

	public void setDatabaseNeedInit(boolean b) {
		setProperty(DATABASENEEDINIT, b);
	}

	public void setView(String listView) {
		setProperty(ViewSIGNAL, listView);
	}

	public void setTabbedSelectIndex(int mainTabbedPanelSelectedIndex) {
		setProperty(MainTabbedPaneSelected, mainTabbedPanelSelectedIndex);
	}

	public void setPasswordEnable(boolean b) {
		setProperty(PASSWORDENABLE, b);
	}

	public void setPassword(String b) {
		setProperty(PASSWORDENABLE, b);
	}
}
