package FileSearch;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import util.AResource;

public class FileSearchPanel extends javax.swing.JPanel {

    private int countFiles;
    private String pathString, filterString;
    private boolean searchRunnable = true;
    private java.util.Hashtable ht = new java.util.Hashtable();
    private String ZEYUANSTRING = "泽苑桌面搜索";
    private String currentPath = System.getProperty("user.home");
    private Image backgroundImage;

    public FileSearchPanel() {
        initComponents();
        directoryText.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                directoryTextContainerChanged();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                directoryTextContainerChanged();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                directoryTextContainerChanged();
            }

            private void directoryTextContainerChanged() {
                if (!directoryText.getText().trim().isEmpty()) {
                    String s = directoryText.getText().trim();
                    java.io.File file = new java.io.File(s);
                    if (!file.isDirectory()) {
                        statusShow1.setText("“" + s + "”不是有效路径！");
                    } else {
                        statusShow1.setText("路径正确！");
                    }
                } else {
                    statusShow1.setText("请输入或选择“查找路径”");
                }
            }
        });
        ht.clear();
    }

    public FileSearchPanel(ImageIcon photo) {
        this();
        backgroundImage = photo.getImage();
    }

    private void doSearch(String filter, String path) {
        if (searchRunnable) {
            java.io.File file = new java.io.File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    java.io.File[] fileArray = file.listFiles();
                    if (fileArray != null) {
                        java.io.File file1;
                        for (int i = 0; i < fileArray.length; i++) {
                            file1 = fileArray[i];
                            if (file1.isDirectory() && !file1.isHidden()) {
                                doSearch(filter, file1.getPath());
                            } else {
                                //最终筛选条件
                                if (file1.getName().indexOf(filter) >= 0 && !file1.isHidden()) {
                                    //最终执行语句
                                    if (!searchRunnable) {
                                        break;
                                    }
                                    countFiles++;
                                    resultList.add(file1.getName());
                                    ht.put(countFiles - 1, file1.getPath());
                                    resultList.select(resultList.getItemCount() - 1);
                                }
                            }
                            statusShow1.setText(file1.getPath());
                        }
                        statusShow2.setText("搜索到的文件数量:" + countFiles);
                    }
                } else {
                    statusShow1.setText("无法打开此路径!");
                }
            } else {
                statusShow2.setText("您所选择的路径无法打开!");
            }
        } else {
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultListPopupMenu = new javax.swing.JPopupMenu();
        openMenuItem = new javax.swing.JMenuItem();
        folderMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        copyMenuItem = new javax.swing.JMenuItem();
        moveMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        popupMenuSeparator = new javax.swing.JPopupMenu.Separator();
        renameMenuItem = new javax.swing.JMenuItem();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        directoryText = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        textFilter = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        statusShow2 = new javax.swing.JLabel();
        resultList = new java.awt.List();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        statusShow1 = new javax.swing.JLabel();
        javax.swing.JLabel aboutLabel = new javax.swing.JLabel();

        openMenuItem.setText("打开");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        resultListPopupMenu.add(openMenuItem);

        folderMenuItem.setText("打开所在文件夹");
        folderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderMenuItemActionPerformed(evt);
            }
        });
        resultListPopupMenu.add(folderMenuItem);
        resultListPopupMenu.add(jSeparator2);

        copyMenuItem.setText("复制到");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        resultListPopupMenu.add(copyMenuItem);

        moveMenuItem.setText("移动到");
        moveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveMenuItemActionPerformed(evt);
            }
        });
        resultListPopupMenu.add(moveMenuItem);

        deleteMenuItem.setText("删除");
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        resultListPopupMenu.add(deleteMenuItem);
        resultListPopupMenu.add(popupMenuSeparator);

        renameMenuItem.setText("重命名");
        renameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameMenuItemActionPerformed(evt);
            }
        });
        resultListPopupMenu.add(renameMenuItem);

        resultListPopupMenu.getAccessibleContext().setAccessibleParent(resultList);

        jLabel1.setText("查找路径");

        directoryText.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
			public void focusGained(java.awt.event.FocusEvent evt) {
                directoryTextFocusGained(evt);
            }
        });

        browseButton.setText("浏览");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("全部或部分文件名");

        textFilter.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
			public void focusGained(java.awt.event.FocusEvent evt) {
                textFilterFocusGained(evt);
            }
        });

        searchButton.setText("搜索");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        statusShow2.setText("搜索到的文件数量:");

        resultList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultListMouseClicked(evt);
            }
            @Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
                resultListMousePressed(evt);
            }
            @Override
			public void mouseReleased(java.awt.event.MouseEvent evt) {
                resultListMousePressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/resources/file.png"))); // NOI18N
        jLabel3.setText("桌面文件搜索");

        statusShow1.setText("准备查找...");

        aboutLabel.setBackground(new java.awt.Color(255, 255, 255));
        aboutLabel.setForeground(new java.awt.Color(172, 168, 153));
        aboutLabel.setText("泽苑软件 zeyuansoftware@163.com");
        aboutLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aboutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(directoryText, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browseButton)
                            .addComponent(searchButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statusShow2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resultList, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statusShow1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                        .addComponent(aboutLabel)
                        .addGap(17, 17, 17)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(aboutLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(directoryText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusShow2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultList, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(statusShow1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        openFile();
    }//GEN-LAST:event_openMenuItemActionPerformed
    private void resultListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultListMousePressed
        if (resultList.getSelectedItem() != null) {
            if (evt.isPopupTrigger()) {
                resultListPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());// 显示弹出式菜单
            }
            resultListPopupMenu.requestFocus(); // 编辑区获取焦点
        }
    }//GEN-LAST:event_resultListMousePressed
    private void resultListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultListMouseClicked
        if (evt.getClickCount() == 2) {
            openFile();
        }
    }//GEN-LAST:event_resultListMouseClicked
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (searchButton.getText().equals("搜索")) {
            searchRunnable = true;
            countFiles = 0;
            resultList.removeAll();
            filterString = textFilter.getText().trim();
            pathString = directoryText.getText().trim();
            if ("".equals(filterString)) {
                statusShow1.setText("查找内容为空，将查找所有文件。");
            }
            if ("".equals(pathString) || (!new java.io.File(pathString).isDirectory())) {
                statusShow1.setText("路径名不正确！");
            } else {
                Thread searchT = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        searchButton.setText("停止");
                        ht.clear();
                        doSearch(filterString, pathString);
                        if (searchRunnable) {
                            statusShow1.setText("搜索完成。");
                        } else {
                            statusShow1.setText("搜索已停止。");
                        }
                        searchButton.setText("搜索");
                    }
                });
                searchT.start();
            }
        } else {
            searchRunnable = false;
            searchButton.setText("搜索");
        }
    }//GEN-LAST:event_searchButtonActionPerformed
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jfc.setCurrentDirectory(new java.io.File(currentPath));
        int d = jfc.showDialog(null, "选择搜索路径");
        if (d == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = jfc.getSelectedFile();
            currentPath = selectedFile.isFile() ? selectedFile.getParent() : selectedFile.getPath();
            directoryText.setText(selectedFile.getPath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void aboutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutLabelMouseClicked
        try {
            java.net.URI uri = new java.net.URI(AResource.getAppHomePage());
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (java.io.IOException ex) {
        } catch (java.net.URISyntaxException ex) {
        }
    }//GEN-LAST:event_aboutLabelMouseClicked

    private void directoryTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_directoryTextFocusGained
        statusShow1.setText("请输入或选择“查找路径”");
    }//GEN-LAST:event_directoryTextFocusGained

    private void textFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFilterFocusGained
        statusShow1.setText("请输入要查找的内容");
    }//GEN-LAST:event_textFilterFocusGained

    private void folderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderMenuItemActionPerformed
        openFold();
    }//GEN-LAST:event_folderMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        int n = resultList.getSelectedIndex();
        String filePathName = (String) ht.get(n);
        java.io.File oldFile = new java.io.File(filePathName);
        if (oldFile.exists()) {
            javax.swing.JFileChooser jFileChooser = new javax.swing.JFileChooser();
            jFileChooser.setMultiSelectionEnabled(false);
            jFileChooser.setDialogTitle(ZEYUANSTRING);
            jFileChooser.setCurrentDirectory(new java.io.File(currentPath));
            int fileChooserOption = jFileChooser.showSaveDialog(resultList);
            java.io.File newFile;
            if (fileChooserOption == javax.swing.JFileChooser.APPROVE_OPTION) {
                newFile = jFileChooser.getSelectedFile();
                String oldNameString = newFile.getPath();
                String oldFileNameString = oldFile.toString();
                String oldFileNameEndString = oldFileNameString.substring(oldFileNameString.lastIndexOf("."));
                if (!oldNameString.endsWith(oldFileNameEndString)) {
                    oldNameString += oldFileNameEndString;
                    newFile = new java.io.File(oldNameString);
                }
                if (!oldFile.equals(newFile)) {
                    copyFile(oldFile, newFile);
                    currentPath = newFile.isFile() ? newFile.getParent() : newFile.getPath();
                }
                String s = "<html>文件已成功复制!<br>原文件：" + oldFile + "<br>新文件：" + newFile + "<br></html>";
                javax.swing.JOptionPane.showMessageDialog(resultList, s, ZEYUANSTRING, javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(resultList, "该文件已被删除！", "警告", javax.swing.JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void moveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveMenuItemActionPerformed
        int n = resultList.getSelectedIndex();
        String filePathName = (String) ht.get(n);
        java.io.File oldFile = new java.io.File(filePathName);
        if (oldFile.exists()) {
            javax.swing.JFileChooser jFileChooser = new javax.swing.JFileChooser();
            jFileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
            jFileChooser.setDialogTitle(ZEYUANSTRING);
            jFileChooser.setCurrentDirectory(new java.io.File(currentPath));
            int fileChooserOption = jFileChooser.showOpenDialog(resultList);
            java.io.File newFile;
            if (fileChooserOption == javax.swing.JFileChooser.APPROVE_OPTION) {
                newFile = jFileChooser.getSelectedFile();
                if (!newFile.isFile()) {
                    newFile = new java.io.File(newFile.toString(), oldFile.getName());
                }
                String oldNameString = newFile.getPath();
                String oldFileNameString = oldFile.toString();
                String oldFileNameEndString = oldFileNameString.substring(oldFileNameString.lastIndexOf("."));
                if (!oldNameString.endsWith(oldFileNameEndString)) {
                    oldNameString += oldFileNameEndString;
                    newFile = new java.io.File(oldNameString);
                }
                if (!oldFile.equals(newFile)) {
                    copyFile(oldFile, newFile);
                    currentPath = newFile.isFile() ? newFile.getParent() : newFile.getPath();
                    oldFile.delete();
                    ht = htRemove(n);
                    resultList.remove(n);
                }
                String s = "<html>文件已成功移动!<br>原文件夹：" + oldFile.getParent() + "<br>新文件夹：" + newFile.getParent() + "<br></html>";
                javax.swing.JOptionPane.showMessageDialog(resultList, s, ZEYUANSTRING, javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(resultList, "该文件已被删除！", "警告", javax.swing.JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_moveMenuItemActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        int n = resultList.getSelectedIndex();
        String filePathName = (String) ht.get(n);
        java.io.File file = new java.io.File(filePathName);
        if (file.exists()) {
            int scd = javax.swing.JOptionPane.showConfirmDialog(resultList, "是否确认删除“" + file.getName() + "”？", ZEYUANSTRING, javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if (scd == 0) {
                file.delete();
                ht = htRemove(n);
                resultList.remove(n);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "该文件已被删除！", "警告", javax.swing.JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void renameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameMenuItemActionPerformed
        int n = resultList.getSelectedIndex();
        String filePathName = (String) ht.get(n);
        java.io.File file = new java.io.File(filePathName);
        if (file.exists()) {
            String sIDString = javax.swing.JOptionPane.showInputDialog(null, "请输入新文件名", "重命名“" + resultList.getSelectedItem() + "”", javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if (sIDString != null) {
                String oldFileNameString = file.toString();
                String oldFileNameEndString = oldFileNameString.substring(oldFileNameString.lastIndexOf("."));
                if (!sIDString.endsWith(oldFileNameEndString)) {
                    sIDString += oldFileNameEndString;
                }
                java.io.File newFile = new java.io.File(file.getParent(), sIDString);
                file.renameTo(newFile);
                ht.put(n, newFile);
                resultList.remove(n);
                resultList.add(sIDString, n);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(resultList, "该文件已被删除！", "警告", javax.swing.JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_renameMenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JTextField directoryText;
    private javax.swing.JMenuItem folderMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem moveMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JPopupMenu.Separator popupMenuSeparator;
    private javax.swing.JMenuItem renameMenuItem;
    private java.awt.List resultList;
    private javax.swing.JPopupMenu resultListPopupMenu;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel statusShow1;
    private javax.swing.JLabel statusShow2;
    private javax.swing.JTextField textFilter;
    // End of variables declaration//GEN-END:variables

    private void openFile() {
        if (resultList.getSelectedItem() != null) {
            int n = resultList.getSelectedIndex();
            String filePathName = (String) ht.get(n);
            try {
                java.io.File file = new java.io.File(filePathName);
                if (file.exists()) {
                    java.awt.Desktop.getDesktop().open(file);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(resultList, "该文件已被删除！", "警告", javax.swing.JOptionPane.OK_OPTION);
                }

            } catch (java.io.IOException ex) {
            }
        } else {
            return;
        }
    }

    private void openFold() {
        int n = resultList.getSelectedIndex();
        String filePathName = (String) ht.get(n);
        try {
            java.io.File file = new java.io.File(filePathName);
            java.awt.Desktop.getDesktop().open(file.getParentFile());
        } catch (java.io.IOException ex) {
        }
    }

    private void copyFile(java.io.File oldFile, java.io.File newFile) {
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream(oldFile);
            byte[] b = new byte[128];
            java.io.FileOutputStream fos = new java.io.FileOutputStream(newFile);
            while ((fis.read(b)) != -1) {
                fos.write(b);
            }
            fis.close();
            fos.close();
        } catch (java.io.FileNotFoundException ex) {
        } catch (java.io.IOException ex) {
        }
    }

    private java.util.Hashtable htRemove(int n) {
        ht.remove(n);
        for (int i = n + 1; i < countFiles; i++) {
            String f = (String) ht.get(i);
            ht.put(i - 1, f);
        }
        countFiles--;
        return ht;
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
