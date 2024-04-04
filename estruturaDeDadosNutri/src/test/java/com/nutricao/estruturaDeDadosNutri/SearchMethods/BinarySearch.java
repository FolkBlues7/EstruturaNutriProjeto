package com.nutricao.estruturaDeDadosNutri.SearchMethods;

import com.nutricao.estruturaDeDadosNutri.DataStructures.DoublyLinkedList;
import java.util.Collection;
import java.util.Comparator;

public class BinarySearch<E extends Comparable<E>> implements Search<E> {

    private final DoublyLinkedList<E> list;
    private final int size;

    public BinarySearch(Collection<E> list) {
        this.list = new DoublyLinkedList<>();
        this.list.addAll(list);
        size = this.list.size();
    }

    @Override
    public int search(E searchElement) {
        int beginning = 0;
        int end = size - 1;
        int middle = -1;
        while (beginning <= end) {
            middle = (beginning + end) / 2;
            int comparison = list.get(middle).compareTo(searchElement);
            if (comparison < 0) {
                beginning = middle + 1;
            } else if (comparison > 0) {
                end = middle - 1;
            } else { // found the element
                return middle;
            }
        }
        return -1; // return -1 if the element is not found
    }
}
