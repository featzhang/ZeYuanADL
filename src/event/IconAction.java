package event;

import components.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class IconAction extends ADLGuiAction {

    public IconAction(MainFrame adlmf) {
        this(adlmf, MainFrame.Icon_String);
    }

    public IconAction(MainFrame adlmf, String name) {
        super(adlmf, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainFrame.isListView()) {
            mainFrame.setIconView();
        }
    }
}
