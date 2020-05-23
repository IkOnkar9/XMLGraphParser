package xmlgraphparser;

import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

import org.jgrapht.graph.DefaultWeightedEdge;

public class GraphPrinter implements interfaces.GraphPrinter {

	@Override
	public <T> void print(interfaces.Graph<T> graph, Appendable appendable, Function<? super T, String> formatter)
			throws IOException {
		Graph completeGraphObject = new Graph();
		completeGraphObject = (Graph) graph;
		System.out.println(completeGraphObject.label);
		// Shows all Vertexs
		System.out.println("Writing all Vertexs");
		completeGraphObject.forEachLabel(null);
		// Shows all Edges
		System.out.println("Writing all Edges");
		completeGraphObject.forEachNeighbor(completeGraphObject, null);
		//Call for Graphic Visualization
		GraphVisualizer applet = new GraphVisualizer(); 
		applet.showGraph(completeGraphObject.graphObject);
	}
}