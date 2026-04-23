package bulletgame;

import java.util.Scanner;

public class StartGame {



    public static void main(String[] args) {
        String start;

        RunGame runGame = new RunGame();
        Scanner in = new Scanner(System.in);

        System.out.println("Would you like to play? Y/N");
        start = in.nextLine();

        if(start.equalsIgnoreCase("Y")) {
            runGame.runGame();
        } else if(start.equalsIgnoreCase("N")) {
            System.out.println("Closing Game...");
            System.exit(0);
        }
    }
}
