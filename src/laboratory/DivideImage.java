package laboratory;

//截取图片类
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DivideImage extends JPanel {

    BufferedImage image = null;
    int x1, y1, x2, y2;

    public DivideImage(File file) {
        super();
        this.addMouseListener(new MouseAdapter() {

            @Override
			public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
			public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                int x = x1 < x2 ? x1 : x2;
                int y = y1 < y2 ? y1 : y2;
                int w = (x1 > x2 ? x1 : x2) - x;
                int h = (y1 > y2 ? y1 : y2) - y;
                Image image = DivideImage.this.getImageByClip(x, y, w, h);
                setClipboardImage2(image);
                x1 = y1 = x2 = y2 = 0;
                JOptionPane.showMessageDialog(DivideImage.this, "图片已保存到系统粘贴板！", "图片已保存", JOptionPane.INFORMATION_MESSAGE);
                DivideImage.this.repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
			public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                DivideImage.this.repaint();
            }
        });
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("请输入一个图片文件！");
        }
    }

    public Image getImage() {
        return image;
    }

    public Image getImageByClip(int x, int y, int w, int h) {
        int rgbs[] = new int[w * h];
        rgbs = image.getRGB(x, y, w, h, rgbs, 0, w);
        BufferedImage tmpImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        tmpImage.setRGB(0, 0, w, h, rgbs, 0, w);
        return tmpImage;
    }

    @Override
	public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
        System.out.println("(" + x1 + "," + y1 + ")(" + x2 + "," + y2 + ")");
        if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0) {
            return;
        }
        System.out.println("rect");
        int x = x1 < x2 ? x1 : x2;
        int y = y1 < y2 ? y1 : y2;
        int w = (x1 > x2 ? x1 : x2) - x;
        int h = (y1 > y2 ? y1 : y2) - y;
        g.setColor(Color.blue);
        g.drawRect(x, y, w, h);
    }

    protected static void setClipboardImage2(final Image image) {
        Transferable trans = new Transferable() {

            @Override
			public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            @Override
			public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            @Override
			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (isDataFlavorSupported(flavor)) {
                    return image;
                }
                throw new UnsupportedFlavorException(flavor);
            }
        };
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
    }
}
