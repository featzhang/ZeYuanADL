package components;

import apps.AppsPanel;
import Clock.Clock;
import Diary.DiaryPanel;
import FileSearch.FileSearchPanel;
import event.AwakeAction;
import event.IconAction;
import event.ListAction;
import event.LockAction;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ui.ATextField;
import ui.ImagePanel;
import util.AResource;
import util.ConfigManager;

/**
 * ADL的主窗口
 * 
 * @author 泽苑软件
 */
public class MainFrame extends ui.AFrame {

	private JMenuBar menuBar = null;
	private JToolBar toolBar = null;
	private JTabbedPane tabbedPane = null;
	private boolean needToolbar = true;
	private Action registActon = null;
	private Action modifyAction = null;
	public static String Regist_String = "新建";
	public static String modify_String = "修改";
	public static String Delete_String = "删除";
	public static String GroupManage_String = "分组管理";
	public static String Exit_String = "退出";
	public static String Options_String = "选项";
	public static String Print_String = "打印";
	public static String ExportSameTable_String = "导出到同一张表";
	public static String ExportDifferentTable_String = "导出到不同表";
	public static String DatabaseInit_String = "数据库清空";
	public static String CreatEmptyExcel_String = "创建空表";
	public static String Save_String = "保存当前表";
	public static String ImportExcel_String = "从Excel导入";
	public static String About_String = "关于";
	public static String Detail_String = "详细信息";
	public static String ShowAll_String = "显示全部";
	public static String Refresh_String = "刷新";
	public static String Filter_String = "筛选";
	public static String Statistics_String = "统计";
	public static String Mini_String = "最小化到托盘";
	public static String QQChart_String = "QQ聊天";
	public static String Email_String = "发送邮件";
	public static String Awake_String = "生日提醒";
	public static String List_String = "列表";
	public static String Icon_String = "图标";
	public static String Lock_String = "锁定";
	private Action deleteAction = null;
	private Action groupManageAction = null;
	private Action exitAction = null;
	private Action optionsAction = null;
	private Action exportSameTableAction = null;
	private Action exportDifferentTableAction = null;
	private Action databaseInitAction = null;
	private Action creatEmptyExcelAction = null;
	private Action saveAction = null;
	private Action importExcelAction = null;
	private Action aboutAction = null;
	private Action detailAction = null;
	private Action showAllAction = null;
	private Action refreshAction = null;
	private Action filterAction = null;
	private Action statisticsAction = null;
	private Action miniAction = null;
	private Action qqAction = null;
	private Action emailAction = null;
	private Action listAction = null;
	private Action iconAction = null;
	private Action lockAction = null;
	private AwakeAction awakeAction = null;
	public JComboBox searchComboBox = null;
	public JTextField searchTextField = null;
	public AdlTable adlMainTable = null;
	private JPanel adlPanel = null;
	public ADLIconScrollPane aDLIconScrollPane = null;
	private JRadioButtonMenuItem listButtonMenuItem = null;
	private JRadioButtonMenuItem iconRadioButtonMenuItem = null;
	private boolean view_list = false;
	private boolean view_icon = false;
	private ConfigManager configManager = new ConfigManager();
	private boolean locked = false;

	public MainFrame() {

		try {
			enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		} catch (Exception e) {
		}
	}

	public void loadSetting() {
		ToolTipManager tipm = ToolTipManager.sharedInstance();
		tipm.setInitialDelay(0);// 初始延迟值设置为0
		tipm.setReshowDelay(0); // 及时显示提示信息
		setTitle(AResource.getAppTitle());
		setIconImage(AResource.getAppImage());
	}

