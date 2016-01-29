import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 499776485135926340L;
	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
		
	public static STATE gameState = STATE.Menu;
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, gameState, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Game Beta", this);
		spawner = new Spawn(handler, hud);
		r = new Random();
		if(gameState == STATE.Game){
			handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32, ID.Player, handler)); //spawns player in the middle
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy, handler)); //spawn enemy	
		}else if(gameState == STATE.Menu){
			for(int i=0;i<10;i++){
				handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){ // Very Popular game loop
		this.requestFocus(); // cause it to automatically focus on the game screen instead of us needing to click on it
		long lastTime = System.nanoTime();
		double ticksAmount = 60.0;
		double ns = 1000000000 / ticksAmount;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		if(gameState == STATE.Game){
			hud.tick();
			spawner.tick();
			
			if(HUD.HEALTH <= 0){
				HUD.HEALTH = 100;
				gameState = STATE.End;
				handler.clearEnemies();
			}
		}
		else if (gameState == STATE.Menu ||gameState == STATE.End){
			menu.tick();
		}
	}
	
	private void render(){
		BufferStrategy buffstrat = this.getBufferStrategy();
		if(buffstrat == null){
			this.createBufferStrategy(3); //create 3 buffer in out game
			return;
		}
		Graphics g = buffstrat.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(gameState == STATE.Game){ //check the state of the program
			hud.render(g);
		}
		else if(gameState == STATE.Menu|| gameState == STATE.Help||gameState == STATE.End){ //check the state of the program
			menu.render(g);
		}
		g.dispose();
		buffstrat.show();
		}
		
	public static float clamp(float var, float min, float max){
		if(var >= max){
			return var = max;
		}
		else if(var <= min){
			return var = min;
		}
		else
			return var;
	}
	public static void main(String args[]){
		new Game();
	}

}
