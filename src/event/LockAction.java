package event;

import components.MainFrame;
import components.PasswordFrame;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import util.AResource;

public class LockAction extends ADLGuiAction {

    public LockAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
    }

    public LockAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Lock_String);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("lock");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setLocked(true);
        mainFrame.setVisible(false);
        PasswordFrame passwordFrame = new PasswordFrame(mainFrame);
        passwordFrame.setVisible(true);
    }
}
