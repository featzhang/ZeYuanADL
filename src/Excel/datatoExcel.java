package Excel;

import dataBase.DataBaseConnection;
import util.ConfigManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@SuppressWarnings({"unused"})
public class datatoExcel {

    private File currentFile;
    private int excelAmountOfSheets; // Excel�ļ��й���������
    private int[] excelSheetColumns; // Excel�ļ�����������Ŀ
    private String[] excelSheetName; // Excel�ļ��й���������
    private int[] excelSheetRows; // Excel�ļ�����������Ŀ
    private JFileChooser filechooser; // �ļ�ѡ����
    private boolean fitsheet[];
    private String lstStatue;
    private JFrame mainFrame;
    public static final int CreateNewTableMODE = 0;
    public static final int SaveInToFileMODE = 1;
    public static final int ImportFromExcelMODE = 2;
    public static final int SaveCurrentTableMODE = 3;
    public static final int SaveInToFileByClass = 4;
    private String currentTableSQLOrder;
    private final Colour firstRowColour = Colour.RED;
    private final Colour firstRowBackground = Colour.GOLD;
    private final Colour rowColour = Colour.BLACK;
    private final Colour rowBackground = Colour.WHITE;
    private WritableFont headLinewWritableFont;
    private WritableFont contentWritableFont;
    private WritableCellFormat conteneWritableCellFormat;
    private WritableCellFormat headLineWritableCellFormat;

    public datatoExcel(int mode, Component parent) {
        mainFrame = (JFrame) parent;
        initExcelFormat();
        datatoExcelChoice(mode, parent);
    }

    public datatoExcel(int mode, Component parent, String currentSQLOrder) {
        currentTableSQLOrder = currentSQLOrder;
        initExcelFormat();
        datatoExcelChoice(mode, parent);
    }

