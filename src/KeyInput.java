import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter { // for player control
	
	private Handler handler;
	private boolean[] keyDown =new boolean[4];
	
	public KeyInput(Handler handler){
		this.handler = handler;
		keyDown[0]=false; //W
		keyDown[1]=false; //S
		keyDown[2]=false; //D
		keyDown[3]=false; //A
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//Key EVENTS for player 1
				if(key == KeyEvent.VK_W){ 
					tempObject.setVelY(-5); //W move forward
					keyDown[0] = true; //being pressed means true
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelY(5); //S move backward
					keyDown[1] = true;//being pressed means true
				}
				if(key == KeyEvent.VK_A){
					tempObject.setVelX(-5); // A Move left
					keyDown[3] = true;//being pressed means true
				}
				if(key == KeyEvent.VK_D){
					tempObject.setVelX(5); // D move right
					keyDown[2] = true;//being pressed means true
				}
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1); //press esc key to exit
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//Key EVENTS for player 1
				if(key == KeyEvent.VK_W) 
					keyDown[0] = false;
					//tempObject.setVelY(0); //stop move forward 
				if(key == KeyEvent.VK_S) 
					keyDown[1] =  false;
					//tempObject.setVelY(0); //stop move backward
				if(key == KeyEvent.VK_A)
					keyDown[3] = false;
					//tempObject.setVelX(0); // stop Move left
				if(key == KeyEvent.VK_D)
					keyDown[2] = false;
					//tempObject.setVelX(0); // stop move right
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1])tempObject.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3])tempObject.setVelX(0);
			}
		}
	}

}

//5/3/2015 Keyboard Input Glitch Fixed
