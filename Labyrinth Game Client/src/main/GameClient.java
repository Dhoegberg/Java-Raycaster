package main;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import controlInterface.KeyManager;
import entity.Player;
import gui.GamePanel;
import labyrinth.ClientTileGrid;
import labyrinth.TileType;
import media.MediaManager;

public class GameClient {
	//Client currentData
	private ClientTileGrid clientTileGrid;
	
	//Client Gui
	private GamePanel gamePanel;
	
	//Media handling
	private MediaManager mediaManager;
	
	//Client updateLoop
	private boolean running = true;
	private Player player;
	private long lastTime, delta;
	public static float alpha;
	public static final double NANO_FACTOR = 1000000000;
	//Game state TODO Såsom i Harvest attack.
	
	
	//Network TODO
	//Socket gameServerSocket;
	
	//Logging
	public static PrintWriter LOG_PRINT;
	
	public GameClient() {
		mediaManager = new MediaManager();
		gamePanel = new GamePanel("Labyrinth Game Client", this);
		
		//Grid init.
		clientTileGrid = new ClientTileGrid(200, 200);
		clientTileGrid.makeOpenRoom(); //TODO DEBUG.
		for(int i = 2; i < 10; i++){
			clientTileGrid.setByteAtPos(5, i, TileType.WALL.getByteValue());
		}
		for(int i = 7; i < 15; i++){
			clientTileGrid.setByteAtPos(i, 9, TileType.WALL.getByteValue());
		}
		for(int i = 7; i < 15; i++){
			clientTileGrid.setByteAtPos(i, 11, TileType.WALL.getByteValue());
		}
		clientTileGrid.setByteAtPos(9, 12, TileType.WALL.getByteValue());
		clientTileGrid.setByteAtPos(3, 0, TileType.WELCOME.getByteValue());
		clientTileGrid.setByteAtPos(2, 0, TileType.PORTRAIT.getByteValue());
		for(int i = 7; i < 15; i++){
			if(i%2 == 1)clientTileGrid.setByteAtPos(i, 14, TileType.WALL.getByteValue());
		}
		for(int y = 10; y < 180; y++){
			for(int x = 30; x < 180; x++){
				if(x%2 == 1 && y%2 == 1){
					clientTileGrid.setByteAtPos(x, y, TileType.WALL.getByteValue());
				}
			}
		}
		//end DEBUG.
		//clientMapGrid.setByteAtPos(x, y, byteP);
		player = new Player(clientTileGrid, 3, 3, 15);
		gamePanel.setGridFocus(player);
		try {
			lastTime = System.nanoTime();
			while(running){
				loop();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.exit(0);
		}
	}

	private void loop() throws InterruptedException{
		//Single player update logic.
		Thread.sleep(10);
		long currentTime = System.nanoTime();
		delta = currentTime - lastTime;
		lastTime = currentTime;
		alpha = (float) (delta / NANO_FACTOR);
		
		//player.
		player.update(alpha);
		gamePanel.repaint();
		if(KeyManager.isKeyPressed(KeyEvent.VK_ESCAPE)) System.exit(0);
	}

	public static void main(String[] args) {
		try{
			new GameClient();
		} catch (RuntimeException e){
			try {
				GameClient.LOG_PRINT = new PrintWriter(new BufferedWriter(new FileWriter("log.txt")));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace(GameClient.LOG_PRINT);
			e.printStackTrace();
		} finally{
			GameClient.LOG_PRINT.flush();
		}
	}
}