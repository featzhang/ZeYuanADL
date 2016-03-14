
package laboratory;
/*
�����WindowsӦ�ó������棬�������IE�ĵ�ַ������������ComboBox���ı�������ʱ��
���������б����Զ��г���ƥ�����Ŀ�����ҽ���ƥ�����Ŀ��ʾ��������С�
��Java���и�JComboBox�࣬������ʵ������ѡ���������ѡ��
����������û���ṩ�Զ����Һ���ɹ��ܡ��������ھ���   ����װ������࣬ʹ�������Զ����Һ���ɹ��ܡ�

��װ˼·���£�
1.�ȼ̳�һ��JComboBox�࣬����setEditableΪtrue.   �����Ļ����û��ſ�����combobox���������֡�
2.����֪��combobox���������һ��JTextField,   ����ͨ��comboBox.getEditor().getEditorComponent()ȡ������ı���
3.Ϊ����ı������һ��KeyListener.
4.���û����ı����а���ʱ����ⷢkeyReleased�¼�������������¼���д��Ҫ��ʵ���Զ����Һ���ɵĴ��롣
˼�������ô�򵥣����Զ����ҵ��㷨���κ�һ���Ա�̲�İ�����˶�����д�����������г������ĳ�����룺
 */

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class JAutoCompleteComboBox extends JComboBox {

    private AutoCompleter completer;

    public JAutoCompleteComboBox() {
        super();
        addCompleter();
    }

    public JAutoCompleteComboBox(ComboBoxModel cm) {
        super(cm);
        addCompleter();
    }

    public JAutoCompleteComboBox(Object[] items) {
        super(items);
        addCompleter();
    }

    public JAutoCompleteComboBox(List v) {
        super((Vector) v);
        addCompleter();
    }

    private void addCompleter() {
        setEditable(true);
        completer = new AutoCompleter(this);
    }

    public void autoComplete(String str) {
        this.completer.autoComplete(str, str.length());
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

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        JFrame frame = new JFrame();
        Object[] items = new Object[]{"abc ", "aab ", "aba ", "hpp ", "pp ", "hlp "};
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        JComboBox cmb = new JAutoCompleteComboBox(model);
        model.addElement("abc ");
        model.addElement("aab ");
        model.addElement("aba ");
        model.addElement("hpp ");
        model.addElement("pp ");
        model.addElement("hlp ");
        frame.getContentPane().add(cmb);
//        frame.setSize(400, 80);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/**
 *   �Զ���������Զ��ҵ���ƥ�����Ŀ���������б����ǰ�档
 *   @author   Turbo   Chen
 */
class AutoCompleter implements KeyListener, ItemListener {

    private JComboBox comboBox = null;
    private JTextField textField = null;
    private ComboBoxModel comboBoxModel = null;

    public AutoCompleter(JComboBox comboBox) {
        this.comboBox = comboBox;
        textField = (JTextField) comboBox.getEditor().getEditorComponent();
        textField.addKeyListener(this);
        comboBoxModel = comboBox.getModel();
        comboBox.addItemListener(this);
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
     *   �Զ���ɡ�������������ݣ����б����ҵ����Ƶ���Ŀ.
     */
    protected void autoComplete(String strf, int caretPosition) {
        Object[] opts;
        opts = getMatchingOptions(strf.substring(0, caretPosition));
        if (comboBox != null) {
            comboBoxModel = new DefaultComboBoxModel(opts);
            comboBox.setModel(comboBoxModel);
        }
        if (opts.length > 0) {
            textField.setCaretPosition(caretPosition);
            textField.setSelectionStart(caretPosition);
            textField.setSelectionEnd(opts.length);
            if (comboBox != null) {
                try {
                    comboBox.showPopup();
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     *
     *   �ҵ����Ƶ���Ŀ,   ���ҽ�֮���е��������ǰ�档
     *   @param   str
     *   @return   ����������Ŀ���б�
     */
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
}
