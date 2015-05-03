import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255319694373975038L;
    public Window(int width, int height, String title, Game game){
    	JFrame frame = new JFrame(title);
    	frame.setPreferredSize(new Dimension(width, height));
    	frame.setMaximumSize(new Dimension(width, height));
    	frame.setMinimumSize(new Dimension(width, height));
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to exit *VERY IMPORTANT
    	frame.setResizable(false); //prevent user to resize windows
    	frame.setLocationRelativeTo(null); //Cause window to appear in center
    	frame.add(game); // add the game
    	frame.setVisible(true); // set to true so that we can see our window
    	game.start();
    	
    }
}
