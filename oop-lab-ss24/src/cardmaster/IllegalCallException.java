package cardmaster;

/**
 * Represents an exception thrown when an illegal method call is made in a particular mode.
 */
public class IllegalCallException extends RuntimeException {
    /**
     * Constructs a new IllegalCallException with the specified details.
     * 
     * @param methodName    the name of the method where the illegal call occurred
     * @param currentMode   the current mode of the application
     * @param expectedMode  the expected mode for the method call
     */
    public IllegalCallException(String methodName, String currentMode, String expectedMode) {
        super("Illegal call in method '" + methodName + "' in current mode '" + currentMode + "'. Expected mode is '"
                + expectedMode + "'.");
    }

    /**
     * Constructs a new IllegalCallException with no detail message.
     */
    public IllegalCallException(){
        super();
    }
}
