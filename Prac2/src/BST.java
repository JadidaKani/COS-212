public class BST<T extends Comparable<T>> {

    public BinaryNode<T> root; //root node

    //************** helper functions *******************

    public boolean search(T el, BinaryNode<T> node)
    {
        if (node == null)
        {
            //System.out.println("false");
            return false;
        }
        else if(node.data.compareTo(el) > 0)//obj1.compareTo(obj2) > 0, obj1 > obj2
        {
            return search(el, node.left);
        }
        else if(node.data.compareTo(el) < 0)
        {
            return search(el,node.right);
        }
        else
        {
            //System.out.println("true");
            return true;
        }
    }

    public BinaryNode<T> insertNode(T el, BinaryNode<T> node)
    {
        if (node == null)
        {
            return new BinaryNode<>(el);
        }

        if(node.data == el)
        {
            return null;
        }
        else if (node.data.compareTo(el) > 0) //node.data > el
        {
            node.left = insertNode(el, node.left);
        }
        else if (node.data.compareTo(el) < 0) //node.data < el
        {
            node.right = insertNode(el, node.right);
        }

        return node;
    }

    public BinaryNode<T> deleteNode(T el, BinaryNode<T> node)
    {
        if (node == null)
        {
            return node;
        }

        if (node.data.compareTo(el) > 0) //node.data > el
        {
            node.left = deleteNode(el, node.left);
        }
        else if (node.data.compareTo(el) < 0) //node.data < el
        {
            node.right = deleteNode(el, node.right);
        }
        else //node is found
        {
            //1 child
            if (node.left == null)
            {
                return node.right;
            }
            else if (node.right == null)
            {
                return node.left;
            }

            //2 child
            node.data = smallest(node.right);
            node.right = deleteNode(node.data, node.right);
        }

        return node;
    }

    public T smallest(BinaryNode<T> n)
    {
        if(n.left != null)
        {
          return smallest(n.left);
        }
        else
        {
            return n.data;
        }
    }

    public String pathFinder(T el, BinaryNode<T> node,String str)
    {
        if (node == null)
        {
            str += "Null";
            return str;
        }
        else if(node.data.compareTo(el) > 0)//obj1 > obj2
        {
            str += node.data + " -> ";
            return pathFinder(el,node.left,str);
        }
        else if(node.data.compareTo(el) < 0)
        {
            str += node.data + " -> ";
            return pathFinder(el,node.right,str);
        }
        else //if (node.data == el)
        {
            str += node.data;
            return str;
        }
    }

    public BinaryNode<T> maxNode(BinaryNode<T> node)
    {
        if(node == null || node.right == null)
        {
            return node;
        }
        else
        {
            return maxNode(node.right);
        }
    }

    public BinaryNode<T> minNode(BinaryNode<T> node)
    {
        if(node == null || node.left == null)
        {
            return node;
        }
        else
        {
            return minNode(node.left);
        }
    }

    public BinaryNode<T> findNode(T el, BinaryNode<T> node)
    {
        if (node == null)
        {
            //System.out.println("null");
            return null;
        }
        else if(node.data.compareTo(el) > 0)//obj1 > obj2
        {
            return findNode(el,node.left);
        }
        else if(node.data.compareTo(el) < 0)
        {
            return findNode(el,node.right);
        }
        else //if (node.data == el)
        {
            return node;
        }
    }

    public int numLeaves(BinaryNode<T> node)
    {
        if(node == null)
        {
            return 0;
        }
        else if ((node.right == null) && (node.left == null))
        {
           return 1;
        }
        else
        {
            return (numLeaves(node.left) + numLeaves(node.right));
        }
    }

    public int treeHeight(BinaryNode<T> node)
    {
        if(node == null)
        {
            return 0;
        }
        else if ((node.right == null) && (node.left == null))
        {
            return 1;
        }

        int lHeight = treeHeight(node.left);
        int rHeight = treeHeight(node.right);

        if(lHeight > rHeight)
        {
            return lHeight+1;
        }
        else if(lHeight < rHeight)
        {
            return rHeight+1;
        }
        else
        {
            return rHeight+1;
        }
    }

    public int numRight(BinaryNode<T> node) //number of nodes in right subtree
    {
        if(node == null)
        {
            return 0;
        }
        if((node.right == null) && (node.left == null))
        {
            return 0;
        }

        return 1+ numRight(node.right) + numRight(node.right.left);
    }

    public int numLeft(BinaryNode<T> node) //number of nodes in left subtree
    {
        if(node == null)
        {
            return 0;
        }
        if((node.right == null) && (node.left == null))
        {
            return 0;
        }

        return 1+ numLeft(node.left) + numLeft(node.left.right);
    }

    public int numNodes(BinaryNode<T> node)
    {
        if (node == null)
        {
            return 0;
        }
        return 1 + numNodes(node.left) + numNodes(node.right);
    }

    public BinaryNode<T> findSubTree(BinaryNode<T> node)
    {
        if(node == null)
        {
            return node;
        }

        int numL = numNodes(node.left);
        int numR = numNodes(node.right);
        //System.out.println(numR);

        if(numL == numR)
        {
            return node;
        }

        if (node.left != null)
        {
            return findSubTree(node.left);
        }
        else if(node.right != null)
        {
            return findSubTree(node.right);
        }

        return null;
    }

    public BinaryNode<T> copySubTree(BinaryNode<T> node)
    {
        if(node == null)
        {
            return null;
        }

        BinaryNode<T> newNode = new BinaryNode<>(node.data);
        newNode.left = copySubTree(node.left);
        newNode.right = copySubTree(node.right);
        return newNode;
    }

    //****************************************************

    public BST()
    {
        root = null;
    }

    public void delete(T data)
    {
        root = deleteNode(data, root);
    }

    public boolean contains(T data)
    {
        return search(data, root);
    }

    public void insert(T data)
    {
        boolean found = search(data, root);
        if(!found)
        {
            root = insertNode(data, root);
        }
    }

    public int getHeight()
    {
        return treeHeight(root);
    }

    public String printSearchPath(T data)
    {
        String str = "";
        return pathFinder(data, root,str);
    }

    public int getNumLeaves()
    {
        return numLeaves(root);
    }

    public BST<T> extractBiggestSuperficiallyBalancedSubTree()
    {
        BST tree = new BST();
        BST treeL = new BST();
        BST treeR = new BST();

        if(root == null)
        {
            return tree;
        }

        int numR = numNodes(root.right);
        int numL = numNodes(root.left);
//        System.out.println("R:"+numR);
//        System.out.println("L:"+numL);

        if(numR == numL)
        {
            tree.root = copySubTree(root);
            return tree;
        }

        treeR.root = copySubTree(findSubTree(root.right));
        treeL.root = copySubTree(findSubTree(root.left));

        numR = numNodes(treeR.root);
        numL = numNodes(treeL.root);

//        System.out.println("R:"+numR);
//        System.out.println("L:"+numL);

        if(numR > numL)
        {
            return treeR;
        }
        else if(numR < numL)
        {
            return treeL;
        }
        else if(numR == numL)
        {
            return treeL;
        }

        return tree;
    }

    public BinaryNode<T> getNode(T data)
    {
        return findNode(data, root);
    }

    public boolean isSuperficiallyBalanced()
    {
        int nodeL = numLeft(root);
        int nodeR = numRight(root);

        if(nodeL == nodeR)
        {
            return true;
        }

        return false;
    }

    public BinaryNode<T> findMax()
    {
        BinaryNode<T> max = maxNode(root);
        return max;
    }

    public BinaryNode<T> findMin()
    {
        BinaryNode<T> min = minNode(root);
        return min;
    }

    ///////////////

    private StringBuilder toString(BinaryNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.data.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return root == null ? "Empty tree" : toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }

}
