//////////////////////////////////////////////////////////////
// Title: (DS_My.java)
// Course: (CS 400 Fall 2019)
//
// Author: (Mihir Khatri)
// Email: (mkhatri@wisc.edu)
// Lecturer's Name: (Debra Deppeler)
//////////////////////////////////////////////////////////////
@SuppressWarnings("rawtypes")
public class DS_My<T extends Comparable<T>, V> implements DataStructureADT {

  private class Node { // inner class node for the priority queue

    private Comparable key;
    private Object value;
    private Node next;

   /**
    * constructor
    * @param k - key
    * @param value - value
    */
    private Node(Comparable k, Object value) {// constructor
      key = k;
      this.value = value;
      next = null;
    }

    /**
     * sets the next node with a node
     * 
     * @param n
     */
    private void setNext(Node n) {// already has a node so sets it as next
      this.next = n;
    }

    /**
     * gets the next node
     * 
     * @return - next node
     */
    private Node getNext() {
      return next;
    }
  }

  private Node head;
  private int size;
  // Private Fields of the class

  /**
   * constructor
   */
  public DS_My() {// constructor
    head = null;
    size = 0;
  }

  @Override
  /**
   *  inserts a node to my DS
   *  @param - k - key, v - value 
   */
  public void insert(Comparable k, Object v) throws IllegalArgumentException, RuntimeException {
    // checks for key conditions
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    if (this.contains(k)) {
      throw new RuntimeException("duplicate key");
    }
    Node node = new Node(k, v);// makes a new node
    if (head == null) {// if there is no head node make a new one
      head = node;
      size++;
    } else {// if there is already a list find the last node and make one after it
      Node curr = head;
      for (int i = 0; i < size - 1; i++) {
        curr = curr.next;
      }
      curr.setNext(node);
      size++;
    }
  }

  @Override
  /**
   * removes a given node
   * @param - key
   * @return - true removed false otherwise
   * @throws - exceptions when required
   */
  public boolean remove(Comparable k) throws IllegalArgumentException {
    if (k == null) {// if key is null
      throw new IllegalArgumentException("Key given cannot be null");
    }
    if (size == 0) {// size 0 so nothing removed
      return false;
    }
    if (size == 1 && head.key.compareTo(k) == 0) {// if there is only one node which is the
                                                  // one we want to remove
      head = null;
      size--;
      return true;
    }
    if (head.key.compareTo(k) == 0) {// if size>1
      head = head.getNext();
      size--;
    }
    Node curr = head;
    Node delete = head.next;
    for (int i = 0; i < size - 1; i++) {
      if (delete.key.compareTo(k) == 0 && delete != null) {// if found the node and the next one is
                                                           // not null
        curr.next = delete.next;// remove any references to the node we wanted to delete
        size--;
        return true;
      }
      curr = curr.next;
      delete = delete.next;
    }
    return false;

  }

  @Override
  /**
   * checks if there is a particular node in the list
   * @param - key
   * @return - true if it contains false otherwise
   */
  public boolean contains(Comparable k) {
    Node curr = head;
    while (curr != null) {
      if (curr.key.compareTo(k) == 0) {// if found
        return true;
      }
      curr = curr.getNext();// otherwise keep looking
    }
    return false;
  }

  @Override
  /**
   * returns a particular nodes
   * @param - key
   * @return - the value
   * @throws - Exception when required
   */
  public Object get(Comparable k) throws IllegalArgumentException {
    if (k == null) {// if key is null
      throw new IllegalArgumentException("Key given cannot be null");
    }
    Node curr = head;
    while (curr != null) {
      if (curr.key.compareTo(k) == 0) {// if found the node
        return curr.value;
      }
      curr = curr.getNext();// otherwise keep looking
    }
    return null;
  }

  @Override
  /**
   * @return - size
   */
  public int size() {
    return size;
  }

}
