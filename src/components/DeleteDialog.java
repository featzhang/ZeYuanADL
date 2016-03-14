package components;

import dataBase.AddressListData;
import java.awt.BorderLayout;
import ui.ADialog;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ui.AButton;
import util.AResource;

public class DeleteDialog extends ADialog {

    private static final long serialVersionUID = 3423536476l;
    private JLabel deleteDetailLabel;
    private JFrame parentFrame;
    private int serial;
    private boolean deleteSuccessed = false;

    public DeleteDialog(JFrame frame, boolean modal, int serial) {
        super(frame, modal);
        setTitle(AResource.getLabel("delete"));
        parentFrame = frame;
        this.serial = serial;
        loadComp();
    }

    private void loadComp() {
        deleteDetailLabel = new JLabel();
        Container contentPane = this.getContentPane(); // 获得其内容面板
        contentPane.setLayout(new BorderLayout());
        JPanel bp2 = new JPanel();
        bp2.add(deleteDetailLabel);
        JPanel bp1 = new JPanel();
        JButton sureButton = new AButton("确定");
        JButton cancelButton = new AButton("取消");
        bp1.add(sureButton);
        bp1.add(cancelButton);
        String message = detailToString(serial);
        deleteDetailLabel.setText(message);
        sureButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dataBase.DataBase.SQLExecute("DELETE * FROM ADDRESSLISTTABLE WHERE ID='"
                        + serial + "'");
                dispose();
                JOptionPane.showMessageDialog(parentFrame, "已成功删除！");
                deleteSuccessed = true;
            }
        });
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(bp2, java.awt.BorderLayout.CENTER);
        bp2.setBorder(BorderFactory.createEtchedBorder());
        bp1.setBorder(BorderFactory.createEtchedBorder());
        bp1.setOpaque(false);
        bp2.setOpaque(false);
        contentPane.add(bp1, java.awt.BorderLayout.SOUTH);
//        this.setUndecorated(true);
        this.pack();
    }

    private String detailToString(int serial) {
        String message = null;
        AddressListData addressListData = new dataBase.DataBase().getAddressListDataByID(serial + "");
        String Namedelete = addressListData.getName();
        String Sexdelete = addressListData.getSex();
        String peopleClass = addressListData.getXClass();
        String No1 = addressListData.getNo1();
        String No1Class = addressListData.getNo1Class();
        String No2 = addressListData.getNo2();
        String No2Class = addressListData.getNo2Class();
        String No3 = addressListData.getNo3();
        String No3Class = addressListData.getNo3Class();
        String Address = addressListData.getAddress();
        String HomeTown = addressListData.getHometown();
        String Qq = addressListData.getQQ();
        String Email = addressListData.getEmail();
        String Remarks = addressListData.getRemarks();

        message = "<html><body><table border='0' WIDTH='320'>";
        message += "<tr><td width='50'> </td><td align='left'><font size='6' color='red' >"
                + Namedelete;
        if (!(Sexdelete.isEmpty())) {
            message += "</font></td></tr><tr><td>性别:</td><td align='left'>"
                    + Sexdelete;
        }

        message += "</td></tr><tr><td>分组:</td><td align='left'>"
                + peopleClass;
        if (!(Address.isEmpty())) {
            message += "</td></tr><tr><td>地址:</td><td align='left'>"
                    + Address;
        }
        if (!HomeTown.isEmpty()) {
            message += "</td></tr><tr><td>家乡地址:</td><td align='left'>"
                    + HomeTown;
        }

        message += "</td></tr><tr><td>号码1：</td><td align='left'><font size='5' color='red'>"
                + No1 + "</font>(" + No1Class + ")";
        if (!(No2.isEmpty())) {
            message += "</td></tr><tr><td>号码2：</td><td align='left'>" + No2
                    + " (" + No2Class + ")";
        }
        if (!(No3.isEmpty())) {
            message += "</td></tr><tr><td>号码3：</td><td align='left'>" + No3
                    + " (" + No3Class + ")";
        }
        if (!(Qq.isEmpty())) {
            message += "</td></tr><tr><td>QQ/MSN :</td><td align='left'>"
                    + Qq;
        }
        if (!(Email.isEmpty())) {
            message += "</td></tr><tr><td>Email :</td><td align='left'>"
                    + Email;
        }
        if (!(Remarks.isEmpty())) {
            message += "</td></tr><tr><td>备注：</td><td align='left'>"
                    + Remarks;
        }
        message += "</td></tr>";
        message += "<tr><td></td><td><font size='4' color='red'>删除后不可恢复！是否确认删除？</font></td></tr></table></body></html>";
        return message;
    }

    public boolean getDeleteSuccessed() {
        return deleteSuccessed;
    }
}
