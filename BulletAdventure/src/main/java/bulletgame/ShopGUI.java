package bulletgame;

import java.util.Scanner;

public class ShopGUI {

    //Scanner
    Scanner input = new Scanner(System.in);

    //Passed Variables
    int health;
    int maxHealth;
    int gold;
    int knightSlash;
    int wizardDamage;
    int wizardHeal;
    int baseDamage;
    String type;

    String shopChoice;

    //Constructor
    public ShopGUI(int health, int maxHealth, int gold, int knightSlash, int wizardDamage, int wizardHeal, int baseDamage) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.gold = gold;
        this.knightSlash = knightSlash;
        this.wizardDamage = wizardDamage;
        this.wizardHeal = wizardHeal;
        this.baseDamage = baseDamage;
    }

    public void UpdateShop(int health, int maxHealth, int gold, int knightSlash, int wizardDamage, int wizardHeal, String type, int baseDamage) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.gold = gold;
        this.knightSlash = knightSlash;
        this.wizardDamage = wizardDamage;
        this.wizardHeal = wizardHeal;
        this.baseDamage = baseDamage;
        this.type = type;

        shopGUI();
    }

    public void shopGUI() {
        System.out.println();
        System.out.println("--Shop--");
        System.out.println();


        while (true) {

            System.out.println("Gold: " + gold);
            System.out.println();
            //Selection
            if (type.equalsIgnoreCase("Knight")) {
                System.out.println("[40] +1 'Sword Slash'");
                System.out.println("[30] 'Sharpen Sword' +20 Attack");
                System.out.println("[35] 'Forge Armor' +20 Health");
                System.out.println("[40] 'Restore' Max Health");
                System.out.println("'Exit' to leave the store");
            } else  if (type.equalsIgnoreCase("Wizard")) {
                System.out.println("[35] +1 'Healing Potion'");
                System.out.println("[50] +1 'Fireball'");
                System.out.println("[20] 'Enchant Staff' +10 Attack");
                System.out.println("[25] 'Refit Cloak' +10 Health");
                System.out.println("[40] 'Restore' Max Health");
                System.out.println("'Exit' to leave the store");
            }

            shopChoice = input.nextLine();

            //Exit
            if (shopChoice.equalsIgnoreCase("Exit")) {
                break;
            }

            //Health Restore
            else if (shopChoice.equalsIgnoreCase("Restore")) {
                if (gold >= 40) {
                    health = maxHealth;
                }
            }

            //Knight Options

            //KnightSlash
            else if (shopChoice.equalsIgnoreCase("Sword Slash")) {
                if (gold >= 40) {
                    knightSlash++;
                    gold -= 40;
                } else {
                    System.out.println("Not enough gold for Sword Slash");
                }
            }
            //Sharpen Sword
            else if (shopChoice.equalsIgnoreCase("Sharpen Sword")) {
                if (gold >= 30) {
                    baseDamage += 20;
                    gold -= 30;
                } else {
                    System.out.println("Not enough gold for Sharpen Sword");
                }
            }
            //Forge Armor
            else if (shopChoice.equalsIgnoreCase("Forge Armor")) {
                if (gold >= 35) {
                    maxHealth += 20;
                    gold -= 35;
                } else {
                    System.out.println("Not enough gold for Forge Armor");
                }
            }

            //Wizard Options

            //WizardHeal
            else if (shopChoice.equalsIgnoreCase("Healing Potion")) {
                if (gold >= 30) {
                    wizardHeal++;
                    gold -= 30;
                } else {
                    System.out.println("Not enough gold for Healing Potion");
                }
            }
            //WizardDamage
            else if (shopChoice.equalsIgnoreCase("Fireball")) {
                if (gold >= 50) {
                    wizardDamage++;
                    gold -= 50;
                } else {
                    System.out.println("Not enough gold for Fireball");
                }
            }
            //Enchant Staff
            else if (shopChoice.equalsIgnoreCase("Enchant Staff")) {
                if (gold >= 20) {
                    baseDamage += 10;
                    gold -= 20;
                } else {
                    System.out.println("Not enough gold for Enchant Staff");
                }
            }
            //Refit Cloak
            else if (shopChoice.equalsIgnoreCase("Refit Cloak")) {
                if (gold >= 25) {
                    health += 10;
                    gold -= 25;
                } else {
                    System.out.println("Not enough gold for Forge Armor");
                }
            }
        }
    }

    /*
    int health;
    int maxHealth;
    int gold;
    int knightSlash;
    int wizardDamage;
    int wizardHeal;
    int baseDamage;
    String type;
    */

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold;
    }

    public int getKnightSlash() {
        return knightSlash;
    }

    public int getWizardDamage() {
        return wizardDamage;
    }

    public int getWizardHeal() {
        return wizardHeal;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
