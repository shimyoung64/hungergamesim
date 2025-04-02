package hungergamesim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ProfileSaver {
	ArrayList<Contestant> contestants = new ArrayList<Contestant>();
	
	public ProfileSaver(ArrayList<Contestant> characters)
	{
		contestants = characters;
	}
	
	public void saveCharacters()
	{
		//File goja = new File("data/characterData.txt");
		try {
			FileWriter shimyoung = new FileWriter("data/characterData.txt");
			for(Contestant contestant : contestants)
			{
				shimyoung.write(contestant.getName() + "\n");
				shimyoung.write(contestant.getImagePath() + "\n");
				shimyoung.write(contestant.getGender() + "\n");
				shimyoung.write("stat-health=" + contestant.getMaxHealth() + "\n");
				shimyoung.write("stat-stamina=" + contestant.getMaxStamina() + "\n");
				//Start with int stats
				for(Map.Entry<String, Integer> entry : contestant.getUnmodifiedStats().entrySet()) {
				    String key = entry.getKey();
				    Integer value = entry.getValue();

				    shimyoung.write("stat-" + key + "=" + value + "\n");
				}
				for(Map.Entry<String, Boolean> entry : contestant.getBinaryTrait().entrySet()) {
				    String key = entry.getKey();
				    Boolean value = entry.getValue();

				    shimyoung.write("boolt-" + key + "=" + value + "\n");
				}
				for(Map.Entry<String, Boolean> entry : contestant.getBinaryStats().entrySet()) {
				    String key = entry.getKey();
				    Boolean value = entry.getValue();

				    shimyoung.write("bools-" + key + "=" + value + "\n");
				}
				for(Map.Entry<String, Integer> entry : contestant.getItems().entrySet()) {
				    String key = entry.getKey();
				    Integer value = entry.getValue();

				    shimyoung.write("item-" + key + "=" + value + "\n");
				}
				shimyoung.write("end\n");
			}
			shimyoung.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveLocations()
	{
		
	}
}
