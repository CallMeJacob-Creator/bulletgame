package bulletgame;

import java.util.Scanner;

public class CharacterCreator {

    //Scanner Setup
    Scanner input = new Scanner(System.in);


    String playerName;
    String characterType;

    //Character Creator
    public void createCharacter() {
        System.out.println("What is your name?");
        playerName = input.nextLine();
        System.out.println();

        System.out.println("What is your character type?");
        while (true) {
            System.out.println("Knight or Wizard");

            characterType = input.nextLine();

            if (characterType.equalsIgnoreCase("Knight") || characterType.equalsIgnoreCase("Wizard")) {
                break;
            } else {
                System.out.println("Invalid character type, please try again");
            }
        }
    }

    //Return Variables
    public String getPlayerName() {
        return playerName;
    }

    public String getCharacterType() {
        return characterType;
    }
}
