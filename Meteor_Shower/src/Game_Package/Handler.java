package Game_Package;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler 
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick()
	{
		for (int i = 0 ; i < object.size(); i++)
		{
			GameObject tempobj = object.get(i) ; 
			tempobj.tick();
		}
		
	}
	
	public void render(Graphics g )
	{
		for (int i = 0 ; i < object.size(); i++)
		{
			GameObject tempobj = object.get(i) ; 
			tempobj.render(g);
		}
		
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object)  ;
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}

	public void clearEnemys() 
	{
		for (int i = 0 ; i < object.size() ; i++)
		{
			GameObject tempobj = object.get(i) ; 
			
			if (tempobj.getID() == ID.Player)
			{
				object.clear();
				if (Game.gameState != Game.STATE.End)
					addObject(new Player ((int)tempobj.getX() , (int)tempobj.getY(), ID.Player , this)) ; 
			}
		}
		
	}
}













