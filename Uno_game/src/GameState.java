public class GameState implements GameAlertState {
    public void waitingPlayers() {
        System.out.println("Waiting for players to join...");
    }

    public void skipTurn() {
        System.out.println("Skipping turn...");
    }

    public void playCard() {
        System.out.println("Playing a card...");
    }

    public void drawCard() {
        System.out.println("Drawing a card...");
    }
    public void reversedTurns() {
        System.out.println("Reverse round...");
    }
}