	public void loadFinalHandle() {
		setSize(940, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchTextField.setFocusable(true);
		searchTextField.requestFocus();
		tabbedPane.setSelectedIndex(new ConfigManager()
				.getMainTabbedPaneSelectedIndex());
	}

	public void initComponent() {
		adlPanel = new ImagePanel(AResource.getWallpaper());
		adlPanel.setOpaque(false);
		setJMenuBar(createMenuBar());
		if (ConfigManager.LIST_VIEW.equals(configManager.getViewName())) {
			setListView();
		} else if (ConfigManager.ICON_VIEW.equals(configManager.getViewName())) {
			setIconView();
		}
		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT,
				JTabbedPane.WRAP_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		tabbedPane.setOpaque(false);
		tabbedPane.addTab("", AResource.getImageIcon("Application"), adlPanel,
				"通讯录");// 添加主选项卡
		tabbedPane.addTab("", AResource.getImageIcon("notePad"),
				new DiaryPanel(AResource.getWallpaper()), "日记本");
		tabbedPane.addTab("", AResource.getImageIcon("file"),
				new FileSearchPanel(AResource.getWallpaper()), "文件搜索");
		tabbedPane.addTab("", AResource.getImageIcon("APPs"), new AppsPanel(
				AResource.getWallpaper()), "应用平台");
		this.setContentPane(tabbedPane);
		tabbedPane.setDropTarget(this.getDropTarget());
	}

	private JMenuBar createMenuBar() {
		menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("系统[S]");
		menu1.setMnemonic(KeyEvent.VK_S);
		menu1.add(exitAction);
		menuBar.add(menu1);
		JMenu listMenu = new JMenu("记录[L]");
		listMenu.setMnemonic(KeyEvent.VK_L);
		listMenu.add(registActon);
		listMenu.add(modifyAction);
		listMenu.add(deleteAction);
		listMenu.add(detailAction);
		listMenu.addSeparator();
		listMenu.add(groupManageAction);
		menuBar.add(listMenu);
		JMenu viewMenu = new JMenu("视图[V]");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		listButtonMenuItem = new JRadioButtonMenuItem();
		listButtonMenuItem.setAction(listAction);
		viewMenu.add(listButtonMenuItem);
		iconRadioButtonMenuItem = new JRadioButtonMenuItem();
		iconRadioButtonMenuItem.setAction(iconAction);
		iconRadioButtonMenuItem.setEnabled(false);
		viewMenu.add(iconRadioButtonMenuItem);
		menuBar.add(viewMenu);
		ButtonGroup bg = new ButtonGroup();
		bg.add(listButtonMenuItem);
		bg.add(iconRadioButtonMenuItem);
		JMenu databaseMenu = new JMenu("数据库[D]");
		databaseMenu.setMnemonic(KeyEvent.VK_D);
		JMenu importMenu = new JMenu("导入");
		importMenu.add(importExcelAction);
		databaseMenu.add(importMenu);
		JMenu exportMenu = new JMenu("导出");
		exportMenu.add(exportSameTableAction);
		exportMenu.add(exportDifferentTableAction);
		databaseMenu.add(exportMenu);
		databaseMenu.add(databaseInitAction);
		databaseMenu.add(creatEmptyExcelAction);
		databaseMenu.addSeparator();
		databaseMenu.add(saveAction);
		menuBar.add(databaseMenu);
		JMenu toolMenu = new JMenu("工具[T]");
		toolMenu.setMnemonic(KeyEvent.VK_T);
		toolMenu.add(optionsAction);
		JMenu printMenu = new JMenu("打印");
		toolMenu.add(printMenu);
		menuBar.add(toolMenu);
		toolMenu.addSeparator();
		toolMenu.add(lockAction);
		JMenu helpMenu = new JMenu("帮助[H]");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);
		return menuBar;
	}

	private JToolBar createToolBar() {
		toolBar = new JToolBar() {

			Insets insets = new Insets(0, 0, 0, 0);

			@Override
			protected JButton createActionComponent(Action a) {
				JButton b = super.createActionComponent(a);
				b.setMargin(insets);
				return b;
			}
		};
		FlowLayout flayout = new FlowLayout(FlowLayout.CENTER, 0, 0); // 布局方式
		toolBar.setLayout(flayout);
		toolBar.setFloatable(false);
		toolBar.putClientProperty("JToolBar.isRollover", Boolean.FALSE);
		toolBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
		toolBar.add(awakeAction);
		toolBar.add(new Clock(this));
		this.addToolBarButton(detailAction);
		this.addToolBarButton(registActon);
		this.addToolBarButton(modifyAction);
		this.addToolBarButton(deleteAction);
		toolBar.addSeparator();
		this.addToolBarButton(showAllAction);
		this.addToolBarButton(refreshAction);
		toolBar.addSeparator();
		this.addToolBarButton(filterAction);
		this.addToolBarButton(statisticsAction);
		toolBar.addSeparator();
		addSearch();
		toolBar.add(miniAction);
		toolBar.setOpaque(false);
		return toolBar;
	}

