class Node
{
    int data;
    Node next;

    Node(int data)
    {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList
{
    Node head;

    // Constructor to initialize the linked list
    LinkedList()
    {
        this.head = null;
    }

    // Recursive method to count the number of nodes
    public int countNodes(Node currentNode)
    {
        // Base case: when the current node is null (end of the list)
        if (currentNode == null)
        {
            return 0;
        } else
        {
            // Recursive case: count the current node and move to the next node
            return 1 + countNodes(currentNode.next);
        }
    }

    // Wrapper method to initiate the recursive counting
    public int countNodes()
    {
        return countNodes(this.head);
    }

    // Example usage
    public static void main(String[] args)
    {
        LinkedList linkedList = new LinkedList();

        // Adding nodes to the linked list
        linkedList.head = new Node(1);
        linkedList.head.next = new Node(2);
        linkedList.head.next.next = new Node(3);
        linkedList.head.next.next.next = new Node(4);

        // Counting the nodes
        int numberOfNodes = linkedList.countNodes();
        System.out.println("Number of nodes: " + numberOfNodes);
    }
}
