
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	double dx, dy;

	public Projectile(int x, int y, int width, int height, double dx, double dy) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		this.dx = dx;
		this.dy = dy;
		if (needImage) {
		    loadImage ("bullet.png");
		}
	}
	
	void update() {
		x+=dx*6;
		y+=dy*6;
		super.update();
	}
	
	void draw(Graphics g) {
		
		g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
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
	
//	public Projectile getProjectile() {
//		
//        return new Projectile(x+width/2, y, 10, 10);
//	} 
}
