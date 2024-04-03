package com.nutricao.estruturaDeDadosNutri.Lists;

import com.nutricao.estruturaDeDadosNutri.Interfaces.ListExtension;
import com.sun.istack.NotNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements ListExtension<E> {

    private class Node {
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
            System.out.println("The Doubly Linked List is as follows:");
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
            System.out.println("The Doubly Linked List is as follows:");
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
        return new Iterator<E>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = currentNode.data;
                currentNode = currentNode.next;
                return data;
            }
        };
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
    @SuppressWarnings("unchecked")
    public boolean containsAll(Collection<?> c) {
        List<E> list = (List<E>) c;
        for (E e : list) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        List<E> list = (List<E>) c;
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        for (E e : list) {
            Node newNode = new Node(e);
            newNode.next = currentNode.next;
            newNode.prev = currentNode;
            if (currentNode.next != null) {
                currentNode.next.prev = newNode;
            } else {
                tail = newNode;
            }
            currentNode.next = newNode;
            currentNode = newNode;
        }
        size += list.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified |= remove(o);
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node currentNode = head;
        while (currentNode != null) {
            if (!c.contains(currentNode.data)) {
                Node nextNode = currentNode.next;
                if (currentNode == head) {
                    head = nextNode;
                    if (nextNode != null) {
                        nextNode.prev = null;
                    }
                } else if (currentNode == tail) {
                    tail = currentNode.prev;
                    tail.next = null;
                } else {
                    Node prevNode = currentNode.prev;
                    prevNode.next = nextNode;
                    nextNode.prev = prevNode;
                }
                currentNode.next = null;
                currentNode.prev = null;
                size--;
                modified = true;
            }
            currentNode = currentNode.next;
        }
        return modified;
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
        return new ListIterator<E>() {
            private Node currentNode = head;
            private Node lastAccessed = null;
            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = currentNode.data;
                lastAccessed = currentNode;
                currentNode = currentNode.next;
                nextIndex++;
                return data;
            }

            @Override
            public boolean hasPrevious() {
                return lastAccessed != null;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                E data = lastAccessed.data;
                currentNode = lastAccessed;
                lastAccessed = null;
                nextIndex--;
                return data;
            }

            @Override
            public int nextIndex() {
                return nextIndex;
            }

            @Override
            public int previousIndex() {
                return nextIndex - 1;
            }

            @Override
            public void remove() {
                if (lastAccessed == null) {
                    throw new IllegalStateException();
                }
                Node nextNode = lastAccessed.next;
                Node prevNode = lastAccessed.prev;

                if (prevNode == null) {
                    head = nextNode;
                } else {
                    prevNode.next = nextNode;
                    lastAccessed.prev = null;
                }

                if (nextNode == null) {
                    tail = prevNode;
                } else {
                    nextNode.prev = prevNode;
                    lastAccessed.next = null;
                }

                if (currentNode == lastAccessed) {
                    currentNode = nextNode;
                } else {
                    nextIndex--;
                }

                lastAccessed = null;
                size--;
            }

            @Override
            public void set(E e) {
                if (lastAccessed == null) {
                    throw new IllegalStateException();
                }
                lastAccessed.data = e;
            }

            @Override
            public void add(E e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                lastAccessed = null;
                Node newNode = new Node(e);

                if (currentNode == null) {
                    newNode.prev = tail;
                    newNode.next = null;
                    tail = newNode;
                    if (head == null) {
                        head = newNode;
                    }
                } else {
                    newNode.prev = currentNode.prev;
                    newNode.next = currentNode;
                    currentNode.prev = newNode;
                    if (newNode.prev == null) {
                        head = newNode;
                    } else {
                        newNode.prev.next = newNode;
                    }
                }

                size++;
                nextIndex++;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        return new ListIterator<E>() {
            private Node currentNode = getNode(index);
            private Node lastAccessedNode = null;
            private int nextIndex = index;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastAccessedNode = currentNode;
                E data = currentNode.data;
                currentNode = currentNode.next;
                nextIndex++;
                return data;
            }

            @Override
            public boolean hasPrevious() {
                return nextIndex > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                currentNode = (currentNode == null) ? tail : currentNode.prev;
                lastAccessedNode = currentNode;
                E data = currentNode.data;
                nextIndex--;
                return data;
            }

            @Override
            public int nextIndex() {
                return nextIndex;
            }

            @Override
            public int previousIndex() {
                return nextIndex - 1;
            }

            @Override
            public void remove() {
                if (lastAccessedNode == null) {
                    throw new IllegalStateException();
                }
                Node nextNode = lastAccessedNode.next;
                Node prevNode = lastAccessedNode.prev;

                if (prevNode == null) {
                    head = nextNode;
                } else {
                    prevNode.next = nextNode;
                    lastAccessedNode.prev = null;
                }

                if (nextNode == null) {
                    tail = prevNode;
                } else {
                    nextNode.prev = prevNode;
                    lastAccessedNode.next = null;
                }

                if (currentNode == lastAccessedNode) {
                    currentNode = nextNode;
                } else {
                    nextIndex--;
                }

                lastAccessedNode = null;
                size--;
            }

            @Override
            public void set(E e) {
                if (lastAccessedNode == null) {
                    throw new IllegalStateException();
                }
                lastAccessedNode.data = e;
            }

            @Override
            public void add(E e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                lastAccessedNode = null;
                Node newNode = new Node(e);

                if (currentNode == null) {
                    newNode.prev = tail;
                    newNode.next = null;
                    tail = newNode;
                    if (head == null) {
                        head = newNode;
                    }
                } else {
                    newNode.prev = currentNode.prev;
                    newNode.next = currentNode;
                    currentNode.prev = newNode;
                    if (newNode.prev == null) {
                        head = newNode;
                    } else {
                        newNode.prev.next = newNode;
                    }
                }

                size++;
                nextIndex++;
            }

            private Node getNode(int index) {
                Node node = head;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
                return node;
            }
        };
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
