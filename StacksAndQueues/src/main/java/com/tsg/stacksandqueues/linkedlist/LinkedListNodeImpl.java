/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.linkedlist;

import java.util.Iterator;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class LinkedListNodeImpl<Item> implements LinkedList<Item> {

    private Node first;
    private Node last;
    private int numItems = 0;

    @Override
    public void append(Item item) {
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
    public Item get(int index) {
        return getNode(index).item;
    }

    @Override
    public void insert(int index, Item item) {
        if (index == numItems - 1) {
            append(item);
        } else {
            Node nodeAtIndex = getNode(index);
            Node newNode = new Node();
            newNode.item = item;
            newNode.next = nodeAtIndex.next;
            nodeAtIndex.next = newNode;
            numItems++;
        }
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void prepend(Item item) {
        Node prevFirst = first;
        first = new Node();
        first.item = item;
        first.next = prevFirst;
        numItems++;

        if (numItems == 1) {
            last = first;
        }
    }

    @Override
    public Item remove(int index) {
        Item item = null;

        if (index == 0) {
            item = first.item;
            first = first.next;
            if (index == numItems - 1) {
                last = null;
            }
        } else {
            Node nodeBefore = getNode(index - 1);
            item = nodeBefore.next.item;
            nodeBefore.next = nodeBefore.next.next;
            if (index == numItems - 1) {
                last = nodeBefore;
            }
        }
        numItems--;
        return item;
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<T> implements Iterator<T> {

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

    private Node getNode(int index) {
        if (index >= numItems || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (numItems - 1) + " inclusive.");
        }
        Node nodeAtIndex = first;
        for (int i = 0; i < index; i++) {
            nodeAtIndex = nodeAtIndex.next;
        }
        return nodeAtIndex;
    }

}
