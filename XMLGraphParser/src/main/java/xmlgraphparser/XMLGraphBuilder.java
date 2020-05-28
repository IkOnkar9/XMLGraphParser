package xmlgraphparser;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;

import interfaces.GraphBuilder;


public class XMLGraphBuilder implements GraphBuilder<String> {
	XMLGraph root;
	Set<String> vertexNodes;
	Set<XMLEdge> edges;
	public XMLGraphBuilder(XMLGraph root) {
		this.root = root; 
		this.edges = new LinkedHashSet<>();
		this.vertexNodes = new LinkedHashSet<>();
	}

	@Override
	public XMLGraphBuilder addNode(String label) {
		
		this.vertexNodes.add(label);
		return this;
	}

	@Override
	public XMLGraphBuilder addEdge(String from, String to, double weight) {
		XMLEdge edge = new XMLEdge(from, to, weight);
		this.edges.add(edge);
		return this;
	}

	@Override
	public XMLGraph buildGraph() {
		
		for (String eachVertex : vertexNodes) {
			root.nodes.add(eachVertex);
			root.graphObject.addVertex(eachVertex);
		}
		for (XMLEdge eachEdge : edges) {
			root.edges.add(eachEdge);
			DefaultWeightedEdge edge =  root.graphObject.addEdge(eachEdge.from, eachEdge.to);
			root.graphObject.setEdgeWeight(edge, eachEdge.weight);
		}
		return root;
	}
}
