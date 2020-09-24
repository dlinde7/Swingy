package lin.swingy.model;

import lin.swingy.model.artifact.Armor;
import lin.swingy.model.artifact.Artifact;
import lin.swingy.model.artifact.Helm;
import lin.swingy.model.artifact.Weapon;
import lin.swingy.model.character.Character;
import lin.swingy.model.character.Hero;
import lin.swingy.model.character.Villain;
import lin.swingy.data.Point;

import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private static Game instance = null;

    private Hero hero;
    private Point heroCoord;
    private int mapSize;
    private boolean[][] map;

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void initGame(Hero hero) {
        this.hero = hero;
        generateMap();
        generateVillains();
        putHero();
    }

    private void generateMap() {
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    private void generateVillains() {
        int rand;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                rand = ThreadLocalRandom.current().nextInt(0, 111);
                if ((level + 1) * 10 >= rand)
                    map[i][j] = true;
            }
        }
    }

    public Villain generateVillain() {
        int attack = ThreadLocalRandom.current().nextInt(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
        int defense = ThreadLocalRandom.current().nextInt(hero.getDefense() - 20, hero.getDefense() + 2 + hero.getLevel());
        int hitPoints = ThreadLocalRandom.current().nextInt(hero.getHitPoints() - 50, hero.getHitPoints() + 20 + hero.getLevel());

        attack = attack < 0 ? -attack : attack;
        defense = defense < 0 ? -defense : defense;
        hitPoints = hitPoints < 0 ? -hitPoints : hitPoints;
        Artifact artifact = generateArtifact();

        return new Villain("Villain", attack, defense, hitPoints, artifact);
    }

    private Artifact generateArtifact() {
        int rand;
        if (hero.getArmor() == null && hero.getHelm() == null && hero.getWeapon() == null) {
            rand = ThreadLocalRandom.current().nextInt(0, 4);
        } else {
            rand = ThreadLocalRandom.current().nextInt(0, 8);
        }

        Artifact artifact = null;
        if (rand == 0) {
            if ("warrior".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Weapon("Sword", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
            } else if ("shaman".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Weapon("Skull", ThreadLocalRandom.current().nextInt(1, 4 * (hero.getLevel() + 1)));
            } else if ("priest".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Weapon("Holy water", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }else if ("paladin".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Weapon("Mace", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }else if ("mage".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Weapon("Staff", ThreadLocalRandom.current().nextInt(1, 5 * (hero.getLevel() + 1)));
            }else if ("ranger".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Weapon("Bow", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
            }
        } else if (rand == 1) {
            if ("warrior".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Helm("Steel Cap", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            } else if ("shaman".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Helm("Bull Head", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
            } else if ("priest".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Helm("Cowl", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }else if ("paladin".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Helm("Winged helm", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
            }else if ("mage".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Helm("Pointy hat", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }else if ("ranger".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Helm("Leather cap", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }
        } else if (rand == 2) {
            if ("warrior".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Armor("Chest plate", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
            } else if ("shaman".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Armor("Animal skin", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            } else if ("priest".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Armor("Holy robes", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }else if ("paladin".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Armor("Shield", ThreadLocalRandom.current().nextInt(1, 4 * (hero.getLevel() + 1)));
            }else if ("mage".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Armor("Robes", ThreadLocalRandom.current().nextInt(1, 2 * (hero.getLevel() + 1)));
            }else if ("ranger".equalsIgnoreCase(hero.getHeroClass())){
                artifact = new Armor("Bracers", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
            }
        }
        return artifact;
    }

    public int fightResult(Character villain) {
        int xp = villain.getAttack() + villain.getDefense() + villain.getHitPoints();
        int rand = ThreadLocalRandom.current().nextInt(0, 101);

        if (rand < 3){
            if (hero.getLevel() < 10){
                return xp;
            } else {
                return 0;
            }
        } else if (rand > 98){
            return -1;
        }

        if (hero.fight(villain)){
            if (hero.getLevel() < 10){
                return xp;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    
    }

    private void putHero() {
        heroCoord = new Point(mapSize / 2, mapSize / 2);
        map[heroCoord.getY()][heroCoord.getX()] = false;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Point getHeroCoord() {
        return heroCoord;
    }

    public void setHeroCoord(Point heroCoord) {
        this.heroCoord = heroCoord;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }
}