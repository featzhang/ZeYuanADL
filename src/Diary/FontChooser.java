package Diary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontChooser extends JComponent
{
	public static final int APPROVE_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	public static final int ERROR_OPTION = -1;	
	private static final Font DEFAULTFONT = new Font("Dialog",Font.PLAIN,13);	
	private Map<String, Integer> styleMap = new HashMap<String, Integer>(4);
	private Map<Integer, String> styleMap2 = new HashMap<Integer, String>(4);
	private Vector<String> fontVector = null;
	private Map<JList, JTextField> utilMap = new HashMap<JList, JTextField>();	
	private int returnValue = ERROR_OPTION;	
	private Action approveAction = null;
	private Action cancelAction = null;	
	private Font seletedFont = null;	
	private JButton approveButton = null;
	private JButton cancelButton = null;	
	private JList fontList = null;
	private JList styleList = null;
	private JList sizeList = null;	
	private JTextField fontNameField = null;
	private JTextField styleField = null;
	private JTextField sizeField = null;	
	private JLabel preViewLabel = null;	
	private JDialog dialog = null;
	
	public FontChooser()
	{
		this(null);
	}
	
	public FontChooser(Font currentFont)
	{		
		initActions();
		initComponents();
		layoutComponets();
		setSeletedFont(currentFont);
	}

	private void initActions()
	{
		approveAction = new ApproveAction();
		cancelAction = new CancelAction();
	}
	
	private void initComponents()
	{
		String[] styles = {"正常","粗体","斜体","粗斜体"};
		styleMap.put(styles[0], Font.PLAIN);
		styleMap2.put(Font.PLAIN, styles[0]);
		styleMap.put(styles[1], Font.BOLD);
		styleMap2.put(Font.BOLD, styles[1]);
		styleMap.put(styles[2], Font.ITALIC);
		styleMap2.put(Font.ITALIC, styles[2]);
		styleMap.put(styles[3], Font.BOLD+Font.ITALIC);
		styleMap2.put(Font.BOLD+Font.ITALIC, styles[3]);
		
		approveButton = new JButton(approveAction);
		cancelButton = new JButton(cancelAction);
		
		String[] ts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontVector = new Vector<String>();
		for (String s : ts)
		{
			fontVector.add(s);
		}
		ListSelectionListener lstl = new ListSelectionHanlder();
		Border border = new EmptyBorder(5,3,5,2);
		fontList = new JList(fontVector);
		fontList.setVisibleRowCount(8);
		fontList.setBorder( border );
		
		styleList = new JList(styles);
		styleList.setVisibleRowCount(4);
		styleList.setBorder( border );
		
		Vector<Integer> sizeVector = new Vector<Integer>();
		for (int i = 1; i < 73; i++)
		{
			sizeVector.add(i);			
		}
		sizeList = new JList(sizeVector);
		sizeList.setBorder( border );
		
		fontList.addListSelectionListener(lstl);
		styleList.addListSelectionListener(lstl);
		sizeList.addListSelectionListener(lstl);
		
		fontNameField = new JTextField(15);
		styleField = new JTextField(10);
		sizeField =new JTextField(5);
		preViewLabel = new JLabel("字体预览 Font PreView");
		preViewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		preViewLabel.setVerticalAlignment(SwingConstants.CENTER);
		preViewLabel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("字体预览"), new EmptyBorder(10,10,10,10)));
		preViewLabel.setPreferredSize(new Dimension(300, 125));
		//--- put the components to map ---
		utilMap.put(fontList, fontNameField);
		utilMap.put(styleList, styleField);
		utilMap.put(sizeList, sizeField);
	}
	
	private void layoutComponets()
	{
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets.set(10, 10, 10, 5);
		JLabel label = new JLabel("字体:(F)");
		label.setLabelFor(fontNameField);
		label.setDisplayedMnemonic(KeyEvent.VK_F);
		this.add(label, gbc);
		label = new JLabel("风格:(Y)");
		label.setLabelFor(styleField);
		label.setDisplayedMnemonic(KeyEvent.VK_Y);
		gbc.insets.left = 0;
		this.add(label, gbc);
		label = new JLabel("大小:(S)");
		label.setLabelFor(sizeField);
		label.setDisplayedMnemonic(KeyEvent.VK_S);
		gbc.insets.set(10, 0, 10, 10);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add( label, gbc);
		
		gbc.gridwidth = 1;
		gbc.insets.set(0, 10, 5, 5);
		this.add( fontNameField, gbc);
		gbc.insets.left = 0;
		this.add( styleField, gbc);
		gbc.insets.right = 10;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add( sizeField, gbc);		
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.insets.set(0, 10, 10, 5);
		JScrollPane scroller = new JScrollPane(fontList);
		this.add( scroller, gbc);
		
		gbc.insets.left = 0;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		
		scroller = new JScrollPane(styleList);
		this.add( scroller, gbc);
		gbc.insets.right = 10;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add( new JScrollPane(sizeList), gbc);
		
		gbc.insets.left = 10;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		this.add( preViewLabel, gbc);
		
		
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		Box p = Box.createVerticalBox();
		p.add(approveButton);
		p.add(Box.createVerticalStrut(10));
		p.add(cancelButton);
		this.add( p, gbc);
		
	}

	public Font getSeletedFont()
	{
		return seletedFont;
	}
	
	public void setSeletedFont(Font font)
	{
		if ( font != null)
		{
			this.seletedFont = font;
		}else {
			this.seletedFont = DEFAULTFONT;
		}
		fontList.setSelectedValue(seletedFont.getFamily(), true);
		styleList.setSelectedValue( styleMap2.get(seletedFont.getStyle()), true);
		sizeList.setSelectedValue(seletedFont.getSize(), true);
	}
	
	public int showChooseDialog(Component parent, Font currentFont)
	{
		setSeletedFont(currentFont);
		dialog = createDialog(parent);
		dialog.addWindowListener(new WindowAdapter() {
	    @Override
		public void windowClosing(WindowEvent e) {
	    	returnValue = CANCEL_OPTION;
	    	dialog.dispose();
	    }
		});
		returnValue = ERROR_OPTION;
		dialog.setVisible(true);
		return returnValue;
	}
	
	private JDialog createDialog(Component parent)
	{
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(parent),true);
		dialog.setTitle("Font Chooser");
		Container c = dialog.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(this, BorderLayout.CENTER);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		dialog.setMinimumSize(dialog.getSize());
		return dialog;
	}
	
	private void seleteUpdated()
	{
		Object nameObject = fontList.getSelectedValue();
		Object styleObject = styleList.getSelectedValue();
		Object sizeObject = sizeList.getSelectedValue();
		if ( nameObject == null || styleObject == null || sizeObject == null)
		{
			return;
		}
		String name = nameObject.toString();
		int style = styleMap.get(styleObject);
		int size = 10;
		try
		{
			size = Integer.parseInt(sizeField.getText());
		} catch (Exception e)
		{
			sizeField.setText(""+size);
		}
		seletedFont = new Font(name,style,size);
		preViewLabel.setFont(seletedFont);

	}
	
	
	private class ApproveAction extends AbstractAction
	{
		ApproveAction()
		{
			this("确定");
		}
		ApproveAction(String name)
		{
			super(name);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			returnValue = APPROVE_OPTION;
			if ( dialog != null)
			{
				dialog.setVisible(false);
				dialog.dispose();
			}
		}
	}
	
	private class CancelAction extends AbstractAction
	{
		CancelAction()
		{
			this("取消");
		}
		CancelAction(String name)
		{
			super(name);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			returnValue = CANCEL_OPTION;
			if ( dialog != null)
			{
				dialog.setVisible(false);
				dialog.dispose();
			}
		}
	}
	
	private class ListSelectionHanlder implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			JList list = (JList)e.getSource();
			JTextField jtf = utilMap.get(list);
			if ( jtf != null)
			{
				jtf.setText(list.getSelectedValue().toString());
			}
			seleteUpdated();
		}
		
	}
	
	public static void main(String args[])
	{
		FontChooser fc = new FontChooser();
		int i = fc.showChooseDialog(null, null);
		if ( i == FontChooser.APPROVE_OPTION)
		{
			System.out.println(fc.getSeletedFont());
		}
		System.out.println(i);
	}
}
