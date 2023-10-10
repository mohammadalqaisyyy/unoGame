public interface GameAlertState {
    void waitingPlayers();
    void skipTurn();
    void playCard();
    void drawCard();
    void reversedTurns();
}
