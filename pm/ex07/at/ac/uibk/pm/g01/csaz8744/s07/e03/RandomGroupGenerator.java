package at.ac.uibk.pm.g01.csaz8744.s07.e03;

import java.util.*;

public class RandomGroupGenerator<Result extends Comparable<Result>> implements GroupGenerator<Result> {

    private Random random = new Random();

    @Override
    public Set<Group<Result>> assignGroups(Set<Player> players) {
        int groupAmount;
        if(players.size() == 2){
            groupAmount = 1;
        }
        else {
            groupAmount = players.size()/2;
        }

        List<Group<Result>> groups = new ArrayList<>();
        List<Player> playerList = new ArrayList<>(players);

        Collections.shuffle(playerList, random);

        for (int i = 0; i < groupAmount; i++) {
            groups.add(new Group());
        }

        // Initialize with two players
        for (Group group : groups) {
            group.addPlayer(getOnePlayer(playerList));
            group.addPlayer(getOnePlayer(playerList));
        }

        // Add remaining players
        for (Player player : playerList) {
            groups.get(random.nextInt(groupAmount)).addPlayer(player);
        }

        return new HashSet<Group<Result>>(groups);
    }

    private Player getOnePlayer(List<Player> playerList){
        Player output = playerList.get(0);
        playerList.remove(0);
        return output;
    }
}
