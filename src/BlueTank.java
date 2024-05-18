
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.Math;

import javax.imageio.ImageIO;

public class BlueTank extends GameObject{

	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;
	public boolean rotatingLeft = false;
	public boolean rotatingRight = false;

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	int angle = 0;

	public BlueTank(int x, int y, int width, int height) {

		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 2;

		if (needImage) {
			loadImage ("bluetank.png");
		}
	}

	void draw(Graphics g) {

		g.setColor(Color.BLUE);
		Graphics2D g2 = (Graphics2D)g;

		double radian = Math.toRadians(angle);
		g2.rotate(radian, x+width/2, y+height/2);

		if (gotImage) {
			g2.drawImage(image, x, y, width, height, null);
		} else {
			g2.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}

		g2.rotate(-radian, x+width/2, y+height/2);




		if(rotatingLeft == true) {
			angle -= 6;
			angle %= 360;
		}

		if(rotatingRight == true) {
			angle += 6;
			angle %= 360;
		}


	}

	public void update() {
		if (right && x < TankWars.WIDTH - width) {
			right();
		}

		if (left && x > 0) {
			left();
		}

		if (up /*&& y > 0*/) {
			up(1);
		}

		if (down /*&& y < TankWars.HEIGHT - height*/) {
			down();
		}


		super.update();
	}

	public void right() {
	
	}

	public void left() {
		
	}

	public void up(int oneForForward) {
	
		System.out.println(angle);
		double radian = Math.toRadians(angle);
		double dx = 0;
		double dy = 0;
		if(angle>=0 && angle <=90   || angle <= 0 && angle >= -90) {
			 dx = Math.cos(radian);
			 dy = Math.sin(radian);
		}

		if(angle >90 && angle <= 180 || angle < -90 && angle >= -180 ) {
			 dx = -Math.cos(Math.PI - radian);
			 dy = Math.sin(Math.PI - radian);
		}
		
		
		if(angle >180 && angle <= 270 || angle < -180 && angle >= -270) {
			 dx = -Math.sin(3*Math.PI/2 - radian);
			 dy = -Math.cos(3*Math.PI/2 - radian);
		}
		
		if(angle >270 && angle < 360 ||  angle < -270 && angle > -360) {
			 dx = Math.cos(2*Math.PI - radian);
			 dy = -Math.sin(2*Math.PI - radian);
		}

		x += dx*5*oneForForward;
		y += dy*5*oneForForward;


	}

	public void down() {
		up(-1);
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
