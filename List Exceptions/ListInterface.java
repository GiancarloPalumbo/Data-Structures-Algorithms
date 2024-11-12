public interface ListInterface<T> 
{
    void add(T item) throws ListException;
    void add(T item, int position) throws ListException;
    T get(int position) throws ListException;
    T set(T item, int position) throws ListException;
    int find(T item, int startPosition, int endPosition) throws ListException;
    int size();
    String toString();
    T remove(int position) throws ListException;
    void clear();
    T[] toArray();
    boolean isEmpty();
}