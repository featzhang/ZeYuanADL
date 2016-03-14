/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 *
 * @author Administrator
 */
public class ExportDifferentTableAction extends ADLGuiAction {

    private MainFrame frame = null;

    public ExportDifferentTableAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.ExportDifferentTable_String);
    }

    public ExportDifferentTableAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        frame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_B);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Excel.datatoExcel(Excel.datatoExcel.SaveInToFileByClass, frame);
    }
}
