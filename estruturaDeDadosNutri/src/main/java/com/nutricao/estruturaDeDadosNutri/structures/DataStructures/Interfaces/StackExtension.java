package com.nutricao.estruturaDeDadosNutri.structures.DataStructures.Interfaces;

public interface StackExtension<E> {

    public E peek();
    public E pop();
    public void push(E element);
    public int size();
    public boolean isEmpty();
    public boolean contains(Object o);

}