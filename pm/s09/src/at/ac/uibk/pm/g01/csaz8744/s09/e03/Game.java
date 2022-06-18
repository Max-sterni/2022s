package at.ac.uibk.pm.g01.csaz8744.s09.e03;

public class Game {

	private final Player player1;
	private final Player player2;
	private int round = 1;
	private int draws = 0;

	public int getPlayer1Wins() {
		return player1.getWins();
	}

	public int getPlayer2Wins() {
		return player2.getWins();
	}

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public int getDraws() {
		return draws;
	}

	public int getRound() {
		return round;
	}

	public Choice makePlayer1Choice(){
		return player1.getNewChoice();
	}

	public Choice makePlayer2Choice(){
		return player2.getNewChoice();
	}

	public Player playRound(Choice player1Choice, Choice player2Choice){
		int result = player1Choice.compareTo(player2Choice);
		round++;
		switch (result){
			case 0 -> {
				draws++;
				return null;
			}
			case -1 -> {
				player1.iterateWins();
				return player1;
			}
			case 1 -> {
				player2.iterateWins();
				return player2;
			}
		}
		return null;
	}

	public static void main(String args[]) {
		Game game = new Game(new Player(), new Player());

		// Game Loop
		do {
			System.out.println("***** Round: " + game.getRound() + " *********************\n");
			System.out.println("Number of Draws: " + game.getDraws() + "\n");
			Choice p1Choice = game.makePlayer1Choice();
			System.out.println("Player 1: " + p1Choice + "\t Player 1 Total Wins: " + game.getPlayer1Wins());
			Choice p2Choice = game.makePlayer2Choice();
			System.out.println("Player 2: " + p2Choice + "\t Player 2 Total Wins: " + game.getPlayer2Wins());

			Player winner = game.playRound(p1Choice, p2Choice);

			if(winner == null){
				System.out.println("DRAW");
			}
			else {
				System.out.println(winner + " wins");
			}

			if ((game.getPlayer1Wins() >= 3) || (game.getPlayer2Wins() >= 3)) {
				System.out.println("GAME WON");
				break;
			}
			System.out.println();
		} while (true);
	}

}
