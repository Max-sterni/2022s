package at.ac.uibk.pm.g01.csaz8744.s06.e03;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
    
}

private void dHondtMethod(ElectoralRegion electoralRegion){
    List<> quotients = new ArrayList<>();

    for (PoliticalParty party : politicalParties) {
        
        quotients.add();
    }

    for (int i = 0; i < electoralRegion.getMaximumSeats(); i++) {
        
    }
}

private class QuotientPartyPair implements Iterator<QuotientPartyPair>{

    private Integer quotiant;
    private final PoliticalParty party;
    private final ElectoralRegion region;

    @Override
    public QuotientPartyPair next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        quotiant = 
    }

    public Integer getQuotiant() {
        return quotiant;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    public final QuotientPartyPair(PoliticalParty party, ElectoralRegion region){
        this.party = party;
        this.region = region;
        quotiant = party.getVotesFromRegion(region);
    }
    
}

public Set<ElectoralRegion> getElectionRegions() {
    return electionRegions;
}

public Set<PoliticalParty> getPoliticalParties() {
    return politicalParties;
}

}
