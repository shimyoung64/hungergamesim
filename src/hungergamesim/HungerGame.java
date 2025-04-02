package hungergamesim;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A Game, which handles the contestants (you can have multiple games running theoretically)
 * @author Mathetes tou Megalou Alexandrou
 *
 */
public class HungerGame extends JFrame implements ActionListener {

	ArrayList<Contestant> contestants = new ArrayList<Contestant>();
	//Each game is a separate frame, allowing for multiple games with same setting
	public HungerGame() {
		this.setSize(1024,768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//ScrollPane for Text
		JTextArea testText = new JTextArea(800, 600);
		JScrollPane scrollPane = new JScrollPane(testText);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//this.add(menuPanel);
    	this.add(scrollPane);
    	//this.pack();
    	this.setVisible(true);
	}
	
	public HungerGame(ArrayList<Contestant> gamers)
	{
		contestants = gamers;
		this.setSize(1024,768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//ScrollPane for Text
		//JTextArea testText = new JTextArea(800, 600);
		//JScrollPane scrollPane = new JScrollPane(testText);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		//On the left, a panel for contestants
		JPanel contestantPanel = new JPanel(new GridLayout(0,1,10,10));
		JScrollPane scrollPane = new JScrollPane(contestantPanel);
		contestantPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		for(Contestant con : contestants) {
			CharacterPanelMain cpm = new CharacterPanelMain(con, con.getImage());
			contestantPanel.add(cpm);
		}
		c.ipady = 200;
		c.weightx = 0.75;
		c.weighty = 0.9;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(scrollPane, c);
		
		
		JPanel mapPanel = new JPanel(); // Replace with MapPanel Class
		mapPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.9;
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(mapPanel, c);
		
		JPanel statusPanel = new JPanel(); // Replace with MapPanel Class
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.9;
		c.gridx = 2;
		c.gridy = 0;
		mainPanel.add(statusPanel, c);
		
		JPanel controlPanel = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weightx = 0.75;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(controlPanel, c);
		
		//this.add(menuPanel);
    	this.add(mainPanel);
    	//this.pack();
    	this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
