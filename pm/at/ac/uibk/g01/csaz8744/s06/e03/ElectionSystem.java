package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
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

public void assignSeats(){
    for (ElectoralRegion electoralRegion : electionRegions) {
        dHondtMethod(electoralRegion);
    }

    for (PoliticalParty politicalParty : politicalParties) {
        System.out.println(politicalParty.showSeats());
    }
}

private void dHondtMethod(ElectoralRegion electoralRegion){
    List<QuotientPartyPair> quotients = new ArrayList<>();

    for (PoliticalParty party : politicalParties) {
        quotients.add(new QuotientPartyPair(party, electoralRegion));
    }


    for (int i = 0; i < electoralRegion.getMaximumSeats(); i++) {
        Collections.sort(quotients);

        quotients.get(0).getParty().addSeat(electoralRegion);
        quotients.get(0).next();
    }
}

private class QuotientPartyPair implements Iterator<Integer>, Comparable<QuotientPartyPair>{

    private Integer quotiant;
    private final PoliticalParty party;
    private final ElectoralRegion region;

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        quotiant = party.getVotesFromRegion(region) / (party.getSeatsFromRegion(region) + 1); 
        return quotiant;
    }

    public Integer getQuotiant() {
        return quotiant;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    public QuotientPartyPair(PoliticalParty party, ElectoralRegion region){
        this.party = party;
        this.region = region;
        quotiant = party.getVotesFromRegion(region);
    }
    
    @Override
    public int compareTo(QuotientPartyPair otherParty) {
        return -this.quotiant.compareTo(otherParty.getQuotiant());
    }

    public PoliticalParty getParty() {
        return party;
    }

    @Override
    public String toString() {
        return party.getName() + " " + quotiant;
    }

}

public Set<ElectoralRegion> getElectionRegions() {
    return electionRegions;
}

public Set<PoliticalParty> getPoliticalParties() {
    return politicalParties;
}

}
