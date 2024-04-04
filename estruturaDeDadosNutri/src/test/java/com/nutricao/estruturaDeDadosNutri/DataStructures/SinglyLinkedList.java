package com.nutricao.estruturaDeDadosNutri.DataStructures;

import com.nutricao.estruturaDeDadosNutri.DataStructures.Interfaces.ListExtension;
import com.sun.istack.NotNull;

import java.util.*;

//TODO: make docs
//TODO: change e to element in method params

public class SinglyLinkedList<E> implements ListExtension<E> {
    
    private class Node {
        E data;
        Node next;
        
        public Node(E data) {
            this.data = data;
            this.next = null;
        }
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
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
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        E dataToCompare = (E) o;
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(dataToCompare)) {
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
        Object[] array = new Object[size];
        Node currentNode = head;
        for (int i = 0; currentNode != null; i++) {
            array[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return array;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
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
        E dataToCompare = (E) o;
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(dataToCompare)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (c.contains(null)) {
            throw new NullPointerException();
        }
        if (c.size() > size) {
            return false;
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (c.contains(null)) {
            throw new NullPointerException();
        }
        for (E element : c) {
            if (element == null) {
                throw new NullPointerException();
            }
        }
        for (E element : c) {
            add(element);
        }
        return true;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (c.contains(null)) {
            throw new NullPointerException();
        }
        Node currentNode = head;
        for (int i = 0; i < index-1; i++) {
            currentNode = currentNode.next;
        }
        for (E element : c) {
            if (element == null) {
                throw new NullPointerException();
            }
        }
        for (E element : c) {
            Node newNode = new Node(element);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            currentNode = newNode;
            size++;
        }
        return true;
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        boolean allRemoved = false;
        for (Object o : c) {
            allRemoved |= remove(o);
        }
        return allRemoved;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        boolean isModified = false;
        Node prevNode = null;
        Node currentNode = head;
        while (currentNode != null) {
            if (!c.contains(currentNode.data)) {
                if (prevNode == null) {
                    head = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                if (currentNode == tail) {
                    tail = prevNode;
                }
                size--;
                isModified = true;
            } else {
                prevNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        return isModified;
    }
    
    @Override
    public void clear() {
        head.next = null;
        head = null;
        tail = null;
        size = 0;
    }
    
    @Override
    public E get(int index) {
        if (index >= size || index < 0) { // size is +1 in relation to index
            throw new ArrayIndexOutOfBoundsException();
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }
    
    @Override
    public E set(int index, E element) { // TODO: add in docs the difference with add(index) + that size isn't changed
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        Node currentNode = head;
        E oldData = null;
        if (index == 0) {
            oldData = head.data;
            newNode.next = head.next;
            head = newNode;
        } else {
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    oldData = currentNode.next.data;
                    newNode.next = currentNode.next.next;
                    currentNode.next = newNode;
                }
                currentNode = currentNode.next;
            }
        }
        return oldData;
    }
    
    @Override
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        Node currentNode = head;
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
        } else {
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    newNode.next = currentNode.next;
                    currentNode.next = newNode;
                    size++;
                }
                currentNode = currentNode.next;
            }
        }
    }
    
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node currentNode = head;
        E data = head.data;
        if (index == 1) {
            return data;
        } else {
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    Node oldNode = currentNode.next;
                    data = oldNode.data;
                    currentNode.next = currentNode.next.next;
                    oldNode.next = null;
                }
                currentNode = currentNode.next;
            }
        }
        size--;
        return data;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public int indexOf(Object o) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(); // TODO: check if it is this exception
        }
        Node currentNode = head;
        E dataToCompare = (E) o;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.data.equals(dataToCompare)) {
                return index;
            }
            index++;
            currentNode = currentNode.next;
        }
        throw new NullPointerException(); // TODO: check if it is this exception
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public int lastIndexOf(Object o) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int foundIndex = -1;
        Node currentNode = head;
        for (int i = 0; currentNode != null; i++) {
            if (currentNode.data.equals((E) o)) {
                foundIndex = i;
            }
            currentNode = currentNode.next;
        }
        if (foundIndex < 0) {
            throw new NullPointerException();
        }
        return foundIndex;
    }
    
    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            private Node currentNode = head;
            private Node lastAccessedNode = null;
            private int nextIndex = 0;

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
                currentNode = (currentNode == null) ? tail : currentNode;
                lastAccessedNode = currentNode;
                E data = currentNode.data;
                currentNode = currentNode.next;
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
                Node walkerNode = head;
                while (walkerNode.next != lastAccessedNode) {
                    walkerNode = walkerNode.next;
                }
                walkerNode.next = lastAccessedNode.next;
                if (lastAccessedNode == tail) {
                    tail = walkerNode;
                }
                size--;
                if (nextIndex > 0) {
                    nextIndex--;
                }
                lastAccessedNode = null;
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
                Node newNode = new Node(e);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else if (currentNode == head) {
                    newNode.next = head;
                    head = newNode;
                } else {
                    Node walkerNode = head;
                    while (walkerNode.next != currentNode) {
                        walkerNode = walkerNode.next;
                    }
                    newNode.next = walkerNode.next;
                    walkerNode.next = newNode;
                    if (newNode.next == null) {
                        tail = newNode;
                    }
                }
                size++;
                nextIndex++;
                lastAccessedNode = null;
            }
        };
    }
    
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIterator<E>() {
            private Node currentNode = head;
            private Node lastAccessedNode = null;
            private int nextIndex = 0;

            {
                for (int i = 0; i < index; i++) {
                    next();
                }
            }

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
                currentNode = (currentNode == null) ? tail : currentNode;
                lastAccessedNode = currentNode;
                E data = currentNode.data;
                currentNode = currentNode.next;
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
                Node walkerNode = head;
                while (walkerNode.next != lastAccessedNode) {
                    walkerNode = walkerNode.next;
                }
                walkerNode.next = lastAccessedNode.next;
                if (lastAccessedNode == tail) {
                    tail = walkerNode;
                }
                size--;
                if (nextIndex > 0) {
                    nextIndex--;
                }
                lastAccessedNode = null;
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
                Node newNode = new Node(e);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else if (currentNode == head) {
                    newNode.next = head;
                    head = newNode;
                } else {
                    Node walkerNode = head;
                    while (walkerNode.next != currentNode) {
                        walkerNode = walkerNode.next;
                    }
                    newNode.next = walkerNode.next;
                    walkerNode.next = newNode;
                    if (newNode.next == null) {
                        tail = newNode;
                    }
                }
                size++;
                nextIndex++;
                lastAccessedNode = null;
            }
        };
    }
    
    @Override
    @NotNull
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || isEmpty() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = head;
        List<E> subList = new SinglyLinkedList<>();
        for (int i = 0; currentNode != null; i++) {
            if (i >= fromIndex && i <= toIndex) {
                subList.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        if (subList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return subList;
    }
    
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
        if (e == null) {
            throw new NullPointerException();
        }
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(criteria)) { //found Node with criteria data
                Node newNode = new Node(e);
                newNode.next = currentNode.next;
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
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return head.data;
    }
    
    @Override
    public E peekLast() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return tail.data;
    }
    
    @Override
    public E removeFirst() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E data = head.data;
        Node oldHead = head;
        if (size == 1) { //TODO: check need for if else
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        oldHead.next = null;
        size--;
        return data;
    }
    
    @Override
    public E removeLast() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E data = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node currentNode = head;
            while (currentNode.next != tail) { // gets the Node before the tail
                currentNode = currentNode.next;
            }
            tail = currentNode;
            tail.next = null;
        }
        size--;
        return data;
    }
    
    @Override
    public void show() {
        if (isEmpty()) {
            System.out.println("The Singly Linked List is empty");
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
            System.out.println("The Singly Linked List is empty");
        } else {
            Stack<E> stack = new Stack<>(); // TODO: use our Stack class
            Node currentNode = head;
            while (currentNode != null) {
                stack.push(currentNode.data);
                currentNode = currentNode.next;
            }
            System.out.println("The Singly Linked list is as follows:");
            while (!stack.isEmpty()) {
                System.out.print(" " + stack.pop());
                if (!stack.isEmpty()) {
                    System.out.print(",");
                }
            }
        }
    }
}
