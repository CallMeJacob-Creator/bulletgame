package bulletgame;

public class RoomGenerator {

    //Room Count Variables
    int currentRoom;
    int totalRooms;

    //Trapped Variables
    boolean trapped;
    int trapChance;

    //Treasure Variables
    boolean goldTreasure = false;
    int goldChance;
    int gold;

    //Monsters Variables
    MonsterGenerator monster = new MonsterGenerator();
    boolean hasMonsters;
    int monsterChance;
    String monsterType;
    int monsterHealth;
    int monsterDamage;
    int fleeChance;
    int monsterLoot;

    //Trader Variables
    boolean hasTrader;
    int traderChance;

    //Constructor
    public RoomGenerator(int currentRoom, int totalRooms) {
        this.currentRoom = currentRoom;
        this.totalRooms = totalRooms;
    }

    //Room Generates
    public void roomStats() {

        //Trapped?
        trapChance = (int)(Math.random()*10);
        if (trapChance == 5 || trapChance == 2) {
            trapped = true;
        } else {
            trapped = false;
        }

        //Treasure
        if (trapped) {
            goldChance = 0;
        } else {
            goldChance = (int)(Math.random() * 6);
            if (goldChance <= 2) {
                goldTreasure = true;
                gold = (int)(Math.random() * 50);
            }
        }

        //Monsters
        monsterChance = (int)(Math.random()* 100);
        if (monsterChance <= 60) {
            hasMonsters = true;
        } else {
            hasMonsters = false;
        }
        if (hasMonsters) {
            monster.monsterGenerator();
            monsterType = monster.getMonster();
            monsterHealth = monster.getMonsterHealth();
            monsterDamage = monster.getMonsterDamage();
            fleeChance = monster.getFleeChance();
            monsterLoot = monster.getMonsterLoot();
        }

        //Trader

        if (!hasMonsters) {
            traderChance = (int) (Math.random() * 100);
            if (traderChance <= 35) {
                hasTrader = true;
            } else {
                hasTrader = false;
            }
        }
    }

    //Returned Variables

    //Trapped?
    public boolean getTrapped() {
        return trapped;
    }

    //Treasure
    public int getGold() {
        return gold;
    }

    //Flee Chance
    public boolean getHasMonster() {
        return hasMonsters;
    }

    public int getFleeChance() {
        return fleeChance;
    }

    //Monster Stats
    public String getMonsterType(){
        return monsterType;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public int getMonsterDamage() {
        return monsterDamage;
    }

    public int getMonsterLoot() {
        return monsterLoot;
    }

    public boolean getHasTrader() {
        return hasTrader;
    }

}
