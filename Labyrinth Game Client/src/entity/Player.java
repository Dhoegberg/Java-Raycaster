package entity;

import java.awt.event.KeyEvent;
import vectorMath.AngleCalculation;
import controlInterface.KeyManager;
import labyrinth.TileGrid;
import labyrinth.TileType;

public class Player implements MapEntity, Updatable {
	private TileGrid tileGrid;
	private float posX, posY, direction;
	public static final int speed = 4;
	
	public Player(TileGrid mapGridParameter,
			int posXParameter, int posYParameter, float directionParameter){
		tileGrid = mapGridParameter;
		
		posX = posXParameter;
		posY = posYParameter;
		direction = AngleCalculation.getDirectionFromAngle((float)Math.toRadians(directionParameter));
	}
	@Override
	public TileGrid getTileGrid() {
		return tileGrid;
	}
	
	@Override
	public float getPosX() {
		return posX;
	}
	
	@Override
	public float getPosY() {
		return posY;
	}
	@Override
	public float getDirection() {
		return direction;
	}
	@Override
	public void update(float alpha) {
		if(KeyManager.isKeyPressed(KeyEvent.VK_LEFT)){
			rotateDirection((float)-Math.toRadians(180*alpha));
			//move(-speed * alpha, 0);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_RIGHT)){
			rotateDirection((float)Math.toRadians(180*alpha));
			//move(speed * alpha, 0);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_UP)){
			move((float)AngleCalculation.gameSin(direction)* speed * alpha, (float)AngleCalculation.gameCos(direction) * speed * alpha);
			//move(0, -speed * alpha);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_DOWN)){
			move((float)AngleCalculation.gameSin(direction)* -speed* alpha, (float)AngleCalculation.gameCos(direction) * -speed * alpha);
			//move(0, speed * alpha);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_A)){
			move((float)AngleCalculation.gameSin(direction + AngleCalculation.getDirectionFromAngle((float)Math.toRadians(90)))* -speed * alpha, (float)AngleCalculation.gameCos(direction + AngleCalculation.getDirectionFromAngle((float)Math.toRadians(90))) * -speed * alpha);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_D)){
			move((float)AngleCalculation.gameSin(direction + AngleCalculation.getDirectionFromAngle((float)Math.toRadians(90)))* speed * alpha, (float)AngleCalculation.gameCos(direction + AngleCalculation.getDirectionFromAngle((float)Math.toRadians(90))) * speed * alpha);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_W)){
			move((float)AngleCalculation.gameSin(direction)* speed * alpha, (float)AngleCalculation.gameCos(direction) * speed * alpha);
		}
		if(KeyManager.isKeyPressed(KeyEvent.VK_S)){
			move((float)AngleCalculation.gameSin(direction)* -speed* alpha, (float)AngleCalculation.gameCos(direction) * -speed * alpha);
		}
	}
	public void move(float x, float y){
		float newPosX = posX + x, newPosY = posY + y;
		byte tileByte = tileGrid.getByteAtPos((int)newPosX, (int)newPosY);
		if(tileByte == TileType.OPEN.getByteValue()){
			posX = newPosX;
			posY = newPosY;
		} else{ //Sliding
			tileByte = tileGrid.getByteAtPos((int)newPosX, (int)posY);
			if(tileByte == TileType.OPEN.getByteValue()){
				posX = newPosX;
			} else{
				tileByte = tileGrid.getByteAtPos((int)posX, (int)newPosY);
				if(tileByte == TileType.OPEN.getByteValue()){
					posY = newPosY;
				}
			}
		}
	}
	public void rotateDirection(float angle){
		direction = AngleCalculation.rotateDirection(direction, angle);
	}
	public void setDirection(float angle){
		direction = AngleCalculation.getDirectionFromAngle(angle);
	}
}
