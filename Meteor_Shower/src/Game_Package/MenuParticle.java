package Game_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject
{

	private Handler handler;
	Random r = new Random() ; 
	
//	private int red = r.nextInt(255) ; 
//	private int green = r.nextInt(255) ; 
//	private int blue = r.nextInt(255) ;
	
	private Color color ; 
	
	int dir = 0 ; 
	
	public MenuParticle(int x, int y, ID id,Handler handler) 
	{
		super(x, y, id);
		this.handler = handler ; 
		
//		velX = (r.nextInt(5 - -5) + -5) ; 
//		velY = (r.nextInt(5 - -5) + -5) ; 
		
		//design objects moving in random speeds 
		velX = 4 ; //(r.nextInt(5)) ; 
		velY = 4 ; //(r.nextInt(5)) ;
		
		//if the design object is static without moving , increase its speed
		if (velX == 0 ) velX = 3 ; 
		if (velY == 0 ) velY = 3 ; 
		
//		dir = r.nextInt(2) ; 
//		if (dir == 0 )
//		{
//			velX = 2 ; 
//			velY = 7 ; 
//		}
//		else if (dir == 1)
//		{
//			velX = 7 ; 
//			velY = 2 ;
//		}
		 
		
		color = new Color(r.nextInt(255) , r.nextInt(255) , r.nextInt(255) ) ; 
	}

	@Override
	public void tick() 
	{
		x+=velX ; 
		y+=velY ; 
		
		//handling boundary conditions for the enemy object
		if (x <= 0 || x >= Game.WIDTH - 16) velX*=-1 ; 
		if (y <= 0 || y >= Game.HEIGHT - 32) velY*=-1 ; 
		
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,color,16,16,0.05f,handler));
		//in the above line , u can change the length of the tail by changing the float value 
		//no . of enemies can be increased by increasing the number of enemy objects in Game.java file 
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,16,16) ; 
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(color);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
