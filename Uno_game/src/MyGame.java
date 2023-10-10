import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MyGame extends Game {
    public MyGame(@NotNull ArrayList<String> namesOfPlayers) throws IllegalAccessException {
        super(namesOfPlayers);
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(players.size());
        while (!isEndGame()) {
            Player player = players.get(getActivePlayerID());
            System.out.println("\n#Scoreboard");
            System.out.println(scores());
            System.out.println("Last card: " + cards.lastCard().toString() + " __ Active color: " + getColor());
            System.out.println(player.getName() + " == " + player.viewCards());
            gameState.playCard();
            String cardStr = scanner.next();
            if (Objects.equals(cardStr, ".")) {
                player.drawCard(cards.drawCard());
                toNextPlayer();
                continue;
            } else if (Objects.equals(cardStr, "exit")) {
                break;
            }
            Card card = player.getCard(Integer.parseInt(cardStr));
            if (turn(player, card)) {
                cards.addToDeletedCards(card);
                player.throwCard(card);
            } else {
                gameState.drawCard();
                player.drawCard(cards.drawCard());
                player.update("Drew card..");
            }
            if (player.isEmpty()) {
                setEndGame(true);
                System.out.println(win(player));
                scores();
                System.exit(0);
            }
            if (getColor() == Color.NONE || getColor() == UnoGame.Color.BLACK) {
                setEmptyTable(false);
                setColor(cards.lastCard().getColor());
            }
            toNextPlayer();
        }
    }


    public boolean turn(Player player, Card card) {
        player.update("Played card..");
        if (isEmptyTable() || getColor() == UnoGame.Color.BLACK) {
            players.get(getActivePlayerID()).addScore(card.getNumber());
            return true;
        }
        if (acceptThrowCard(card)) {
            if (card.getType() == UnoGame.Type.NUMBER && card.getNumber() == cards.lastCard().getNumber()) {
                players.get(getActivePlayerID()).addScore(card.getNumber());
                changeColor(card.getColor());
                return true;
            }
            if (card.getType() == UnoGame.Type.DRAW_TWO) {
                players.get(getActivePlayerID()).addScore(20);
                drawPlayerCards(nextPlayerID(player), 2);
                return true;
            }
            if (card.getType() == UnoGame.Type.REVERSE) {
                players.get(getActivePlayerID()).addScore(20);
                gameState.reversedTurns();
                reverse();
                return true;
            }
            if (card.getType() == UnoGame.Type.SKIP) {
                players.get(getActivePlayerID()).addScore(20);
                gameState.skipTurn();
                toNextPlayer();
                return true;
            }
            if (card.getType() == UnoGame.Type.WILD_DRAW_FOUR) {
                players.get(getActivePlayerID()).addScore(50);
                chooseColorString();
                drawPlayerCards(nextPlayerID(player), 4);
                return true;
            }
            if (card.getType() == UnoGame.Type.WILD) {
                players.get(getActivePlayerID()).addScore(50);
                chooseColorString();
                return true;
            }
            if (card.getColor() == getColor()) {
                players.get(getActivePlayerID()).addScore(card.getNumber());
                return true;
            }
        }
        return false;
    }

    public void chooseColorString() {
        Scanner s = new Scanner(System.in);
        System.out.println("(1) Red __ (2) Blue __ (3) Yellow __ (4) Green");
        int ss = s.nextInt();
        if (ss == 1)
            chooseColor("red");
        else if (ss == 2)
            chooseColor("blue");
        else if (ss == 3)
            chooseColor("yellow");
        else if (ss == 4)
            chooseColor("green");
        else
            throw new ArrayIndexOutOfBoundsException("Not in Choices");
    }

    @Override
    public String toString() {
        return "MyGame{" +
                "gameState=" + gameState +
                ", players=" + players +
                '}';
    }
}
