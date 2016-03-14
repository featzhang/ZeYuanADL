package ui;

public class ATextArea extends javax.swing.JTextArea implements java.awt.event.MouseListener {

    private static final long serialVersionUID = -2308615404205560110L;
    private javax.swing.JPopupMenu pop = null; // �����˵�
    private javax.swing.JMenuItem copy = null, paste = null, cut = null; // �������ܲ˵�

    public ATextArea() {
        super();
        init();
    }

    private void init() {
        this.addMouseListener(this);
        pop = new javax.swing.JPopupMenu();
        pop.add(copy = new javax.swing.JMenuItem("����"));
        pop.add(paste = new javax.swing.JMenuItem("ճ��"));
        pop.add(cut = new javax.swing.JMenuItem("����"));
        copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke('C', java.awt.event.InputEvent.CTRL_MASK));
        paste.setAccelerator(javax.swing.KeyStroke.getKeyStroke('V', java.awt.event.InputEvent.CTRL_MASK));
        cut.setAccelerator(javax.swing.KeyStroke.getKeyStroke('X', java.awt.event.InputEvent.CTRL_MASK));
        copy.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                action(e);
            }
        });
        paste.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                action(e);
            }
        });
        cut.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                action(e);
            }
        });
        this.add(pop);
    }

    /**
     * �˵�����
     * @param e
     */
    public void action(java.awt.event.ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals(copy.getText())) { // ����
            this.copy();
        } else if (str.equals(paste.getText())) { // ճ��
            this.paste();
        } else if (str.equals(cut.getText())) { // ����
            this.cut();
        }
    }

    public javax.swing.JPopupMenu getPop() {
        return pop;
    }

    public void setPop(javax.swing.JPopupMenu pop) {
        this.pop = pop;
    }

    /**
     * ���а����Ƿ����ı����ݿɹ�ճ��
     *
     * @return trueΪ���ı�����
     */
    public boolean isClipboardString() {
        boolean b = false;
        java.awt.datatransfer.Clipboard clipboard = this.getToolkit().getSystemClipboard();
        java.awt.datatransfer.Transferable content = clipboard.getContents(this);
        try {
            if (content.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor) instanceof String) {
                b = true;
            }
        } catch (Exception e) {
        }
        return b;
    }

    /**
     * �ı�������Ƿ�߱����Ƶ�����
     *
     * @return trueΪ�߱�
     */
    public boolean isCanCopy() {
        boolean b = false;
        int start = this.getSelectionStart();
        int end = this.getSelectionEnd();
        if (start != end) {
            b = true;
        }
        return b;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            copy.setEnabled(isCanCopy());
            paste.setEnabled(isClipboardString());
            cut.setEnabled(isCanCopy());
            pop.show(this, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }
}
