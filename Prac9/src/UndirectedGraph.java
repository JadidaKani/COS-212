
public class UndirectedGraph<T> extends Graph<T>
{
    public UndirectedGraph(GraphImplementation<T> impl)
    {
        super(impl);
    }

    @Override
    public void addEdge(T from, T to)
    {
        impl.addEdge(to,from);
        impl.addEdge(from,to);
    }

    @Override
    public void removeEdge(T from, T to)
    {
        impl.removeEdge(to,from);
        impl.removeEdge(from,to);
    }
}
