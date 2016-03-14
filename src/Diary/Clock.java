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

        t = new Thread(this); // ʵ�����߳�
        t.start(); // �����߳�
    }

    @Override
    public void paint(Graphics g) {
        Font f = new Font("����", Font.BOLD, 16);
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy'��'MM'��'dd'��'HH:mm:ss");// ��ʽ��ʱ����ʾ����
        Calendar now = Calendar.getInstance();
        time = SDF.format(now.getTime()); // �õ���ǰ���ں�ʱ��
        g.setFont(f);
        g.drawString(time, 1, 25);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // ����1����
            } catch (InterruptedException e) {
                System.out.println("�쳣");
            }
            this.repaint(100);
        }
    }
}
