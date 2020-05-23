package interfaces;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Represents an unmodifiable, reflexive, directed graph with strictly positive edge weights.
 * @param <T> The type of the labels. Each label uniquely identifies a node in the context of a
 * graph and must never be null.
 */
public interface Graph<T> {
	/**
	 * @return A collection containing all labels of nodes that belong to this graph structure.
	 */
	Collection<T> getLabels();
	
	void forEachLabel(Consumer<? super T> visitor);
	
	/**
	 * Calls visitor for each node combination that is adjacent excluding self loops
	 * @param node The start node
	 * @param visitor The callback method that accepts the edges
	 */
	void forEachNeighbor(T node, EdgeConsumer<? super T> visitor);
	
	/**
	 * Returns the weight of the edge from <b>from</b> to <b>to</b>.
	 * @param from The label of the start node
	 * @param to The label of the target node
	 * @return The weight of the edge from <b>from</b> to <b>to</b>, if there is one,<br />
	 * infinity, if there is none or<br /> 0 if <b>from</b> and <b>to</b> refer to the same node.
	 */
	double getWeight(T from, T to);
	
	
	public interface EdgeConsumer<T> {
		void accept(T from, T to, double weight);
	}
}
