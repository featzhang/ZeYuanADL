package apps;

import Excel.MyFileFilter;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import ui.AButton;
import ui.ADialog;

public class AddNewAppDialog extends ADialog {

    private String currentPath = System.getProperty("user.home");

    /** Creates new form NewJDialog */
    public AddNewAppDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        iconTextField = new javax.swing.JTextField();
        cancelButton = new AButton();
        sureButton = new AButton();
        exeBrowerButton = new AButton();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        iconBrowerButton2 = new AButton();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        exeTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("添加新应用");

        jPanel1.setOpaque(false);

        iconTextField.setEditable(false);
        iconTextField.setOpaque(false);

        cancelButton.setText("取消");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        sureButton.setText("添加");
        sureButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                sureButtonActionPerformed(evt);
            }
        });

        exeBrowerButton.setText("浏览");
        exeBrowerButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                exeBrowerButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("图标");

        nameTextField.setOpaque(false);

        iconBrowerButton2.setText("浏览");
        iconBrowerButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                iconBrowerButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("可执行文件");

        jLabel1.setText("名称");

        exeTextField.setEditable(false);
        exeTextField.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(iconTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iconBrowerButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exeBrowerButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sureButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)))
                        .addGap(10, 10, 10))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exeBrowerButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iconTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconBrowerButton2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(sureButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconBrowerButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iconBrowerButton2ActionPerformed
        javax.swing.JFileChooser filechooser = new javax.swing.JFileChooser();
        filechooser.setCurrentDirectory(new java.io.File(currentPath));
        filechooser.removeChoosableFileFilter(filechooser.getAcceptAllFileFilter()); // 移去所有文件过滤器
        String[] iconEndsStrings = {"bmp", "jpg", "jpeg", "png"};
        filechooser.addChoosableFileFilter(new MyFileFilter("bmp", "位映射存储格式(bmp)"));
        filechooser.addChoosableFileFilter(new MyFileFilter("jpg", "联合图像专家组(jpg)"));
        filechooser.addChoosableFileFilter(new MyFileFilter("jpeg", "联合图像专家组(jpeg)"));
        filechooser.addChoosableFileFilter(new MyFileFilter("png", "可移植性网络图像(png)"));
        filechooser.addChoosableFileFilter(new MyFileFilter(iconEndsStrings, "常见图片格式"));

        filechooser.setMultiSelectionEnabled(false);
        int d = filechooser.showDialog(null, "选择图标文件");
        if (d == javax.swing.JFileChooser.APPROVE_OPTION) {
            iconFile = filechooser.getSelectedFile();
            iconTextField.setText(iconFile.getPath());
        } else {
            iconTextField.setText("");
        }
    }//GEN-LAST:event_iconBrowerButton2ActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void exeBrowerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exeBrowerButtonActionPerformed
        javax.swing.JFileChooser filechooser = new javax.swing.JFileChooser();
        filechooser.setCurrentDirectory(new java.io.File(currentPath));
        filechooser.removeChoosableFileFilter(filechooser.getAcceptAllFileFilter()); // 移去所有文件过滤器
        filechooser.addChoosableFileFilter(new MyFileFilter("jar",
                "Java可执行文件(jar)")); // 增加文件过滤器,筛选xls文件
        filechooser.setMultiSelectionEnabled(false);
        int d = filechooser.showDialog(null, "选择可执行文件");
        if (d == javax.swing.JFileChooser.APPROVE_OPTION) {
            exeFile = filechooser.getSelectedFile();
            exeTextField.setText(exeFile.getPath());
        } else {
            exeTextField.setText("");
        }
    }//GEN-LAST:event_exeBrowerButtonActionPerformed

    private void sureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sureButtonActionPerformed
        if (nameTextField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "请输入名称！");
            return;
        }
        if (exeTextField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "请选择可执行文件！");
            return;
        }
        if (iconTextField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "请选择图标！");
            return;
        }
        String path = "APPs";
        File appsDir = new File(path);
        if (!appsDir.exists()) {
            appsDir.mkdir();
        }
        path = "APPs" + System.getProperty("file.separator") + nameTextField.getText().trim();
        appsDir = new File(path);
        if (!appsDir.exists()) {
            appsDir.mkdir();
        }
        File newExeFile = new File(path + System.getProperty("file.separator") + exeFile.getName());
        copyFile(exeFile, newExeFile);
        File newIconFile = new File(path + System.getProperty("file.separator") + iconFile.getName());
        copyFile(iconFile, newIconFile);
        dispose();
    }//GEN-LAST:event_sureButtonActionPerformed
    private java.io.File exeFile = null;
    private java.io.File iconFile = null;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                AddNewAppDialog dialog = new AddNewAppDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton exeBrowerButton;
    private javax.swing.JTextField exeTextField;
    private javax.swing.JButton iconBrowerButton2;
    private javax.swing.JTextField iconTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton sureButton;
    // End of variables declaration//GEN-END:variables

    private void copyFile(java.io.File oldFile, java.io.File newFile) {
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream(oldFile);
            byte[] b = new byte[128];
            int read;
            java.io.FileOutputStream fos = new java.io.FileOutputStream(newFile);
            while ((read = fis.read(b)) != -1) {
                fos.write(b);
            }
            fis.close();
            fos.close();
        } catch (java.io.FileNotFoundException ex) {
        } catch (java.io.IOException ex) {
        }
    }
}
