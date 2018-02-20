package media;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

public abstract class ImageProcessing {
	
	private static final GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	
	public static BufferedImage createCompatibleImage(int width, int height, int transparency){
		return graphicsConfig.createCompatibleImage(width, height, transparency);
		//Graphics2D compatibleContext = compatibleImage.createGraphics();
	}
}