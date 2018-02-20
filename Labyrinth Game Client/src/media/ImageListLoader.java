package media;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import main.GameClient;

public abstract class ImageListLoader {
	public static HashMap<String, Image> loadImagesFromResource(String resourceList){
		//Holder of images.
		HashMap<String, Image> graphicsMap = new HashMap<String, Image>();
		
		//Resource file scanner.
		Scanner graphicsScanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/" + resourceList));
		
		while(graphicsScanner.hasNext()){
			//Läs in filadress och namn för fil från textfil.
			String graphicPath;
			do{
				graphicPath = /*System.getProperty("user.dir") + "\\" +*/ "resources/" + graphicsScanner.next();
				if(graphicPath.contains("//"))graphicsScanner.nextLine();
			} while(graphicPath.contains("//"));
			
			String graphicName = graphicsScanner.next();
			System.out.println(graphicPath);
			System.out.println(graphicName);
			
			Image newImage = null;
			
			try {
				newImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource(graphicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, ("Kan inte ladda fil = " + Thread.currentThread().getContextClassLoader().getResource("resources/" + graphicPath)));
				e.printStackTrace();
			}
			
			//Skapa compatibleImage för accelererad visning, skapa kontext och skriv in newImage.
			BufferedImage compatibleImage = ImageProcessing.createCompatibleImage(newImage.getWidth(null), newImage.getHeight(null), Transparency.TRANSLUCENT);
			Graphics2D compatibleContext = compatibleImage.createGraphics();
			
			//Kopiera newImage till compatiblecontext, dvs compatibleImage.
			if(compatibleContext.drawImage(newImage, 0, 0, null) == false) System.out.println("NewImage är inte klar för ritning till compatibleContext");
			
			//TODO Detta var i princip en implementering av key-value pair, så imageType är onödig när jag använder hashmap istället för arraylist.
			//Skapa en ImageType för compatibleImage, med image och NAMN för enumtester.
			//ImageType graphicType = new ImageType(compatibleImage, graphicName.toUpperCase());
			
			//Lägg ImageType i graphicsList.
			graphicsMap.put(graphicName.toUpperCase(), compatibleImage);
		}
		
		//TODO alla dessa associeringar skulle kunna förenklas med att man flyttade ut en metod som löste i och j.
		//Associerar TerrainType med bilder
		
		/*System.out.println("Associerar grafik med TerrainType");
		TerrainType[] terrainTypes = TerrainType.values();
		for(int i = 0; i < terrainTypes.length; i++){
			for(int j = 0; j < graphicsList.size(); j++){
				if(terrainTypes[i].toString().equals(graphicsList.get(j).getType())){
					terrainTypes[i].setTerrainTileImage(graphicsList.get(j).getImage());
				}
			}
		}*/
		
		System.out.println("Färdig med grafikladdning.");
		graphicsScanner.close();
		
		return graphicsMap;
	}
}
