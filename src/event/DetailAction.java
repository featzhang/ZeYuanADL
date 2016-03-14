/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import components.AdlTable;
import components.DetailDialog;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import util.AResource;

/**
 *
 * @author Administrator
 */
public class DetailAction extends ADLGuiAction {

    private MainFrame parentFrame = null;

    public DetailAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Detail_String);
    }

    public DetailAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        parentFrame = mainFrame;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("detail");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AdlTable adlTable = parentFrame.adlMainTable;
        if (adlTable.getSelectID() != -1) {
            int serial = Integer.parseInt(adlTable.getValueAt(adlTable.getSelectedRow(), 9).toString());
            System.out.println("œÍœ∏–≈œ¢ –Ú∫≈ «£∫" + serial);
            DetailDialog detailDialog = new DetailDialog(parentFrame, adlTable.getCurrentSQLOrder(), serial);
            if (detailDialog.getStateChanged()) {
                adlTable.refresh();
            }
        }
    }
}
