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
public class ExportSameTableAction extends ADLGuiAction {

    private MainFrame frame = null;

    public ExportSameTableAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.ExportSameTable_String);
    }

    public ExportSameTableAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        frame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_T);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Excel.datatoExcel(Excel.datatoExcel.SaveInToFileMODE, frame);
    }
}
