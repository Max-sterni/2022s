package at.ac.uibk.pm.g01.csaz8744.s06.e02;

import java.util.HashMap;
import java.util.Map;

public class PoliticalParty {
    
    private final String name;
    private Map<ElectoralRegion, Integer> votesFromRegions = new HashMap<>();

    public PoliticalParty(String name) {
        this.name = name;
    }

    public void setVotes(ElectoralRegion region, Integer votes) {
        votesFromRegions.put(region, votes);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(name + " ");
        for (Map.Entry<ElectoralRegion, Integer> voteInfo : votesFromRegions.entrySet()) {
            output.append(voteInfo.getKey() + " - " + voteInfo.getValue() + " votes ");
        }
        return output.toString();
    }

    public String getName() {
        return name;
    }
}
