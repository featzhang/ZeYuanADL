package Clock;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class CalendarCompute { // 1900年1月1日是星期一

    public int daysofMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return isLeapYear(year) ? 29 : 28;
        }
        return 30;
    }

    public int daysofYear(int year) {
        if (isLeapYear(year)) {
            return 366;
        } else {
            return 365;
        }
    }

    public boolean isLeapYear(int year) {
        boolean l = false;
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                l = true;
            }
        } else if (year % 4 == 0) {
            l = true;
        }
        return l;
    }

    public int month_firstDayOfWeek(int year, int month) {
        int daysCount = 0;
        for (int j = 1900; j < year; j++) {
            daysCount += isLeapYear(j) ? 366 : 365;
            daysCount %= 7;
        }
        for (int i = 1; i < month; i++) {
            daysCount += daysofMonth(year, i);
            daysCount %= 7;
        }
        daysCount += 1;
        daysCount = (daysCount + 7) % 7 + 1;
        // System.out.println(year+"年的"+month+"月的第一天是一星期中的第"+daysCount+"天");
        return daysCount;
    }
}

public class ChineseCalendar extends JDialog {

    private static final long serialVersionUID = 5876217945977329336L;
    private int chineseCalenderFrameX, chineseCalenderFrameY;
    private final int dayPanelCount = 42;
    private JPanel topPanel = new JPanel();
    private JPanel topPanel1 = new JPanel();
    private JPanel topPanel2 = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel center1Panel = new JPanel();
    private JPanel center2Panel = new JPanel();
    private JLabel gongyuanLabel = new JLabel("公元");
    private JComboBox yearComboBox = new JComboBox();
    private JLabel nianLabel = new JLabel("年");
    private JComboBox monthComboBox = new JComboBox();
    private JLabel yueLabel = new JLabel("月");
    private JComboBox dayComboBox = new JComboBox();
    private JLabel riLabel = new JLabel("日");
    private JLabel message1Label = new JLabel("        信息1           ");
    private JButton exitButton = new JButton("关闭");
    private JLabel message2Label = new JLabel("            信息2         ");
    private JButton lastYearButton = new JButton("上年");
    private JButton nextYearButton = new JButton("下年");
    private JButton lastMonthButton = new JButton("上月");
    private JButton nextMonthButton = new JButton("下月");
    private JButton todayButton = new JButton("今日");
    private boolean isMouseDraging = false;
    private int dialogX, dialogY;
    private JLabel[] dayLabel1 = new JLabel[dayPanelCount];
    private JLabel[] dayLabel2 = new JLabel[dayPanelCount];
    private JPanel[] dayPanel = new JPanel[dayPanelCount];
    private final String[] weekString = {"日(Sun)", "一(Mon)", "二(Tue)", "三(Wed)",
        "四(Thu)", "五(Fri)", "六(Sat)"};
    private final String[] monthString = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
        "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private int year_num, month_num, day_num;
    private Calendar currentSetCalendar = Calendar.getInstance(); // 实例化Calendar
    private final int year_max = 2050, year_min = 1900;
    private Lunar lunar = new Lunar();
    private int showDay = 1;

