public class LList implements ListInterface
{
    private class Node
    {
        int data;
        Node next;

        Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int counter;

    public LList()
    {
        head = null;
        tail = null;
        counter = 0;
    }


    public void add(int element) throws ListException
    {
        try
        {
            Node newNode = new Node(element);

            if (head == null)
            {
                // If the list is empty, set the new node as both head and tail
                head = newNode;
                tail = newNode;
            } else if (element <= head.data)
            {
                // If the new element is smaller or equal to the head, insert at the beginning
                newNode.next = head;
                head = newNode;
            } else if (element >= tail.data)
            {
                // If the new element is greater or equal to the tail, insert at the end
                tail.next = newNode;
                tail = newNode;
            } else
            {
                // Insert the new element at the correct position in the middle
                Node current = head;
                while (current.next != null && current.next.data < element)
                {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }

            counter++;
        } catch (OutOfMemoryError e)
        	{
        	    throw new ListException("Out of memory: Unable to add element to the list.");
        	}
    }


    public void display()
    {
        Node current = head;
        while (current != null)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}