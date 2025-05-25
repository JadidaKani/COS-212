public class SplayTree
{
    public Node root; //if tree is empty this should be null
    /*
     * The functions below this line was given
     */

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "Empty Tree" : "{" + toStringOneLine(root) + "}");
    }

    public String toStringOneLine(Node node) {
        return node.toString()
                + (node.left == null ? "{}" : "{" + toStringOneLine(node.left) + "}")
                + (node.right == null ? "{}" : "{" + toStringOneLine(node.right) + "}");
    }

    public SplayTree() {
        root = null;
    }

    /*
     * The functions above this line was given
     */

    //===============helper functions========================

    public static int studentNumber(String str)
    {
        int start = str.indexOf("u") + 1;
        int end = str.indexOf(":", start);
        return Integer.parseInt(str.substring(start, end));
    }

    public static int studentMark(String str) {
        int start = str.indexOf(":") + 1;
        int end = str.indexOf("%", start);
        return Integer.parseInt(str.substring(start, end));
    }
    public Node buildLeftTree(String str, Node n)
    {
        String leftTree = str.substring(1, str.length() - 1);//get rid of the first and last "{}"
        int num = studentNumber(leftTree);
        int mark = studentMark(leftTree);
        leftTree = leftTree.replace("[u" + num + ":" + mark + "%]", "");
        Node node = new Node(num,mark);
        n = node;

        String left = "";
        String right = "";
        int numOpen = 0;
        int numClosed = 0;
        int index = 0;

        if(leftTree.equals("{}{}"))
        {
            n.left = null;
            n.right = null;
            return n;
        }
        else
        {
            for (int i = 0; i < leftTree.length(); i++)
            {
                if (leftTree.charAt(i) == '{')
                {
                    numOpen++;
                }
                else if (leftTree.charAt(i) == '}')
                {
                    numClosed++;
                }

                if(numClosed != numOpen)
                {
                    left += leftTree.charAt(i);
                    index ++;
                }
                else if(numClosed == numOpen)
                {
                    left += leftTree.charAt(i);
                    index ++;
                    break;
                }
            }
            right = leftTree.substring(index);
            if(!left.equals("{}"))
            {
                n.left = buildLeftTree(left,n.left);
            }
            if(!right.equals("{}"))
            {
                n.right = buildRightTree(right,n.right);
            }
        }

        return n;
    }

    public Node buildRightTree(String str, Node n)
    {
        String rightTree = str.substring(1, str.length() - 1);//get rid of the first and last "{}"
        int num = studentNumber(rightTree);
        int mark = studentMark(rightTree);
        rightTree = rightTree.replace("[u" + num + ":" + mark + "%]", "");
        Node node = new Node(num,mark);
        n = node;
        String left = "";
        String right = "";
        int numOpen = 0;
        int numClosed = 0;
        int index = 0;

        if(rightTree.equals("{}{}"))
        {
            n.left = null;
            n.right = null;
            return n;
        }
        else
        {
            for (int i = 0; i < rightTree.length(); i++)
            {
                if (rightTree.charAt(i) == '{')
                {
                    numOpen++;
                }
                else if (rightTree.charAt(i) == '}')
                {
                    numClosed++;
                }

                if(numClosed != numOpen)
                {
                    left += rightTree.charAt(i);
                    index ++;
                }
                else if(numClosed == numOpen)
                {
                    left += rightTree.charAt(i);
                    index ++;
                    break;
                }
            }
            right = rightTree.substring(index);
            if(!left.equals("{}"))
            {
                n.left = buildLeftTree(left,n.left);
            }
            if(!right.equals("{}"))
            {
                n.right = buildRightTree(right,n.right);
            }
        }

        return n;
    }

    public Node search(int num, Node node)
    {
        if (node == null)
        {
            return null;
        }
        else if(node.studentNumber > num)//obj1 > obj2
        {
            return search(num,node.left);
        }
        else if(node.studentNumber < num)
        {
            return search(num,node.right);
        }
        else //if (node.data == el)
        {
            return node;
        }
    }

    public Node splay(Node node, int num)
    {
        if(node.studentNumber == num)
        {
            return node;
        }

        if(node.studentNumber > num)
        {
            if(node.left == null)
            {
                return node;
            }

            //Zig-Zig (left,left)
            if (num < node.left.studentNumber)
            {
                node.left.left = splay(node.left.left, num);
                node = rightRotate(node);
            }
            //Zig-Zag (left,right)
            else if (num > node.left.studentNumber)
            {
                node.left.right = splay(node.left.right, num);
                if (node.left.right != null)
                {
                    node.left = leftRotate(node.left);
                }
            }

            if (node.left == null)
            {
                return node;
            }
            else
            {
                return rightRotate(node);
            }
        }
        else if(node.studentNumber < num)
        {
            if(node.right == null)
            {
                return node;
            }

            // Zig-Zig (right,right)
            if (num > node.right.studentNumber)
            {
                node.right.right = splay(node.right.right, num);
                node = leftRotate(node);
            }

            //Zig-zag (right,left)
            else if (num < node.right.studentNumber)
            {
                node.right.left = splay(node.right.left, num);
                if (node.right.left != null)
                {
                    node.right = rightRotate(node.right);
                }
            }

            if (node.right == null)
            {
                return node;
            }
            else
            {
                return leftRotate(node);
            }
        }
        return node;
    }

    public Node rightRotate(Node node)
    {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    public Node leftRotate(Node node)
    {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }

    public Node insertNode(int num, Node node, Integer m)
    {
        if (node == null)
        {
            return new Node(num,m);
        }

        if(node.studentNumber == num)
        {
            return null;
        }
        else if (node.studentNumber > num) //node.data > el
        {
            node.left = insertNode(num, node.left, m);
        }
        else if (node.studentNumber < num) //node.data < el
        {
            node.right = insertNode(num, node.right, m);
        }

        return node;
    }

    public Node maxNode(Node node)
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

    public String ascStudentNumLeft(String str, Node node)
    {
        if(node == null)
        {
            return str;
        }
        else
        {
            if(!str.contains(node.toString()))
            {
                str += node.toString();
            }

            if(node.left != null)
            {
                str = ascStudentNumLeft(str,node.left);
            }
        }
        return str;
    }

    public String ascStudentNumRight(String str, Node node)
    {
        if(node == null)
        {
            return str;
        }
        else
        {
            if(!str.contains(node.toString()))
            {
                str += node.toString();
            }
            if(node.right != null)
            {
                str = ascStudentNumRight(str,node.right);
            }
        }
        return str;
    }

    //=======================================================

    public SplayTree(String input)
    {
        if(input.equals("Empty Tree"))
        {
            root = null;
        }
        else
        {
            String tree = input.substring(1, input.length() - 1);//get rid of the first and last "{}"
            int num = studentNumber(tree);
            int mark = studentMark(tree);
            tree = tree.replace("[u" + num + ":" + mark + "%]", "");
            Node node = new Node(num,mark);
            root = node;
            String left = "";
            String right = "";
            int numOpen = 0;
            int numClosed = 0;
            int index = 0;

            if(tree.equals("{}{}"))
            {
               root.left = null;
               root.right = null;
            }
            else
            {
                for (int i = 0; i < tree.length(); i++)
                {
                    if (tree.charAt(i) == '{')
                    {
                        numOpen++;
                    }
                    else if (tree.charAt(i) == '}')
                    {
                        numClosed++;
                    }

                    if(numClosed != numOpen)
                    {
                        left += tree.charAt(i);
                        index ++;
                    }
                    else if(numClosed == numOpen)
                    {
                        left += tree.charAt(i);
                        index ++;
                        break;
                    }
                }
                right = tree.substring(index);
                if(!left.equals("{}"))
                {
                    root.left = buildLeftTree(left,root.left);
                }
                if(!right.equals("{}"))
                {
                    root.right = buildRightTree(right,root.right);
                }
            }

        }
    }

    public Node access(int studentNumber)
    {
        return access(studentNumber,null);
    }

    public Node access(int studentNumber, Integer mark)
    {
        if(root == null)
        {
            root = insertNode(studentNumber,root,mark);
            return root;
        }

        Node found = search(studentNumber,root);
        if(found == null)
        {
            root = insertNode(studentNumber,root,mark);
        }
        else
        {
            if(mark != null)
            {
                found.mark = mark;
            }
        }
        root = splay(root,studentNumber);
        return root;
    }

    public Node remove(int studentNumber)
    {
        root = access(studentNumber,null);

        if((root.left) == null && (root.right == null))
        {
            root = null;
        }
        else if (root.left == null)
        {
            root = root.right;
        }
        else
        {
            Node left = root.left;
            root = splay(root.left, maxNode(root.left).studentNumber);
            root.right = left.right;
        }

        return root;
    }

    public String sortByStudentNumber()
    {
        if(root == null)
        {
            return "Empty Tree";
        }
        String leftLeft = "";
        String rightLeft = "";
        String rightRight = "";
        String leftRight = "";

        leftLeft = ascStudentNumLeft(leftLeft,root.left);
        leftLeft += root.toString();
        rightLeft = ascStudentNumRight(rightLeft,root.left.right);
        leftLeft += rightLeft;
        rightRight = ascStudentNumLeft(rightRight,root.right);
        leftLeft += rightRight;
        leftRight = ascStudentNumLeft(rightRight,root.right.left);
        leftLeft += leftRight ;


        return leftLeft;
    }

    public String sortByMark()
    {
        if(root == null)
        {
            return "Empty Tree";
        }

       return "";
    }
}
