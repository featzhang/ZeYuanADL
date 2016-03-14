/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import components.AdlTable;
import components.DeleteDialog;
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
public class DeleteAction extends ADLGuiAction {

    MainFrame mainFrame = null;

    public DeleteAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Delete_String);
    }

    public DeleteAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        this.mainFrame = mainFrame;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AdlTable adlTable = mainFrame.adlMainTable;
        if (adlTable.getSelectID() != -1) {
            int serial = Integer.parseInt(adlTable.getValueAt(
                    adlTable.getSelectedRow(), 9).toString());
            System.out.println("É¾³ý ÐòºÅÊÇ£º" + serial);
            DeleteDialog deleteDialog = new DeleteDialog(mainFrame, true, serial);
            int x = (mainFrame.getWidth() - deleteDialog.getWidth()) / 2 + mainFrame.getX();
            int y = (mainFrame.getHeight() - deleteDialog.getHeight()) / 2 + mainFrame.getY();
            deleteDialog.setLocation(x, y);
            deleteDialog.setVisible(true);
            if (deleteDialog.getDeleteSuccessed()) {
                adlTable.refresh();
            }
        }
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("delete");
    }
}
