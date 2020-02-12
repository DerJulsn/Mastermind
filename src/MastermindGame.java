public interface MastermindGame {

    //implemented from A2.pdf

    byte MAX_MOVES = 7;
    byte NUMBER_COLORS = 6;
    byte NUMBER_SLOTS = 4;
    void switchGuesser(); // switch coder and guesser
    boolean isMachineGuessing(); // who is guessing?
    int getMoveCount(); // number of moves
    ColorCode getGameState(int moveNo); // color choice for move moveNo
    ColorCode getSecret(); // secret machine color code
    void humanMove(ColorCode move); // human’s move
    ColorCode machineMove(); // machine’s move
    void processEval(ColorCode move, byte black, byte white); // human’s rating
}