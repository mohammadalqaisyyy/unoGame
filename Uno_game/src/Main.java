import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        ArrayList<String> players = new ArrayList<>();
        players.add("Mohammad");
        players.add("Ahmad");
        new MyGame(players);
    }
}
