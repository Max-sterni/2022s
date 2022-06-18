package at.ac.uibk.pm.g01.csaz8744.s09.e03;

public class Player {
	private int wins;
	private final int id;
	private static int playerAmount = 0;

	public Player() {
		playerAmount++;
		this.id = playerAmount;
	}

	/**
	 * Randomly choose rock, paper, or scissors
	 */
	public Choice getNewChoice() {
		return new Choice();
	}

	public void iterateWins() {
		wins++;
	}

	public int getWins() {
		return wins;
	}

	@Override
	public String toString() {
		return "Player " + id;
	}
}
