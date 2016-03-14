package util;

import java.io.File;

public class FileUtil {

    public static void copyFile(java.io.File oldFile, java.io.File newFile) {
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream(oldFile);
            byte[] b = new byte[128];
            java.io.FileOutputStream fos = new java.io.FileOutputStream(newFile);
            while ((fis.read(b)) != -1) {
                fos.write(b);
            }
            fis.close();
            fos.close();
        } catch (java.io.FileNotFoundException ex) {
        } catch (java.io.IOException ex) {
        }
    }

    public static void openFile(File file) {
        try {
            if (file.exists()) {
                java.awt.Desktop.getDesktop().open(file);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "该文件不存在！", "警告", javax.swing.JOptionPane.OK_OPTION);
            }

        } catch (java.io.IOException ex) {
        }
    }

    public static void openFold(File file) {
        try {
            java.awt.Desktop.getDesktop().open(file.getParentFile());
        } catch (java.io.IOException ex) {
        }
    }
}
