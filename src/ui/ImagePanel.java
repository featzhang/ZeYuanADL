package ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 3979333578588946978L;
    private Image backgroundImage;

    public ImagePanel(String filename) {
        backgroundImage = new ImageIcon(filename).getImage();
    }

    public ImagePanel(ImageIcon photo) {
        backgroundImage = photo.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                    this);
        }
    }
}
