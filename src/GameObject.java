import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY; // control speed on x and y direction
	
	public GameObject(float x, float y, ID id){ // our Constructor
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); //we are going to use intersect method from rectangle class to detect collision
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	
	

}
