package hungergamesim;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class CharacterEditor extends JFrame implements ActionListener {
	
	Contestant character;
	JButton confirmStat, cancel;
	StatPanel[] statPanels;
	ButtonGroup genders;
	JRadioButton male;
	JRadioButton female;
	JRadioButton nonbinary;
	JRadioButton noGender;
	JTextArea extraStats;
	static boolean init = false;
	static HashMap<String, String[]> langTable = new HashMap<String, String[]>();
	
	class StatBar extends JFormattedTextField {
		public String stat;
		public StatBar(String statItem, NumberFormatter nf) {
			super(nf);
			stat = statItem;
		}
	}
	
	class StatPanel extends JPanel {
		String stat;
		CharacterEditor actionListener;
		public StatPanel(String statItem, CharacterEditor aL)
		{
			stat = statItem;
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			JLabel statLabel = new JLabel(statItem);
			
			statLabel.setText(langTable.get(statItem)[MainMenu.languageCh]);
			
			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
			
			StatBar statField = new StatBar(statItem, formatter);
			statField.setText("5");
			
			this.add(statLabel);
			this.add(statField);
			statField.addActionListener(aL);
			actionListener = aL;
		}
	}
	
	public static void setLanguageTable()
	{
		String[] healthTable = {"Health", "체력", "Ζωή"};
		langTable.put("Health", healthTable);
		String[] staminaTable = {"Stamina", "지구력", "καρτερία"};
		langTable.put("Stamina", staminaTable);
		String[] strengthTable = {"Strength", "근력", "ἰσχῡς"};
		langTable.put("Strength", strengthTable);
		String[] agiTable = {"Agility", "민첩성", "ἐλαφρότης"};
		langTable.put("Agility", agiTable);
		String[] handiTable = {"Handiness", "손재주", "τεχνή"};
		langTable.put("Handiness", handiTable);
		String[] dexTable = {"Dexterity", "손놀림", "δεξιότης"};
		langTable.put("Dexterity", dexTable);
		String[] marksTable = {"Marksmanship", "사격술", "τοξική"};
		langTable.put("Marksmanship", marksTable);
		String[] constTable = {"Constitution", "체격", "σῶμα"};
		langTable.put("Constitution", constTable);
	}
	
	public CharacterEditor(Contestant cha)
	{
		if(!init)
		{
			setLanguageTable();
			init = true;
		}
		character = cha;
		this.setSize(640,640);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel bigPanel = new JPanel();
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.PAGE_AXIS));
		
		JPanel basicStats = new JPanel(new GridLayout(0,2,10,10));
		//Create the bunch of basic stats
		statPanels = new StatPanel[8];
		statPanels[0] = new StatPanel("Health", this);
		statPanels[1] = new StatPanel("Stamina", this);
		statPanels[2] = new StatPanel("Strength", this);
		statPanels[3] = new StatPanel("Agility", this);
		statPanels[4] = new StatPanel("Handiness", this);
		statPanels[5] = new StatPanel("Dexterity", this);
		statPanels[6] = new StatPanel("Marksmanship", this);
		statPanels[7] = new StatPanel("Constitution", this);
		
		//basicStats.add(healthPanel);
		for(int i = 0; i < 8; i++)
		{
			basicStats.add(statPanels[i]);
		}
		
		//Gender
		genders = new ButtonGroup();
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		nonbinary = new JRadioButton("Nonbinary");
		noGender = new JRadioButton("Ungendered");
		
		genders.add(male);
		genders.add(female);
		genders.add(nonbinary);
		genders.add(noGender);
		
		if(character.getGender() == 'm')
		{
			male.setSelected(true);
		}
		else if(character.getGender() == 'f')
		{
			female.setSelected(true);
		}
		else if(character.getGender() == 'n')
		{
			nonbinary.setSelected(true);
		}
		else if(character.getGender() == 'o')
		{
			noGender.setSelected(true);
		}
		
		
		//Create an extra for extraneous stats
		extraStats = new JTextArea();
		//Read data if character has data
		String extraData = "";
		for(Map.Entry<String, Integer> entry : character.getUnmodifiedStats().entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();

		    extraData += "stat-" + key + "=" + value + "\n";
		}
		for(Map.Entry<String, Boolean> entry : character.getBinaryTrait().entrySet()) {
		    String key = entry.getKey();
		    Boolean value = entry.getValue();

		    extraData += "boolt-" + key + "=" + value + "\n";
		}
		for(Map.Entry<String, Boolean> entry : character.getBinaryStats().entrySet()) {
		    String key = entry.getKey();
		    Boolean value = entry.getValue();

		    extraData += "bools-" + key + "=" + value + "\n";
		}
		for(Map.Entry<String, Integer> entry : character.getItems().entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();

		    extraData += "item-" + key + "=" + value + "\n";
		}
		extraStats.setText(extraData);
		//
		confirmStat = new JButton("Confirm");
		confirmStat.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		changeLanguage(MainMenu.languageCh);
		
		bigPanel.add(basicStats);
		bigPanel.add(male);
		bigPanel.add(female);
		bigPanel.add(nonbinary);
		bigPanel.add(noGender);
		bigPanel.add(extraStats);
		bigPanel.add(confirmStat);
		bigPanel.add(cancel);
		JScrollPane scrollPane = new JScrollPane(bigPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
		
		this.setVisible(true);
	}
	
	public void changeLanguage(int lang)
	{
		switch(lang)
		{
		default:
		case 0:
			male.setText("Male");
			female.setText("Female");
			nonbinary.setText("Nonbinary");
			noGender.setText("Ungendered");
			confirmStat.setText("Confirm");
			cancel.setText("Cancel");
		break;
		case 1:
			male.setText("남성");
			female.setText("여성");
			nonbinary.setText("비이분법적 성별");
			noGender.setText("무성");
			confirmStat.setText("확인");
			cancel.setText("취소");
		break;
		case 2:
			male.setText("ἀνήρ");
			female.setText("γυνή");
			nonbinary.setText("μη-δυαδικός");
			noGender.setText("μηφύλος");
			confirmStat.setText("βεβαιοῠν");
			cancel.setText("λῡειν");
		break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof StatBar)
		{
			character.setStat(((StatBar)e.getSource()).stat, ((StatBar)e.getSource()).getText());
		}
		else if(e.getSource() == male)
		{
			character.setGender('m');
		}
		else if(e.getSource() == female)
		{
			character.setGender('f');
		}
		else if(e.getSource() == nonbinary)
		{
			character.setGender('n');
		}
		else if(e.getSource() == noGender)
		{
			character.setGender('o');
		}
		else if(e.getSource() == confirmStat)
		{
			setStats();
			this.dispose();
		}
		else if(e.getSource() == cancel)
		{
			this.dispose();
		}
	}
	
	public void setStats() {
		for(int i = 0; i < 8; i++)
		{
			character.setStat(statPanels[i].stat, ((StatBar)statPanels[i].getComponents()[1]).getText());
		}
		Scanner miscStat = new Scanner(extraStats.getText());
		while(miscStat.hasNextLine())
		{
			String newStat = miscStat.nextLine();
			if(newStat.substring(0, newStat.indexOf("-")).equals("stat")) {
				character.setStat(newStat.substring(newStat.indexOf("-")+1, newStat.indexOf("=")), newStat.substring(newStat.indexOf("=")+1));
			}
			if(newStat.substring(0, newStat.indexOf("-")).equals("item")) {
				character.setItem(newStat.substring(newStat.indexOf("-")+1, newStat.indexOf("=")), newStat.substring(newStat.indexOf("=")+1));
			}
			if(newStat.substring(0, newStat.indexOf("-")).equals("boolt")) {
				character.setBinaryTrait(newStat.substring(newStat.indexOf("-")+1, newStat.indexOf("=")), Boolean.parseBoolean(newStat.substring(newStat.indexOf("=")+1)));
			}
			if(newStat.substring(0, newStat.indexOf("-")).equals("bools")) {
				character.setBinaryStat(newStat.substring(newStat.indexOf("-")+1, newStat.indexOf("=")), Boolean.parseBoolean(newStat.substring(newStat.indexOf("=")+1)));
			}
		}
	}
}
