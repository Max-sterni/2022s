package at.ac.uibk.pm.g01.csaz8744.midterm1.e01;

public class Exercise1Application {
    public static void main(String[] args) {
        Team testTeam = new Team();
        testTeam.addPlayer(new Attacker("Hans Gunther", Position.LEFT, 10));
        testTeam.addPlayer(new Attacker("Gunther Jauch", Position.RIGHT, 8));

        testTeam.addPlayer(new Attacker("Sandra Sansibar", Position.CENTER, 6));
        testTeam.addPlayer(new Attacker("Gaby Blaubach", Position.CENTER, 3));

        System.out.println(testTeam.toString());
    }
}
