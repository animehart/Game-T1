import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-37); //TRIAL AND ERROR TO FIND LIMIT
		y = Game.clamp(y, 0, Game.HEIGHT-60); //TRIAL AND ERROR TO FIND LIMIT

		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 32, 32, 0.05f, handler));
		collision();
		
	}
	
	private void collision(){ //TO DETECT DAMAGE AND DECREASE HP BAR
		for(int i=0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID()==ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy){
				//collision stuff
				if(getBounds().intersects(tempObject.getBounds())){ //if player touches enemy then decrease the player hp
					HUD.HEALTH-=2;
				}
			}
			else if(tempObject.getID() == ID.EnemyBoss){
				if(getBounds().intersects(tempObject.getBounds())){ //if player touches enemy then decrease the player hp
					HUD.HEALTH-=100;
			}
			}
		}
		}
	
	public void render(Graphics g){
		//if(id == ID.Player)
		
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
}