	public void initActions() {
		exitAction = new event.ExitAction(this);
		registActon = new event.RegistAction(this);
		modifyAction = new event.ModifyAction(this);
		deleteAction = new event.DeleteAction(this);
		detailAction = new event.DetailAction(this);
		groupManageAction = new event.GroupManageAction(this);
		optionsAction = new event.OptionsAction(this);
		exportSameTableAction = new event.ExportSameTableAction(this);
		exportDifferentTableAction = new event.ExportDifferentTableAction(this);
		databaseInitAction = new event.DatabaseInitAction(this);
		creatEmptyExcelAction = new event.CreatEmptyExcelAction(this);
		saveAction = new event.SaveAction(this);
		importExcelAction = new event.ImportExcelAction(this);
		aboutAction = new event.AboutAction(this);
		showAllAction = new event.ShowAllAction(this);
		refreshAction = new event.RefreshAction(this);
		filterAction = new event.FilterAction(this);
		statisticsAction = new event.StatisticsAction(this);
		miniAction = new event.MiniAction(this);
		qqAction = new event.QQAction(this);
		emailAction = new event.EmailAction(this);
		awakeAction = new AwakeAction(this);
		listAction = new ListAction(this);
		iconAction = new IconAction(this);
		lockAction = new LockAction(this);
		setTableSelectActionEnable(false);
	}

	private void addToolBarButton(Action registActon) {
		toolBar.add(Box.createRigidArea(new Dimension(6, 6)));
		toolBar.add(registActon).setFocusable(false);
	}

	private void addSearch() {
		String[] titleNameArray = dataBase.DataBase.searchNameArray;
		searchComboBox = new JComboBox(titleNameArray);
		searchTextField = new ATextField();
		Font searchFont = new Font("", Font.PLAIN, 10);
		searchTextField.setColumns(18);
		searchTextField.setForeground(Color.red);
		searchTextField.setFont(searchFont);
		searchComboBox.setMaximumRowCount(10);
		searchTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent e) {// 这是更改操作的处理
						dosearch();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {// 这是插入操作的处理
						dosearch();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {// 这是删除操作的处理
						dosearch();
					}

					private void dosearch() {
						String s = searchTextField.getText().trim();
						int i = searchComboBox.getSelectedIndex();
						doSearch(i, s);
					}
				});
		searchTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = searchTextField.getText().trim();
					int i = searchComboBox.getSelectedIndex();
					int listShow = doSearch(i, s);
					if (listShow > 0) {
						adlMainTable.setRowSelectionInterval(0, 0);
						adlMainTable.requestFocus();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		toolBar.add(searchComboBox);
		toolBar.add(searchTextField);
	}

