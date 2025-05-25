public class PrimeNumberGenerator {
    PrimeNode head; // head of the PrimeNumberGenerator linked list. Never null.

    @Override
    public String toString()
    {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator()
    {
        head = new PrimeNode(2);
    }

    public int currentPrime()
    {
        return head.value;
    }

    public int nextPrime()
    {
        if(head.next == null)
        {
            sieveOfEratosthenes();
        }

        head = head.next;
        return head.value;
    }

    public void sieveOfEratosthenes()
    {
        boolean[] notPrime = new boolean[head.value * 2 + 1];
        for(int i=0; i<notPrime.length; i++)
        {
            notPrime[i] = false;
        }

        int jump = 2;
        while(jump < notPrime.length)
        {
            int counter = jump + jump;
            while(counter < notPrime.length)
            {
                notPrime[counter] = true;
                counter += jump;
            }
            jump += 1;
        }

        PrimeNode current = head;

        while(current != null)
        {
            int count = 2;
            while(count < notPrime.length)
            {
                if(notPrime[count] == false)
                {
                    if(current.value == count)
                    {
                        notPrime[count] = true;
                    }
                    if(count < head.value)
                    {
                        notPrime[count] = true;
                    }
                }
                count ++;
            }
            current = current.next;
        }

        for(int i=2; i<notPrime.length; i++)
        {
            if(notPrime[i] == false)
            {
                PrimeNode newNode = new PrimeNode(i);
                current = head;
                while (current.next != null)
                {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

    }

}
