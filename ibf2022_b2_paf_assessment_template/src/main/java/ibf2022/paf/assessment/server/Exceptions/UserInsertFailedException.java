package ibf2022.paf.assessment.server.Exceptions;

public class UserInsertFailedException extends RuntimeException{
    public UserInsertFailedException(String message) {
        super(message);
    }
}
