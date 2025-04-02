package hungergamesim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ProfileLoader {
	MainMenu instance;
	
	public ProfileLoader(MainMenu parent)
	{
		instance = parent;
	}
	public void loadCharacters()
	{
		instance.clearCharacters();
		File characterData = new File("data/characterData.txt");
		try {
			Scanner myReader = new Scanner(characterData);
			Contestant character = new Contestant();
			int mode = 0;
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				if(line.equals("end"))
				{
					instance.addCharacter(character);
					mode = 0;
				}
				else
				{
					switch(mode)
					{
						case 0:
							character.setName(line);
							mode = 1;
						break;
						case 1:
							character.setImagePath(line);
							mode = 2;
						break;
						case 2:
							character.setGender(line.charAt(0));
							mode = 3;
						break;
						case 3:
							if(line.substring(0, line.indexOf("-")).equals("stat")) {
								character.setStat(line.substring(line.indexOf("-")+1, line.indexOf("=")), line.substring(line.indexOf("=")+1));
							}
							if(line.substring(0, line.indexOf("-")).equals("item")) {
								character.setItem(line.substring(line.indexOf("-")+1, line.indexOf("=")), line.substring(line.indexOf("=")+1));
							}
							if(line.substring(0, line.indexOf("-")).equals("boolt")) {
								character.setBinaryTrait(line.substring(line.indexOf("-")+1, line.indexOf("=")), Boolean.parseBoolean(line.substring(line.indexOf("=")+1)));
							}
							if(line.substring(0, line.indexOf("-")).equals("bools")) {
								character.setBinaryStat(line.substring(line.indexOf("-")+1, line.indexOf("=")), Boolean.parseBoolean(line.substring(line.indexOf("=")+1)));
							}
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadMap()
	{
	}
}
