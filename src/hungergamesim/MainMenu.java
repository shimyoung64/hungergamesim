package hungergamesim;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Image;

public class MainMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton addChar;
	JButton startGame;
	JButton saveChar;
	JButton loadChar;
	JComboBox<String> language;
	JPanel characterList;
	static int languageCh = 0;
	static Dimension prefSize = new Dimension(720, 150);
	static Dimension prefTextSize = new Dimension(100, 30);
	
	ArrayList<Contestant> contestants = new ArrayList<Contestant>();
	
	class CharacterPanel extends JPanel implements ActionListener {
		ImageIcon imgIcon;
		JPanel boxFrame;
		JButton editCharacter, removeCharacter;
		JTextField charName;
		MainMenu master;
		Contestant character;
		
		public CharacterPanel(ImageIcon ii, MainMenu ang, String path)
		{
			imgIcon = ii;
			master = ang;
			JPanel boxFrame = new JPanel();
			boxFrame.setLayout(new BoxLayout(boxFrame, BoxLayout.Y_AXIS));
			JLabel image = new JLabel();
			character = new Contestant();
			//character.setImage(ii);
			character.setImagePath(path);
			character.generateImage();
			charName = new JTextField();
			
			if(character.getName() != null)
			{
				charName.setText(character.getName());
			}
			
			editCharacter = new JButton("Edit Character");
			removeCharacter = new JButton("Remove Character");
			
			editCharacter.addActionListener(this);
			removeCharacter.addActionListener(this);
			
			image.setIcon(imgIcon);
			
			boxFrame.add(image);
			boxFrame.add(charName);
			boxFrame.add(editCharacter);
			boxFrame.add(removeCharacter);
			this.add(boxFrame);
		}
		public CharacterPanel(Contestant chara, MainMenu ang)
		{
			master = ang;
			JPanel boxFrame = new JPanel();
			boxFrame.setLayout(new BoxLayout(boxFrame, BoxLayout.Y_AXIS));
			JLabel image = new JLabel();
			character = chara;
			//character.setImage(ii);
			character.generateImage();
			imgIcon = character.getImage();
			charName = new JTextField();
			
			if(character.getName() != null)
			{
				charName.setText(character.getName());
			}
			
			editCharacter = new JButton("Edit Character");
			removeCharacter = new JButton("Remove Character");
			
			editCharacter.addActionListener(this);
			removeCharacter.addActionListener(this);
			
			image.setIcon(imgIcon);
			
			boxFrame.add(image);
			boxFrame.add(charName);
			boxFrame.add(editCharacter);
			boxFrame.add(removeCharacter);
			this.add(boxFrame);
		}
		
		public void changeLanguage(int lang)
		{
			switch(lang)
			{
			default:
			case 0:
				editCharacter.setText("Edit Character");
				removeCharacter.setText("Remove Character");
			break;
			case 1:
				editCharacter.setText("캐릭터 편집");
				removeCharacter.setText("캐릭터 제거");
			break;
			case 2:
				editCharacter.setText("μετᾰβᾰ́λλειν χᾰρᾰκτῆρᾰ");
				removeCharacter.setText("ἆποφέρειν χᾰρᾰκτῆρᾰ");
			break;
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == removeCharacter)
			{
				int index = 0;
				while(index < characterList.getComponentCount())
				{
					if(characterList.getComponents()[index] == this)
					{
						break;
					}
					index ++;
				}
				characterList.remove(index);
				master.revalidate();
			}
			if(e.getSource() == editCharacter)
			{
				new CharacterEditor(character);
			}
			String text = charName.getText();
			character.setName(text);
		}
	}
	
	public MainMenu(){
		//Main Menu
		this.setSize(1024,768);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
    	addChar = new JButton("Add Character");
    	addChar.addActionListener(this);
    	addChar.setSize(100, 60);
    	
    	startGame = new JButton("Start Game");
    	startGame.addActionListener(this);
    	startGame.setSize(100, 60);
    	
    	saveChar = new JButton("Save Characters");
    	saveChar.addActionListener(this);
    	saveChar.setSize(100, 60);
    	
    	loadChar = new JButton("Load Characters");
    	loadChar.addActionListener(this);
    	loadChar.setSize(100, 60);
    	
    	String[] languages = { "English", "한국어", "Ἑλληνῐκή" };
    	language = new JComboBox(languages);
    	language.addActionListener(this);
    	language.setSize(100, 60);
    	
    	characterList = new JPanel(new GridLayout(4,4,10,10));
    	characterList.setBounds(20,20,768,640);
    	characterList.setLayout(new BoxLayout(characterList, BoxLayout.LINE_AXIS));
    	JScrollPane scrollPane = new JScrollPane(characterList);
    	scrollPane.setBackground(new Color(190, 240, 255));
    	scrollPane.setBounds(0, 0, 768, 680);
    	scrollPane.setPreferredSize(new Dimension(768, 680));
    	
    	//this.add(scrollPane);
    	
    	JPanel menuPanel = new JPanel();
    	menuPanel.add(addChar);
    	menuPanel.add(startGame);
    	menuPanel.add(saveChar);
    	menuPanel.add(loadChar);
    	menuPanel.add(language);
    	
    	mainPanel.add(scrollPane);
    	mainPanel.add(menuPanel);
    	menuPanel.setLocation(0, 680);
    	menuPanel.setSize(768, 80);
    	menuPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE)
            ));
    	//this.pack();
    	this.add(mainPanel);
    	this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addChar)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			
			int response = chooser.showOpenDialog(null);
			File[] files = chooser.getSelectedFiles();
			
			if(response == JFileChooser.APPROVE_OPTION)
			{
				for(File f : files)
				{
					//File file = new File(chooser.getSelectedFile().getAbsolutePath());
					ImageIcon imgIcon = new ImageIcon(f.getAbsolutePath());
					Image img = imgIcon.getImage();
					imgIcon = new ImageIcon(img.getScaledInstance(128,128,java.awt.Image.SCALE_SMOOTH));
					
					JPanel characterFrame = new CharacterPanel(imgIcon, this, f.getAbsolutePath());
					
					characterList.add(characterFrame, BorderLayout.CENTER);
					int i = characterList.getComponentCount();
					//System.out.println(i);
					characterList.setLayout(new GridLayout((i+3)/4,4,10,10));
					
					//System.out.println("ANG?");
					revalidate();
				}
			}
		}
		if(e.getSource() == startGame)
		{
			//Start game for now, add character stuff later
			contestants.clear();
			for(int i = 0; i < characterList.getComponentCount(); i++)
			{
				CharacterPanel contestant = (CharacterPanel) characterList.getComponents()[i];
				String onoma = ((JTextField)(((JPanel)contestant.getComponents()[0]).getComponents()[1])).getText();
				//System.out.println(onoma);
				contestant.character.setName(onoma);
				contestant.character.randomizeStats();
				
				contestants.add(contestant.character);
			}
			new HungerGame(contestants);
		}
		if(e.getSource() == saveChar)
		{
			contestants.clear();
			for(int i = 0; i < characterList.getComponentCount(); i++)
			{
				CharacterPanel contestant = (CharacterPanel) characterList.getComponents()[i];
				String onoma = ((JTextField)(((JPanel)contestant.getComponents()[0]).getComponents()[1])).getText();
				//System.out.println(onoma);
				contestant.character.setName(onoma);
				contestant.character.randomizeStats();
				
				contestants.add(contestant.character);
			}
			ProfileSaver ps = new ProfileSaver(contestants);
			ps.saveCharacters();
		}
		if(e.getSource() == loadChar)
		{
			ProfileLoader pl = new ProfileLoader(this);
			pl.loadCharacters();
		}
		if(e.getSource() instanceof JComboBox)
		{
			JComboBox languageChoice = (JComboBox)e.getSource();
			String ang = (String) languageChoice.getSelectedItem();
			if(ang == "English")
			{
				changeLanguage(0);
			}
			else if(ang == "한국어")
			{
				changeLanguage(1);
			}
			else if(ang == "Ἑλληνῐκή")
			{
				changeLanguage(2);
			}
		}
	}
	
	public void clearCharacters()
	{
		characterList.removeAll();
	}
	
	public void addCharacter(Contestant chara)
	{
		ImageIcon imgIcon = new ImageIcon(chara.getImagePath());
		Image img = imgIcon.getImage();
		imgIcon = new ImageIcon(img.getScaledInstance(128,128,java.awt.Image.SCALE_SMOOTH));
		
		JPanel characterFrame = new CharacterPanel(chara, this);
		
		characterList.add(characterFrame, BorderLayout.CENTER);
		int i = characterList.getComponentCount();
		//System.out.println(i);
		characterList.setLayout(new GridLayout((i+3)/4,4,10,10));
		
		//System.out.println("ANG?");
		revalidate();
	}

	public void changeLanguage(int lang)
	{
		languageCh = lang;
		switch(lang)
		{
		default:
		case 0:
			addChar.setText("Add Character(s)");
			startGame.setText("Start Game");
			saveChar.setText("Save Characters");
			loadChar.setText("Load Characters");
		break;
		case 1:
			addChar.setText("캐릭터(들) 추가하기");
			startGame.setText("헝거게임 시작");
			saveChar.setText("캐릭터들 저장하기");
			loadChar.setText("캐릭터들 불러오기");
		break;
		case 2:
			addChar.setText("πρόσθεσις χᾰρᾰκτῆρᾰ");
			startGame.setText("ἀρχή παιδείας");
			saveChar.setText("ἀπογραφή χᾰρᾰκτῆρᾰς");
			loadChar.setText("κλῆσις χᾰρᾰκτῆρᾰς");
		break;
		}
		for(int i = 0; i < characterList.getComponentCount(); i++)
		{
			CharacterPanel contestant = (CharacterPanel) characterList.getComponents()[i];
			contestant.changeLanguage(lang);
		}
	}
}
	