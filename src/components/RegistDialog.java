package components;

import dataBase.DataBase;
import ui.ADialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ui.AButton;
import ui.ATextArea;
import ui.ATextField;

public class RegistDialog extends ADialog {

    private static final long serialVersionUID = 344456346l;
    private JFrame fatherFrame = null;
    private String nameString;
    private String sexString;
    private String classString;
    private String no1String;
    private String no1ClassString;
    private String no2String;
    private String no2ClassString;
    private String no3String;
    private String no3ClassString;
    private String province1String;
    private String city1String;
    private String county1String;
    private String other1String;
    private String province2String;
    private String city2String;
    private String county2String;
    private String other2String;
    private String qqString;
    private String emailString;
    private String remarksString;
    private boolean registSuccessed = false;

    public RegistDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        fatherFrame = parent;
        initComponents();
        loadData();
        loadAction();
    }

    public RegistDialog(JFrame parent) {
        super(parent, true);
        fatherFrame = parent;
        initComponents();
        loadData();
        loadAction();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        nameTextField = new ATextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        sexComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        classComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        provinceComboBox = new javax.swing.JComboBox();
        cityComboBox = new javax.swing.JComboBox();
        countyTextField = new javax.swing.JTextField();
        otherTextField = new javax.swing.JTextField();
        province2ComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        other2TextField = new javax.swing.JTextField();
        city2ComboBox = new javax.swing.JComboBox();
        county2TextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        no1TextField = new ATextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        no1ClassComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        no2ClassComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        no2TextField = new ATextField();
        no3ClassComboBox = new javax.swing.JComboBox();
        no3TextField = new ATextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        qqTextField = new ATextField();
        emailTextField = new ATextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new ATextArea();
        sureButton = new AButton();
        cancelButton = new AButton();
        javax.swing.JLabel statueLabel1 = new javax.swing.JLabel();
        timePanel21 = new components.TimePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("添加记录");

        jLabel1.setText("姓名");

        nameTextField.setOpaque(false);

        jLabel2.setText("性别");

        sexComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sexComboBox.setOpaque(false);

        jLabel3.setText("分组");

        classComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        classComboBox.setOpaque(false);

        jLabel4.setText("居住地址");

        provinceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        provinceComboBox.setOpaque(false);

        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cityComboBox.setOpaque(false);

        countyTextField.setOpaque(false);

        otherTextField.setOpaque(false);

        province2ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        province2ComboBox.setOpaque(false);

        jLabel5.setText("家乡地址");

        other2TextField.setOpaque(false);

        city2ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        city2ComboBox.setOpaque(false);

        county2TextField.setOpaque(false);

        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("号码1");

        no1TextField.setOpaque(false);

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("号码类型");

        no1ClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        no1ClassComboBox.setOpaque(false);

        jLabel8.setText("号码2");

        no2ClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        no2ClassComboBox.setOpaque(false);

        jLabel9.setText("号码类型");

        no2TextField.setOpaque(false);

        no3ClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        no3ClassComboBox.setOpaque(false);

        no3TextField.setOpaque(false);

        jLabel10.setText("号码3");

        jLabel11.setText("号码类型");

        jLabel12.setText("QQ/MSN");

        qqTextField.setOpaque(false);

        emailTextField.setOpaque(false);

        jLabel13.setText("Email");

        jLabel14.setText("备注");

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        remarksTextArea.setOpaque(false);
        jScrollPane1.setViewportView(remarksTextArea);

        sureButton.setText("确定");
        sureButton.setHideActionText(true);

        cancelButton.setText("取消");
        cancelButton.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(province2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(city2ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(countyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(county2TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(other2TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(otherTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(no1TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(no2TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(no3TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(statueLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(sureButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timePanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(sexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel4)
                    .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(province2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(county2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(other2TextField))
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
                .addComponent(timePanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sureButton)
                    .addComponent(cancelButton)
                    .addComponent(statueLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadData() {
        sexComboBox.removeAllItems();
        sexComboBox.addItem("");
        sexComboBox.addItem("男");
        sexComboBox.addItem("女");
        sexComboBox.setSelectedIndex(1);
        loadClassComboBoxData();
        String[] provinceArray = new dataBase.DataBase().getProvinceName();
        provinceComboBox.removeAllItems();
        province2ComboBox.removeAllItems();
        for (int i = 0; i < provinceArray.length; i++) {
            provinceComboBox.addItem(provinceArray[i]);
            province2ComboBox.addItem(provinceArray[i]);
        }
        cityComboBox.removeAllItems();
        city2ComboBox.removeAllItems();
        loadNoClassComboBoxData();
//        timeShowPanel.add(timePanel);
    }

    private void loadClassComboBoxData() {
        classComboBox.removeAllItems();
        classComboBox.addItem("");
        String[] classNameArray = new dataBase.DataBase().getGroupNameArray();
        for (int i = 0; i < classNameArray.length; i++) {
            classComboBox.addItem(classNameArray[i]);
        }
        classComboBox.addItem("分组管理");
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
        no1ClassComboBox.addItem("类型管理");
        no2ClassComboBox.addItem("类型管理");
        no3ClassComboBox.addItem("类型管理");
    }

    private void loadAction() {
        classComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                String s = e.getItem().toString();
                if (s.equals("分组管理") && e.getStateChange() == ItemEvent.SELECTED) {
                    dispose();
                    classComboBox.setSelectedIndex(0);
                    GroupManageDialog classAdminDialog = new GroupManageDialog(fatherFrame, true, 1);
                    int x = (fatherFrame.getWidth() - classAdminDialog.getWidth()) / 2 + fatherFrame.getX();
                    int y = (fatherFrame.getHeight() - classAdminDialog.getHeight()) / 2 + fatherFrame.getY();
                    classAdminDialog.setLocation(x, y);
                    classAdminDialog.setVisible(true);
                    loadClassComboBoxData();
                    setVisible(true);
                }
            }
        });
        provinceComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                String s = provinceComboBox.getSelectedItem().toString();
                if (!s.equals("") && e.getStateChange() == ItemEvent.SELECTED) {
                    String[] cityString = new dataBase.DataBase().getCityName(s);
                    cityComboBox.removeAllItems();
                    for (int i = 0; i < cityString.length; i++) {
                        cityComboBox.addItem(cityString[i]);
                    }
                }
            }
        });
        province2ComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                String s = province2ComboBox.getSelectedItem().toString();
                if (!s.equals("") && e.getStateChange() == ItemEvent.SELECTED) {
                    String[] city2String = new dataBase.DataBase().getCityName(s);
                    city2ComboBox.removeAllItems();
                    for (int i = 0; i < city2String.length; i++) {
                        city2ComboBox.addItem(city2String[i]);
                    }
                }
            }
        });
        no1ClassComboBox.addItemListener(new NOClassItemListener());
        no2ClassComboBox.addItemListener(new NOClassItemListener());
        no3ClassComboBox.addItemListener(new NOClassItemListener());
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        sureButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nameString = nameTextField.getText().trim();
                sexString = sexComboBox.getSelectedItem().toString();
                classString = classComboBox.getSelectedItem().toString();
                no1String = no1TextField.getText().trim();
                no1ClassString = no1ClassComboBox.getSelectedItem().toString();
                no2String = no2TextField.getText().trim();
                no2ClassString = no2String.equals("") ? "" : no2ClassComboBox.getSelectedItem().toString();
                no3String = no3TextField.getText().trim();
                no3ClassString = no3String.equals("") ? "" : no3ClassComboBox.getSelectedItem().toString();
                province1String = provinceComboBox.getSelectedItem().toString();
                if (province1String.equals("")) {
                    city1String = "";
                } else {
                    city1String = cityComboBox.getSelectedItem().toString();
                }
                county1String = countyTextField.getText().trim();
                other1String = otherTextField.getText().trim();
                province2String = province2ComboBox.getSelectedItem().toString();
                if (province2String.equals("")) {
                    city2String = "";
                } else {
                    city2String = city2ComboBox.getSelectedItem().toString();
                }
                county2String = county2TextField.getText().trim();
                other2String = other2TextField.getText().trim();
                qqString = qqTextField.getText().trim();
                emailString = emailTextField.getText().trim();
                remarksString = remarksTextArea.getText().trim();
                String birthdayCalendarString = "";
                String birthdayLunarString = "";
                birthdayCalendarString = timePanel21.getCalendarDateString();
                birthdayLunarString = timePanel21.getLunarDataString();
                if (formatCheck()) {
                    int ID = new dataBase.DataBase().getMaxSerial() + 1;
                    String SQLOrder = "INSERT INTO ADDRESSLISTTABLE VALUES(" + ID
                            + ",'" + nameString + "','" + sexString + "','" + classString
                            + "','" + no1String + "','" + no1ClassString + "','" + no2String + "','"
                            + no2ClassString + "','" + no3String + "','" + no3ClassString + "','";
                    SQLOrder += province1String;
                    if (!province1String.trim().isEmpty()) {
                        SQLOrder += new dataBase.DataBase().getAppellationName(province1String);
                    }
                    SQLOrder += city1String;
                    if (!city1String.trim().isEmpty()) {
                        SQLOrder += "市";
                    }
                    SQLOrder += county1String + other1String + "','"
                            + province2String;
                    if (!province2String.trim().isEmpty()) {
                        SQLOrder += new dataBase.DataBase().getAppellationName(province1String);
                    }
                    SQLOrder += city2String;
                    if (!city2String.trim().isEmpty()) {
                        SQLOrder += "市";
                    }
                    SQLOrder += county2String + other2String + "','" + qqString
                            + "','" + emailString + "','" + remarksString + "','"
                            + birthdayCalendarString + "','" + birthdayLunarString + "')";

                    DataBase.SQLExecute(SQLOrder);
                    registSuccessed = true;
                    dispose();
                }

            }
        });
    }

    public Boolean formatCheck() {
        if (no1TextField.getText().isEmpty()
                || !(no1TextField.getText().matches("\\p{Digit}{7,13}"))) {
            int i = JOptionPane.showConfirmDialog(null,
                    "<html><p>您未输入号码1或输入不正确，请重新输入！</p><p>号码应为大于7位小于14位的数字</p><p>是否要继续添加？</p></html>");
            if (i == 1 || i == 2) {
                return false;
            }
        }
        if (!(no2TextField.getText().isEmpty())) {
            if (!(no2TextField.getText().matches("\\p{Digit}{7,13}"))) {
                int i = JOptionPane.showConfirmDialog(null,
                        "<html><p>您未输入号码2或输入不正确，请重新输入！</p><p>号码应为大于7位小于14位的数字</p><p>是否要继续添加？</p></html>");
                if (i == 1 || i == 2) {
                    return false;
                }
            } else if (no2TextField.getText().equals(no1TextField.getText())) {
                JOptionPane.showMessageDialog(null, "号码1与号码2重复！"); // 显示提示信息
                return false;
            }
        }
        if (!(no3TextField.getText().isEmpty())) {
            if (!(no3TextField.getText().matches("\\p{Digit}{7,13}"))) {
                int i = JOptionPane.showConfirmDialog(null,
                        "<html><p>您未输入号码3或输入不正确，请重新输入！</p><p>号码应为大于7位小于14位的数字</p><p>是否要继续添加？</p></html>");
                if (i == 1 || i == 2) {
                    return false;
                }
            } else if (no3TextField.getText().equals(no1TextField.getText())) {
                JOptionPane.showMessageDialog(null, "号码1与号码3重复！"); // 显示提示信息
                return false;
            } else if (no3TextField.getText().equals(no2TextField.getText())) {
                JOptionPane.showMessageDialog(null, "号码2与号码3重复！"); // 显示提示信息
                return false;
            }
        }
        if (!(qqTextField.getText().isEmpty())) {
            if (!(qqTextField.getText().matches("\\p{Digit}{7,12}"))
                    && !(qqTextField.getText().matches(".{1,}@.{1,}"))) {
                JOptionPane.showMessageDialog(null,
                        "<html><p>QQ号码应该为7~11位数字</p><p>MSN例为：sample@live.com</p></html>");
                return false;
            }
        }
        if (!(emailTextField.getText().isEmpty())) {
            if (!(emailTextField.getText().matches(".{1,}@.{1,}"))) {
                JOptionPane.showMessageDialog(null,
                        "<html><p>Email例为：sample@live.com</p></html>");
                return false;
            }
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton cancelButton;
    javax.swing.JComboBox city2ComboBox;
    javax.swing.JComboBox cityComboBox;
    javax.swing.JComboBox classComboBox;
    javax.swing.JTextField county2TextField;
    javax.swing.JTextField countyTextField;
    javax.swing.JTextField emailTextField;
    javax.swing.JTextField nameTextField;
    javax.swing.JComboBox no1ClassComboBox;
    javax.swing.JTextField no1TextField;
    javax.swing.JComboBox no2ClassComboBox;
    javax.swing.JTextField no2TextField;
    javax.swing.JComboBox no3ClassComboBox;
    javax.swing.JTextField no3TextField;
    javax.swing.JTextField other2TextField;
    javax.swing.JTextField otherTextField;
    javax.swing.JComboBox province2ComboBox;
    javax.swing.JComboBox provinceComboBox;
    javax.swing.JTextField qqTextField;
    javax.swing.JTextArea remarksTextArea;
    javax.swing.JComboBox sexComboBox;
    javax.swing.JButton sureButton;
    components.TimePanel timePanel21;
    // End of variables declaration//GEN-END:variables

    public boolean getRegistSuccessed() {
        return registSuccessed;
    }

    private class NOClassItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            String s = e.getItem().toString();
            if (s.equals("类型管理") && e.getStateChange() == ItemEvent.SELECTED) {
                GroupManageDialog classAdminDialog = new GroupManageDialog(fatherFrame, true, 2);
                int x = (fatherFrame.getWidth() - classAdminDialog.getWidth()) / 2 + fatherFrame.getX();
                int y = (fatherFrame.getHeight() - classAdminDialog.getHeight()) / 2 + fatherFrame.getY();
                classAdminDialog.setLocation(x, y);
                classAdminDialog.setVisible(true);
                loadNoClassComboBoxData();
                classAdminDialog.dispose();
                setVisible(true);
            }
        }
    }

    public void setSearchText(String s) {
        if (s.isEmpty()) {
            return;
        }
        if (s.indexOf("====") != -1) {
            int indexOf = s.indexOf("====");
            String subString = s.substring(indexOf + 4, s.length());
            int c = s.charAt(0) - '0';
            if (c == 1) {
                no1TextField.setText(subString);
            } else if (c == 2) {
                nameTextField.setText(subString);
            } else if (c == 4) {
                otherTextField.setText(subString);
            } else if (c == 5) {
                qqTextField.setText(subString);
            } else if (c == 6) {
                emailTextField.setText(subString);
            } else if (c == 7) {
                remarksTextArea.setText(subString);
            } else if (c == 8) {
                if (subString.equals("男")) {
                    sexComboBox.setSelectedIndex(1);
                } else if (subString.equals("女")) {
                    sexComboBox.setSelectedIndex(2);
                }
            }
        } else {
            if (isNumber(s)) {
                no1TextField.setText(s);
            } else {
                nameTextField.setText(s);
            }
        }
    }

    private boolean isNumber(String numberStr) {
        String valid = "0123456789";
        String temp = "";
        boolean flag = true;
        if (numberStr == null || numberStr.equals("") || numberStr.equals("null")) {
            flag = false;
        } else {
            for (int i = 0; i < numberStr.length(); i++) {
                temp = "" + numberStr.substring(i, i + 1);
                if (valid.indexOf(temp) == -1) {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
