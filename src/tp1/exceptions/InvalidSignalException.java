package tp1.exceptions;

public class InvalidSignalException extends Exception {
    private int charPos;
    private char character;

    public InvalidSignalException(String message, int charPos, char character) {
        super(message);

        this.charPos = charPos;
        this.character = character;
    }

    public int getCharPos() {
        return this.charPos;
    }

    public char getCharacter() {
        return this.character;
    }
}
