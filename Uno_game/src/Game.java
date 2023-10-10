import java.util.*;

public abstract class Game implements RuleOfUnoGame, UnoGame {
    protected final UnoCards cards;
    protected final GameState gameState;
    protected final ArrayList<Player> players;
    private final int numberOfPlayers;
    private final ArrayList<String> listOfPlayers;
    private int cycle;
    private UnoGame.Color color;
    private boolean endGame;
    private boolean emptyTable;
    private int activePlayerID;

    public Game(@org.jetbrains.annotations.NotNull ArrayList<String> namesOfPlayers) throws IllegalAccessException {
        this.numberOfPlayers = namesOfPlayers.size();
        acceptedNumberOfPlayers(this.numberOfPlayers);
        this.players = new ArrayList<>();
        this.listOfPlayers = namesOfPlayers;
        this.cards = new UnoCards();
        activePlayerID = 0;
        cycle = 1;
        color = UnoGame.Color.NONE;
        emptyTable = true;
        endGame = false;
        gameState = new GameState();
        gameState.waitingPlayers();
        startGame();
        play();
    }

    public abstract void play();

    public void acceptedNumberOfPlayers(int numberOfPlayers) throws IllegalAccessException {
        if (numberOfPlayers < 2 || numberOfPlayers > 10)
            throw new IllegalAccessException("Unaccepted number of players");
    }

    public void startGame() {
        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Card> temp = new ArrayList<>();
            for (int u = 0; u < numberOfPlayerCards; u++) {
                temp.add(cards.drawCard());
            }
            Player p = new Player(listOfPlayers.get(i), temp);
            players.add(p);
        }
    }

    public String win(Player p) {
        return "The Winner is : " + p.getName();
    }

    public void chooseColor(String color) {
        if (Objects.equals(color, "red"))
            changeColor(Color.RED);
        else if (Objects.equals(color, "blue"))
            changeColor(Color.BLUE);
        else if (Objects.equals(color, "yellow"))
            changeColor(Color.YELLOW);
        else if (Objects.equals(color, "green"))
            changeColor(Color.GREEN);
    }

    public void toNextPlayer() {
        if (activePlayerID + cycle == players.size())
            activePlayerID = 0;
        else if (activePlayerID + cycle == -1)
            activePlayerID = players.size() - 1;
        else
            activePlayerID += cycle;
    }

    public void changeColor(UnoGame.Color color) {
        this.color = color;
    }

    public void drawPlayerCards(int playerID, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++)
            players.get(playerID).drawCard(cards.drawCard());
    }

    public int nextPlayerID(Player player) {
        int playerID = players.indexOf(player);
        if (playerID == numberOfPlayers - 1 && cycle == 1)
            return 0;
        if (playerID == 0 && cycle == -1)
            return numberOfPlayers - 1;
        return playerID + cycle;
    }

    public void reverse() {
        cycle *= -1;
    }

    public boolean acceptThrowCard(Card newCard) {
        Card temp = cards.lastCard();
        if (newCard.getType() == UnoGame.Type.WILD || newCard.getType() == Type.WILD_DRAW_FOUR)
            return true;
        if (newCard.getType() == UnoGame.Type.NUMBER)
            if (newCard.getColor() == color || newCard.getNumber() == temp.getNumber())
                return true;
        if (newCard.getType() == UnoGame.Type.SKIP && newCard.getColor() == color)
            return true;
        if (newCard.getType() == UnoGame.Type.REVERSE && newCard.getColor() == color)
            return true;
        return newCard.getType() == Type.DRAW_TWO && newCard.getColor() == color;
    }

    public StringBuilder scores() {
        StringBuilder str = new StringBuilder();
        for (Player p : players) {
            String s = p.getName() + " (" + p.getScore() + ") \n";
            str.append(s);
        }
        return str;
    }

    public int getActivePlayerID() {
        return activePlayerID;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEmptyTable(boolean emptyTable) {
        this.emptyTable = emptyTable;
    }

    public boolean isEmptyTable() {
        return emptyTable;
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", listOfPlayers=" + listOfPlayers +
                ", activePlayerID=" + activePlayerID +
                ", color=" + color +
                ", cycle=" + cycle +
                ", emptyTable=" + emptyTable +
                ", endGame=" + endGame +
                '}';
    }
}
