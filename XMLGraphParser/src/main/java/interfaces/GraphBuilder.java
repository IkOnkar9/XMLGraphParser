package interfaces;

/**
 * Builder class that is used to construct graph instances.
 */
public interface GraphBuilder<T> {
	
	GraphBuilder<T> addNode(T label);
	
	GraphBuilder<T> addEdge(T from, T to, double weight);
	
	Graph<T> buildGraph();
	
	
	public static class GraphBuildException extends RuntimeException {
		private static final long serialVersionUID = -7263226618211155125L;

		public GraphBuildException() {
			super();
		}

		public GraphBuildException(String message, Throwable cause) {
			super(message, cause);
		}

		public GraphBuildException(String message) {
			super(message);
		}

		public GraphBuildException(Throwable cause) {
			super(cause);
		}
	}
}
