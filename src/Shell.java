import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Shell implements MastermindGame {

    private static ConsoleManager manager;

    public static void main(String[] args) {

        Shell controller = new Shell();

        new GUI().setShell(controller);
        PApplet.main("GUI");

        manager = new ConsoleManager();
        manager.listen(controller);
    }

    private ColorCode colorCode;
    private int movesLeft = 0;
    private final int NO_POSSIBILITIES = (int) Math.pow(NUMBER_COLORS, NUMBER_SLOTS);
    private ArrayList<ColorCode> possibilities = new ArrayList<>();
    private boolean isMachineGuessing = false;
    ArrayList<ColorCode> history = new ArrayList<>();

    private Shell() {
        startNewGame();
    }

    void quit() {
        manager.getConsoleListener().interrupt();
    }

    @Override
    public void switchGuesser() {
        isMachineGuessing = !isMachineGuessing;
        if (isMachineGuessing) {
            System.out.println("Du bist nun der Codierer!");
        } else {
            System.out.println("Du bist nun der Rater!");
        }
        startNewGame();
    }

    //true if machine is guessing the color code
    @Override
    public boolean isMachineGuessing() {
        return isMachineGuessing;
    }

    @Override
    public int getMoveCount() {
        return MAX_MOVES - movesLeft;
    }

    @Override
    public ColorCode getGameState(int moveNo) {
        if (moveNo < history.size())
            return history.get(moveNo);
        return null;
    }

    @Override
    public ColorCode getSecret() {
        return colorCode;
    }

    @Override
    public void humanMove(ColorCode move) {

        movesLeft--;

        System.out.println("Black Pins: " + getBlackPins(move));
        System.out.println("White Pins: " + getWhitePins(move));

        if (getBlackPins(move) == NUMBER_SLOTS) {
            System.out.println("Richtig! Der geheime Code war: " + Arrays.toString(getSecret().getColors()));
            return;
        }

        if (movesLeft == 0) {
            System.out.println("Game Over!");
        }
    }

    @Override
    public ColorCode machineMove() {

        movesLeft--;
        if (movesLeft == 0) {
            System.out.println("Game Over!");
            return null;
        }

        ColorCode randomCode = possibilities.get(new Random().nextInt(possibilities.size()));
        //Print
        System.out.println(Arrays.toString(randomCode.getColors()));
        history.add(randomCode);

        return randomCode;
    }

    @Override
    public void processEval(ColorCode move, byte black, byte white) {

        ArrayList<ColorCode> toRemove = new ArrayList<>();
        for (ColorCode possibility : possibilities) {
            if (black != getBlackPins(move, possibility) || white != getWhitePins(move, possibility)) {
                toRemove.add(possibility);
            }
        }
        for (ColorCode code : toRemove) {
            possibilities.remove(code);
        }

        if (possibilities.size() == 1) {
            System.out.println("Gewonnen!");
            System.out.println("Der geheime Code war: " + Arrays.toString(possibilities.get(0).getColors()));
            return;
        }
        if (possibilities.size() == 0) {
            System.out.println("Gemogelt!");
            return;
        }

        machineMove();
    }

    private void setAllPossibilities() {
        for (int i = 0; i < NO_POSSIBILITIES; ++i) {
            ColorCode colorCode = new ColorCode(NUMBER_SLOTS);
            int aux = i;
            for (byte j = MastermindGame.NUMBER_SLOTS - 1; j >= 0; --j) {
                colorCode.set(j, (byte) (aux % (int) MastermindGame.NUMBER_COLORS));
                aux = aux / MastermindGame.NUMBER_COLORS;
            }
            possibilities.add(i, colorCode);
        }
    }

    void startNewGame() {
        colorCode = new ColorCode(NUMBER_SLOTS, NUMBER_COLORS);
        movesLeft = MAX_MOVES;
        history.clear();
        setAllPossibilities();

        if (!isMachineGuessing) {
            machineMove();
        }
    }

    private byte getBlackPins(ColorCode a, ColorCode b) {

        byte[] solutionColors = a.getColors().clone();
        byte[] currentColors = b.getColors().clone();
        byte blackPins = 0;

        for (int i = 0; i < NUMBER_SLOTS; i++) {
            if (currentColors[i] == solutionColors[i]) {
                blackPins++;
            }
        }
        return blackPins;

    }

    private byte getBlackPins(ColorCode a) {
        return getBlackPins(a, getSecret());
    }

    private byte getWhitePins(ColorCode a, ColorCode b) {

        byte[] solutionColors = a.getColors().clone();
        byte[] currentColors = b.getColors().clone();

        List<Integer> matches = new ArrayList<>();

        for (int i = 0; i < NUMBER_SLOTS; i++) {
            for (int j = 0; j < NUMBER_SLOTS; j++) {
                if (!matches.contains(j)) {
                    if (currentColors[i] == solutionColors[j]) {
                        matches.add(j);
                        break;
                    }
                }
            }
        }
        return (byte) (matches.size() - getBlackPins(a, b));

    }

    private byte getWhitePins(ColorCode a) {
        return getWhitePins(a, getSecret());
    }

}