    public ChineseCalendar(Frame owner, String Title, boolean model) {
        super(owner, Title, model);
        Container contentPane = getContentPane();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel1.add(gongyuanLabel);
        topPanel1.add(yearComboBox);
        topPanel1.add(nianLabel);
        topPanel1.add(monthComboBox);
        topPanel1.add(yueLabel);
        topPanel1.add(dayComboBox);
        topPanel1.add(riLabel);
        message1Label.setForeground(Color.red);
        topPanel1.add(message1Label);
        topPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
        topPanel.add(topPanel1, java.awt.BorderLayout.NORTH);
        topPanel2.setBackground(Color.WHITE);
        message2Label.setFont(new Font("", Font.BOLD, 15));
        topPanel2.add(message2Label);
        topPanel2.add(lastYearButton);
        topPanel2.add(nextYearButton);
        topPanel2.add(lastMonthButton);
        topPanel2.add(nextMonthButton);
        topPanel2.add(todayButton);
        topPanel2.add(exitButton);
        topPanel2.setBorder(BorderFactory.createEtchedBorder());
//        topPanel.add(topPanel2, java.awt.BorderLayout.SOUTH);
        contentPane.add(topPanel, java.awt.BorderLayout.NORTH);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        center1Panel.setLayout(new GridLayout(1, 7, 1, 1));
        center1Panel.setBorder(BorderFactory.createEtchedBorder());
        for (int i = 0; i < 7; i++) {
            dayPanel[i] = new JPanel();
            dayLabel1[i] = new JLabel(weekString[i], SwingConstants.CENTER);
            dayPanel[i].add(dayLabel1[i]);
            dayPanel[i].setBorder(BorderFactory.createCompoundBorder());
            center1Panel.add(dayPanel[i]);
        }
        dayLabel1[0].setForeground(Color.red);
        center1Panel.setBackground(Color.white);
        centerPanel.add(center1Panel);
        center2Panel.setLayout(new java.awt.GridLayout(dayPanelCount / 7, 7, 1,
                1));
        for (int i = 0; i < dayPanelCount; i++) {
            dayPanel[i] = new JPanel();
            dayPanel[i].setLayout(new BoxLayout(dayPanel[i], BoxLayout.Y_AXIS));
            dayPanel[i].setBackground(Color.WHITE);
            dayLabel1[i] = new JLabel("" + i, SwingConstants.CENTER);
            dayLabel1[i].setFont(new Font("", Font.BOLD, 25));
            dayLabel2[i] = new JLabel("显示农历", SwingConstants.CENTER);
            dayPanel[i].add(dayLabel1[i]);
            dayPanel[i].add(dayLabel2[i]);
            center2Panel.add(dayPanel[i]);
            dayLabel1[i].addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    String s = label.getText();
                    day_num = Integer.parseInt(s);
                    refresh();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        centerPanel.setBorder(BorderFactory.createEtchedBorder());
        centerPanel.add(center2Panel, java.awt.BorderLayout.CENTER);
        centerPanel.add(topPanel2, java.awt.BorderLayout.SOUTH);
        contentPane.add(centerPanel, java.awt.BorderLayout.CENTER);
        init();
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                isMouseDraging = true;
                dialogX = e.getX();
                dialogY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMouseDraging = false;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isMouseDraging) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    setLocation(left + e.getX() - dialogX, top + e.getY()
                            - dialogY);
                }
            }
        });
        lastYearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                year_num--;
                if (year_num <= year_min) {
                    year_num = year_min;
                }
                refresh();
            }
        });
        nextYearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                year_num++;
                if (year_num > year_max) {
                    year_num = year_max;
                }
                refresh();
            }
        });
        lastMonthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                month_num--;
                if (month_num == -1) {
                    year_num--;
                    month_num = 11;
                }
                if (year_num < year_min) {
                    year_num = year_min;
                    month_num = 0;
                }
                refresh();
            }
        });
        nextMonthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                month_num++;
                if (month_num == 12) {
                    year_num++;
                    month_num = 0;
                }
                if (year_num > year_max) {
                    year_num = year_max;
                    month_num = 11;
                }
                refresh();
            }
        });
        todayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentSetCalendar.clear();
                currentSetCalendar = Calendar.getInstance();
                year_num = currentSetCalendar.get(Calendar.YEAR);
                month_num = currentSetCalendar.get(Calendar.MONTH);
                day_num = currentSetCalendar.get(Calendar.DATE);
                // System.out.println(year_num);
                refresh();
            }
        });
        yearComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(e.getItem());
                    year_num = Integer.parseInt(e.getItem().toString());
                    refresh();
                }
            }
        });
        monthComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    month_num = Integer.parseInt(e.getItem().toString()) - 1;
                    refresh();
                }
            }
        });
        dayComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    day_num = Integer.parseInt(e.getItem().toString());
                    refresh();
                }
            }
        });
        setUndecorated(true);
        pack();
        if (owner == null) {
            chineseCalenderFrameX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2;
            chineseCalenderFrameY = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        } else {
            chineseCalenderFrameX = (owner.getWidth() - getWidth()) / 2
                    + owner.getX();
            chineseCalenderFrameY = (owner.getHeight() - getHeight()) / 2
                    + owner.getY();
        }
        setAlwaysOnTop(true);
        setLocation(chineseCalenderFrameX, chineseCalenderFrameY);
    }

    public String getAdditionalMessage(int year_num, int month_num, int day_num) {
        String lunardayString = lunar.getLunarDateString(year_num,
                month_num + 1, day_num);
        return lunardayString;
    }

    public String getTipMessage(int year_num, int month_num, int day_num) {
        String s = "<html><body>";
        s += year_num + "年" + (month_num + 1) + "月" + day_num + "日<br>";

        int i = (use(year_num, month_num) + day_num - 2) % 7;
        // System.out.println(i);
        s += "星期" + weekString[i] + "<br>";
        Lunar mylunar = new Lunar(year_num, month_num + 1, day_num);
        int chineseDay = mylunar.getChineseDay();
        s += "农历：" + mylunar.getChineseMonthString() + "月"
                + Lunar.getChinaDayString(chineseDay) + "<br>";
        s += mylunar.cyclical() + "年";
        s += "<body><html>";
        return s;
    }

    private void init() {
        for (int i = year_min; i <= year_max; i++) {
            yearComboBox.addItem(i);
            // System.out.println(i);
        }
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }
        monthComboBox.setMaximumRowCount(12);
        for (int i = 0; i < dayLabel1.length; i++) {
            if (i % 7 == 0 || i % 7 == 6) {
                dayLabel1[i].setForeground(Color.red);
            }
        }
        year_num = currentSetCalendar.get(Calendar.YEAR);
        month_num = currentSetCalendar.get(Calendar.MONTH);
        day_num = currentSetCalendar.get(Calendar.DATE);
        // System.out.println(now.get(Calendar.DAY_OF_WEEK));
        refresh();
    }

    public void refresh() {
        yearComboBox.setSelectedItem(year_num);
        monthComboBox.setSelectedIndex(month_num);
        int daysofMonth = new CalendarCompute().daysofMonth(year_num,
                month_num + 1);
        for (int i = 0; i < daysofMonth; i++) {
            dayComboBox.addItem(i + 1);
        }
        dayComboBox.setSelectedItem(day_num);
        lunar.lunarInit(year_num, month_num + 1, day_num);
        String animalsYear = lunar.animalsYear();
        String ganZhiString = lunar.cyclical();
        if (year_num > 1949) {
            message1Label.setText("建国" + (year_num - 1949) + "周年   农历:"
                    + animalsYear + "年   【" + ganZhiString + "】年");
        } else {
            message1Label.setText("            农历:" + animalsYear + "年   【"
                    + ganZhiString + "】年");
        }
        message2Label.setText("" + year_num + " " + monthString[month_num]);
        int firstDayofweek = use(year_num, month_num) - 1;
        showDay = 1;
        for (int i = 0; i < dayLabel1.length; i++) {
            if (i < firstDayofweek || showDay > daysofMonth) {
                dayLabel1[i].setText("");
                dayLabel2[i].setText("");
                Color NotSelectcolor = Color.WHITE;
                dayLabel1[i].setBackground(NotSelectcolor);
                dayLabel2[i].setBackground(NotSelectcolor);
                dayPanel[i].setBackground(NotSelectcolor);
                dayLabel2[i].setText("");
                dayPanel[i].setToolTipText(null);
            } else {

                if (day_num == showDay) {
                    Color selectColor = new Color(108, 255, 200);
                    dayLabel1[i].setBackground(selectColor);
                    dayLabel2[i].setBackground(selectColor);
                    dayPanel[i].setBackground(selectColor);
                } else {
                    Color color = Color.WHITE;
                    dayLabel1[i].setBackground(color);
                    dayLabel2[i].setBackground(color);
                    dayPanel[i].setBackground(color);
                }
                dayLabel1[i].setText(showDay + "");
                dayLabel2[i].setText(getAdditionalMessage(year_num, month_num,
                        showDay));
                dayPanel[i].setToolTipText(getTipMessage(year_num, month_num,
                        showDay));
                showDay++;
            }
        }
    }

    public int use(int reyear, int remonth) {
        int week_num;// 返回星期几 1~7
        currentSetCalendar.set(reyear, remonth, 1); // 设置时间为所要查询的年月的第一天
        week_num = (currentSetCalendar.get(Calendar.DAY_OF_WEEK));// 得到第一天的星期
        return week_num;
    }
}
