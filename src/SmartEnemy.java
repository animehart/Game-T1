import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class SmartEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		
		for(int i=0; i<handler.object.size(); i++){ //search our for loop and see if the object is player. if it is, set our gameobject here to player
			if(handler.object.get(i).getID() == ID.Player){
				player = handler.object.get(i);
			}
		}
		
		//velX = 5;
		//velY = 5;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		velX = ((-2/distance)*diffX);
		velY = ((-2/distance)*diffY);
		
	//	if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1; // if it hits the top, multiply the velocity with -1 to reverse its direction
	//	if(x <= 0 || x >= Game.WIDTH-16) velX *= -1; //if it hits the side, multiply the velocity with -1 to reverse its direction
	
		handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 16, 16, 0.05f, handler));
	}
	public void render(Graphics g){
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}