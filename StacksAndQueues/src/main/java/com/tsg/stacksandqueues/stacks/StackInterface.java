/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.stacks;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public interface StackInterface extends Iterable {

    public void push(Object o);

    public Object pop();

    public int size();

    public boolean isEmpty();

    public Object peek();

}
