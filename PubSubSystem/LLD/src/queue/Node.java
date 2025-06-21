package queue;

public class Node<T> {
    private final T item;
    private Node<T> next;

    public Node(){
        this.item = null;
        this.next = null;
    }

    public Node(T item) {
        this.item = item;
        this.next = null;
    }

    public T getItem() {
        return item;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
