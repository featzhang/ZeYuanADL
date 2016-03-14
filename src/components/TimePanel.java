package components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class TimePanel extends javax.swing.JPanel {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TimePanel() {
        initComponents();
        loadData();
        loadAction();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel shengriLabel = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        monthComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        dayComboBox = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        lunLabel = new javax.swing.JLabel();

        setOpaque(false);

        shengriLabel.setText("生日");

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("年");

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("月");

        dayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("日");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(shengriLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lunLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(shengriLabel)
                .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4)
                .addComponent(lunLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox dayComboBox;
    private javax.swing.JLabel lunLabel;
    private javax.swing.JComboBox monthComboBox;
    private javax.swing.JComboBox yearComboBox;
    // End of variables declaration//GEN-END:variables
    private int year = -1;
    private int month = -1;
    private int day = -1;
    private String defaultLunarFieldString = "                 ";

    private void loadData() {
        yearComboBox.removeAllItems();
        yearComboBox.addItem("");
        for (int i = 1980; i < 2012; i++) {
            yearComboBox.addItem("" + i);
        }
        for (int i = 1980; i > 1940; i--) {
            yearComboBox.addItem("" + i);
        }
        monthComboBox.removeAllItems();
        monthComboBox.addItem("");
        for (int i = 1; i < 13; i++) {
            monthComboBox.addItem("" + i);
        }
        dayComboBox.removeAllItems();
        dayComboBox.addItem("");
        lunLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lunLabel.setText(defaultLunarFieldString);
    }

    private void loadAction() {
        monthComboBox.addItemListener(new Year_MonthItemListener());
        yearComboBox.addItemListener(new Year_MonthItemListener());
        dayComboBox.addItemListener(new DayItemListener());
    }

    private class Year_MonthItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (yearComboBox.getSelectedIndex() > 0
                    && monthComboBox.getSelectedIndex() > 0) {//选择了数字
                int yearInt = Integer.parseInt(yearComboBox.getSelectedItem().toString().trim());
                int monthInt = Integer.parseInt(monthComboBox.getSelectedItem().toString().trim());
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.set(yearInt, monthInt, 1);
                calendar.add(java.util.Calendar.DAY_OF_MONTH, -1);
                int dayCount = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                dayComboBox.removeAllItems();
                dayComboBox.addItem("");
                for (int i = 1; i <= dayCount; i++) {
                    dayComboBox.addItem("" + i);
                }
                year = yearInt;
                month = monthInt;
                if (day != -1) {
                    dayComboBox.setSelectedItem("" + day);
                }
            } else if (yearComboBox.getSelectedIndex() == 0
                    || monthComboBox.getSelectedIndex() == 0) {//选择了空白
                if (yearComboBox.getSelectedIndex() == 0) {
                    month = Integer.parseInt(monthComboBox.getSelectedItem().toString().trim());
                } else {
                    year = Integer.parseInt(yearComboBox.getSelectedItem().toString().trim());
                }
                dayComboBox.removeAllItems();
                dayComboBox.addItem("");
                dayComboBox.setSelectedIndex(0);
            }
        }
    }

    private class DayItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (dayComboBox.getSelectedIndex() > 0) {
                int yearInt = Integer.parseInt(yearComboBox.getSelectedItem().toString().trim());
                int monthInt = Integer.parseInt(monthComboBox.getSelectedItem().toString().trim());
                int dayInt = Integer.parseInt(dayComboBox.getSelectedItem().toString().trim());
                year = yearInt;
                month = monthInt;
                day = dayInt;
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.set(year, month - 1, day);
                Date date = calendar.getTime();
                Date lunarCalendar = util.CalendarHandle.getLunarCalendar(date);
                DateFormat simpleFormatter = new SimpleDateFormat("农历:yyyy月M月d日");
                String lunarString = simpleFormatter.format(lunarCalendar);
                lunLabel.setText(lunarString);
            } else {
                lunLabel.setText(defaultLunarFieldString);
            }
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date getCalendarDate() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        if (yearComboBox.getSelectedIndex() == 0 || monthComboBox.getSelectedIndex() == 0 || dayComboBox.getSelectedIndex() == 0) {
            return null;
        }
        int yearInt = Integer.parseInt(yearComboBox.getSelectedItem().toString().trim());
        int monthInt = Integer.parseInt(monthComboBox.getSelectedItem().toString().trim());
        int dayInt = Integer.parseInt(dayComboBox.getSelectedItem().toString().trim());
        year = yearInt;
        month = monthInt;
        day = dayInt;
        calendar.set(year, month - 1, day);
        Date date = calendar.getTime();
        return date;
    }

    public String getCalendarDateString() {
        Date date = getCalendarDate();
        if (date == null) {
            return "";
        }
        String format = dateFormat.format(date);
        return format;
    }

    public Date getLunarData() {
        Date date = getCalendarDate();
        if (date == null) {
            return null;
        }
        Date lunarCalendar = util.CalendarHandle.getLunarCalendar(date);
        return lunarCalendar;
    }

    public String getLunarDataString() {
        Date date = getCalendarDate();
        if (date == null) {
            return "";
        }
        Date lunarCalendar = util.CalendarHandle.getLunarCalendar(date);
        String format = dateFormat.format(lunarCalendar);
        return format;
    }

    public void setDate(Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        year = calendar.get(java.util.Calendar.YEAR);
        month = calendar.get(java.util.Calendar.MONTH) + 1;
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        yearComboBox.setSelectedItem("" + year);
        monthComboBox.setSelectedItem("" + month);
        dayComboBox.setSelectedItem("" + day);
    }

    public void setDate(String dateString) {
        if (dateString.equals("") || dateString.equals("null")) {
            return;
        }
        try {
            Date date = dateFormat.parse(dateString);
            setDate(date);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "日期字符输入格式错误！");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame jFrame = new JFrame("测试");
        TimePanel timePanel = new TimePanel();
        jFrame.add(timePanel);
        java.util.Calendar calendar = java.util.Calendar.getInstance();
//        calendar.add(java.util.Calendar.YEAR, -1);
        Date time = calendar.getTime();
        timePanel.setDate(time);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
