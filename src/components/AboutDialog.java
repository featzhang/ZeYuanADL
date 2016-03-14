package components;

import ui.ADialog;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import ui.AButton;
import util.AResource;

public class AboutDialog extends ADialog {

    private static final long serialVersionUID = 3456677778l;

    public AboutDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JButton jButton1 = new AButton("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("关于");

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setText("产品版本：泽苑通讯录 0.2.1");

        jLabel2.setText("<html><body>千里马崛起湟水河畔驰骋万里，大鹏鸟振翅昆仑山颠一飞冲天。<br>“泽苑通讯录”采用Java开发完成，首次发布仅作内部测试的版本历时(2009年12月-1月)两个月开发完成，意在熟悉Java语言基础。 <br>2010年5月进行改造。有关详细信息请登录<a href='http://blog.163.com/zuofeng_zhang@yeah/blog/static/1197028912010411102828648/'>“泽苑通讯录”帮助网站</a>。</body></html>");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/welcome.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setText("-----青海大学 自动化 张作峰 2010年5月");

        jButton1.setText("关闭");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, 0, 0, Short.MAX_VALUE)
                            .addGap(24, 24, 24))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(87, 87, 87))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        try {
            Desktop.getDesktop().browse(new URI(AResource.getAppHomePage()));
        } catch (IOException ex) {
        } catch (URISyntaxException ex) {
        }
}//GEN-LAST:event_jLabel2MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
}//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
