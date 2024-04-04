package com.nutricao.estruturaDeDadosNutri.DataStructures;

import com.nutricao.estruturaDeDadosNutri.Interfaces.QueueExtension;

import java.util.Collection;
import java.util.Iterator;

public class LinkedQueue<E> implements QueueExtension<E> {

    private class Node {
        E data;
        Node next;

        private Node() { data = null; next = null; }
        private Node(E data) { this.data = data; next = null; }
        private Node(E data, Node next) { this.data = data; this.next = next; }
    }

    private int size;
    private Node beginning;  // next element to be removed
    private Node end;

    @Override
    public boolean add(E element) {
        if (element == null) throw new NullPointerException();
        Node newNode = new Node(element);
        if (isEmpty()) {
            beginning = newNode;
        } else {
            end.next = newNode;
        }
        end = newNode;
        size++;
        return true;
    }

    @Override
    public boolean offer(E e) { // does the same as add method
        if (e == null) throw new NullPointerException();
        Node newNode = new Node(e);
        if (isEmpty()) {
            beginning = newNode;
        } else {
            end.next = newNode;
        }
        end = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if (o == null) throw new NullPointerException();
        Node current = beginning;
        Node previous = null;
        while (current != null) {
            if (current.data.equals(o)) {
                if (previous == null) { // first element is the one to remove
                    beginning = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        beginning = null;
        end = null;
        size = 0;
    }

    @Override
    public E remove() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        E data = beginning.data;
        beginning = beginning.next;
        size--;
        return data;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E data = beginning.data;
        beginning = beginning.next;
        size--;
        return data;
    }

    @Override
    public E element() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        return beginning.data;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : beginning.data;
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
    public boolean contains(Object o) {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if (o == null) throw new NullPointerException();
        Node current = beginning;
        while (current != null) {
            if (current.data.equals(o)) return true;
            current = current.next;
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
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

}
