/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.queues;

import com.tsg.stacksandqueues.stacks.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public interface QueueInterfaceGeneric<T> extends Iterable<T> {

    public void enqueue(T item);

    public T dequeue();

    public int size();

    public boolean isEmpty();

    public T peek();

    public void printQueue();

}
