package util;

import components.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class SingleHandler extends Thread {

    public static final int PORT = 15468;//端口号
    private MainFrame mainFrame = null;//主窗口
    private String[] args = null;//参数
    private static ServerSocket ss = null;

    public static boolean isSingle() {
        if (ss != null) {
            return true;
        }
        try {
            ss = new ServerSocket(PORT);
        } catch (BindException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static SingleHandler getHandler(MainFrame notepad, String[] args) {
        return new SingleHandler(notepad, args);
    }

    private SingleHandler(MainFrame notepad, String[] args) {
        this.mainFrame = notepad;
        this.args = args;
        setPriority(3);
    }

    @Override
    public void run() {
        if (isSingle()) {
            //System.out.println("is single !");
            handlMsg(args);
            while (true) {
                try {
                    Socket s = ss.accept();
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String str = br.readLine();
                    handleMsg(str);
                    br.close();
                    s.close();
                } catch (IOException e) {
                }
            }
        } else {
            //System.out.println("is not single !");
            try {
                Socket s = new Socket("localhost", PORT);
                PrintStream p = new PrintStream(s.getOutputStream());
                if (args.length > 0) {
                    p.println(createMsg(args));
                } else {
                    p.println("hello");
                }
                p.flush();
                p.close();
                s.close();
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }
        }
    }

    private void handleMsg(String msg) {
        if (msg == null) {
            return;
        }
        if (msg.equals("hello")) {
            mainFrame.setVisible(true);
            mainFrame.toFront();
            return;
        }
        String[] strs = msg.split(";");
        handlMsg(strs);
    }

    private void handlMsg(String[] msgs) {
        if (msgs.length < 1) {
            return;
        }
        Vector<File> vector = new Vector<File>();
        for (int i = 0; i < msgs.length; i++) {
            File file = new File(msgs[i]);
            if (file.exists() && file.isFile()) {
                vector.add(file);
            }
        }
        if (vector.isEmpty()) {
            return;
        }
//        notepad.openFiles(null, vector.toArray(new File[0]));
        mainFrame.setVisible(true);
        mainFrame.toFront();
    }

    private String createMsg(String args[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(";");
        }
        return sb.toString();
    }
}
