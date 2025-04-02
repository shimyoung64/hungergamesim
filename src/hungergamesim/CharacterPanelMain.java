package hungergamesim;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CharacterPanelMain extends JPanel {
	ImageIcon imgIcon;
	JPanel boxFrame;
	Contestant character;
	
	public CharacterPanelMain(Contestant chara, ImageIcon image)
	{
		character = chara;
		imgIcon = image;
		boxFrame = new JPanel(new GridBagLayout());
		boxFrame.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.insets = new Insets(10,10,10,10);
		JLabel imageStuff = new JLabel();
		imageStuff.setIcon(imgIcon);
		boxFrame.add(imageStuff, c);
		
		System.out.println(character.getName());
		JLabel onoma = new JLabel(character.getName());
		//c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		boxFrame.add(onoma, c);
		
		JLabel health = new JLabel(character.getHealth() + " / " + character.getMaxHealth());
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		boxFrame.add(health, c);
		
		JLabel stamina = new JLabel(character.getStamina() + " / " + character.getMaxStamina());
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		boxFrame.add(stamina, c);
		
		JButton seeStats = new JButton("Stats");
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		boxFrame.add(seeStats, c);
		
		this.add(boxFrame);
    	//this.pack();
    	this.setVisible(true);
	}
}
