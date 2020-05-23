package interfaces;

import java.io.IOException;
import java.io.InputStream;

public interface GraphParser<T> {

	Graph<T> parse(InputStream inputStream) throws ParserException, IOException;
	
	
	public static class ParserException extends Exception {
		private static final long serialVersionUID = 34476666614434933L;

		public ParserException() {
			super();
		}

		public ParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public ParserException(String message, Throwable cause) {
			super(message, cause);
		}

		public ParserException(String message) {
			super(message);
		}

		public ParserException(Throwable cause) {
			super(cause);
		}
	}
}