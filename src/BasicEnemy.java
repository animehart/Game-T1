import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class BasicEnemy extends GameObject{

	private Handler handler;
	
	
	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1; // if it hits the top, multiply the velocity with -1 to reverse its direction
		if(x <= 0 || x >= Game.WIDTH-16) velX *= -1; //if it hits the side, multiply the velocity with -1 to reverse its direction
	
		handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 16, 16, 0.05f, handler));
	}
	public void render(Graphics g){
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
