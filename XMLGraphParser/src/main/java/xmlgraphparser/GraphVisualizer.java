package xmlgraphparser;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

public class GraphVisualizer extends JApplet {
	private static final long serialVersionUID = 2202072534703043194L;
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
	private JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter;

	public void init(DirectedWeightedPseudograph<String, DefaultWeightedEdge> graph) {
		// create a JGraphT graph
		ListenableGraph<String, DefaultEdge> g = new DefaultListenableGraph<>(
				new DefaultDirectedGraph<>(DefaultEdge.class));

		// create a visualization using JGraph, via an adapter
		jgxAdapter = new JGraphXAdapter<>(graph);
		setPreferredSize(DEFAULT_SIZE);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		resize(DEFAULT_SIZE);

		// positioning via jgraphx layouts
		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
		// center the circle
		int radius = 100;
		layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
		layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
		layout.setRadius(radius);
		layout.setMoveCircle(true);
		layout.execute(jgxAdapter.getDefaultParent());
	}

	public void showGraph(DirectedWeightedPseudograph<String, DefaultWeightedEdge> graph) {

		GraphVisualizer applet = new GraphVisualizer();
		applet.init(graph);
		JFrame frame = new JFrame();
		frame.getContentPane().add(applet);
		frame.setTitle("JGraphT Adapter to JGraphX Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
