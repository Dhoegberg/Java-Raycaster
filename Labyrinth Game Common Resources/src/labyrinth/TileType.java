package labyrinth;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public enum TileType { //Dictionary for MapGrid
	WALL((byte)0), OPEN((byte)1), OUT_OF_BOUNDS((byte)2),
	WELCOME((byte)3), PORTRAIT((byte)4);
	
	private byte byteValue;
	private BufferedImage image;
	private DataBufferInt imageDataBuffer;
	
	private static TileType[] values = TileType.values();
	
	private TileType(byte valueP){
		byteValue = valueP;
	}
	public byte getByteValue(){
		return byteValue;
	}
	public static TileType getTileFromByte(byte valueP){
		//TODO make lookuptable if speed becomes an issue.
		TileType[] array = values;
		for(int i = 0; i < array.length; i++){
			if(array[i].getByteValue() == valueP) return array[i];
		}
		return null;
	} //TODO not sure if necessary.
	public BufferedImage getImage(){
		return image;
	}
	public DataBufferInt getImageDataBuffer(){
		if(imageDataBuffer != null) return imageDataBuffer;
		else{
			if(image != null){
				imageDataBuffer = (DataBufferInt) image.getRaster().getDataBuffer();
				System.out.println("Namn på bild där databuffern används : " + this.toString());
				System.out.println("Är Image rgb? : " + image.getColorModel().getColorSpace().isCS_sRGB());
				System.out.println("Är databuffer integer? : " + (imageDataBuffer.getDataType() == imageDataBuffer.TYPE_INT));
				return imageDataBuffer;
			}
			return null;
		}
	}
	public void setImage(BufferedImage setImage){
		image = setImage;
	}
}
