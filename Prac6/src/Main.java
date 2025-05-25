
public class Main
{
    public static void main(String[] args) throws Exception
    {
        Integer[] array = {4,6,3,7,5,4,1,8};

        //MaxHeap
        MaxHeap<Integer> max1 = new MaxHeap<>();
        MaxHeap<Integer> max2 = new MaxHeap<>(array);
        System.out.println(max2.toString());

        Comparable<Integer> maxChild = 13;
        Comparable<Integer> maxParent = 8;
        max2.compare(maxChild,maxParent);

        max2.push(10);
        System.out.println(max2.toString());
        max2.push(2);
        System.out.println(max2.toString());
        max2.push(6);
        System.out.println(max2.toString());

        max1.pop();
        max2.pop();
        System.out.println(max2.toString());
        max2.pop();
        System.out.println(max2.toString());

        max1.peek();
        max2.peek();
        System.out.println(max1.peek());
        System.out.println(max2.peek());



        //MinHeap
        MinHeap<Integer> min1 = new MinHeap<>();
        MinHeap<Integer> min2 = new MinHeap<>(array);
        System.out.println(min2.toString());

        Comparable<Integer> minChild = 8;
        Comparable<Integer> minParent = 13;
        min2.compare(minChild,minParent);

        min2.push(10);
        System.out.println(min2.toString());
        min2.push(2);
        System.out.println(min2.toString());
        min2.push(6);
        System.out.println(min2.toString());

        min1.pop();
        min2.pop();
        System.out.println(min2.toString());
        min2.pop();
        System.out.println(min2.toString());

        min1.peek();
        min2.peek();
        System.out.println(min1.peek());
        System.out.println(min2.peek());


    }
}
