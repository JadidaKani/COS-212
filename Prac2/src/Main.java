public class Main
{
    public static void main(String[] args)
    {
        BST tree1 = new BST();
        BST tree2 = new BST();
        BST tree3 = new BST();

        tree3.insert(200);
        tree1.insert(100);
        tree1.insert(50);
        tree1.insert(150);
        tree1.insert(25);
        tree1.insert(75);
        tree1.insert(125);
        tree1.insert(175);
        tree1.insert(15);
        tree1.insert(60);
        tree1.insert(140);
        tree1.insert(190);
        tree1.insert(190);
        tree1.insert(10);
        String one = tree1.toString();
        System.out.println(one);

        tree1.contains(10);
        tree1.contains(175);
        tree1.contains(200);

        System.out.println(tree1.printSearchPath(10));
        System.out.println(tree1.printSearchPath(110));
        System.out.println(tree1.printSearchPath(175));

        tree1.findMax();
        tree1.findMin();
//        System.out.println(node1.data);
//        System.out.println(node2.data);

        tree1.getNode(75);
        tree1.getNode(9);
        tree1.getNode(150);

        tree1.getNumLeaves();
        tree2.getNumLeaves();
        tree3.getNumLeaves();

        tree1.getHeight();
        tree2.getHeight();
        tree3.getHeight();

        tree1.isSuperficiallyBalanced();
        tree2.isSuperficiallyBalanced();
        tree3.isSuperficiallyBalanced();
        //tree1.delete(10);

        BST tree4 = tree1.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println(tree4.toString());
        BST tree5 = tree2.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println(tree5.toString());
        BST tree6 = tree3.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println(tree6.toString());

        tree2.insert(100);
        tree2.insert(50);
        tree2.insert(150);
        tree2.insert(125);
        tree2.insert(175);
        tree2.insert(75);
        tree2.insert(140);
        tree2.insert(190);
        tree2.insert(60);
        tree2.insert(180);
        tree2.insert(200);
        System.out.println(tree2.toString());

        BST tree7 = tree2.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println(tree7.toString());

        tree1.delete(10);
        tree1.delete(125);
        tree1.delete(25);
        tree1.delete(150);
        tree1.delete(50);

    }
}
