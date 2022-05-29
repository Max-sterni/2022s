package at.ac.uibk.pm.g01.csaz8744.s06.e02;

import java.util.HashSet;
import java.util.Set;

public class ElectionApp {
    public static void main(String[] args) {
        Set<ElectoralRegion> electionRegions = new HashSet<>();
        electionRegions.add(new ElectoralRegion("Unglang", 50));
        electionRegions.add(new ElectoralRegion("Greencity", 120));
        electionRegions.add(new ElectoralRegion("Hammerbruenicken", 180));
        
        Set<PoliticalParty> politicalParties = new HashSet<>();
        politicalParties.add(new PoliticalParty("The cool guys"));
        politicalParties.add(new PoliticalParty("The smart guys"));
        politicalParties.add(new PoliticalParty("The hard guys"));

        ElectionSystem electionSystem = new ElectionSystem(electionRegions, politicalParties);
        electionSystem.vote(new RandomizedVoteGenorator());
        
    }
}
