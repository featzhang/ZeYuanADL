/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import components.DatabaseInitDialog;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class DatabaseInitAction extends ADLGuiAction {

    public DatabaseInitAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.DatabaseInit_String);
    }

    public DatabaseInitAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
//        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new DatabaseInitDialog(mainFrame);
    }
}
