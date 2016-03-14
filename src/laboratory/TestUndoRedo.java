package laboratory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.undo.UndoManager;

public class TestUndoRedo extends JFrame {

    private JTextArea text;
    private JButton undoButton, redoButton;
    private UndoManager undoManager;
    private Container container;

    public TestUndoRedo() {
        super("Undo&Redo");
        undoManager = new UndoManager();
        container = getContentPane();
        text = new JTextArea(10, 15);
        text.setWrapStyleWord(true);
        text.getDocument().addUndoableEditListener(undoManager);
        undoButton = new JButton("³·Ïú");
        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
        redoButton = new JButton("ÖØ×ö");

        redoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });

        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout());

        panel.add(undoButton);

        panel.add(redoButton);
        container.add(text);
        container.add(panel, BorderLayout.SOUTH);

        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String args[]) {
        TestUndoRedo test = new TestUndoRedo();
    }
}
