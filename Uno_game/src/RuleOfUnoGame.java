public interface RuleOfUnoGame {
    int numberOfPlayerCards = 7;

    void acceptedNumberOfPlayers(int numberOfPlayers) throws IllegalAccessException;

    boolean acceptThrowCard(Card newCard);

    StringBuilder scores();

    void reverse();

    void toNextPlayer();

    void changeColor(UnoGame.Color color);
}
