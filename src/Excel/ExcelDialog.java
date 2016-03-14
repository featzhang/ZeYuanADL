package Excel;

import ui.ADialog;
import ui.ImagePanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ExcelDialog extends ADialog {

    private static final long serialVersionUID = 12324324532l;
    private JButton sureButton;
    private JButton cancelButton;
    private JLabel statueLabel;
    private String currentFile;
    private int excelAmountOfSheets;
    private boolean[] fitsheet;
    private int[] excelSheetRows;

    public ExcelDialog() {
        Container container = getContentPane();
        ImagePanel imagePanel = new ImagePanel(getClass().getClassLoader().getResource("adl0_2/passwordframebackground.png").getFile());
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        JProgressBar progressBar = new JProgressBar();
        sureButton = new JButton("确定");
        cancelButton = new JButton("取消");
        ImagePanel panel = new ImagePanel(getClass().getClassLoader().getResource("adl0_2/passwordframebackground.png").getFile());
        statueLabel = new JLabel("准备从Excel文件中导入");
        panel.add(progressBar);
        panel.add(sureButton);
        panel.add(cancelButton);
        imagePanel.add(panel, BorderLayout.CENTER);
        imagePanel.add(statueLabel, BorderLayout.SOUTH);
        container.add(imagePanel);
        setUndecorated(true);
        pack();
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
//
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception ex) {
//            Logger.getLogger(ExcelDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        new ExcelDialog();
//    }
}
