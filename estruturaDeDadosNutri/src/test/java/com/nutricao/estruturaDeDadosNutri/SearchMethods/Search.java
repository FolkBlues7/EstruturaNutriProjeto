package com.nutricao.estruturaDeDadosNutri.SearchMethods;

import java.util.Collection;

public interface Search<E extends Comparable<E>> {

    int search(Collection<E> list,  E searchElement);

}
