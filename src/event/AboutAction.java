package event;

import components.MainFrame;
import components.AboutDialog;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class AboutAction extends ADLGuiAction {

    public AboutAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.About_String);
    }

    public AboutAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new Thread() {

            @Override
            public void run() {
                AboutDialog aboutDialog = new AboutDialog(mainFrame, true);
                aboutDialog.setLocationRelativeTo(mainFrame);
                aboutDialog.setVisible(true);
            }
        }.start();
    }
}
