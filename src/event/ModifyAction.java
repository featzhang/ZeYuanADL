/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import components.AdlTable;
import components.ModifyDialog;
import util.AResource;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 *
 * @author Administrator
 */
public class ModifyAction extends ADLGuiAction {

    private MainFrame parentFrame = null;

    public ModifyAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.modify_String);
    }

    public ModifyAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        parentFrame = mainFrame;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AdlTable adlTable = parentFrame.adlMainTable;
        if (adlTable.getSelectID() != -1) {
            int serial = Integer.parseInt(adlTable.getValueAt(
                    adlTable.getSelectedRow(), 9).toString());
            System.out.println("ÐÞ¸Ä ÐòºÅÊÇ£º" + serial);
            ModifyDialog modifyDialog = new ModifyDialog(parentFrame, serial, false);
            int x, y;
            x = parentFrame.getX() + Math.abs(parentFrame.getWidth() - modifyDialog.getWidth()) / 2;
            y = parentFrame.getY() + Math.abs(parentFrame.getHeight() - modifyDialog.getHeight()) / 2;
            modifyDialog.setLocation(x, y);
            modifyDialog.setVisible(true);
            if (modifyDialog.getModifiedSuccessed()) {
                adlTable.refresh();
            }
        }
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("modify");
    }
}
