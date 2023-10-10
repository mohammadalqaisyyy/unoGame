import java.util.Objects;

public class Card {
    private final UnoGame.Color color;
    private final UnoGame.Type type;
    private final int number;

    private Card(Builder builder) {
        this.color = builder.color;
        this.type = builder.type;
        this.number = builder.number;
    }

    public static class Builder {
        private final UnoGame.Color color;
        private final UnoGame.Type type;
        private int number;

        public Builder(UnoGame.Color color, UnoGame.Type type) {
            this.color = color;
            this.type = type;
            this.number = -1;
        }

        public Builder number(int number) {
            if (0 <= number && number <= 9) {
                this.number = number;
                return this;
            } else
                throw new IllegalArgumentException("the number is not accepted");
        }

        public Card build() {
            return new Card(this);
        }
    }

    public int getNumber() {
        return number;
    }

    public UnoGame.Type getType() {
        return type;
    }

    public UnoGame.Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        if (this.number == -1)
            return this.type.toString() + " " + this.color.toString();
        return this.type.toString() + " " + this.color.toString() + " " + this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && color == card.color && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, number);
    }
}
