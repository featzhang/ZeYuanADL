package laboratory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class TestPropertise {

	public static final String ICON_VIEW = "icon";
	public static final String LIST_VIEW = "list";
	private static Properties localProperties = null;
	private static File propertiesFile = null;


	public static final String DATABASENEEDINIT_SIGNAL = "DataBaseNeedInit";
	public static final String DEFAULTMAILENABLE_SIGNAL = "DefaultMailenable";
	public static final String DEFAULTMAILSERVERENABLE_SIGNAL = "DefaultMailServerenable";
	public static final String DISABLE_SIGNAL = "DISABLE";
	public static final String EMAILADDRESS_SIGNAL = "EmailAddress";
	public static final String EMAILPASSWORD_SIGNAL = "EmailPassword";
	public static final String EMAILSMTPHOST_SIGNAL = "EmailSMTPHost";
	public static final String ENABLE_SIGNAL = "ENABLE";
	public static final String MainTabbedPaneSelected_SIGNAL = "TabbedSelected";
	public static final String PASSWORD_SIGNAL = "Password";
	public static final String PASSWORDENABLE_SIGNAL = "Passwordable";
	public static final String UILookAndFeelNAME_SIGNAL = "UILookAndFeel";
	public static final String USERNAME_SIGNAL = "UserName";
	public static final String USERWORKPLACE_SIGNAL = "UserWorkplace";
	public static final String USETIMES_SIGNAL = "UseTimes";
	public static final String View_SIGNAL = "View";

	public boolean getDatabaseNeedInit() {
		reloadProperties();
		return ENABLE_SIGNAL.equals(localProperties
				.getProperty(DATABASENEEDINIT_SIGNAL));
	}

	public boolean getDefaultMailEnable() {
		reloadProperties();
		return ENABLE_SIGNAL.equals(localProperties
				.getProperty(DEFAULTMAILENABLE_SIGNAL));
	}

	public boolean getDefaultMailServerEnable() {
		reloadProperties();
		return ENABLE_SIGNAL.equals(localProperties
				.getProperty(DEFAULTMAILSERVERENABLE_SIGNAL));
	}

	public String getEmailAaaress() {
		reloadProperties();
		return localProperties.getProperty(EMAILADDRESS_SIGNAL);
	}

	public String getEmailPassword() {
		reloadProperties();
		return localProperties.getProperty(EMAILPASSWORD_SIGNAL);
	}

	public String getEmailSMTPHost() {
		reloadProperties();
		return localProperties.getProperty(EMAILSMTPHOST_SIGNAL);
	}

	public int getMainTabbedPaneSelectedIndex() {
		reloadProperties();
		String string = localProperties
				.getProperty(MainTabbedPaneSelected_SIGNAL);

		return Integer.parseInt(string);
	}

	public String getPassword() {
		reloadProperties();
		return localProperties.getProperty(PASSWORD_SIGNAL);
	}

	public boolean getPasswordEnable() {
		reloadProperties();
		return ENABLE_SIGNAL.equals(localProperties
				.getProperty(PASSWORDENABLE_SIGNAL));
	}

	public String getUILookAndFeelNAME() {
		reloadProperties();
		return localProperties.getProperty(UILookAndFeelNAME_SIGNAL);
	}

	public String getUserName() {
		reloadProperties();
		return localProperties.getProperty(USERNAME_SIGNAL);
	}

	public String getUserWorkPlace() {
		reloadProperties();
		return localProperties.getProperty(USERWORKPLACE_SIGNAL);
	}

	public int getUseTimes() {
		reloadProperties();
		String string = localProperties.getProperty(USETIMES_SIGNAL);
		return Integer.parseInt(string);
	}

	public String getViewName() {
		reloadProperties();
		return localProperties.getProperty(View_SIGNAL);
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
				newProperties.setProperty(USETIMES_SIGNAL, "0");
				newProperties.setProperty(DATABASENEEDINIT_SIGNAL, "0");
				newProperties.setProperty(USERNAME_SIGNAL, "0");
				newProperties.setProperty(USERWORKPLACE_SIGNAL, "0");
				newProperties
						.setProperty(PASSWORDENABLE_SIGNAL, DISABLE_SIGNAL);
				newProperties.setProperty(PASSWORD_SIGNAL, "9008");
				newProperties.setProperty(UILookAndFeelNAME_SIGNAL,
						javax.swing.UIManager.getSystemLookAndFeelClassName());
				newProperties.setProperty(DEFAULTMAILSERVERENABLE_SIGNAL,
						DISABLE_SIGNAL);
				newProperties.setProperty(DEFAULTMAILENABLE_SIGNAL,
						ENABLE_SIGNAL);
				newProperties.setProperty(EMAILADDRESS_SIGNAL, "0");
				newProperties.setProperty(EMAILPASSWORD_SIGNAL, "0");
				newProperties.setProperty(EMAILPASSWORD_SIGNAL, "0");
				newProperties.setProperty(EMAILSMTPHOST_SIGNAL, "smtp.163.com");
				newProperties.setProperty(View_SIGNAL, LIST_VIEW);
				newProperties.setProperty(MainTabbedPaneSelected_SIGNAL, "0");
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

	public void setProperty(String key, String value) {
		localProperties.setProperty(key, value);
		saveProperties();
	}

	public void setProperty(String key, int value) {
		localProperties.setProperty(key, "" + value);
		saveProperties();
	}

	public void setProperty(String key, Boolean value) {

		String valueString = value ? ENABLE_SIGNAL : DISABLE_SIGNAL;
		localProperties.setProperty(key, valueString);
		saveProperties();
	}
}
