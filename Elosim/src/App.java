public class App {
    public static void main(String[] args) throws Exception {
        final int playerCount = 150000;
        final int gameCount = 10000000;

        GameSimulator sim = new GameSimulator(playerCount, gameCount);
        Player[] players = sim.simGames();

        // samples the first 10 players
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.round(players[i].getElo()) + " " + Math.round(players[i].getSkill()) + " "
                    + Math.round(players[i].getEloUpdates()));
        }

        DataAnalyzer dataanal = new DataAnalyzer(players);
        dataanal.printAverageError();
        dataanal.printStandardDevations();
    }
}
