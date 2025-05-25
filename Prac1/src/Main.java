public class Main
{
    public static void main(String[] args)
    {
        RecursiveArray one = new RecursiveArray();
        RecursiveArray two = new RecursiveArray("1,2,3,4,5");
        RecursiveArray three = new RecursiveArray("5,4,3,2,1");
        RecursiveArray four = new RecursiveArray("1,1,1,1");

        String str1 = one.toString();
        System.out.println(str1);
        String str2 = two.toString();
        System.out.println(str2);
        String str3 = three.toString();
        System.out.println(str3);

        two.append(6);
        str2 = two.toString();
        System.out.println(str2);

        two.prepend(0);
        str2 = two.toString();
        System.out.println(str2);

        one.contains(4);
        two.contains(3);
        two.contains(8);

        one.isAscending();
        two.isAscending();
        three.isAscending();
        four.isAscending();

        one.isDescending();
        two.isDescending();
        three.isDescending();
        four.isDescending();

        two.sortAscending();
        four.sortAscending();
        one.sortAscending();
        three.sortAscending();

        three.sortDescending();
        four.sortDescending();
        one.sortDescending();
        two.sortDescending();

    }
}