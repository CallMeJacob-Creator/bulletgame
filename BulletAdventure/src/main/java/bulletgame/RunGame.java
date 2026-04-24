package bulletgame;

import java.util.Scanner;

public class RunGame {

    //World
    int currentRoom = 0;
    int totalRooms = (int)(Math.random() * 101);

    //Stats
    int health;
    int baseDamage;
    String weapon;
    int knightSlash;
    int wizardDamage;
    int wizardHeal;
    int gold = 0;

    int maxHealth = 0;

    String type;

    //Scanner
    Scanner input = new Scanner(System.in);



    public void runGame() {

        CharacterCreator creator = new CharacterCreator();

        creator.createCharacter();

        String name = creator.getPlayerName();
        type = creator.getCharacterType();

        System.out.println("Hello " + name + " the " + type);

        //Setting Player Stats
        if (type.equalsIgnoreCase("Wizard")) {
            health = 50;
            baseDamage = 30;
            weapon = "Staff";
            wizardDamage = 2;
            wizardHeal = 3;

        } else if (type.equalsIgnoreCase("Knight")) {
            health = 80;
            baseDamage = 50;
            weapon = "Knight's Sword";
            knightSlash = 1;
        }

        //Display Stats
        System.out.println();
        System.out.println("You have " + health + " health and you do " + baseDamage + " damage with your " + weapon);
        System.out.println();
        if (type.equalsIgnoreCase("Wizard")) {
            System.out.println("You have got " + wizardDamage + " damage spells that deal 200 points of damage, it's keyword is 'Fireball'");
            System.out.println("You have also got " + wizardHeal + " healing spells that restore full health, it's keyword is 'Healing Spell'");
        } else if (type.equalsIgnoreCase("Knight")) {
            System.out.println("You have got " + knightSlash + " sword slashing abilities that deal 300 points of damage, it's keyword is 'Sword Slash'");
        }

        //Rule Explaining
        System.out.println();
        System.out.println("Your objective is to escape the Castle of Infinite Rooms, somewhere is a portal out!");
        System.out.println("When using abilities, you can use attack abilities when it's your turn in battle");
        System.out.println("The Wizard can use his heal spell during battle or when the room is cleared");
        System.out.println("Every time you enter a room you may encounter a monster, which you must defeat in order ");
        System.out.println("to move on. When the room is cleared you can check for treasure but be careful as some rooms");
        System.out.println("are trapped! When ready you can move on to keep exploring but the way");
        System.out.println("back will be sealed off once you move on.");


        //Starting Game
        String gameGo;

        System.out.println();

        int noCounter = 0;
        boolean finished = false;

        while (!finished) {
            System.out.println();
            System.out.println("Ready to walk through the first door? Y/N?");
            gameGo = input.nextLine();

            if (gameGo.equalsIgnoreCase("y")) {
                System.out.println();
                System.out.println("You are going to walk through the first door");
                Rooms();
            } else if (noCounter > 20) {
                System.out.println();
                System.out.println("You decide to do nothing at all, and out of nowhere a portal opens in the floor and you fall through!?");
                System.out.println("Congratulations! You escaped by dumb luck");
                System.exit(0);
            } else if (gameGo.equalsIgnoreCase("n")) {
                System.out.println();
                System.out.println("You sit in the room waiting for a while");
                noCounter++;
            } else {
                System.out.println();
                System.out.println("That is not yes or no. Try Again.");
            }
        }


    }




