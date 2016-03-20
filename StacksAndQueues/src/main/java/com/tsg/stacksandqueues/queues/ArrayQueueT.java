/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.queues;

import com.thesoftwareguild.queue.Queue;
import com.tsg.stacksandqueues.stacks.*;
import java.util.Iterator;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ArrayQueueT<ItemType> implements Queue<ItemType> {

    private static final int DEFAULT_INITIAL_SIZE = 4;
    private static final String AUTHORS = "David Johnson & Scott Stahl";
    private ItemType[] items;
    private int head = 0;
    private int tail = 0;
    private int numItems = 0;

    public ArrayQueueT() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public ArrayQueueT(int size) {
        items = (ItemType[]) new Object[size];
    }

    @Override
    public void enqueue(ItemType o) {

        if (numItems >= items.length) {
            resize(items.length * 2);
        }
        if (tail == items.length) {
            tail = 0;
        }
        items[tail] = o;
        tail++;
        numItems++;
    }

    @Override
    public ItemType dequeue() {
        if (numItems == 0) {
            throw new IndexOutOfBoundsException("Can not dequeue out of an empty queue.");
        }

        ItemType o = (ItemType) items[head];
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
    public Boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public ItemType peek() {
        if (numItems > 0) {
            return (ItemType) items[head];
        } else {
            throw new IndexOutOfBoundsException("Can not peek at an element of an empty queue.");
        }
    }

    private void resize(int newSize) {
        ItemType[] temp = (ItemType[]) new Object[newSize];
        for (int i = 0; i < numItems; i++) {
            if ((i + head) == items.length) {
                head = -i;
            }
            temp[i] = (ItemType) items[i + head];
        }
        head = 0;
        tail = numItems;
        items = temp;
    }

    @Override
    public Iterator<ItemType> iterator() {
        return new CircularArrayIterator<ItemType>(items);
    }

    @Override
    public String getAuthorName() {
        return AUTHORS;
    }

    private class CircularArrayIterator<T> implements Iterator<T> {

        private T[] iterItems;

        private int i = head;
        private int j = 0;

        public CircularArrayIterator(T[] iterItems) {
            this.iterItems = iterItems;
        }

        @Override
        public boolean hasNext() {
            return j < numItems;
//            return i < tail;
        }

        @Override
        public T next() {
            T o = iterItems[i];
            i++;
            if (i == iterItems.length) {
                i = 0;
            }
            j++;
            return o;
        }

    }

}
