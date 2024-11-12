import java.util.Arrays;

public class AList<T> implements ListInterface<T>
{
    private static final int DEFAULT_CAPACITY = 20;
    private Object[] array;
    private int counter;

    public AList()
    {
        array = new Object[DEFAULT_CAPACITY];
        counter = 0;
    }

    public void add(T item) throws ListException
    {
        if (item == null)
        {
            throw new ListException("Error. Unable to add. Cannot add null entries.");
        }

        if (counter == array.length)
        {
            throw new ListException("Error. Unable to add. List is full or not enough memory.");
        }

        array[counter] = item;
        counter++;
    }

    public void add(T item, int position) throws ListException
    {
        if (item == null)
        {
            throw new ListException("Error. Unable to insert. Attempt to insert null object.");
        }

        if (position < 1 || position > counter + 1)
        {
            throw new ListException("Error. Unable to insert. Bad position.");
        }

        if (counter == array.length)
        {
            throw new ListException("Error. Unable to insert. List is full.");
        }

        if (position <= counter)
        {
            // Shift elements to make space for the new item
            for (int i = counter - 1; i >= position - 1; i--)
            {
                array[i + 1] = array[i];
            }
        }

        array[position - 1] = item;
        counter++;
    }

    public T get(int position) throws ListException
    {
        if (position < 1 || position > counter)
        {
            throw new ListException("Error. Unable to get. Bad position.");
        }

        return (T) array[position - 1];
    }

    public T set(T item, int position) throws ListException
    {
        if (item == null)
        {
            throw new ListException("Error. Unable to replace. Replacement cannot be null.");
        }

        if (position < 1 || position > counter)
        {
            throw new ListException("Error. Unable to replace. Bad position.");
        }

        T replacedItem = (T) array[position - 1];
        array[position - 1] = item;
        return replacedItem;
    }

    public int find(T item, int startPosition, int endPosition) throws ListException
    {
        if (startPosition < 1 || startPosition > counter || endPosition < 1 || endPosition > counter)
        {
            throw new ListException("Error. Unable to find. Start and/or end position bad.");
        }

        if (startPosition > endPosition)
        {
            return -1;
        }

        for (int i = startPosition - 1; i < endPosition; i++)
        {
            if (item == null && array[i] == null)
            {
                return i + 1;
            } else if (item != null && item.equals(array[i]))
            {
                return i + 1;
            }
        }

        return -1;
    }

    public int size()
    {
        return counter;
    }

    public String toString()
    {
        if (counter == 0)
        {
            return "The list is empty.\n";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < counter - 1; i++)
        {
            result.append(array[i].toString()).append("\n");
        }
        result.append(array[counter - 1].toString());

        return result.toString();
    }

    public T remove(int position) throws ListException
    {
        if (position < 1 || position > counter)
        {
            throw new ListException("Error. Unable to remove. Bad position.");
        }

        T removedItem = (T) array[position - 1];

        // Shift elements to fill the gap left by the removed item
        for (int i = position - 1; i < counter - 1; i++)
        {
            array[i] = array[i + 1];
        }

        counter--;
        return removedItem;
    }

    public void clear()
    {
        counter = 0;
    }


   	public T[] toArray()
    {
        return Arrays.copyOf(array, counter, (Class<T[]>) array.getClass());
    }


    public boolean isEmpty()
    {
        return counter == 0;
    }
}