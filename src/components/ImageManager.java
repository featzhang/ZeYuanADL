package components;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageManager {

	public Icon getImage(String id) {
		if (id.equals("-1")) {
			return new ImageIcon("photo.png");
		} else {
		}
		return null;
	}

}
