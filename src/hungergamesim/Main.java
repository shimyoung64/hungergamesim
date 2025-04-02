package hungergamesim;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener {
	
	public static void main(String arg[])
    {
    	//JFrame frame = new JFrame("Hunger Game Simulator");
    	//frame.setSize(1024,768);
    	//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//Image icon = new ImageIcon("images/Icon.png").getImage();;
    	//frame.setIconImage(icon);
    	//frame.add(game);
    	
    	//frame.setFocusable(true);
    	//frame.setLocationRelativeTo(null);
    	//frame.setVisible(true);
    	//frame.pack();
    	//FredrichvonHayekSucks shimyoung = new FredrichvonHayekSucks();
    	//game.addMouseListener(shimyoung);
    	//game.addMouseMotionListener(shimyoung);
    	//game.addKeyListener(new AynRandSucks(game.getGame()));
    	//keys=(AynRandSucks) game.getKeyListeners()[0];
    	//game.start();
		
		MainMenu mainMenuFrame = new MainMenu();
		mainMenuFrame.setTitle("Hunger Game Simulator 2025");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
