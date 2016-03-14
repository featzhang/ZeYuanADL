package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * 系统配置文件管理类
 * @author 张作峰
 */
public class ConfigFileManager {

    private final String ENABLE_SIGNAL = "ENABLE";
    private final String DISABLE_SIGNAL = "DISABLE";
    private final String USETIMES_SIGNAL = "UseTimes";
    private final String DATABASENEEDINIT_SIGNAL = "DataBaseNeedInit";
    private final String USERNAME_SIGNAL = "UserName";
    private final String USERWORKPLACE_SIGNAL = "UserWorkplace";
    private final String PASSWORDENABLE_SIGNAL = "Passwordable";
    private final String PASSWORD_SIGNAL = "Password";
    private final String UILookAndFeelNAME_SIGNAL = "UILookAndFeel";
    private final String DEFAULTMAILSERVERENABLE_SIGNAL = "DefaultMailServerenable";
    private final String DEFAULTMAILENABLE_SIGNAL = "DefaultMailenable";
    private final String EMAILADDRESS_SIGNAL = "EmailAddress";
    private final String EMAILPASSWORD_SIGNAL = "EmailPassword";
    private final String EMAILSMTPHOST_SIGNAL = "EmailSMTPHost";
    private final String View_SIGNAL = "View";
    private final String MainTabbedPaneSelected_SIGNAL = "TabbedSelected";
    private String currentMDBFileDir = new stateClass().getcurrentDir();
    private File systemFileName = new File(currentMDBFileDir + "\\systemset.ini");
    private String useTimes;
    private String isDatabaseNeedInit;
    private String userNameString;
    private String userWorkplaceString;
    private boolean passwordEnable;
    private String passwordString;
    private String classNameString;
    private boolean defaultMailServerEnable;
    private boolean defaultMailEnable;
    private String emailAddressString;
    private String emailPasswordString;
    private String emailSMTPHostString;
    private String viewString;
    private int mainTabbedPaneSelectedIndex;
    public static final String LIST_VIEW = "list";
    public static final String ICON_VIEW = "icon";

