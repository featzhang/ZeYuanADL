package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;

public class CalendarHandle {

    private final static int MONTH_DAYS[] = {29, 30};
    private final static int DAYS_MONTH[][] = {
        {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
        {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
    };
    private static String[] constellationChineseNames = {"Ħ����", "ˮƿ��", "˫����", "������", "��ţ��", "˫����", "��з��", "ʨ����", "��Ů��", "�����", "��Ы��", "������"};
    private static String[] constellationEnglishNames = {"Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius"};
    private final static int DATAS[][] = {
        {23, 3, 2, 17, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0},
        {41, 0, 4, 23, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1},
        {30, 7, 5, 28, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1},
        {49, 0, 6, 33, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
        {38, 0, 0, 38, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {26, 6, 2, 44, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0},
        {45, 0, 3, 49, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {35, 0, 4, 54, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
        {24, 4, 5, 59, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
        {43, 0, 0, 5, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
        {32, 0, 1, 10, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
        {21, 2, 2, 15, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
        {40, 0, 3, 20, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
        {28, 7, 5, 26, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {47, 0, 6, 31, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1},
        {36, 0, 0, 36, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {26, 5, 1, 41, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
        {44, 0, 3, 47, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1},
        {33, 0, 4, 52, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0},
        {23, 3, 5, 57, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1},
        {42, 0, 6, 2, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1},
        {30, 8, 1, 8, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
        {48, 0, 2, 13, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
        {38, 0, 3, 18, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {27, 6, 4, 23, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0},
        {45, 0, 6, 29, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0},
        {35, 0, 0, 34, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {24, 4, 1, 39, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0},
        {43, 0, 2, 44, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0},
        {32, 0, 4, 50, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1},
        {20, 3, 5, 55, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0},
        {39, 0, 6, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0},
        {29, 7, 0, 5, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {47, 0, 2, 11, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
        {36, 0, 3, 16, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0},
        {26, 5, 4, 21, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {45, 0, 5, 26, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
        {33, 0, 0, 32, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1},
        {22, 4, 1, 37, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1},
        {41, 0, 2, 42, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1},
        {30, 8, 3, 47, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
        {48, 0, 5, 53, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
        {37, 0, 6, 58, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
        {27, 6, 0, 3, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0},
        {46, 0, 1, 8, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0},
        {35, 0, 3, 14, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1},
        {24, 4, 4, 19, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
        {43, 0, 5, 24, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
        {32, 10, 6, 29, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1},
        {50, 0, 1, 35, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
        {39, 0, 2, 40, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1},
        {28, 6, 3, 45, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0},
        {47, 0, 4, 50, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {36, 0, 6, 56, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0},
        {26, 5, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1},
        {45, 0, 1, 6, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
        {34, 0, 2, 11, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0},
        {22, 3, 4, 17, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
        {40, 0, 5, 22, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
        {30, 8, 6, 27, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1},
        {49, 0, 0, 32, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1},
        {37, 0, 2, 38, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {27, 5, 3, 43, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1},
        {46, 0, 4, 48, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1},
        {35, 0, 5, 53, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1},
        {23, 4, 0, 59, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {42, 0, 1, 4, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {31, 0, 2, 9, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0},
        {21, 2, 3, 14, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {39, 0, 5, 20, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
        {28, 7, 6, 25, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
        {48, 0, 0, 30, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
        {37, 0, 1, 35, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
        {25, 5, 3, 41, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
        {44, 0, 4, 46, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
        {33, 0, 5, 51, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {22, 4, 6, 56, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {40, 0, 1, 2, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {30, 9, 2, 7, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
        {49, 0, 3, 12, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {38, 0, 4, 17, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0},
        {27, 6, 6, 23, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1},
        {46, 0, 0, 28, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0},
        {35, 0, 1, 33, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0},
        {24, 4, 2, 38, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
        {42, 0, 4, 44, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {31, 0, 5, 49, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0},
        {21, 2, 6, 54, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
        {40, 0, 0, 59, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {28, 6, 2, 5, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0},
        {47, 0, 3, 10, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
        {36, 0, 4, 15, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1},
        {25, 5, 5, 20, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0},
        {43, 0, 0, 26, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
        {32, 0, 1, 31, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0},
        {22, 3, 2, 36, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0}
    };

    public static String[] getConstellationChineseNames() {
        return constellationChineseNames;
    }

    public static String getConstellationChineseName(int i) {
        return constellationChineseNames[i];
    }

    public static String[] getConstellationEnglishNames() {
        return constellationEnglishNames;
    }

    public static String getConstellationEnglishName(int i) {
        return constellationEnglishNames[i];
    }

    /**
     * ��������ȡ�ö�Ӧ���������ڣ������ڱ�����1936��2028��֮�䣩
     *
     * @param s �������ڣ��ַ�����
     * @return ���ض�Ӧ����������
     * @throws Exception
     */
    public static Date getGregorianCalendar(String s) {
        Calendar c = getCalendar(s);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int index = year - 1936;
        int endIndex = month;

        if ((DATAS[index][1] != 0) && (month > DATAS[index][1])) {
            endIndex++;
        }

        int dayNum = 0;

        for (int i = 0; i < (endIndex - 1); i++) {
            dayNum += MONTH_DAYS[DATAS[index][4 + i]];
        }

        dayNum += day;
        dayNum += DATAS[index][0];

        int year_days = 365;

        if (isLeapYear(year)) {
            year_days = 366;
        }

        if (dayNum > year_days) {
            year++;
            dayNum -= year_days;
        }

        month = 1;

        int dayOfMonth[] = DAYS_MONTH[0];

        if (isLeapYear(year)) {
            dayOfMonth = DAYS_MONTH[1];
        }
        int i = 0;
        for (; i < 12; i++) {
            dayNum -= dayOfMonth[i];

            if (dayNum <= 0) {
                break;
            }
            month++;
        }
        day = dayOfMonth[i] + dayNum;
        return getDate(year + "-" + month + "-" + day);
    }

    /**
     * ��������ȡ�ö�Ӧ���������ڣ������ڱ�����1936��2028��֮�䣩
     *
     * @param string �������ڣ��ַ�����
     * @return ������������
     * @throws Exception
     */
    public static Date getLunarCalendar(String string) {
        Calendar calendar = getCalendar(string);
        int year = calendar.get(Calendar.YEAR);
        int month = 1;
        int day;

        if ((year < 1936) || (year > 2028)) {
            return null;
        }

        int index = year - 1936;
        int l_days = DATAS[index][0];
        int day_year = calendar.get(Calendar.DAY_OF_YEAR);
        int days;

        if (day_year >= l_days) {
            days = day_year - l_days;
        } else {
            index--;
            year--;

            Calendar c = getCalendar(year + "-12-31");
            days = (c.get((Calendar.DAY_OF_YEAR)) + day_year)
                    - DATAS[index][0];
        }

        int i = 0;
        int day_num = 0;
        for (; i < 13; i++) {
            day_num += MONTH_DAYS[DATAS[index][i + 4]];
            if (day_num >= days) {
                break;
            }
            month++;
        }
        day = MONTH_DAYS[DATAS[index][i + 4]] - (day_num - days);
        if ((DATAS[index][1] != 0) && (month > DATAS[index][1])) {
            month--;
        }
        return getDate(year + "-" + month + "-" + day);
    }

    public static Date getLunarCalendar(Date date) {
        String dateString = parseDateToString(date);
        return getLunarCalendar(dateString);
    }

    private static Calendar getCalendar(String string) {
        Date dd = getDate(string);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);

        return calendar;
    }

    private static Date getDate(String string) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = null;

        try {
            dd = format.parse(string);
        } catch (Exception e) {
        }

        return dd;
    }

    private static boolean isLeapYear(int year) {
        if ((year % 400) == 0) {
            return true;
        } else if ((year % 100) == 0) {
            return false;
        } else if ((year % 4) == 0) {
            return true;
        }
        return false;
    }

    public static String parseDateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(date);
        return s;
    }

    public static Date parseStringToDate(String dateString) {
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(dateString);
        } catch (ParseException ex) {
        }
        return date;
    }

    public static String parseStringFormat(String dateString) {
        String s = "";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2 = new SimpleDateFormat("yyyy��MM��dd��");

        try {
            Date date = format.parse(dateString);
            s = format2.format(date);
        } catch (ParseException ex) {
        }
        return s;
    }

    public static String parseStringFormatYYYYMM(String dateString) {
        String s = "";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2 = new SimpleDateFormat("MM��dd��");

        try {
            Date date = format.parse(dateString);
            s = format2.format(date);
        } catch (ParseException ex) {
        }
        return s;
    }

    public static int getConstellation(Date s) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(s);
        int xingzuo = -1;
        int day = cal.get(Calendar.DAY_OF_YEAR);
        if ((cal.get(Calendar.YEAR) % 4 == 0) && (cal.get(Calendar.YEAR) % 100 != 0) || (cal.get(Calendar.YEAR) % 400 == 0)) {
            if ((day >= 1 && day <= 19) || (day >= 357 && day <= 366)) {
                xingzuo = 0;// "ħЫ��";
            } else if (day >= 20 && day <= 49) {
                xingzuo = 1;// "ˮƿ��";
            } else if (day >= 50 && day <= 80) {
                xingzuo = 2;// "˫����";
            } else if (day >= 81 && day <= 110) {
                xingzuo = 3;// "������";
            } else if (day >= 111 && day <= 141) {
                xingzuo = 4;// "��ţ��";
            } else if (day >= 142 && day <= 173) {
                xingzuo = 5;// "˫����";
            } else if (day >= 174 && day <= 203) {
                xingzuo = 6;// "��з��";
            } else if (day >= 204 && day <= 235) {
                xingzuo = 7;// "ʨ����";
            } else if (day >= 236 && day <= 266) {
                xingzuo = 8;// "��Ů��";
            } else if (day >= 267 && day <= 296) {
                xingzuo = 9;// "�����";
            } else if (day >= 297 && day <= 326) {
                xingzuo = 10;// "��Ы��";
            } else if (day >= 327 && day <= 356) {
                xingzuo = 11;// "������";
            }
        } else {
            if ((day >= 1 && day <= 19) || (day >= 357 && day <= 366)) {
                xingzuo = 0;// "ħЫ��";
            } else if (day >= 20 && day <= 48) {
                xingzuo = 1;// "ˮƿ��";
            } else if (day >= 49 && day <= 79) {
                xingzuo = 2;// "˫����";
            } else if (day >= 80 && day <= 109) {
                xingzuo = 3;// "������";
            } else if (day >= 110 && day <= 140) {
                xingzuo = 4;// "��ţ��";
            } else if (day >= 141 && day <= 172) {
                xingzuo = 5;// "˫����";
            } else if (day >= 173 && day <= 202) {
                xingzuo = 6;// "��з��";
            } else if (day >= 203 && day <= 234) {
                xingzuo = 7;// "ʨ����";
            } else if (day >= 235 && day <= 265) {
                xingzuo = 8;// "��Ů��";
            } else if (day >= 266 && day <= 295) {
                xingzuo = 9;// "�����";
            } else if (day >= 296 && day <= 325) {
                xingzuo = 10;// "��Ы��";
            } else if (day >= 326 && day <= 355) {
                xingzuo = 11;// "������";
            }
        }
        return xingzuo;
    }

    public static String getConstellationChineseNameByDate(Date s) {
        int constellation = getConstellation(s);
        return constellationChineseNames[constellation];
    }

    public static ImageIcon getConstellationIcon(Date s) {
        int index = getConstellation(s);
        String c = constellationEnglishNames[index];
        return AResource.getImageIcon(c);
    }
}
