package event;

import components.MainFrame;
import components.GroupManageDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 *
 * @author Administrator
 */
public class GroupManageAction extends ADLGuiAction {

    private JFrame frame;

    public GroupManageAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.GroupManage_String);
    }

    public GroupManageAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        this.frame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_K);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GroupManageDialog classAdminDialog = new GroupManageDialog(frame, true, 1);
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        x = (x - classAdminDialog.getWidth()) / 2;
        y = (y - classAdminDialog.getHeight()) / 2;
        classAdminDialog.setLocation(x, y);
        classAdminDialog.setVisible(true);
    }

    @Override
    protected Icon getIcon() {
        return null;
    }
}
