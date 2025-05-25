public abstract class Graph<T> {
    public GraphImplementation<T> impl;

    Graph(GraphImplementation<T> impl) {
        this.impl = impl;
    }

    public void addNode(T node)
    {
        if (!impl.containsNode(node))
        {
            impl.addNode(node);
        }
    }

    abstract void addEdge(T from, T to);

    public void removeNode(T node)
    {
        if (impl.containsNode(node))
        {
            impl.removeNode(node);
        }
    }

    abstract void removeEdge(T from, T to);

    public void display()
    {
        System.out.println(impl.display());
    }

    public LinkedList<T> bfs(T startNode)
    {
        LinkedList<T> visited = new LinkedList<>();
        LinkedList<T> queue = new LinkedList<>();

        queue.add(startNode);

        while (queue.size() > 0)
        {
            T currNode = queue.get(0);
            queue.removeIndex(0);

            if (visited.indexOf(currNode) == -1)
            {
                visited.add(currNode);
                LinkedList<T> neighbours = impl.getNeighbors(currNode);

                int i = 0;
                while (i < neighbours.size())
                {
                    T neighbor = neighbours.get(i);
                    if (visited.indexOf(neighbor) == -1)
                    {
                        queue.add(neighbor);
                    }
                    i++;
                }
            }

        }

        return visited;
    }

    public LinkedList<T> dfs(T startNode)
    {
        LinkedList<T> visited = new LinkedList<>();
        LinkedList<T> stack = new LinkedList<>();

        stack.add(startNode);

        while (stack.size() > 0)
        {
            T currNode = stack.get(stack.size()-1);
            stack.removeIndex(stack.size()-1);

            if (visited.indexOf(currNode) == -1)
            {
                visited.add(currNode);
                LinkedList<T> neighbours = impl.getNeighbors(currNode);

                int i = neighbours.size() - 1;
                while (i >= 0)
                {
                    T neighbor = neighbours.get(i);
                    if (visited.indexOf(neighbor) == -1)
                    {
                        stack.add(neighbor);
                    }
                    i--;
                }
            }
        }

        return visited;
    }

    public LinkedList<T> unweightedShortestPath(T startNode, T endNode)
    {
        LinkedList<T> queue = new LinkedList<>();
        LinkedList<T> visitedList = new LinkedList<>();
        LinkedList<T> prevNodes = new LinkedList<>();
        LinkedList<T> nodes = new LinkedList<>();

        queue.add(startNode);
        visitedList.add(startNode);
        prevNodes.add(null);
        nodes.add(startNode);

        while (queue.size() > 0)
        {
            T currNode = queue.get(0);
            queue.removeIndex(0);

            if (currNode.equals(endNode))
            {
                return constructPath(prevNodes, nodes, startNode, endNode);
            }

            LinkedList<T> neighbors = impl.getNeighbors(currNode);
            int i = 0;
            while (i < neighbors.size())
            {
                T neighbour = neighbors.get(i);
                if (visitedList.indexOf(neighbour) == -1)
                {
                    visitedList.add(neighbour);
                    queue.add(neighbour);
                    prevNodes.add(currNode);
                    nodes.add(neighbour);
                }
                i++;
            }
        }

        return null;
    }

    //helper function
    private LinkedList<T> constructPath(LinkedList<T> prevN, LinkedList<T> nodes, T startN, T endN)
    {
        LinkedList<T> path = new LinkedList<>();
        T currNode = endN;

        while (!currNode.equals(startN))
        {
            path.add(currNode);
            int currInd = nodes.indexOf(currNode);
            currNode = prevN.get(currInd);
        }
        path.add(startN);

        LinkedList<T> revPath = new LinkedList<>();
        for (int i = path.size() - 1; i >= 0; i--)
        {
            revPath.add(path.get(i));
        }

        return revPath;
    }


}
