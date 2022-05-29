package at.ac.uibk.pm.g01.csaz8744.s07.e03;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TournamentSimulator {
    public static void main(String[] args) {
        Set<Player> players = new HashSet<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));

        players.add(new Player("Player3"));
        players.add(new Player("Player4"));

        players.add(new Player("Player5"));
        players.add(new Player("Player6"));

        players.add(new Player("Player7"));
        players.add(new Player("Player8"));

        RandomResultFunction function = new RandomResultFunction();
        TournamentManager<Integer> simulation = new TournamentManager<>(players,
                new RandomGroupGenerator(), new RandomResultGenerator(function));

        simulation.printRanking();
        simulation.createFinale();
    }
}
