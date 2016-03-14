package components;

import util.ConfigManager;
import mail.SendMailJDialog;
import ui.ADialog;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FilterDialog extends ADialog {

    private MainFrame parentFrame;
    private String[] classNameArray;
    private JCheckBox checkBox[];
    private JButton filterButton;
    private JButton emailButton;
    private JButton groupManageButton;
    private JButton filterBarExitButton;
    private JPanel checkBoxPanel;

    public FilterDialog(MainFrame frame) {
        super(frame, true);
        initComponents();
        parentFrame = frame;
    }

    private void initComponents() {
        setTitle("过滤");
        Container contentPane = getContentPane(); // 获得其内容面板
        contentPane.setLayout(new java.awt.BorderLayout(1, 2));
        checkBoxPanel = new JPanel();
        checkBoxPanel.setOpaque(false);
        refreshcheckBoxPanel();
        contentPane.add(checkBoxPanel, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        BoxLayout bottonPanelLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(bottonPanelLayout);
        buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "", TitledBorder.CENTER, TitledBorder.TOP));
        filterButton = new ui.AButton("过滤");
        filterButton.setActionCommand("过滤");
        filterButton.setToolTipText("数据过滤");
        filterButton.setText("过滤");
        filterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                boolean runnable = true;
                String s1 = "SELECT * FROM ADDRESSLISTTABLE WHERE";
                for (int i = 0; i < checkBox.length && runnable; i++) {
                    if (checkBox[i].isSelected()) {
                        s1 += "  CLASS='" + classNameArray[i] + "' ";
                        runnable = false;
                    } else {
                        continue;
                    }
                    for (int j = i + 1; j < checkBox.length; j++) {
                        if (checkBox[j].isSelected()) {
                            s1 += "OR  CLASS='" + classNameArray[j] + "' ";
                        }
                    }
                }

                s1 += " ORDER BY ID+10000";
                System.out.println(s1);
                parentFrame.adlMainTable.listShow(s1);
            }
        });

        buttonPanel.add(filterButton);
        emailButton = new ui.AButton("");
        emailButton.setToolTipText("发送群邮件");
        emailButton.setText("群邮件");
        emailButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                emailButtonAction();
            }
        });
        buttonPanel.add(emailButton);


        groupManageButton = new ui.AButton("");
        groupManageButton.setToolTipText("分组管理");
        groupManageButton.setText("分组管理");
        groupManageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                groupManageButtonAction();
            }
        });
        buttonPanel.add(groupManageButton);


        filterBarExitButton = new ui.AButton("退出");
        filterBarExitButton.setToolTipText("退出");
        filterBarExitButton.setText("退出");
        filterBarExitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                filterButton.setEnabled(true);
                dispose();
            }
        });
        buttonPanel.add(filterBarExitButton);
        contentPane.add(buttonPanel, BorderLayout.EAST);
        setUndecorated(true);
        pack();
    }

    private void groupManageButtonAction() {
//        dispose();
        GroupManageDialog classAdminDialog = new GroupManageDialog(parentFrame, true, 1);
        classAdminDialog.setLocationRelativeTo(parentFrame);
        classAdminDialog.setVisible(true);
        refreshcheckBoxPanel();
    }

    private void emailButtonAction() {
        boolean runnable = true;
        String s1 = "SELECT * FROM ADDRESSLISTTABLE WHERE";
        for (int i = 0; i < checkBox.length && runnable; i++) {
            if (checkBox[i].isSelected()) {
                s1 += "  CLASS='" + classNameArray[i] + "' ";
                runnable = false;
            } else {
                continue;
            }
            for (int j = i + 1; j < checkBox.length; j++) {
                if (checkBox[j].isSelected()) {
                    s1 += "OR  CLASS='" + classNameArray[j] + "' ";
                }
            }
        }

        s1 += " ORDER BY ID+10000";
        System.out.println(s1);
        String Email = new dataBase.DataBase().getEmailsFromDatabase(s1);
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
            int x = (parentFrame.getWidth() - sendMailJFrame.getWidth()) / 2 + parentFrame.getX();
            int y = (parentFrame.getHeight() - sendMailJFrame.getHeight()) / 2 + parentFrame.getY();
            sendMailJFrame.setLocation(x, y);
            sendMailJFrame.setVisible(true);
        }
    }

    private void refreshcheckBoxPanel() {
        checkBoxPanel.removeAll();
        checkBoxPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "", TitledBorder.CENTER, TitledBorder.TOP));
        classNameArray = new dataBase.DataBase().getGroupNameArray();
        int classCount = classNameArray.length;
        GridLayout buttonPanelLayout = new GridLayout(4, classCount / 4, 10, 2);
        checkBoxPanel.setLayout(buttonPanelLayout);
        checkBox = new JCheckBox[classCount];
        for (int i = 0; i < classCount; i++) {
            checkBox[i] = new JCheckBox(classNameArray[i]);
            checkBox[i].setOpaque(false);
            checkBoxPanel.add(checkBox[i]);
        }
        checkBoxPanel.validate();
    }
}
