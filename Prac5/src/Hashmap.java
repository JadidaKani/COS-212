import java.lang.Math;;

public class Hashmap {
    public KeyValuePair[] array; // array of KeyValuePairs, which will store the hashmap data. Empty? null
    public PrimeNumberGenerator prime = new PrimeNumberGenerator(); //object used for prime numbers that are needed for the implementation

    @Override
    public String toString()
    {
        String res = String.valueOf(prime.currentPrime()) + "\n";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine()
    {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public Hashmap()
    {
        array = new KeyValuePair[1];
    }

    public Hashmap(String inp)
    {
        int commas = 1;
        for (int i = 0; i < inp.length(); i++)
        {
            if (inp.charAt(i) == ',')
            {
                commas++;
            }
        }

        array = new KeyValuePair[commas];
        int index = inp.indexOf('[');
        String primeStr = inp.substring(0, index);
        int primeNum = Integer.parseInt(primeStr);
        inp = inp.substring(index);

        int num = 2;
        while(primeNum >= num)
        {
            if(num == primeNum)
            {
                break;
            }
            num = prime.nextPrime();
        }

        if (inp.startsWith("["))
        {
            inp = inp.substring(1);
        }

        if (inp.endsWith("]")) {
            inp = inp.substring(0, inp.length() - 1);
        }

        inp += ",";
        for(int i=0; i<array.length; i++)
        {
            index = inp.indexOf(',');
            String result = "";

            if (index != -1)
            {
                result = inp.substring(0, index);
                inp = inp.substring(index + 1);
            }
            if(result.equals("-"))
            {
                array[i] = null;
            }
            else
            {
                int colonIndex = result.indexOf(':');
                int percentIndex = result.indexOf('%');

                if (colonIndex != -1 && percentIndex != -1)
                {
                    String strNum = result.substring(1, colonIndex);
                    int sNum = Integer.parseInt(strNum);
                    String strMarks = result.substring(colonIndex + 1, percentIndex);
                    int marks = Integer.parseInt(strMarks);

                    array[i] = new KeyValuePair(sNum, marks);
                }
            }
        }

    }

    public int hash(int studentNumber)
    {
        String str = String.valueOf(studentNumber);
        int hashVal = 0;

        for (int i = 0; i < str.length(); i++)
        {
            String strDigit = Character.toString(str.charAt(i));
            int digit = Integer.parseInt(strDigit);
            hashVal = prime.currentPrime() * hashVal + digit;
        }

        if (hashVal < 0)
        {
            hashVal = Math.abs(hashVal);
        }
        hashVal %= array.length;

        return hashVal;
    }

    public KeyValuePair search(int studentNumber)
    {
        int value = hash(studentNumber);
        int[] offsets = {1, -1, 4, -4, 9, -9};

        if(array[value] != null && array[value].studentNumber == studentNumber)
        {
            return array[value];
        }

        for (int i = 0; i < offsets.length; i++)
        {
            int newValue = Math.abs((value + offsets[i] * prime.currentPrime())) % array.length;
            if (array[newValue] != null && array[newValue].studentNumber == studentNumber)
            {
                return array[newValue];
            }
        }

        return null;
    }

    public void insert(int studentNumber, int mark)
    {
        int value = hash(studentNumber);
        int[] offsets = {1, -1, 4, -4, 9, -9};

        if(array[value] != null && array[value].studentNumber == studentNumber)
        {
            array[value].mark = mark;
            return;
        }

        for (int i = 0; i < offsets.length; i++)
        {
            int newValue = Math.abs((value + offsets[i] * prime.currentPrime())) % array.length;
            if (array[newValue] != null && array[newValue].studentNumber == studentNumber)
            {
                array[newValue].mark = mark;
                return;
            }
        }

        if(array[value] == null)
        {
            array[value] = new KeyValuePair(studentNumber,mark);
            return;
        }

        for (int i = 0; i < offsets.length; i++)
        {
            int newValue = Math.abs((value + offsets[i] * prime.currentPrime())) % array.length;
            if(array[newValue] == null)
            {
               array[newValue] = new KeyValuePair(studentNumber,mark);
               return;
            }
        }

        KeyValuePair[] tempArr = new KeyValuePair[array.length];
        for(int i=0; i< array.length; i++)
        {
            if(array[i] != null)
            {
                tempArr[i] = new KeyValuePair(array[i].studentNumber, array[i].mark);
            }
        }

        array = new KeyValuePair[tempArr.length*2];
        prime.nextPrime();

        for(int i=0; i< tempArr.length; i++)
        {
            if(tempArr[i] != null)
            {
                int index = hash(tempArr[i].studentNumber);
                int power = 1;
                if (array[index] == null)
                {
                    array[index] = new KeyValuePair(tempArr[i].studentNumber, tempArr[i].mark);
                }
                else
                {
                    boolean done = false;
                    for (int j = 0; j < offsets.length; j++)
                    {
                        int newValue = Math.abs((index + offsets[j] * prime.currentPrime())) % array.length;
                        if (array[newValue] == null)
                        {
                            done = true;
                            array[newValue] = new KeyValuePair(tempArr[i].studentNumber, tempArr[i].mark);
                            break;
                        }
                    }
                    if (done != true)
                    {
                        System.out.println("here");
                        power += 1;
                        array = new KeyValuePair[tempArr.length * (int) Math.pow(2, power)];
                        prime.nextPrime();
                        i = -1;
                    }
                }
            }
        }

        value = hash(studentNumber);
        if(array[value] == null)
        {
            array[value] = new KeyValuePair(studentNumber,mark);
            return;
        }

        for (int i = 0; i < offsets.length; i++)
        {
            int newValue = Math.abs((value + offsets[i] * prime.currentPrime())) % array.length;
            if(array[newValue] == null)
            {
                array[newValue] = new KeyValuePair(studentNumber,mark);
                return;
            }
        }
    }

    public void remove(int studentNumber)
    {
        int index = -1;
        int value = hash(studentNumber);
        int[] offsets = {1, -1, 4, -4, 9, -9};

        if(array[value] != null && array[value].studentNumber == studentNumber)
        {
            index = value;
        }

        for (int i = 0; i < offsets.length; i++)
        {
            int newValue = Math.abs((value + offsets[i] * prime.currentPrime())) % array.length;
            if (array[newValue] != null && array[newValue].studentNumber == studentNumber)
            {
                index = newValue;
            }
        }

        if(index > -1)
        {
            array[index] = null;
        }
    }
}
