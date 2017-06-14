package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;

import java.util.function.Consumer;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Use this nested class for storing the values of the
    // DoublyLinkedList. Each DoublyLinkedList.Node contains the value at its
    // index, and a link to the DoublyLinkedList.Node at the next index (called
    // the "child" here) and at the previous index (called "previous"). If the
    // child is null, that denotes the last element of the DoublyLinkedList,
    // while a null previous denotes the first.
    class Node {
        public Object value;
        public Node previous;
        public Node child;

        public Node(Object value) {
            this(value, null, null);
        }

        public Node(Object value, Node previous, Node child) {
            this.value = value;
            this.previous = previous;
            this.child = child;
        }
    }

    public int size() {
        return size;
    }

    public DoublyLinkedList prepend(Object value) {
        if (head == null) {
            head = new Node(value);
            tail = head;
        } else {
            head = new Node(value, null, head);
            head.child.previous = head;
        }
        size++;
        return this;
    }

    public DoublyLinkedList add(Object value) {
        if (head == null) {
            head = new Node(value);
            tail = head;
        } else {
            tail = new Node(value, tail, null);
            tail.previous.child = tail;
        }
        size++;
        return this;
    }

    public Object first() {
        checkListNotEmpty();
        return head.value;
    }

    public Object last() {
        checkListNotEmpty();
        return tail.value;
    }

    public Object deleteFirst() {
        checkListNotEmpty();
        Object deletedValue = head.value;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.child;
            head.previous = null;
        }
        size--;
        return deletedValue;
    }

    public Object deleteLast() {
        checkListNotEmpty();
        Object deletedValue = tail.value;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.child = null;
        }
        size--;
        return deletedValue;
    }

    public DoublyLinkedList each(Consumer<Object> block) {
        Node tempNode = head;
        while (tempNode != null) {
            block.accept(tempNode.value);
            tempNode = tempNode.child;
        }
        return this;
    }

    public DoublyLinkedList eachReversed(Consumer<Object> block) {
        Node tempNode = tail;
        while (tempNode != null) {
            block.accept(tempNode.value);
            tempNode = tempNode.previous;
        }
        return this;
    }

    private void checkBounds(int index) {
        checkLowerBound(index);
        checkUpperBound(index);
    }

    private void checkLowerBound(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkUpperBound(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkListNotEmpty() {
        if (size() == 0) {
            throw new IndexOutOfBoundsException("List is empty");
        }
    }
}