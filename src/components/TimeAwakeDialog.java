package components;

import dataBase.AddressListData;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.UIManager;
import ui.ImagePanel;
import util.AResource;
import util.CalendarHandle;

public class TimeAwakeDialog extends JDialog {

    javax.swing.JFrame parentFrame = null;
    private int year = -1;
    private int month = -1;
    private int day = -1;
    private int oldYear = -1;
    private int oldMonth = -1;
    private int oldDay = -1;
    private int monthLunar = -1;
    private int dayLunar = -1;
    private Color labelEnableColor = Color.red;
    private Color labelUnableColor = Color.black;

    public TimeAwakeDialog(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        parentFrame = parent;
        setUndecorated(true);
        initComponents();
        loadData();
        refreshTimeRowPanel();
        loadAction();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new ImagePanel(AResource.getImageIcon("background"));
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        signTopLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        timeShowPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        leftLabel = new javax.swing.JLabel();
        monthCalendarLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        dayCalendarLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        monthLunarLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        dayLunarLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        rightLabel = new javax.swing.JLabel();
        todayLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setOpaque(false);

        jLabel1.setText("生日提醒");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/down.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        signTopLabel.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(signTopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(signTopLabel))
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        timeShowPanel.setOpaque(false);
        timeShowPanel.setLayout(new javax.swing.BoxLayout(timeShowPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(timeShowPanel);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        leftLabel.setForeground(new java.awt.Color(255, 51, 102));
        leftLabel.setText("←");
        leftLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        monthCalendarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthCalendarLabel.setText(" ");
        monthCalendarLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 102), null));
        monthCalendarLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        monthCalendarLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setText("月");

        dayCalendarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayCalendarLabel.setText(" ");
        dayCalendarLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 102), null));
        dayCalendarLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dayCalendarLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setText("日");

        jLabel9.setText("农历");

        monthLunarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthLunarLabel.setText(" ");
        monthLunarLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 102), null));
        monthLunarLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        monthLunarLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setText("月");

        dayLunarLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayLunarLabel1.setText(" ");
        dayLunarLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 102), null));
        dayLunarLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dayLunarLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setText("日");

        rightLabel.setForeground(new java.awt.Color(255, 51, 51));
        rightLabel.setText("→");
        rightLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        todayLabel1.setText("今天");
        todayLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(leftLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todayLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthCalendarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dayCalendarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthLunarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dayLunarLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightLabel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(leftLabel)
                .addComponent(monthCalendarLabel)
                .addComponent(jLabel5)
                .addComponent(dayCalendarLabel)
                .addComponent(jLabel7)
                .addComponent(todayLabel1)
                .addComponent(jLabel9)
                .addComponent(monthLunarLabel)
                .addComponent(jLabel11)
                .addComponent(dayLunarLabel1)
                .addComponent(jLabel13)
                .addComponent(rightLabel))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TimeAwakeDialog dialog = new TimeAwakeDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dayCalendarLabel;
    private javax.swing.JLabel dayLunarLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel leftLabel;
    private javax.swing.JLabel monthCalendarLabel;
    private javax.swing.JLabel monthLunarLabel;
    private javax.swing.JLabel rightLabel;
    private javax.swing.JLabel signTopLabel;
    private javax.swing.JPanel timeShowPanel;
    private javax.swing.JLabel todayLabel1;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        parseLunarDate();
        showLabelText();
    }

    private boolean isDateChanged() {
        if (year == oldYear && month == oldMonth && day == oldDay) {
            return false;
        }
        return true;
    }

    private void refreshTimeRowPanel() {
        String[] cds = new String[7];
        String[] lds = new String[7];
        timeShowPanel.removeAll();
        timeShowPanel.repaint();
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            Date date = calendar.getTime();
            String calendarDateString = CalendarHandle.parseDateToString(date);
            Date lunarDate = CalendarHandle.getLunarCalendar(calendarDateString);
            String lunarDateString = CalendarHandle.parseDateToString(lunarDate);
            calendarDateString = calendarDateString.substring(calendarDateString.indexOf("-") + 1, calendarDateString.length());
            lunarDateString = lunarDateString.substring(lunarDateString.indexOf("-") + 1, lunarDateString.length());
            cds[i] = calendarDateString;
            lds[i] = lunarDateString;
        }
        String sqlOrder = "SELECT * FROM ADDRESSLISTTABLE WHERE [Birthday] like '%" + cds[0] + "' OR [BirthdayLunar] like '%" + lds[0] + "'"
                + "OR [Birthday] like '%" + cds[1] + "' OR [BirthdayLunar] like '%" + lds[1] + "'"
                + "OR [Birthday] like '%" + cds[2] + "' OR [BirthdayLunar] like '%" + lds[2] + "'"
                + "OR [Birthday] like '%" + cds[3] + "' OR [BirthdayLunar] like '%" + lds[3] + "'"
                + "OR [Birthday] like '%" + cds[4] + "' OR [BirthdayLunar] like '%" + lds[4] + "'"
                + "OR [Birthday] like '%" + cds[5] + "' OR [BirthdayLunar] like '%" + lds[5] + "'"
                + "OR [Birthday] like '%" + cds[6] + "' OR [BirthdayLunar] like '%" + lds[6] + "'";
        ArrayList aDLDatas = dataBase.DataBase.getAddressListDatasBySQL(sqlOrder);
        if (aDLDatas != null) {
            for (int j = 0; j < aDLDatas.size(); j++) {
                AddressListData aDLData = (AddressListData) aDLDatas.get(j);
                TimeRowPanel timeRowPanel = new TimeRowPanel(parentFrame);
                timeRowPanel.setADLData(aDLData);
                timeShowPanel.add(timeRowPanel);
//                System.out.println(aDLData.toString());
            }
        }
        final int counter = TimeRowPanel.getCounter();
        for (int i = counter; i < 5; i++) {
            TimeRowPanel timeRowPanel = new TimeRowPanel(parentFrame);
            timeShowPanel.add(timeRowPanel);
        }
        timeShowPanel.revalidate();//重新验证
        timeShowPanel.repaint(1);
        oldDay = day;
        oldMonth = month;
        oldYear = year;
        signTopLabel.setText(CalendarHandle.getConstellationChineseNameByDate(new Date(year, month, day)));
    }

    private void parseLunarDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        Date date = calendar.getTime();
        String calendarDateString = CalendarHandle.parseDateToString(date);
        Date lunarDate = CalendarHandle.getLunarCalendar(calendarDateString);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(lunarDate);
        calendar1.get(Calendar.YEAR);
        monthLunar = calendar1.get(Calendar.MONTH) + 1;
        dayLunar = calendar1.get(Calendar.DAY_OF_MONTH);
    }

    private void loadAction() {
        todayLabel1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setAllLabelUnable();
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                if (isDateChanged()) {
                    refreshTimeRowPanel();
                    parseLunarDate();
                    showLabelText();
                }
            }
        });
        monthCalendarLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setAllLabelUnable();
                monthCalendarLabel.setForeground(labelEnableColor);
            }
        });
        dayCalendarLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setAllLabelUnable();
                dayCalendarLabel.setForeground(labelEnableColor);
            }
        });
        monthLunarLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setAllLabelUnable();
                monthLunarLabel.setForeground(labelEnableColor);
            }
        });
        dayLunarLabel1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setAllLabelUnable();
                dayLunarLabel1.setForeground(labelEnableColor);
            }
        });
        leftLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (dayCalendarLabel.getForeground() == labelEnableColor
                        || dayLunarLabel1.getForeground() == labelEnableColor) {
                    dateRoll(Calendar.DAY_OF_MONTH, -1);
                    parseLunarDate();
                    showLabelText();
                    refreshTimeRowPanel();
                } else if (monthCalendarLabel.getForeground() == labelEnableColor
                        || monthLunarLabel.getForeground() == labelEnableColor) {

                    dateRoll(Calendar.MONTH, -1);
                    parseLunarDate();
                    showLabelText();
                    refreshTimeRowPanel();
                } else {
                    dateRoll(Calendar.DAY_OF_MONTH, -1);
                    parseLunarDate();
                    showLabelText();
                    refreshTimeRowPanel();
                }
            }
        });
        rightLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (dayCalendarLabel.getForeground() == labelEnableColor
                        || dayLunarLabel1.getForeground() == labelEnableColor) {
                    dateRoll(Calendar.DAY_OF_MONTH, 1);
                    parseLunarDate();
                    showLabelText();
                    refreshTimeRowPanel();
                } else if (monthCalendarLabel.getForeground() == labelEnableColor
                        || monthLunarLabel.getForeground() == labelEnableColor) {

                    dateRoll(Calendar.MONTH, 1);
                    parseLunarDate();
                    showLabelText();
                    refreshTimeRowPanel();
                } else {
                    dateRoll(Calendar.DAY_OF_MONTH, 1);
                    parseLunarDate();
                    showLabelText();
                    refreshTimeRowPanel();
                }
            }
        });

    }

    private void showLabelText() {
        monthCalendarLabel.setText(month + "");
        dayCalendarLabel.setText(day + "");
        monthLunarLabel.setText(monthLunar + "");
        dayLunarLabel1.setText(dayLunar + "");
    }

    private void setAllLabelUnable() {
        monthCalendarLabel.setForeground(labelUnableColor);
        dayCalendarLabel.setForeground(labelUnableColor);
        monthLunarLabel.setForeground(labelUnableColor);
        dayLunarLabel1.setForeground(labelUnableColor);
    }

    private void dateRoll(int m, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        calendar.add(m, i);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
}
