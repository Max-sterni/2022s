package at.ac.uibk.pm.g01.csaz8744.s07.e02;

import java.util.List;
import java.util.Set;

public class TournamentManager< Score> {

    private final GroupGenerator groupGenerator;
    private final ResultGenerator resultGenerator;
    private final Set<Player> players;
    private Set<Group> groups;
    private List<Encounter> encounterList;

    public TournamentManager(Set<Player> players, GroupGenerator groupGenerator, ResultGenerator resultGenerator) {
        this.players = players;
        this.groupGenerator = groupGenerator;
        this.resultGenerator = resultGenerator;
    }

    private void assignGroups(){
        this.groups = groupGenerator.assignGroups(players);
    }

    private void createEncounter(){
        for (Group group : groups) {
            encounterList.addAll(group.getEncounters());
        }
        for (Encounter encounter : encounterList) {
            encounter.evaluate(resultGenerator);
            System.out.println(encounter);
        }
    }

    public void printRanking(){
        for (Group group : groups) {
            System.out.println(group.getRankingList());
        }
    }

}
