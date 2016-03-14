
package event;

import components.MainFrame;
import util.AResource;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
public class StatisticsAction extends ADLGuiAction {

    public StatisticsAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Statistics_String);
    }

    public StatisticsAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
//        putValue(MNEMONIC_KEY, KeyEvent.VK_F3);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("statistics");
    }

    @Override
	public void actionPerformed(ActionEvent e) {
    }
}
