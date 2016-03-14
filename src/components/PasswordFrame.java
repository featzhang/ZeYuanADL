package components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import ui.AButton;
import ui.ImagePanel;
import util.AResource;
import util.ConfigManager;
import util.passwordHandle;

public class PasswordFrame extends JFrame {

	private JPasswordField passwordField;
	private JCheckBox memeryCheckBox;
	private JButton sureButton;
	private JButton cancelButton;
	private MainFrame addressList2;
	private static final long serialVersionUID = 6758767687987l;

	public PasswordFrame() {
		String resourceString = AResource.getAppTitle();
		setTitle(resourceString);
		setIconImage(AResource.getAppImageIcon().getImage());
		initCompont();
		loadAction();
	}

	public PasswordFrame(MainFrame addressList2) {
		this();
		this.addressList2 = addressList2;
	}

	private void initCompont() {
		setUndecorated(true);
		Container contentPane = getContentPane();
		ImagePanel imagePanel = new ImagePanel(AResource.getWelcomeImage());
		imagePanel.setLayout(null);
		imagePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel boxpanel = new JPanel();
		boxpanel.setOpaque(false);
		boxpanel.setBorder(BorderFactory.createRaisedBevelBorder());
		boxpanel.setSize(280, 100);
		boxpanel.setLocation(140, 200);
		boxpanel.setLayout(null);
		JLabel tiplabel = new JLabel("请输入密码");
		tiplabel.setSize(100, 20);
		tiplabel.setLocation(10, 2);
		boxpanel.add(tiplabel);
		passwordField = new JPasswordField(30);
		passwordField.setEchoChar('*');
		passwordField.setForeground(Color.MAGENTA);
		passwordField.setOpaque(false);
		passwordField.setSize(260, 20);
		passwordField.setLocation(10, 30);
		boxpanel.add(passwordField);
		memeryCheckBox = new JCheckBox("记住密码");
		memeryCheckBox.setOpaque(false);
		memeryCheckBox.setSize(100, 20);
		memeryCheckBox.setLocation(10, 55);
		boxpanel.add(memeryCheckBox);
		sureButton = new AButton("确定");
		sureButton.setSize(60, 20);
		sureButton.setLocation(150, 70);
		boxpanel.add(sureButton);
		cancelButton = new AButton("取消");
		cancelButton.setSize(60, 20);
		cancelButton.setLocation(210, 70);
		boxpanel.add(cancelButton);
		imagePanel.add(boxpanel);
		contentPane.add(imagePanel);
		setSize(500, 305);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void loadAction() {
		sureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = String.valueOf(passwordField.getPassword());
				if (text.isEmpty()) {
					JOptionPane
							.showMessageDialog(PasswordFrame.this, "密码不能为空！");
				} else {
					String sbf = new passwordHandle().numberparse(
							new StringBuffer(text)).toString();
					if (sbf.equals(new ConfigManager().getpassword())) {
						if (memeryCheckBox.isSelected()) {
							new ConfigManager().setPasswordEnable(false);
						}
						addressList2.setLocked(false);
						addressList2.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(PasswordFrame.this,
								"密码输入错误，请重新输入！");
						passwordField.setText("");
					}
				}
			}
		});
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		passwordField.addKeyListener(new KeyListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == Event.ENTER) {
					String text = passwordField.getText();
					if (text.equals("")) {
						return;
					}
					String sbf = new passwordHandle().numberparse(
							new StringBuffer(text)).toString();
					if (sbf.equals(new ConfigManager().getpassword())) {
						if (memeryCheckBox.isSelected()) {
							new ConfigManager().setPasswordEnable(false);
						}
						addressList2.setLocked(false);
						addressList2.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(PasswordFrame.this,
								"密码输入错误，请重新输入！");
						passwordField.setText("");
					}
				} else if (e.getKeyChar() == Event.ESCAPE) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}
}
