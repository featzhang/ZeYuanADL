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
		setTitle("数据库清空");
		Container contentPane = getContentPane();
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		String str = "\t警告\n  数据库清空工具将删除数据库，"
				+ "所有数据将会丢失，建议使用前将有用数据备份，数据库清空后不可恢复，"
				+ "慎用！\n  点击“确定”执行数据库格式化，点击“取消”关闭窗口。";
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
		JButton b1 = new ui.AButton("确定");
		JButton b2 = new ui.AButton("取消");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataBase.SQLExecute("DELETE * FROM ADDRESSLISTTABLE");
				dispose();
				int i = JOptionPane.showConfirmDialog(fatherFrame,
						"数据库已经成功清空！是否继续清空‘记录分组’和‘号码分组’数据库？", "完成",
						JOptionPane.OK_CANCEL_OPTION);
				if (i == 0) {
					DataBase.SQLExecute("DELETE * FROM CLASSSET");
					DataBase.SQLExecute("DELETE * FROM NOCLASSSET");
					new ConfigManager().setDatabaseNeedInit(true);
					JOptionPane.showMessageDialog(fatherFrame,
							"‘记录分组’和‘号码分组’数据库已清空");
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
