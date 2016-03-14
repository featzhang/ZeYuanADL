package components;

import dataBase.DataBase;
import ui.ADialog;
import util.ConfigManager;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DatabaseInitDialog extends ADialog {

	private JFrame fatherFrame;

	public DatabaseInitDialog(JFrame f0) {
		super(f0, true);
		fatherFrame = f0;
		setTitle("���ݿ����");
		Container contentPane = getContentPane();
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		String str = "\t����\n  ���ݿ���չ��߽�ɾ�����ݿ⣬"
				+ "�������ݽ��ᶪʧ������ʹ��ǰ���������ݱ��ݣ����ݿ���պ󲻿ɻָ���"
				+ "���ã�\n  �����ȷ����ִ�����ݿ��ʽ���������ȡ�����رմ��ڡ�";
		JTextArea textarea = new JTextArea(str, 4, 20);
		textarea.setOpaque(false);
		textarea.setFont(new java.awt.Font(java.awt.Font.DIALOG,
				java.awt.Font.PLAIN, 20));
		textarea.setBorder(BorderFactory.createEtchedBorder());
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		textarea.setForeground(java.awt.Color.RED);
		topPanel.add(textarea);
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		JButton b1 = new ui.AButton("ȷ��");
		JButton b2 = new ui.AButton("ȡ��");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataBase.SQLExecute("DELETE * FROM ADDRESSLISTTABLE");
				dispose();
				int i = JOptionPane.showConfirmDialog(fatherFrame,
						"���ݿ��Ѿ��ɹ���գ��Ƿ������ա���¼���顯�͡�������顯���ݿ⣿", "���",
						JOptionPane.OK_CANCEL_OPTION);
				if (i == 0) {
					DataBase.SQLExecute("DELETE * FROM CLASSSET");
					DataBase.SQLExecute("DELETE * FROM NOCLASSSET");
					new ConfigManager().setDatabaseNeedInit(true);
					JOptionPane.showMessageDialog(fatherFrame,
							"����¼���顯�͡�������顯���ݿ������");
				}
				System.out.println(i);
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		centerPanel.add(b1);
		centerPanel.add(b2);

		contentPane.add(topPanel, java.awt.BorderLayout.CENTER);
		contentPane.add(centerPanel, java.awt.BorderLayout.SOUTH);

		pack();
		setSize(350, 300);
		setLocationRelativeTo(fatherFrame);
		setVisible(true);
	}
}
