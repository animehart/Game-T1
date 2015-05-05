import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	protected STATE state;
	public Menu(Game game, Handler handler, STATE state, HUD hud){
		this.game = game;
		this.handler = handler;
		this.state = state;
		this.hud = hud;
	}
	private Spawn spawn = new Spawn(handler, hud);
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu){
			
		if(mouseOver(mx, my, 215, 150, 200,64) ){
			Game.gameState = STATE.Game;
			hud.setLevel(1);
			hud.score(0);
			handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler)); //spawns player in the middle
			handler.clearEnemies();
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler)); //spawn enemy
		}
		
		if(mouseOver(mx,my,215,250,200,64)){
			handler.clearEnemies();
			game.gameState = STATE.Help;
		}
		
		if(mouseOver(mx,my,215,350,200,64)){
			System.exit(1);
		}
		
		}
		
		if(game.gameState == STATE.End){
			if(mouseOver(mx,my,215,350,200,64)){
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		if(game.gameState == STATE.Help){
			if(mouseOver(mx,my,215,350,200,64)){
				game.gameState = STATE.Menu;
				return;
			}
		}	
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}
			else return false;
		}else return false;
	}

	public void tick(){
		
	}
	public void render(Graphics g){ //Title Screen
		
		if(game.gameState == STATE.Menu){
			Font font = new Font("Arial",1,50); //declaring font type
			Font font2 = new Font("Arial",1, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Beta", 180, 50);
		
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawRect(215, 150, 200, 64);
			g.drawString("Play Game",240,190);
		
			g.setColor(Color.white);
			g.drawRect(215, 250, 200, 64);
			g.drawString("Tutorial",240,290);
		
			g.setColor(Color.white);
			g.drawRect(215, 350, 200, 64);
			g.drawString("Exit",240,390);
		}
		 if(game.gameState == STATE.Help){
			Font font = new Font("Arial",1,50); //declaring font type
			Font font2 = new Font("Arial",1, 20);
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Help Tutorial", 250, 50);
			g.drawString("Use WASD key to control. Dodge the projectiles", 100, 250);
			g.drawRect(215, 350, 200, 64);
			g.drawString("Return",280,390);
		}
		 if(game.gameState == STATE.End){
				Font font = new Font("Arial",1,50); //declaring font type
				Font font2 = new Font("Arial",1, 20);
				g.setFont(font2);
				g.setColor(Color.white);
				g.drawString("Game Over", 250, 50);
				g.drawString("Score: "+ hud.getScore(), 100, 250);
				g.drawRect(215, 350, 200, 64);
				g.drawString("Try Again",280,390);
			}
	}
}
