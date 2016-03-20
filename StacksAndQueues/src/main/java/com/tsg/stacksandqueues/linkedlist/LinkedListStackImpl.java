/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.linkedlist;

import com.tsg.stacksandqueues.stacks.StackInterface;
import java.util.Iterator;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class LinkedListStackImpl implements StackInterface {

    private Node top;
    private int numItems = 0;

    @Override
    public void push(Object o) {
        Node prevFirst = top;
        top = new Node();
        top.item = o;
        top.next = prevFirst;
        numItems++;
    }

    @Override
    public Object pop() {
        Object o = top.item;
        top = top.next;
        numItems--;
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
        return top.item;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListStackIterator();
    }

    private class LinkedListStackIterator implements Iterator {

        Node current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (current == null) {
                return null;
            } else {
                Object o = current.item;
                current = current.next;
                return o;
            }
        }
    }

    private class Node {

        Object item;
        Node next;
    }
}
