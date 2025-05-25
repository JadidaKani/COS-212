public class Main {
    public static void main(String[] args) throws Exception {

        TestAdjList();
        TestAdjMatrix();

        // Undirected Graph
        GraphTesting(new UndirectedGraph<>(new AdjacencyList<>()));

        // Directed Graph
        GraphTesting(new DirectedGraph<>(new AdjacencyList<>()));

        RemoveNodeTesting();
        RemoveEdgeTesting();
        NumEdgesTest();

    }


    private static void TestAdjList() {
        AdjacencyList<String> list = new AdjacencyList<>();

        // addNode
        list.addNode("A");
        list.addNode("B");
        list.addNode("C");
        list.addNode("D");
        System.out.println(list.display());

        // numberOfNodes
        System.out.println(list.numberOfNodes());

        // addEdge
        list.addEdge("A", "B");
        list.addEdge("A", "C");
        list.addEdge("B", "C");
        list.addEdge("C", "D");
        System.out.println(list.display());

        // numberOfEdges
        System.out.println(list.numberOfEdges());

        // containsNode
        System.out.println(list.containsNode("A"));
        System.out.println(list.containsNode("E"));

        // indexOfNode
        System.out.println(list.indexOfNode("C"));
        System.out.println(list.indexOfNode("E"));

        // nodeAtIndex
        System.out.println(list.nodeAtIndex(1));

        // isConnected
        System.out.println(list.isConnected("A", "B"));
        System.out.println(list.isConnected("A", "D"));

        // getNeighbors
        LinkedList<String> neighbours = list.getNeighbors("A");
        for (int i = 0; i < neighbours.size(); i++)
        {
            System.out.print(neighbours.get(i) + " ");
        }
        System.out.println();

        // removeEdge
        list.removeEdge("A", "C");
        System.out.println(list.display());

        // removeNode
        list.removeNode("C");
        System.out.println(list.display());

        // number of nodes and edges
        System.out.println(list.numberOfNodes());
        System.out.println(list.numberOfEdges());
    }


    private static void TestAdjMatrix() {
        AdjacencyMatrix<String> matrix = new AdjacencyMatrix<>();

        // addNode
        matrix.addNode("A");
        matrix.addNode("B");
        matrix.addNode("C");
        matrix.addNode("D");
        System.out.println(matrix.display());

        // addEdge
        matrix.addEdge("A", "B");
        matrix.addEdge("A", "C");
        matrix.addEdge("B", "C");
        matrix.addEdge("C", "D");
        System.out.println(matrix.display());

        // removeNode
        matrix.removeNode("C");
        System.out.println(matrix.display());

        // removeEdge
        matrix.removeEdge("A", "B");
        System.out.println(matrix.display());

        // numberOfNodes
        System.out.println(matrix.numberOfNodes());

        // numberOfEdges
        System.out.println(matrix.numberOfEdges());

        // containsNode
        System.out.println(matrix.containsNode("A"));
        System.out.println(matrix.containsNode("E"));

        // indexOfNode
        System.out.println(matrix.indexOfNode("B"));

        // nodeAtIndex
        System.out.println(matrix.nodeAtIndex(0));

        // isConnected
        System.out.println(matrix.isConnected("A", "B"));
        System.out.println(matrix.isConnected("A", "D"));

        // getNeighbors
        System.out.println(matrix.getNeighbors("A"));
        System.out.println(matrix.display());
    }


    private static void GraphTesting(Graph<Integer> theGraph) {
        // addNode
        theGraph.addNode(2);
        theGraph.addNode(4);
        theGraph.addNode(6);
        theGraph.addNode(8);

        // Adding edges
        theGraph.addEdge(2, 4);
        theGraph.addEdge(2, 6);
        theGraph.addEdge(4, 8);
        theGraph.addEdge(4, 2);
        theGraph.addEdge(6, 4);
        theGraph.addEdge(8, 6);

        theGraph.display();

        // BFS
        LinkedList<Integer> bfsResult = theGraph.bfs(2);
        bfsResult.printList();

        // DFS
        LinkedList<Integer> dfsResult = theGraph.dfs(2);
        dfsResult.printList();

        // unweighted shortest path
        LinkedList<Integer> shortestPath = theGraph.unweightedShortestPath(2, 8);
        shortestPath.printList();

        // Removing edges
        theGraph.removeEdge(2, 4);
        theGraph.removeEdge(6, 4);

        theGraph.display();

        // Removing nodes
        theGraph.removeNode(8);
        theGraph.removeNode(10);

        theGraph.display();
    }

    private static void RemoveNodeTesting() {
        AdjacencyMatrix<Integer> myGraph = new AdjacencyMatrix<>();
        myGraph.addNode(2);
        myGraph.addNode(4);
        myGraph.addNode(6);
        myGraph.addEdge(2, 4);
        myGraph.addEdge(4, 6);

        System.out.println(myGraph.display());

        // Remove node 2
        myGraph.removeNode(4);
        System.out.println(myGraph.display());
    }

    private static void RemoveEdgeTesting() {
        AdjacencyMatrix<Integer> myGraph = new AdjacencyMatrix<>();
        myGraph.addNode(2);
        myGraph.addNode(4);
        myGraph.addNode(6);
        myGraph.addEdge(2, 4);
        myGraph.addEdge(4, 6);

        System.out.println(myGraph.display());

        myGraph.removeEdge(2, 4);
        System.out.println(myGraph.display());
    }

    private static void NumEdgesTest() {
        AdjacencyMatrix<Integer> myGraph = new AdjacencyMatrix<>();
        myGraph.addNode(2);
        myGraph.addNode(4);
        myGraph.addNode(6);
        myGraph.addEdge(2, 4);
        myGraph.addEdge(4, 6);

        System.out.println(myGraph.numberOfEdges());
        myGraph.removeEdge(1, 2);
        System.out.println(myGraph.numberOfEdges());
    }
}