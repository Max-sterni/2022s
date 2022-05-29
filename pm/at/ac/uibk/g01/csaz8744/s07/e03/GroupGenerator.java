package at.ac.uibk.pm.g01.csaz8744.s07.e03;

import java.util.Set;

public interface GroupGenerator<Result extends Comparable<Result>> {
    Set<Group<Result>> assignGroups(Set<Player> players);
}
