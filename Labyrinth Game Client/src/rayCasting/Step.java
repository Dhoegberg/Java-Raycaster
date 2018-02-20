package rayCasting;

import java.util.ArrayList;

public final class Step {
	private static final ArrayList<Step> stepPool = new ArrayList<Step>();
	
	public float x;
	public float y;
	
	private Step(){
		
	}
	public static Step getNewStep(float xP, float yP){
		if(stepPool.isEmpty()) stepPool.add(new Step());
		Step newStep = stepPool.remove(stepPool.size()-1);
		newStep.x = xP;
		newStep.y = yP;
		return newStep;
	}
	public void recycle(){
		x = 0;
		y = 0;
		stepPool.add(this);
	}
}
