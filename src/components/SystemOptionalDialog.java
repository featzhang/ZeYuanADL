package components;

import util.ConfigManager;
import util.passwordHandle;
import ui.ADialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SystemOptionalDialog extends ADialog implements DocumentListener {

	private static final long serialVersionUID = 546657889l;
	private String userNameOldString = null;
	private String userWorkSpaceOldString = null;
	private String passwordOldString = null;
	private boolean passwordOldEnable;
	private String classNameOldString = null;
	private boolean defaultMailServerOldEnable;
	private boolean defaultMailOldEnable;
	private String emailAddressOldString = null;
	private String emailPasswordOldString = null;
	private String userNameNewString = null;
	private String userWorkSpaceNewString = null;
	private boolean passwordNewEnable;
	private String classNameNewString = null;
	private boolean defaultMailServerNewEnable;
	private boolean defaultMailNewEnable;
	private String emailAddressNewString = null;
	private String emailPasswordNewString = null;
	private String emailSMtpHostString = null;
	private ConfigManager sfd = null;

	public SystemOptionalDialog(javax.swing.JFrame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		loadData();
		loadAction();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		mainTabbedPane1 = new javax.swing.JTabbedPane();
		userPanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		userNameTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		workPlaceTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		oldPasswordField = new javax.swing.JPasswordField();
		jLabel4 = new javax.swing.JLabel();
		userPasswordField = new javax.swing.JPasswordField();
		jLabel5 = new javax.swing.JLabel();
		userPasswordField2 = new javax.swing.JPasswordField();
		passwordEnableCheckBox = new javax.swing.JCheckBox();
		stylePanel = new javax.swing.JPanel();
		EmailPanel = new javax.swing.JPanel();
		defaultEmailServerCheckBox = new javax.swing.JCheckBox();
		defaultEmailCheckBox = new javax.swing.JCheckBox();
		jLabel6 = new javax.swing.JLabel();
		EmailTextField = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		EmailPasswordField = new javax.swing.JPasswordField();
		jLabel8 = new javax.swing.JLabel();
		EmailPasswordField2 = new javax.swing.JPasswordField();
		jLabel9 = new javax.swing.JLabel();
		emailSMTPHostTextField = new javax.swing.JTextField();
		sureButton = new ui.AButton("");
		cancelButton = new ui.AButton("");

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("系统选项");

		userPanel.setOpaque(false);

		jLabel1.setText("用户名");

		jLabel2.setText("单位");

		jLabel3.setText("旧密码");

		oldPasswordField.setForeground(new java.awt.Color(255, 51, 204));
		oldPasswordField.setEchoChar('*');

		jLabel4.setText("新密码");

		userPasswordField.setForeground(new java.awt.Color(255, 51, 204));
		userPasswordField.setEchoChar('*');

		jLabel5.setText("密码确认");

		userPasswordField2.setForeground(new java.awt.Color(255, 51, 204));
		userPasswordField2.setEchoChar('*');

		passwordEnableCheckBox.setText("启动软件时需输入密码");
		passwordEnableCheckBox.setOpaque(false);

		javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(
				userPanel);
		userPanel.setLayout(userPanelLayout);
		userPanelLayout
				.setHorizontalGroup(userPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								userPanelLayout
										.createSequentialGroup()
										.addGap(40, 40, 40)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel5)
														.addComponent(jLabel4)
														.addComponent(jLabel3)
														.addComponent(jLabel2)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																passwordEnableCheckBox)
														.addComponent(
																workPlaceTextField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																157,
																Short.MAX_VALUE)
														.addComponent(
																oldPasswordField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																157,
																Short.MAX_VALUE)
														.addComponent(
																userPasswordField2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																157,
																Short.MAX_VALUE)
														.addComponent(
																userPasswordField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																157,
																Short.MAX_VALUE)
														.addComponent(
																userNameTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																157,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(62, 62, 62)));
		userPanelLayout
				.setVerticalGroup(userPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								userPanelLayout
										.createSequentialGroup()
										.addGap(34, 34, 34)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																userNameTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																workPlaceTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																oldPasswordField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																userPasswordField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												userPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																userPasswordField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(passwordEnableCheckBox)
										.addContainerGap(50, Short.MAX_VALUE)));

		mainTabbedPane1.addTab("用户", userPanel);

		stylePanel.setOpaque(false);

		javax.swing.GroupLayout stylePanelLayout = new javax.swing.GroupLayout(
				stylePanel);
		stylePanel.setLayout(stylePanelLayout);
		stylePanelLayout.setHorizontalGroup(stylePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 293, Short.MAX_VALUE));
		stylePanelLayout.setVerticalGroup(stylePanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 242,
				Short.MAX_VALUE));

		mainTabbedPane1.addTab("软件风格", stylePanel);

		EmailPanel.setOpaque(false);

		defaultEmailServerCheckBox.setText("使用默认邮件服务器");
		defaultEmailServerCheckBox.setOpaque(false);
		defaultEmailServerCheckBox
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						defaultEmailServerCheckBoxActionPerformed(evt);
					}
				});

		defaultEmailCheckBox.setText("使用“泽苑通讯录”默认邮箱");
		defaultEmailCheckBox.setOpaque(false);
		defaultEmailCheckBox
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						defaultEmailCheckBoxActionPerformed(evt);
					}
				});

		jLabel6.setText("邮    箱");

		EmailTextField.setOpaque(false);

		jLabel7.setText("密    码");

		EmailPasswordField.setOpaque(false);

		jLabel8.setText("密码确认");

		EmailPasswordField2.setOpaque(false);

		jLabel9.setText("邮件服务器");

		emailSMTPHostTextField.setEditable(false);
		emailSMTPHostTextField.setOpaque(false);

		javax.swing.GroupLayout EmailPanelLayout = new javax.swing.GroupLayout(
				EmailPanel);
		EmailPanel.setLayout(EmailPanelLayout);
		EmailPanelLayout
				.setHorizontalGroup(EmailPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								EmailPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												EmailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																EmailPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				defaultEmailServerCheckBox)
																		.addContainerGap())
														.addGroup(
																EmailPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				defaultEmailCheckBox)
																		.addContainerGap(
																				102,
																				Short.MAX_VALUE))
														.addGroup(
																EmailPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				EmailTextField,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				214,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				17,
																				Short.MAX_VALUE))
														.addGroup(
																EmailPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				EmailPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								EmailPanelLayout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel8)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												EmailPasswordField2,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												214,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								EmailPanelLayout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel7)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												EmailPasswordField,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												214,
																												Short.MAX_VALUE)))
																		.addGap(17,
																				17,
																				17))
														.addGroup(
																EmailPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel9)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				emailSMTPHostTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				202,
																				Short.MAX_VALUE)
																		.addGap(17,
																				17,
																				17)))));
		EmailPanelLayout
				.setVerticalGroup(EmailPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								EmailPanelLayout
										.createSequentialGroup()
										.addGap(21, 21, 21)
										.addComponent(
												defaultEmailServerCheckBox)
										.addGap(10, 10, 10)
										.addComponent(defaultEmailCheckBox)
										.addGap(16, 16, 16)
										.addGroup(
												EmailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																EmailTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												EmailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel9)
														.addComponent(
																emailSMTPHostTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(13, 13, 13)
										.addGroup(
												EmailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																EmailPasswordField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												EmailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																EmailPasswordField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(16, Short.MAX_VALUE)));

		mainTabbedPane1.addTab("邮箱设置", EmailPanel);

		sureButton.setText("确定");
		sureButton.setOpaque(false);
		sureButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sureButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("取消");
		cancelButton.setOpaque(false);
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(mainTabbedPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 298,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(60, Short.MAX_VALUE)
								.addComponent(sureButton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										73,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cancelButton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										77,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(70, 70, 70)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(mainTabbedPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										271,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(cancelButton)
												.addComponent(sureButton))));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		try {
			UIManager.setLookAndFeel(classNameOldString);
			SwingUtilities.updateComponentTreeUI(this.getParent());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ex) {
		}
		dispose();
	}// GEN-LAST:event_cancelButtonActionPerformed

	private void defaultEmailServerCheckBoxActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_defaultEmailServerCheckBoxActionPerformed
		if (defaultEmailServerCheckBox.isSelected()) {
			defaultEmailCheckBox.setEnabled(false);
			EmailTextField.setEnabled(false);
			EmailPasswordField.setEnabled(false);
			EmailPasswordField2.setEnabled(false);
		} else {
			defaultEmailCheckBox.setEnabled(true);
			if (defaultEmailCheckBox.isSelected()) {
				EmailTextField.setEnabled(false);
				EmailPasswordField.setEnabled(false);
				EmailPasswordField2.setEnabled(false);
			} else {
				EmailTextField.setEnabled(true);
				EmailPasswordField.setEnabled(true);
				EmailPasswordField2.setEnabled(true);
			}
		}
	}// GEN-LAST:event_defaultEmailServerCheckBoxActionPerformed

	private void defaultEmailCheckBoxActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_defaultEmailCheckBoxActionPerformed
		if (defaultEmailCheckBox.isSelected()) {
			EmailTextField.setEnabled(false);
			EmailPasswordField.setEnabled(false);
			EmailPasswordField2.setEnabled(false);
		} else {
			EmailTextField.setEnabled(true);
			EmailPasswordField.setEnabled(true);
			EmailPasswordField2.setEnabled(true);
		}
	}// GEN-LAST:event_defaultEmailCheckBoxActionPerformed

	@SuppressWarnings({ "deprecation", "deprecation" })
	private void sureButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_sureButtonActionPerformed

		String message = "<html>";
		boolean access = true;
		String newpwd1 = userPasswordField.getText().trim();// 获取新密码
		passwordNewEnable = passwordEnableCheckBox.isSelected();// 获取“是否开启密码”
		if (!newpwd1.equals("")) {// 存在新密码
			String newpwd2 = userPasswordField2.getText().trim();// 新密码确认
			String oldpwd = oldPasswordField.getText().trim();// 旧密码
			if (oldpwd.equals("")) {// 没有输入旧密码
				message += "修改用户密码必须输入旧密码！请输入用户的旧密码。<br>";
				access = false;
			} else if (!newpwd1.equals(newpwd2)) {// 两次输入的密码不匹配
				message += "两次输入的新用户密码不匹配！<br>";
				access = false;
			} else if (!passwordOldString.equals(new passwordHandle()
					.numberparse(new StringBuffer(oldpwd)).toString())) {
				message += "用户密码输入错误！<br>";
				access = false;
			} else {
				sfd.setPassword(new passwordHandle().numberparse(
						new StringBuffer(newpwd1)).toString());
			}
		}

		if (!EmailPasswordField.getText().trim()
				.equals(EmailPasswordField2.getText().trim())) {
			message += "邮箱密码不匹配！<br>";
			access = false;
		}

		if (passwordOldEnable != passwordNewEnable) {
			String oldpwd = oldPasswordField.getText().trim();
			if (oldpwd.equals("")) {
				message += "启动密码必须输入旧用户密码！<br>";
				access = false;
			} else if (!passwordOldString.equals(new passwordHandle()
					.numberparse(new StringBuffer(oldpwd)).toString())) {
				message += "输入的用户错误密码！<br>";
				access = false;
			} else {
				sfd.setPasswordEnable(passwordNewEnable);
			}
		}
		if (!access) {
			message += "</html>";
			JOptionPane.showMessageDialog(null, message);
			return;
		}

		userNameNewString = userNameTextField.getText().trim();
		if (!userNameNewString.equals(userNameOldString)) {
			sfd.setUserNameString(userNameNewString);
		}
		userWorkSpaceNewString = workPlaceTextField.getText().trim();
		if (!userWorkSpaceNewString.equals(userWorkSpaceOldString)) {
			sfd.setUserWorkplace(userWorkSpaceNewString);
		}
		// classNameNewString = "";
		defaultMailServerNewEnable = defaultEmailServerCheckBox.isSelected();
		if (defaultMailServerNewEnable != defaultMailServerOldEnable) {
			sfd.setDefaultMailServerEnable(defaultMailServerNewEnable);
		}
		if (defaultMailServerNewEnable == false) {
			defaultMailNewEnable = defaultEmailCheckBox.isSelected();
			if (defaultMailNewEnable != defaultMailOldEnable) {
				sfd.setDefaultMailEnable(defaultMailNewEnable);
			}
			if (defaultMailNewEnable == false) {
				emailAddressNewString = EmailTextField.getText().trim();
				if (!emailAddressNewString.equals("")) { // 输入了新的邮件地址
					if (!emailAddressOldString.equals(emailAddressNewString)) {
						sfd.setEmailAddressString(emailAddressNewString);
						emailSMtpHostString = emailSMTPHostTextField.getText();
						sfd.setEmailSMTPHostString(emailSMtpHostString);
					}
					emailPasswordNewString = EmailPasswordField.getText()
							.trim();
					if (!emailPasswordNewString.equals("")) {// 密码存在
						if (!emailPasswordNewString
								.equals(emailPasswordOldString)) {
							sfd.setEmailPasswordString(emailPasswordNewString);
						}
					} else {
						// 密码为空
						JOptionPane.showMessageDialog(null, "邮箱密码不能为空！");
						return;
					}
				} else {
					// 没有邮件地址
					JOptionPane.showMessageDialog(null, "请输入邮箱！");
					return;
				}
			}
		}
		if (!classNameNewString.equals(classNameOldString)) {
			sfd.setUILookAndFeelNameString(classNameNewString);
		}
		dispose();
	}// GEN-LAST:event_sureButtonActionPerformed
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JPanel EmailPanel;
	private javax.swing.JPasswordField EmailPasswordField;
	private javax.swing.JPasswordField EmailPasswordField2;
	private javax.swing.JTextField EmailTextField;
	private javax.swing.JButton cancelButton;
	private javax.swing.JCheckBox defaultEmailCheckBox;
	private javax.swing.JCheckBox defaultEmailServerCheckBox;
	private javax.swing.JTextField emailSMTPHostTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JTabbedPane mainTabbedPane1;
	private javax.swing.JPasswordField oldPasswordField;
	private javax.swing.JCheckBox passwordEnableCheckBox;
	private javax.swing.JPanel stylePanel;
	private javax.swing.JButton sureButton;
	private javax.swing.JTextField userNameTextField;
	private javax.swing.JPanel userPanel;
	private javax.swing.JPasswordField userPasswordField;
	private javax.swing.JPasswordField userPasswordField2;
	private javax.swing.JTextField workPlaceTextField;
	// End of variables declaration//GEN-END:variables
	private LookAndFeelInfo[] installedLookAndFeels = null;

	private void loadData() {
		sfd = new ConfigManager();
		userNameOldString = sfd.getUserName();
		userWorkSpaceOldString = sfd.getUserWorkPlace();
		passwordOldEnable = sfd.getPasswordEnable();
		passwordOldString = sfd.getpassword();
		classNameNewString = classNameOldString = sfd.getLookAndFeelClassName();
		defaultMailServerOldEnable = sfd.getDefaultMailServerEnable();
		defaultMailOldEnable = sfd.getDefaultMailEnable();
		emailAddressOldString = sfd.getEmailAddressString();
		emailPasswordOldString = sfd.getEmailPasswordString();
		emailSMtpHostString = sfd.getEmailSMTPHostString();
		// //////////////////////////////
		userNameTextField.setText(userNameOldString);
		workPlaceTextField.setText(userWorkSpaceOldString);
		passwordEnableCheckBox.setSelected(passwordOldEnable);
		stylePanel.setLayout(new BoxLayout(stylePanel, BoxLayout.Y_AXIS));
		installedLookAndFeels = UIManager.getInstalledLookAndFeels();
		ButtonGroup bg = new ButtonGroup();
		String oldName = UIManager.getLookAndFeel().getName();
		for (int i = 0; i < installedLookAndFeels.length; i++) {
			LookAndFeelInfo lookAndFeelInfo = installedLookAndFeels[i];
			String name = lookAndFeelInfo.getName();
			JRadioButton jRadioButton = new JRadioButton(name);
			jRadioButton.setOpaque(false);
			bg.add(jRadioButton);
			stylePanel.add(jRadioButton);
			if (name.equals(oldName)) {
				jRadioButton.setSelected(true);
			}
			jRadioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();
					LookAndFeelRadioButtonActionPerformed(s);
				}
			});
		}
		defaultEmailServerCheckBox.setSelected(defaultMailServerOldEnable);
		if (defaultMailServerOldEnable) {
			defaultEmailCheckBox.setEnabled(false);
			EmailTextField.setEnabled(false);
			EmailPasswordField.setEnabled(false);
			EmailPasswordField2.setEnabled(false);
		} else {
			defaultEmailCheckBox.setEnabled(true);
			defaultEmailCheckBox.setSelected(defaultMailOldEnable);
			if (defaultMailOldEnable) {
				EmailTextField.setEnabled(false);
				EmailPasswordField.setEnabled(false);
				EmailPasswordField2.setEnabled(false);
			} else {
				EmailTextField.setEnabled(true);
				EmailTextField.setText(emailAddressOldString);
				emailSMTPHostTextField.setText(emailSMtpHostString);
				EmailPasswordField.setEnabled(true);
				EmailPasswordField2.setEnabled(true);
			}
		}
		EmailTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void insertUpdate(DocumentEvent e) {
						EmailTextFieldDocumentAction();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						EmailTextFieldDocumentAction();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						EmailTextFieldDocumentAction();
					}

					private void EmailTextFieldDocumentAction() {
						String text = EmailTextField.getText();
						int indexOf = text.indexOf('@');
						if (indexOf >= 0) {
							String substring = text.substring(indexOf);
							String emailHost = getEmailHost(substring);
							if (emailHost.equals("")) {
								emailSMTPHostTextField.setText("请输入邮件服务器！");
								emailSMTPHostTextField.setEditable(true);
								sureButton.setEnabled(true);
							} else {
								emailSMTPHostTextField.setText(emailHost);
								emailSMTPHostTextField.setEditable(false);
								sureButton.setEnabled(true);
							}
						}
						if (text.equals("")) {
							sureButton.setEnabled(true);
						}
					}
				});
	}

	private void LookAndFeelRadioButtonActionPerformed(String s) {
		System.out.println(s);
		LookAndFeelInfo lookAndFeelInfo = null;
		int i = 0;
		while (!installedLookAndFeels[i++].getName().equals(s)) {
		}
		lookAndFeelInfo = installedLookAndFeels[i - 1];
		System.out.println(lookAndFeelInfo.getClassName());
		try {
			UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
			classNameNewString = lookAndFeelInfo.getClassName();
			SwingUtilities.updateComponentTreeUI(this.getParent());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ex) {
		}
	}

	public void setMainTabbedPane1SelectedIndex(int index) {
		mainTabbedPane1.setSelectedIndex(index);
	}

	private String getEmailHost(String emailAddress) {
		String emailSmtpHost = "";
		int atIndex = emailAddress.indexOf('@');
		String emailServer = emailAddress.substring(atIndex + 1);
		if (emailServer.equals("163.com")) {
			emailSmtpHost = "smtp.163.com";
		} else if (emailServer.equals("126.com")) {
			emailSmtpHost = "smtp.126.com";
		} else if (emailServer.equals("netease.com")) {
			emailSmtpHost = "smtp.netease.com";
		} else if (emailServer.equals("yeah.net")) {
			emailSmtpHost = "smtp.yeah.net";
		} else if (emailServer.equals("gmail.com")) {
			emailSmtpHost = "smtp.gmail.com";
		} else if (emailServer.equals("elong.com")) {
			emailSmtpHost = "smtp.elong.com";
		} else if (emailServer.equals("china.com")) {
			emailSmtpHost = "smtp.china.com";
		} else if (emailServer.equals("sohu.com")) {
			emailSmtpHost = "smtp.sohu.com";
		} else if (emailServer.equals("etang.com")) {
			emailSmtpHost = "smtp.etang.com";
		} else if (emailServer.equals("yahoo.com")) {
			emailSmtpHost = "smtp.mail.yahoo.com";
		} else if (emailServer.equals("yahoo.com.cn")) {
			emailSmtpHost = "smtp.mail.yahoo.com.cn";
		} else if (emailServer.equals("21cn.com")) {
			emailSmtpHost = "smtp.21cn.com sina.com";
		} else if (emailServer.equals("sina.com")) {
			emailSmtpHost = "smtp.sina.com.cn";
		} else if (emailServer.equals("tom.com")) {
			emailSmtpHost = "smtp.tom.com";
		} else if (emailServer.equals("263.net")) {
			emailSmtpHost = "smtp.263.net";
		} else if (emailServer.equals("x263.net")) {
			emailSmtpHost = "smtp.x263.net";
		} else if (emailServer.equals("263.net.cn")) {
			emailSmtpHost = "smtp.263.net.cn";
		} else {
			emailSmtpHost = "";
		}
		return emailSmtpHost;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	private void loadAction() {

	}
}
