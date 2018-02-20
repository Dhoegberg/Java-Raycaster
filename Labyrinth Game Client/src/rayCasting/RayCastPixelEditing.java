package rayCasting;

import java.awt.image.DataBufferInt;

public class RayCastPixelEditing {
	public static final PixelArray getColumnPixelsFromRayColumnWall(RayColumnWall currentRayColumn){
		
		DataBufferInt tileImageDataBuffer = currentRayColumn.wallType.getImageDataBuffer();
		
		if(tileImageDataBuffer != null){
			PixelArray columnPixelDataArray = PixelArray.getNewPixelArray();
			int[] tileImageDataArray = tileImageDataBuffer.getData();
			int imageColumn = (int) (PixelArray.SIZE * currentRayColumn.offset);
			
			for(int y = 0; y < PixelArray.SIZE; y++){
				int columnIndex = imageColumn + PixelArray.SIZE * y;
				columnPixelDataArray.array[y] = tileImageDataArray[columnIndex];
			}
			return columnPixelDataArray;
		}
		return null;
	}
}
