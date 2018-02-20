package labyrinth;

public interface TileGrid {
	
	//Byte position methods.
	public byte getByteAtPos(int x, int y);
	public void setByteAtPos(int x, int y, byte byteP);
	
	//Size Methods
	public int getSizeX();
	public int getSizeY();
}
