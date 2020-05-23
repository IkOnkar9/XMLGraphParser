package xmlgraphparser;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;


public class GraphBuilder implements interfaces.GraphBuilder<Graph> {
	Graph root;
	Set<Graph> vertexNodes;
	Set<XEdge> edges;
	public GraphBuilder(Graph root) {
		this.root = root; 
		this.edges = new LinkedHashSet<>();
		this.vertexNodes = new LinkedHashSet<>();
	}

	@Override
	public GraphBuilder addNode(Graph label) {
		
		this.vertexNodes.add(label);
		return this;
	}

	@Override
	public GraphBuilder addEdge(Graph from, Graph to, double weight) {
		XEdge edge = new XEdge(from, to, weight);
		this.edges.add(edge);
		return this;
	}

	@Override
	public Graph buildGraph() {
		
		for (Graph eachVertex : vertexNodes) {
			root.nodes.add(eachVertex);
			root.graphObject.addVertex(eachVertex.label);
		}
		for (XEdge eachEdge : edges) {
			root.edges.add(eachEdge);
			DefaultWeightedEdge edge =  root.graphObject.addEdge(eachEdge.from.label, eachEdge.to.label);
			root.graphObject.setEdgeWeight(edge, eachEdge.weight);
		}
		return root;
	}
}
