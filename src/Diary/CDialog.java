package Diary;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class CDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private boolean isMouseDraging;
    private int dialogX;
    private int dialogY;
    private Container fatherContainer = null;

    public CDialog() {
        super();
        addMouseMoveAction();
    }

    public CDialog(JFrame frame, boolean modal) {
        super(frame, modal);
        fatherContainer = frame;
        addMouseMoveAction();
    }

    public CDialog(javax.swing.JDialog dialog, boolean modal) {
        super(dialog, modal);
        fatherContainer = dialog;
//        int x = dialog.getWidth() - getWidth() / 2 + dialog.getX();
//        int y = dialog.getHeight() - getHeight() / 2 + dialog.getY();
//        setLocation(x, y);
        addMouseMoveAction();
    }

    public CDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        fatherContainer = parent;
        addMouseMoveAction();
    }

    public void setLocationAtCenter() {
        if (fatherContainer != null) {
            int x = fatherContainer.getWidth() - getWidth() / 2 + fatherContainer.getX();
            int y = fatherContainer.getHeight() - getHeight() / 2 + fatherContainer.getY();
            setLocation(x, y);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            setLocation(width / 2, height / 2);
        }
    }

    public void setLocationAtScreenCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    private void addMouseMoveAction() {

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
                    setLocation(left + e.getX() - dialogX, top + e.getY()
                            - dialogY);
                }
            }
        });
    }
}
