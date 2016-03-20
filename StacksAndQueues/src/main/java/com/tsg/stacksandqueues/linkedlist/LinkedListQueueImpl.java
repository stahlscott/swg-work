/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.linkedlist;

import com.tsg.stacksandqueues.queues.QueueInterfaceGeneric;
import java.util.Iterator;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class LinkedListQueueImpl<Item> implements QueueInterfaceGeneric<Item> {

    private Node first;
    private Node last;
    private int numItems = 0;

    @Override
    public void enqueue(Item item) {
        Node prevLast = last;
        last = new Node();
        last.item = item;
        if (prevLast == null) {
            first = last;
        } else {
            prevLast.next = last;
        }
        numItems++;
    }

    @Override
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        numItems--;
        if (numItems == 0) {
            last = null;
        }
        return item;
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
    public Item peek() {
        return first.item;
    }

    @Override
    public void printQueue() {
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListQueueIterator<>();
    }

    private class LinkedListQueueIterator<T> implements Iterator<T> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public T next() {
            if (current == null) {
                return null;
            } else {
                T item = (T) current.item;
                current = current.next;
                return item;
            }
        }
    }

    private class Node {

        Item item;
        Node next;
    }
}
