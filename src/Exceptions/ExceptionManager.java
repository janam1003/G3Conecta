package exceptions; // This is the package declaration

/**
 * This class represents an ExceptionManager that extends the built-in Exception
 * class. It is used for creating custom exceptions.
 *
 * @author Janam
 */
public class ExceptionManager extends Exception { // Define a class named ExceptionManager that extends the Exception class

    private static final long serialVersionUID = 1L; // A serialVersionUID for versioning

    /**
     * Default constructor for ExceptionManager. Creates an ExceptionManager
     * without a specific message.
     */
    public ExceptionManager() {
        super(); // Call the constructor of the parent class (Exception)

    }

    /**
     * Constructor for ExceptionManager with a custom error message.
     *
     * @param message The custom error message to associate with this exception.
     */
    public ExceptionManager(String message) {
        super(message); // Call the constructor of the parent class (Exception) with a custom message

    }
}
