package components;

import dataBase.DataBase;
import javax.swing.*;

import java.awt.*;
import util.AResource;
import util.ConfigManager;

public class SplashWindows extends JWindow implements Runnable {// ������������

	public static final long serialVersionUID = 879900908l;
	private Thread splashThread; // ������
	private JProgressBar progressBar;
	MainFrame mainFrame = null;

	public SplashWindows(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		Container container = getContentPane(); // �õ�����
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // ���ù��

		ImageIcon resourceImageIcon = AResource.getImageIcon("welcome"); // ͼƬ��λ��
		if (resourceImageIcon != null) {
			JLabel label = new JLabel(resourceImageIcon);
			label.setText("�汾�� " + AResource.getAppVersion());
			label.setHorizontalTextPosition(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createEtchedBorder());
			container.add(label, BorderLayout.NORTH); // ����ͼƬ
		}
		progressBar = new JProgressBar(1, 100); // ʵ����������
		progressBar.setStringPainted(true); // �������
		progressBar.setString("�������,���Ժ�......"); // ������ʾ����
		progressBar.setBackground(Color.white); // ���ñ���ɫ
		progressBar.setForeground(Color.green);
		progressBar.setBorderPainted(true);
		container.add(progressBar, BorderLayout.CENTER); // ���ӽ�������������
		pack(); // ������Ӧ����ߴ�
		setLocationRelativeTo(null);
	}

	public void start() {
		this.toFront(); // ����ǰ����ʾ
		splashThread = new Thread(this);
		splashThread.start(); // ��ʼ�����߳�
	}

	@Override
	public void run() {
		setVisible(true); // ��ʾ����
		try {
			ConfigManager configManager = null;
			for (int i = 0; i < 100; i++) {
				Thread.sleep(2); // �߳�����
				progressBar.setValue(progressBar.getValue() + 1); // ���ý�����ֵ
				if (i == 5) {
					progressBar.setString("ϵͳ�ļ� �����Լ��");
					configManager = new ConfigManager();
					configManager.systemFileCheck();
					javax.swing.UIManager.setLookAndFeel(configManager
							.getLookAndFeelClassName());
					UIManager.put("AuditoryCues.playList",
							UIManager.get("AuditoryCues.allAuditoryCues"));
					UIManager.put("OptionPane.questionSound",
							"sounds/OptionPaneError.wav");
					UIManager.put("MenuItem.commandSound",
							"sounds/OptionPaneError.wav");
					System.setProperty("java.awt.im.style", "on-the-spot");
				} else if (i == 25) {
					progressBar.setString("�������� ... ...");
					mainFrame.loadSetting();

				} else if (i == 35) {
					progressBar.setString("�������ü�� ... ...");
					System.out.print("���ݿ������:");
					if (configManager.getDatabaseNeedInit()) {
						DataBase dataBase = new dataBase.DataBase();
						dataBase.groupNameInit();
						dataBase.noClassNameCheck();
						configManager.setDatabaseNeedInit(false);
					} else {
						System.out.println("����Ҫ��ʼ����");
					}
					mainFrame.loadSetting();

				} else if (i == 50) {
					progressBar.setString("�����¼� ... ...");
					mainFrame.initActions();
				} else if (i == 65) {
					progressBar.setString("��ʼ�� ���� ... ...");
					mainFrame.initComponent();
				} else if (i == 90) {
					progressBar.setString("����� ... ...");
					mainFrame.loadFinalHandle();
				} else if (i == 99) {
					configManager.reloadProperties();
					if (configManager.getPasswordEnable()) {
						mainFrame.setLocked(true);
						PasswordFrame passwordFrame = new PasswordFrame(
								mainFrame);
						passwordFrame.setVisible(true);
					} else {
						mainFrame.setVisible(true);
					}
				}
			}
		} catch (Exception ex) {
		}
		dispose(); // �ͷŴ���
	}
}
