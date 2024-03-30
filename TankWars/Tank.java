import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tank extends GameObject{
	
	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Tank(int x, int y, int width, int height) {
		
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		
		if (needImage) {
		    loadImage ("rocket.png");
		}
	}
	
	void draw(Graphics g) {
		
		g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
	}
	
	public void update() {
		if (right && x < LeagueInvaders.WIDTH - width) {
			right();
		}
		
		if (left && x > 0) {
			left();
		}
		
		if (up && y > 0) {
			up();
			System.out.println(y);
		}
		
		if (down && y < LeagueInvaders.HEIGHT - height) {
			down();
		}
	}
	
	public void right() {
        x+=speed;
    }
	
	public void left() {
        x-=speed;
    }
	
	public void up() {
        y-=speed;
    }
	
	public void down() {
        y+=speed;
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
	
	public Projectile getProjectile() {
		
        return new Projectile(x+width/2, y, 10, 10);
	} 
}