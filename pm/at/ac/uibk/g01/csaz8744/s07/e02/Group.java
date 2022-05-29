package at.ac.uibk.pm.g01.csaz8744.s07.e02;

import java.util.*;

public class Group {

    private List<Player> players;
    private final Comparator<Player> comparator;

    public Group() {
        players = new ArrayList<>();
        this.comparator = new PlayerComparator();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public List<Player> getRankingList() {
        players.sort(comparator);
        return players;
    }

    public Set<Encounter> getEncounters() {
        Set<Encounter> output = new HashSet<>();
        for (Player player1 : players) {
            for (Player player2 : players) {
                if (!player1.equals(player2)){
                    output.add(new Encounter(player1, player2));
                }
            }
        }
        return output;
    }

    private class PlayerComparator implements Comparator<Player>{
        @Override
        public int compare(Player player1, Player player2) {
            return Integer.compare(player1.getScore(), player2.getScore());
        }
    }
}
