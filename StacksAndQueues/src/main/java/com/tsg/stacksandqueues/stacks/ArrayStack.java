/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.stacks;

import java.util.Iterator;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ArrayStack implements StackInterface {

    private static final int DEFAULT_INITIAL_SIZE = 4;
    private Object[] items;
    private int numItems = 0;

    public ArrayStack() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public ArrayStack(int size) {
        items = new Object[size];
    }

    @Override
    public void push(Object o) {
        if (numItems == items.length) {
            resize(2 * items.length);
        }
        items[numItems] = o;
        numItems++;
    }

    @Override
    public Object pop() {
        if (numItems > 0) {
            numItems--;

            Object item = items[numItems];
            items[numItems] = null;

            if (numItems == (items.length / 4)) {
                resize(items.length / 2);
            }
            return item;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public Object peek() {
        if (numItems > 0) {
            return items[numItems-1];
        } else {
            return null;
        }
    }

    private void resize(int newSize) {
        Object[] itemsResize = new Object[newSize];
        for (int i = 0; i < numItems; i++) {
            itemsResize[i] = items[i];
        }
        items = itemsResize;
    }

    @Override
    public Iterator iterator() {
        return new ReverseArrayIterator();
    }

    // this is an internal class
    private class ReverseArrayIterator implements Iterator {

        private int i = numItems;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Object next() {
            i--;
            return items[i];
        }

    }

}
