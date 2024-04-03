package com.nutricao.estruturaDeDadosNutri.Lists;

import com.nutricao.estruturaDeDadosNutri.Interfaces.ListExtension;
import com.sun.istack.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedList<E> implements ListExtension<E> {

    class Node {
        E data;
        Node next;
        Node prev;

        public Node(E data) { this.data = data; next = null; prev = null; }
    }

    private Node head;
    private Node tail;
    private int size;

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean addAfter(E e, E criteria) {
        if (e == null || criteria == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        Node newNode = new Node(e);
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(criteria)) {
                newNode.next = currentNode.next;
                newNode.prev = currentNode;
                if (currentNode.next != null) {
                    currentNode.next.prev = newNode;
                } else {
                    tail = newNode;
                }
                currentNode.next = newNode;
                size++;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public E peekFirst() {
        return head.data;
    }

    @Override
    public E peekLast() {
        return tail.data;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        E data = head.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node nextNode = head.next;
            head.next = null;
            head = nextNode;
        }
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        E data = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node prevNode = tail.prev;
            tail.prev = null;
            tail.next = null;
            tail = prevNode;
            tail.next = null;
        }
        size--;
        return data;
    }

    @Override
    public void show() {
        if (isEmpty()) {
            System.out.println("The Doubly Linked List is empty");
        } else {
            System.out.println("The Singly Linked List is as follows:");
            Node currentNode = head;
            while (currentNode != null) {
                System.out.print(" " + currentNode.data);
                currentNode = currentNode.next;
                if (currentNode.next != null) {
                    System.out.println(",");
                }
            }
        }
    }

    @Override
    public void showReversed() {
        if (isEmpty()) {
            System.out.println("The Doubly Linked List is empty");
        } else {
            System.out.println("The Singly Linked List is as follows:");
            Node currentNode = tail;
            while (currentNode != null) {
                System.out.print(" " + currentNode.data);
                currentNode = currentNode.prev;
                if (currentNode.prev != null) {
                    System.out.println(",");
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals((E) o)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        Object[] objects = new Object[size];
        Node currentNode = head;
        for (int i = 0; currentNode != null; i++) {
            objects[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return objects;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        Node currentNode = head;
        for (int i = 0; currentNode != null; i++) {
            a[i] = (T) currentNode.data;
            currentNode = currentNode.next;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals((E) o)) {
                if (currentNode == head) {
                    Node nextNode = head.next;
                    head.next = null;
                    if (nextNode != null) {
                        nextNode.prev = null;
                    }
                    head = nextNode;
                } else if (currentNode == tail) {
                    Node prevNode = tail.prev;
                    tail.prev = null;
                    if (prevNode != null) {
                        prevNode.next = null;
                    }
                    tail = prevNode;
                } else {
                    Node nextNode = currentNode.next;
                    Node prevNode = currentNode.prev;
                    currentNode.prev = null;
                    currentNode.next = null;
                    prevNode.next = nextNode;
                    nextNode.prev = prevNode;
                }
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head.next = null;
        head = null;
        tail.prev = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    @Override
    public E set(int index, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        E data = currentNode.data;
        Node newNode = new Node(element);
        newNode.next = currentNode.next;
        newNode.prev = currentNode.prev;
        if (currentNode.prev != null) {
            currentNode.prev.next = newNode;
        } else {
            head = newNode;
        }
        if (currentNode.next != null) {
            currentNode.next.prev = newNode;
        } else {
            tail = newNode;
        }
        return data;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            Node currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            newNode.prev = currentNode;
            currentNode.next.prev = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        E data = currentNode.data;
        if (currentNode.prev != null) {
            currentNode.prev.next = currentNode.next;
        } else {
            head = currentNode.next;
        }
        if (currentNode.next != null) {
            currentNode.next.prev = currentNode.prev;
        } else {
            tail = currentNode.prev;
        }
        currentNode.next = null;
        currentNode.prev = null;
        size--;
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return -1;
        }
        Node currentNode = head;
        for (int i = 0; currentNode != null; i++) {
            if (currentNode.data.equals((E) o)) {
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int lastIndexOf(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return -1;
        }
        Node currentNode = head;
        int foundNode = -1;
        for (int i = 0; currentNode != null; i++) {
            if (currentNode.data.equals((E) o)) {
                foundNode = i;
            }
            currentNode = currentNode.next;
        }
        return foundNode;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        List<E> subList = new DoublyLinkedList<>();
        Node currentNode = head;
        for (int i = 0; i <= toIndex; i++) {
            if (i >= fromIndex) {
                subList.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return subList;
    }

}
