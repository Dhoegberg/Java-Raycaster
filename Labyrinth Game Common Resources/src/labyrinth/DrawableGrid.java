package labyrinth;

import java.awt.Graphics;

public interface DrawableGrid {
	public void draw(Graphics g,
			int topLeftX, int topLeftY, int sizeX, int sizeY,
			float gridTopLeftX, float gridTopLeftY, int gridSizeX, int gridSizeY);
}
