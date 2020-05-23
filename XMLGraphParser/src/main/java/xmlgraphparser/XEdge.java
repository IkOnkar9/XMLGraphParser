package xmlgraphparser;

public class XEdge {
	
	@Override
	public String toString() {
		return "XEdge [from=" + from.label + ", to=" + to.label + ", weight=" + weight + "]";
	}

	Graph from;
	Graph to;
	double weight;
	
	public XEdge(Graph from, Graph to, double weight) {
		this.from=from;
		this.to=to;
		this.weight=weight;
	}
}
