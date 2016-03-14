package event;

import components.MainFrame;
import components.TimeAwakeDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import util.AResource;

public class AwakeAction extends ADLGuiAction {

    private TimeAwakeDialog timeAwakeDialog = null;

    public AwakeAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Awake_String);
    }

    public AwakeAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);

    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("birthday");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timeAwakeDialog == null) {
            timeAwakeDialog = new TimeAwakeDialog(mainFrame, false);
        }
        if (timeAwakeDialog.isVisible()) {
            timeAwakeDialog.setVisible(false);
        } else {            
            Point toolBarLocation = mainFrame.getToolBarLocation();
//            System.out.println(toolBarLocation);
            double x = toolBarLocation.getX();
            double y = toolBarLocation.getY();
            Dimension size = timeAwakeDialog.getSize();
            double height = size.getHeight();
            timeAwakeDialog.setLocation((int) x+4, (int) (y - height+52));
//            timeAwakeDialog.setLocation((int) x, (int) y);
            timeAwakeDialog.setVisible(true);
        }
    }
}
