package rayCasting;

import java.util.ArrayList;

import labyrinth.TileType;

public class RayColumnWall {
	private static final ArrayList<RayColumnWall> columnPool = new ArrayList<RayColumnWall>();
	
	public TileType wallType;
	public float distance;
	public boolean xStep;
	public float offset;
	
	private RayColumnWall(){
		
	}
	public static RayColumnWall getNewColumn(){
		if(columnPool.isEmpty()) columnPool.add(new RayColumnWall());
		RayColumnWall newColumn = columnPool.remove(columnPool.size()-1);
		return newColumn;
	}
	public void recycle(){
		//Clearing
		wallType = null;
		distance = 0;
		xStep = false;
		offset = 0;
		//Recycling
		columnPool.add(this);
	}
}
