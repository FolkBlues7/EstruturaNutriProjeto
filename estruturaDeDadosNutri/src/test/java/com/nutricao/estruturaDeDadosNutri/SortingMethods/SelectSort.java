package com.nutricao.estruturaDeDadosNutri.SortingMethods;

import com.nutricao.estruturaDeDadosNutri.DataStructures.DoublyLinkedList;
import java.util.Collection;

public class SelectSort<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public Collection<E> sort(Collection<E> list) {
        DoublyLinkedList<E> listAsList = new DoublyLinkedList<>();
        listAsList.addAll(list);
        for (int i = 0; i < listAsList.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < listAsList.size(); j++) {
                if (listAsList.get(j).compareTo(listAsList.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            E temp = listAsList.get(i);
            listAsList.set(i, listAsList.get(minIndex));
            listAsList.set(minIndex, temp);
        }
        return listAsList;
    }

}
