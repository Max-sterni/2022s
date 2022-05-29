package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FixedVoteGenerator implements VoteGenerator{

    List<Integer> votes = Arrays.asList(new Integer[]{10, 100, 1000});
    @Override
    public void distributeVotes(Set<PoliticalParty> parties, Set<ElectoralRegion> regions) {
        int i = 0;
        for (PoliticalParty party: parties
        ) {
            for (ElectoralRegion region: regions
            ) {
                party.setVotes(region, votes.get(i++ % votes.size()));
            }
        }
    }
}
