/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.queues;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class GenericQueueTest {

    QueueInterfaceGeneric<Integer> queue = new ArrayQueueGeneric<>();

    public GenericQueueTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void properEmptyResponse() {

        try {
            queue.peek();
            fail();
        } catch (IndexOutOfBoundsException exp) {
            Assert.assertTrue(true);
        } catch (Exception exp) {
            Assert.assertFalse("Peek : wrong exception type thrown", true);
        }

        try {
            queue.dequeue();
            fail();
        } catch (IndexOutOfBoundsException exp) {
            Assert.assertTrue(true);
        } catch (Exception exp) {
            Assert.assertFalse("Dequeue : wrong exception type thrown", true);
        }

    }

    @Test
    public void enqueueDequeueOneElement() {
        Integer num = 20;
        queue.enqueue(num);
        Integer expected = 20;
        Assert.assertEquals(expected, queue.peek());
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals(expected, queue.dequeue());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void fifoIsFunctional() {

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(42);
        Assert.assertEquals((Integer) 10, queue.dequeue());
        Assert.assertEquals((Integer) 20, queue.dequeue());
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals((Integer) 30, queue.dequeue());
        Assert.assertEquals((Integer) 42, queue.dequeue());
    }

    @Test
    public void fifoCanEmptyAndRefill() {
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(42);
        Assert.assertEquals((Integer) 10, queue.dequeue());
        Assert.assertEquals((Integer) 20, queue.dequeue());
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals((Integer) 30, queue.dequeue());
        Assert.assertEquals((Integer) 42, queue.dequeue());
    }

    @Test
    public void fifoCanPartiallyEmptyAndRefill() {
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.dequeue();
        queue.dequeue();

        queue.enqueue(30);
        queue.enqueue(42);

        Assert.assertEquals((Integer) 10, queue.dequeue());
        Assert.assertEquals((Integer) 20, queue.dequeue());
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals((Integer) 30, queue.dequeue());
        Assert.assertEquals((Integer) 42, queue.dequeue());
    }

    @Test
    public void fifoEatsOwnTail() {
        // how would we do this as a black box test?
        queue.enqueue(11);
        queue.printQueue();
        queue.enqueue(12);
        queue.printQueue();
        queue.enqueue(10);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(20);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(21);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(22);
        queue.printQueue();
        queue.enqueue(23);
        //extended test starts here
        queue.printQueue();
        queue.enqueue(24);
        queue.printQueue();
        queue.enqueue(25);
        queue.printQueue();
        queue.enqueue(26);
        queue.printQueue();
        queue.enqueue(27);
        queue.printQueue();
        queue.enqueue(28);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(29);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(30);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(31);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(32);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(33);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(34);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(35);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(36);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(37);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(38);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(39);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(40);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(41);
        queue.printQueue();
        System.out.println("-----------------------------8<---------------------------------");
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();

        assertEquals((Integer) 38, queue.dequeue());
        queue.printQueue();
        assertEquals((Integer) 39, queue.dequeue());
        queue.printQueue();
        assertEquals((Integer) 40, queue.dequeue());
        queue.printQueue();
        assertEquals((Integer) 41, queue.dequeue());
        queue.printQueue();
    }

    @Test
    public void iteratorTest() {

        ArrayList<Integer> inNumbers = new ArrayList<>();
        inNumbers.add(11);
        inNumbers.add(12);
        inNumbers.add(10);
        inNumbers.add(20);

        queue.enqueue(inNumbers.get(0));
        queue.enqueue(inNumbers.get(1));
        queue.enqueue(inNumbers.get(2));
        queue.enqueue(inNumbers.get(3));

        ArrayList<Integer> outNumbers = new ArrayList<>();

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {

            String s = iterator.next().toString();
            outNumbers.add(Integer.parseInt(s));

        }

        Assert.assertEquals(inNumbers, outNumbers);

    }

    @Test
    public void iteratorSnakeTest() {

        ArrayList<Integer> inNumbers = new ArrayList<>();
        inNumbers.add(33);
        inNumbers.add(34);
        inNumbers.add(35);
        inNumbers.add(36);
        inNumbers.add(37);
        inNumbers.add(38);
        inNumbers.add(39);
        inNumbers.add(40);
        inNumbers.add(41);

        queue.enqueue(11);
        queue.printQueue();
        queue.enqueue(12);
        queue.printQueue();
        queue.enqueue(10);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(20);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(21);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(22);
        queue.printQueue();
        queue.enqueue(23);
        queue.printQueue();
        queue.enqueue(24);
        queue.printQueue();
        queue.enqueue(25);
        queue.printQueue();
        queue.enqueue(26);
        queue.printQueue();
        queue.enqueue(27);
        queue.printQueue();
        queue.enqueue(28);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(29);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(30);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(31);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(32);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(33);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(34);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(35);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(36);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(37);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(38);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(39);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(40);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(41);
        queue.printQueue();

        ArrayList<Integer> outNumbers = new ArrayList<>();

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {

            String s = iterator.next().toString();
            outNumbers.add(Integer.parseInt(s));

        }

        Assert.assertEquals(inNumbers, outNumbers);

    }

    @Test
    public void isEmptyMethodTest() {

        boolean result = queue.isEmpty();
        boolean expected = true;

        Assert.assertEquals(expected, result);

        queue.enqueue(5);

        result = queue.isEmpty();
        expected = false;

        Assert.assertEquals(expected, result);

    }

    @Test
    public void testSize() {

        Integer result = queue.size();
        Integer expected = 0;

        Assert.assertEquals(expected, result);

        queue.enqueue(5);

        result = queue.size();
        expected = 1;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testPeekDoesNotEmpty() {

        queue.enqueue(5);
        Integer number1 = (Integer) queue.peek();
        Integer result = 5;
        Assert.assertEquals(queue.peek(), result);
        Integer size = queue.size();
        Integer expected = 1;
        Assert.assertEquals(expected, size);

    }
}
