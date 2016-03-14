package event;

import components.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import util.ConfigManager;

public class ExitAction extends ADLGuiAction {

    public ExitAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Exit_String);
    }

    public ExitAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
    }

    @Override
    protected Icon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int mainTabbedPanelSelectedIndex = mainFrame.getMainTabbedPanelSelectedIndex();
        new ConfigManager().setMainTabbedPaneSelectedIndex(mainTabbedPanelSelectedIndex);
        System.out.println("系统——关闭窗口！");
        System.exit(0);
    }
}
