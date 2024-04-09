package com.nutricao.estruturaDeDadosNutri.structures.SortingMethods;

import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;
import com.nutricao.estruturaDeDadosNutri.structures.SortingMethods.Sorter;
import java.util.Collection;
import java.util.List;

public class BubbleSort<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public List<E> sort(Collection<E> list) {
        List<E> listAsList = new DoublyLinkedList<>();
        listAsList.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (listAsList.get(j).compareTo(listAsList.get(j + 1)) > 0) { // swap
                    E temp = listAsList.get(j);
                    listAsList.set(j, listAsList.get(j + 1));
                    listAsList.set(j + 1, temp);
                }
            }
        }
        return listAsList;
    }

}
