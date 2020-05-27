package xmlgraphparser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import interfaces.GraphParser;

public class XMLGraphParser implements GraphParser<String> {

	@Override
	public XMLGraph parse(InputStream inputStream) throws ParserException, IOException {

		XMLGraph graph = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName("node");
			graph = new XMLGraph("XML_2_Graph");
			XMLGraphBuilder graphBuilder = new XMLGraphBuilder(graph);
			// Traverse through Each Vertexs
			for (int eachNodeIndex = 0; eachNodeIndex < nodeList.getLength(); eachNodeIndex++) {
				Node graphNode = (Node) nodeList.item(eachNodeIndex);
				Element graphNodeElement = (Element) graphNode;
				XMLGraph fromGraphNode = new XMLGraph(graphNodeElement.getAttribute("label"));
				// Adding vertex to GraphBuilder
				graphBuilder = graphBuilder.addNode(fromGraphNode.label);
				// Traverse through all edges in Each Vertex Nodes according to given XML Schema
				NodeList edgeList = graphNodeElement.getElementsByTagName("edge");
				for (int eachEdgeIndex = 0; eachEdgeIndex < edgeList.getLength(); eachEdgeIndex++) {
					Node eachEdge = (Node) edgeList.item(eachEdgeIndex);
					Element edgeElement = (Element) eachEdge;
					XMLGraph toGraphNode = new XMLGraph(edgeElement.getAttribute("to"));
					String weight = edgeElement.getAttribute("weight");
					// Adding Edge to GraphBuilder
					graphBuilder = graphBuilder.addEdge(fromGraphNode.label, toGraphNode.label, Double.parseDouble(weight));
				}
			}
			// Building Graph
			graph = graphBuilder.buildGraph();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}

}
