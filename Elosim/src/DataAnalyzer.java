public class DataAnalyzer {
    private Player[] data;

    public DataAnalyzer(Player[] data) {
        this.data = data;
    }

    public void printAverageError() {
        double sumError = 0;
        for (Player p : data) {
            sumError += Math.abs(p.getElo() - p.getSkill());
        }
        System.out.println("Average error = " + sumError / data.length);
    }

    public double getMeanElo() {
        double total = 0;
        for (Player p : data) {
            total += p.getElo();
        }
        return total / data.length;
    }

    public double getMeanSkill() {
        double total = 0;
        for (Player p : data) {
            total += p.getSkill();
        }
        return total / data.length;
    }

    public void printStandardDevations() {
        double sumElodiffs = 0;
        double sumSkilldiffs = 0;
        double meanElo = getMeanElo();
        double meanSkill = getMeanSkill();

        for (Player p : data) {
            sumElodiffs += Math.pow(p.getElo() - meanElo, 2);
            sumSkilldiffs += Math.pow(p.getSkill() - meanSkill, 2);
        }
        double stdElo = Math.sqrt(sumElodiffs / data.length);
        double stdSkill = Math.sqrt(sumSkilldiffs / data.length);

        System.out.println("Elo std: " + stdElo);
        System.out.println("Skill std: " + stdSkill);
    }
}
