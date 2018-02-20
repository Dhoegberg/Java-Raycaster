package media;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import labyrinth.TileType;

public class MediaManager {
	private HashMap<String, Image> graphicsMap;
	
	public MediaManager(){
		graphicsMap = ImageListLoader.loadImagesFromResource("graphicslist.txt");
		TileType.WALL.setImage((BufferedImage)graphicsMap.get("BRICKWALL512"));
		TileType.WELCOME.setImage((BufferedImage)graphicsMap.get("WELCOME512"));
		TileType.PORTRAIT.setImage((BufferedImage)graphicsMap.get("PORTRAIT"));
	}
	
	public Image getImage(String name){
		return graphicsMap.get(name);
	}
}