import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

 @SuppressWarnings("unchecked")

public class ResizingArrayDeque<T> implements Deque<T> {
  private T[] deque = (T[]) new Object[4];         // array of items
  private int size;

  public int size() {
    return size;
   }

  public boolean isEmpty() {
    return this.size == 0;
   }

  public void resize(int max) {
    T[] temp = (T[]) new Object[max];
    for (int i = 0; i < size; i++)
      temp[i] = deque[i];
    deque = temp;
  }

  public String toString() {
    String answer = "The Resized Deque: ";
    for (int i = 0; i < size; i++) {
      answer += deque[i].toString() + " ";
    }
    return answer;
  }

  public void pushLeft(T item) {
    if (size == deque.length) {
      resize(2*deque.length);
   }
    for (int i = size; i > -1; i--) {
      deque[i+1] = deque[i];
   }
   deque[0] = item;
   size++;
  }

  public T popLeft() {
    if (isEmpty()) {
      throw new NoSuchElementException("Underflow");
   }
    T temp = deque[0];
    for (int i = 1; i < size; i++) {
      deque[i-1] = deque[i];
   }
    size--;
    if (size > 0 && size == deque.length/4) {
      resize(deque.length/4);
   }
    return temp;
  }

  public void pushRight(T item) {
    if (size == deque.length) {
      resize(2*deque.length);
    }
    deque[size] = item;
    size++;
 }

 public T popRight() {
   if (isEmpty()) {
     throw new NoSuchElementException("Underflow");
  }

    T temp = deque[size - 1];
    deque[size - 1] = null;
    size--;
    if (size > 0 && size == deque.length/4) {
      resize(deque.length/4);
    }
    return temp;
 }

  public static void main(String[] args) {
   Deque<String> test = new ResizingArrayDeque<String> ();
   test.pushLeft("l");
   test.pushRight("l");
   test.pushLeft("A");
   test.pushRight("l");
   test.pushRight("y");
   System.out.println(test.toString());
   System.out.println("Size of deque: " + test.size());
   System.out.format("Pop of front: %s%n", test.popLeft());
   System.out.format("Pop of back: %s%n", test.popRight());
   System.out.println("Updated size of deque: " + test.size());
   System.out.println(test.toString()); 
   }
 }
