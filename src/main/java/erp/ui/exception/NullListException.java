package erp.ui.exception;

@SuppressWarnings("serial")
public class NullListException extends RuntimeException {

	public NullListException() {
		super("해당 사원이 없습니다.");
	}

	public NullListException(Throwable cause) {
		super("해당 사원이 없습니다.", cause);
	}

}
