package components;

import dataBase.AddressListData;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import util.AResource;

public class ADLIconButton extends JButton {

    private static final long serialVersionUID = 39082560987930759L;
    public static final Color BUTTON_COLOR1 = new Color(205, 255, 205);
    public static final Color BUTTON_COLOR2 = new Color(51, 154, 47);
    public static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
    private boolean hover;
    @SuppressWarnings({"rawtypes", "unused"})
    private AddressListData addressListData;
    private MainFrame mainFrame;

    public ADLIconButton(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        improve();
    }

    private void improve() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setToolTipText("请稍等... ...");
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(BUTTON_COLOR2);
                hover = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(BUTTON_FOREGROUND_COLOR);
                hover = true;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                DetailDialog detailDialog = new DetailDialog(mainFrame, id);
                id = Integer.parseInt(addressListData.getID());
                detailDialog.setSerial(id);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int buttonlongI = 60;
        int h = getHeight();
        int w = getWidth();
        int x = getX();
        int y = getY();
        if (h < buttonlongI) {
            h = buttonlongI;
            setBounds(x, y, w, h);
        }
        if (w < buttonlongI) {
            w = buttonlongI;
            setBounds(x, y, w, h);
        }
        float tran = 1F;
        if (!hover) {
            tran = 0.3F;
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p1;
        GradientPaint p2;
        if (getModel().isPressed()) {
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
                    new Color(100, 100, 100));
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                    new Color(255, 255, 255, 100));
        } else {
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
                    new Color(0, 0, 0));
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
                    h - 3, new Color(0, 0, 0, 50));
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                tran));
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
                h - 1, 20, 20);
        Shape clip = g2d.getClip();
        g2d.clip(r2d);
        GradientPaint gp = new GradientPaint(0.0F, 0.0F, BUTTON_COLOR1, 0.0F,
                h, BUTTON_COLOR2, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.setClip(clip);
        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, w - 3, h - 3, 18, 18);
        g2d.dispose();
        super.paintComponent(g);
    }
    private int id = -1;

    @SuppressWarnings("unused")
    public void setData(AddressListData addressListData) {
        this.addressListData = addressListData;
        id = Integer.parseInt(addressListData.getID());
        String name = addressListData.getName();
        String sex = addressListData.getSex();
        String xclass = addressListData.getXClass();
        String NO1 = addressListData.getNo1();
        String NO1Class = addressListData.getNo1Class();
        String NO2 = addressListData.getNo2();
        String NO2Class = addressListData.getNo2Class();
        String NO3 = addressListData.getNo3();
        String NO3Class = addressListData.getNo3Class();
        String address = addressListData.getAddress();
        String hometown = addressListData.getHometown();
        String qq = addressListData.getQQ();
        String email = addressListData.getEmail();
        String remarks = addressListData.getRemarks();
        if (name.length() > 6) {
            setText(name.substring(0, 6));
        } else {
            setText(name);
        }
        String s1 = "<html>姓名:" + name + "<br>";
        if (!sex.isEmpty()) {
            s1 += "性别：" + sex + "<br>";
        }
        s1 += "分组：" + xclass + "<br>";
        if (!NO1.isEmpty()) {
            s1 += "号码：" + NO1 + ":" + NO1Class + "<br>";
        }
        if (!NO2.isEmpty()) {
            s1 += "号码：" + NO2 + ":" + NO2Class + "<br>";
        }
        if (!NO3.isEmpty()) {
            s1 += "号码：" + NO3 + ":" + NO3Class + "<br>";
        }
        if (!address.isEmpty()) {
            s1 += "地址：" + address + "<br>";
        }
        if (!hometown.isEmpty()) {
            s1 += "家乡：" + hometown + "<br>";
        }
        if (!qq.isEmpty()) {
            s1 += "QQ：" + qq + "<br>";
        }
        if (!email.isEmpty()) {
            s1 += "Email：" + email + "<br>";
        }
        if (!remarks.isEmpty()) {
            s1 += "备注：" + remarks + "<br>";
        }
        s1 += "</html>";
        setToolTipText(s1);
        ImageIcon abuttonImageIcon = AResource.getImageIcon("QQ");
        setIcon(abuttonImageIcon);
    }
}
