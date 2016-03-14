package mail;

import ui.ADialog;
import util.ConfigManager;
import components.SystemOptionalDialog;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SendMailJDialog extends ADialog {

    private String subjectS;
    private String textS;
    private String sendMailTo;
    private JFrame parentFrame;
//mail.setUser("zeyuansendmail"); // 用户名
//mail.setPassword("zeyuansendfrom16"); // 密码

    public SendMailJDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        parentFrame = parent;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        attachmentFileChooser = new javax.swing.JFileChooser();
        attachmentAdminButton = new javax.swing.JButton();
        addEmailButton = new javax.swing.JButton();
        javax.swing.JLabel addAttachmentLabel = new javax.swing.JLabel();
        mailSetButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        mailTextTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        mailSubjectTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel sendToLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();
        sendToTextField = new javax.swing.JTextField();
        attachmentTextField = new javax.swing.JTextField();

        attachmentFileChooser.setDialogTitle("添加附件");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("邮件发送");

        attachmentAdminButton.setText("附件");
        attachmentAdminButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachmentAdminButtonActionPerformed(evt);
            }
        });

        addEmailButton.setText("添加");
        addEmailButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmailButtonActionPerformed(evt);
            }
        });

        addAttachmentLabel.setText("附  件:");

        mailSetButton.setText("邮箱设置");
        mailSetButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailSetButtonActionPerformed(evt);
            }
        });

        sendButton.setText("发送");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        mailTextTextArea.setColumns(20);
        mailTextTextArea.setRows(5);
        mailTextTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(mailTextTextArea);

        closeButton.setText("关闭");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("主  题:");

        sendToLabel.setText("收件人:");

        jLabel3.setText("内  容:");

        attachmentTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(stateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sendToLabel)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mailSubjectTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                    .addComponent(sendToTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addAttachmentLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(attachmentTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(attachmentAdminButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addEmailButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mailSetButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sendToLabel)
                    .addComponent(sendToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEmailButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(mailSubjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(mailSetButton))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAttachmentLabel)
                    .addComponent(attachmentAdminButton)
                    .addComponent(attachmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(closeButton)
                        .addComponent(sendButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMailTo = sendToTextField.getText();
        subjectS = mailSubjectTextField.getText();
        textS = mailTextTextArea.getText();
        sendButton.setEnabled(false);
        new Thread() {

            @Override
            public void run() {
                ConfigManager configManager = new ConfigManager();
                boolean defaultMailEnable = configManager.getDefaultMailEnable();
                stateLabel.setText("正在发送邮件...");
                System.out.println("SENDER-" + this.getId() + ":/>开始发送邮件...");
                // 创建邮件对象
                Mail mail = new Mail();
                if (defaultMailEnable) {
                    mail.setHost("smtp.163.com"); // 邮件服务器地址
                    mail.setUser("zeyuansendmail"); // 用户名
                    mail.setPassword("zeyuansendfrom16"); // 密码
                    mail.setFrom("zeyuansendmail@163.com"); // 发件人邮箱
                } else {
                    String emailAddressString = configManager.getEmailAddressString();
                    String emailPasswordString = configManager.getEmailPasswordString();
                    String emailHost = configManager.getEmailSMTPHostString();
                    mail.setHost(emailHost); // 邮件服务器地址
                    // 邮件服务器地址
                    String emailUserName = emailAddressString.substring(0, emailAddressString.indexOf('@'));
                    mail.setUser(emailUserName); // 用户名
                    mail.setPassword(emailPasswordString); // 密码
                    mail.setFrom(emailAddressString); // 发件人邮箱
                }
                sendMailTo = sendToTextField.getText();
                String[] split = sendMailTo.split(";");
                for (int i = 0; i < split.length; i++) {
                    String string = split[i];
                    mail.addTo(string); // 收件人邮箱
                }
                mail.setSubject(subjectS); // 邮件主题
                mail.setContent(textS); // 邮件正文
                String text = attachmentTextField.getText();
                if (!text.equals("")) {
                    String[] split1 = text.split(";");
                    for (int i = 0; i < split1.length; i++) {
                        String string = split1[i];
//                        System.out.println(string);
                        mail.addAttachment(string);
                    }
                }
                // 添加附件
                String s = mail.send();
                stateLabel.setText(s);
//                stateLabel.setText("邮件发送成功...");
                System.out.println(s); // 发送
                System.out.println("SENDER-" + this.getId() + ":/>"
                        + "邮件已发送完毕！");
                sendButton.setEnabled(true);
            }
        }.start();
}//GEN-LAST:event_sendButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
}//GEN-LAST:event_closeButtonActionPerformed

    private void mailSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailSetButtonActionPerformed
        SystemOptionalDialog systemOptionalDialog = new SystemOptionalDialog(parentFrame, true);
        systemOptionalDialog.setMainTabbedPane1SelectedIndex(2);
        int x, y;
        x = (parentFrame.getWidth() - systemOptionalDialog.getWidth()) / 2 + parentFrame.getX();
        y = (parentFrame.getHeight() - systemOptionalDialog.getHeight()) / 2 + parentFrame.getY();
        systemOptionalDialog.setLocation(x, y);
        systemOptionalDialog.setVisible(true);
    }//GEN-LAST:event_mailSetButtonActionPerformed

    private void addEmailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmailButtonActionPerformed
        AddAddresseeDialog addresseeJDialog = new AddAddresseeDialog(parentFrame, true);
        int x, y;
        x = parentFrame.getX() + (parentFrame.getWidth() - addresseeJDialog.getWidth()) / 2;
        y = parentFrame.getY() + (parentFrame.getHeight() - addresseeJDialog.getHeight()) / 2;
        addresseeJDialog.setLocation(x, y);
        addresseeJDialog.setVisible(true);
        String[] otherEmailStrings = addresseeJDialog.getEmailStrings();
        if (otherEmailStrings != null) {
            if (!sendToTextField.getText().equals("")) {
                String s;
                s = sendToTextField.getText();
                for (int i = 0; i < otherEmailStrings.length; i++) {
                    String string = otherEmailStrings[i];
                    s += ";" + string;
                }
                sendToTextField.setText(s);
            } else {
                String s = "";
                s += otherEmailStrings[0];
                for (int i = 1; i < otherEmailStrings.length; i++) {
                    String string = otherEmailStrings[i];
                    s += ";" + string;
                }
                sendToTextField.setText(s);
            }
        }
    }//GEN-LAST:event_addEmailButtonActionPerformed

    private void attachmentAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachmentAdminButtonActionPerformed
        int showOpenDialog = attachmentFileChooser.showOpenDialog(parentFrame);
        if (showOpenDialog == javax.swing.JFileChooser.APPROVE_OPTION) {
            File selectedFile = attachmentFileChooser.getSelectedFile();
            String name = selectedFile.toString();
            String text = attachmentTextField.getText();
            if (!text.equals("")) {
                text += ";" + name;
            } else {
                text = name;
            }
            attachmentTextField.setText(text);
        }
    }//GEN-LAST:event_attachmentAdminButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton addEmailButton;
    javax.swing.JButton attachmentAdminButton;
    javax.swing.JFileChooser attachmentFileChooser;
    javax.swing.JTextField attachmentTextField;
    javax.swing.JButton closeButton;
    javax.swing.JButton mailSetButton;
    javax.swing.JTextField mailSubjectTextField;
    javax.swing.JTextArea mailTextTextArea;
    javax.swing.JButton sendButton;
    javax.swing.JTextField sendToTextField;
    javax.swing.JLabel stateLabel;
    // End of variables declaration//GEN-END:variables

    public void setSendTo(String Email) {
        sendMailTo = Email;
        sendToTextField.setText(Email);
    }

    public JTextField getSendToTextField() {
        return sendToTextField;
    }

    public JTextField getMailSubjectTextField() {
        return mailSubjectTextField;
    }

    public JButton getAddEmailButton() {
        return addEmailButton;
    }

    public JButton getAttachmentAdminButton() {
        return attachmentAdminButton;
    }

    public JButton getMailSetButton() {
        return mailSetButton;
    }

    public JButton getSendButton() {
        return sendButton;
    }

}
