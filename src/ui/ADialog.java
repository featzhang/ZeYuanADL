package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.AResource;

public class ADialog extends JDialog {

    private boolean isMouseDraging;
    private int dialogX;
    private int dialogY;

    public ADialog() {
        super();
        addMouseMoveAction();
        setIconImage(AResource.getAppImage());
    }

    public ADialog(JFrame frame, boolean modal) {
        super(frame, modal);
        addMouseMoveAction();
        setIconImage(AResource.getAppImage());
    }

    private void addMouseMoveAction() {
        JPanel panel = new ImagePanel(AResource.getWallpaper());
        setContentPane(panel);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                isMouseDraging = true;
                dialogX = e.getX();
                dialogY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMouseDraging = false;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isMouseDraging) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    setLocation(left + e.getX() - dialogX, top
                            + e.getY() - dialogY);
                }
            }
        });
    }
}
