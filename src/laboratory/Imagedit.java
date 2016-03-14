package laboratory;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Imagedit {
	private static JFrame frame;
    public static void main(String[] args) {
        frame = new TransformFrame();
        frame.setVisible(true);
//        frame.add(new DivideImage(new File("D://p_head_KKKV_61160000061f2d0f.jpg")));
    }
    public static JFrame getFrame(){
		return frame;
	}
}
class TransformFrame extends JFrame implements ActionListener {
    JButton open = new JButton("打开");
    JButton save = new JButton("保存");
    JButton rote1 = new JButton("逆时针");
    JButton rote = new JButton("顺时针");
    JButton flipX = new JButton("水平翻转");
    JButton flipY = new JButton("垂直翻转");
    JButton zoomIn = new JButton("放大");
    JButton zoomOut = new JButton("缩小");
    public TransformFrame() {
        setTitle("Java图片处理");
        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = getContentPane();
        canvas = new TransPanel();
        contentPane.add(canvas, "Center");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(open);
        open.addActionListener(this);
        buttonPanel.add(save);
        save.addActionListener(this);
        buttonPanel.add(rote1);
        rote1.addActionListener(this);
        buttonPanel.add(rote);
        rote.addActionListener(this);
        buttonPanel.add(flipX);
        flipX.addActionListener(this);
        buttonPanel.add(flipY);
        flipY.addActionListener(this);
        buttonPanel.add(zoomIn);
        zoomIn.addActionListener(this);
        buttonPanel.add(zoomOut);
        zoomOut.addActionListener(this);
        contentPane.add(buttonPanel, "North");
    }
    @Override
	public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == rote1) {
            canvas.setRotate1();
        }
        if (source == rote) {
            canvas.setRotate();
        } else if (source == flipX) {
            canvas.flipX();
        } else if (source == flipY) {
            canvas.flipY();
        } else if (source == zoomIn) {
            canvas.zoomIn();
        } else if (source == zoomOut) {
            canvas.zoomOut();
        } else if (source == save) {
            canvas.save();
        }
        else if(source==open){
        	canvas.open();
        }
    }
    private TransPanel canvas;
}
class TransPanel extends JPanel {
    JTextPane jTextPane1 = new JTextPane();
    //水平翻转比例的标志。-1表示需要进行水平翻转
    int m_nFlipXScale = 1;
    //垂直翻转比例的标志。-1表示需要进行垂直翻转
    int m_nFlipYScale = 1;
    //旋转的角度。因为工程需要，代码中直接写成了90，可以根据具体需要动态修改，以符合实际情况
    int roteAngle = 0;
    //缩放比例。默认的比例0表示没有翻转，具体的翻转大小通过一个方法:getZoomSize()获取
    int zoomLevel = 0;
    public TransPanel() {
        img = new ImageIcon("").getImage();
    }
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        drawTransImage(g, img.getWidth(this), img.getHeight(this), zoomLevel);
    }
    public void drawTransImage(Graphics g, int drawx, int drawy, int zoom) {
        int x = 0;
        int y = 0;
        int w = img.getWidth(this);
        int h = img.getHeight(this);
        int zoomw = getZoomSize(w, zoom);
        int zoomh = getZoomSize(h, zoom);
        int xPos = 0;
        int yPos = 0;
        if (m_nFlipXScale == -1) {
            xPos = -zoomw;
        }
        if (m_nFlipYScale == -1) {
            yPos = -zoomh;
        }
        Graphics2D g2 = (Graphics2D) g;
        //转换坐标原点。这步不要也成，但是将当前位置转换为坐标原点后，可以节省好多计算步骤，非常好用。
        //不过记得用完了以后，一定要把原点转换回来，要不然其他地方就乱了
        g2.translate(drawx, drawy);
        if (roteAngle != 0) {
            g2.rotate(Math.toRadians(m_nFlipXScale * m_nFlipYScale * roteAngle), zoomw >> 1, zoomh >> 1);
        }
        //上面的m_nFlipXScale * m_nFlipYScale需要特殊说明一下：因为实际使用中，可能遇到各种组合的情况，比如
        //先flipX或者flipY以后然后再旋转，这时候，图片的旋转方向就会出现错误，加上这段代码可以保证无论使用哪种组合
        //操作方式，都保证在旋转图片的时候是按照顺时针的方向进行旋转。
        if (m_nFlipXScale == -1) {
            g2.scale(-1, 1);//第一个值表示水平，-1表示等宽水平翻转，Math.abs(m_nFlipXScale)的值越大，出来的图片就越宽
        }
        if (m_nFlipYScale == -1) {
            g2.scale(1, -1);//第二个值表示垂直，-1表示等高垂直翻转，Math.abs(m_nFlipYScale)的值越大，出来的图片就越高
        }        //显示图片
        g2.drawImage(img, xPos, yPos, xPos + zoomw, yPos + zoomh, x, y, w, h, null);
        g2.translate(-drawx, -drawy);
    }
    //实现逆时针的方法
    public void setRotate1() {
        roteAngle -= 90;
        roteAngle %= 360;
        repaint();
    }
    //实现顺时针的方法
    public void setRotate() {
        roteAngle += 90;
        roteAngle %= 360;
        repaint();
    }
    //实现水平移动的方法
    public void flipX() {
        m_nFlipXScale = -m_nFlipXScale;
        repaint();
    }
    public void flipY() {
        m_nFlipYScale = -m_nFlipYScale;
        repaint();
    }
    //放大的方法
    public void zoomIn() {
        zoomLevel++;
        repaint();
    }
    //缩小的方法
    public void zoomOut() {
        zoomLevel--;
        repaint();
    }
    public void open(){//
    	JFileChooser chooser=new JFileChooser();
//    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int result=chooser.showOpenDialog(null);
    	if(result==JFileChooser.APPROVE_OPTION){
    	String filePath=chooser.getSelectedFile().getPath();
    	JFrame frame = Imagedit.getFrame();
    	frame.add(new DivideImage(new File(filePath)));
    	img = new ImageIcon(filePath).getImage();
//    	System.out.println(frame);
    	frame.setVisible(true);
    	System.out.println(filePath);
    	}
    }
    /**
     * 保存图片的方法
     */
    public void save() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String savefile = fc.getSelectedFile().getPath();
            if (savefile == null) {
                return;
            } else {
                String docToSave = jTextPane1.getText();
                if (docToSave != null) {
                    ObjectOutputStream fstrm = null;
                    BufferedOutputStream ostrm = null;
                    try {
                        fstrm = new ObjectOutputStream(new FileOutputStream(savefile));
                        ostrm = new BufferedOutputStream(fstrm);
                        byte[] bytes = null;
                        try {
                            bytes = docToSave.getBytes();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        ostrm.write(bytes);
                    } catch (IOException io) {
                        System.err.println("IOException: " +
                                io.getMessage());
                    } finally {
                        try {
                            ostrm.flush();
                            fstrm.close();
                            ostrm.close();
                        } catch (IOException ioe) {
                            System.err.println("IOException: " +
                                    ioe.getMessage());
                        }
                    }
                }
            }
        }
        return;
    }
    public static final int getZoomSize(int sourceSize, int zoomLevel) {
        if (zoomLevel == 0) {
            return sourceSize;
        } else if (zoomLevel < 0) {
            return sourceSize / (Math.abs(zoomLevel) + 1);
        } else {
            return sourceSize * (zoomLevel + 1);
        }
    }
    private Image img;
}
