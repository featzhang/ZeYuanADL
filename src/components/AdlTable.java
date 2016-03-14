package components;

import dataBase.AddressListData;
import dataBase.DataBase;
import dataBase.DataBaseConnection;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import util.stateClass;

public class AdlTable extends JTable {

    private Vector<Vector<String>> adlMainVector;
    private String[] columnName = {"姓名", "分组", "号码1", "号码1类型", "地址",
        "家乡地址", "QQ/MSN", "EMail", "备注", "序号"};
    public AbstractTableModel adlmainTableModel = null;
    private String currentClassName = "ALL";
    private MainFrame parentFrame = null;
    private String currentSQLOrder = null;
    private stateClass state = new stateClass();

    public void initComponents() {
        adlMainVector = new Vector<Vector<String>>();
        adlmainTableModel = new AbstractTableModel() { // 实现表格抽象类的接口

            private static final long serialVersionUID = 500000L;

            @Override
            public Class<? extends Object> getColumnClass(int c) // 获取列标所对的类
            {
                return getValueAt(0, c).getClass();
            }

            @Override
            public int getColumnCount() { // 获取列数
                return columnName.length;
            }

            @Override
            public String getColumnName(int column) // 获取列标
            {
                return columnName[column];
            }

            @Override
            public int getRowCount() { // 获取行数
                return adlMainVector.size();
            }

            @Override
            public Object getValueAt(int row, int column) { // 获取row行column列的值
                if (!adlMainVector.isEmpty()) {
                    return ((Vector<?>) adlMainVector.elementAt(row)).elementAt(column);
                } else {
                    return null;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public void setValueAt(Object value, int row, int column) // 设定row行column列的值
            {
            }
        };
        setModel(adlmainTableModel);
        this.setToolTipText("<html>单击选择单元格<br>双击显示详细信息</html>");
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setFillsViewportHeight(true);
        this.setGridColor(java.awt.Color.gray);
        this.setForeground(java.awt.Color.BLUE);
        this.setBackground(new Color(255, 244, 173));
        this.setSelectionBackground(new Color(71, 190, 255));
        this.setSelectionForeground(new Color(255, 100, 3));
        this.setRowHeight(20);
        this.getTableHeader().setBackground(new Color(255, 244, 173));
        this.getTableHeader().setForeground(Color.gray);
        this.getTableHeader().setReorderingAllowed(false);
        this.setOpaque(false);
        this.getTableHeader().addMouseListener(new TableHeaderGesture());
        this.getTableHeader().setToolTipText("单击表头进行排序");
        this.addMouseListener(new MouseAdapter() {

            private void checkForTriggerEvent(MouseEvent e) {
                JPopupMenu popupMenu = creatPopupMenu();
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());// 显示弹出式菜单
                }
                popupMenu.requestFocus(); // 编辑区获取焦点
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    boolean isAnySelected = false;
                    for (int i = 0; i < 15; i++) {
                        if (isColumnSelected(i)) {
                            isAnySelected = true;
                        }
                    }
                    if (isAnySelected) {
                        mainListDetailAction();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                checkForTriggerEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                checkForTriggerEvent(e);
            }
        });
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int firstIndex = e.getFirstIndex();
                if (firstIndex == -1) {
                    parentFrame.setTableSelectActionEnable(false);
                } else {
                    parentFrame.setTableSelectActionEnable(true);
                    if (AdlTable.this == null) {
                        return;
                    }
                    if (AdlTable.this.getSelectedRow() <= 0) {
                        return;
                    }
                    String toString = getValueAt(getSelectedRow(), 6).toString();
                    String toString2 = getValueAt(getSelectedRow(), 7).toString();
                    if (toString.trim().equals("")) {
                        parentFrame.getQQAction().setEnabled(false);
                    }
                    if (toString2.trim().equals("")) {
                        parentFrame.getEmailAction().setEnabled(false);
                    }
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    System.out.println("回车");
                    mainListDetailAction();
                }
            }
        });
    }

    public AdlTable(MainFrame frame) {
        this.parentFrame = frame;
    }

    public String getCLASSName() {
        return currentClassName;
    }

    public String getCurrentSQLOrder() {
        return currentSQLOrder;
    }

    public void refresh() {
        if (currentClassName.equals("ALL")) {
            listShow("Select * From ADDRESSLISTTABLE  ORDER BY ID+10000");
            currentClassName = "";
        } else {
            if (currentSQLOrder == null) {
                listShow("Select * From ADDRESSLISTTABLE  ORDER BY ID+10000");
                currentClassName = "";
            } else {
                listShow(currentSQLOrder);
            }
        }
    }

    private JPopupMenu creatPopupMenu() {
        JPopupMenu tpopupMenu2;
        tpopupMenu2 = new JPopupMenu();
        tpopupMenu2.add(parentFrame.getDetailAction());
        tpopupMenu2.addSeparator();
        tpopupMenu2.add(parentFrame.getModifyAction());
        tpopupMenu2.add(parentFrame.getDeleteAction());
        JMenu movetoMenu = createMovetoMenu();
        tpopupMenu2.add(movetoMenu);
        tpopupMenu2.addSeparator();
        tpopupMenu2.add(parentFrame.getQQAction());
        tpopupMenu2.add(parentFrame.getEmailAction());
        tpopupMenu2.addSeparator();
        tpopupMenu2.add(parentFrame.getRefreshAction());
        // 右键菜单注册事件
        return tpopupMenu2;
    }

    public int listShow(String SQLorder) {
        if (SQLorder == null) {
            SQLorder = "Select * From ADDRESSLISTTABLE  ORDER BY ID+10000";
        }
        currentSQLOrder = SQLorder;
        int i = 0;
        System.out.println("执行SQL语句：" + SQLorder);
        try {
            String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName(DBDriver); // 加载驱动器
            con = DriverManager.getConnection(DataBaseConnection.getConnectionStr()); // 连接数据库
            stmt = con.createStatement(); // 创建Statement对象
            rs = stmt.executeQuery(SQLorder);
            adlMainVector.removeAllElements();// 刷新表格显示结果集中的数据
            adlmainTableModel.fireTableStructureChanged();

            while (rs.next()) {
                Vector<String> vector2 = new Vector<String>();
                Vector<String> rec_vector = vector2;
                i++;

                rec_vector.addElement(rs.getString("NAME"));
                rec_vector.addElement(String.valueOf(rs.getString("CLASS")));
                rec_vector.addElement(String.valueOf(rs.getString("NO1")));
                rec_vector.addElement(String.valueOf(rs.getString("NO1CLASS")));
                rec_vector.addElement(String.valueOf(rs.getString("ADDRESS")));
                rec_vector.addElement(String.valueOf(rs.getString("HOMETOWN")));
                rec_vector.addElement(String.valueOf(rs.getString("QQ")));
                rec_vector.addElement(String.valueOf(rs.getString("EMAIL")));
                rec_vector.addElement(String.valueOf(rs.getString("REMARKS")));
                rec_vector.addElement(rs.getString("ID"));
                adlMainVector.addElement(rec_vector);
            }
            adlmainTableModel.fireTableStructureChanged();
            stmt.close(); // 关闭语句
            con.close(); // 关闭连接
        } catch (Exception ex) {
        }
        return i;
    }

    public boolean isTableSeclected() {
        for (int i = 0; i < 15; i++) {
            if (this.isColumnSelected(i)) {
                return true;
            }
        }
        return false;
    }

    public int getSelectID() {
        if (isTableSeclected()) {
            return Integer.parseInt(this.getValueAt(
                    this.getSelectedRow(), 9).toString());
        } else {
            return -1;
        }
    }

    private void mainListDetailAction() {
        if (this.getSelectID() != -1) {
            int serial = Integer.parseInt(this.getValueAt(
                    this.getSelectedRow(), 9).toString());
            System.out.println("详细信息 序号是：" + serial);
            DetailDialog detailDialog = new DetailDialog(parentFrame, currentSQLOrder, serial);
            if (detailDialog.getStateChanged()) {
                refresh();
            }
        }
    }

    public void setAll() {
        this.currentClassName = "ALL";
    }

    private JMenu createMovetoMenu() {
        JMenu movetoMenu = new JMenu("移动到");
        int selectID = getSelectID();
        if (selectID == -1) {
            movetoMenu.setEnabled(false);
        } else {
            DataBase dataBase = new dataBase.DataBase();
            AddressListData addressListData = dataBase.getAddressListDataByID(selectID + "");
            String xClass = addressListData.getXClass();
            String[] groupNameArray = dataBase.getGroupNameArray();
            for (String string : groupNameArray) {
                if (string.equals(xClass)) {
                    continue;
                } else {
                    JMenuItem menuItem = new JMenuItem(string);
                    movetoMenu.add(menuItem);
                    menuItem.setToolTipText("移动到 " + string + " 分组");
                    menuItem.setActionCommand(string);
                    menuItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectID = getSelectID();
                            AddressListData addressListData = new dataBase.DataBase().getAddressListDataByID(selectID + "");
                            String name = addressListData.getName();
                            String sting = "是否将‘" + name + "’移动到‘" + e.getActionCommand() + "’分组?";
                            int showConfirmDialog = JOptionPane.showConfirmDialog(null, sting, "移动确认", JOptionPane.YES_NO_OPTION);
                            if (showConfirmDialog == 0) {
                                System.out.println("移动到" + e.getActionCommand());
                                DataBase.moveToGroup(selectID, e.getActionCommand());
                                refresh();
                            }
                        }
                    });
                }
            }
        }
        return movetoMenu;
    }

    class TableHeaderGesture extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent me) {
            int columnIndex = AdlTable.this.getTableHeader().columnAtPoint(me.getPoint());
            String s = columnName[columnIndex];

            if (s.equals("序号")) {
                System.out.print("按序号排序");
                if (state.getSortbyID()) {
                    state.setSortbyID(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY ID+10000");
                } else {
                    state.setSortbyID(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY ID+10000 DESC");
                }
            } else if (s.equals("姓名")) {
                System.out.print("按姓名排序");
                if (state.getSortbyName()) {
                    state.setSortbyName(false);

                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NAME");
                } else {
                    state.setSortbyName(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NAME DESC");
                }
            } else if (s.equals("性别")) {
                System.out.print("按性别排序");
                if (state.getSortbySex()) {
                    state.setSortbySex(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY SEX");
                } else {
                    state.setSortbySex(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY SEX DESC");
                }
            } else if (s.equals("分组")) {
                System.out.print("按分组排序");
                if (state.getSortbyClass()) {
                    state.setSortbyClass(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY CLASS");
                } else {
                    state.setSortbyClass(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY CLASS DESC");
                }
            } else if (s.equals("号码1")) {
                System.out.print("按号码1排序");
                if (state.getSortNO1()) {
                    state.setSortNO1(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO1");
                } else {
                    state.setSortNO1(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO1 DESC");
                }
            } else if (s.equals("号码1类型")) {
                System.out.print("按号码1类型排序");
                if (state.getSortNO1Class()) {
                    state.setSortNO1Class(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO1CLASS");
                } else {
                    state.setSortNO1Class(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO1CLASS DESC");
                }
            } else if (s.equals("号码2")) {
                System.out.print("按号码2排序");
                if (state.getSortNO2()) {
                    state.setSortNO2(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO2");
                } else {
                    state.setSortNO2(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO2 DESC");
                }
            } else if (s.equals("号码2类型")) {
                System.out.print("按号码2类型排序");
                if (state.getSortNO2Class()) {
                    state.setSortNO2Class(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO2CLASS");
                } else {
                    state.setSortNO2Class(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO2CLASS DESC");
                }
            } else if (s.equals("号码3")) {
                System.out.print("按号码3排序");
                if (state.getSortNO3()) {
                    state.setSortNO3(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO3");
                } else {
                    state.setSortNO3(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO3 DESC");
                }
            } else if (s.equals("号码3类型")) {
                System.out.print("按号码3类型排序");
                if (state.getSortNO3Class()) {
                    state.setSortNO3Class(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO3CLASS");
                } else {
                    state.setSortNO3Class(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY NO3CLASS DESC");
                }
            } else if (s.equals("地址")) {
                System.out.print("按地址排序");
                if (state.getSortbyAddress()) {
                    state.setSortbyAddress(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY ADDRESS");
                } else {
                    state.setSortbyAddress(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY ADDRESS DESC");
                }
            } else if (s.equals("家乡地址")) {
                System.out.print("按家乡地址排序");
                if (state.getSortbyHomeTown()) {
                    state.setSortbyHomeTown(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY HOMETOWN");
                } else {
                    state.setSortbyHomeTown(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY HOMETOWN DESC");
                }
            } else if (s.equals("QQ/MSN")) {
                System.out.print("按QQ排序");
                if (state.getSortbyQq()) {
                    state.setSortbyQq(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY QQ");
                } else {
                    state.setSortbyQq(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY QQ DESC");
                }
            } else if (s.equals("EMail")) {
                System.out.print("按email排序");
                if (state.getSortbyEmail()) {
                    state.setSortbyEmail(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY EMAIL");
                } else {
                    state.setSortbyEmail(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY EMAIL DESC");
                }
            } else if (s.equals("备注")) {
                System.out.print("按备注排序");
                if (state.getSortbyRemarks()) {
                    state.setSortbyRemarks(false);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY REMARKS");
                } else {
                    state.setSortbyRemarks(true);
                    AdlTable.this.listShow("SELECT * FROM ADDRESSLISTTABLE ORDER BY REMARKS DESC");
                }
            }
        }
    }
}