	private int doSearch(int i, String s0) {

		String sQLOrder = "select * from ADDRESSLISTTABLE  ";
		if (!s0.equals("")) {
			switch (i) {
			case 0:
				sQLOrder += "WHERE NO1='" + s0 + "' OR NO1 LIKE '%" + s0
						+ "' OR NO1 LIKE  '" + s0 + "%' OR NO1 LIKE '%" + s0
						+ "%'" + " OR NO2='" + s0 + "' OR NO2 LIKE '%" + s0
						+ "' OR NO2 LIKE  '" + s0 + "%' OR NO2 LIKE '%" + s0
						+ "%'" + " OR NO3='" + s0 + "' OR NO3 LIKE '%" + s0
						+ "' OR NO3 LIKE  '" + s0 + "%' OR NO3 LIKE '%" + s0
						+ "%'" + " OR NO1='" + s0 + "' OR NO1 LIKE '%" + s0
						+ "' OR NO1 LIKE  '" + s0 + "%' OR NO1 LIKE '%" + s0
						+ "%'" + " OR NO2='" + s0 + "' OR NO2 LIKE '%" + s0
						+ "' OR NO2 LIKE  '" + s0 + "%' OR NO2 LIKE '%" + s0
						+ "%'" + " OR NO3='" + s0 + "' OR NO3 LIKE '%" + s0
						+ "' OR NO3 LIKE  '" + s0 + "%' OR NO3 LIKE '%" + s0
						+ "%'" + " OR NAME='" + s0 + "' OR NAME LIKE '%" + s0
						+ "' OR NAME LIKE '" + s0 + "%' OR NAME LIKE '%" + s0
						+ "%'" + " OR CLASS='" + s0 + "'" + " OR ADDRESS='"
						+ s0 + "' OR ADDRESS LIKE '%" + s0
						+ "' OR ADDRESS LIKE  '" + s0 + "%' OR ADDRESS LIKE '%"
						+ s0 + "%'" + " OR HOMETOWN='" + s0
						+ "' OR HOMETOWN LIKE '%" + s0
						+ "' OR HOMETOWN LIKE  '" + s0
						+ "%' OR HOMETOWN LIKE '%" + s0 + "%'" + " OR QQ='"
						+ s0 + "' OR QQ LIKE '%" + s0 + "' OR QQ LIKE '" + s0
						+ "%' OR QQ LIKE '%" + s0 + "%'" + " OR EMAIL='" + s0
						+ "' OR EMAIL LIKE '%" + s0 + "' OR EMAIL LIKE '" + s0
						+ "%' OR EMAIL LIKE '%" + s0 + "%'" + " OR REMARKS='"
						+ s0 + "' OR REMARKS LIKE '%" + s0
						+ "' OR REMARKS LIKE '" + s0 + "%' OR REMARKS LIKE '%"
						+ s0 + "%'";
				break;
			case 1:
				sQLOrder += "WHERE NO1='" + s0 + "' OR NO1 LIKE '%" + s0
						+ "' OR NO1 LIKE  '" + s0 + "%' OR NO1 LIKE '%" + s0
						+ "%'" + " OR NO2='" + s0 + "' OR NO2 LIKE '%" + s0
						+ "' OR NO2 LIKE  '" + s0 + "%' OR NO2 LIKE '%" + s0
						+ "%'" + " OR NO3='" + s0 + "' OR NO3 LIKE '%" + s0
						+ "' OR NO3 LIKE  '" + s0 + "%' OR NO3 LIKE '%" + s0
						+ "%'";
				break;
			case 2:
				sQLOrder += "WHERE NAME='" + s0 + "' OR NAME LIKE '%" + s0
						+ "' OR NAME LIKE '" + s0 + "%' OR NAME LIKE '%" + s0
						+ "%'";
				break;
			case 3:
				sQLOrder += "WHERE CLASS='" + s0 + "' OR CLASS LIKE '%" + s0
						+ "' OR CLASS LIKE  '" + s0 + "%' OR CLASS LIKE '%"
						+ s0 + "%'";
				break;
			case 4:
				sQLOrder += "WHERE ADDRESS='" + s0 + "' OR ADDRESS LIKE '%"
						+ s0 + "' OR ADDRESS LIKE  '" + s0
						+ "%' OR ADDRESS LIKE '%" + s0 + "%'"
						+ " OR HOMETOWN='" + s0 + "' OR HOMETOWN LIKE '%" + s0
						+ "' OR HOMETOWN LIKE  '" + s0
						+ "%' OR HOMETOWN LIKE '%" + s0 + "%'";
				break;
			case 5:
				sQLOrder += "WHERE QQ='" + s0 + "' OR QQ LIKE '%" + s0
						+ "' OR QQ LIKE '" + s0 + "%' OR QQ LIKE '%" + s0
						+ "%'";
				break;
			case 6:
				sQLOrder += "WHERE EMAIL='" + s0 + "' OR EMAIL LIKE '%" + s0
						+ "' OR EMAIL LIKE '" + s0 + "%' OR EMAIL LIKE '%" + s0
						+ "%'";
				break;
			case 7:
				sQLOrder += "WHERE REMARKS='" + s0 + "' OR REMARKS LIKE '%"
						+ s0 + "' OR REMARKS LIKE '" + s0
						+ "%' OR REMARKS LIKE '%" + s0 + "%'";
				break;
			case 8:
				sQLOrder += "WHERE SEX='" + s0 + "'";
				break;
			}
			sQLOrder += " ORDER BY ID+10000";
		}
		return SQLExecu(sQLOrder);
	}

