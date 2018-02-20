package gui;

import static gui.GamePanel.TILE_SIZE;

import java.awt.Color;
import java.awt.Graphics;

import vectorMath.AngleCalculation;
import entity.MapEntity;
import labyrinth.DrawableGrid;

public class GridView {
	private int topLeftX, topLeftY,
		sizeX, sizeY, gridSizeX, gridSizeY;
	
	private MapEntity mapFocus;
	
	public GridView(int topLeftXParameter, int topLeftYParameter, int sizeXParameter, int sizeYParameter,
			int gridSizeXParameter, int gridSizeYParameter){
		topLeftX = topLeftXParameter;
		topLeftY = topLeftYParameter;
		sizeX = sizeXParameter;
		sizeY = sizeYParameter;
		gridSizeX = gridSizeXParameter;
		gridSizeY = gridSizeYParameter;
	}
	public void setFocus(MapEntity newFocus){
		mapFocus = newFocus;
	}
	
	public void draw(Graphics g){
		if(mapFocus != null){
			DrawableGrid drawGrid = (DrawableGrid)mapFocus.getTileGrid();
			drawGrid.draw(g, topLeftX, topLeftY, sizeX, sizeY,
					mapFocus.getPosX() - gridSizeX/2, mapFocus.getPosY() - gridSizeY/2, gridSizeX, gridSizeY);
			g.setColor(Color.YELLOW);
			//Paint player, should be done in entity itself!? Grid or other construct should hold entities like localTile did.
			g.fillRect(topLeftX + TILE_SIZE * gridSizeX/2, topLeftY + TILE_SIZE * gridSizeY/2, TILE_SIZE, TILE_SIZE);
			g.setColor(Color.BLACK);
			int centerX = (int) (topLeftX + TILE_SIZE * (gridSizeX/2 + 0.5));
			int centerY = (int) (topLeftY + TILE_SIZE * (gridSizeY/2 + 0.5));
			g.drawLine(centerX, centerY, (int)(centerX + 20*AngleCalculation.gameSin(mapFocus.getDirection())), (int)(centerY + 20*AngleCalculation.gameCos(mapFocus.getDirection())));
		}
	}
}