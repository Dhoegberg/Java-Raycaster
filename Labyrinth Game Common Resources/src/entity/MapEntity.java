package entity;

import labyrinth.TileGrid;

public interface MapEntity {
	
	public TileGrid getTileGrid();
	
	public float getPosX();
	public float getPosY();
	
	/**
	 * Returns direction angle in radians.
	 * North is angle 0;
	 */
	public float getDirection();
}
