/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.queues;

import com.tsg.stacksandqueues.stacks.*;
import java.util.Iterator;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ArrayQueue implements QueueInterface {

    private static final int DEFAULT_INITIAL_SIZE = 4;
    private Object[] items;
    private int head = 0;
    private int tail = 0;
    private int numItems = 0;

    public ArrayQueue() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public ArrayQueue(int size) {
        items = new Object[size];
    }

    @Override
    public void enqueue(Object o) {

        items[tail] = o;
        tail++;
        numItems++;
        if (numItems >= items.length) {
            resize(items.length * 2);
        }
        if (tail == items.length) {
            tail = 0;
        }
    }

    @Override
    public Object dequeue() {
        if (head == tail) {
            throw new IndexOutOfBoundsException("Can not dequeue out of an empty queue.");
        }

        Object o = items[head];
        items[head] = null;
        head++;
        numItems--;
        if (numItems < (items.length / 4)) {
            resize(items.length / 2);
        }
        
        if (head == items.length) {
            head = 0;
        }
        return o;
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
            return items[head];
        } else {
            throw new IndexOutOfBoundsException("Can not peek at an element of an empty queue.");
        }
    }

    private void resize(int newSize) {
        Object[] temp = new Object[newSize];
        for (int i = 0; i < numItems; i++) {
            if ((i + head) == items.length) {
                head = -i;
            }
            temp[i] = items[i + head];
        }
        head = 0;
        tail = numItems;
        items = temp;
    }

    @Override
    public Iterator iterator() {
        return new CircularArrayIterator();
    }

    public void printQueue() {

        for (Object o : items) {
            if (o == null) {
                System.out.print("[__]");
            } else {
                System.out.print("[" + o + "]");
            }
        }
        System.out.println();
    }

    // this is an internal class
    private class CircularArrayIterator implements Iterator {

        private int i = head;

        @Override
        public boolean hasNext() {
            // this assumes that tail is always greater than head!
            return i < tail;
        }

        @Override
        public Object next() {
            Object o = items[i];
            i++;
            if (i == items.length) {
                i = 0;
            }
            return o;
        }

    }

}
