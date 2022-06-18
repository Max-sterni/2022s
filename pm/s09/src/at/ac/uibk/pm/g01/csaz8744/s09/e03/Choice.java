package at.ac.uibk.pm.g01.csaz8744.s09.e03;

import java.util.Random;

public class Choice implements Comparable<Choice> {

    private final ChoiceType type;

    public Choice(){
        this.type = ChoiceType.getRandomChoice();
    }

    @Override
    public int compareTo(Choice opponentsChoice) {
        if(type == opponentsChoice.type){
            return 0;
        }
        if (evalWin(opponentsChoice.type)){
            return -1;
        }
        return 1;
    }

    private boolean evalWin(ChoiceType opponentsType){
        switch (type) {
            case ROCK -> {
                return opponentsType == ChoiceType.SCISSORS;
            }
            case SCISSORS -> {
                return opponentsType == ChoiceType.PAPER;
            }
            case PAPER -> {
                return opponentsType == ChoiceType.ROCK;
            }
        }
        return false;
    }

    public ChoiceType getType() {
        return type;
    }

    @Override
    public String toString() {
        switch (type) {
            case PAPER -> {
                return "paper";
            }
            case ROCK -> {
                return "rock";
            }
            case SCISSORS -> {
                return "scissors";
            }
        }
        return "";
    }

    private enum ChoiceType {
        ROCK,
        PAPER,
        SCISSORS;

        private static final Random RANDOM = new Random();
        private static ChoiceType getRandomChoice(){
            return values()[RANDOM.nextInt(3)];
        }
    }
}
