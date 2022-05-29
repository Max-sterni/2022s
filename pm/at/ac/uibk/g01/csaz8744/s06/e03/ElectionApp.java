package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.util.HashSet;
import java.util.Set;

public class ElectionApp {
    public static void main(String[] args) {
        Set<ElectoralRegion> electionRegions = new HashSet<>();
        electionRegions.add(new ElectoralRegion("FirstRegion", 10));
        electionRegions.add(new ElectoralRegion("SecondRegion", 100));
        electionRegions.add(new ElectoralRegion("ThirdRegion", 1000));

        Set<PoliticalParty> politicalParties = new HashSet<>();
        politicalParties.add(new PoliticalParty("TestParty"));
        politicalParties.add(new PoliticalParty("SecondTestParty"));


        ElectionSystem electionSystem = new ElectionSystem(electionRegions, politicalParties);
        electionSystem.vote(new FixedVoteGenerator());
        electionSystem.assignSeats();
    }
}
