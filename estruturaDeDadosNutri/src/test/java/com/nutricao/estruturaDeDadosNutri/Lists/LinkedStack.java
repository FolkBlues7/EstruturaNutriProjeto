package com.nutricao.estruturaDeDadosNutri.Lists;

import com.nutricao.estruturaDeDadosNutri.Interfaces.StackExtension;

import java.util.NoSuchElementException;

public class LinkedStack<E> implements StackExtension<E> {

    private class Node {
        E data;
        Node next;

        public Node() { data = null; next = null; }
        public Node(E data) { this.data = data; next = null; }
        public Node(E data, Node next) { this.data = data; this.next = next; }
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
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E returnData = top.data;
        top = top.next;
        size--;
        if (isEmpty()) {
            bottom = null;
        }
        return returnData;
    }

    @Override
    public void push(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(element);
        newNode.next = top;
        top = newNode;
        if (bottom == null) {
            bottom = newNode;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        Node currentNode = bottom;
        while (currentNode != null) {
            if (currentNode.data.equals((E) o)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

}
