package rayCasting;

import java.awt.Color;
import java.util.ArrayList;
/**
 * This class is useless atm.
 * @author Daniel Högberg
 *
 */
public class PixelColor extends Color {
	private static final ArrayList<PixelColor> pixelColorPool = new ArrayList<PixelColor>();
	
	
	private int red, green, blue, alpha;
	public PixelColor(int r, int g, int b, int a) {
		super(r, g, b, a);
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	
	//Overridden getters.
	@Override
	public int getRed(){
		return red;
	}
	@Override
	public int getGreen(){
		return green;
	}
	@Override
	public int getBlue(){
		return blue;
	}
	@Override
	public int getAlpha(){
		return alpha;
	}
	@Override
	public int getRGB(){
		return alpha<<24 + red<<16 + green<<8 + blue;
	}
	
	//Setters
	public void setRed(int redP){
		red = redP;
	}
	public void setGreen(int greenP){
		green = greenP;
	}
	public void setBlue(int blueP){
		blue = blueP;
	}
	public void setAlpha(int alphaP){
		alpha = alphaP;
	}
	public void setRGBA(int r, int g, int b, int a){
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	//Effects
	public void darken(int magnitude){
		red -= magnitude;
		if(red < 0) red = 0;
		green -= magnitude;
		if(green < 0) green = 0;
		blue -= magnitude;
		if(blue < 0) blue = 0;
	}
	public void lighten(int magnitude){
		red += magnitude;
		if(red > 255) red = 255;
		green += magnitude;
		if(green > 255) green = 255;
		blue += magnitude;
		if(blue > 255) blue = 255;
	}
	
	//Pool Methods:
	public static PixelColor getNewPixelColor(int r, int g, int b, int a){
		if(pixelColorPool.isEmpty()) pixelColorPool.add(new PixelColor(r, g, b, a));
		PixelColor newPixelColor = pixelColorPool.remove(pixelColorPool.size()-1);
		newPixelColor.setRGBA(r, g, b, a);
		return newPixelColor;
	}
	public void recycle(){
		pixelColorPool.add(this);
	}
}
