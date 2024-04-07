package com.nutricao.estruturaDeDadosNutri.structures.DataStructures.Interfaces;

import java.util.Collection;
import java.util.List;

public interface ListExtension<E> extends List<E> {
    
    /**
     * Appends the specified element to the start of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to the start of this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     */
    public boolean addFirst(E e);
    
    /**
     * Adds the specified element to after the element that fits the criteria
     * to this list (optional operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended after the element with the criteria
     *          to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     */
    public boolean addAfter(E e, E criteria);
    
    /**
     * Returns the element at the first position in this list.
     *
     * @return {@code element} the element at the start position in this list
     * @throws IndexOutOfBoundsException if the List is empty (size <= 0)
     */
    public E peekFirst();
    
    /**
     * Returns the element at the last position in this list.
     *
     * @return {@code element} the element at the end position in this list
     * @throws IndexOutOfBoundsException if the List is empty (size <= 0)
     */
    public E peekLast();
    
    /**
     * Removes the element at the first position in this list.
     *
     * @return {@code element} the element at the start position in this list
     * @throws IndexOutOfBoundsException if the List is empty (size <= 0)
     */
    public E removeFirst();
    
    /**
     * Removes the element at the last position in this list.
     *
     * @return {@code element} the element at the end position in this list
     * @throws IndexOutOfBoundsException if the List is empty (size <= 0)
     */
    public E removeLast();
    
    /**
     * Shows in the console all the elements in this list.
     *
     */
    public void show();
    
    /**
     * Shows in the console all the elements in this list.
     *
     */
    public void showReversed();
    
}
