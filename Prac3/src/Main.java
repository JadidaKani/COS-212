public class Main
{
    public static void main(String[] args)
    {
        SplayTree empty = new SplayTree();
        SplayTree one = new SplayTree("Empty Tree");
        SplayTree two = new SplayTree("{[u10:50%]{}{}}");
        SplayTree three = new SplayTree("{[u10:50%]{[u5:40%]{}{}}{}}");
        SplayTree four = new SplayTree("{[u10:50%]{[u5:40%]{}{}}{[u15:60%]{}{}}}");
        SplayTree five = new SplayTree("{[u10:50%]{[u5:40%]{[u6:75%]{}{}}{[u11:75%]{}{}}}{[u15:60%]{[u214:65%]{}{}}{[u100:75%]{}{}}}}");

        System.out.println(one.toString());
        System.out.println(two.toString());
        System.out.println(three.toString());
        System.out.println(four.toString());
        System.out.println(five.toString());

        five.access(15);
        System.out.println(five.toString());
        five.access(15,95);
        System.out.println(five.toString());
        five.access(10,60);
        System.out.println(five.toString());
        five.access(20,45);
        System.out.println(five.toString());

        System.out.println(five.sortByStudentNumber());
        System.out.println(empty.sortByStudentNumber());

        System.out.println(five.sortByMark());
        System.out.println(empty.sortByMark());

        five.remove(5);
        System.out.println(five.toString());

    }
}