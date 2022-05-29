package at.ac.uibk.pm.g01.csaz8744.s07.e02;

import java.util.*;

public class RandomGroupGenerator implements GroupGenerator{

    private Random random = new Random();

    @Override
    public Set<Group> assignGroups(Set<Player> players) {
        int groupAmount = random.nextInt(players.size()/2 - 1) + 1;

        List<Group> groups = new ArrayList<>();
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

        return new HashSet<>(groups);
    }

    private Player getOnePlayer(List<Player> playerList){
        Player output = playerList.get(0);
        playerList.remove(0);
        return output;
    }
}
