package interfaces;

import java.io.IOException;
import java.util.function.Function;

public interface GraphPrinter {
	
	<T> void print(Graph<T> graph, Appendable appendable, Function<? super T, String> formatter) throws IOException ;
	
	default <T> void printGraph(Graph<T> graph, Appendable appendable) throws IOException {
		print(graph, appendable, Object::toString);
	}
}
