package Diary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

public class DiaryPanel extends JPanel {

    private static final long serialVersionUID = 5876217945977329336L;
    private final int dayPanelCount = 42;
    private JComboBox yearComboBox;
    private JComboBox monthComboBox;
    private JComboBox dayComboBox;
    private JLabel guideLabel;
    private JLabel todayLabel;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private final String yearSuffix = DiaryResource.getLabel("year");
    private final String monthSuffix = DiaryResource.getLabel("month");
    private final String daySuffix = DiaryResource.getLabel("day");
    private final String lunarSuffix = DiaryResource.getLabel("lunar");
    private final String xingQiSuffix = DiaryResource.getLabel("xingQi");
    private final String[] weekString = {"»’(Sun)", "“ª(Mon)", "∂˛(Tue)", "»˝(Wed)",
        "Àƒ(Thu)", "ŒÂ(Fri)", "¡˘(Sat)"};
    private int year_num, month_num, day_num;
    private Calendar currentSetCalendar = Calendar.getInstance();
    private final int year_max = 2050, year_min = 1900;
    private Lunar lunar = new Lunar();
    private int showDay = 1;
    private CellPanel[] cellPanel;
    private NotePadPanel notePadPanel = null;
    private CalendarPanel calendarPanel = null;
    private Image backgroundImage = null;

    public DiaryPanel() {
        notePadPanel = new NotePadPanel();
        calendarPanel = new CalendarPanel();
        JSplitPane splitPane = new JSplitPane();
        splitPane.setRightComponent(notePadPanel);
        splitPane.setLeftComponent(calendarPanel);
        splitPane.setOpaque(false);
        add(splitPane);
    }

    public DiaryPanel(ImageIcon photo) {
        this();
        backgroundImage = photo.getImage();
    }

    public String getAdditionalMessage(int year_num, int month_num, int day_num) {
        String lunardayString = lunar.getLunarDateString(year_num,
                month_num + 1, day_num);
        return lunardayString;
    }

    public String getTipMessage(int year_num, int month_num, int day_num) {
        String s = "<html><body>";
        s += year_num + yearSuffix + (month_num + 1) + monthSuffix + day_num + daySuffix + "<br>";

        int i = (use(year_num, month_num) + day_num - 2) % 7;
        // System.out.println(i);
        s += xingQiSuffix + weekString[i] + "<br>";
        Lunar mylunar = new Lunar(year_num, month_num + 1, day_num);
        int chineseDay = mylunar.getChineseDay();
        s += lunarSuffix + ":" + mylunar.getChineseMonthString() + monthSuffix + Lunar.getChinaDayString(chineseDay) + "<br>";
        s += mylunar.cyclical() + yearSuffix;
        s += "<body><html>";
        return s;
    }

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

    public int use(int reyear, int remonth) {
        int week_num;// ËøîÂõûÊòüÊúüÂá?1~7
        currentSetCalendar.set(reyear, remonth, 1); // ËÆæÁΩÆÊó∂Èó¥‰∏∫ÊâÄË¶ÅÊü•ËØ¢ÁöÑÂπ¥ÊúàÁöÑÁ¨¨‰∏?§©
        week_num = (currentSetCalendar.get(Calendar.DAY_OF_WEEK));// ÂæóÂà∞Á¨¨‰∏ÄÂ§©ÁöÑÊòüÊúü
        return week_num;
    }

