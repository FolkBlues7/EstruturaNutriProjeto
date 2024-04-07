package com.nutricao.estruturaDeDadosNutri.structures.SearchMethods;

import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;
import java.util.Collection;
import java.util.List;

public class LinearSearch<E extends Comparable<E>> implements Search<E> {

    @Override
    public int search(Collection<E> list, E searchElement) {
        List<E> listAsList = new DoublyLinkedList<E>();
        listAsList.addAll(list);
        for (int i = 0; i < listAsList.size(); i++) {
            if (listAsList.get(i).compareTo(searchElement) == 0) {
                return i;
            }
        }
        return -1;
    }

}
