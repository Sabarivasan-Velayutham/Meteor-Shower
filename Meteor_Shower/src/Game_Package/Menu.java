package Game_Package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import Game_Package.Game.STATE;

public class Menu extends MouseAdapter
{
	private Game game ; 
	private Handler handler ; 
	private HUD hud  ; 
	private Random r = new Random() ; 
	
	public Menu(Game game , Handler handler  , HUD hud )
	{
		this.game = game ; 
		this.hud = hud ; 
		this.handler = handler ; 
	}
	
	public void mousePressed(MouseEvent e )
	{
		int mx = e.getX() ; 
		int my = e.getY() ; 
		
		if (Game.gameState == STATE.Menu)
		{
			
			//play Button codes 
			if (mouseOver(mx,my,210,150,200,64))
			{
				Game.gameState = STATE.Game ; 
				handler.addObject(new Player(Game.WIDTH/2-32 , Game.HEIGHT/2-32,ID.Player,handler));
				handler.clearEnemys() ; 
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) , r.nextInt(Game.HEIGHT - 50) , ID.BasicEnemy,handler));
			}
		}
			
			//help Button codes 
			if (mouseOver(mx,my,210,250,200,64))
			{
				Game.gameState = STATE.Help ; 
			}
			
			//back button from help button codes 
			if (Game.gameState == STATE.Help)
			{
				if (mouseOver(mx,my,210,350,200,64))
				{
					Game.gameState = STATE.Menu ; 
					return ; 
				} 
			}
			
			//back button from help button codes 
			if (Game.gameState == STATE.End)
			{
				if (mouseOver(mx,my,210,350,200,64))  //210,350,200,64
				{
					Game.gameState = STATE.Game ; 
					hud.setLevel(1) ; 
					hud.score(0) ; 
					handler.addObject(new Player(Game.WIDTH/2-32 , Game.HEIGHT/2-32,ID.Player,handler));
					handler.clearEnemys() ; 
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) , r.nextInt(Game.HEIGHT - 50) , ID.BasicEnemy,handler));
				} 
				return ; 
			}
					
			//quit Button codes 
			if (mouseOver(mx,my,210,350,200,64))
			{
				System.exit(1) ; 
			}
//		}
	}
	
	public void mouseReleased(MouseEvent e )
	{
		
	}
	
	private boolean mouseOver(int mx , int my , int x , int y , int width , int height )
	{
		if (mx > x && mx < x + width)
		{
			if (my > y && my < y + height) return true ;
			else return false ; 
		}
		else return false ; 
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g )
	{
		Font fnt1 = new Font("arial",1,50) ; 
		Font fnt2 = new Font("arial",1,30) ;
		
		if (Game.gameState == STATE.Menu)
		{
//			Font fnt1 = new Font("arial",1,50) ; 
//			Font fnt2 = new Font("arial",1,30) ; 
			
			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Menu", 240, 70);
			
			g.setFont(fnt2) ; 

			g.drawRect(210,150,200,64) ;
			g.drawString("Play",270,190) ; 
			
			
			g.drawRect(210,250,200,64) ;
			g.drawString("Help",270,290) ;
			
			g.drawRect(210,350,200,64) ;
			g.drawString("Quit",270,390) ;
		}
		
		else if (Game.gameState == STATE.Help)
		{
//			Font fnt1 = new Font("arial",1,50) ; 
			
			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("Use Arrow Keys to move ...",100,200) ;
			
			g.setFont(fnt2);
			g.drawRect(210,350,200,64) ;
			g.drawString("Back",270,390) ;
		}
		
		else if (Game.gameState == STATE.End)
		{
			
			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt2);
			g.drawString("You lost with a score of : " +hud.getScore() ,115,200) ;
			
			g.setFont(fnt2);
			g.drawRect(210,350,200,64) ;
			g.drawString("Try Again",245,390) ;
		}
		
	}
}

