package com.nutricao.estruturaDeDadosNutri.Lists;

import com.nutricao.estruturaDeDadosNutri.Interfaces.StackExtension;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedStack<E> implements StackExtension<E> {

    class Node {
        E data;
        Node next;

        public Node() {
            data = null;
            next = null;
        }

        public Node(E data) {
            this.data = data;
            next = null;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size;
    private Node bottom;
    private Node top;

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return top.data;
    }

    @Override
    public E pop() {
        E returnData = top.data;
        if (size == 1) {
            size = 0;
            top = null;
            bottom = null;
            return returnData;
        }
        Node beforeNode = nodeBefore(returnData);
        if (beforeNode == null) { //means the queue isEmpty
            throw new ArrayIndexOutOfBoundsException();
        }
        beforeNode.next = null;
        top = beforeNode;
        size--;
        return returnData;
    }

    @Override
    public E push(E element) {
        return null;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public int search(Object o) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
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

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
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

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
        return null;
    }

    private Node nodeBefore(E returnData) {
        Node current = bottom;
        while (current.next != null) {
            if (current.next.data.equals(returnData)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

}
