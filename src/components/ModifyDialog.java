package components;

import ui.ADialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ui.ATextArea;
import ui.ATextField;

public class ModifyDialog extends ADialog {

    private JFrame parentFrame = null;
    private int Serial;
    private String name = null;
    private String sex = null;
    private String xclass = null;
    private String NO1 = null;
    private String NO1Class = null;
    private String NO2 = null;
    private String NO2Class = null;
    private String NO3 = null;
    private String NO3Class = null;
    private String address = null;
    private String hometown = null;
    private String qq = null;
    private String email = null;
    private String remarks = null;
    private String birthdayCalendar = null;
    private boolean modificationSuccessed = false;

    public ModifyDialog(javax.swing.JFrame parent, int serial, boolean modal) {
        super(parent, modal);
        parentFrame = parent;
        this.Serial = serial;
        initComponents();
        loadData();
        loadAction();
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new ATextArea();
        emailTextField = new ATextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        hometownTextField = new ATextField();
        cancelButton = new javax.swing.JButton();
        addressTextField = new ATextField();
        sureButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        no1TextField = new ATextField();
        statueLabel1 = new javax.swing.JLabel();
        no1ClassComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        no2ClassComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        no2TextField = new ATextField();
        no3ClassComboBox = new javax.swing.JComboBox();
        no3TextField = new ATextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sexComboBox = new javax.swing.JComboBox();
        qqTextField = new ATextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nameTextField = new ATextField();
        jLabel4 = new javax.swing.JLabel();
        classComboBox = new javax.swing.JComboBox();
        timePanel1 = new components.TimePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("�޸�");

        jLabel6.setText("����1");

        jLabel14.setText("��ע");

        jScrollPane1.setOpaque(false);

        remarksTextArea.setOpaque(false);
        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        jLabel5.setText("�����ַ");

        jLabel13.setText("Email");

        cancelButton.setText("ȡ��");

        sureButton.setText("ȷ��");

        jLabel7.setText("��������");

        no1ClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("����2");

        no2ClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("��������");

        no3ClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("����3");

        jLabel1.setText("����");

        jLabel11.setText("��������");

        jLabel3.setText("����");

        sexComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("�Ա�");

        jLabel12.setText("QQ/MSN");

        jLabel4.setText("��ס��ַ");

        classComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qqTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hometownTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(no1TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(no2TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(no3TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(no1ClassComboBox, 0, 84, Short.MAX_VALUE)
                    .addComponent(no2ClassComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(no3ClassComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(statueLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(sureButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(timePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hometownTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(no1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no1ClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(no2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no2ClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(no3TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(no3ClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(qqTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sureButton)
                    .addComponent(cancelButton)
                    .addComponent(statueLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane1.getViewport().setOpaque(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextField addressTextField;
    javax.swing.JButton cancelButton;
    javax.swing.JComboBox classComboBox;
    javax.swing.JTextField emailTextField;
    javax.swing.JTextField hometownTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTextField nameTextField;
    javax.swing.JComboBox no1ClassComboBox;
    javax.swing.JTextField no1TextField;
    javax.swing.JComboBox no2ClassComboBox;
    javax.swing.JTextField no2TextField;
    javax.swing.JComboBox no3ClassComboBox;
    javax.swing.JTextField no3TextField;
    javax.swing.JTextField qqTextField;
    javax.swing.JTextArea remarksTextArea;
    javax.swing.JComboBox sexComboBox;
    private javax.swing.JLabel statueLabel1;
    javax.swing.JButton sureButton;
    private components.TimePanel timePanel1;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        sexComboBox.removeAllItems();
        sexComboBox.addItem(" ");
        sexComboBox.addItem("��");
        sexComboBox.addItem("Ů");
        loadClassComboBoxData();
        loadNoClassComboBoxData();
        dataBase.AddressListData ald = new dataBase.DataBase().getAddressListDataByID(Serial + "");
        name = ald.getName();
        sex = ald.getSex();
        xclass = ald.getXClass();
        classComboBox.setSelectedItem(xclass);
        NO1 = ald.getNo1();
        NO1Class = ald.getNo1Class();
        no1ClassComboBox.setSelectedItem(NO1Class);
        NO2 = ald.getNo2();
        NO2Class = ald.getNo2Class();
        no2ClassComboBox.setSelectedItem(NO2Class);
        NO3 = ald.getNo3();
        NO3Class = ald.getNo3Class();
        no3ClassComboBox.setSelectedItem(NO3Class);
        address = ald.getAddress();
        hometown = ald.getHometown();
        qq = ald.getQQ();
        email = ald.getEmail();
        remarks = ald.getRemarks();
        birthdayCalendar = ald.getCalendarBirthday();
        ald.getLunarBirthday();


        nameTextField.setText(name);
        sexComboBox.setSelectedItem(sex);
        no1TextField.setText(NO1);
        no2TextField.setText(NO2);
        no3TextField.setText(NO3);
        qqTextField.setText(qq);
        emailTextField.setText(email);
        remarksTextArea.setText(remarks);
        addressTextField.setText(address);
        hometownTextField.setText(hometown);
        timePanel1.setDate(birthdayCalendar);
    }

    private void loadNoClassComboBoxData() {
        String[] phoneArray = new dataBase.DataBase().getPhoneClassNameArray();
        no1ClassComboBox.removeAllItems();
        no2ClassComboBox.removeAllItems();
        no3ClassComboBox.removeAllItems();
        no1ClassComboBox.addItem("");
        no2ClassComboBox.addItem("");
        no3ClassComboBox.addItem("");
        for (int i = 0; i < phoneArray.length; i++) {
            no1ClassComboBox.addItem(phoneArray[i]);
            no2ClassComboBox.addItem(phoneArray[i]);
            no3ClassComboBox.addItem(phoneArray[i]);
        }
        no1ClassComboBox.addItem("���͹���");
        no2ClassComboBox.addItem("���͹���");
        no3ClassComboBox.addItem("���͹���");
    }

    private void loadClassComboBoxData() {
        classComboBox.removeAllItems();
        classComboBox.addItem("");
        String[] classNameArray = new dataBase.DataBase().getGroupNameArray();
        for (int i = 0; i < classNameArray.length; i++) {
            classComboBox.addItem(classNameArray[i]);
        }
        classComboBox.addItem("�������");
    }

    private void loadAction() {
        classComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("�������") && e.getStateChange() == ItemEvent.SELECTED) {
                    GroupManageDialog classAdminDialog = new GroupManageDialog(parentFrame, true, 1);
                    int x = (parentFrame.getWidth() - classAdminDialog.getWidth()) / 2 + parentFrame.getX();
                    int y = (parentFrame.getHeight() - classAdminDialog.getHeight()) / 2 + parentFrame.getY();
                    classAdminDialog.setLocation(x, y);
                    classAdminDialog.setVisible(true);
                    classAdminDialog.dispose();
                    loadClassComboBoxData();
                    setVisible(true);
                }
            }
        });
        no1ClassComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("���͹���") && e.getStateChange() == ItemEvent.SELECTED) {
                    GroupManageDialog classAdminDialog = new GroupManageDialog(parentFrame, true, 2);
                    int x = (parentFrame.getWidth() - classAdminDialog.getWidth()) / 2 + parentFrame.getX();
                    int y = (parentFrame.getHeight() - classAdminDialog.getHeight()) / 2 + parentFrame.getY();
                    classAdminDialog.setLocation(x, y);
                    classAdminDialog.setVisible(true);
                    loadNoClassComboBoxData();
                    setVisible(true);
                }
            }
        });
        no2ClassComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("���͹���") && e.getStateChange() == ItemEvent.SELECTED) {
                    GroupManageDialog classAdminDialog = new GroupManageDialog(parentFrame, true, 2);
                    int x = (parentFrame.getWidth() - classAdminDialog.getWidth()) / 2 + parentFrame.getX();
                    int y = (parentFrame.getHeight() - classAdminDialog.getHeight()) / 2 + parentFrame.getY();
                    classAdminDialog.setLocation(x, y);
                    classAdminDialog.setVisible(true);
                    loadNoClassComboBoxData();
                    setVisible(true);
                }
            }
        });
        no3ClassComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().toString().equals("���͹���") && e.getStateChange() == ItemEvent.SELECTED) {
                    GroupManageDialog classAdminDialog = new GroupManageDialog(parentFrame, true, 2);
                    int x = (parentFrame.getWidth() - classAdminDialog.getWidth()) / 2 + parentFrame.getX();
                    int y = (parentFrame.getHeight() - classAdminDialog.getHeight()) / 2 + parentFrame.getY();
                    classAdminDialog.setLocation(x, y);
                    classAdminDialog.setVisible(true);
                    loadNoClassComboBoxData();
                    setVisible(true);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        sureButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText().trim();
                sex = sexComboBox.getSelectedItem().toString();
                xclass = classComboBox.getSelectedItem().toString();
                address = addressTextField.getText();
                hometown = hometownTextField.getText();
                NO1 = no1TextField.getText().trim();
                NO1Class = no1ClassComboBox.getSelectedItem().toString();
                NO2 = no2TextField.getText().trim();
                NO2Class = NO2.equals("") ? "" : no2ClassComboBox.getSelectedItem().toString();
                NO3 = no3TextField.getText().trim();
                NO3Class = NO3.equals("") ? "" : no3ClassComboBox.getSelectedItem().toString();
                qq = qqTextField.getText().trim();
                email = emailTextField.getText().trim();
                remarks = remarksTextArea.getText().trim();

                String birthdayCalendarString = "";
                String birthdayLunarString = "";
                birthdayCalendarString = timePanel1.getCalendarDateString();
                birthdayLunarString = timePanel1.getLunarDataString();
                if (formatCheck()) {
                    String SQLOrder = "";
                    SQLOrder += "update  ADDRESSLISTTABLE  SET";
                    SQLOrder += "  NAME='" + name + "'";
                    SQLOrder += " ,   SEX='"
                            + sex + "'";
                    SQLOrder += " ,   CLASS='"
                            + xclass + "'";
                    SQLOrder += " ,   ADDRESS='" + address + "'";
                    SQLOrder += " ,  HOMETOWN='" + hometown + "'";
                    SQLOrder += " ,   NO1='" + NO1 + "'";
                    SQLOrder += " ,   NO1CLASS='"
                            + NO1Class
                            + "'";
                    SQLOrder += " ,   NO2='" + NO2 + "'";
                    SQLOrder += " ,   No2CLASS='"
                            + NO2Class
                            + "'";
                    SQLOrder += " ,   NO3='" + NO3 + "'";
                    SQLOrder += " ,   No3CLASS='"
                            + NO3Class
                            + "'";
                    SQLOrder += " ,  QQ='" + qq + "'";
                    SQLOrder += " ,  EMAIL='" + email + "'";
                    SQLOrder += " ,  REMARKS='" + remarks + "'";
                    SQLOrder += " ,  Birthday='" + birthdayCalendarString + "'";
                    SQLOrder += " ,  BirthdayLunar='" + birthdayLunarString + "'";
                    SQLOrder += "  WHERE ID='" + Serial + "'";
                    modificationSuccessed = true;
                    new dataBase.DataBase().SQLExecute(SQLOrder);
                    dispose();
                }
            }
        });
    }

    public Boolean formatCheck() {
        if (no1TextField.getText().isEmpty()
                || !(no1TextField.getText().matches("\\p{Digit}{7,13}"))) {
            int i = JOptionPane.showConfirmDialog(null,
                    "<html><p>��δ�������1�����벻��ȷ�����������룡</p><p>����ӦΪ����7λС��14λ������</p><p>�Ƿ�Ҫ������ӣ�</p></html>");
            if (i == 1 || i == 2) {
                return false;
            }
        }
        if (!(no2TextField.getText().isEmpty())) {
            if (!(no2TextField.getText().matches("\\p{Digit}{7,13}"))) {
                int i = JOptionPane.showConfirmDialog(null,
                        "<html><p>��δ�������2�����벻��ȷ�����������룡</p><p>����ӦΪ����7λС��14λ������</p><p>�Ƿ�Ҫ������ӣ�</p></html>");
                if (i == 1 || i == 2) {
                    return false;
                }
            } else if (no2TextField.getText().equals(no1TextField.getText())) {
                JOptionPane.showMessageDialog(null, "����1�����2�ظ���"); // ��ʾ��ʾ��Ϣ
                return false;
            }
        }
        if (!(no3TextField.getText().isEmpty())) {
            if (!(no3TextField.getText().matches("\\p{Digit}{7,13}"))) {
                int i = JOptionPane.showConfirmDialog(null,
                        "<html><p>��δ�������3�����벻��ȷ�����������룡</p><p>����ӦΪ����7λС��14λ������</p><p>�Ƿ�Ҫ������ӣ�</p></html>");
                if (i == 1 || i == 2) {
                    return false;
                }
            } else if (no3TextField.getText().equals(no1TextField.getText())) {
                JOptionPane.showMessageDialog(null, "����1�����3�ظ���"); // ��ʾ��ʾ��Ϣ
                return false;
            } else if (no3TextField.getText().equals(no2TextField.getText())) {
                JOptionPane.showMessageDialog(null, "����2�����3�ظ���"); // ��ʾ��ʾ��Ϣ
                return false;
            }
        }
        if (!(qqTextField.getText().isEmpty())) {
            if (!(qqTextField.getText().matches("\\p{Digit}{7,12}"))
                    && !(qqTextField.getText().matches(".{1,}@.{1,}"))) {
                JOptionPane.showMessageDialog(null,
                        "<html><p>QQ����Ӧ��Ϊ7~11λ����</p><p>MSN��Ϊ��sample@live.com</p></html>");
                return false;
            }
        }
        if (!(emailTextField.getText().isEmpty())) {
            if (!(emailTextField.getText().matches(".{1,}@.{1,}"))) {
                JOptionPane.showMessageDialog(null,
                        "<html><p>Email��Ϊ��sample@live.com</p></html>");
                return false;
            }
        }
        return true;
    }

    public boolean getModifiedSuccessed() {
        return modificationSuccessed;
    }
}
