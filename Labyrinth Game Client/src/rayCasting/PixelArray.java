package rayCasting;

import java.util.ArrayList;

public class PixelArray {
	private static final ArrayList<PixelArray> pixelArrayPool = new ArrayList<PixelArray>();
	
	public static final int ELEMENTS_PER_PIXEL = 1;
	public static final int SIZE = 512;//64;
	//public static final int DATA_SIZE = SIZE * ELEMENTS_PER_PIXEL;
	
	public int[] array;
	
	private PixelArray(){
		array = new int[SIZE];
	}
	
	/**
	 * The wrapped array's default values are undefined,
	 * because they are recycled without clearing the values.
	 * @return
	 */
	public static PixelArray getNewPixelArray(){
		if(pixelArrayPool.isEmpty()) pixelArrayPool.add(new PixelArray());
		PixelArray newPixelArray = pixelArrayPool.remove(pixelArrayPool.size()-1);
		return newPixelArray;
	}
	public void recycle(){
		pixelArrayPool.add(this);
	}
}
