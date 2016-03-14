package print;

import dataBase.AddressListData;
import dataBase.DataBase;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

public class printFrame extends ui.ADialog {

    private String currentSQL;
    private JLabel perviewLabel;
    private JButton printHtmlButton;
    private JButton printButton;
    private JButton closeButton;
    private Dimension printFrameDimension = new Dimension(500, 400);
    private String namesString;
    private String remarkString;
    private String emailsString;
    private String sexString;
    private String classString;
    private String No1;
    private String No1Class;
    private String No2;
    private String No2Class;
    private String No3;
    private String No3Class;
    private String addressString;
    private String homeTownString;
    private String qqString;

    public printFrame() {
        super();
        initcomponents();
        this.toFront();
    }

    public printFrame(JFrame mainFrame) {
        super(mainFrame, true);
        initcomponents();
        this.toFront();
        this.setLocationRelativeTo(mainFrame);
    }

    public void setCurrentSQL(String string) {
        this.currentSQL = string;
        String s = dataToHtml();
        perviewLabel.setText(s);
    }

    private void initcomponents() {
        setTitle("打印预览");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        perviewLabel = new JLabel("无法预览");
        perviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(perviewLabel);
        printHtmlButton = new JButton("打印到HTML");
        printHtmlButton.setMnemonic('H');
        printButton = new JButton("打印(P)");
        printButton.setMnemonic('P');
        closeButton = new JButton("关闭");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        buttonPanel.add(printHtmlButton);
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        ButtonGroup bg = new ButtonGroup();
        bg.add(printHtmlButton);
        bg.add(printButton);
        bg.add(closeButton);
        printHtmlButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                printToHtmlAction();
            }
        });
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                printAction();
            }
        });
        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        scrollPane.setAutoscrolls(true);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setSize(printFrameDimension);
    }

    private void printAction() {
        performPrintWork(perviewLabel);
    }

    private void printToHtmlAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("保存到HTML");
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File file) { // 重载FileFilter中的accept方法
                if (file.isDirectory()) // 如果是目录,则返回true
                {
                    return true;
                }
                String fileName = file.getName(); // 得到文件名称
                if (fileName.toUpperCase().endsWith("html".toUpperCase())) // 把文件后缀与可接受后缀转成大写后比较
                {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                return "网页文件 .Html";
            }
        });
        int showSaveDialog = fileChooser.showSaveDialog(this);
        if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String name = selectedFile.toString();
            if (!name.endsWith("html") && !name.endsWith("HTML")) {
                name += ".html";
            }
            selectedFile = new File(name);
            String text = perviewLabel.getText();
            byte[] bytes = text.getBytes();
            try {
                FileOutputStream randomAccessFile = new FileOutputStream(selectedFile);
                randomAccessFile.write(bytes);
                randomAccessFile.close();
                Desktop.getDesktop().open(selectedFile);
            } catch (IOException ex) {
            }
        }
    }

    private String dataToHtml() {
        String s = "";
        s += "<html><body><table width='884' height='212' border='0' align='left' cellpadding='0' cellspacing='0'>";

        ArrayList addressListDatas = DataBase.getAddressListDatasBySQL(currentSQL);
        for (int j = 0; j < addressListDatas.size(); j++) {
            AddressListData addressListData = (AddressListData) addressListDatas.get(j);
            if (j + 1 % 3 == 1) {
                s += "<tr>";
            }
            s += "<td width='290' align='left' valign='top'><table width='280' cellpadding='0' cellspacing='0' border='1' bordercolor='#FF9999'><tr><td>";
            namesString = addressListData.getName();
            sexString = addressListData.getSex();
            classString = addressListData.getXClass();
            No1 = addressListData.getNo1();
            No1Class = addressListData.getNo1Class();
            No2 = addressListData.getNo2();
            No2Class = addressListData.getNo2Class();
            No3 = addressListData.getNo3();
            No3Class = addressListData.getNo3Class();
            addressString = addressListData.getAddress();
            homeTownString = addressListData.getHometown();
            qqString = addressListData.getQQ();
            emailsString = addressListData.getEmail();
            remarkString = addressListData.getRemarks();
            s += oneDataToHtml();
            s += "</td></tr></table></td>";
            if (j + 1 % 3 == 0) {
                s += "</tr>";
            }
        }
        s += "</table></body></html>";

        return s;
    }

    private String oneDataToHtml() {
        //创建数据表格
        String s = "";
        s += "<table width='280' height='196'  border='0' cellpadding=‘0’ cellspacing=‘0’ align='left' bgcolor='#FFFFCC'>";
        s += "<tr><td colspan='4'  align='center'  valign='middle'><font size=5 color ='#FF0000'>" + namesString + "</font></td></tr>";
        s += "<tr><td>" + (sexString.equals("") ? "" : ("性别:" + sexString)) + "&nbsp;&nbsp;"
                + (classString.equals("") ? "" : ("分组:" + classString)) + "</td></tr>";
        s += (addressString.equals("") ? "" : ("<tr><td>" + "地址:" + addressString + "</td></tr>"));
        s += (homeTownString.equals("") ? "" : ("<tr><td>" + "家乡:" + homeTownString + "</td></tr>"));
        if (!No1.equals("")) {
            s += "<tr><td>" + (No1Class.equals("") ? "电话" : No1Class) + ":" + No1 + "</td></tr>";
        }
        if (!No2.equals("")) {
            s += "<tr><td>" + No2Class + ":" + No2 + "</td></tr>";
        }
        if (!No3.equals("")) {
            s += "<tr><td>" + No3Class + ":" + No3 + "</td></tr>";
        }
        if (!qqString.equals("")) {
            s += "<tr><td>QQ/MSN:" + qqString + "</td></tr>";
        }
        if (!emailsString.equals("")) {
            s += "<tr><td>Email:" + emailsString + "</td></tr>";
        }
        if (!remarkString.equals("")) {
            s += "<tr><td align ='left' valign ='top'>备注:" + remarkString + "</td></tr>";
        }
        s += "</table>";
        return s;
    }

    public void performPrintWork(final Container container) {
        //创建打印内容
        Printable printcontext = new Printable() {

            @Override
            public int print(Graphics g, PageFormat pf, int pageno) {
                if (pageno >= 1) {
                    return Printable.NO_SUCH_PAGE;
                }
                ((Graphics2D) g).translate(pf.getImageableX(), pf.getImageableY());
                container.print(g);
                return Printable.PAGE_EXISTS;
            }
        };
        //设置打印工作
        PrinterJob printjob = PrinterJob.getPrinterJob();
        printjob.setPrintable(printcontext, printjob.defaultPage());
        try {
            //打印
            printjob.print();
        } catch (PrinterException ex) {
        }
    }
}
