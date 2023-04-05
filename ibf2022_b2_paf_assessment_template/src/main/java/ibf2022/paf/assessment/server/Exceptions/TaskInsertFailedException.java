package ibf2022.paf.assessment.server.Exceptions;

public class TaskInsertFailedException extends RuntimeException{
    
    public TaskInsertFailedException(String message) {
        super(message);
    }
}
