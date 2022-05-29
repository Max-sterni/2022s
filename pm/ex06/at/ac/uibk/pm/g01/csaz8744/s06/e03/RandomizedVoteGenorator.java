package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.util.Random;
import java.util.Set;

public class RandomizedVoteGenorator implements VoteGenerator{
    
    private Random random = new Random();
    private final int votingConstant = 10000;

    @Override
    public void distributeVotes(Set<PoliticalParty> parties, Set<ElectoralRegion> regions) {
        for (PoliticalParty politicalParty : parties) {
            for (ElectoralRegion electoralRegion : regions) {
                politicalParty.setVotes(electoralRegion, random.nextInt(votingConstant));
    
            }
        }
    }

}
