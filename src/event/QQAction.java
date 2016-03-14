/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import util.AResource;
public class QQAction extends ADLGuiAction {

    public QQAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.QQChart_String);
    }

    public QQAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        this.mainFrame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("QQ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String QQ = mainFrame.adlMainTable.getValueAt(mainFrame.adlMainTable.getSelectedRow(), 6).toString();
        String order = "cmd /c @start tencent://message/?uin=" + QQ;
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(order);
        } catch (Throwable t) {
        }
    }
}
