package Game_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject
{
	Random r = new Random() ;
	Handler handler; 
	
	public Player(int x , int y , ID id , Handler handler )
	{
		super(x,y,id) ; 
		this.handler = handler ; 
//		velX = r.nextInt(5) + 1 ;
//		velY = r.nextInt(5) ; 
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,32,32) ; 
	}
	
	public void tick()
	{
		x += velX ; 
		y += velY ; 
		
		//handling boundary conditions for the object 
		x = Game.clamp((int)x, 0, Game.WIDTH - 37) ; 
		y = Game.clamp((int)y, 0, Game.HEIGHT - 60) ;
		
		collision() ; 
	}
	
	private void collision ()
	{
		for (int i = 0 ; i <handler.object.size() ; i++)
		{
			GameObject tempObj = handler.object.get(i) ;
			
			if (tempObj.getID() == ID.BasicEnemy || tempObj.getID() == ID.FastEnemy || tempObj.getID() == ID.SmartEnemy )
			{
				if (getBounds().intersects(tempObj.getBounds()))
				{
					//collision code 
					HUD.HEALTH -= 2 ; 
				}
			}
		}
	}
	
	public void render(Graphics g )
	{
		
		//if (id == ID.Player)
		g.setColor(Color.white);
		//else if (id == ID.Player2)
			//g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
	}
}