    private void initExcelFormat() {
        try {
            headLinewWritableFont = new WritableFont(WritableFont.ARIAL, 13, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, firstRowColour);
            contentWritableFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, rowColour);
            headLineWritableCellFormat = new WritableCellFormat(headLinewWritableFont);
            headLineWritableCellFormat.setBackground(firstRowBackground);
            headLineWritableCellFormat.setBorder(Border.ALL, BorderLineStyle.DOTTED);
            headLineWritableCellFormat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
            conteneWritableCellFormat = new WritableCellFormat(contentWritableFont);
            conteneWritableCellFormat.setBackground(rowBackground);
            conteneWritableCellFormat.setBorder(Border.ALL, BorderLineStyle.DOTTED);
        } catch (WriteException ex) {
        }

    }

    private void creatEmptyExcel(File file) {

        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(file);
        } catch (IOException e) {
        }
        if (wwb != null) {
            WritableSheet ws = wwb.createSheet("ͨѶ¼", 0);
            try {
                int i = 0;
                int j = 0;

                headLineWritableCellFormat.setBackground(firstRowBackground);
                headLineWritableCellFormat.setBorder(Border.ALL, BorderLineStyle.DOTTED);
                headLineWritableCellFormat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
                ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "�Ա�", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����1", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����2", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����3", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����(����)", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����(����)", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "��ַ", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "QQ", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "EMAIL", headLineWritableCellFormat));
                ws.addCell(new Label(j++, i, "��ע", headLineWritableCellFormat));
                i++;
                while (i < 100) {
                    j = 0;
                    Label labelC0 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC1 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC2 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC3 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC4 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC5 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC6 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC7 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC8 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC9 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC10 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC11 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC12 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC13 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC14 = new Label(j++, i, "", conteneWritableCellFormat);
                    Label labelC15 = new Label(j++, i, "", conteneWritableCellFormat);

                    // �����ɵĵ�Ԫ����ӵ���������
                    ws.addCell(labelC0);
                    ws.addCell(labelC1);
                    ws.addCell(labelC2);
                    ws.addCell(labelC3);
                    ws.addCell(labelC4);
                    ws.addCell(labelC5);
                    ws.addCell(labelC6);
                    ws.addCell(labelC7);
                    ws.addCell(labelC8);
                    ws.addCell(labelC9);
                    ws.addCell(labelC10);
                    ws.addCell(labelC11);
                    ws.addCell(labelC12);
                    ws.addCell(labelC13);
                    ws.addCell(labelC14);
                    ws.addCell(labelC15);

                    i++;
                }
            } catch (Exception ex) {
            }
            try {
                wwb.write();
                wwb.close();
                System.out.println("�����յ�EXCEL�ɹ���");
            } catch (IOException e) {
            } catch (WriteException e) {
            }
        }
    }

    private void datatoExcelChoice(int mode, Component parent) {
        int fileChoserState = -1; // �ļ�ѡ��������״̬
        filechooser = new JFileChooser(); // ��ʼ���ļ�ѡ����
        filechooser.removeChoosableFileFilter(filechooser.getAcceptAllFileFilter()); // ��ȥ�����ļ�������
        filechooser.addChoosableFileFilter(new MyFileFilter("xls",
                "Microsoft office 2003 Excel(xls)")); // �����ļ�������,ɸѡxls�ļ�
        filechooser.setMultiSelectionEnabled(false);
        if (mode == CreateNewTableMODE || mode == SaveInToFileMODE
                || mode == SaveCurrentTableMODE || mode == SaveInToFileByClass) // ��Ͽ�Ϊ"����"
        {
            if (mode == CreateNewTableMODE) {
                filechooser.setDialogTitle("���ɿձ�");
            } else if (mode == SaveInToFileMODE) {
                filechooser.setDialogTitle("����Excel�ļ�");
            } else if (mode == SaveCurrentTableMODE) {
                filechooser.setDialogTitle("���浱ǰ��");
            } else if (mode == SaveInToFileByClass) {
                filechooser.setDialogTitle("�����鱣�浽������");
            }
            fileChoserState = filechooser.showSaveDialog(parent); // ��ʾ�����ļ��Ի���
        } else if (mode == ImportFromExcelMODE) {
            filechooser.setDialogTitle("��Excel�е���");
            fileChoserState = filechooser.showOpenDialog(parent); // ���ļ��Ի���
        }
        File file = filechooser.getSelectedFile(); // �õ�ѡ����ļ�
        if (file != null && fileChoserState == JFileChooser.APPROVE_OPTION) { // ѡ�����ļ�������˴򿪿ɱ��水ť
            file = fileNameCheck(file);
            int showConfirmDialog = -1;
            switch (mode) {
                case 0: {
                    creatEmptyExcel(fileNameCheck(file));
                    showConfirmDialog = JOptionPane.showConfirmDialog(null, "�ɹ������ձ�\n" + file + "\n�Ƿ�򿪣�","�����ձ�",JOptionPane.YES_NO_OPTION);
                    break;
                }
                case 1: {
                    writeDataIntoExcel(fileNameCheck(file),
                            "Select * From ADDRESSLISTTABLE ORDER BY ID+10000", 1);
                    showConfirmDialog = JOptionPane.showConfirmDialog(null, "�ɹ��������ļ�\n" + file+ "\n�Ƿ�򿪣�","�����ļ�",JOptionPane.YES_NO_OPTION); // ��ʾ��ʾ��Ϣ
                    break;
                }
                case 2: {
                    // �����ݵ������ݿ�
                    file = fileNameCheck(file);
                    Excelfilecheck(file);
                    importExcelIntoDatabase(file);
                    break;
                }
                case SaveCurrentTableMODE: {
                    writeDataIntoExcel(fileNameCheck(file), currentTableSQLOrder, 3);
                    showConfirmDialog = JOptionPane.showConfirmDialog(null, "�ѳɹ����浱ǰ��Excel�ļ�\n" + file + "\n�Ƿ�򿪣�","����ɹ�",JOptionPane.YES_NO_OPTION);
                    break;
                }
                case 4: {
                    writeDataIntoExcel(fileNameCheck(file),
                            "Select * From ADDRESSLISTTABLE ORDER BY ID+10000", 4);

                    showConfirmDialog = JOptionPane.showConfirmDialog(null, "�ѳɹ������ݰ���ͬ���鱣�浽\n" + file + "\n�Ƿ�򿪣�","����ɹ�",JOptionPane.YES_NO_OPTION); // ��ʾ��ʾ��Ϣ
                    break;
                }
            }
            if (showConfirmDialog == 0) {

                try {
                    if (mode != ImportFromExcelMODE) {
                        java.awt.Desktop.getDesktop().open(file);
                    }
                } catch (IOException ex) {
                }
            }
        } else if (fileChoserState == JFileChooser.CANCEL_OPTION) { // ����˳�����ť
        } else if (fileChoserState == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null, "����!"); // ��ʾ��ʾ��Ϣ
        }
    }

    private void Excelfilecheck(File file) {
        // ��ȡExcel�ļ��Ĳ�������������������������ƣ�ÿ�������������������
        try {
            InputStream is = new FileInputStream(file);
            jxl.Workbook rwb = Workbook.getWorkbook(is);
            this.excelAmountOfSheets = rwb.getNumberOfSheets();
            this.excelSheetName = new String[this.excelAmountOfSheets];
            this.excelSheetColumns = new int[this.excelAmountOfSheets];
            this.excelSheetRows = new int[this.excelAmountOfSheets];
            this.fitsheet = new boolean[this.excelAmountOfSheets];
            Sheet rs = rwb.getSheet(0);
            System.out.println("excelAmountOfSheets:" + excelAmountOfSheets);
            int j = 0;
            for (int i = 0; i + 1 <= excelAmountOfSheets; i++) {
                rs = rwb.getSheet(i);
                excelSheetName[i] = rs.getName();
                excelSheetColumns[i] = rs.getColumns();
                excelSheetRows[i] = rs.getRows();
                if (excelSheetColumns[i] == 16) {
                    fitsheet[j++] = true;
                } else {
                    fitsheet[j++] = false;
                }
                System.out.println(excelSheetColumns[i]);
                System.out.println(fitsheet[j - 1]);
            }
            rwb.close();
        } catch (Exception e) {
        }
    }

    private File fileNameCheck(File fileName) {
        System.out.println("fileNameCheck");
        if (fileName.toString().endsWith(".xls")) {
            return fileName;
        } else {
            String newfileName = (fileName.toString() + ".xls");
            if (fileName.exists()) {
                fileName.renameTo(new File(newfileName));
            } else {
                return new File(newfileName);
            }
        }
        return fileName;
    }

    public String getdatabaseIEID(dataBase.AddressListData excelAdldata) {
        setLstStatue("����");
        String SQL = "INSERT INTO ADDRESSLISTTABLE VALUES("
                + (getDatabaseMaxSerial() + 1) + ",'" + excelAdldata.getName()
                + "','" + excelAdldata.getSex() + "','"
                + excelAdldata.getXClass() + "','" + excelAdldata.getNo1()
                + "','" + excelAdldata.getNo1Class() + "','"
                + excelAdldata.getNo2() + "','" + excelAdldata.getNo2Class()
                + "','" + excelAdldata.getNo3() + "','"
                + excelAdldata.getNo3Class() + "','"
                + excelAdldata.getAddress() + "','"
                + excelAdldata.getHometown() + "','" + excelAdldata.getQQ()
                + "','" + excelAdldata.getEmail() + "','" + excelAdldata.getRemarks()
                + "','" + excelAdldata.getCalendarBirthday() + "','" + excelAdldata.getLunarBirthday()
                + "')";
        try {
            String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName(DBDriver); // ����������
            con = DriverManager.getConnection(DataBaseConnection.getConnectionStr()); // �������ݿ�
            stmt = con.createStatement(); // ����Statement����
            rs = stmt.executeQuery("Select * From ADDRESSLISTTABLE ORDER BY ID+10000");
            dataBase.AddressListData dbAdldata = new dataBase.AddressListData();
            while (rs.next()) {
                boolean nameisSame = false;
                boolean no1isSame = false;
                boolean no2isSame = false;
                boolean no3isSame = false;
                boolean no1isSame2 = false;
                boolean no1isSame3 = false;
                boolean no2isSame3 = false;
                boolean no2isSame1 = false;
                boolean no3isSame1 = false;
                boolean no3isSame2 = false;
                boolean noisSameCase1 = false;
                boolean noisSameCase2 = false;
                boolean noisSameCase3 = false;
                boolean noisSameCase4 = false;
                boolean noisSameCase5 = false;
                boolean noisSameCase6 = false;
                boolean noisSame = false;
                boolean noisNotSame = false;
                boolean addressisSame = false;
                boolean emailisSame = false;
                boolean hometownisSame = false;
                boolean qqisSame = false;
                boolean remarksisSame = false;
                boolean birthdayCalendarisSame = false;
                boolean birthdayLunarisSame = false;


                dbAdldata.setID(String.valueOf(rs.getString("ID")));
                dbAdldata.setName(String.valueOf(rs.getString("NAME")));
                dbAdldata.setSex(String.valueOf(rs.getString("SEX")));
                dbAdldata.setXClass(String.valueOf(rs.getString("CLASS")));
                dbAdldata.setNo1(String.valueOf(rs.getString("NO1")));
                dbAdldata.setNo1Class(String.valueOf(rs.getString("NO1CLASS")));
                dbAdldata.setNo2(String.valueOf(rs.getString("NO2")));
                dbAdldata.setNo2Class(String.valueOf(rs.getString("NO2CLASS")));
                dbAdldata.setNo3(String.valueOf(rs.getString("NO3")));
                dbAdldata.setNo3Class(String.valueOf(rs.getString("NO3CLASS")));
                dbAdldata.setAddress(String.valueOf(rs.getString("ADDRESS")));
                dbAdldata.setHometown(String.valueOf(rs.getString("HOMETOWN")));
                dbAdldata.setQQ(String.valueOf(rs.getString("QQ")));
                dbAdldata.setEmail(String.valueOf(rs.getString("EMAIL")));
                dbAdldata.setRemarks(String.valueOf(rs.getString("REMARKS")));
                dbAdldata.setXCalendarBirthday(String.valueOf(rs.getString("Birthday")));
                dbAdldata.setXLunarBirthday(String.valueOf(rs.getString("BirthdayLunar")));

                if (excelAdldata.getName().trim().equals(
                        dbAdldata.getName().trim())) {
                    nameisSame = true;
                }
                if (excelAdldata.getNo1().trim().equals(
                        dbAdldata.getNo1().trim())) {
                    no1isSame = true;
                }
                if (excelAdldata.getNo2().trim().equals(
                        dbAdldata.getNo2().trim())) {
                    no2isSame = true;
                }
                if (excelAdldata.getNo3().trim().equals(
                        dbAdldata.getNo3().trim())) {
                    no3isSame = true;
                }
                if (excelAdldata.getNo1().trim().equals(
                        dbAdldata.getNo2().trim())) {
                    no1isSame2 = true;
                }
                if (excelAdldata.getNo1().trim().equals(
                        dbAdldata.getNo3().trim())) {
                    no1isSame3 = true;
                }
                if (excelAdldata.getNo2().trim().equals(
                        dbAdldata.getNo3().trim())) {
                    no2isSame3 = true;
                }
                if (excelAdldata.getNo2().trim().equals(
                        dbAdldata.getNo1().trim())) {
                    no2isSame1 = true;
                }
                if (excelAdldata.getNo3().trim().equals(
                        dbAdldata.getNo1().trim())) {
                    no3isSame1 = true;
                }
                if (excelAdldata.getNo3().trim().equals(
                        dbAdldata.getNo2().trim())) {
                    no3isSame2 = true;
                }
                if (excelAdldata.getAddress().trim().equals(
                        dbAdldata.getAddress().trim())) {
                    addressisSame = true;
                }
                if (excelAdldata.getEmail().trim().equals(
                        dbAdldata.getEmail().trim())) {
                    emailisSame = true;
                }
                if (excelAdldata.getEmail().trim().equals(
                        dbAdldata.getEmail().trim())) {
                    emailisSame = true;
                }
                if (excelAdldata.getHometown().trim().equals(
                        dbAdldata.getHometown().trim())) {
                    hometownisSame = true;
                }
                if (excelAdldata.getQQ().trim().equals(
                        dbAdldata.getQQ().trim())) {
                    qqisSame = true;
                }
                if (excelAdldata.getRemarks().trim().equals(
                        dbAdldata.getRemarks().trim())) {
                    remarksisSame = true;
                }
                if (excelAdldata.getCalendarBirthday().trim().equals(
                        dbAdldata.getCalendarBirthday().trim())) {
                    birthdayCalendarisSame = true;
                }

                if (excelAdldata.getLunarBirthday().trim().equals(
                        dbAdldata.getLunarBirthday().trim())) {
                    birthdayLunarisSame = true;
                }
                if (no1isSame && no2isSame && no3isSame) {
                    noisSameCase1 = true;
                } else if (no1isSame && no2isSame3 && no3isSame2) {
                    noisSameCase2 = true;
                } else if (no1isSame2 && no2isSame1 && no3isSame) {
                    noisSameCase3 = true;
                } else if (no1isSame2 && no2isSame3 && no3isSame1) {
                    noisSameCase4 = true;
                } else if (no1isSame3 && no2isSame && no3isSame1) {
                    noisSameCase5 = true;
                } else if (no1isSame3 && no2isSame1 && no3isSame2) {
                    noisSameCase6 = true;
                }

                if (noisSameCase1 || noisSameCase2 || noisSameCase3
                        || noisSameCase4 || noisSameCase5 || noisSameCase6) {
                    noisSame = true;
                }
                if (!noisSameCase1 && !noisSameCase2 && !noisSameCase3
                        && !noisSameCase4 && !noisSameCase5 && !noisSameCase6) {
                    noisNotSame = true;
                }
                if (excelAdldata.getName().isEmpty()
                        && excelAdldata.getNo1().isEmpty()) {
                    setLstStatue("����");
                    SQL = "nothing";
                    break;
                }
                if (nameisSame && addressisSame && hometownisSame && qqisSame
                        && emailisSame && remarksisSame && noisSame) {
                    setLstStatue("����");
                    SQL = "nothing";
                    break;
                }
                if (nameisSame && no1isSame && !dbAdldata.getNo2().isEmpty()
                        && excelAdldata.getNo2().isEmpty() && addressisSame
                        && hometownisSame && qqisSame && emailisSame
                        && remarksisSame) {
                    setLstStatue("����");
                    SQL = "nothing";
                    break;
                }

                if (nameisSame
                        && no1isSame
                        && ((dbAdldata.getNo2().isEmpty()
                        && dbAdldata.getNo3().isEmpty() && (!excelAdldata.getNo2().isEmpty() || !excelAdldata.getNo3().isEmpty())) || (no2isSame
                        && dbAdldata.getNo3().isEmpty() && !excelAdldata.getNo3().isEmpty()))) {
                    setLstStatue("�޸�");
                    SQL = "update  ADDRESSLISTTABLE  SET" + "  NAME='"
                            + excelAdldata.getName()
                            + "'"
                            + " ,   SEX='"
                            + excelAdldata.getSex()
                            + "'"
                            + " ,   CLASS='"
                            + excelAdldata.getXClass()
                            + "'"
                            + " ,   ADDRESS='"
                            + (addressisSame ? excelAdldata.getAddress()
                            : (excelAdldata.getAddress() + dbAdldata.getAddress()))
                            + "'"
                            + " ,  HOMETOWN='"
                            + (hometownisSame ? excelAdldata.getHometown()
                            : (excelAdldata.getHometown() + dbAdldata.getHometown()))
                            + "'"
                            + " ,   NO1='"
                            + excelAdldata.getNo1()
                            + "'"
                            + " ,   NO1CLASS='"
                            + excelAdldata.getNo1Class()
                            + "'"
                            + " ,   NO2='"
                            + excelAdldata.getNo2()
                            + "'"
                            + " ,   No2CLASS='"
                            + excelAdldata.getNo2Class()
                            + "'"
                            + " ,   NO3='"
                            + excelAdldata.getNo3()
                            + "'"
                            + " ,   No3CLASS='"
                            + excelAdldata.getNo3Class()
                            + "'"
                            + " ,  QQ='"
                            + (qqisSame ? excelAdldata.getQQ() : (excelAdldata.getQQ() + dbAdldata.getQQ()))
                            + "'"
                            + " ,  EMAIL='"
                            + (emailisSame ? excelAdldata.getEmail()
                            : (excelAdldata.getEmail() + dbAdldata.getEmail()))
                            + "'"
                            + " ,  Birthday='"
                            + (dbAdldata.getCalendarBirthday().trim().equals("") ? excelAdldata.getCalendarBirthday() : dbAdldata.getCalendarBirthday())
                            + "'"
                            + " ,  BirthdayLunar='"
                            + (dbAdldata.getLunarBirthday().trim().equals("") ? excelAdldata.getLunarBirthday() : dbAdldata.getLunarBirthday())
                            + "'"
                            + " ,  REMARKS='"
                            + (remarksisSame ? excelAdldata.getRemarks()
                            : (excelAdldata.getRemarks() + dbAdldata.getRemarks())) + "'"
                            + "  WHERE ID='" + dbAdldata.getID() + "'";
                    break;
                }
                if (nameisSame && no1isSame && !dbAdldata.getNo2().isEmpty()
                        && !no2isSame && excelAdldata.getNo3().isEmpty()
                        && !excelAdldata.getNo2().isEmpty()) {
                    setLstStatue("�޸�");
                    SQL = "update  ADDRESSLISTTABLE  SET" + "  NAME='"
                            + excelAdldata.getName()
                            + "'"
                            + " ,   SEX='"
                            + excelAdldata.getSex()
                            + "'"
                            + " ,   CLASS='"
                            + excelAdldata.getXClass()
                            + "'"
                            + " ,   ADDRESS='"
                            + (addressisSame ? excelAdldata.getAddress()
                            : (excelAdldata.getAddress() + dbAdldata.getAddress()))
                            + "'"
                            + " ,  HOMETOWN='"
                            + (hometownisSame ? excelAdldata.getHometown()
                            : (excelAdldata.getHometown() + dbAdldata.getHometown()))
                            + "'"
                            + " ,   NO1='"
                            + dbAdldata.getNo1()
                            + "'"
                            + " ,   NO1CLASS='"
                            + dbAdldata.getNo1Class()
                            + "'"
                            + " ,   NO2='"
                            + dbAdldata.getNo2()
                            + "'"
                            + " ,   No2CLASS='"
                            + dbAdldata.getNo2Class()
                            + "'"
                            + " ,   NO3='"
                            + excelAdldata.getNo2()
                            + "'"
                            + " ,   No3CLASS='"
                            + excelAdldata.getNo2Class()
                            + "'"
                            + " ,  QQ='"
                            + (qqisSame ? excelAdldata.getQQ() : (excelAdldata.getQQ() + dbAdldata.getQQ()))
                            + "'"
                            + " ,  EMAIL='"
                            + (emailisSame ? excelAdldata.getEmail()
                            : (excelAdldata.getEmail() + dbAdldata.getEmail()))
                            + "'"
                            + " ,  Birthday='"
                            + (dbAdldata.getCalendarBirthday().trim().equals("") ? excelAdldata.getCalendarBirthday() : dbAdldata.getCalendarBirthday())
                            + "'"
                            + " ,  BirthdayLunar='"
                            + (dbAdldata.getLunarBirthday().trim().equals("") ? excelAdldata.getLunarBirthday() : dbAdldata.getLunarBirthday())
                            + "'"
                            + " ,  REMARKS='"
                            + (remarksisSame ? excelAdldata.getRemarks()
                            : (excelAdldata.getRemarks() + dbAdldata.getRemarks())) + "'"
                            + "  WHERE ID='" + dbAdldata.getID() + "'";
                    break;
                }
            }
            stmt.close(); // �ر����
            con.close(); // �ر�����
        } catch (Exception ex) {
        }
        if (SQL.equals("nothing")) {
            return null;
        } else {
            setLstStatue("���");
            return SQL;
        }
    }

    private int getDatabaseMaxSerial() {
        int maxSerial = 0;
        int serial;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); // ����������
            Connection con = DriverManager.getConnection(DataBaseConnection.getConnectionStr());
            // �����ݿ⽨������
            Statement stmt = con.createStatement(); // �������
            ResultSet rs = stmt.executeQuery("Select * From ADDRESSLISTTABLE");
            while (rs.next()) {
                serial = Integer.parseInt(rs.getString("ID"));
                maxSerial = serial > maxSerial ? serial : maxSerial;
            }
            stmt.close();
            con.close(); // �رյ����ݿ������
            return maxSerial;
        } catch (Exception e) {
            System.out.print("��ȡ�������ݿ��¼��Ŀ�����ݿ����ʧ��");
        }
        return maxSerial;
    }

    private void importExcelIntoDatabase(File file) {
        setLstStatue("");
        currentFile = file;
        System.out.println("importExcelIntoDatabase");
        showProcessFrameInit("�������ݵ��뵽���ݿ�... ...");
    }
    private JDialog showProcessFrame;
    private JLabel statueLabel;
    private JProgressBar processBar;
    private JButton cancelButton;
    private List processLst;
    private JButton startButton;

    @SuppressWarnings("deprecation")
    public void showProcessFrameInit(String s) {
        Color backColor = Color.PINK;
        showProcessFrame = new JDialog(mainFrame, s, true);
        Container contentPane = showProcessFrame.getContentPane();
        contentPane.setBackground(backColor);
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        northPanel.setBackground(backColor);
        statueLabel = new JLabel();
        statueLabel.setText("����.....");
        northPanel.add(statueLabel, BorderLayout.NORTH);
        processBar = new JProgressBar(0, 100);
        processBar.setIndeterminate(true);
        northPanel.add(processBar, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("��ʼ");
        cancelButton = new JButton("ȡ��");
        buttonPanel.add(startButton);
//        buttonPanel.add(cancelButton);
        northPanel.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setBackground(backColor);
        contentPane.add(northPanel, BorderLayout.NORTH);
        processLst = new List(5, false);
        processLst.clear();
        processLst.add("���'��ʼ'���е���... ...");
        contentPane.add(processLst, BorderLayout.CENTER);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelButton.setEnabled(false);
                    showProcessFrame.setEnabled(false);
                    statueLabel.setText("���ڵ������� ... ...");
                    processLst.clear();
                    InputStream is = new FileInputStream(currentFile);
                    jxl.Workbook rwb = Workbook.getWorkbook(is);
                    dataBase.AddressListData exceladldata = new dataBase.AddressListData();
                    for (int i = 0; i + 1 <= excelAmountOfSheets && fitsheet[i]; i++) {
                        Sheet rsheet = rwb.getSheet(i);
                        for (int j = 1; j < excelSheetRows[i]; j++) {
                            exceladldata.setID("-1");
                            exceladldata.setName(rsheet.getCell(0, j).getContents());
                            exceladldata.setSex(rsheet.getCell(1, j).getContents());
                            exceladldata.setXClass(rsheet.getCell(2, j).getContents());
                            exceladldata.setNo1(rsheet.getCell(3, j).getContents());
                            exceladldata.setNo1Class(rsheet.getCell(4, j).getContents());
                            exceladldata.setNo2(rsheet.getCell(5, j).getContents());
                            exceladldata.setNo2Class(rsheet.getCell(6, j).getContents());
                            exceladldata.setNo3(rsheet.getCell(7, j).getContents());
                            exceladldata.setNo3Class(rsheet.getCell(8, j).getContents());
                            exceladldata.setXCalendarBirthday(rsheet.getCell(9, j).getContents());
                            exceladldata.setXLunarBirthday(rsheet.getCell(10, j).getContents());
                            exceladldata.setAddress(rsheet.getCell(11, j).getContents());
                            exceladldata.setHometown(rsheet.getCell(12, j).getContents());
                            exceladldata.setQQ(rsheet.getCell(13, j).getContents());
                            exceladldata.setEmail(rsheet.getCell(14, j).getContents());
                            exceladldata.setRemarks(rsheet.getCell(15, j).getContents());

                            String s = getdatabaseIEID(exceladldata);
                            int m = processLst.getSelectedIndex() + 2;
                            processLst.add("" + m + "  "
                                    + exceladldata.getName() + "   "
                                    + exceladldata.getNo1() + "   ����...  "
                                    + getLstStatue(), m);
                            processLst.select(m - 1);
                            System.out.println(m + s);
                            dataBase.DataBase.SQLExecute(s);
                        }
                    }
                } catch (Exception ex) {
                }
                processLst.add("����¼���顱���ݿ��ʼ��... ...");
                processLst.select(processLst.getSelectedIndex() + 1);
                new dataBase.DataBase().groupNameInit();
                processLst.add("��������顱���ݿ��ʼ��... ...");
                processLst.select(processLst.getSelectedIndex() + 1);
                new dataBase.DataBase().noClassNameCheck();
                processLst.add("�����´����ݿ������... ...");
                processLst.select(processLst.getSelectedIndex() + 1);
                new ConfigManager().setDatabaseNeedInit(true);
                showProcessFrame.dispose();
                JOptionPane.showMessageDialog(null, "�Ѿ�ȫ�������ݵ��뵽���ݿ��У�");
            }
        });
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showProcessFrame.dispose();
            }
        });
