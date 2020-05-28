package xmlgraphparser;

import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jgrapht.graph.DefaultWeightedEdge;

import interfaces.Graph;
import interfaces.GraphPrinter;

public class XMLGraphPrinter implements GraphPrinter {

	@Override
	public <T> void print(Graph<T> graph, Appendable appendable, Function<? super T, String> formatter)
			throws IOException {
		XMLGraph completeGraphObject = new XMLGraph();
		completeGraphObject = (XMLGraph) graph;
		System.out.println("\n"+completeGraphObject.label);

		// Shows all Vertexs
		System.out.println("\nWriting all Vertexs\n");
		Consumer<String> consumer = t -> System.out.println(t);
		completeGraphObject.forEachLabel(consumer);

		// Traverse all vertexes and print all of its associated Edges one by one
		System.out.println("\nWriting all Edges\n");
		printEachVertexEdges(completeGraphObject);
		

		// If you only want to print all its Edges without printing each Node and Edge relationship, then please uncomment "printOnlyEdges(completeGraphObject)",
		// and comment the "printEachVertexEdges(completeGraphObject)" above.

		//printOnlyEdges(completeGraphObject);

		// Call for Graphic Visualization
		XMLGraphVisualizer applet = new XMLGraphVisualizer();
		applet.showGraph(completeGraphObject.graphObject);
		
		System.out.print("All Nodes and Edges are Printed");

	}

	private void printOnlyEdges(XMLGraph completeGraphObject) {
		Set<DefaultWeightedEdge> allEdges = completeGraphObject.graphObject.edgeSet();
		for (DefaultWeightedEdge eachEdge : allEdges) {
			String source = completeGraphObject.graphObject.getEdgeSource(eachEdge);
			String target = completeGraphObject.graphObject.getEdgeTarget(eachEdge);
			double weight = completeGraphObject.graphObject.getEdgeWeight(eachEdge);
			// Avoiding Self Loops
			if (weight != 0.0)
				System.out.println("Vertex : " + source + " -(is connected to)-> Vertex : " + target + " , Weight = " + weight);
		}
	}

	private void printEachVertexEdges(XMLGraph completeGraphObject) {
		EdgeConsumer edgeConsumer = new EdgeConsumer();
		Set<String> allVertexs = completeGraphObject.graphObject.vertexSet();
		for (String eachVertex : allVertexs) {
			System.out.println("All Edges Associated With Vertex : "+eachVertex);
			completeGraphObject.forEachNeighbor(eachVertex, edgeConsumer);
		}

	}
}