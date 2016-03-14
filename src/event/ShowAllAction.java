package event;

import components.MainFrame;
import util.AResource;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

public class ShowAllAction extends ADLGuiAction {

    public ShowAllAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.ShowAll_String);
    }

    public ShowAllAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
//        putValue(MNEMONIC_KEY, KeyEvent.VK_F4);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("showAll");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (mainFrame.isListView()) {
            mainFrame.adlMainTable.setAll();
            mainFrame.adlMainTable.refresh();
        } else if (mainFrame.isIconView()) {
        }
    }
}
