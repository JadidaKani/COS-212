class AdjacencyList<T> implements GraphImplementation<T> {

    public LinkedList<Node<T>> nodes;

    /*
     * Constructor
     */
    public AdjacencyList() {
        this.nodes = new LinkedList<>();
    }

    /*
     * Public methods to adhere to the interface
     */
    @Override
    public void addNode(T data)
    {
        if(!containsNode(data))
        {
            Node<T> newNode = new Node<>(data);
            nodes.add(newNode);
        }
    }

    @Override
    public void addEdge(T dataFrom, T dataTo)
    {
        if(containsNode(dataFrom))
        {
            if(containsNode(dataTo))
            {
                if(!isConnected(dataFrom,dataTo))
                {
                    Node<T> currentNode1 = nodes.get(indexOfNode(dataFrom));
                    Node<T> currentNode2 = nodes.get(indexOfNode(dataTo));

                    currentNode1.addEdge(currentNode2);
                }
            }
        }

    }

    public void removeNode(T data)
    {
       if(containsNode(data))
       {
           Node<T> currentNode = nodes.get(indexOfNode(data));
           if (currentNode != null)
           {
               nodes.remove(currentNode);
               int count = 0;
               while (count < nodes.size())
               {
                   Node<T> temp = nodes.get(count);
                   temp.removeEdge(currentNode);
                   count++;
               }
           }
       }

    }

    public void removeEdge(T dataFrom, T dataTo)
    {
          if(containsNode(dataFrom))
          {
              if(containsNode(dataTo))
              {
                  if(isConnected(dataFrom,dataTo))
                  {
                      Node<T> currentNode1 = nodes.get(indexOfNode(dataFrom));
                      Node<T> currentNode2 = nodes.get(indexOfNode(dataTo));
                      currentNode1.removeEdge(currentNode2);
                  }
              }
          }

    }

    @Override
    public int numberOfNodes()
    {
        //System.out.println(nodes.size());
        return nodes.size();
    }

    @Override
    public int numberOfEdges()
    {
        int count = 0;
        int ind = 0;
        while (ind < nodes.size())
        {
            Node<T> node = nodes.get(ind);
            count += node.getEdges().size();
            ind++;
        }

        //System.out.println(count);
        return count;
    }

    @Override
    public boolean containsNode(T data)
    {
        int count = 0;
        while (count < nodes.size())
        {
            Node<T> temp = nodes.get(count);
            if (temp.data.equals(data))
            {
                //System.out.println("true");
                return true;
            }
            count++;
        }

        //System.out.println("false");
        return false;
    }

    @Override
    public int indexOfNode(T data)
    {
        int ind = 0;
        while (ind < nodes.size())
        {
            Node<T> temp = nodes.get(ind);
            if (temp.data.equals(data))
            {
                return ind;
            }
            ind++;
        }
        return -1;
    }

    @Override
    public T nodeAtIndex(int index)
    {
        if (index >= 0 && index < nodes.size())
        {
            Node<T> temp = nodes.get(index);
            //System.out.println(temp.data);
            return temp.data;
        }
        return null;
    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo)
    {
        if((!containsNode(dataFrom)))
        {
            //System.out.println("false isConnected");
            return false;
        }
        if((!containsNode(dataTo)))
        {
            //System.out.println("false isConnected");
            return false;
        }

        Node<T> currentNode = nodes.get(indexOfNode(dataFrom));
        LinkedList<Node<T>> edges = currentNode.getEdges();

        for (int j = 0; j < edges.size(); j++)
        {
            if(edges.get(j).data == dataTo)
            {
                //System.out.println("true isConnected");
                return true;
            }
        }

        //System.out.println("false isConnected");
        return false;
    }

    @Override
    public LinkedList<T> getNeighbors(T data)
    {
        Node<T> currentNode = nodes.get(indexOfNode(data));
        LinkedList<Node<T>> edges = currentNode.getEdges();
        LinkedList<T> neighbors = new LinkedList<>();

        int count = 0;
        while (count < edges.size())
        {
            Node<T> temp = edges.get(count);
            neighbors.add(temp.data);
            count++;
        }

//        System.out.println("Neighbors of "+data+" : ");
//        int k = 0;
//        while (k < neighbors.size())
//        {
//            T neighbor = neighbors.get(k);
//            System.out.print(neighbor + " ");
//            k++;
//        }
//        System.out.println();

        return neighbors;
    }

    @Override
    public String display() {
        String out = ("Graph structure:\n");
        for (int i = 0; i < nodes.size(); i++) {
            Node<T> currentNode = nodes.get(i);

            StringBuilder displayString = new StringBuilder("\t" + currentNode.data.toString() + ": ");
            LinkedList<Node<T>> edges = currentNode.getEdges();

            for (int j = 0; j < edges.size(); j++) {
                displayString.append(edges.get(j).data.toString());
                if (j < edges.size() - 1) {
                    displayString.append(", ");
                }
            }

            out += (displayString + "\n");
        }
        return out;
    }

    /*
     * Private methods used for this implementation
     */

    /*
     * Inner nodes used for this implementation
     */
    private class Node<U> {
        public U data;
        public LinkedList<Node<U>> edges;

        public Node(U data) {
            this.data = data;
            this.edges = new LinkedList<>();
        }

        public LinkedList<Node<U>> getEdges() {
            return this.edges;
        }

        public void addEdge(Node<U> newConnection) {
            this.edges.add(newConnection);
        }

        public void removeEdge(Node<U> oldConnection) {
            this.edges.remove(oldConnection);
        }

    }
}
