package xmlgraphparser;

public class XEdge {
	
	@Override
	public String toString() {
		return "XEdge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}

	String from;
	String to;
	double weight;
	
	public XEdge(String from, String to, double weight) {
		this.from=from;
		this.to=to;
		this.weight=weight;
	}
}
