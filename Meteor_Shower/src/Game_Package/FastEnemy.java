package Game_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject
{

	private Handler handler;

	public FastEnemy(int x, int y, ID id,Handler handler) 
	{
		super(x, y, id);
		this.handler = handler ; 
		velX = 2 ; 
		velY = 9 ; 
	}

	@Override
	public void tick() 
	{
		x+=velX ; 
		y+=velY ; 
		
		//handling boundary conditions for the enemy object
		if (x <= 0 || x >= Game.WIDTH - 16) velX*=-1 ; 
		if (y <= 0 || y >= Game.HEIGHT - 32) velY*=-1 ; 
		
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.cyan,16,16,0.05f,handler));
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
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
