package cardmaster;

import java.util.Random;

/**
 * Die Shape Enumeration repräsentiert die möglichen Formen von Spielkarten.
 */
public enum Shape {
    CIRCLE, STAR, SQUARE;
    
    private static final Shape[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();
    
    /**
     * Gibt eine zufällige Form zurück.
     * 
     * @return eine zufällige Form
     */
    public static Shape getRandom() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
