package com.nutricao.estruturaDeDadosNutri.structures.SortingMethods;

import java.util.Collection;

public interface Sorter<E extends Comparable<E>> {

    Collection<E> sort(Collection<E> list);

}
