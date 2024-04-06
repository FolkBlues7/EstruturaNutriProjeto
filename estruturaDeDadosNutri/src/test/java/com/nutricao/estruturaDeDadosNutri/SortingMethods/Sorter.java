package com.nutricao.estruturaDeDadosNutri.SortingMethods;

import java.util.Collection;
import java.util.List;

public interface Sorter<E extends Comparable<E>> {

    Collection<E> sort(Collection<E> list);

}
