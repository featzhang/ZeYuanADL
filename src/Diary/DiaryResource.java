package Diary;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;

public class DiaryResource {

    private static ResourceBundle resources;
    private static String imageSuffix = "Image";
    private static String labelSuffix = "Label";
    private static String booleanSuffix = "Boolean";
    private static String intSuffix = "Number";

    static {
        try {
            resources = ResourceBundle.getBundle("Diary.DiaryPanel",
                    Locale.getDefault());
        } catch (MissingResourceException mre) {
            System.err.println("Diary.DiaryPanel.properties not found");
            System.exit(1);
        }
    }

    public static String getString(String nm) {
        String str;
        try {
            str = resources.getString(nm);
        } catch (MissingResourceException mre) {
            str = null;
        }
        return str;
    }

    public URL getResources(String key) {
        String name = getString(key);
        if (name != null) {
            URL url = this.getClass().getResource(name);
            return url;
        }

//        System.out.println(key+"=null");
        return null;
    }

    public static ImageIcon getImageIcon(String cmd) {
        
        URL url = new DiaryResource().getResources(cmd + imageSuffix);
        if (url != null) {
            return new ImageIcon(url);
        }
//        System.out.println(cmd+"Image=null");
        return null;
    }

    public static java.awt.Image getImage(String cmd) {
        return getImageIcon(cmd).getImage();
    }

    public static String getApplicationTitle() {
        return getString("Application.title");
    }

    public static String getApplicationName() {
        return getString("Application.name");
    }

    public static String getApplicationVersion() {
        return getString("Application.version");
    }

    public static String getApplicationCompany() {
        return getString("Application.company");
    }
    
    public static ImageIcon getApplicationImageIcon() {
        return getImageIcon("Application");
    }

    public static java.awt.Image getApplicationImage() {
        return getApplicationImageIcon().getImage();
    }

    public static ImageIcon getWelcomeImage() {
        return getImageIcon("welcome");
    }

    public static String getApplicationHomePage() {
        return getString("Application.homepage");
    }

    public static ImageIcon getWallpaper() {
        return getImageIcon("wallpaper");
    }

    public static String getLabel(String s) {
        String str = null;
        try {
            if (s.indexOf("-") != -1) {
                str = "";
                String[] split = s.split("-");
                for (int i = 0; i < split.length; i++) {
                    String string = split[i];
                    String string1 = resources.getString(string.trim() + labelSuffix);
                    str += string1;
                }
            } else {
                str = resources.getString(s.trim() + labelSuffix);
            }
        } catch (MissingResourceException mre) {
            str = null;
        }
        return str;
    }

    public static boolean getBoolean(String s) {
        Boolean boo = null;
        String str = null;
        try {
            str = resources.getString(s.trim() + booleanSuffix);
            if (str != null) {
                boo = Boolean.valueOf(str);
            }
        } catch (MissingResourceException mre) {
            boo = null;
        }
        return boo;
    }

    public static int getInt(String s) {
        int i = Integer.MIN_VALUE;
        String str = null;
        try {
            str = resources.getString(s.trim() + intSuffix);
            if (str != null) {
                i = Integer.parseInt(str);
            }
        } catch (MissingResourceException mre) {
            i = Integer.MIN_VALUE;
        }
        return i;
    }

}
