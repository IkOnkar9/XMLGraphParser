package xmlgraphparser;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class Graph implements interfaces.Graph<Graph> {
	String label;
	Set<Graph> nodes;
	Set<XEdge> edges;
	DirectedWeightedPseudograph<String, DefaultWeightedEdge> graphObject;
	
	// Parameterized Constructor
	public Graph() {
		this.label = "Default Root";
		this.edges = new LinkedHashSet<>();
		this.nodes = new LinkedHashSet<>();
		graphObject = new DirectedWeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}

	public Graph(String label) {
		this.label = label;
		this.edges = new LinkedHashSet<>();
		this.nodes = new LinkedHashSet<>();
		graphObject = new DirectedWeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

	}

	@Override
	public Collection<Graph> getLabels() {
		Set<String> allVertexs =  this.graphObject.vertexSet();
		Set<Graph> allLabels =  new HashSet<Graph>();
		for(String eachVertex : allVertexs) {
			Graph eachLabel = new Graph(eachVertex);
			allLabels.add(eachLabel);
		}
		return allLabels;
	}

	// I am assuming it will Print all Vertexs
	@Override
	public void forEachLabel(Consumer<? super Graph> visitor) {
		Set<String> allVertexs =  this.graphObject.vertexSet();
		for(String eachVertex : allVertexs) {
			System.out.println(eachVertex);
		}
	}
	// I am assuming it will Print all Edges
	@Override
	public void forEachNeighbor(Graph graphNode, EdgeConsumer<? super Graph> visitor) {
		Set<DefaultWeightedEdge> allEdges =  this.graphObject.edgeSet();
		for(DefaultWeightedEdge eachEdge : allEdges) {
			String source = graphNode.graphObject.getEdgeSource(eachEdge);
			String target = graphNode.graphObject.getEdgeTarget(eachEdge);
			double weight = graphNode.graphObject.getEdgeWeight(eachEdge);
			//Avoiding Self Loops
			if(weight!=0.0)
			 System.out.println("Vertex : "+source+" is connected to : "+target+" , Weight = "+weight);
		}
	}

	@Override
	public double getWeight(Graph from, Graph to) {
		DefaultWeightedEdge edge = graphObject.getEdge(from.label, to.label);
		double weight = graphObject.getEdgeWeight(edge);
		return weight;
	}
}
