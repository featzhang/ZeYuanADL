import components.MainFrame;
import components.SplashWindows;
import javax.swing.UIManager;
import util.SingleHandler;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		MainFrame adlmf = null;
		if (SingleHandler.isSingle()) {
			adlmf = new MainFrame();
			SplashWindows splashWindows = new SplashWindows(adlmf);
			splashWindows.start();
		}
		SingleHandler sh = SingleHandler.getHandler(adlmf, args);
		sh.start();
	}
}
