// Molly Soja and Ally Rooney

import java.util.*;

public class MaxPQC<T extends Comparable<T>> implements MaxPQ<T> {

  private class Node {
    private Node parent;
    private T info;
    private Node left;
    private Node right;

  public Node(Node parent, T info, Node left, Node right) {
    this.parent = parent;
    this.info = info;
    this.left = left;
    this.right = right;
  }

  public Node(Node parent, T info) {
    this.info = null;
    this.parent = null;
  }
}

  private Node root;
  private int size;

  public void MaxPQC() {
    Node root = new Node(null, null, null, null);
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public String toString() { return this.traverse(root); }

  private void exchange(Node child, Node parent) {
    T temp = child.info;
    child.info = parent.info;
    parent.info = temp;
  }

  public boolean leaf(Node node) {
    return (node.left == null && node.right == null);
  }

  public void sink(Node sinkNode) {
    if(leaf(sinkNode)) {
      System.exit(0);
    } else {
        Node leftchild = sinkNode.left;
        Node rightchild = sinkNode.right;
        if (leftchild != null && rightchild != null && leftchild.info.compareTo(rightchild.info) < 0 ) {
          Node temp = leftchild;
          leftchild = rightchild;
          rightchild = temp;
        }
        if (leftchild != null && leftchild.info.compareTo(sinkNode.info) > 0) {
          exchange(leftchild, sinkNode);
          sink(leftchild);
        }
    }
  }

  public void swim(Node swimNode, Node par){
    //System.out.println("par info " + par.info);
    if (par != null && swimNode.info.compareTo(par.info) > 0) {
      exchange(swimNode, par);
      swim(par, par.parent);
    }
  }

  public Node find(Node node, int n) {
    if (n  == 1) {
      //System.out.println(node.info);
      return node;
    }
    else {
      Node gp = find(node, n / 2);
      if (n % 2 == 0){
        //System.out.println(size);
        return gp.left;
      }
      else {
        //System.out.println(size);
        return gp.right;
      }
    }
  }

  private String traverse(Node temp){
    if(temp == null) {
      return " ";
    }
    else {
      if (leaf(temp)){
        return temp.info.toString();
      }
      else{
          String left = traverse(temp.left);
          if(temp.right == null){
            return temp.info + "(" + left + ")";
          }
          else{
            String right = traverse(temp.right);
            return temp.info + "(" + left + "," + right + ")";
          }
        }
      }
    }

  public void insert(T key) {
    if(isEmpty()) {
      root = new Node(null, key, null, null); //is our tree is empty
      //System.out.println("root info " + root.info);
      size++;
    }
    else {
      //System.out.println(size);
      //int parentplace = ((size + 1) /2);
      int parentplace = ((size ) /2);
      Node p = find(root, parentplace);
          //System.out.println(p.info);
    //System.out.println("p info insert " + p.info);
    //int newsize = size + 1;
      Node child = new Node(p, key, null, null);
      //System.out.println("child " + child.info);
    //child.info = key;
      if (size % 2 == 0) {
        p.left = child;
        //System.out.println("left " + p.left.info);
            //System.out.println("p left " + p.left.info);
      //assign child to the left
      }
      else {
        //System.out.println(size);
        p.right = child;
        //System.out.println("right " + p.right.info);
              //System.out.println("p right " + p.right.info);
        }
      swim(child, p);
      }
    size++;
  }


public T delMax() {
      //find the node containing the last entry in the complete binary tree
      if(isEmpty()) { throw new NoSuchElementException("Your tree is empty"); }
      //set root as max
      T max = root.info;
      Node top = find(root, size - 1);
      if (size % 2 == 1) {
        top.parent.left = null;
      }
      else {
        top.parent.right = null;
      }
      //System.out.println("top " + top.info);
      root.info = top.info;
      size--;
      if(this.isEmpty()) {
        root = null;
      }
      else {
        sink(root);
      }
      return max;
    }

  public static void main(String[] args) {
    MaxPQ<String> PQ = new MaxPQC<String>();
    PQ.insert("M");
    PQ.insert("O");
    PQ.insert("D");
    PQ.insert("J");
    PQ.insert("A");
    PQ.insert("B");
    PQ.insert("Y");
    System.out.println(PQ.toString());
    String maxdel = PQ.delMax();
    System.out.println(PQ.toString());
    //System.out.println(maxdel);
  }
}
