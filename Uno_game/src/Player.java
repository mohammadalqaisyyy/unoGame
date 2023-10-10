import java.util.*;

public class Player implements Observer {
    private final String name;
    private final ArrayList<Card> cards;
    private int score;

    public Player(String name, ArrayList<Card> cards) {
        this.name = name;
        this.cards = cards;
        this.score = 0;
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public void throwCard(Card card) {
        for (Card c : cards) {
            if (c.equals(card)) {
                cards.remove(c);
                return;
            }
        }
        throw new IllegalArgumentException("Does not contain this card");
    }


    public StringBuilder viewCards() {
        StringBuilder str = new StringBuilder();
        for (Card c : cards) {
            if (cards.indexOf(c) == cards.size() - 1) {
                String s = "(" + (cards.indexOf(c) + 1) + ") " + c.toString();
                str.append(s);
                break;
            }
            String s = "(" + (cards.indexOf(c) + 1) + ") " + c.toString() + " __ ";
            str.append(s);
        }
        return str;
    }


    public void addScore(int number) {
        score += number;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public Card getCard(int number) {
        return cards.get(number - 1);
    }

    @Override
    public void update(String message) {
        System.out.println(name + ": " + message);
    }


    @Override
    public String toString() {
        return this.name + " " + this.cards.size();
    }
}
