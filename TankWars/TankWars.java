
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;

public class TankWars {
	
	JFrame frame;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	GamePanel gamePanel;
	
	
	public static void main(String[] args) {
		TankWars game = new TankWars();
		game.setup();
	}
	
	public TankWars() {
		frame = new JFrame();
		gamePanel = new GamePanel();
	}
	
	void setup() {
		frame.add(gamePanel);
		gamePanel.setPreferredSize(new Dimension (WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(gamePanel);
		frame.pack();
	}
}