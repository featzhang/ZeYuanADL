/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import components.MainFrame;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import util.AResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Icon;

/**
 *
 * @author Administrator
 */
public class MiniAction extends ADLGuiAction {

    private javax.swing.JFrame frame;

    public MiniAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Mini_String);
    }

    public MiniAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        this.frame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_F5);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("miniSystemTray");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SystemTray.isSupported()) {
            frame.setVisible(false);
            SystemTray systemTray = SystemTray.getSystemTray();// 获取表示桌面托盘区的
            Image image = AResource.getAppImage();
            PopupMenu popupMenu = new PopupMenu();
            MenuItem exitItem = new MenuItem("关闭");
            MenuItem startItem = new MenuItem("显示‘泽苑通讯录’主窗口");

            popupMenu.add(startItem);
            popupMenu.add(exitItem);
            final TrayIcon trayIcon = new TrayIcon(image, "泽苑通讯录 0.2.0",
                    popupMenu);
            trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        frame.setVisible(!(frame.isVisible()));
                        System.out.println("右击");
                        SystemTray.getSystemTray().remove(trayIcon);
                    }
                }
            });
            exitItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        System.exit(0);
                    } catch (Exception ex) {
                    }
                }
            });
            startItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(true);
                    SystemTray.getSystemTray().remove(trayIcon);
                }
            });
            try {
                systemTray.add(trayIcon); // 将 TrayIcon 添加到 SystemTray。
            } catch (AWTException f) {
                System.err.println(f);
            }
        }
    }
}
