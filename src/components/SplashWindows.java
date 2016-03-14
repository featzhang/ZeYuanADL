package components;

import dataBase.DataBase;
import javax.swing.*;

import java.awt.*;
import util.AResource;
import util.ConfigManager;

public class SplashWindows extends JWindow implements Runnable {// 程序启动界面

	public static final long serialVersionUID = 879900908l;
	private Thread splashThread; // 进度条
	private JProgressBar progressBar;
	MainFrame mainFrame = null;

	public SplashWindows(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		Container container = getContentPane(); // 得到容器
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // 设置光标

		ImageIcon resourceImageIcon = AResource.getImageIcon("welcome"); // 图片的位置
		if (resourceImageIcon != null) {
			JLabel label = new JLabel(resourceImageIcon);
			label.setText("版本： " + AResource.getAppVersion());
			label.setHorizontalTextPosition(SwingConstants.CENTER);
			label.setBorder(BorderFactory.createEtchedBorder());
			container.add(label, BorderLayout.NORTH); // 增加图片
		}
		progressBar = new JProgressBar(1, 100); // 实例化进度条
		progressBar.setStringPainted(true); // 描绘文字
		progressBar.setString("程序加载,请稍候......"); // 设置显示文字
		progressBar.setBackground(Color.white); // 设置背景色
		progressBar.setForeground(Color.green);
		progressBar.setBorderPainted(true);
		container.add(progressBar, BorderLayout.CENTER); // 增加进度条到容器上
		pack(); // 窗口适应组件尺寸
		setLocationRelativeTo(null);
	}

	public void start() {
		this.toFront(); // 窗口前端显示
		splashThread = new Thread(this);
		splashThread.start(); // 开始运行线程
	}

	@Override
	public void run() {
		setVisible(true); // 显示窗口
		try {
			ConfigManager configManager = null;
			for (int i = 0; i < 100; i++) {
				Thread.sleep(2); // 线程休眠
				progressBar.setValue(progressBar.getValue() + 1); // 设置进度条值
				if (i == 5) {
					progressBar.setString("系统文件 完整性检查");
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
					progressBar.setString("载入设置 ... ...");
					mainFrame.loadSetting();

				} else if (i == 35) {
					progressBar.setString("分组设置检查 ... ...");
					System.out.print("数据库分组检查:");
					if (configManager.getDatabaseNeedInit()) {
						DataBase dataBase = new dataBase.DataBase();
						dataBase.groupNameInit();
						dataBase.noClassNameCheck();
						configManager.setDatabaseNeedInit(false);
					} else {
						System.out.println("不需要初始化！");
					}
					mainFrame.loadSetting();

				} else if (i == 50) {
					progressBar.setString("载入事件 ... ...");
					mainFrame.initActions();
				} else if (i == 65) {
					progressBar.setString("初始化 界面 ... ...");
					mainFrame.initComponent();
				} else if (i == 90) {
					progressBar.setString("最后处理 ... ...");
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
		dispose(); // 释放窗口
	}
}
