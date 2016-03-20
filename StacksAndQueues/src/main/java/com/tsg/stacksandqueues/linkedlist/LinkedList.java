/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.linkedlist;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public interface LinkedList<T> extends Iterable<T> {

    //append to end of list
    void append(T item);

    T get(int index);

    //insert after given index
    void insert(int index, T item);

    boolean isEmpty();

    //insert at beginning
    void prepend(T item);

    T remove(int index);

    int size();
}
