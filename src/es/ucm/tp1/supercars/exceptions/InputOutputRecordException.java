package es.ucm.tp1.supercars.exceptions;

public class InputOutputRecordException extends GameException {

	private static final long serialVersionUID = 1L;
	
	public InputOutputRecordException() {
		super();
	}
	
	public InputOutputRecordException(String message) {
		super(message);
	}
	
	public InputOutputRecordException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public InputOutputRecordException(Throwable cause) {
        super(cause);
    }
	
	protected InputOutputRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}