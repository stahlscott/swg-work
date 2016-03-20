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
public interface QueueInterface extends Iterable {

    public void enqueue(Object o);

    public Object dequeue();

    public int size();

    public boolean isEmpty();

    public Object peek();
    
    public void printQueue();

}
