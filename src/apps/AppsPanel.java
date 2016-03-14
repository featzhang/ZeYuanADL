package apps;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.AResource;

public class AppsPanel extends JPanel {

    private int appsCount = 0;
    private JButton addButton = null;
    private Image backgroundImage = null;

    public AppsPanel() {
        initCompent();
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        addButton = new JButton();

        int rows = 0;
        int columns = 0;
        rows = appsCount / 3 + 2;
        columns = 3;
        setLayout(new GridLayout(rows + 1, columns, 20, 20));
        addButton.setIcon(AResource.getImageIcon("cross"));
        addButton.setRequestFocusEnabled(false);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        add(addButton);
        addButton.setToolTipText("添加新的应用");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                AddNewAppDialog dialog = new AddNewAppDialog(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
        for (int i = appsCount; i <= 9; i++) {
            JButton button = new JButton();
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setEnabled(false);
            add(button);
        }
    }

    public AppsPanel(ImageIcon photo) {
        this();
        backgroundImage = photo.getImage();
    }

    private void initCompent() {
        File APPsDir = new File("APPs");
        if (APPsDir.exists()) {
            File[] listFiles = APPsDir.listFiles();//遍历APPs文件夹中的文件
            for (int i = 0; i < listFiles.length; i++) {
                File parentDir = listFiles[i];
                if (parentDir.isDirectory()) {
                    File[] listFiles1 = parentDir.listFiles();
                    File execFile = null;//jar文件
                    File imageFile = null;//图片文件
                    for (int j = 0; j < listFiles1.length; j++) {
                        File file2 = listFiles1[j];
                        String fileName = file2.getName();
                        if (fileName.endsWith("jar")) {
                            execFile = file2;
                        } else if (fileName.endsWith("jpg") || fileName.endsWith("png") || fileName.endsWith("gif")) {
                            imageFile = file2;
                        }
                    }
                    {//jar文件的处理
                        String name = execFile.getName();
                        final String path = execFile.getAbsolutePath();
                        JButton nameButton = new JButton();
                        nameButton.setBorderPainted(false);
                        nameButton.setContentAreaFilled(false);
                        name = name.substring(0, name.indexOf("."));
                        nameButton.setToolTipText(name);
                        if (imageFile != null) {
                            String absolutePath = imageFile.getAbsolutePath();
                            ImageIcon icon = new ImageIcon(absolutePath);
                            nameButton.setIcon(icon);
                        } else {
                            if (name.length() > 4) {
                                name = name.substring(0, 4);
                            }
                            nameButton.setText(name);
                            System.out.println(name);
                        }

                        nameButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s = "java -jar \"" + path + "\"";
//                            System.out.println(s);
                                try {
                                    Runtime.getRuntime().exec(s);
                                } catch (IOException ex) {
                                }
                            }
                        });
                        nameButton.setSelected(false);
                        nameButton.setRequestFocusEnabled(false);
                        nameButton.setFocusable(false);
                        add(nameButton);
                        appsCount++;

                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new AppsPanel());
        frame.pack();
        frame.setVisible(true);
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