	public String getSearchText() {
		String text = searchTextField.getText();
		String string = "";
		if (searchComboBox.getSelectedIndex() == 0) {
			string = text;
		} else {
			string = searchComboBox.getSelectedIndex() + "====" + text;
		}
		return string;
	}

	public void setTableSelectActionEnable(boolean b) {
		detailAction.setEnabled(b);
		deleteAction.setEnabled(b);
		modifyAction.setEnabled(b);
		qqAction.setEnabled(b);
		emailAction.setEnabled(b);
	}

	public Action getDetailAction() {
		return detailAction;
	}

	public Action getDeleteAction() {
		return deleteAction;
	}

	public Action getModifyAction() {
		return modifyAction;
	}

	public Action getRefreshAction() {
		return refreshAction;
	}

	public Action getQQAction() {
		return qqAction;
	}

	public Action getEmailAction() {
		return emailAction;
	}

	public Point getToolBarLocation() {
		Point location = toolBar.getLocation();
		Point location1 = getLocation();
		location.setLocation(location.getX() + location1.getX(),
				location.getY() + location1.getY());
		return location;
	}

	public void setListView() {
		listButtonMenuItem.setSelected(true);
		view_list = true;
		view_icon = false;
		configManager.setView(ConfigManager.LIST_VIEW);
		refreshView();
	}

	public void setIconView() {
		iconRadioButtonMenuItem.setSelected(true);
		view_list = false;
		view_icon = true;
		configManager.setView(ConfigManager.ICON_VIEW);
		refreshView();
	}

	private void refreshView() {
		adlPanel.setLayout(new BorderLayout(0, 0));
		adlPanel.removeAll();
		if (listButtonMenuItem.isSelected()) {
			if (adlMainTable == null) {
				adlMainTable = new AdlTable(this);
				adlMainTable.initComponents();
				adlMainTable.refresh();
			}
			JScrollPane ADLScrollPane = new ADLMainScrollPane(adlMainTable);
			ADLScrollPane.setOpaque(false);
			ADLScrollPane.getViewport().setOpaque(false);
			adlPanel.add(ADLScrollPane, BorderLayout.CENTER);
		} else if (iconRadioButtonMenuItem.isSelected()) {
			if (aDLIconScrollPane == null) {
				aDLIconScrollPane = new ADLIconScrollPane(this);
			}
			adlPanel.add(aDLIconScrollPane, BorderLayout.CENTER);
		}
		if (needToolbar) {// 是否需要ToolBar
			adlPanel.add(createToolBar(), BorderLayout.SOUTH);
		}
	}

	public boolean isListView() {
		return view_list;
	}

	public boolean isIconView() {
		return view_icon;
	}

	public int SQLExecu(String sQLOrder) {
		int listShow = -1;
		if (isListView()) {
			listShow = adlMainTable.listShow(sQLOrder);
		} else {
			listShow = aDLIconScrollPane.listShow(sQLOrder);
		}
		return listShow;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isLocked() {
		return locked;
	}

	@Override
	public void setVisible(boolean b) {
		if (b && !locked || b == false) {
			super.setVisible(b);
		}
	}

	public int getMainTabbedPanelSelectedIndex() {
		return tabbedPane.getSelectedIndex();
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int mainTabbedPanelSelectedIndex = tabbedPane.getSelectedIndex();
			new ConfigManager()
					.setTabbedSelectIndex(mainTabbedPanelSelectedIndex);
			System.out.println("关闭窗口！");
			System.exit(0);
		} else {
			super.processWindowEvent(e);
		}
	}
}
