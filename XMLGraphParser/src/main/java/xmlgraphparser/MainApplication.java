package xmlgraphparser;

import java.io.InputStream;
import java.util.Scanner;

public class MainApplication {

	public static void main(String[] args) throws Exception {

		InputStream inputStream = null;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		// test
		try (Scanner scannerObject = new Scanner(System.in)) {

			System.out.println("Please select, Which file should be loaded:\n\n"
					+ "Type : 1        --- For smallGraph.xml\n" + "Type : 2        --- For mediumGraph.xml\n"
					+ "Type : 3        --- For largeGraph.xml\n" + "Type : 4        --- For hugeGraph.xml\n");

			 int graphChoice = scannerObject.nextInt();
			switch (graphChoice) {
			case 1:
				inputStream = classloader.getResourceAsStream("smallGraph.xml");
				break;
			case 2:
				inputStream = classloader.getResourceAsStream("mediumGraph.xml");
				break;
			case 3:
				inputStream = classloader.getResourceAsStream("largeGraph.xml");
				break;
			case 4:
				inputStream = classloader.getResourceAsStream("hugeGraph.xml");
				break;
			}

			XMLGraph graph = null;
			// Creating Parser Object
			XMLGraphParser parser = new XMLGraphParser();
			
			// Generating Graph Object from Given XML
			graph = parser.parse(inputStream);
			
			// Printing Graph Data on Console and jFrame (In Graphics) as well
			XMLGraphPrinter printer = new XMLGraphPrinter();
			printer.printGraph(graph, null);
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
