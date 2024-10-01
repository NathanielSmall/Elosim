public class Playerfactory {

    public Playerfactory() {

    }

    public Player[] createPlayers(int playercount) {
        Player[] players = new Player[playercount];

        for (int i = 0; i < playercount; i++) {
            players[i] = new Player(i);
        }

        return players;
    }
}
