public class RedBlackTree<T extends Comparable<T>> {

    /*
     * Sentinel is not the root. Go check the text book if this doesn't make sense
     */
    public RedBlackNode<T> SENTINEL; //root will always be on the right of the sentinel node
    public RedBlackNode<T> NULL_NODE; //want to know if a node is "null" we compare against this node

    public static final int RED = 1;
    public static final int BLACK = 0;

    public RedBlackTree()
    {
        SENTINEL = new RedBlackNode<T>(null);
        SENTINEL.left = null;
        SENTINEL.right = null;

        NULL_NODE = new RedBlackNode<T>(null);
        NULL_NODE.left = null;
        NULL_NODE.right = null;
    }

    public RedBlackNode<T> getRoot()
    {
        if(SENTINEL.right == null)
        {
            return NULL_NODE;
        }
        return SENTINEL.right;
    }

    public void bottomUpInsert(T data)
    {
        if(!search(data, SENTINEL.right))
        {
            RedBlackNode<T> newNode = new RedBlackNode<>(data);
            if (SENTINEL.right == NULL_NODE)
            {
                SENTINEL.right = newNode;
                newNode.left = NULL_NODE;
                newNode.right = NULL_NODE;
            }
            else
            {
                newNode.colour = RED;
                SENTINEL.right = insertNode(data,SENTINEL.right);
                SENTINEL.right = fixProperties(newNode);
            }
        }

    }

    public boolean search(T el, RedBlackNode<T> node)
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

    public RedBlackNode<T> insertNode(T el, RedBlackNode<T> node)
    {
        if (node == null)
        {
            return new RedBlackNode<>(el);
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

    public RedBlackNode<T> myParent(RedBlackNode<T> child)
    {
        if (child == null || child == SENTINEL.right) {
            return null;
        }

        RedBlackNode<T> parent = null;
        RedBlackNode<T> node = SENTINEL.right;

        while (node != NULL_NODE)
        {
            if (node.left == child || node.right == child)
            {
                parent = node;
                break;
            }
            else if (child.data.compareTo(node.data) < 0)
            {
                node = node.left;
            }
            else if (child.data.compareTo(node.data) > 0)
            {
                node = node.right;
            }
        }

        return parent;
    }

    public RedBlackNode<T> fixProperties(RedBlackNode<T> child)
    {
        RedBlackNode<T> parent = myParent(child);
        if (parent == null)
        {
            child.colour = BLACK;
            return child;
        }

        if (parent.colour == BLACK)
        {
            return parent;
        }

        RedBlackNode<T> grandParent = myParent(parent);

        if (grandParent == null)
        {
            parent.colour = BLACK;
            return parent;
        }

        RedBlackNode<T> uncle = myUncle(grandParent,parent);

        if (uncle != null && uncle.colour == RED)
        {
            parent.colour = BLACK;
            grandParent.colour = RED;
            uncle.colour = BLACK;
            return fixProperties(grandParent);
        }
        else if (grandParent.left == parent) // parent == grandParent.left
        {
            if (child == parent.right) // zig-zag(triangle)
            {
                leftRotate(parent);
                parent = child;
            }

            rightRotate(grandParent); // zig-zig(line)
            parent.colour = BLACK;
            grandParent.colour = RED;

            return grandParent;
        }
        else if(grandParent.right == parent) // parent == grandParent.left
        {
            if (child == parent.left)
            {
                rightRotate(parent);
                parent = child;
            }

            leftRotate(grandParent);
            parent.colour = BLACK;
            grandParent.colour = RED;

            return grandParent;
        }

        return null;
    }

    public RedBlackNode<T> myUncle(RedBlackNode<T> grandParent, RedBlackNode<T> parent)
    {
        if(grandParent.right == parent)
        {
            return grandParent.left;
        }
        else if (grandParent.left == parent)
        {
            return grandParent.right;
        }

        return null;
    }

    public boolean isValidRedBlackTree()
    {
        if(SENTINEL.right == NULL_NODE)
        {
            return true;
        }
        else if(SENTINEL.right.colour == BLACK)
        {
            return isValid(SENTINEL.right);
        }
        else
        {
            return false;
        }
    }

    public boolean isValid(RedBlackNode<T> root)
    {
        if(root == NULL_NODE)
        {
            return true;
        }

        if((root.colour != RED) && (root.colour != BLACK))
        {
            return false;
        }

        if(root.colour == RED)
        {
            if((root.right.colour != BLACK) || (root.left.colour != BLACK))
            {
                return false;
            }
        }

        int leftBlackHeight = blackHeight(root.left);
        int rightBlackHeight = blackHeight(root.right);
        if (leftBlackHeight != rightBlackHeight)
        {
            return false;
        }

        return isValid(root.left) && isValid(root.right);
    }

    public int blackHeight(RedBlackNode<T> node)
    {
        if (node == NULL_NODE)
        {
            return 1;
        }

        int leftH = blackHeight(node.left);
        int rightH = blackHeight(node.right);

        if(leftH == rightH)
        {
            if(node.colour == BLACK)
            {
                return leftH + 1;
            }
            else
            {
                return leftH;
            }
        }
        else
        {
            return -1;
        }
    }

    public void topDownDelete(T data)
    {

    }

    /* -------------------------------------------------------------------------- */
    /* Private methods, which shouldn't be called from outside the class */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I worked really hard to make it pretty. */
    /* Also, it matches the website. -------------------------------------------- */
    /* Also, also, we might test against it ;) ---------------------------------- */
    /* -------------------------------------------------------------------------- */
    private StringBuilder toString(RedBlackNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != NULL_NODE) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");
        if (node.left != NULL_NODE) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return SENTINEL.right == NULL_NODE ? "Empty tree"
                : toString(SENTINEL.right, new StringBuilder(), true, new StringBuilder()).toString();
    }

    public String toVis() {
        return toVisHelper(getRoot());
    }

    private String toVisHelper(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return "{}";
        }
        String leftStr = toVisHelper(node.left);
        String rightStr = toVisHelper(node.right);
        return "{" + node.toString() + leftStr + rightStr + "}";
    }

}
