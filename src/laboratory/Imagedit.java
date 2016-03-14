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
    JButton open = new JButton("��");
    JButton save = new JButton("����");
    JButton rote1 = new JButton("��ʱ��");
    JButton rote = new JButton("˳ʱ��");
    JButton flipX = new JButton("ˮƽ��ת");
    JButton flipY = new JButton("��ֱ��ת");
    JButton zoomIn = new JButton("�Ŵ�");
    JButton zoomOut = new JButton("��С");
    public TransformFrame() {
        setTitle("JavaͼƬ����");
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
    //ˮƽ��ת�����ı�־��-1��ʾ��Ҫ����ˮƽ��ת
    int m_nFlipXScale = 1;
    //��ֱ��ת�����ı�־��-1��ʾ��Ҫ���д�ֱ��ת
    int m_nFlipYScale = 1;
    //��ת�ĽǶȡ���Ϊ������Ҫ��������ֱ��д����90�����Ը��ݾ�����Ҫ��̬�޸ģ��Է���ʵ�����
    int roteAngle = 0;
    //���ű�����Ĭ�ϵı���0��ʾû�з�ת������ķ�ת��Сͨ��һ������:getZoomSize()��ȡ
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
        //ת������ԭ�㡣�ⲽ��ҪҲ�ɣ����ǽ���ǰλ��ת��Ϊ����ԭ��󣬿��Խ�ʡ�ö���㲽�裬�ǳ����á�
        //�����ǵ��������Ժ�һ��Ҫ��ԭ��ת��������Ҫ��Ȼ�����ط�������
        g2.translate(drawx, drawy);
        if (roteAngle != 0) {
            g2.rotate(Math.toRadians(m_nFlipXScale * m_nFlipYScale * roteAngle), zoomw >> 1, zoomh >> 1);
        }
        //�����m_nFlipXScale * m_nFlipYScale��Ҫ����˵��һ�£���Ϊʵ��ʹ���У���������������ϵ����������
        //��flipX����flipY�Ժ�Ȼ������ת����ʱ��ͼƬ����ת����ͻ���ִ��󣬼�����δ�����Ա�֤����ʹ���������
        //������ʽ������֤����תͼƬ��ʱ���ǰ���˳ʱ��ķ��������ת��
        if (m_nFlipXScale == -1) {
            g2.scale(-1, 1);//��һ��ֵ��ʾˮƽ��-1��ʾ�ȿ�ˮƽ��ת��Math.abs(m_nFlipXScale)��ֵԽ�󣬳�����ͼƬ��Խ��
        }
        if (m_nFlipYScale == -1) {
            g2.scale(1, -1);//�ڶ���ֵ��ʾ��ֱ��-1��ʾ�ȸߴ�ֱ��ת��Math.abs(m_nFlipYScale)��ֵԽ�󣬳�����ͼƬ��Խ��
        }        //��ʾͼƬ
        g2.drawImage(img, xPos, yPos, xPos + zoomw, yPos + zoomh, x, y, w, h, null);
        g2.translate(-drawx, -drawy);
    }
    //ʵ����ʱ��ķ���
    public void setRotate1() {
        roteAngle -= 90;
        roteAngle %= 360;
        repaint();
    }
    //ʵ��˳ʱ��ķ���
    public void setRotate() {
        roteAngle += 90;
        roteAngle %= 360;
        repaint();
    }
    //ʵ��ˮƽ�ƶ��ķ���
    public void flipX() {
        m_nFlipXScale = -m_nFlipXScale;
        repaint();
    }
    public void flipY() {
        m_nFlipYScale = -m_nFlipYScale;
        repaint();
    }
    //�Ŵ�ķ���
    public void zoomIn() {
        zoomLevel++;
        repaint();
    }
    //��С�ķ���
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
     * ����ͼƬ�ķ���
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
