package lin.swingy.model.character;

public class Director {

    private static HeroBuilder buildNew(String name) {
        HeroBuilder builder = new HeroBuilder();
        builder.setName(name);
        builder.setLevel(0);
        builder.setExperience(0);
        return builder;
    }

    public static Hero createWarrior(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(30);
        builder.setDefense(20);
        builder.setHitPoints(100);
        builder.setHeroClass("Warrior");
        return builder.getHero();
    }

    public static Hero createShaman(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(35);
        builder.setDefense(20);
        builder.setHitPoints(90);
        builder.setHeroClass("Shaman");
        return builder.getHero();
    }

    public static Hero createPriest(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(25);
        builder.setDefense(25);
        builder.setHitPoints(100);
        builder.setHeroClass("Priest");
        return builder.getHero();
    }

    public static Hero createPaladin(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(10);
        builder.setDefense(30);
        builder.setHitPoints(120);
        builder.setHeroClass("Paladin");
        return builder.getHero();
    }

    public static Hero createMage(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(45);
        builder.setDefense(15);
        builder.setHitPoints(80);
        builder.setHeroClass("Mage");
        return builder.getHero();
    }

    public static Hero createRanger(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(25);
        builder.setDefense(20);
        builder.setHitPoints(110);
        builder.setHeroClass("Ranger");
        return builder.getHero();
    }
}