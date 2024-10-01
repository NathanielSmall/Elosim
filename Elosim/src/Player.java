public class Player {
    private int id;
    private double skill;
    private double elo;
    private int eloUpdates;

    public Player(int id) {
        this.elo = 1000;
        this.id = id;
        this.skill = generateSkill();
    }

    private double generateSkill() {
        // box muller transform uesd to generate a normal distribution
        // centered around 1000, standard devation 500
        double phi = 2 * Math.PI * Math.random();
        double r = Math.sqrt(-2 * Math.log(Math.random()));
        return 1000 + (r * Math.cos(phi) * 500);
    }

    public double getSkill() {
        return skill;
    }

    public double getElo() {
        return elo;
    }

    public int getId() {
        return id;
    }

    public int getEloUpdates() {
        return eloUpdates;
    }

    public double changeElo(double change) {
        elo += change;
        eloUpdates += 1;
        return elo;
    }
}
