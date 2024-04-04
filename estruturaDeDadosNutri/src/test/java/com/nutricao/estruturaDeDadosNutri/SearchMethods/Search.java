package com.nutricao.estruturaDeDadosNutri.SearchMethods;

public interface Search<E extends Comparable<E>> {

    int search(E searchElement);

}
