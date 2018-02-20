package labyrinth;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import static gui.GamePanel.TILE_SIZE;;

public class ClientTileGrid implements TileGrid, DrawableGrid {
	private byte[][] mapGrid;
	
	public ClientTileGrid(int sizeX, int sizeY){
		mapGrid = new byte[sizeX][sizeY];
	}
	
	public void makeOpenRoom(){ //DEBUG
		for(int x = 1; x < mapGrid.length -1; x++){
			for(int y = 1; y < mapGrid[0].length -1; y++){
				mapGrid[x][y] = TileType.OPEN.getByteValue();
			}
		}
	}
	
	@Override
	public byte getByteAtPos(int x, int y) {
		if(x < 0 || x >= mapGrid.length
			|| y < 0 || y >= mapGrid[0].length) return TileType.OUT_OF_BOUNDS.getByteValue();
		return mapGrid[x][y];
	}
	
	@Override
	public void setByteAtPos(int x, int y, byte byteP) {
		//Not allowed. TODO
		//JOptionPane.showMessageDialog(null, "Client is not allowed to set bytevalues in grid atm.");
		mapGrid[x][y] = byteP;
	}
	
	@Override
	public int getSizeX() {
		return mapGrid.length;
	}
	
	@Override
	public int getSizeY() {
		return mapGrid[0].length;
	}

	@Override
	public void draw(Graphics g,
			int topLeftX, int topLeftY, int sizeX, int sizeY,
			float gridTopLeftX, float gridTopLeftY, int gridSizeX, int gridSizeY) {
		
		for(int x = 0; x < gridSizeX; x++){
			for(int y = 0; y < gridSizeY; y++){
				//Enforce screen size parameter.
				if((y+1) * TILE_SIZE > sizeY) continue;
				if((x+1) * TILE_SIZE > sizeX) break;
				
				byte tile = getByteAtPos((int)gridTopLeftX + x, (int)gridTopLeftY + y );
				if(tile == TileType.OPEN.getByteValue()) g.setColor(Color.WHITE);
				if(tile == TileType.WALL.getByteValue()) g.setColor(Color.GRAY);
				if(tile == TileType.OUT_OF_BOUNDS.getByteValue()) g.setColor(Color.BLACK);
				g.fillRect(topLeftX - (int)(TILE_SIZE*(gridTopLeftX%1)) + TILE_SIZE * x, topLeftY - (int)(TILE_SIZE*(gridTopLeftY%1)) + TILE_SIZE * y,
						TILE_SIZE, TILE_SIZE);
			}
		}
	}
}
