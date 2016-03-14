package components;

import dataBase.AddressListData;
import dataBase.DataBase;
import dataBase.DataBaseConnection;
import java.text.ParseException;
import util.ConfigManager;
import java.util.Date;
import ui.ADialog;
import mail.SendMailJDialog;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import ui.ImagePanel;
import util.AResource;
import util.CalendarHandle;

public class DetailDialog extends ADialog {

    private JFrame parentFrame;
    private String currentSQLOrder;
    private int showDetailSerial;
    private int[] serialArray;
    private boolean stateChanged = false;
    private String qqString = null;
    private String EmailsString = null;
    private Dimension detailDialogDimension = new Dimension(375, 435);

    public DetailDialog(JFrame f, String currentOrder, int serial) {
        super(f, true);
        initComponents();
        parentFrame = f;
        currentSQLOrder = currentOrder;
        setLocationRelativeTo(f);
        if (serial != -1) {
            showDetail(serial);
        }
        setVisible(true);
    }

    public DetailDialog(JFrame f, int serial) {
        this(f, "select * from ADDRESSLISTTABLE", serial);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new ImagePanel(AResource.getWallpaper());
        jPanel1 = new javax.swing.JPanel();
        showDetailAboveButton = new javax.swing.JButton();
        showDetailBelowButton = new javax.swing.JButton();
        showDetailDeleteButton = new javax.swing.JButton();
        showDetailEditButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        showDetailcloseButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        sexLabel = new javax.swing.JLabel();
        sexTextField = new javax.swing.JTextField();
        classTextField = new ui.ATextField();
        classLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        addressTextField = new ui.ATextField();
        hometownTextField = new ui.ATextField();
        hometownLabel = new javax.swing.JLabel();
        no1TextField = new ui.ATextField();
        no1Label = new javax.swing.JLabel();
        no1classTextField = new javax.swing.JTextField();
        no2Label = new javax.swing.JLabel();
        no2TextField = new ui.ATextField();
        no2classTextField = new javax.swing.JTextField();
        no3TextField = new ui.ATextField();
        no3classTextField = new javax.swing.JTextField();
        no3Label = new javax.swing.JLabel();
        QQTextField = new ui.ATextField();
        QQLabel = new javax.swing.JLabel();
        EmailTextField = new ui.ATextField();
        EmailLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new ui.ATextArea();
        QQButton = new javax.swing.JButton();
        emailButton = new javax.swing.JButton();
        nameTextField = new ui.ATextField();
        birthdayLabel = new javax.swing.JLabel();
        birthdayTextField = new ui.ATextField();
        constellationLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("详细信息");
        setBackground(new java.awt.Color(255, 255, 204));
        setForeground(new java.awt.Color(255, 255, 204));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 204));
        jPanel1.setOpaque(false);

        showDetailAboveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/pageup.png"))); // NOI18N
        showDetailAboveButton.setToolTipText("上一条");
        showDetailAboveButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showDetailAboveButton.setContentAreaFilled(false);
        showDetailAboveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showDetailAboveButton.setSelected(true);

        showDetailBelowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/pagedown.png"))); // NOI18N
        showDetailBelowButton.setToolTipText("下一条");
        showDetailBelowButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showDetailBelowButton.setContentAreaFilled(false);
        showDetailBelowButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        showDetailDeleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/delete.png"))); // NOI18N
        showDetailDeleteButton.setToolTipText("删除");
        showDetailDeleteButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showDetailDeleteButton.setContentAreaFilled(false);
        showDetailDeleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        showDetailEditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/modify.png"))); // NOI18N
        showDetailEditButton.setToolTipText("修改");
        showDetailEditButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showDetailEditButton.setContentAreaFilled(false);
        showDetailEditButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/printor.png"))); // NOI18N
        printButton.setToolTipText("打印");
        printButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        printButton.setContentAreaFilled(false);
        printButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        showDetailcloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/exit.png"))); // NOI18N
        showDetailcloseButton.setToolTipText("关闭");
        showDetailcloseButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showDetailcloseButton.setContentAreaFilled(false);
        showDetailcloseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showDetailAboveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDetailBelowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showDetailDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDetailEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDetailcloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showDetailcloseButton, javax.swing.GroupLayout.Alignment.TRAILING, 0, 39, Short.MAX_VALUE)
                    .addComponent(printButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(showDetailEditButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(showDetailDeleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(showDetailBelowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(showDetailAboveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 204));
        jPanel2.setOpaque(false);

        sexLabel.setText("性别:");

        sexTextField.setBackground(new java.awt.Color(255, 255, 204));
        sexTextField.setEditable(false);
        sexTextField.setHorizontalAlignment(SwingConstants.CENTER);
        sexTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        sexTextField.setOpaque(false);

        classTextField.setBackground(new java.awt.Color(255, 255, 204));
        classTextField.setEditable(false);
        classTextField.setHorizontalAlignment(SwingConstants.CENTER);
        classTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        classTextField.setOpaque(false);

        classLabel.setText("分组:");

        addressLabel.setText("地址:");

        addressTextField.setBackground(new java.awt.Color(255, 255, 204));
        addressTextField.setEditable(false);
        addressTextField.setHorizontalAlignment(SwingConstants.CENTER);
        addressTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        addressTextField.setOpaque(false);

        hometownTextField.setBackground(new java.awt.Color(255, 255, 204));
        hometownTextField.setEditable(false);
        hometownTextField.setHorizontalAlignment(SwingConstants.CENTER);
        hometownTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        hometownTextField.setOpaque(false);

        hometownLabel.setText("家乡:");

        no1TextField.setBackground(new java.awt.Color(255, 255, 204));
        no1TextField.setEditable(false);
        no1TextField.setHorizontalAlignment(SwingConstants.CENTER);
        no1TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        no1TextField.setOpaque(false);

        no1Label.setText("号码1:");

        no1classTextField.setEditable(false);
        no1classTextField.setHorizontalAlignment(SwingConstants.CENTER);
        no1classTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        no1classTextField.setOpaque(false);

        no2Label.setText("号码2:");

        no2TextField.setBackground(new java.awt.Color(255, 255, 204));
        no2TextField.setEditable(false);
        no2TextField.setHorizontalAlignment(SwingConstants.CENTER);
        no2TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        no2TextField.setOpaque(false);

        no2classTextField.setEditable(false);
        no2classTextField.setHorizontalAlignment(SwingConstants.CENTER);
        no2classTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        no2classTextField.setOpaque(false);

        no3TextField.setBackground(new java.awt.Color(255, 255, 204));
        no3TextField.setEditable(false);
        no3TextField.setHorizontalAlignment(SwingConstants.CENTER);
        no3TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        no3TextField.setOpaque(false);

        no3classTextField.setEditable(false);
        no3classTextField.setHorizontalAlignment(SwingConstants.CENTER);
        no3classTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        no3classTextField.setOpaque(false);

        no3Label.setText("号码3:");

        QQTextField.setBackground(new java.awt.Color(255, 255, 204));
        QQTextField.setEditable(false);
        QQTextField.setHorizontalAlignment(SwingConstants.CENTER);
        QQTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        QQTextField.setOpaque(false);

        QQLabel.setText("QQ/MSN:");

        EmailTextField.setBackground(new java.awt.Color(255, 255, 204));
        EmailTextField.setEditable(false);
        EmailTextField.setHorizontalAlignment(SwingConstants.CENTER);
        EmailTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        EmailTextField.setOpaque(false);

        EmailLabel.setText("Email:");

        remarksLabel.setText("备注:");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setOpaque(false);

        remarksTextArea.setBackground(new java.awt.Color(255, 255, 204));
        remarksTextArea.setColumns(20);
        remarksTextArea.setEditable(false);
        remarksTextArea.setFont(new java.awt.Font("宋体", 0, 12));
        remarksTextArea.setLineWrap(true);
        remarksTextArea.setRows(3);
        remarksTextArea.setOpaque(false);
        jScrollPane1.setViewportView(remarksTextArea);
        jScrollPane1.getViewport().setOpaque(false);

        QQButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/QQ.png"))); // NOI18N
        QQButton.setToolTipText("启动QQ聊天");
        QQButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        QQButton.setBorderPainted(false);
        QQButton.setContentAreaFilled(false);
        QQButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        emailButton.setAction(null);
        emailButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/email.png"))); // NOI18N
        emailButton.setToolTipText("发送邮件");
        emailButton.setContentAreaFilled(false);
        emailButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nameTextField.setEditable(false);
        nameTextField.setFont(new java.awt.Font("楷体_GB2312", 0, 24)); // NOI18N
        nameTextField.setForeground(new java.awt.Color(204, 0, 0));
        nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        nameTextField.setBorder(null);
        nameTextField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        nameTextField.setOpaque(false);

        birthdayLabel.setText("生日:");

        birthdayTextField.setBackground(new java.awt.Color(255, 255, 204));
        birthdayTextField.setEditable(false);
        birthdayTextField.setHorizontalAlignment(SwingConstants.CENTER);
        birthdayTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        birthdayTextField.setOpaque(false);

        constellationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        constellationLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(no1Label)
                                    .addComponent(hometownLabel)
                                    .addComponent(no2Label)
                                    .addComponent(no3Label)
                                    .addComponent(QQLabel)
                                    .addComponent(addressLabel)
                                    .addComponent(sexLabel)
                                    .addComponent(birthdayLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(no2TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                            .addComponent(no3TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                            .addComponent(QQTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                            .addComponent(no1TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                            .addComponent(EmailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                            .addComponent(birthdayTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(emailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(QQButton, 0, 0, Short.MAX_VALUE)
                                                .addComponent(no1classTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                                .addComponent(no2classTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                                .addComponent(no3classTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                                .addComponent(constellationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(addressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(hometownTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(sexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(classLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))))
                        .addGap(18, 18, 18))
                    .addComponent(EmailLabel)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(remarksLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexLabel)
                    .addComponent(sexTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classLabel)
                    .addComponent(classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hometownLabel)
                    .addComponent(hometownTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(no1Label)
                            .addComponent(no1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(no1classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(no2Label)
                            .addComponent(no2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(no3Label)
                            .addComponent(no3TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(QQLabel)
                            .addComponent(QQTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(QQButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(no2classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(no3classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(emailButton, 0, 0, Short.MAX_VALUE)
                            .addComponent(EmailTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(birthdayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthdayLabel)
                            .addComponent(constellationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(EmailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remarksLabel))
                .addContainerGap())
        );

        jScrollPane1.getViewport().setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        print.printFrame printFrame = new print.printFrame(parentFrame);
        System.out.println("打印 序号是：" + showDetailSerial);
        printFrame.setCurrentSQL("Select * From ADDRESSLISTTABLE where id='" + showDetailSerial + "'");
        this.dispose();
        printFrame.setEnabled(true);
        printFrame.setFocusable(true);
        printFrame.setFocusableWindowState(true);
        printFrame.setVisible(true);
//        this.setVisible(true);
    }//GEN-LAST:event_printButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JButton QQButton;
    private javax.swing.JLabel QQLabel;
    private javax.swing.JTextField QQTextField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JTextField birthdayTextField;
    private javax.swing.JLabel classLabel;
    private javax.swing.JTextField classTextField;
    private javax.swing.JLabel constellationLabel;
    private javax.swing.JButton emailButton;
    private javax.swing.JLabel hometownLabel;
    private javax.swing.JTextField hometownTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel no1Label;
    private javax.swing.JTextField no1TextField;
    private javax.swing.JTextField no1classTextField;
    private javax.swing.JLabel no2Label;
    private javax.swing.JTextField no2TextField;
    private javax.swing.JTextField no2classTextField;
    private javax.swing.JLabel no3Label;
    private javax.swing.JTextField no3TextField;
    private javax.swing.JTextField no3classTextField;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JTextField sexTextField;
    private javax.swing.JButton showDetailAboveButton;
    private javax.swing.JButton showDetailBelowButton;
    private javax.swing.JButton showDetailDeleteButton;
    private javax.swing.JButton showDetailEditButton;
    private javax.swing.JButton showDetailcloseButton;
    // End of variables declaration//GEN-END:variables

    private void showDetail(int serial) {
        setFocusable(true);
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (showDetailAboveButton.isEnabled()) {
                        aboveButtonAction();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (showDetailBelowButton.isEnabled()) {
                        belowButtonAction();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    if (showDetailEditButton.isEnabled()) {
                        editButtonAction();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    deleteButtonAction();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        showDetailSerial = serial;
        serial = serialManage(serial, 0);
        if (serialManage(showDetailSerial, 1) < 0) {
            showDetailBelowButton.setEnabled(false);
        }
        if (serialManage(showDetailSerial, -1) < 0) {
            showDetailAboveButton.setEnabled(false);
        }
        getContentPane().setBackground(new java.awt.Color(255, 255, 204));
        showDetailcloseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        showDetailAboveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aboveButtonAction();
            }
        });
        showDetailBelowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                belowButtonAction();
            }
        });
        showDetailDeleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonAction();
            }
        });
        showDetailEditButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editButtonAction();
            }
        });
        loadData(serial);
    }

    public void aboveButtonAction() {

        int i = serialManage(showDetailSerial, -1);
        if (i >= 0) {
            showDetailSerial = i;
            if (serialManage(showDetailSerial, -1) >= 0) {
                showDetailAboveButton.setEnabled(true);
            } else {
                showDetailAboveButton.setEnabled(false);
            }
            showDetailBelowButton.setEnabled(true);
            showDetailDeleteButton.setEnabled(true);
            showDetailEditButton.setEnabled(true);
            loadData(showDetailSerial);
        } else if (i == -1) {
            loadData(showDetailSerial);
            showDetailAboveButton.setEnabled(false);
            showDetailDeleteButton.setEnabled(false);
            showDetailEditButton.setEnabled(false);
        } else if (i == -3) {
            showDetailDeleteButton.setEnabled(false);
        }
    }

    public void belowButtonAction() {

        int i = serialManage(showDetailSerial, 1);
        if (i >= 0) {
            showDetailSerial = i;
            if (serialManage(showDetailSerial, 1) < 0) {
                showDetailBelowButton.setEnabled(false);
            } else {
                showDetailBelowButton.setEnabled(true);
            }
            showDetailAboveButton.setEnabled(true);
            showDetailDeleteButton.setEnabled(true);
            showDetailEditButton.setEnabled(true);
            loadData(showDetailSerial);
//                    showDetailDialoglabel.setText(detailToString(showDetailSerial));
        } else if (i == -2) {
            loadData(showDetailSerial);
//                    showDetailDialoglabel.setText(detailToString(showDetailSerial));
            showDetailBelowButton.setEnabled(false);
            showDetailDeleteButton.setEnabled(false);
            showDetailEditButton.setEnabled(false);
        } else if (i == -3) {
//                    showDetailDialoglabel.setText("<html><body><font size='6'><p><p><p><p><p>已删除！</font></body></html>");
            showDetailDeleteButton.setEnabled(false);
        }
    }

    public void deleteButtonAction() {

        int answer = JOptionPane.showConfirmDialog(null,
                "是否删除该记录？", "删除确认", JOptionPane.YES_NO_OPTION);
        if (answer == 0) {
            DataBase.SQLExecute("DELETE * FROM ADDRESSLISTTABLE WHERE ID='"
                    + showDetailSerial + "'");
            stateChanged = true;
            int i = serialManage(showDetailSerial, 1);
            if (i < 0) {
                i = serialManage(showDetailSerial, -1);
            }
            serialManage(showDetailSerial, 2);
            loadData(showDetailSerial);
            showDetailDeleteButton.setEnabled(false);
            showDetailEditButton.setEnabled(false);
            if (i >= 0) {
                showDetailSerial = i;
                loadData(showDetailSerial);
                showDetailDeleteButton.setEnabled(true);
            } else if (i == -2) {
                i = serialManage(showDetailSerial, -1);
                if (i >= 0) {
                    showDetailSerial = i;
                    loadData(showDetailSerial);
                    showDetailDeleteButton.setEnabled(true);
                }
            }
        }
    }

    public void editButtonAction() {
        ModifyDialog modificationDialog = new ModifyDialog(parentFrame, showDetailSerial, true);
        modificationDialog.setLocationRelativeTo(parentFrame);
        dispose();
        modificationDialog.setVisible(true);
        modificationDialog.setFocusable(true);
        loadData(showDetailSerial);
        setVisible(true);
        if (modificationDialog.getModifiedSuccessed()) {
            stateChanged = true;
        }
    }

    private int serialManage(int giveSerial, int mode) {
        int serial = 0;
        if (mode == 0) {
            try {
                String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
                Class.forName(DBDriver); // 加载驱动器
                Connection con = DriverManager.getConnection(DataBaseConnection.getConnectionStr()); // 连接数据库
                Statement stmt = con.createStatement(); // 创建Statement对象
                String SQLorder = currentSQLOrder;
                int i = 0;
                ResultSet rs = stmt.executeQuery(SQLorder);
                while (rs.next()) {
                    i++;
                }
                int serialArrayLength = i;
                serialArray = new int[serialArrayLength];
                rs = stmt.executeQuery(SQLorder);
                i = 0;
                while (rs.next()) {
                    serialArray[i++] = rs.getInt("ID");
                }
                stmt.close(); // 关闭语句
                con.close(); // 关闭连接
            } catch (Exception ex) {
                System.out.print("错误！！！");
            }
            serial = giveSerial;
        } else if (mode == -1) {// 上一个
            boolean flag = false;
            for (int i = serialArray.length - 1; i >= 0; i--) {
                if (serialArray[i] == giveSerial) {
                    flag = true;
                } else if (flag && serialArray[i] != -3) {
                    return serialArray[i];
                }
            }
            return -1;
        } else if (mode == 1) {// 下一个
            boolean flag = false;
            for (int i = 0; i < serialArray.length; i++) {
                if (serialArray[i] == giveSerial) {
                    flag = true;
                } else if (flag && serialArray[i] != -3) {
                    return serialArray[i];
                }
            }
            return -2;
        } else if (mode == 2) {// 删除
            for (int i = 0; i < serialArray.length; i++) {
                if (giveSerial == serialArray[i]) {
                    serialArray[i] = -3;
                    return -3;
                }
            }
        }
        return serial;
    }

    private void loadData(int serial) {
        AddressListData addressListData = new dataBase.DataBase().getAddressListDataByID(serial + "");
        String namesString = addressListData.getName();
        String sexString = addressListData.getSex();
        String peopleClass = addressListData.getXClass();
        String No1 = addressListData.getNo1();
        String No1Class = addressListData.getNo1Class();
        String No2 = addressListData.getNo2();
        String No2Class = addressListData.getNo2Class();
        String No3 = addressListData.getNo3();
        String No3Class = addressListData.getNo3Class();
        String Address = addressListData.getAddress();
        String HomeTown = addressListData.getHometown();
        qqString = addressListData.getQQ();
        EmailsString = addressListData.getEmail();
        String RemarkString = addressListData.getRemarks();
        String birthdayCalendarString = addressListData.getCalendarBirthday();
        String birthdayLunarString = addressListData.getLunarBirthday();

        System.out.println("详细信息：" + namesString + "," + sexString + "," + peopleClass + "," + No1 + "," + No2
                + "," + No3 + "," + Address + "," + HomeTown + "," + qqString + "," + EmailsString
                + "," + RemarkString + "," + birthdayCalendarString + "," + birthdayLunarString);

        if (namesString.equals("")) {
            nameTextField.setVisible(false);
        } else {
            nameTextField.setVisible(true);
            nameTextField.setText(namesString);
        }
        if (sexString.equals("")) {
            sexLabel.setVisible(false);
            sexTextField.setVisible(false);
        } else {
            sexLabel.setVisible(true);
            sexTextField.setVisible(true);
            sexTextField.setText(sexString);
        }
        classTextField.setText(peopleClass);
        if (Address.equals("")) {
            addressTextField.setVisible(false);
            addressLabel.setVisible(false);
        } else {
            addressTextField.setVisible(true);
            addressLabel.setVisible(true);
            addressTextField.setText(Address);
        }
        if (HomeTown.equals("")) {
            hometownLabel.setVisible(false);
            hometownTextField.setVisible(false);
        } else {
            hometownLabel.setVisible(true);
            hometownTextField.setVisible(true);
            hometownTextField.setText(HomeTown);
        }
        no1TextField.setText(No1);
        no1classTextField.setText(No1Class);
        if (No2.equals("")) {
            no2Label.setVisible(false);
            no2TextField.setVisible(false);
            no2classTextField.setVisible(false);
        } else {
            no2Label.setVisible(true);
            no2TextField.setVisible(true);
            no2classTextField.setVisible(true);
            no2TextField.setText(No2);
            no2classTextField.setText(No2Class);
        }
        if (No3.equals("")) {
            no3Label.setVisible(false);
            no3TextField.setVisible(false);
            no3classTextField.setVisible(false);
        } else {
            no3Label.setVisible(true);
            no3TextField.setVisible(true);
            no3classTextField.setVisible(true);
            no3TextField.setText(No3);
            no3classTextField.setText(No3Class);
        }
        if (qqString.equals("")) {
            QQLabel.setVisible(false);
            QQTextField.setVisible(false);
            QQButton.setVisible(false);
        } else {
            QQLabel.setVisible(true);
            QQTextField.setVisible(true);
            QQButton.setVisible(true);
            QQTextField.setText(qqString);
            ActionListener[] actionListener = QQButton.getActionListeners();
            for (int i = 0; i < actionListener.length; i++) {
                QQButton.removeActionListener(actionListener[i]);
            }
            QQButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String order = "cmd /c @start tencent://message/?uin=" + qqString;
                    try {
                        Runtime rt = Runtime.getRuntime();
                        rt.exec(order);
                    } catch (Throwable t) {
                    }
                }
            });
        }
        if (EmailsString.equals("")) {
            EmailLabel.setVisible(false);
            EmailTextField.setVisible(false);
            emailButton.setVisible(false);
        } else {
            ActionListener[] actionListener = emailButton.getActionListeners();
            for (int i = 0; i < actionListener.length; i++) {
                emailButton.removeActionListener(actionListener[i]);
            }
            EmailLabel.setVisible(true);
            EmailTextField.setVisible(true);
            emailButton.setVisible(true);
            EmailTextField.setText(EmailsString);

            emailButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    sendMailAction();
                }
            });
        }
        if (birthdayCalendarString.equals("") || birthdayCalendarString.equals("null")) {
            birthdayLabel.setVisible(false);
            birthdayTextField.setVisible(false);
            constellationLabel.setVisible(false);
        } else {
            birthdayLabel.setVisible(true);
            birthdayTextField.setVisible(true);
            constellationLabel.setVisible(true);
            String calString = null;
            String lunString = null;
            DateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
            Date calendarDate = null;
            Date lunarDate = null;
            try {
                calendarDate = format0.parse(birthdayCalendarString);
                lunarDate = format0.parse(birthdayLunarString);
            } catch (ParseException ex) {
            }
            DateFormat format1 = new SimpleDateFormat("yyyy年M月d日");
            calString = format1.format(calendarDate);
            lunString = format1.format(lunarDate);
            birthdayTextField.setText("阳历:" + calString + ",阴历:" + lunString);
            constellationLabel.setIcon(CalendarHandle.getConstellationIcon(calendarDate));
            constellationLabel.setToolTipText(CalendarHandle.getConstellationChineseNameByDate(calendarDate));
        }
        remarksTextArea.setEditable(false);
        if (RemarkString.equals("")) {
            remarksLabel.setVisible(false);
            jScrollPane1.setVisible(false);
            remarksTextArea.setVisible(false);
        } else {
            remarksLabel.setVisible(true);
            jScrollPane1.setVisible(true);
            remarksTextArea.setVisible(true);
            remarksTextArea.setText(RemarkString);
        }
        setSize(detailDialogDimension);
    }

    public boolean getStateChanged() {
        return stateChanged;
    }

    private void sendMailAction() {
        String Email = EmailsString;
        boolean defaultMailServerEnable = new ConfigManager().getDefaultMailServerEnable();
        if (defaultMailServerEnable) {
            try {
                URI uri = new URI("mailto:" + Email);
                Desktop.getDesktop().mail(uri);
            } catch (URISyntaxException ex) {
            } catch (IOException ex) {
            }
        } else {
            SendMailJDialog sendMailJFrame = new SendMailJDialog(parentFrame, true);
            sendMailJFrame.setSendTo(Email);
            sendMailJFrame.setLocationRelativeTo(parentFrame);
            sendMailJFrame.setVisible(true);
        }
    }

    void setSerial(int id) {
        showDetail(id);
    }
}
