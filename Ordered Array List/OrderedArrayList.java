public class OrderedArrayList
{
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EXPANSION_CONSTANT = 2;

    private int[] array;
    private int size;

    public OrderedArrayList()
    {
        this.array = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Add an element to the ordered list
    public void add(int element)
    {
        ensureCapacity(); // Ensure capacity before adding an element

        int index = 0;
        while (index < size && array[index] < element)
        {
            index++;
        }

        // Shift elements to make space for the new element
        for (int i = size - 1; i >= index; i--)
        {
            array[i + 1] = array[i];
        }

        // Insert the new element at the correct position
        array[index] = element;
        size++;
    }

    // Ensure capacity by expanding the array if needed
    private void ensureCapacity()
    {
        if (size == array.length)
        {
            int[] newArray = resize();
            array = newArray;
        }
    }

    // Resize the array by creating a newer larger array
    private int[] resize()
    {
        int newCapacity = array.length * EXPANSION_CONSTANT;
        int[] newArray = new int[newCapacity];

        // Copy the contents of the old array to the new array
        for (int i = 0; i < size; i++)
        {
            newArray[i] = array[i];
        }

        return newArray;
    }

    // Get the current size of the internal array
    public int getCapacity()
    {
        return array.length;
    }

    // Compare two lists and return true if they contain the same items
    public boolean equals(OrderedArrayList otherList)
    {
        if (otherList.size != this.size)
        {
            return false;
        }

        for (int i = 0; i < size; i++)
        {
            if (this.array[i] != otherList.array[i])
            {
                return false;
            }
        }

        return true;
    }

    // Display the elements in the ordered list
    public void display()
    {
        for (int i = 0; i < size; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
