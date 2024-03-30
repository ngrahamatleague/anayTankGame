
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    
    public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
    int currentState = MENU;
    
    Font titleFont;
    Font defaultFont;
    
    Timer frameDraw;
    Timer powerUpSpawn;
    
    Tank tank = new Rocketship(250,700,50,50);
    
    ObjectManager objectManager = new ObjectManager(tank);

    public GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	defaultFont = new Font("Arial", Font.PLAIN, 20);
    	
	   	frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		 
		if (needImage) {
			 loadImage("space.png");
		}

	}
    
    void startGame() {
    	
        powerUpSpawn = new Timer(1000 , objectManager);
        powerUpSpawn.start();
    }
    
    void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
    
	@Override
	public void paintComponent(Graphics g){
		
		if(currentState == MENU){
		    drawMenuState(g);
		}
		
		else if(currentState == GAME){
		    drawGameState(g);
		}
		
		else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	void updateMenuState() { 
		 
	}
	void updateGameState() { 
		tank.update();
		objectManager.update();
		
		if(tank.isActive == false) {
			currentState = END;
		}
	}
	void updateEndState() { 
		 
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS",23, 100);
		
		g.setFont(defaultFont);
		g.drawString("Press ENTER To Start",152, 400);
		
		g.drawString("Press SPACE For Instructions",120, 500);
		objectManager.score = 0;
	}
	
	void drawGameState(Graphics g) { 
		g.setColor(Color.BLACK);
		 
		g.drawImage(image, 0, 0, TankWars.WIDTH, TankWars.HEIGHT, null);
		g.setFont(titleFont);
		
		objectManager.draw(g);
		
		g.setFont(defaultFont);
		g.drawString("Score: " + objectManager.score,10, 20);
	}
	
	void drawEndState(Graphics g)  { 
		g.setColor(Color.RED);
		g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER",94, 100);
		
		g.setFont(defaultFont);
		g.drawString("You Killed " + objectManager.score + " Enemies",156, 400);
		
		g.drawString("Press ENTER To Restart",135, 500);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		 
		 if(currentState == MENU){
			 updateMenuState();
		 }
		 
		 else if(currentState == GAME){
			 updateGameState();
		 }
		 
		 else if(currentState == END){
			 updateEndState();
		 }
		 
		 //System.out.println("action");
		 repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			
		    if (currentState == END) {
		        currentState = MENU;
		    } 
		    
		    else {
		    	
		    	if(currentState == MENU) {
		    		startGame();
		    	}
		    	
		    	if(currentState == GAME) {
		    		powerUpSpawn.stop();
		    	}
		    	
		        currentState++;
		    }
		}   
		
		if (currentState == GAME) {
			
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    System.out.println("UP");
			    if (tank.y > 0) {
			    	tank.up = true;
				}
			    else {
			    	tank.up = false;
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    System.out.println("DOWN");
			    tank.down = true;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    System.out.println("LEFT");
			    if (tank.x > 0) {
			    	tank.left = true;
			    }
			    else {
			    	tank.left = false;
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    System.out.println("RIGHT");
			    if (tank.x < 450) {
			    	tank.right = true;
			    }
			    else {
			    	tank.right = false;
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				objectManager.addProjectile(tank.getProjectile());
				//not working^
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			tank.up = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			tank.down = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			tank.left = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			tank.right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
