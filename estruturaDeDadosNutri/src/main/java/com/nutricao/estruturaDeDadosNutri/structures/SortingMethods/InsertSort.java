package com.nutricao.estruturaDeDadosNutri.structures.SortingMethods;

import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;
import com.nutricao.estruturaDeDadosNutri.structures.SortingMethods.Sorter;
import java.util.Collection;

public class InsertSort<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public Collection<E> sort(Collection<E> list) {
        DoublyLinkedList<E> listAsList = new DoublyLinkedList<>();
        listAsList.addAll(list);
        for (int pivot = 0; pivot < listAsList.size() - 1; pivot++) {
            E pivotValue = listAsList.get(pivot);
            int i = pivot - 1;
            while (i >= 0 && listAsList.get(i).compareTo(pivotValue) > 0) {
                listAsList.set(i + 1, listAsList.get(i));
                i--;
            }
            listAsList.set(i + 1, pivotValue);
        }
        return listAsList;
    }

}
