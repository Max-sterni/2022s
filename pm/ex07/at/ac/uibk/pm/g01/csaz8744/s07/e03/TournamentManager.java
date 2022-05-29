package at.ac.uibk.pm.g01.csaz8744.s07.e03;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TournamentManager<Result extends Comparable<Result>> {

    private final GroupGenerator groupGenerator;
    private final ResultGenerator resultGenerator;
    private final Set<Player> players;
    private Set<Group<Result>> groups;
    private List<Encounter<Result>> encounterList;

    private Group finaleGroup;
    private final int TOPPLAYERCONSTANT = 1;
    public TournamentManager(Set<Player> players, GroupGenerator groupGenerator, ResultGenerator resultGenerator) {
        this.players = players;
        this.groupGenerator = groupGenerator;
        this.resultGenerator = resultGenerator;
        this.encounterList = new ArrayList<>();
        assignGroups();
        createEncounter();
    }

    private void assignGroups(){
        this.groups = groupGenerator.assignGroups(players);
    }

    private void createEncounter(){
        for (Group<Result> group : groups) {
            encounterList.addAll(group.getEncounters());
        }
        for (Encounter<Result> encounter : encounterList) {
            encounter.evaluate(resultGenerator);
            System.out.println(encounter);
        }
    }

    public void printRanking(){
        for (Group<Result> group : groups) {
            for (int i = 0; i < group.getRankingList().size(); i++) {
                System.out.printf("%d. %s%n", i + 1, group.getRankingList().get(i).nameWithScore());
            }
        }
    }

    public void createFinale(){
        finaleGroup = new Group<Result>();
        for (Group<Result> group : groups) {
            for (int i = 0; i < TOPPLAYERCONSTANT; i++) {
                finaleGroup.addPlayer(group.getRankingList().get(i));
            }
        }
        encounterList.clear();
        if(finaleGroup.isValid()){
            encounterList.addAll(finaleGroup.getEncounters());

        }
        else {
            throw new IllegalStateException();
        }

        for (Encounter<Result> encounter : encounterList) {
            encounter.evaluate(resultGenerator);
            System.out.println(encounter);
        }

        for (int i = 0; i < finaleGroup.getRankingList().size(); i++) {
            System.out.printf("%d. %s%n", i + 1, ((Player)finaleGroup.getRankingList().get(i)).nameWithScore());
        }
    }

}
