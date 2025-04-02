package hungergamesim;

import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;



/**
 * Character
 * @author Mathetes tou Megalou Alexandrou
 *
 */
public class Contestant {
	//Attributes
	//Certain encounters require certain stats, and stats can determine combat situations.
	//For example, in the mountains, a high strength character with the initiative can roll a boulder, while high marksmanship allows for snipes.
	//Stealth and initiative is also dependent on stats: Craftiness is the most important factor, and agility next. Higher constitution reduces stealth because big bois are harder to hide.
	//However, the actual effects of the stats will be elsewhere
	//int strength, agility, stamina, craftsmanship, craftiness, dexterity, marksmanship, constitution;
	
	//Health and Total War style stamina
	int health, stamina;
	int maxhealth, maxstamina;
	boolean alive;
	String name;
	String imagePath;
	char gender;
	boolean noBaseStats; //Used to randomize stats
	ImageIcon imageIcon;
	
	HashMap<String, Boolean> boolTraits = new HashMap<String, Boolean>();
	HashMap<String, Boolean> boolStats = new HashMap<String, Boolean>();
	HashMap<String, Integer> items = new HashMap<String, Integer>();
	HashMap<String, Integer> initStats = new HashMap<String, Integer>();
	HashMap<String, Integer> modifiers = new HashMap<String, Integer>();
	
	public Contestant()
	{
		alive = true;
		gender = 'm';
		health = 10;
		stamina = 10;
		noBaseStats = true;
	}
	
	public Contestant(String n, char g)
	{
		alive = true;
		gender = g;
		name = n;
		health = 10;
		stamina = 10;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean status) {
		alive = status;
	}
	
	public HashMap<String, Boolean> getBinaryTrait(){
		return boolTraits;
	}
	public HashMap<String, Boolean> getBinaryStats(){
		return boolStats;
	}
	public HashMap<String, Integer> getItems(){
		return items;
	}
	public HashMap<String, Integer> getUnmodifiedStats(){
		return initStats;
	}
	public HashMap<String, Integer> getStatModifiers(){
		return modifiers;
	}
	
	public boolean setBinaryTrait(String trait, boolean status){
		return boolTraits.put(trait, status);
	}
	public boolean setBinaryStat(String trait, boolean stat){
		return boolStats.put(trait, stat);
	}
	public Integer setItem(String itemType, String stat)
	{
		int am = Integer.parseInt(stat);
		return items.put(itemType, am);
	}
	
	//Set Stats
	public void setStat(String statType, String stat)
	{
		int am = Integer.parseInt(stat);
		//Debug
		//System.out.println(statType + ", " + stat);
		switch(statType)
		{
			//Health and Stamina are special
			case "Health": health = am; break;
			case "Stamina": stamina = am; break;
			default: initStats.put(statType, am); break;
		}
		noBaseStats = false;
	}
	
	public int getEffectiveStat(String stat)
	{
		if(initStats.get(stat) == null)
		{
			return 0;
		}
		if(modifiers.get(stat) == null)
		{
			return initStats.get(stat);
		}
		return initStats.get(stat) + modifiers.get(stat);
	}
	
	public String getImagePath()
	{
		return imagePath;
	}
	public void setImagePath(String ip)
	{
		imagePath = ip;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String onoma)
	{
		name = onoma;
	}
	
	public char getGender()
	{
		return gender;
	}
	public void setGender(char g)
	{
		gender = g;
	}
	
	public void randomizeStats()
	{
		//Basic stats
		if (noBaseStats)
		{
			Random rng = new Random();
			health = 5 + rng.nextInt(46);
			stamina = 5 + rng.nextInt(26);
			maxhealth = health;
			maxstamina = stamina;
			initStats.put("Strength", rng.nextInt(31));
			initStats.put("Agility", rng.nextInt(31));
			initStats.put("Handiness", rng.nextInt(31));
			initStats.put("Dexterity", rng.nextInt(31));
			initStats.put("Marksmanship", rng.nextInt(31));
			initStats.put("Constitution", rng.nextInt(31));
			noBaseStats = false;
		}
	}
	
	public int getHealth()
	{
		return health;
	}
	public int getStamina()
	{
		return stamina;
	}
	public int getMaxHealth()
	{
		return maxhealth;
	}
	public int getMaxStamina()
	{
		return maxstamina;
	}
	
	public void setHealth(int h)
	{
		health = h;
	}
	public void setHealth(int h, int mode)
	{
		health = h;
		switch(mode)
		{
			case 0:
				if(health > maxhealth)
				{
					health = maxhealth;
				}
			break;
			case 1:
				if(health > maxhealth)
				{
					maxhealth = health;
				}
			break;
		}
	}
	public void changeHealth(int dh)
	{
		health += dh;
	}
	public void changeHealth(int dh, int mode)
	{
		health += dh;
		switch(mode)
		{
			case 0:
				if(health > maxhealth)
				{
					health = maxhealth;
				}
			break;
			case 1:
				if(health > maxhealth)
				{
					maxhealth = health;
				}
			break;
		}
	}
	
	public void setStamina(int s)
	{
		stamina = s;
	}
	public void setStamina(int s, int mode)
	{
		stamina = s;
		switch(mode)
		{
			case 0:
				if(stamina > maxstamina)
				{
					stamina = maxstamina;
				}
			break;
			case 1:
				if(stamina > maxstamina)
				{
					maxstamina = stamina;
				}
			break;
		}
	}
	public void changeStamina(int ds)
	{
		stamina += ds;
	}
	public void changeStamina(int ds, int mode)
	{
		stamina += ds;
		switch(mode)
		{
			case 0:
				if(stamina > maxstamina)
				{
					stamina = maxstamina;
				}
			break;
			case 1:
				if(stamina > maxstamina)
				{
					maxstamina = stamina;
				}
			break;
		}
	}

	public void generateImage()
	{
		imageIcon = new ImageIcon(imagePath);
		imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(128,128,java.awt.Image.SCALE_SMOOTH));
	}

	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return imageIcon;
	}
}