    public void refresh() {
        yearComboBox.setSelectedItem(year_num);
        monthComboBox.setSelectedIndex(month_num);
        int daysofMonth = daysofMonth(year_num,
                month_num + 1);
        for (int i = 0; i < daysofMonth; i++) {
            dayComboBox.addItem(i + 1);
        }
        dayComboBox.setSelectedItem(day_num);
        lunar.lunarInit(year_num, month_num + 1, day_num);
        String animalsYear = lunar.animalsYear();
        String ganZhiString = lunar.cyclical();
        String s = "";
        int weekI = (use(year_num, month_num) + day_num - 2) % 7;

        Lunar mylunar = new Lunar(year_num, month_num + 1, day_num);
        int chineseDay = mylunar.getChineseDay();
        s += xingQiSuffix + weekString[weekI] + "  ";
        s += lunarSuffix + ganZhiString + yearSuffix + mylunar.getChineseMonthString() + monthSuffix + Lunar.getChinaDayString(chineseDay);
        guideLabel.setText(s);
        int firstDayofweek = use(year_num, month_num) - 1;
        showDay = 1;
        for (int i = 0; i < dayPanelCount; i++) {
            if (i < firstDayofweek || showDay > daysofMonth) {
                cellPanel[i].setEmpty();
            } else {
                if (day_num == showDay) {
                    cellPanel[i].setSelected();
                } else {
                    cellPanel[i].setNotSelected();
                }
                cellPanel[i].setADLabelText(showDay + "");
                cellPanel[i].setLunarLabelText(getAdditionalMessage(year_num, month_num,
                        showDay));
                cellPanel[i].setDate(year_num, month_num, showDay);
                showDay++;
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(year_num, month_num, day_num);
        notePadPanel.setCurrentCalendar(calendar);
        notePadPanel.openLogFile();
    }

    class CalendarPanel extends JPanel {

        public CalendarPanel() {
            setOpaque(false);
            initCompenont();
            initData();
            loadAction();
        }

        private void initData() {
            for (int i = year_min; i <= year_max; i++) {
                yearComboBox.addItem(i);
            }
            for (int i = 1; i <= 12; i++) {
                monthComboBox.addItem(i);
            }
            monthComboBox.setMaximumRowCount(12);
            year_num = currentSetCalendar.get(Calendar.YEAR);
            month_num = currentSetCalendar.get(Calendar.MONTH);
            day_num = currentSetCalendar.get(Calendar.DATE);
            refresh();
        }

        private void initCompenont() {
            this.setLayout(new BorderLayout());
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            topPanel.setOpaque(false);
            JPanel topPanel1 = new JPanel();
            topPanel1.setOpaque(false);
            yearComboBox = new JComboBox();
            monthComboBox = new JComboBox();
            dayComboBox = new JComboBox();
            cellPanel = new CellPanel[dayPanelCount];

            JLabel copyRightLabel = new JLabel();
            JLabel nianLabel = new JLabel();
            nianLabel.setText(DiaryResource.getLabel("year"));
            JLabel yueLabel = new JLabel();
            yueLabel.setText(DiaryResource.getLabel("month"));
            JLabel riLabel = new JLabel();
            riLabel.setText(DiaryResource.getLabel("day"));
            leftLabel = new JLabel();
            rightLabel = new JLabel();
            leftLabel.setIcon(DiaryResource.getImageIcon("left"));
            rightLabel.setIcon(DiaryResource.getImageIcon("right"));
            guideLabel = new JLabel();
            todayLabel = new JLabel();
            todayLabel.setText(DiaryResource.getLabel("today"));
            todayLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            copyRightLabel.setText(DiaryResource.getApplicationName());
            copyRightLabel.setEnabled(false);
            topPanel1.add(copyRightLabel);
            topPanel1.add(todayLabel);
            topPanel1.add(leftLabel);
            topPanel1.add(yearComboBox);
            topPanel1.add(nianLabel);
            topPanel1.add(monthComboBox);
            topPanel1.add(yueLabel);
            topPanel1.add(dayComboBox);
            topPanel1.add(riLabel);
            topPanel1.add(rightLabel);
            guideLabel.setForeground(Color.red);
            topPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
            topPanel.add(topPanel1, java.awt.BorderLayout.NORTH);
            this.add(topPanel, java.awt.BorderLayout.NORTH);
            JPanel centerPanel = new JPanel();
            centerPanel.setOpaque(false);
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            JPanel center1Panel = new JPanel();
            center1Panel.setOpaque(false);
            center1Panel.setLayout(new GridLayout(1, 7, 1, 1));
            center1Panel.setBorder(BorderFactory.createEtchedBorder());

            JLabel[] dayLabel1 = new JLabel[7];
            JPanel[] dayPanel = new JPanel[7];
            for (int i = 0; i < 7; i++) {
                dayPanel[i] = new JPanel();
                dayPanel[i].setOpaque(false);
                dayLabel1[i] = new JLabel(weekString[i], SwingConstants.CENTER);
                dayPanel[i].add(dayLabel1[i]);
                dayPanel[i].setBorder(BorderFactory.createCompoundBorder());
                center1Panel.add(dayPanel[i]);
            }
            dayLabel1[0].setForeground(Color.red);
            center1Panel.setBackground(Color.white);
            centerPanel.add(center1Panel);
            JPanel center2Panel = new JPanel();
            center2Panel.setOpaque(false);
            center2Panel.setLayout(new java.awt.GridLayout(dayPanelCount / 7, 7, 1,
                    1));
            for (int i = 0; i < dayPanelCount; i++) {
                cellPanel[i] = new CellPanel(i);
                center2Panel.add(cellPanel[i]);
            }
            centerPanel.setBorder(BorderFactory.createEtchedBorder());
            centerPanel.add(center2Panel);
            this.add(centerPanel, java.awt.BorderLayout.CENTER);
            JPanel southPanel = new JPanel();
            southPanel.setOpaque(false);
            southPanel.add(guideLabel, java.awt.BorderLayout.WEST);
            southPanel.setBorder(BorderFactory.createEtchedBorder());
            this.add(southPanel, java.awt.BorderLayout.SOUTH);
        }

        private void loadAction() {
            todayLabel.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    currentSetCalendar.clear();
                    currentSetCalendar = Calendar.getInstance();
                    year_num = currentSetCalendar.get(Calendar.YEAR);
                    month_num = currentSetCalendar.get(Calendar.MONTH);
                    day_num = currentSetCalendar.get(Calendar.DATE);
                    refresh();
                }
            });
            yearComboBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
//                    System.out.println(e.getItem());
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
            leftLabel.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    lastDay();
                }
            });
            rightLabel.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    nextDay();
                }
            });
        }

        private void nextDay() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year_num, month_num, day_num);
            calendar.add(Calendar.DATE, 1);

            year_num = calendar.get(Calendar.YEAR);
            month_num = calendar.get(Calendar.MONTH);
            day_num = calendar.get(Calendar.DATE);
            refresh();
        }

        private void lastDay() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year_num, month_num, day_num);
            calendar.add(Calendar.DATE, -1);

            year_num = calendar.get(Calendar.YEAR);
            month_num = calendar.get(Calendar.MONTH);
            day_num = calendar.get(Calendar.DATE);
            refresh();
        }

        public int daysofYear(int year) {
            if (isLeapYear(year)) {
                return 366;
            } else {
                return 365;
            }
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
            // System.out.println(year+"Âπ¥ÁöÑ"+month+"ÊúàÁöÑÁ¨¨‰∏ÄÂ§©ÊòØ‰∏?òüÊúü‰∏≠ÁöÑÁ¨¨"+daysCount+"Â§?);
            return daysCount;
        }
    }

    class CellPanel extends JPanel {

        private JLabel ADLabel;
        private JLabel lunarLabel;
        private int i;
        private int thisYear;
        private int thisMonth;
        private int thisDay;
        private boolean isEmpty = false;
        private boolean visiable = false;
        private String TipMessage;

        public CellPanel(int i) {
            this.i = i;
            setOpaque(false);
            initCompenont();
            loadAction();
        }

        private void initCompenont() {
            setBorder(BorderFactory.createLineBorder(Color.yellow));
            setForeground(Color.red);
            ADLabel = new JLabel();
            lunarLabel = new JLabel();
            this.setLayout(new BoxLayout(CellPanel.this, BoxLayout.Y_AXIS));
            this.setBackground(Color.WHITE);
            ADLabel = new JLabel("" + i, SwingConstants.CENTER);
            ADLabel.setFont(new Font("", Font.BOLD, 25));
            lunarLabel = new JLabel(lunarSuffix, SwingConstants.CENTER);
            this.add(ADLabel);
            this.add(lunarLabel);
            if (i % 7 == 0 || i % 7 == 6) {
                ADLabel.setForeground(Color.red);
            }
        }

        private void loadAction() {
            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!isEmpty) {
                        String s = ADLabel.getText();
                        day_num = Integer.parseInt(s);
                        refresh();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }

        private void setEmpty() {
            isEmpty = true;
            ADLabel.setText("");
            lunarLabel.setText("");
            Color NotSelectcolor = Color.WHITE;
            ADLabel.setBackground(NotSelectcolor);
            lunarLabel.setBackground(NotSelectcolor);
            this.setBackground(NotSelectcolor);
            lunarLabel.setText("");
            this.setToolTipText(null);
        }

        private void setSelected() {
            Color selectColor = Color.lightGray;
            ADLabel.setBackground(selectColor);
            lunarLabel.setBackground(selectColor);
            this.setBackground(selectColor);
            setOpaque(true);
        }

        private void setNotSelected() {
            Color color = Color.WHITE;
            ADLabel.setBackground(color);
            lunarLabel.setBackground(color);
            this.setBackground(color);
            setOpaque(false);
        }

        private void setADLabelText(String string) {
            ADLabel.setText(string);
        }

        private void setLunarLabelText(String additionalMessage) {
            lunarLabel.setText(additionalMessage);
        }

        private void setDate(int year_num, int month_num, int showDay) {
            thisYear = year_num;
            thisMonth = month_num;
            thisDay = showDay;
            TipMessage = getTipMessage(thisYear, thisMonth, thisDay);
            setToolTipText(TipMessage);
        }
    }

    public static void main(String[] args) {
        ToolTipManager.sharedInstance().setDismissDelay(0);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame jFrame = new JFrame();
        DiaryPanel cnpp = new DiaryPanel();
        jFrame.add(cnpp);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                    this);
        }
    }
}
