package media;

import java.awt.image.BufferedImage;

public class ImageType {
	private BufferedImage image;
	private String type;
	
	public ImageType(BufferedImage imag, String typ){
		image = imag;
		type = typ;
	}
	public BufferedImage getImage(){
		return image;
	}
	public String getType(){
		return type;
	}
}