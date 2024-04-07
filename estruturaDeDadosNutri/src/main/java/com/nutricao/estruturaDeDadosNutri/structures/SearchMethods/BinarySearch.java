package com.nutricao.estruturaDeDadosNutri.structures.SearchMethods;

import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;
import java.util.Collection;
import java.util.List;

public class BinarySearch<E extends Comparable<E>> implements Search<E> {

    @Override
    public int search(Collection<E> list, E searchElement) {
        List<E> listAsList = new DoublyLinkedList<E>();
        listAsList.addAll(list);
        int beginning = 0;
        int end = listAsList.size() - 1;
        int middle = -1;
        while (beginning <= end) {
            middle = (beginning + end) / 2;
            int comparison = listAsList.get(middle).compareTo(searchElement);
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
