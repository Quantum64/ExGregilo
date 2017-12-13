package co.q64.exgregilo.api.link;

public class MalformedLinkException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MalformedLinkException(String message) {
		super(message);
	}

	public MalformedLinkException(String message, Throwable cause) {
		super(message, cause);
	}

	public MalformedLinkException(Throwable cause) {
		super(cause);
	}
}
