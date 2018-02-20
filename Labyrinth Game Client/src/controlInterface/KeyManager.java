package controlInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	private static boolean[] keyCodePressStatus;

	public KeyManager(){
		keyCodePressStatus = new boolean[KeyEvent.KEY_LAST+1];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyCodePressStatus[e.getKeyCode()] = true;
		//System.out.println(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keyCodePressStatus[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static boolean isKeyPressed(int keyCode){
		return keyCodePressStatus[keyCode];
	}
}
