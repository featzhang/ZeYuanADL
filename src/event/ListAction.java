package event;

import components.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class ListAction extends ADLGuiAction {

    public ListAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.List_String);
    }

    public ListAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainFrame.isIconView()) {
            mainFrame.setListView();
        }
    }
}
