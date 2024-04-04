package com.nutricao.estruturaDeDadosNutri.SearchMethods;

import com.nutricao.estruturaDeDadosNutri.DataStructures.DoublyLinkedList;
import java.util.Collection;

public class LinearSearch<E extends Comparable<E>> implements Search<E> {

    private final DoublyLinkedList<E> list;
    private final int size;

    public LinearSearch(Collection<E> list) {
        this.list = new DoublyLinkedList<>();
        this.list.addAll(list);
        this.size = this.list.size();
    }

    @Override
    public int search(E searchElement) {
        for (int i = 0; i < size; i++) {
            if (list.get(i).compareTo(searchElement) == 0) {
                return i;
            }
        }
        return -1;
    }

}
