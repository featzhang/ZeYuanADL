/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import util.AResource;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;

/**
 *
 * @author Administrator
 */
public class RefreshAction extends ADLGuiAction {

    public RefreshAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Refresh_String);
    }

    public RefreshAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        putValue(MNEMONIC_KEY, KeyEvent.VK_F5);
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("refresh");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainFrame.isListView()) {
            mainFrame.adlMainTable.refresh();
        } else if (mainFrame.isIconView()) {
            mainFrame.aDLIconScrollPane.refresh();
        }
    }
}
