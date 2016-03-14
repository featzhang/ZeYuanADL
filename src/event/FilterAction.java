package event;

import components.MainFrame;
import components.FilterDialog;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import util.AResource;

public class FilterAction extends ADLGuiAction {

    private static FilterDialog filterDialog = null;

    public FilterAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Filter_String);
    }

    public FilterAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
//        putValue(MNEMONIC_KEY, KeyEvent.VK_Y);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("filter");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (filterDialog == null) {
            filterDialog = new FilterDialog(mainFrame);
            filterDialog.toFront();
            filterDialog.setLocationRelativeTo(mainFrame);
        }
        filterDialog.setVisible(true);
    }
}
