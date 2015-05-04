import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	protected STATE state;
	public Menu(Game game, Handler handler, STATE state){
		this.game = game;
		this.handler = handler;
		this.state = state;
	}
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if(mouseOver(mx, my, 215, 150, 200,64) ){
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler)); //spawns player in the middle
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler)); //spawn enemy
		}
		
		if(mouseOver(mx,my,215,250,200,64)){
			game.gameState = STATE.Help;
		}
		
		if(mouseOver(mx,my,215,350,200,64)){
			System.exit(1);
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
			Font font2 = new Font("Arial",1, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help Tutorial", 180, 50);
		}
	}
}