    public void Rooms() {
        RoomGenerator room = new RoomGenerator(currentRoom, totalRooms);
        InventoryGUI InventoryGUI = new InventoryGUI(health, maxHealth, gold, knightSlash, wizardDamage, wizardHeal, type, baseDamage);
        ShopGUI shopGUI = new ShopGUI(health, maxHealth, gold, knightSlash, wizardDamage, wizardHeal, baseDamage);

        //Console Commands
        Scanner console = new Scanner(System.in);
        String consoleHowLong;
        String consoleTrapped;
        String consoleGoldCount;
        String consoleHasMonsters;



        while (currentRoom < totalRooms) {



            System.out.println();

            //Checking Max Health
            if (maxHealth < health) {
                maxHealth = health;
            }

            //Updates Room Counter
            currentRoom++;
            System.out.println("You are in room " + currentRoom);

            //Room Stats
            room.roomStats();
            boolean trapped = room.getTrapped();
            int roomGold = room.getGold();
            boolean hasMonsters = room.getHasMonster();
            String monsterType = room.getMonsterType();
            int monsterHealth = room.getMonsterHealth();
            int monsterDamage = room.getMonsterDamage();
            int monsterLoot = room.getMonsterLoot();

            boolean hasTrader = room.getHasTrader();

            int fleeChance = room.getFleeChance();

            int criticalChance;
            int criticalHit = baseDamage * 2;




            //Fighting/Fleeing Logic
            System.out.println();

            if (hasMonsters) {
                String encounterAction;


                System.out.println("Oh No! There is a " + monsterType + " in this room!");

                while (hasMonsters) {

                    System.out.println("Do you want to Fight or Flee? Your flee chance is " + fleeChance + "%");
                    encounterAction = input.nextLine();
                    //Flee
                    if (encounterAction.equalsIgnoreCase("Flee")) {
                        int runAway = (int) (Math.random() * 100);
                        if (runAway <= fleeChance) {
                            System.out.println("You failed to flee!");
                            System.out.println("The monster attacks you and you took " + monsterDamage + " damage.");
                            health = health - monsterDamage;
                            if (health <= 0) {
                                System.out.println("You have died fighting.");
                                System.out.println("You had " + (totalRooms - currentRoom) + " rooms left.");
                                System.out.println("You had " + gold + " gold left.");
                                System.exit(0);
                            } else {
                                System.out.println("Your health is at " + health);
                                System.out.println("Your flee chance dropped 10%");
                                baseDamage = baseDamage - 10;
                                System.out.println("Your attack points dropped by 10 and is now " + baseDamage);
                                fleeChance = fleeChance - 10;

                            }
                        } else {
                            System.out.println("You successfully fled!");
                            hasMonsters = false;
                            encounterAction = "null";
                        }
                    }

                    //Abilities
                    //Knight
                    if (encounterAction.equalsIgnoreCase("Sword Slash") && type.equalsIgnoreCase("Knight")
                    && knightSlash > 0) {
                        System.out.println("You use your ability and deal 300 damage to the monster!");
                        monsterHealth -= 300;
                        if (monsterHealth <= 0) {
                            System.out.println("The monster perishes after your attack!");
                            System.out.println("The monster dropped " + monsterLoot + " gold.");
                            gold += monsterLoot;
                            hasMonsters = false;
                        }
                    } else if (encounterAction.equalsIgnoreCase("Sword Slash") && type.equalsIgnoreCase("Knight")
                            && knightSlash <= 0) {
                        System.out.println("You're all out of Sword Abilities!");
                    }
                    //Wizard
                    //Damage
                    if (encounterAction.equalsIgnoreCase("Fireball") && type.equalsIgnoreCase("Wizard")
                    && wizardDamage > 0) {
                        System.out.println("You use your ability and deal 200 damage to the monster!");
                        monsterHealth -= 200;
                        if (monsterHealth <= 0) {
                            System.out.println("The monster perishes after your attack!");
                            System.out.println("The monster dropped " + monsterLoot + " gold.");
                            gold += monsterLoot;
                            hasMonsters = false;
                        }
                    } else if (encounterAction.equalsIgnoreCase("Fireball") && type.equalsIgnoreCase("Wizard")
                            && wizardDamage <= 0) {
                        System.out.println("You're all out of Fireballs!");
                    }
                    //Heal
                    if (encounterAction.equalsIgnoreCase("Healing Potion") && type.equalsIgnoreCase("Wizard")
                            && wizardHeal >0) {
                        System.out.println("You use your ability and heal yourself to " + maxHealth + " health!");
                        health = maxHealth;
                    } else if (encounterAction.equalsIgnoreCase("Healing Potion") && type.equalsIgnoreCase("Wizard")
                            && wizardHeal <= 0) {
                        System.out.println("You're all out of Healing Potions!");
                    }

                    //Fight
                    if (encounterAction.equalsIgnoreCase("Fight")) {
                        criticalChance = (int)(Math.random() * 100);
                        if (criticalChance <= 20) {
                            System.out.println("You scored a Critical Hit! You dealt " + criticalHit + " damage!");
                            monsterHealth = monsterHealth - criticalHit;
                            fleeChance = fleeChance + 20;
                            if (monsterHealth <= 0) {
                                System.out.println("The monster was slain and dropped " + monsterLoot + " gold.");
                                gold = gold + monsterLoot;
                                hasMonsters = false;
                            }
                        } else {
                            System.out.println("You dealt " + baseDamage + " damage.");
                            monsterHealth = monsterHealth - baseDamage;
                            fleeChance = fleeChance + 10;
                            if (monsterHealth <= 0) {
                                System.out.println("The monster was slain and dropped " + monsterLoot + " gold.");
                                gold = gold + monsterLoot;
                                hasMonsters = false;
                            }
                        }
                    }

                    //Monster Attacks
                    if (hasMonsters) {
                        System.out.println("The monster attacks you");
                        health = health - monsterDamage;
                        if (health < 0) {
                            System.out.println("You have died fighting.");
                            System.out.println("You had " + (totalRooms - currentRoom) + " rooms left.");
                            System.out.println("You had " + gold + " gold left.");
                            System.exit(0);
                        } else {
                            System.out.println("Your health is now " + health);
                            baseDamage = baseDamage - 10;
                            System.out.println("Your attack points dropped by 10 and is now " + baseDamage);
                        }
                    }
                }
            }

            //Room is cleared
            String roomClearAction;


            System.out.println();
            //Has Trader & Is Wizard
            if (hasTrader && type.equalsIgnoreCase("Wizard")) {
                System.out.println("The room is clear & has a trader. What do you want to do?");
                if (trapped) {
                    System.out.println("~You sense this room may be trapped~");
                }
                System.out.println("[Search][Move On][Open Inventory][Open Shop]");
            }
            //Has Trader & Is Knight
            else if (hasTrader) {
                System.out.println("The room is clear & has a trader. What do you want to do?");
                System.out.println("[Search][Move On][Open Inventory][Open Shop]");
            }
            //Is Wizard
            else if (type.equalsIgnoreCase("Wizard")){
                System.out.println("The room is clear. What do you want to do?");
                if (trapped) {
                    System.out.println("~You sense this room may be trapped~");
                }
                System.out.println("[Search][Move On][Open Inventory]");
            }
            //Is Knight
            else {
                System.out.println("The room is clear. What do you want to do?");
                System.out.println("[Search][Move On][Open Inventory]");
            }

            roomClearAction = input.nextLine();

            //Wizard Heal
            if (roomClearAction.equalsIgnoreCase("Healing Potion") && type.equalsIgnoreCase("Wizard")
                    && wizardHeal > 0) {
                System.out.println("You use your ability and heal yourself to " + maxHealth + " health!");
                health = maxHealth;
            } else if (roomClearAction.equalsIgnoreCase("Healing Potion") && type.equalsIgnoreCase("Wizard")
                    && wizardHeal <= 0) {
                System.out.println("You are all out of Healing Potions");
                health = maxHealth;
            }

            //Search Room
            if (roomClearAction.equalsIgnoreCase("search")) {
                if (trapped) {
                    System.out.println("This room was trapped! You lost 10 health!");
                    health = health - 10;
                    if (health < 0) {
                        System.out.println("You have died.");
                        System.out.println("You had " + (totalRooms - currentRoom) + " rooms left.");
                        System.out.println("You had " + gold + " gold left.");
                        System.exit(0);
                    }
                    System.out.println("Your health is at " + health);
                } else {
                    System.out.println("You found " + roomGold + " gold!");
                    gold = gold + roomGold;
                }

            }
            //Move On
            else if (roomClearAction.equalsIgnoreCase("Move On")) {

            }
            //Open Inventory
            else if (roomClearAction.equalsIgnoreCase("Open Inventory")) {
                InventoryGUI.UpdateInventory(health, maxHealth, gold, knightSlash, wizardDamage, wizardHeal, type, baseDamage);
            }
            //Open Shop
            else if (roomClearAction.equalsIgnoreCase("Open Shop") && hasTrader) {
                shopGUI.UpdateShop(health, maxHealth, gold, knightSlash, wizardDamage, wizardHeal, type, baseDamage);
                health = shopGUI.getHealth();
                maxHealth = shopGUI.getMaxHealth();
                gold = shopGUI.getGold();
                knightSlash = shopGUI.getKnightSlash();
                wizardDamage = shopGUI.getWizardDamage();
                wizardHeal = shopGUI.getWizardHeal();
                baseDamage = shopGUI.getBaseDamage();
            }


//            //Console Commands
//            consoleHowLong = input.nextLine();
//            if (consoleHowLong.equalsIgnoreCase("How_long")) {
//                System.out.println(totalRooms-currentRoom);
//            }
//
//            consoleTrapped = input.nextLine();
//            if (consoleTrapped.equalsIgnoreCase("Is_it_trapped")) {
//                System.out.println(trapped);
//            }
//
//            consoleGoldCount = input.nextLine();
//            if (consoleGoldCount.equalsIgnoreCase("How_much_gold")) {
//                System.out.println(gold);
//            }
//
//            consoleHasMonsters = input.nextLine();
//            if (consoleHasMonsters.equalsIgnoreCase("Are_there_monsters")) {
//                System.out.println(hasMonsters);
//                if (hasMonsters == true) {
//                    System.out.println("It is a " + monsterType);
//                }
//            }




        }

        System.out.println("You open the final door and before you is the portal out! You step through it and escape.");
        System.out.println("Total Gold: " + gold);
    }
}
