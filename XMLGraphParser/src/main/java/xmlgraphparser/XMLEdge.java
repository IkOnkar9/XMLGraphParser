package xmlgraphparser;

public class XMLEdge {
	
	@Override
	public String toString() {
		return "XEdge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}

	String from;
	String to;
	double weight;
	
	public XMLEdge(String from, String to, double weight) {
		this.from=from;
		this.to=to;
		this.weight=weight;
	}
}