    public ConfigFileManager() {
        filesCheck();
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(systemFileName);
            properties.load(fis);
            Iterator<?> keys = properties.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = properties.getProperty(key);

                if (key.equals(USETIMES_SIGNAL)) {
                    useTimes = value;
                } else if (key.equals(USERNAME_SIGNAL)) {
                    userNameString = value;
                } else if (key.equals(PASSWORDENABLE_SIGNAL)) {
                    if (value.equals(ENABLE_SIGNAL)) {
                        passwordEnable = true;
                    } else if (value.equals(DISABLE_SIGNAL)) {
                        passwordEnable = false;
                    }
                } else if (key.equals(PASSWORD_SIGNAL)) {
                    passwordString = value;
                } else if (key.equals(USERWORKPLACE_SIGNAL)) {
                    userWorkplaceString = value;
                } else if (key.equals(UILookAndFeelNAME_SIGNAL)) {
                    classNameString = value;
                } else if (key.equals(DATABASENEEDINIT_SIGNAL)) {
                    isDatabaseNeedInit = value;
                } else if (key.equals(DEFAULTMAILSERVERENABLE_SIGNAL)) {
                    if (value.equals(ENABLE_SIGNAL)) {
                        defaultMailServerEnable = true;
                    } else if (value.equals(DISABLE_SIGNAL)) {
                        defaultMailServerEnable = false;
                    }
                } else if (key.equals(DEFAULTMAILENABLE_SIGNAL)) {
                    if (value.equals(ENABLE_SIGNAL)) {
                        defaultMailEnable = true;
                    } else if (value.equals(DISABLE_SIGNAL)) {
                        defaultMailEnable = false;
                    }
                } else if (key.equals(EMAILADDRESS_SIGNAL)) {
                    emailAddressString = value;
                } else if (key.equals(EMAILPASSWORD_SIGNAL)) {
                    emailPasswordString = value;
                } else if (key.equals(EMAILSMTPHOST_SIGNAL)) {
                    emailSMTPHostString = value;
                } else if (key.equals(View_SIGNAL)) {
                    viewString = value;
                } else if (key.equals(MainTabbedPaneSelected_SIGNAL)) {
                    mainTabbedPaneSelectedIndex = Integer.parseInt(value);
                }
            }
            fis.close();
        } catch (IOException ef) {
        }
    }

    public boolean getDefaultMailEnable() {
        return defaultMailEnable;
    }

    public boolean getDefaultMailServerEnable() {
        return defaultMailServerEnable;
    }

    public String getEmailAddressString() {
        if (emailAddressString.equals("0")) {
            return "";
        }
        return emailAddressString;
    }

    public String getEmailPasswordString() {
        return emailPasswordString;
    }

    public boolean isDatabaseNeedInit() {
        if (isDatabaseNeedInit.equals(DISABLE_SIGNAL)) {
            return false;
        } else {
            return true;
        }
    }

    public String getLookAndFeelClassName() {
        return classNameString;
    }

    public String getpassword() {
        return passwordString;
    }

    public boolean getPasswordEnable() {
        return passwordEnable;
    }

    public int getuot() {
        int uot = Integer.parseInt(useTimes);
        setproperty(USETIMES_SIGNAL, uot + 1);
        return uot;
    }

    public String getUserName() {
        if (userNameString.equals("0")) {
            return "泽苑软件";
        }
        return userNameString;
    }

    public String getUserWorkPlace() {
        if (userWorkplaceString.equals("0")) {
            return "泽苑软件";
        }
        return userWorkplaceString;
    }

    public String getViewString() {
        return viewString;
    }

    /**
     * 设置用户界面UILookAndFeel类型名称
     * @param UILookAndFeelNameString 用户界面类型名称
     */
    public void setUILookAndFeelNameString(String UILookAndFeelNameString) {
        setproperty(UILookAndFeelNAME_SIGNAL, UILookAndFeelNameString);
    }

    public void setDefaultMailEnable(boolean defaultMailEnable) {
        if (defaultMailEnable) {
            setproperty(DEFAULTMAILENABLE_SIGNAL, ENABLE_SIGNAL);
        } else {
            setproperty(DEFAULTMAILENABLE_SIGNAL, DISABLE_SIGNAL);
        }
    }

    public void setDefaultMailServerEnable(boolean defaultMailServerEnable) {
        if (defaultMailServerEnable) {
            setproperty(DEFAULTMAILSERVERENABLE_SIGNAL, ENABLE_SIGNAL);
        } else {
            setproperty(DEFAULTMAILSERVERENABLE_SIGNAL, DISABLE_SIGNAL);
        }
    }

    public void setEmailAddressString(String emailAddressString) {
        setproperty(EMAILADDRESS_SIGNAL, emailAddressString);
    }

    public void setEmailPasswordString(String emailPasswordString) {
        setproperty(EMAILPASSWORD_SIGNAL, emailPasswordString);
    }

    public void setDatabaseNeedInit(boolean b) {
        if (b == true) {
            setproperty(DATABASENEEDINIT_SIGNAL, ENABLE_SIGNAL);
        } else {
            setproperty(DATABASENEEDINIT_SIGNAL, DISABLE_SIGNAL);
        }
    }

    public void setpassword(String s) {
        setproperty(PASSWORD_SIGNAL, s);
    }

    public void setpasswordable(boolean a) {
        if (a == true) {
            setproperty(PASSWORDENABLE_SIGNAL, ENABLE_SIGNAL);
        } else if (a == false) {
            setproperty(PASSWORDENABLE_SIGNAL, DISABLE_SIGNAL);
        }
    }

    public void setPasswordEnable(boolean passwordEnable) {
        if (passwordEnable) {
            setproperty(PASSWORDENABLE_SIGNAL, ENABLE_SIGNAL);
        } else {
            setproperty(PASSWORDENABLE_SIGNAL, DISABLE_SIGNAL);
        }
    }

    public void setPasswordString(String passwordString) {
        setproperty(PASSWORD_SIGNAL, passwordString);
    }

    public boolean setproperty(String setkey, int setvalue) {
        setproperty(setkey, setvalue + "");
        return false;
    }

    public boolean setproperty(String setkey, String setvalue) {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(systemFileName);
            properties.load(fis);
            properties.setProperty(setkey, setvalue);
            fis.close();
            FileOutputStream fos = new FileOutputStream(systemFileName);
            properties.store(fos, "Zeyuan AddressList V0.2.1");
            fos.close();
        } catch (IOException ef) {
        }
        return false;
    }

    private void setUseofTime(int t) {
        setproperty(USETIMES_SIGNAL, t);
    }

    public void setUserNameString(String s) {
        setproperty(USERNAME_SIGNAL, s);
    }

    public void setUserWorkplace(String s) {
        setproperty(USERWORKPLACE_SIGNAL, s);
    }

    public void setUserWorkplaceString(String userWorkplaceString) {
        setproperty(USERWORKPLACE_SIGNAL, userWorkplaceString);
    }

    public String getEmailSMTPHostString() {
        return emailSMTPHostString;
    }

    public void setEmailSMTPHostString(String emailSMTPHostString) {
        setproperty(EMAILSMTPHOST_SIGNAL, emailSMTPHostString);
    }

    public void setViewString(String viewString) {
        this.viewString = viewString;
        setproperty(View_SIGNAL, viewString);
    }

    public boolean systemFileCheck() {

        if (!systemFileName.exists()) {
            File tempfile = new File("zyadls.ini");
            try {
                tempfile.createNewFile();
                if (tempfile.exists()) {
                    System.out.println("文件不存在，创建新文件" + tempfile + "成功。");
                } else {
                    System.out.print("文件创建失败！");
                    return false;
                }
                RandomAccessFile file = new RandomAccessFile(tempfile, "rw");
                file.writeBytes(USETIMES_SIGNAL + "=0\n");
                file.writeBytes(DATABASENEEDINIT_SIGNAL + "=" + ENABLE_SIGNAL + "\n");
                file.writeBytes(USERNAME_SIGNAL + "=0\n");
                file.writeBytes(USERWORKPLACE_SIGNAL + "=0\n");
                file.writeBytes(PASSWORDENABLE_SIGNAL + "=" + DISABLE_SIGNAL + "\n");
                file.writeBytes(PASSWORD_SIGNAL + "=9008\n");
                file.writeBytes(UILookAndFeelNAME_SIGNAL + "=" + javax.swing.UIManager.getSystemLookAndFeelClassName() + "\n");
                file.writeBytes(DEFAULTMAILSERVERENABLE_SIGNAL + "=" + DISABLE_SIGNAL + "\n");
                file.writeBytes(DEFAULTMAILENABLE_SIGNAL + "=" + ENABLE_SIGNAL + "\n");
                file.writeBytes(EMAILADDRESS_SIGNAL + "=0 \n");
                file.writeBytes(EMAILPASSWORD_SIGNAL + "=0 \n");
                file.writeBytes(EMAILSMTPHOST_SIGNAL + "=smtp.163.com \n");
                file.writeBytes(View_SIGNAL + "=" + LIST_VIEW + "\n");
                file.writeBytes(MainTabbedPaneSelected_SIGNAL + "=" + 0 + "\n");
                file.close();
            } catch (IOException e) {
            }
            try {
                Properties properties = new Properties();
                FileInputStream fis = new FileInputStream(tempfile);
                properties.load(fis);
                fis.close();

                // 输入文件
                FileOutputStream fos = new FileOutputStream(systemFileName);
                properties.store(fos, "Zeyuan AddressList 0.2.1");
                fos.close();
            } catch (IOException ef) {
            }
            if (systemFileName.exists()) {
                tempfile.delete();
            } else {
                return false;
            }
        }
        return true;
    }

    private void filesCheck() {
        System.out.print("文件完整性检查：");
        boolean fileIsExist, needDisuse;
        needDisuse = false;
        fileIsExist = true;
        String message = "";
        String adllMDBFileDir = currentMDBFileDir + "\\adll.mdb";
        File filename = new File(adllMDBFileDir);
        if (!filename.exists()) {
            needDisuse = true;
            fileIsExist = false;
            message += "系统未能找到数据库文件“adll.mdb”，将强行退出！\n请确认该文件是否存在或者是否正确设置软件默认启动路径！" + currentMDBFileDir;
            System.out.println("当前路径：" + currentMDBFileDir);
        } else {
            if (!systemFileName.exists()) {
                fileIsExist = false;
                message += "系统配置文件不存在，将使用默认配置！\n";
            }
        }
        if (needDisuse) {
            System.out.println("失败。");
            JOptionPane.showMessageDialog(null, message, "警告",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (!fileIsExist) {
            message += "是否继续？";
            int i = JOptionPane.showConfirmDialog(null, message, "警告",
                    JOptionPane.YES_NO_OPTION);
            if (i == 1) {
                System.out.println("用户取消！");
                System.exit(0);
            } else if (!new File("systemset.ini").exists()) {
                System.out.println("强制启动！");
                systemFileCheck();
            } else {
                System.out.println("强制启动！");
            }
        } else {
            System.out.println("正常！");
        }
    }

    public void startUp() {
        int useI = Integer.parseInt(useTimes);
        setUseofTime(useI + 1);
    }

    public int getMainTabbedPaneSelectedIndex() {
        return mainTabbedPaneSelectedIndex;
    }

    public void setMainTabbedPaneSelectedIndex(int mainTabbedPaneSelectedIndex) {
        this.mainTabbedPaneSelectedIndex = mainTabbedPaneSelectedIndex;
        setproperty(MainTabbedPaneSelected_SIGNAL, mainTabbedPaneSelectedIndex);
    }
}
