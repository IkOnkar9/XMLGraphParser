package xmlgraphparser;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import interfaces.Graph;

public class XMLGraph implements Graph<String> {
	String label;
	Set<String> nodes;
	Set<XEdge> edges;
	DirectedWeightedPseudograph<String, DefaultWeightedEdge> graphObject;

	// Parameterized Constructor
	public XMLGraph() {
		this.label = "Default Root";
		this.edges = new LinkedHashSet<>();
		this.nodes = new LinkedHashSet<>();
		graphObject = new DirectedWeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}

	public XMLGraph(String label) {
		this.label = label;
		this.edges = new LinkedHashSet<>();
		this.nodes = new LinkedHashSet<>();
		graphObject = new DirectedWeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

	}

	@Override
	public Collection<String> getLabels() {
		Set<String> allVertexs = this.graphObject.vertexSet();
		Set<String> allLabels = new HashSet<String>();
		for (String eachVertex : allVertexs) {
			allLabels.add(eachVertex);
		}
		return allLabels;
	}

	// Print all Vertexs by calling visitor.accept()
	@Override
	public void forEachLabel(Consumer<? super String> visitor) {
		Set<String> allVertexs = (Set<String>) this.getLabels();
		for (String eachVertex : allVertexs) {
			visitor.accept(eachVertex);
		}
	}

	// Print all the Edges associated with the provided Graph Node(Vertex) as a Parameter
	@Override
	public void forEachNeighbor(String graphNode, EdgeConsumer<? super String> visitor) {
		// visitor.accept(eachVertex);

		Set<DefaultWeightedEdge> allEdges = this.graphObject.edgesOf(graphNode);
		for (DefaultWeightedEdge eachEdge : allEdges) {
			String source = this.graphObject.getEdgeSource(eachEdge);
			String target = this.graphObject.getEdgeTarget(eachEdge);
			double weight = this.graphObject.getEdgeWeight(eachEdge);
			// Avoiding Self Loops
			if (weight != 0.0)
				visitor.accept(source, target, weight);
		}
		System.out.println("");

	}

	@Override
	public double getWeight(String from, String to) {
		DefaultWeightedEdge edge = graphObject.getEdge(from, to);
		double weight = graphObject.getEdgeWeight(edge);
		return weight;
	}
}

class EdgeConsumer implements Graph.EdgeConsumer<String> {
	@Override
	public void accept(String from, String to, double weight) {
		System.out.println("Vertex : " + from + " -(is connected to)-> Vertex : " + to + " , Weight = " + weight);
	}
}
