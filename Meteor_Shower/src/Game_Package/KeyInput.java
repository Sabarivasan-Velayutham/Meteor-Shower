package Game_Package;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent ; 

public class KeyInput extends KeyAdapter
{
	private Handler handler ; 
	private boolean[] keyDown = new boolean[4] ; 
	
	public KeyInput(Handler handler)
	{
		this.handler = handler ; 
		
		keyDown[0] = false ; 
		keyDown[1] = false ; 
		keyDown[2] = false ; 
		keyDown[3] = false ; 
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode()  ; 
		//System.out.println(key);
		
		for (int i = 0 ; i < handler.object.size();i++)
		{
			GameObject tempObj = handler.object.get(i) ; 
			
			if (tempObj.getID() == ID.Player)
			{
				// the value in the argument in setVel() func denotes the speed of the object 
				//- indicates normal direction 
				//+ indicates inverse direction 
				if (key == KeyEvent.VK_UP) { tempObj.setVelY(-5);   keyDown[0] = true ; }  
				if (key == KeyEvent.VK_DOWN) { tempObj.setVelY(5);  keyDown[1] = true ; } 
				if (key == KeyEvent.VK_RIGHT) { tempObj.setVelX(5); keyDown[2] = true ; } 
				if (key == KeyEvent.VK_LEFT) { tempObj.setVelX(-5); keyDown[3] = true ; } 
			}
			
			if (key == KeyEvent.VK_ESCAPE) System.exit(1);
			
//			if (tempObj.getID() == ID.Player2)
//			{
//				//key events for player 2
//				if (key == KeyEvent.VK_UP) tempObj.setVelY(-5);
//				if (key == KeyEvent.VK_DOWN) tempObj.setVelY(5);
//				if (key == KeyEvent.VK_RIGHT) tempObj.setVelX(5);
//				if (key == KeyEvent.VK_LEFT) tempObj.setVelX(-5);
//				
//			}
		}
	}
	
	public void keyReleased(KeyEvent e )
	{
		int key = e.getKeyCode()  ;
		
		for (int i = 0 ; i < handler.object.size();i++)
		{
			GameObject tempObj = handler.object.get(i) ; 
			
			// the value in the argument in setVel() func denotes the speed of the object 
			//- indicates normal direction 
			//+ indicates inverse direction 
			
			if (tempObj.getID() == ID.Player)
			{
				//key events for player 1 
				if (key == KeyEvent.VK_UP)    tempObj.setVelY(0);
				if (key == KeyEvent.VK_DOWN)  tempObj.setVelY(0);
				if (key == KeyEvent.VK_RIGHT) tempObj.setVelX(0);
				if (key == KeyEvent.VK_LEFT)  tempObj.setVelX(0);
			}
			
//			//vertical movement 
//			if (!keyDown[0] && !keyDown[1] ) tempObj.setVelY(0);
//			//horizontal movement 
//			if (!keyDown[2] && !keyDown[3] ) tempObj.setVelX(0);
			
//			if (tempObj.getID() == ID.Player2)
//			{
//				//key events for player 2
//				if (key == KeyEvent.VK_UP) tempObj.setVelY(0);
//				if (key == KeyEvent.VK_DOWN) tempObj.setVelY(0);
//				if (key == KeyEvent.VK_RIGHT) tempObj.setVelX(0);
//				if (key == KeyEvent.VK_LEFT) tempObj.setVelX(0);
//				
//			}
		}
	}
}




