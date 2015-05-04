import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class EnemyBoss extends GameObject{

	private Handler handler;
	Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 50;
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if(timer<=0){
			velY = 0;
		}
		else
			timer--;
		
		if(timer <= 0){
			timer2--;
		}
		if(timer2 <= 0){
			if(velX == 0){
				velX = 3;
			}
				int spawn = r.nextInt(5);
				if(spawn == 0){
					handler.addObject(new BossBullet((int)x+48,(int)y+48, ID.BasicEnemy,handler));
			}
		}
		
		//if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1; // if it hits the top, multiply the velocity with -1 to reverse its direction
		if(x <= 0 || x >= Game.WIDTH-96) velX *= -1; //if it hits the side, multiply the velocity with -1 to reverse its direction
	
		//handler.addObject(new Trail(x, y, ID.Trail, Color.ORANGE, 64, 64, 0.05f, handler));
	}
	public void render(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillRect((int)x, (int)y, 64, 64);
	}

}
