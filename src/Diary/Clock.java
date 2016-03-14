package Diary;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;

public class Clock extends Canvas implements Runnable {

    private static final long serialVersionUID = 3660124045489727166L;
    Thread t;
    String time;
    JFrame mainFrame;

    public Clock(JFrame mf) {
        mainFrame = mf;
        setSize(195, 35);
        setForeground(Color.red);
//                setBackground(Color.BLACK);

        t = new Thread(this); // 实例化线程
        t.start(); // 调用线程
    }

    @Override
    public void paint(Graphics g) {
        Font f = new Font("宋体", Font.BOLD, 16);
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy'年'MM'月'dd'日'HH:mm:ss");// 格式化时间显示类型
        Calendar now = Calendar.getInstance();
        time = SDF.format(now.getTime()); // 得到当前日期和时间
        g.setFont(f);
        g.drawString(time, 1, 25);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // 休眠1秒钟
            } catch (InterruptedException e) {
                System.out.println("异常");
            }
            this.repaint(100);
        }
    }
}
