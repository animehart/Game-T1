import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class MenuParticle extends GameObject{

	private Handler handler;
	Random r = new Random();
	private Color col ;
	
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		velX = 2;
		velY = 9;
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1; // if it hits the top, multiply the velocity with -1 to reverse its direction
		if(x <= 0 || x >= Game.WIDTH-16) velX *= -1; //if it hits the side, multiply the velocity with -1 to reverse its direction
	
		handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.05f, handler));
	}
	public void render(Graphics g){
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}