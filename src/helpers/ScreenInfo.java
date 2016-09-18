package helpers;
import javax.swing.*;
import java.awt.*;


/**
 * @author Niklas Karlsson
 * All the Screen-helper
 * (and Mouse)
 * 
 */
public class ScreenInfo {

	public static int getScreenW() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int width = d.width;
		System.out.println("Screen width = " + d.width);
		System.out.println("Screen height = " + d.height);
		return width;
	}

	public static int getScreenH() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int height = d.height;
		System.out.println("Screen width = " + d.width);
		System.out.println("Screen height = " + d.height);

		return height;
	}



}
