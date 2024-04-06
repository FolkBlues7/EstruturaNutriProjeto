package com.nutricao.estruturaDeDadosNutri.DataStructures.Interfaces;

import java.util.List;
import java.util.Vector;

public interface StackExtension<E> {

    public E peek();
    public E pop();
    public void push(E element);
    public int size();
    public boolean isEmpty();
    public boolean contains(Object o);

}