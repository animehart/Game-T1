import java.util.Random;


public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud  =  hud;
	}
	
	public void tick(){
		scoreKeep++;
		if(scoreKeep==500){ //if the score reached certain value, increase the level
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1); //increase the level
			
			if(hud.getLevel() == 2){ //commenting this if statement will cause the program to spawn enemy everytime we hit 500
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50, ID.BasicEnemy, handler)); //if player reach level 2, spawn enemy
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50, ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 3){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50, ID.FastEnemy, handler));
			}
			else if(hud.getLevel() == 4){
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50, ID.SmartEnemy, handler));
			}
			else if(hud.getLevel() == 5){
				handler.clearEnemies();
				handler.addObject(new EnemyBoss((Game.WIDTH/2)-48,-120,ID.EnemyBoss,handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50, ID.SmartEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50, ID.SmartEnemy, handler));
			}
		}
	}
}
