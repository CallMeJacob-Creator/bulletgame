package bulletgame;

import java.util.Scanner;

public class InventoryGUI {

    //Scanner Setup
    Scanner invInput = new Scanner(System.in);

    //Passed Variables
    int health;
    int maxHealth;
    int gold;
    int knightSlash;
    int wizardDamage;
    int wizardHeal;
    int baseDamage;
    String type;

    String exitInv;

    boolean inInventory = false;

    //Constructor
    public InventoryGUI(int health, int maxHealth, int gold, int knightSlash, int wizardDamage, int wizardHeal, String type, int baseDamage) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.gold = gold;
        this.knightSlash = knightSlash;
        this.wizardDamage = wizardDamage;
        this.wizardHeal = wizardHeal;
        this.type = type;
        this.baseDamage = baseDamage;
    }

    public void UpdateInventory(int health, int maxHealth, int gold, int knightSlash, int wizardDamage, int wizardHeal, String type, int baseDamage) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.gold = gold;
        this.knightSlash = knightSlash;
        this.wizardDamage = wizardDamage;
        this.wizardHeal = wizardHeal;
        this.type = type;
        this.baseDamage = baseDamage;

        InventoryScreen();
    }

    //Inventory Screen Logic
    public void InventoryScreen() {

        inInventory = true;

        System.out.println();
        System.out.println("--Inventory--");
        System.out.println();
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Attack Damage: " + baseDamage);
        System.out.println("Gold: " + gold);
        if (type.equalsIgnoreCase("Knight")) {
            System.out.println("Sword Slash: " + knightSlash);
        } else if (type.equalsIgnoreCase("Wizard")) {
            System.out.println("Healing Spells: " + wizardHeal);
            System.out.println("Fireballs: " + wizardDamage);
        }
        System.out.println();
        System.out.println("Type 'exit' to return");

        //Exit Inventory
        while (inInventory) {
            exitInv = invInput.nextLine();
            if (exitInv.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting Inventory");
                break;
            } else {
                System.out.println("Try Again");
            }
        }

    }
}
