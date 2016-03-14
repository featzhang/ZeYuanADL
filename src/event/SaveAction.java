package event;

import components.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class SaveAction extends ADLGuiAction {

    public SaveAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Save_String);
    }

    public SaveAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
//        putValue(MNEMONIC_KEY, KeyEvent.VK_S);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Excel.datatoExcel(Excel.datatoExcel.SaveCurrentTableMODE, mainFrame, mainFrame.adlMainTable.getCurrentSQLOrder());
    }
}
