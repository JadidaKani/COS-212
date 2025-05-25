public abstract class Heap<T extends Comparable<T>> {

    public Comparable<T>[] data; //array that holds all the elements
    public int size; //number of elements in the heap

    //---------------------------helper functions --------------------------
    public void swap(int i, int j)
    {
        Comparable<T> temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void reorderL(int index)
    {
        int largest = index;
        int child1 = 2 * index + 1;
        int child2 = 2 * index + 2;

        if (child1 < size && data[child1].compareTo((T) data[largest]) > 0)
        {
            largest = child1;
        }

        if (child2 < size && data[child2].compareTo((T) data[largest]) > 0)
        {
            largest = child2;
        }

        if (largest != index)
        {
            swap(index, largest);
            reorderL(largest);
        }
    }

    public void reorderS(int index)
    {
        int smallest = index;
        int child1 = 2 * index + 1;
        int child2 = 2 * index + 2;

        if (child1 < size && data[child1].compareTo((T) data[smallest]) < 0)
        {
            smallest = child1;
        }

        if (child2 < size && data[child2].compareTo((T) data[smallest]) < 0)
        {
            smallest = child2;
        }

        if (smallest != index)
        {
            swap(index, smallest);
            reorderS(smallest);
        }
    }

    //----------------------------------------------------------------------

    public Heap()
    {
        this.data = (Comparable<T>[]) new Comparable[2];
        size = 0;
    }

    public Heap(T[] array)
    {
        data = (Comparable<T>[]) new Comparable[array.length];
        size = 0;

        for (int i = 0; i < array.length; i++)
        {
            if(array[i] != null)
            {
                size += 1;
            }
            data[i] = array[i];
        }

        int lastIndex = data.length -1;
        int count = array.length;

        if(this.getClass() == MaxHeap.class) {
            while (count > 0) {
                int i = (lastIndex - 1) / 2;
                while ((i >= 0)) {
                    Comparable<T> d = data[i];
                    int child1 = 2 * i + 1;
                    int child2 = 2 * i + 2;
                    boolean one = false;
                    boolean notleaf = false;
                    boolean both = false;

                    if (child1 < array.length) {
                        if (data[child1] != null) {
                            one = true;
                            notleaf = true;
                        }
                    }

                    if (child2 < array.length) {
                        if (data[child2] != null) {
                            both = true;
                        }
                    }

                    while (notleaf) {
                        if (both) {
                            if ((d.compareTo((T) data[child1]) < 0) && (d.compareTo((T) data[child2]) < 0)) {
                                Comparable<T> c1 = data[child1];
                                Comparable<T> c2 = data[child2];

                                if (c1.compareTo((T) c2) < 0) {
                                    swap(i, child2);
                                    d = data[i];
                                } else {
                                    swap(i, child1);
                                    d = data[i];
                                }
                            } else if ((d.compareTo((T) data[child1]) < 0)) {
                                swap(i, child1);
                                d = data[i];
                            } else if ((d.compareTo((T) data[child2]) < 0)) {
                                swap(i, child2);
                                d = data[i];
                            } else {
                                break;
                            }
                        } else if (one && (d.compareTo((T) data[child1]) < 0)) {
                            swap(i, child1);
                            d = data[i];
                        } else {
                            break;
                        }

                    }
                    i--;
                }
                count--;
            }
        }

        count = array.length;

        if(this.getClass() == MinHeap.class) {
            while (count > 0) {
                int i = (lastIndex - 1) / 2;
                while ((i >= 0)) {
                    Comparable<T> d = data[i];
                    int child1 = 2 * i + 1;
                    int child2 = 2 * i + 2;
                    boolean one = false;
                    boolean notleaf = false;
                    boolean both = false;

                    if (child1 < array.length) {
                        if (data[child1] != null) {
                            one = true;
                            notleaf = true;
                        }
                    }

                    if (child2 < array.length) {
                        if (data[child2] != null) {
                            both = true;
                        }
                    }

                    while (notleaf) {
                        if (both) {
                            if ((d.compareTo((T) data[child1]) > 0) && (d.compareTo((T) data[child2]) > 0)) {
                                Comparable<T> c1 = data[child1];
                                Comparable<T> c2 = data[child2];

                                if (c1.compareTo((T) c2) > 0) {
                                    swap(i, child2);
                                    d = data[i];
                                } else {
                                    swap(i, child1);
                                    d = data[i];
                                }
                            } else if ((d.compareTo((T) data[child1]) > 0)) {
                                swap(i, child1);
                                d = data[i];
                            } else if ((d.compareTo((T) data[child2]) > 0)) {
                                swap(i, child2);
                                d = data[i];
                            } else {
                                break;
                            }
                        } else if (one && (d.compareTo((T) data[child1]) > 0)) {
                            swap(i, child1);
                            d = data[i];
                        } else {
                            break;
                        }

                    }
                    i--;
                }
                count--;
            }
        }
    }

    protected abstract boolean compare(Comparable<T> child, Comparable<T> parent);

    public void push(T item) {
        if (size == data.length) {
            Comparable<T>[] temp = (Comparable<T>[]) new Comparable[data.length * 2];
            for (int j = 0; j < data.length; j++) {
                temp[j] = data[j];
            }

            data = (Comparable<T>[]) new Comparable[temp.length];
            for (int j = 0; j < temp.length; j++) {
                data[j] = temp[j];
            }
        }

        data[size] = item;
        size++;

        int index = size-1;
        int parent = (index-1)/2;

        if(getClass() == MaxHeap.class) {
            while (index > 0 && (data[index].compareTo((T) data[parent]) > 0)) {
                swap(index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        if(getClass() == MinHeap.class) {
            while (index > 0 && (data[index].compareTo((T) data[parent]) < 0)) {
                swap(index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

    }

    public Comparable<T> pop()
    {
        if (size == 0)
        {
            return null;
        }

        Comparable<T> dequeued = data[0];
        data[0] = data[size - 1];
        data[size-1] = null;
        size--;

        if(getClass() == MaxHeap.class)
        {
            reorderL(0);
        }

        if(getClass() == MinHeap.class)
        {
            reorderS(0);
        }

        return dequeued;
    }

    public Comparable<T> peek()
    {
        return data[0];
    }

    /*
     * 
     * Functions used for the toString
     * Do not change them but feel free to use them
     * 
     */

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(0, "", true, sb); // Start from the root
        return sb.toString();
    }

    private void buildString(int index, String prefix, boolean isTail, StringBuilder sb) {
        if (index < size) {
            String linePrefix = isTail ? "└── " : "┌── ";
            if (getRightChildIndex(index) < size) { // Check if there is a right child
                buildString(getRightChildIndex(index), prefix + (isTail ? "|   " : "    "), false, sb);
            }
            sb.append(prefix).append(linePrefix).append(data[index]).append("\n");
            if (getLeftChildIndex(index) < size) { // Check if there is a left child
                buildString(getLeftChildIndex(index), prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

}
