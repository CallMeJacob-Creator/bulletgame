package bulletgame;

public class MonsterGenerator {

    String monsterType;
    int monsterHealth;
    int monsterDamage;
    int monsterLoot;

    int monsterChance;
    int fleeChance;

    String monsterGoblin = "Goblin";
    String monsterGoul = "Goul";
    String monsterDragon = "Dragon";
    String monsterKing = "Monster King";

    public void monsterGenerator() {
        monsterChance = (int)(Math.random()*100);

        if (monsterChance <= 55) {
            monsterType = monsterGoblin;
            monsterHealth = 20;
            monsterDamage = 10;
            fleeChance = 50;
            monsterLoot = (int)(Math.random()*20);
        } else if (monsterChance > 55 && monsterChance <= 85) {
            monsterType = monsterGoul;
            monsterHealth = 60;
            monsterDamage = 25;
            fleeChance = 25;
            monsterLoot = (int)(Math.random()*40);
        } else if (monsterChance > 85 && monsterChance < 95) {
            monsterType = monsterDragon;
            monsterHealth = 165;
            monsterDamage = 45;
            fleeChance = 10;
            monsterLoot = (int)(Math.random()*80);
        } else if (monsterChance >= 95 && monsterChance <= 100) {
            monsterType = monsterKing;
            monsterHealth = 300;
            monsterDamage = 60;
            fleeChance = 3;
            monsterLoot = (int)(Math.random()*160);
        }
    }

    public String getMonster() {
        return monsterType;
    }
    public int getMonsterHealth() {
        return monsterHealth;
    }
    public int getMonsterDamage() {
        return monsterDamage;
    }
    public int getFleeChance() {
        return fleeChance;
    }
    public int getMonsterLoot() {
        return monsterLoot;
    }
}
