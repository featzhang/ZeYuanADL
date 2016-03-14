package components;

import ui.ADialog;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;

public class GroupManageDialog extends ADialog {

    private static final long serialVersionUID = -3207713085869219441L;

    public GroupManageDialog(JFrame frame, boolean madal, int mode) {
        super(frame, madal);
        classAdmin(mode);
    }
    private Vector<Vector<String>> classSetVector, noClassSetVector;
    private AbstractTableModel classSetTableModel,
            noClassSetTableModel;
    private String[] classNameArray;
    private JTable classSetTable, noClassSetTable;

    public void classAdmin(int mode) {
        setTitle("�������");
        Container contentPane = getContentPane();
        JTabbedPane tabbedPane = new JTabbedPane();
        // ///////////////////////////classset
        JPanel classSetPanel = new JPanel();
        final String[] classSetTitle = {"���", "��������"};
        classSetVector = new Vector<Vector<String>>();

        classSetTableModel = new AbstractTableModel() {

            private static final long serialVersionUID = 500000L;

            @Override
            public int getColumnCount() {
                return classSetTitle.length;
            }

            @Override
            public String getColumnName(int column) // ��ȡ�б�
            {
                return classSetTitle[column];
            }

            @Override
            public int getRowCount() {
                return classSetVector.size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (!classSetVector.isEmpty()) {
                    return ((Vector<?>) classSetVector.elementAt(row)).elementAt(column);
                } else {
                    return null;
                }
            }
        };
        classSetVector.removeAllElements();// ˢ�±����ʾ������е�����
        classSetTableModel.fireTableStructureChanged();
        classSetTableRefresh();
        classSetTable = new JTable(classSetTableModel);
        classSetTable.setShowGrid(true);
        classSetTable.setToolTipText("����ѡ��Ԫ��");
        JScrollPane classScrollPane = new JScrollPane(classSetTable);
        classSetPanel.add(classScrollPane, java.awt.BorderLayout.CENTER);
        JPanel classSetbuttonPanel = new JPanel();// ��ӡ��༭��ɾ����ť
        classSetbuttonPanel.setLayout(new BoxLayout(classSetbuttonPanel,
                BoxLayout.Y_AXIS));

        JButton addClassButton = new JButton("���");
        JButton editClassButton = new JButton("�༭");
        JButton deleteClassButton = new JButton("ɾ��");
        classSetbuttonPanel.add(addClassButton);
        addClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog(GroupManageDialog.this, "������Ҫ��ӵķ��������");
                if (!(s == null)) {
                    if (new dataBase.DataBase().addGroupName(s)) {
                        JOptionPane.showMessageDialog(GroupManageDialog.this, "���� '" + s + "' �Ѵ��ڣ�");
                    }
                    classSetTableRefresh();
                }
            }
        });
        editClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = classSetTable.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(GroupManageDialog.this,
                            "��δѡ����ѡ������ԣ�");
                } else {
                    String oldString = new dataBase.DataBase().getClassName(i);
                    String newString = JOptionPane.showInputDialog(GroupManageDialog.this, "�������޸ĺ������");
                    if (!(newString == null)) {
                        new dataBase.DataBase().modifyGroupName(oldString, newString);
                        classSetTableRefresh();
                    }
                }
            }
        });
        classSetbuttonPanel.add(editClassButton);
        deleteClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = classSetTable.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(null,
                            "��δѡ��,��ѡ������ԣ�");
                } else {
                    int j = JOptionPane.showConfirmDialog(null,
                            "�Ƿ�ȷ��ɾ����", "ѡ��", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println(j);
                    if (j == 0) {
                        String classNameString = new dataBase.DataBase().getClassName(i);
                        System.out.println(classNameString);
                        new dataBase.DataBase().deleteGroupName(classNameString);
                        classSetTableRefresh();
                    }
                }
            }
        });
        classSetbuttonPanel.add(deleteClassButton);
        classSetbuttonPanel.setBackground(java.awt.Color.red);
        classSetPanel.add(classSetbuttonPanel, java.awt.BorderLayout.EAST);
        tabbedPane.add(classSetPanel, "��¼���� ");
        // //////////////////////noclassset
        JPanel noClassSetPanel = new JPanel();
        noClassSetVector = new Vector<Vector<String>>();

        noClassSetTableModel = new AbstractTableModel() {

            private static final long serialVersionUID = 500000L;

            @Override
            public int getColumnCount() {
                return classSetTitle.length;
            }

            @Override
            public String getColumnName(int column) // ��ȡ�б�
            {
                return classSetTitle[column];
            }

            @Override
            public int getRowCount() {
                return noClassSetVector.size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (!noClassSetVector.isEmpty()) {
                    return ((Vector<?>) noClassSetVector.elementAt(row)).elementAt(column);
                } else {
                    return null;
                }
            }
        };
        noClassSetVector.removeAllElements();// ˢ�±����ʾ������е�����
        noClassSetTableModel.fireTableStructureChanged();
        noClassSetTableRefresh();
        noClassSetTable = new JTable(noClassSetTableModel);
        noClassSetTable.setShowGrid(true);
        noClassSetTable.setToolTipText("����ѡ��Ԫ��");
        JScrollPane noclassScrollPane = new JScrollPane(noClassSetTable);
        noClassSetPanel.add(noclassScrollPane, java.awt.BorderLayout.CENTER);
        JPanel noClassSetbuttonPanel = new JPanel();// ��ӡ��༭��ɾ����ť
        noClassSetbuttonPanel.setLayout(new BoxLayout(noClassSetbuttonPanel,
                BoxLayout.Y_AXIS));
        JButton addnoClassButton = new JButton("���");
        JButton editnoClassButton = new JButton("�༭");
        JButton deletenoClassButton = new JButton("ɾ��");
        addnoClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String s = JOptionPane.showInputDialog(GroupManageDialog.this,
                        "������Ҫ��ӵķ��������");
                if (!(s == null)) {
                    if (new dataBase.DataBase().addNoClassName(s)) {
                        JOptionPane.showMessageDialog(GroupManageDialog.this, "���� '"
                                + s + "' �Ѵ��ڣ�");
                    }
                    noClassSetTableRefresh();
                }
            }
        });
        noClassSetbuttonPanel.add(addnoClassButton);
        editnoClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = noClassSetTable.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(GroupManageDialog.this,
                            "��δѡ����ѡ������ԣ�");
                } else {
                    String oldString = new dataBase.DataBase().getNOClassName(i);
                    String newString = JOptionPane.showInputDialog(GroupManageDialog.this, "�������޸ĺ������");
                    if (!(newString == null)) {
                        new dataBase.DataBase().modifyNoClassName(oldString,
                                newString);
                        noClassSetTableRefresh();
                    }
                }
            }
        });
        noClassSetbuttonPanel.add(editnoClassButton);
        deletenoClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = noClassSetTable.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(GroupManageDialog.this,
                            "��δѡ��,��ѡ������ԣ�");
                } else {
                    int j = JOptionPane.showConfirmDialog(GroupManageDialog.this,
                            "�Ƿ�ȷ��ɾ����", "ѡ��", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println(j);
                    if (j == 0) {
                        String noClassNameString = new dataBase.DataBase().getNOClassName(i);
                        System.out.println(noClassNameString);
                        new dataBase.DataBase().deleteNoClassName(noClassNameString);
                        noClassSetTableRefresh();
                    }
                }
            }
        });
        noClassSetbuttonPanel.add(deletenoClassButton);
        noClassSetPanel.add(noClassSetbuttonPanel, java.awt.BorderLayout.EAST);
        tabbedPane.add(noClassSetPanel, "�������� ");

        if (mode == 1) {
            tabbedPane.setSelectedIndex(0);
        } else if (mode == 2) {
            tabbedPane.setSelectedIndex(1);
        }
        contentPane.add(tabbedPane, java.awt.BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    private void classSetTableRefresh() {
        classSetVector.removeAllElements();
        classSetTableModel.fireTableStructureChanged();
        classNameArray = new dataBase.DataBase().getGroupNameArray();
        for (int i = 0; i < classNameArray.length; i++) {
            Vector<String> vector2 = new Vector<String>();
            Vector<String> rec_vector = vector2;
            rec_vector.addElement("" + (i + 1));
            rec_vector.addElement(classNameArray[i]);
            classSetVector.addElement(rec_vector);
        }
        classSetTableModel.fireTableStructureChanged();
    }

    private void noClassSetTableRefresh() {
        noClassSetVector.removeAllElements();
        noClassSetTableModel.fireTableStructureChanged();
        String[] noClassNameArray = new dataBase.DataBase().getPhoneClassNameArray();
        for (int i = 0; i < noClassNameArray.length; i++) {
            Vector<String> vector2 = new Vector<String>();
            Vector<String> rec_vector = vector2;
            rec_vector.addElement("" + (i + 1));
            rec_vector.addElement(noClassNameArray[i]);
            noClassSetVector.addElement(rec_vector);
        }
        noClassSetTableModel.fireTableStructureChanged();
    }
}
