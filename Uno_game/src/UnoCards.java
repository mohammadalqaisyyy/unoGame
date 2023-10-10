import java.util.*;

public class UnoCards {
    public final int NUMBER_OF_REGULAR_CARDS = 2;
    public final int NUMBER_OF_ZERO_CARDS = 1;
    public final int NUMBER_OF_ACTION_CARDS = 2;
    public final int NUMBER_OF_WILD_CARDS = 4;
    private final ArrayList<Card> cards;
    private final ArrayList<Card> deletedCards;
    private final Random rand;

    public UnoCards() {
        this.cards = new ArrayList<>();
        this.deletedCards = new ArrayList<>();
        rand = new Random(5);
        createSetOfCards();
        shuffleCards();
    }

    public void addToDeletedCards(Card card) {
        deletedCards.add(card);
        cards.remove(card);
    }

    public Card drawCard() {
        if (isEmpty())
            return null;
        Card temp = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return temp;
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public Card lastCard() {
        if (deletedCards.size() > 0)
            return deletedCards.get(deletedCards.size() - 1);
        return new Card.Builder(UnoGame.Color.NONE, UnoGame.Type.NONE).build();
    }


    private void createSetOfCards() {
        for (int i = 1; i < 10; i++) {
            for (int u = 0; u < NUMBER_OF_REGULAR_CARDS; u++) {
                cards.add(new Card.Builder(UnoGame.Color.RED, UnoGame.Type.NUMBER).number(i).build());
                cards.add(new Card.Builder(UnoGame.Color.BLUE, UnoGame.Type.NUMBER).number(i).build());
                cards.add(new Card.Builder(UnoGame.Color.YELLOW, UnoGame.Type.NUMBER).number(i).build());
                cards.add(new Card.Builder(UnoGame.Color.GREEN, UnoGame.Type.NUMBER).number(i).build());
            }
        }
        for (int i = 0; i < NUMBER_OF_ZERO_CARDS; i++) {
            cards.add(new Card.Builder(UnoGame.Color.RED, UnoGame.Type.NUMBER).number(0).build());
            cards.add(new Card.Builder(UnoGame.Color.BLUE, UnoGame.Type.NUMBER).number(0).build());
            cards.add(new Card.Builder(UnoGame.Color.YELLOW, UnoGame.Type.NUMBER).number(0).build());
            cards.add(new Card.Builder(UnoGame.Color.GREEN, UnoGame.Type.NUMBER).number(0).build());
        }
        for (int i = 0; i < NUMBER_OF_ACTION_CARDS; i++) {
            cards.add(new Card.Builder(UnoGame.Color.RED, UnoGame.Type.REVERSE).build());
            cards.add(new Card.Builder(UnoGame.Color.RED, UnoGame.Type.SKIP).build());
            cards.add(new Card.Builder(UnoGame.Color.RED, UnoGame.Type.DRAW_TWO).build());

            cards.add(new Card.Builder(UnoGame.Color.BLUE, UnoGame.Type.REVERSE).build());
            cards.add(new Card.Builder(UnoGame.Color.BLUE, UnoGame.Type.SKIP).build());
            cards.add(new Card.Builder(UnoGame.Color.BLUE, UnoGame.Type.DRAW_TWO).build());

            cards.add(new Card.Builder(UnoGame.Color.YELLOW, UnoGame.Type.REVERSE).build());
            cards.add(new Card.Builder(UnoGame.Color.YELLOW, UnoGame.Type.SKIP).build());
            cards.add(new Card.Builder(UnoGame.Color.YELLOW, UnoGame.Type.DRAW_TWO).build());

            cards.add(new Card.Builder(UnoGame.Color.GREEN, UnoGame.Type.REVERSE).build());
            cards.add(new Card.Builder(UnoGame.Color.GREEN, UnoGame.Type.SKIP).build());
            cards.add(new Card.Builder(UnoGame.Color.GREEN, UnoGame.Type.DRAW_TWO).build());
        }
        for (int i = 0; i < NUMBER_OF_WILD_CARDS; i++) {
            cards.add(new Card.Builder(UnoGame.Color.BLACK, UnoGame.Type.WILD).build());
            cards.add(new Card.Builder(UnoGame.Color.BLACK, UnoGame.Type.WILD_DRAW_FOUR).build());
        }
    }

    public void shuffleCards() {
        for (int i = 0; i < cards.size(); i++) {
            int x = rand.nextInt(cards.size());
            int y = rand.nextInt(cards.size());
            Card temp = cards.get(x);
            cards.set(x, cards.get(y));
            cards.set(y, temp);
        }
    }

    public void reloadCards() {
        cards.addAll(deletedCards);
        deletedCards.clear();
        shuffleCards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeAllCards() {
        cards.clear();
    }

    public void clearDeletedCards() {
        deletedCards.clear();
    }

    @Override
    public String toString() {
        return Integer.toString(cards.size());
    }
}
