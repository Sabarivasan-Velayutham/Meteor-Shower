package Game_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject
{

	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id,Handler handler) 
	{
		super(x, y, id);
		this.handler = handler ; 
//		velX = 5 ; 
//		velY = 5 ; 
		
		for (int i = 0 ; i < handler.object.size() ; i++)
		{
			if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i) ; 
		}
	}

	@Override
	public void tick() 
	{
		x+=velX ; 
		y+=velY ; 
		
		float diffX = x - player.getX() - 8 ;
		float diffY = y - player.getY() - 8 ;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY())) ; 
		
		velX = (int) ((-1.0/distance) * diffX) ; 
		velY = (int) ((-1.0/distance) * diffY) ; 
		
		//handling boundary conditions for the enemy object
		if (x <= 0 || x >= Game.WIDTH - 16) velX*=-1 ; 
		if (y <= 0 || y >= Game.HEIGHT - 32) velY*=-1 ; 
		
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.green,16,16,0.05f,handler));
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
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
