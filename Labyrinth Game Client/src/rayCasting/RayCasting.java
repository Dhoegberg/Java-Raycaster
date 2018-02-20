package rayCasting;

import vectorMath.AngleCalculation;
import labyrinth.TileGrid;
import labyrinth.TileType;
import entity.MapEntity;

public abstract class RayCasting {
	
	public static final RayColumnWall castRayToWall(MapEntity caster, float castAngle){
		float originX = caster.getPosX();
		float originY = caster.getPosY();
		
		castAngle = AngleCalculation.rotateDirection(caster.getDirection(), castAngle);
		TileGrid tileGrid = caster.getTileGrid();
		
		float deltaX = (float) AngleCalculation.gameSin(castAngle);
		float deltaY = (float) AngleCalculation.gameCos(castAngle);
		
		boolean deltaXPositive = deltaX > 0 ? true : false;
		boolean deltaYPositive = deltaY > 0 ? true : false;
		
		Step stepX = Step.getNewStep(originX, originY);
		Step stepY = Step.getNewStep(originX, originY);
		
		RayColumnWall rayColumnWall = RayColumnWall.getNewColumn();
		
		boolean hitWall = false;
		while(!hitWall){
			takeStep(stepX, stepX.x, deltaX, deltaY, true, deltaXPositive);
			takeStep(stepY, stepY.y, deltaY, deltaX, false, deltaYPositive);
			
			//These lengths are used for length comparison mostly, thus they stay squared for now.
			float stepXLength2 = squareSum(stepX.x - originX, stepX.y - originY);
			float stepYLength2 = squareSum(stepY.x - originX, stepY.y - originY);
			
			if(stepXLength2 <= stepYLength2){ //Smallest first.
				if(checkStepForWall(stepX, tileGrid, rayColumnWall, true, deltaXPositive)){
					rayColumnWall.distance = (float) Math.sqrt(stepXLength2);
					rayColumnWall.xStep = true;
					rayColumnWall.offset = stepX.y%1;
					hitWall = true;
				}
				stepY.x = stepX.x;
				stepY.y = stepX.y;
			}
			if(stepYLength2 < stepXLength2){ //Smallest first.
				if(checkStepForWall(stepY, tileGrid, rayColumnWall, false, deltaYPositive)){
					rayColumnWall.distance = (float) Math.sqrt(stepYLength2);
					rayColumnWall.xStep = false;
					rayColumnWall.offset = stepY.x%1;
					hitWall = true;
				}
				stepX.x = stepY.x;
				stepX.y = stepY.y;
			}
		}
		stepX.recycle();
		stepY.recycle();
		
		return rayColumnWall;
	}
	private static final void takeStep(Step step, float a, float deltaA, float deltaB, boolean xStep, boolean positive){
		//The floor+1 is to ensure it always takes a step.
		float stepDeltaA = (float) (positive ?
				Math.floor(a + 1) - a :
				Math.ceil(a - 1) - a);
		float stepDeltaB = stepDeltaA * (deltaB / deltaA);
		
		step.x += xStep ? stepDeltaA : stepDeltaB;
		step.y += xStep ? stepDeltaB : stepDeltaA;
	}
	private static final float squareSum(float x, float y){
		return x*x + y*y;
	}
	private static final boolean checkStepForWall(Step step, TileGrid tileGrid, RayColumnWall rayColumnWall, boolean xStep, boolean positive){
		byte tile;
		if(xStep) tile = tileGrid.getByteAtPos(positive ? (int)step.x : (int)step.x - 1, (int)step.y);
		else tile = tileGrid.getByteAtPos((int)step.x, positive ? (int)step.y : (int)step.y - 1);
		if(tile == TileType.WALL.getByteValue()
				|| tile == TileType.OUT_OF_BOUNDS.getByteValue()
				|| tile == TileType.WELCOME.getByteValue()
				|| tile == TileType.PORTRAIT.getByteValue()){
			rayColumnWall.wallType = TileType.getTileFromByte(tile);
			return true;
		}
		return false;
	}
}