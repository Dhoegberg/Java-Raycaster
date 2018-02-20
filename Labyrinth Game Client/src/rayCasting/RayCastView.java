package rayCasting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;

import labyrinth.ClientTileGrid;
import labyrinth.TileGrid;
import media.ImageProcessing;
import vectorMath.AngleCalculation;
import entity.MapEntity;
import entity.Player;

public class RayCastView {
	//View Component position and size
	private int topLeftX, topLeftY,
	sizeX, sizeY;
	
	//Camera settings
	private float fov;
	private MapEntity caster;
	
	//View ImageData
	private BufferedImage viewImage;
	private DataBufferInt viewImageDataBuffer;
	
	private static final Color WALL = new Color(110,110,110), WALL_SHADOW = new Color(90,90,90);
	
	public RayCastView(int topLeftXP, int topLeftYP,
			int sizeXP, int sizeYP, int fovP){
		topLeftX = topLeftXP;
		topLeftY = topLeftYP;
		sizeX = sizeXP;
		sizeY = sizeYP;
		
		viewImage = ImageProcessing.createCompatibleImage(sizeX, sizeY, Transparency.TRANSLUCENT);
		viewImageDataBuffer = (DataBufferInt) viewImage.getRaster().getDataBuffer();
		
		//Degrees to radian.
		fov = (float)Math.toRadians(fovP);
	}
	public void drawRayCasting(Graphics g){
		if(caster != null){
			
			int[] viewPixelData = viewImageDataBuffer.getData();
			//Clearing viewImage
			for(int i = 0; i < viewPixelData.length; i++){
				viewPixelData[i] = 0;
			}
			
			for(int viewColumn = 0; viewColumn < sizeX; viewColumn++){
				float columnAngleRatio = (float)viewColumn / sizeX - 0.5f;
				float columnAngle = columnAngleRatio * fov;
				
				RayColumnWall currentRayColumnWall = RayCasting.castRayToWall(caster, columnAngle);
				
				//Use perspective to remove fisheye. Works best in small FOV's.
				float perspectiveDistance = currentRayColumnWall.distance;
				perspectiveDistance *= AngleCalculation.perspectiveCos(columnAngle);
				int height = (int) (sizeY / perspectiveDistance);
				
				height -= height%2; //TODO tycks lösa problemet med skalning.
				
				//Prototyp för väggtextur-- Funkar ok!
				//Skaländringen tar bort en del fulheter men det uppstår
				//fortfarande en del konstiga förskjutningar som syns
				//på raka linjer, men de är sporadiska. FIXME
				PixelArray columnPixelDataArray = RayCastPixelEditing.getColumnPixelsFromRayColumnWall(currentRayColumnWall);
				if(columnPixelDataArray != null){
					float pace = PixelArray.SIZE/(float)height;
					if(height < sizeY){
						for(int h = 0; h < height; h++){
							int advance = (int) (pace * h);
							int viewPixelDataIndex = (int) (sizeX * (sizeY/2 - height/2 + h) + viewColumn);
							//System.out.println(viewPixelDataIndex/(advance+1) + " " + height%2);
							viewPixelData[viewPixelDataIndex]
									= columnPixelDataArray.array[advance];
						}
					} else{
						for(int y = 0; y < sizeY; y++){
							int viewPixelDataIndex = sizeX * y + viewColumn;
							//Includes offset \/
							int advance = (int) ((pace * y) + (-(pace * sizeY) + PixelArray.SIZE)/2);
							viewPixelData[viewPixelDataIndex]
									= columnPixelDataArray.array[advance];
						}
					}
					//--PrototypEnd
				} else{
					if(currentRayColumnWall.xStep) g.setColor(WALL_SHADOW);
					else g.setColor(WALL);
					g.drawLine(viewColumn + topLeftX, topLeftY + sizeY/2 - height/2, viewColumn + topLeftX, topLeftY + sizeY/2 + height/2);
				}
				if(columnPixelDataArray!=null)columnPixelDataArray.recycle();
				currentRayColumnWall.recycle();
			}
			g.drawImage(viewImage, topLeftX, topLeftY, null);
		}
	}
	public void setCaster(MapEntity casterP){
		caster = casterP;
	}
}