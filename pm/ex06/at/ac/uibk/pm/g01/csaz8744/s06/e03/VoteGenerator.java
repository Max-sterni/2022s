package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.util.Set;

public interface VoteGenerator {

    void distributeVotes(Set<PoliticalParty> parties, Set<ElectoralRegion> regions);
}

