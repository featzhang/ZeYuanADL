package laboratory;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AutoComboBox extends javax.swing.JComboBox implements KeyListener, ItemListener {

    private JTextField textField = null;
    private ComboBoxModel comboBoxModel;

    public AutoComboBox() {
        addCompleter();
    }

    public void setItems(Object[] items) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        setModel(model);
        for (int i = 0; i < items.length; i++) {
            Object object = items[i];
            model.addElement(object);
        }
        comboBoxModel = getModel();
    }

    public String getText() {
        return ((JTextField) getEditor().getEditorComponent()).getText();
    }

    public void setText(String text) {
        ((JTextField) getEditor().getEditorComponent()).setText(text);
    }

    public boolean containsItem(String itemString) {
        for (int i = 0; i < this.getModel().getSize(); i++) {
            String _item = " " + this.getModel().getElementAt(i);
            if (_item.equals(itemString)) {
                return true;
            }
        }
        return false;
    }

    private void addCompleter() {
        setEditable(true);

        textField = (JTextField) getEditor().getEditorComponent();
        textField.addKeyListener(this);
        comboBoxModel = getModel();
        addItemListener(this);
    }

    public void autoComplete(String str) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char ch = e.getKeyChar();
        if (ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch) || ch == KeyEvent.VK_DELETE) {
            return;
        }

        int caretPosition = textField.getCaretPosition();
        System.out.println(caretPosition);
        String str = textField.getText();
        if (str.length() == 0) {
            return;
        }
        autoComplete(str, caretPosition);
    }

    /**
     *   自动完成。根据输入的内容，在列表中找到相似的项目.
     */
    protected void autoComplete(String strf, int caretPosition) {
        Object[] opts;
        opts = getMatchingOptions(strf.substring(0, caretPosition));
        if (AutoComboBox.this != null) {
            comboBoxModel = new DefaultComboBoxModel(opts);
            setModel(comboBoxModel);
        }
        if (opts.length > 0) {
            textField.setCaretPosition(caretPosition);
            textField.setSelectionStart(caretPosition);
            textField.setSelectionEnd(opts.length);
            if (AutoComboBox.this != null) {
                try {
                    showPopup();
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     *   找到相似的项目,   并且将之排列到数组的最前面。
     *   @return   返回所有项目的列表。
     */
    @SuppressWarnings("rawtypes")
	protected Object[] getMatchingOptions(String str) {
        List v = new Vector();
        List v1 = new Vector();

        for (int k = 0; k < comboBoxModel.getSize(); k++) {
            Object itemObj = comboBoxModel.getElementAt(k);
            if (itemObj != null) {
                String item = itemObj.toString().toLowerCase();
                if (item.startsWith(str.toLowerCase())) {
                    v.add(comboBoxModel.getElementAt(k));
                } else {
                    v1.add(comboBoxModel.getElementAt(k));
                }
            } else {
                v1.add(comboBoxModel.getElementAt(k));
            }
        }
        for (int i = 0; i < v1.size(); i++) {
            v.add(v1.get(i));
        }
        if (v.isEmpty()) {
            v.add(str);
        }
        return v.toArray();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            int caretPosition = textField.getCaretPosition();
            if (caretPosition != -1) {
                try {
                    textField.moveCaretPosition(caretPosition);
                } catch (IllegalArgumentException ex) {
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        Object[] items = new Object[]{"abc ", "aab ", "aba ", "hpp ", "pp ", "hlp "};
        AutoComboBox autoComboBox = new AutoComboBox();
        autoComboBox.setItems(items);
        JFrame frame = new JFrame();
        frame.add(autoComboBox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
