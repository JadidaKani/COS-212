public class RecursiveArray {
    public Integer[] array; //array of integers

    //******************************** helper functions *************************
    public void addElements(String[] string, int i, int size)
    {
        if(i<size)
        {
            array[i] = Integer.parseInt(string[i]);
            i++;
            addElements(string,i,size);
        }
    }

    public String stringConverter(int i)
    {
        if(i >= array.length)
        {
            return "";
        }
        if (i == (array.length-1))
        {
            return array[i] + stringConverter(i+1);
        }
        return array[i] + "," + stringConverter(i+1);
    }

    public Integer[] createNewArrayAppend(Integer[] newArr, int i)
    {
        if(i >= array.length)
        {
            return newArr;
        }

        newArr[i] = array[i];
        return createNewArrayAppend(newArr,i+1);
    }

    public Integer[] createNewArrayPrepend(Integer[] newArr, int i, int j)
    {
        if(i >= array.length)
        {
            return newArr;
        }

        newArr[j] = array[i];
        return createNewArrayPrepend(newArr,i+1, j+1);
    }

    public boolean search(int val, int i)
    {
        if(i >= array.length)
        {
            return false;
        }
        if(array[i] == val)
        {
            return true;
        }

        return search(val,i+1);
    }

    public boolean ascend(int i)
    {
        if(i == (array.length -1))
        {
            return true;
        }
        if(array[i] > array[i+1])
        {
            return false;
        }

        return ascend(i+1);
    }

    public boolean descend(int i)
    {
        if(i == (array.length -1))
        {
            return true;
        }
        if(array[i] < array[i+1])
        {
            return false;
        }

        return descend(i+1);
    }

    public void sortAsc(int i) //using "selection sort"
    {
        if(i == (array.length -1))
        {
            return;
        }

        int minNum = findMin(i, array.length - 1);

        int temp = array[i];
        array[i] = array[minNum];
        array[minNum] = temp;

        sortAsc(i+1);
    }

    public int findMin(int start, int end)
    {
        if(start == end)
        {
            return start;
        }

        int min = findMin(start + 1, end);

        if (array[start] < array[min])
        {
            return start;
        }
        else
        {
            return min;
        }
    }

    public void sortDsc(int i) //using "selection sort"
    {
        if(i == (array.length -1))
        {
            return;
        }

        int maxNum = findMax(i, array.length - 1);

        int temp = array[i];
        array[i] = array[maxNum];
        array[maxNum] = temp;

        sortDsc(i+1);
    }

    public int findMax(int start, int end)
    {
        if(start == end)
        {
            return start;
        }

        int max = findMax(start + 1, end);

        if (array[start] > array[max])
        {
            return start;
        }
        else
        {
            return max;
        }
    }

    //****************************************************************************

    public RecursiveArray()
    {
        array = new Integer[0]; //empty array
    }

    public RecursiveArray(String input)
    {
        if(input.isEmpty())
        {
            array = new Integer[0];
        }
        else
        {
            String[] str = input.split(","); //gets rid of commas and makes a String array
            int length = str.length;
            array = new Integer[length];
            int num = 0;

            addElements(str,num,length);
        }
    }

    @Override
    public String toString()
    {
        if(array.length == 0)
        {
            return "Empty Array";
        }

        int num = 0;
        String str = stringConverter(num);
        str = "[" + str;
        str += "]";

        return str;
    }

    public void append(Integer value)
    {
        Integer[] newArray = new Integer[array.length+1];
        int num = 0;
        newArray = createNewArrayAppend(newArray,num);
        newArray[array.length] = value;

        array = newArray;
    }

    public void prepend(Integer value)
    {
        Integer[] newArray = new Integer[array.length+1];
        newArray[0] = value;
        int num1 = 0;
        int num2 = 1;

        newArray = createNewArrayPrepend(newArray,num1,num2);
        array = newArray;
    }

    public boolean contains(Integer value)
    {
        int num = 0;
        boolean found = search(value,num);
        if(found == true)
        {
            return true;
        }
        return false;
    }

    public boolean isAscending()
    {
        int num = 0;
        boolean asc;

        if(array.length == 0)
        {
            asc = true;
        }
        else
        {
            asc = ascend(num);
        }

        if(asc == true)
        {
            return true;
        }
        return false;
    }

    public boolean isDescending()
    {
        int num = 0;
        boolean dsc;

        if(array.length == 0)
        {
            dsc = true;
        }
        else
        {
            dsc = descend(num);
        }

        if(dsc == true)
        {
            return true;
        }
        return false;
    }

    public void sortAscending()
    {
        int num = 0;
        boolean asc;

        if((array.length == 0))
        {
            return;
        }

        asc = ascend(num);
        if(asc)
        {
            return;
        }
        else
        {
            sortAsc(num);
        }
    }

    public void sortDescending()
    {
        int num = 0;
        boolean dsc;

        if((array.length == 0))
        {
            return;
        }

        dsc = descend(num);
        if(dsc)
        {
            return;
        }
        else
        {
            sortDsc(num);
        }
    }

}
