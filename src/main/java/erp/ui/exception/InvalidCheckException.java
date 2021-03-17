package erp.ui.exception;

//checked는 try-catch를 강제하기 때문에 RunTimeException으로 받아옴
public class InvalidCheckException extends RuntimeException {

	public InvalidCheckException() {
		super("공백이 존재합니다.");
	}

	public InvalidCheckException(Throwable cause) {
		super("공백이 존재합니다.", cause);
	}

	public InvalidCheckException(String message) {
		super(message);
	}

	
}
