package tiktactoe.model.gamedata;

/**
 * Class that defines enums that will be used constantly in the application
 */
public enum GameEnum {
    WIN,
    LOST,
    TIE,
    X,
    O,
    NONE;

    /**
     * Converts a char to the specific GameEnum symbol
     * @param symbol The char representation of the board symbol
     * @return The GameEnum representation of the symbol
     */
    public static GameEnum fromChar(char symbol) {
        try {
            return switch (symbol) {
            case 'X' -> X;
            case 'O' -> O;
            case ' ' -> NONE;
            default -> null;
            };
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return NONE;
    }
}
