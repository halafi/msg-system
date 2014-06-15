package fh.msgsystem.exceptions;

import javax.persistence.PersistenceException;

/**
 *
 * @author Filip
 */
public class NonExistingEntityException extends PersistenceException  {

    public NonExistingEntityException() {
    }

    public NonExistingEntityException(String message) {
        super(message);
    }

    public NonExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingEntityException(Throwable cause) {
        super(cause);
    }
    
}