//        showProcessFrame.setUndecorated(true);
        showProcessFrame.pack();
        showProcessFrame.setLocationRelativeTo(mainFrame);
        showProcessFrame.setVisible(true);
        showProcessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showProcessFrame.setResizable(false);
    }
    private void writeDataIntoExcel(File file, String SQLOrder, int mode) {
        int count = 1;
        String[] classNameArray = new dataBase.DataBase().getGroupNameArray();
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(file);
        } catch (IOException e) {
        }
        if (wwb != null) {
            WritableSheet ws;
            try {
                String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                Class.forName(DBDriver); // ����������
                con = DriverManager.getConnection(DataBaseConnection.getConnectionStr()); // �������ݿ�
                stmt = con.createStatement(); // ����Statement����
                for (int m = 0; m < count; m++) {
                    if (mode == 4) {
                        ws = wwb.createSheet(classNameArray[m], m);
                        SQLOrder = "Select * From ADDRESSLISTTABLE where class='" + classNameArray[m] + "' ORDER BY ID+10000";
                        count = classNameArray.length;
                    } else {
                        ws = wwb.createSheet("ͨѶ¼1", 0);
                    }
                    rs = stmt.executeQuery(SQLOrder);
                    int i = 0;
                    int j = 0;
                    ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "�Ա�", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����1", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����2", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����3", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����(����)", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����(����)", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "��ַ", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "����", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "QQ", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "EMAIL", headLineWritableCellFormat));
                    ws.addCell(new Label(j++, i, "��ע", headLineWritableCellFormat));
                    i++;

                    while (rs.next()) {
                        j = 0;
                        String cont = rs.getString("NAME");
                        Label labelC0 = new Label(j++, i, cont, conteneWritableCellFormat);
                        Label labelC1 = new Label(j++, i, rs.getString("SEX"),
                                conteneWritableCellFormat);
                        Label labelC2 = new Label(j++, i, rs.getString("CLASS"),
                                conteneWritableCellFormat);
                        Label labelC3 = new Label(j++, i, rs.getString("NO1"),
                                conteneWritableCellFormat);
                        Label labelC4 = new Label(j++, i, rs.getString("NO1CLASS"),
                                conteneWritableCellFormat);
                        Label labelC5 = new Label(j++, i, rs.getString("NO2"),
                                conteneWritableCellFormat);
                        Label labelC6 = new Label(j++, i, rs.getString("NO2CLASS"),
                                conteneWritableCellFormat);
                        Label labelC7 = new Label(j++, i, rs.getString("NO3"),
                                conteneWritableCellFormat);
                        Label labelC8 = new Label(j++, i, rs.getString("NO3CLASS"),
                                conteneWritableCellFormat);
                        Label labelC9 = new Label(j++, i, rs.getString("Birthday"),
                                conteneWritableCellFormat);
                        Label labelC10 = new Label(j++, i, rs.getString("BirthdayLunar"),
                                conteneWritableCellFormat);
                        Label labelC11 = new Label(j++, i, rs.getString("ADDRESS"),
                                conteneWritableCellFormat);
                        Label labelC12 = new Label(j++, i,
                                rs.getString("HOMETOWN"), conteneWritableCellFormat);
                        Label labelC13 = new Label(j++, i, rs.getString("QQ"),
                                conteneWritableCellFormat);
                        Label labelC14 = new Label(j++, i, rs.getString("EMAIL"),
                                conteneWritableCellFormat);
                        Label labelC15 = new Label(j++, i, rs.getString("REMARKS"),
                                conteneWritableCellFormat);

                        ws.addCell(labelC0);
                        ws.addCell(labelC1);
                        ws.addCell(labelC2);
                        ws.addCell(labelC3);
                        ws.addCell(labelC4);
                        ws.addCell(labelC5);
                        ws.addCell(labelC6);
                        ws.addCell(labelC7);
                        ws.addCell(labelC8);
                        ws.addCell(labelC9);
                        ws.addCell(labelC10);
                        ws.addCell(labelC11);
                        ws.addCell(labelC12);
                        ws.addCell(labelC13);
                        ws.addCell(labelC14);
                        ws.addCell(labelC15);
                        i++;
                    }
                }

                stmt.close(); // �ر����
                con.close(); // �ر�����
                System.out.print("д��EXCEL�ɹ���");
            } catch (Exception ex) {
            }
            try {
                wwb.write();
                wwb.close();
            } catch (IOException e) {
            } catch (WriteException e) {
            }
        }

    }

    public String getLstStatue() {
        return lstStatue;
    }

    public void setLstStatue(String lstStatue) {
        this.lstStatue = lstStatue;
    }
}
