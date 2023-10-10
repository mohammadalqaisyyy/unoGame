
public interface UnoGame {
    enum Color {RED, YELLOW, GREEN, BLUE, BLACK, NONE}

    enum Type {NUMBER, REVERSE, SKIP, DRAW_TWO, WILD, WILD_DRAW_FOUR, NONE}

    void startGame();
}
