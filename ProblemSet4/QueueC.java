import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class QueueC<T> implements Queue<T> {
  private Deque<T> q = new LinkedDeque<T>();

  public void enqueue(T item) {
    q.pushLeft(item);
  }

  public T dequeue() {
    return q.popRight();
    }

  public T peek() { //take it out push it back then say it
    T temp = q.popRight();
    q.pushRight(temp);
    return temp;
    }

  public int size() {
    return q.size();
    }

  public boolean isEmpty() {
    return q.size() == 0;
    }

  public String toString() {
      return q.toString();
    }

  public static void main(String[] args) {
    Queue<String> test = new QueueC<String> ();
    test.enqueue("2");
    test.enqueue("s");
    test.enqueue("c");
    System.out.println(test.toString());
    test.dequeue();
    System.out.println(test.toString());
    System.out.println("Size of queue: " + test.size());
    System.out.format("Peek: %s%n", test.peek());
    System.out.println("Updated size of deque: " + test.size());
    System.out.println(test.toString()); 
    }
}
