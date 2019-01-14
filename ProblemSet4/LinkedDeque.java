// Molly Soja and Ally Rooney

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class LinkedDeque<T> implements Deque<T> {
  private Node<T> front;
  private Node<T> back;
  private int size;

  private class Node<T> {
    private T item;
    private Node next;
    private Node previous;
  }

  public LinkedDeque() {
    this.front = null; // "grounding"
    this.back = null; // "grounding"
    this.size = 0;
  }
  //public void Node(T item, Node next, Node previous) {
  //  this.item = item;
  //  this.next = next;
  //  this.previous = previous;
  //    }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public String toString() {
    String answer = "The LinkedDeque: ";
    Node<T> temp = this.front;
    while(temp != null) {
      answer += temp.item.toString() + " ";
      temp = temp.next; }
    return answer;
  }

  public void pushLeft(T item) {
    Node<T> temp = front;
    front = new Node<T> ();
    front.item = item;
    if (temp != null) {
      temp.previous = front;
    }
    if (isEmpty()) {
      back = front;
    } else {
      front.next = temp;
    }
    size++;
  }

  public T popLeft() {
    if (isEmpty()) {
      throw new NoSuchElementException("Underflow");
    }
    T item = front.item;
    front = front.next;
    front.previous = null;
    size--;
    return item;
  }

  public void pushRight(T item) {
    Node<T> temp = back;
    back = new Node<T> ();
    back.item = item;
    if (temp.item != null) {
      temp.next = back;
    }
    if (isEmpty()) {
      front = back;
    } else {
      back.previous = temp;
    }
    size++;
  }

  public T popRight() {
    if (isEmpty()) {
      throw new NoSuchElementException("Underflow");
    }
    T item = back.item;
    back = back.previous;
    back.next = null;
    size--;
    return item;
  }

  public static void main(String[] args) {
    Deque<String> test = new LinkedDeque<String> ();
    test.pushLeft("o");
    test.pushRight("l");
    test.pushLeft("M");
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
