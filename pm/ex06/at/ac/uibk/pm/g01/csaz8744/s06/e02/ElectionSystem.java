package at.ac.uibk.pm.g01.csaz8744.s06.e02;

import java.util.Set;

public class ElectionSystem {
    
    private final Set<ElectoralRegion> electionRegions;
    private final Set<PoliticalParty> politicalParties;

    public ElectionSystem(Set<ElectoralRegion> electionRegions, Set<PoliticalParty> politicalParties) {
        this.electionRegions = electionRegions;
        this.politicalParties = politicalParties;
    }
    
public void vote(VoteGenerator generator) {
    generator.distributeVotes(politicalParties, electionRegions);

    for (PoliticalParty politicalParty : politicalParties) {
        System.out.println(politicalParty);
    }
}

public Set<ElectoralRegion> getElectionRegions() {
    return electionRegions;
}

public Set<PoliticalParty> getPoliticalParties() {
    return politicalParties;
}

}
