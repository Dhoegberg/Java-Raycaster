package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rayCasting.RayCastView;
import controlInterface.KeyManager;
import entity.MapEntity;
import main.GameClient;

public class GamePanel extends JPanel {
	private JFrame panelFrame;
	
	private GameClient gameClient;//TODO remove?
	private GridView gridView;
	private RayCastView rayCastView;
	
	public static final int FOV = 60;
	
	public static final int TILE_SIZE = 16;
	
	public GamePanel(String frameTitle, GameClient gameClientP){
		super();
		//
		gameClient = gameClientP;
		
		//Större gridview.
		//gridView = new GridView(10, 10, 288, 288, 18, 18);
		//rayCastView = new RayCastView(308, 10, 482, 500, FOV);
		
		gridView = new GridView(830, 10, 144, 144, 9, 9);
		rayCastView = new RayCastView(10, 10, 800, 600, FOV);
		setPreferredSize(new Dimension(1024, 768)); //Alt 800,600.
		
		//Frame and visibility stuff.
		panelFrame = new JFrame(frameTitle);
		panelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelFrame.add(this);
		panelFrame.pack();
		panelFrame.setVisible(true);
		
		//input handling
		addKeyListener(new KeyManager());
		
		//make sure we start with focus.
		requestFocusInWindow();
	}
	public void setGridFocus(MapEntity newFocus){
		gridView.setFocus(newFocus);
		rayCastView.setCaster(newFocus);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(gridView != null){
			gridView.draw(g);
		}
		if(rayCastView != null){
			rayCastView.drawRayCasting(g);
		}
		g.setColor(Color.BLACK);
		
		//Väldigt grovt TODO.
		g.drawString(1 / GameClient.alpha + " FPS", 10, 10);
		//g.drawString("Fov : " + Integer.toString(FOV), 10 , 10);
	}
}
