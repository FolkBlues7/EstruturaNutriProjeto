package com.nutricao.estruturaDeDadosNutri.Lists;

import com.nutricao.estruturaDeDadosNutri.Interfaces.ListExtension;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//TODO: make limitation to not allow null elements

public class SinglyLinkedList<E> implements ListExtension<E> {
    
    class Node {
        E data;
        Node next;
        
        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
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
    
    @Override
    public boolean addFirst(E e) {
        return false;
    }
    
    @Override
    public boolean addAfter(E e, E criteria) {
        return false;
    }
    
    @Override
    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return head.data;
    }
    
    @Override
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return tail.data;
    }
    
    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) { //check if needed
            E data = head.data;
            head = null;
            tail = null;
            size = 0;
            return data;
        }
        E data = head.data;
        head = head.next;
        head.next = null;
        size--;
        return data;
    }
    
    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            head = null;
        }
        E data = tail.data;
        //finish method (need the element before the last to be new tail
    }
    
    @Override
    public void show() {
    
    }
    
    @Override
    public void showReversed() {
    
    }
}
