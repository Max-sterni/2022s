package at.ac.uibk.pm.g01.csaz8744.midterm1.e01;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<OutfieldPlayer> players;

    public Team(){
        players = new ArrayList<OutfieldPlayer>();
    }

    public void addPlayer(OutfieldPlayer player){
        players.add(player);
    }
    @Override
    public String toString() {
        String output = "Team lineup: \n";

        for (OutfieldPlayer player: players
             ) {
            output = output + player.toString() + "\n";
        }

        return output;
    }
}
