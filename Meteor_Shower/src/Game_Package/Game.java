package Game_Package;

import java.awt.*;//.Canvas;
//import java.awt.*;//Dimension;
//import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

//import javax.swing.*;//JFrame;

public class Game extends Canvas implements Runnable
{
	
	private static final long serialVersionUID = 1L ;
	public static final int WIDTH = 640 , HEIGHT = WIDTH/12*9 ; 
	
	private Thread thread ; 
	private boolean running = false ; 
	
	private Handler handler ; 
	private Spawn Spawner ; 
	private Random r ; 
	private HUD hud ; 
	private Menu menu ; 
	
	
	public enum STATE
	{
		Menu , 
		Help , 
		Game ,
		End ; 
	}
	
	public static STATE gameState = STATE.Menu ;
	//public STATE gameState = STATE.Game ;
	
	public Game()
	{
		handler = new Handler() ;
		hud = new HUD() ; 
		menu = new Menu(this,handler, hud ) ;
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH,HEIGHT,"METEOR SHOWER",this);
//		hud = new HUD() ; 
		 
		Spawner = new Spawn(handler , hud ) ; 
		r = new Random() ; 
		
		if (gameState == STATE.Game)
		{
			handler.addObject(new Player(WIDTH/2-32 , HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50) , r.nextInt(HEIGHT - 50) , ID.BasicEnemy,handler));
		}
		else
		{
			for (int i = 0 ; i < 30 ; i++)
			{
				handler.addObject(new MenuParticle(r.nextInt(WIDTH) , r.nextInt(HEIGHT),ID.MenuParticle,handler));
			}
		}
		//handler.addObject(new SmartEnemy(r.nextInt(WIDTH - 50) , r.nextInt(HEIGHT - 50) , ID.SmartEnemy,handler));
		
		//this for loop creates multiple enemies at random positions 
//		for (int i = 0 ; i < 10 ; i++)
//		{
//			handler.addObject(new BasicEnemy(r.nextInt(WIDTH) , r.nextInt(HEIGHT) , ID.BasicEnemy));
//		}
		
		//handler.addObject(new BasicEnemy(WIDTH/2-32 , HEIGHT/2-32,ID.BasicEnemy));
		
	}
	public synchronized void start()
	{
		thread = new Thread(this) ; 
		thread.start() ; 
		running = true ; 
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join() ; 
			running = false ; 
		}
		catch (Exception e )
		{e.printStackTrace();}
	}
	
	public void run()
	{
		//this line is used to focus on the main object , which means you don't need to click the mouse on the screen 
		//to make the object ready for movement 
		//otherwise you need to click on the screen for object movement initialization 
		this.requestFocus();
		
		long lastTime = System.nanoTime(); 
		double amountofticks = 60;
		double ns = 1000000000 / amountofticks;
		double delta = 0 ; 
		long timer = System.nanoTime();
		int frames = 0 ; 
		while ( running )
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns ; 
			lastTime = now ; 
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if (System.currentTimeMillis() - timer < 1000)
			{
				timer += 1000 ; 
				//System.out.println("FPS : "+frames) ; 
			}
		}
		stop() ; 
	}
	
	private void tick()
	{
		handler.tick(); 
		if (gameState == STATE.Game)
		{
			hud.tick();
			Spawner.tick();
			
			if (HUD.HEALTH <= 0 )
			{
				HUD.HEALTH = 100 ; 
//				hud.setLevel(1);
//				hud.score(0);
				gameState = STATE.End ; 
				handler.clearEnemys();
				
				for (int i = 0 ; i < 30 ; i++)
				{
					handler.addObject(new MenuParticle(r.nextInt(WIDTH) , r.nextInt(HEIGHT),ID.MenuParticle,handler));
				}
			}
		}
		
		else if (gameState == STATE.Menu || gameState == STATE.End )
		{
			menu.tick();
		}
//		hud.tick();
//		Spawner.tick() ; 
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy() ; 
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return ; 
		}
		
		Graphics g = bs.getDrawGraphics() ; 
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if (gameState == STATE.Game)
		{
			hud.render(g) ;
			//Spawner.tick();
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End )
		{
			menu.render(g);
		}
		else 
		{
			g.setColor(Color.white);
			g.drawString("Menu",100,100) ; 
		}
		
		g.dispose(); 
		bs.show() ; 
		
	}
	
	public static float clamp(float var , float min , float max)
	{
		if (var >= max ) return var = max ;
		else if (var <= min ) return var = min ; 
		else return var ; 
	}
	
	public static void main(String args[])
	{
		new Game() ; 
	}
}


















