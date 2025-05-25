public class Main
{
    public static void main(String[] args)
    {
        //PrimeNumberGenerator
        PrimeNumberGenerator gen1 = new PrimeNumberGenerator();
        System.out.println(gen1.toString());
        int head = gen1.currentPrime();
        System.out.println(head);

        for(int i=0; i<10; i++)
        {
            gen1.nextPrime();
            System.out.println(gen1.toString());
        }
        head = gen1.currentPrime();
        System.out.println(head);

        //Hashmap
        Hashmap map1 = new Hashmap();
        System.out.println(map1.toString());
        System.out.println(map1.toStringOneLine());

        Hashmap map2 = new Hashmap("21[-,-,u15:25%,u5:45%,-,-,u120:85%,u10:10%,-,-]");
        System.out.println(map2.toString());
        System.out.println(map2.toStringOneLine());

        int hashNum1 = map1.hash(120);
        System.out.println(hashNum1);
        int hashNum2 = map2.hash(120);
        System.out.println(hashNum2);

        Hashmap map3 = new Hashmap("11[u5:51%,u30:50%,-,u32:59%,-,u34:65%,u20:60%,-,-,-,-,u10:90%,u74:76%,u27:39%,-,u120:40%]");
        System.out.println(map3.toString());
        System.out.println();
        map3.search(120);
        map3.search(1);

        map3.insert(32,65);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(11,75);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(22,45);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(65,95);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(85,85);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(105,25);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(60,13);
        System.out.println(map3.toString());
        System.out.println();
        map3.insert(18,28);
        System.out.println(map3.toString());
        System.out.println();

        map3.remove(18);
        System.out.println(map3.toString());
        System.out.println();
        map3.remove(18);
        System.out.println(map3.toString());
        System.out.println();

        Hashmap map4 = new Hashmap("2[u5:51%]");
        System.out.println(map4.toString());
        System.out.println();
        map4.insert(333333333,65);
        System.out.println(map4.toString());
        System.out.println();

    }
}