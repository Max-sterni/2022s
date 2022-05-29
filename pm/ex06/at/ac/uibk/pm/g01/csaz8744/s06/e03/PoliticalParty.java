package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.util.HashMap;
import java.util.Map;

public class PoliticalParty implements Comparable<PoliticalParty>{
    
    private final String name;
    private Map<ElectoralRegion, Integer> votesFromRegions = new HashMap<>();
    private Map<ElectoralRegion, Integer> seatsFromRegions = new HashMap<>();

    public PoliticalParty(String name) {
        this.name = name;
    }

    public void setVotes(ElectoralRegion region, Integer votes) {
        votesFromRegions.put(region, votes);
    }

    public Integer getVotesFromRegion(ElectoralRegion region){
        if(votesFromRegions.containsKey(region)){
            return votesFromRegions.get(region);
        }
        else{
            throw new IllegalArgumentException("No such region exists");
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(name + " ");
        for (Map.Entry<ElectoralRegion, Integer> voteInfo : votesFromRegions.entrySet()) {
            output.append(voteInfo.getKey() + " - " + voteInfo.getValue() + " votes ");
        }
        return output.toString();
    }

    @Override
    public int compareTo(PoliticalParty otherParty) {
        return name.compareTo(otherParty.getName());
    }

    public String getName() {
        return name;
    }
}
