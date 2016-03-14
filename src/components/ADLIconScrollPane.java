package components;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.ImagePanel;
import util.AResource;
import dataBase.AddressListData;
import dataBase.DataBase;

public class ADLIconScrollPane extends JScrollPane {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private MainFrame mainFrame = null;
    private String currentSQLOrder = null;

   
    public ADLIconScrollPane(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainPanel = new ImagePanel(AResource.getWallpaper());
        setViewportView(mainPanel);
        listShow("Select * From ADDRESSLISTTABLE  ORDER BY ID+10000");
    }

    @SuppressWarnings("rawtypes")
	int listShow(String SQL) {
        currentSQLOrder = SQL;
        System.out.println("ADLIconScrollPane£º"+SQL);
        mainPanel.removeAll();
        ArrayList mainVector = DataBase.getAddressListDatasBySQL(SQL);
        int length = mainVector.size();
        GridLayout mainPaneLayout = new GridLayout();

        int row = length / 9 + 1;
        int column = 19;
        mainPaneLayout.setRows(row);
        mainPaneLayout.setColumns(column);
        mainPaneLayout.setHgap(20);
        mainPaneLayout.setVgap(40);
        mainPanel.setLayout(mainPaneLayout);
        int i = 0;
        while (i < length) {
            AddressListData vector = (AddressListData) mainVector.get(i);
            ADLIconButton aButton = null;
            aButton = new ADLIconButton(mainFrame);
            aButton.setData(vector);
            aButton.setSize(60, 60);
            mainPanel.add(aButton);
            i++;
        }
        return i;
    }

    public void refresh() {
        listShow(currentSQLOrder);
    }

    public String getCurrentSQLOrder() {
        return currentSQLOrder;
    }
}
