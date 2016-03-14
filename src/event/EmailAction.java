package event;

import components.MainFrame;
import util.ConfigManager;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Icon;
import mail.SendMailJDialog;
import util.AResource;

public class EmailAction extends ADLGuiAction {

    private MainFrame mainFrame = null;

    public EmailAction(MainFrame mainFrame) {
        this(mainFrame, MainFrame.Email_String);
    }

    public EmailAction(MainFrame mainFrame, String name) {
        super(mainFrame, name);
        this.mainFrame = mainFrame;
//        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    protected Icon getIcon() {
        return AResource.getImageIcon("email");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Email = mainFrame.adlMainTable.getValueAt(mainFrame.adlMainTable.getSelectedRow(), 7).toString();
        boolean defaultMailServerEnable = new ConfigManager().getDefaultMailServerEnable();
        if (defaultMailServerEnable) {
            try {
                URI uri = new URI("mailto:" + Email);
                Desktop.getDesktop().mail(uri);
            } catch (URISyntaxException ex) {
            } catch (IOException ex) {
            }
        } else {
            SendMailJDialog sendMailJFrame = new SendMailJDialog(mainFrame, true);
            sendMailJFrame.setSendTo(Email);
            int x = (mainFrame.getWidth() - sendMailJFrame.getWidth()) / 2 + mainFrame.getX();
            int y = (mainFrame.getHeight() - sendMailJFrame.getHeight()) / 2 + mainFrame.getY();
            sendMailJFrame.setLocation(x, y);
            sendMailJFrame.setVisible(true);
        }
    }
}
