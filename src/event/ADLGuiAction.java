package event;

import components.MainFrame;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class ADLGuiAction extends AbstractAction {

    private static final String ENABLE_HTML = "<html><font color='red'>";
    private static final String DISABLE_HTML = "<html><font color='#777777'>";
    private static final String HTML_END = "</font>";
    protected MainFrame mainFrame = null;

    public ADLGuiAction(MainFrame mainFrame, String name) {
        super(name);
        this.mainFrame = mainFrame;
//        ACTIONS.add(this);
        putValue(SMALL_ICON, getIcon());
        putValue(SHORT_DESCRIPTION, getShortDescription());
    }

    protected abstract Icon getIcon();

    protected String getShortDescription() {
        String name = getValue(NAME).toString();
        return isEnabled() ? getEnableHTMLString(name) : getDisableHTMLString(name);
    }

    protected String getEnableHTMLString(String str) {
        return ENABLE_HTML + str + HTML_END;
    }

    protected String getDisableHTMLString(String str) {
        return DISABLE_HTML + str + HTML_END;
    }

    @Override
    public void setEnabled(boolean enbaled) {
        if (isEnabled() == enbaled) {
            return;
        }
        super.setEnabled(enbaled);
        if (isEnabled()) {
            putValue(SHORT_DESCRIPTION, getShortDescription());
        } else {
            putValue(SHORT_DESCRIPTION, getDisableHTMLString(getValue(NAME).toString()) + "<br>此操作不可用，请选定单元格。");
        }
    }
}
