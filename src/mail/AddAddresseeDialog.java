package mail;

import dataBase.DataBaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class AddAddresseeDialog extends javax.swing.JDialog {

    private Vector<Vector<String>> adlMainVector;
    private AbstractTableModel adlmainTableModel;
    private String[] columnName;
    private String[] emailStrings = null;

    /** Creates new form addAddresseeJDialog */
    public AddAddresseeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        EmailTable = new javax.swing.JTable();
        sureButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("添加收件人");

        jScrollPane1.setViewportView(EmailTable);

        sureButton.setText("添加");
        sureButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                sureButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("取消");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(sureButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sureButton)
                    .addComponent(cancelButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void sureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sureButtonActionPerformed
        int selectedRowCount = EmailTable.getSelectedRow();
        if (selectedRowCount != -1) {
            int[] selectedRowsIndex = EmailTable.getSelectedRows();
            emailStrings = new String[selectedRowsIndex.length];
            for (int i = 0; i < selectedRowsIndex.length; i++) {
                int j = selectedRowsIndex[i];
                emailStrings[i] = EmailTable.getValueAt(j, 1).toString();
//                System.out.println(emailStrings[i]);
            }
        }
        dispose();
    }//GEN-LAST:event_sureButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable EmailTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sureButton;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        columnName = new String[]{"姓名", "邮箱"};
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
        EmailTable.setModel(adlmainTableModel);
        EmailTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listShow("Select * From ADDRESSLISTTABLE  ORDER BY ID+10000");
    }

    public int listShow(String SQLorder) {
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

            while (rs.next()) {
                Vector<String> vector2 = new Vector<String>();
                Vector<String> rec_vector = vector2;
                i++;
                String nameString = rs.getString("NAME");
                String emaString = rs.getString("EMAIL");
                if (!emaString.isEmpty()) {
                    rec_vector.addElement(nameString);
                    rec_vector.addElement(String.valueOf(emaString));
                    adlMainVector.addElement(rec_vector);
                }
            }
            adlmainTableModel.fireTableStructureChanged();
            stmt.close(); // 关闭语句
            con.close(); // 关闭连接
        } catch (Exception ex) {
        }
        return i;
    }

    public String[] getEmailStrings() {
        return emailStrings;
    }
}
