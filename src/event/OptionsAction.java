/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import components.SystemOptionalDialog;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 *
 * @author Administrator
 */
public class OptionsAction extends ADLGuiAction {

    private javax.swing.JFrame frame = null;

    public OptionsAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Options_String);
    }

    public OptionsAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        this.frame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SystemOptionalDialog systemOptionalDialog = new SystemOptionalDialog(frame, true);
        int x = (frame.getWidth() - systemOptionalDialog.getWidth()) / 2 + frame.getX();
        int y = (frame.getHeight() - systemOptionalDialog.getHeight()) / 2 + frame.getY();
        systemOptionalDialog.setLocation(x, y);
        systemOptionalDialog.setResizable(false);
        systemOptionalDialog.setVisible(true);
    }
}
