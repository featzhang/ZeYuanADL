/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import components.RegistDialog;
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
public class RegistAction extends ADLGuiAction {

    public RegistAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Regist_String);
    }

    public RegistAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RegistDialog registDialog = new RegistDialog(mainFrame);
        String searchText = mainFrame.getSearchText();
        registDialog.setSearchText(searchText);
        int x = (mainFrame.getWidth() - registDialog.getSize().width) / 2 + mainFrame.getX();
        int y = (mainFrame.getHeight() - registDialog.getSize().height) / 2 + mainFrame.getY();
        registDialog.setLocation(x, y);
        registDialog.setVisible(true);
        if (registDialog.getRegistSuccessed()) {
            mainFrame.adlMainTable.refresh();
            mainFrame.adlMainTable.adlmainTableModel.fireTableStructureChanged();
        }
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("regist");
    }
}
