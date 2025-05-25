
public class AdjacencyMatrix<T> implements GraphImplementation<T> {
    public LinkedList<T> nodes; // Stores the nodes to maintain index mapping
    public boolean[][] matrix; // Adjacency matrix to store edge information

    /*
     * Constructor
     */
    public AdjacencyMatrix() {
        nodes = new LinkedList<>();
        matrix = new boolean[0][0];
    }

    /*
     * Public methods to adhere to the interface
     */
    @Override
    public void addNode(T data)
    {
        if(!containsNode(data))
        {
            nodes.add(data);
            int tempSize = nodes.size();
            boolean[][] tempMatrix = new boolean[tempSize][tempSize];

            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j < matrix[i].length; j++)
                {
                    tempMatrix[i][j] = matrix[i][j];
                }
            }

            matrix = tempMatrix;
        }

    }

    @Override
    public void addEdge(T dataFrom, T dataTo)
    {
        int index1 = nodes.indexOf(dataFrom);
        int index2 = nodes.indexOf(dataTo);

        if(containsNode(dataFrom))
        {
            if(containsNode(dataTo))
            {
                if(!isConnected(dataFrom,dataTo))
                {
                    matrix[index1][index2] = true;
                }
            }
        }

    }

    public void removeNode(T data)
    {
        int index = nodes.indexOf(data);
        if (index != -1)
        {
            nodes.removeIndex(index);
            int tempSize = nodes.size();
            boolean[][] tempMatrix = new boolean[tempSize][tempSize];

            for (int i = 0, new_i = 0; i < matrix.length; i++)
            {
                if (i == index) continue;
                for (int j = 0, new_j = 0; j < matrix[i].length; j++)
                {
                    if (j == index) continue;
                    tempMatrix[new_i][new_j] = matrix[i][j];
                    new_j++;
                }
                new_i++;
            }

            matrix = tempMatrix;
        }
    }

    public void removeEdge(T dataFrom, T dataTo)
    {
        int index1 = nodes.indexOf(dataFrom);
        int index2 = nodes.indexOf(dataTo);
        if (index1 != -1 && index2 != -1 && matrix[index1][index2])
        {
            matrix[index1][index2] = false;
            //matrix[toIndex][fromIndex] = false; // For undirected graph
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
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (matrix[i][j])
                {
                    count++;
                }
            }
        }

        //System.out.println(count);
        return count;
    }

    @Override
    public boolean containsNode(T data)
    {
        if(nodes.indexOf(data) != -1)
        {
            //System.out.println("true");
        }
        else
        {
            //System.out.println("false");
        }
        return nodes.indexOf(data) != -1;
    }

    @Override
    public int indexOfNode(T data)
    {
        return nodes.indexOf(data);
    }

    @Override
    public T nodeAtIndex(int index)
    {
        if (index >= 0 && index < nodes.size())
        {
            //System.out.println(nodes.get(index));
            return nodes.get(index);
        }

        return null;
    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo)
    {
        int index1 = nodes.indexOf(dataFrom);
        int index2 = nodes.indexOf(dataTo);

        if(index1 == -1)
        {
            //System.out.println("false isConnected");
            return false;
        }
        if(index2 == -1)
        {
            //System.out.println("false isConnected");
            return false;
        }
        else if(matrix[index1][index2])
        {
            //System.out.println("true isConnected");
           return true;
        }

        //System.out.println("false isConnected");
        return false;
    }

    @Override
    public LinkedList<T> getNeighbors(T data)
    {
        LinkedList<T> neighbours = new LinkedList<>();
        int ind = nodes.indexOf(data);
        if (ind != -1)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                if (matrix[ind][j])
                {
                    neighbours.add(nodes.get(j));
                }
            }
        }

//        System.out.println("Neighbors of "+data+" : ");
//        int k = 0;
//        while (k < neighbours.size())
//        {
//            T neighbor = neighbours.get(k);
//            System.out.print(neighbor + " ");
//            k++;
//        }

        return neighbours;
    }

    @Override
    public String display() {
        String out = ("Adjacency Matrix:\n");
        for (int i = 0; i < matrix.length; i++) {
            out += "\t" + nodes.get(i) + ": ";
            for (int j = 0; j < matrix[i].length; j++) {
                out += (matrix[i][j] ? "1 " : "0 ");
            }
            out += "\n";
        }
        return out;
    }

    /*
     * Private methods used for this implementation
     */

}
