package com.nutricao.estruturaDeDadosNutri.Interfaces;

import java.util.List;

public interface StackExtension<E> extends List<E> {

    E peek();
    E pop();
    E push(E element);
    boolean empty();
    int search(Object o);

}