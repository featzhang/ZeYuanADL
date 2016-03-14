package Diary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;
import javax.swing.undo.UndoManager;
import ui.ATextArea;

public class NotePadPanel extends JPanel implements DocumentListener {

    private JTextArea logContentArea;
    private Calendar currentCalendar = Calendar.getInstance();
    private JButton undoButton, redoButton, fontButton, saveButton;
    private UndoManager undoManager;
    private String UNDO_STRING = "撤销";
    private String Redo_STRING = "重做";
    private String Save_STRING = "保存";

    public NotePadPanel() {
        setOpaque(false);
        undoManager = new UndoManager();
        initCompenont();
        loadAction();
    }

    public void setCurrentCalendar(Calendar currentCalendar) {
        this.currentCalendar = currentCalendar;
    }

    private void initCompenont() {
        setLayout(new BorderLayout());
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane();
        logContentArea = new ATextArea();
        logContentArea.setColumns(65);
        logContentArea.setRows(20);
        logContentArea.setOpaque(false);
        logContentArea.setLineWrap(true);
        textAreaPanel.add(logContentArea);
        scrollPane.setViewportView(textAreaPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        saveButton = new JButton("保存");
        saveButton.setAction(new SaveAction());
        saveButton.setIcon(DiaryResource.getImageIcon("save"));
        saveButton.setContentAreaFilled(false);
        saveButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonPanel.add(saveButton);
        undoButton = new JButton("撤销");
        undoButton.setAction(new UndoAction());
        undoButton.setIcon(DiaryResource.getImageIcon("undo"));
        undoButton.setContentAreaFilled(false);
        undoButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonPanel.add(undoButton);
        redoButton = new JButton("重做");
        redoButton.setAction(new RedoAction());
        redoButton.setIcon(DiaryResource.getImageIcon("redo"));
        redoButton.setContentAreaFilled(false);
        redoButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonPanel.add(redoButton);
        fontButton = new JButton("字体");
        fontButton.setIcon(DiaryResource.getImageIcon("font"));
        fontButton.setContentAreaFilled(false);
        fontButton.setHorizontalTextPosition(SwingConstants.RIGHT);

        buttonPanel.add(fontButton);
    }

    private File getFileName() {
        String fileSeparator = System.getProperty("file.separator");
        Date time = currentCalendar.getTime();
        String dateFormatString = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        String format = dateFormat.format(time);
        String fileNameString = "LOG-" + format + "-DEFAULT.txt";
        fileNameString = "DATABASE" + fileSeparator + fileNameString;
        String currentDir = System.getProperty("user.dir");
        fileNameString = currentDir + fileSeparator + fileNameString;
        return new File(fileNameString);
    }

    private void saveNote() {
        File file = getFileName();
        File parent = file.getParentFile();
        System.out.println(parent);
        System.out.println(parent.isFile());
        if (!parent.exists()) {
            parent.mkdirs();
            System.out.println(parent.exists());
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }
        Document doc = logContentArea.getDocument();
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("GBK"));
            Segment text = new Segment();
            text.setPartialReturn(true);
            int charsLeft = doc.getLength();
            int offset = 0;
            while (charsLeft > 0) {
                doc.getText(offset, Math.min(4096, charsLeft), text);
                out.write(text.array, text.offset, text.count);
                charsLeft -= text.count;
                offset += text.count;
            }
            out.flush();
        } catch (IOException e) {
        } catch (BadLocationException e) {
        } finally {
            try {
                out.close();
            } catch (IOException e) {
            }
        }

    }

    public void openLogFile() {
        logContentArea.setText("");
        File f = getFileName();
        if (f.exists()) {
            Document doc = logContentArea.getDocument();
            BufferedReader in = null;
            try {
                FileInputStream fis = new FileInputStream(f);
                in = new BufferedReader(new InputStreamReader(fis, Charset.forName("GBK")));

                char[] buff = new char[4096];
                int nch;
                while ((nch = in.read(buff, 0, buff.length)) != -1) {
                    String s = new String(buff, 0, nch);
                    doc.insertString(doc.getLength(), s, null);
                }
            } catch (IOException e) {
            } catch (BadLocationException e) {
                System.err.println(e.getMessage());
            } catch (OutOfMemoryError e) {
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        } else {
            logContentArea.setText("");
        }
        undoButton.setEnabled(undoManager.canUndo());
    }

    private void loadAction() {
        logContentArea.getDocument().addUndoableEditListener(undoManager);
        InputMap inputMap = logContentArea.getInputMap();
        ActionMap actionMap = logContentArea.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), Save_STRING);
        actionMap.put(Save_STRING, new SaveAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK), UNDO_STRING);
        actionMap.put(UNDO_STRING, new UndoAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK), Redo_STRING);
        actionMap.put(Redo_STRING, new RedoAction());

        fontButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FontChooser fc = new FontChooser();
                int i = fc.showChooseDialog(null, logContentArea.getFont());
                if (i == FontChooser.APPROVE_OPTION) {
                    System.out.println(fc.getSeletedFont());
                    logContentArea.setFont(fc.getSeletedFont());
                }
                System.out.println(i);
            }
        });
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        saveButton.setEnabled(true);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        saveButton.setEnabled(true);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        saveButton.setEnabled(true);
    }

    private class UndoAction extends javax.swing.AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
            undoButton.setEnabled(undoManager.canUndo());
            redoButton.setEnabled(undoManager.canRedo());
        }
    }

    private class SaveAction extends javax.swing.AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveNote();
        }
    }

    private class RedoAction extends javax.swing.AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
            undoButton.setEnabled(undoManager.canUndo());
            redoButton.setEnabled(undoManager.canRedo());
        }
    }
}
