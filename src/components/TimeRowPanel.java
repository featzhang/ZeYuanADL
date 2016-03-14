package components;

import dataBase.AddressListData;
import java.awt.Cursor;
import java.util.Date;
import javax.swing.JFrame;
import util.CalendarHandle;

public class TimeRowPanel extends javax.swing.JPanel {

    private AddressListData adld = null;
    private static int counter = 0;
    private int id;
    private JFrame mainFrame = null;

    public TimeRowPanel(JFrame frame) {
        this(frame, null);
    }

    public TimeRowPanel(JFrame frame, AddressListData adld) {
        mainFrame = frame;
        initComponents();
        setADLData(adld);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        tipLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setOpaque(false);

        nameLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameLabelMouseClicked(evt);
            }
        });

        tipLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tipLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameLabelMouseClicked
        actionPerformance();
    }//GEN-LAST:event_nameLabelMouseClicked

    private void tipLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipLabelMouseClicked
        actionPerformance();
    }//GEN-LAST:event_tipLabelMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel tipLabel;
    // End of variables declaration//GEN-END:variables

    public void setADLData(AddressListData adld) {
        if (adld == null) {
            return;
        }
        this.adld = adld;
        counter++;
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        nameLabel.setCursor(cursor);
        tipLabel.setCursor(cursor);
        id = Integer.parseInt(adld.getID());
        String name = adld.getName();
        String calendarBirthday = adld.getCalendarBirthday();
        String newString = CalendarHandle.parseStringFormatYYYYMM(calendarBirthday);
        Date date = CalendarHandle.getLunarCalendar(calendarBirthday);
        String parseDateToString = CalendarHandle.parseDateToString(date);
        String parseStringFormat = CalendarHandle.parseStringFormatYYYYMM(parseDateToString);
        nameLabel.setText(name);
        tipLabel.setText("阳历:" +newString + " 农历:" + parseStringFormat);
        System.out.println(name);
    }

    public int getId() {
        return id;
    }

    public void setCounter(int counter) {
        TimeRowPanel.counter = counter;
    }

    public static int getCounter() {
        return counter;
    }

    private void actionPerformance() {
        if (adld != null) {
            System.out.println("详细信息 序号是：" + id);
            new DetailDialog(mainFrame, id);
        }
    }
}
