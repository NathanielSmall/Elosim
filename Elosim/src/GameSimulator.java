import java.util.Arrays;
import java.util.Collections;

public class GameSimulator {
    private static final int k = 25;

    private final Playerfactory pfac;
    private final int playerCount;
    private final int gameCount;

    private double mmRange = 100;
    private Player[] players;

    public GameSimulator(int playerCount, int gameCount) {
        pfac = new Playerfactory();
        this.playerCount = playerCount;
        this.gameCount = gameCount;
        players = pfac.createPlayers(playerCount);
    }

    public Player[] simGames() {
        for (int i = 0; i < gameCount; i++) {
            Player[] gamePlayers = selectPlayersForGame();
            resolveGame(gamePlayers);
        }
        return players;
    }

    public Player[] selectPlayersForGame() {
        mmRange = 100;
        Player first = players[(int) (Math.random() * playerCount)];
        double mmElo = first.getElo();

        Player[] playerList = new Player[10];
        playerList[0] = first;
        int playersfilled = 1;

        while (playersfilled < 10) {
            Player randPlayer = players[(int) (Math.random() * playerCount)];
            if (Math.abs(randPlayer.getElo() - mmElo) < mmRange) {
                playerList[playersfilled] = randPlayer;
                playersfilled += 1;
            } else {
                mmRange += 5;
            }
        }

        Collections.shuffle(Arrays.asList(playerList)); // first 5 are team a, last 5 are team b
        return playerList;
    }

    public void resolveGame(Player[] players) {
        double teamAElo = getAverageElos(0, 5, players);
        double teamBElo = getAverageElos(5, 10, players);
        double teamASkill = getAverageSkill(0, 5, players);
        double teamBSkill = getAverageSkill(5, 10, players);

        double mElo = (teamBElo - teamAElo) / 480;
        double mSkill = (teamASkill - teamBSkill) / 480;

        double aWinchance = 1 / (1 + Math.pow(10, mSkill));

        double aExpectedScore = 1 / (1 + Math.pow(10, mElo));
        double bExpectedScore = 1 - aExpectedScore;

        // System.out.println("---newgame---");
        // System.out.println(teamAElo);
        // System.out.println(teamBElo);
        // System.out.println(teamASkill);
        // System.out.println(teamBSkill);
        // System.out.println(mElo);
        // System.out.println(aExpectedScore);

        double aChange = 0;
        double bChange = 0;
        if (aWinchance < Math.random()) {
            aChange = k * (1 - aExpectedScore);
            bChange = k * (0 - bExpectedScore);
        } else {
            aChange = k * (0 - aExpectedScore);
            bChange = k * (1 - bExpectedScore);
        }
        updateElos(aChange, bChange, players);
    }

    public void updateElos(double aChange, double bChange, Player[] players) {
        for (int i = 0; i < 5; i++) {
            players[i].changeElo(aChange);
        }
        for (int i = 5; i < 10; i++) {
            players[i].changeElo(bChange);
        }
    }

    public double getAverageElos(int start, int end, Player[] players) { // start is inclusive, end is exclusive
        double total = 0.0;
        for (int i = start; i < end; i++) {
            total += players[i].getElo();
        }
        return total / (end - start);
    }

    public double getAverageSkill(int start, int end, Player[] players) { // start is inclusive, end is exclusive
        double total = 0.0;
        for (int i = start; i < end; i++) {
            total += players[i].getSkill();
        }
        return total / (end - start);
    }
}
