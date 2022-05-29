package at.ac.uibk.pm.g01.csaz8744.s07.e02;

import java.util.Comparator;
import java.util.Set;

public interface GroupGenerator {
    Set<Group> assignGroups(Set<Player> players);
}
