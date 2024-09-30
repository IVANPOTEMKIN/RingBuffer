package ru.effective_mobile;

public class RingBuffer<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    private final int capacity;

    public RingBuffer() {
        this(DEFAULT_CAPACITY);
    }

    public RingBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void push(E element) {
        if (isFull()) {
            throw new RuntimeException("Buffer is full");
        }

        Node<E> node = new Node<>(element);

        if (head == null) {
            tail = head = node;
        } else {
            node.next = head;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public synchronized E pop() {
        E element = peek();
        head = head.next;
        tail.next = head;
        size--;

        if (size == 0) {
            head = tail = null;
        }
        return element;
    }

    public synchronized E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Buffer is empty");
        }
        return head.element;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized boolean isFull() {
        return size == capacity;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized void clear() {
        tail = head = null;
        size = 0;
    }

    private static class Node<E> {
        private final E element;
        private Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }
}