package com.tsg.stacksandqueues.linkedlist;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tsg.stacksandqueues.queues.*;
import com.thesoftwareguild.queue.LinkedListQueue;
import com.thesoftwareguild.queue.Queue;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ilyagotfryd
 */
public class LinkedListQueueTest {

    public LinkedListQueueTest() {
    }

    QueueInterfaceGeneric<Integer> queue = null;

    @Before
    public void setUp() {
        queue = new LinkedListQueueImpl<>();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void properEmptyResponse() {

        try {
            queue.peek();
            Assert.assertFalse(true);
        } catch (IndexOutOfBoundsException exp) {
            Assert.assertTrue(true);
        } catch (Exception exp) {
//            Assert.assertFalse("Peek : wrong exception type thrown", true);
        }

        try {
            queue.dequeue();
            Assert.assertFalse(true);
        } catch (IndexOutOfBoundsException exp) {
            Assert.assertTrue(true);
        } catch (Exception exp) {
//            Assert.assertFalse("Dequeue : wrong exception type thrown", true);
        }

    }

    @Test
    public void enqueueDequeueOneElement() {
        queue.enqueue(20);
        Integer expected = 20;
        Assert.assertEquals(expected, queue.peek());
        Assert.assertEquals(1, queue.size());
        Integer result = queue.dequeue();
        Assert.assertEquals(expected, result);
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
        assertEquals(0, queue.size());

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
        queue.enqueue(12);
        queue.enqueue(10);
        queue.dequeue();
        queue.enqueue(20);
        queue.dequeue();
        queue.enqueue(21);
        queue.dequeue();
        queue.enqueue(22);
        queue.enqueue(23);

        assertEquals((Integer) 20, queue.dequeue());
        assertEquals((Integer) 21, queue.dequeue());
        assertEquals((Integer) 22, queue.dequeue());
        assertEquals((Integer) 23, queue.dequeue());
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
    public void fifoEatsOwnTailIterator() {
        ArrayList<Integer> inNumbers = new ArrayList<>();
        inNumbers.add(20);
        inNumbers.add(21);
        inNumbers.add(22);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(10);
        queue.dequeue();
        queue.enqueue(20);
        queue.dequeue();
        queue.enqueue(21);
        queue.dequeue();
        queue.enqueue(22);

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

//    @Test
//    public void testDoesAuthorReturn() {
//        assertEquals("David Johnson & Scott Stahl", queue.getAuthorName());
//    }
    @Test
    public void fifoDoesNull() {
        queue.enqueue(null);
        assertEquals(1, queue.size());
        Integer num = queue.dequeue();
        assertEquals(null, num);
    }

//    @Test
//    public void fifoGoesWest() {
//        for (int i = 0; i < 10000000; i++) {
//            queue.enqueue(i);
//        }
//        for (int i = 0; i < 9999999; i++) {
//            queue.dequeue();
//        }
//        assertEquals((Integer) 9999999, queue.dequeue());
//    }
//
//    @Test
//    public void fifoGoesWestABunch() {
//        long startTime, estimatedTime, averageTime = 0;
//        for (int i = 0; i < 4; i++) {
//            fifoGoesWest();
//        }
//        
//        for (int i = 0; i < 10; i++) {
//            startTime = System.currentTimeMillis();
//            fifoGoesWest();
//            estimatedTime = System.currentTimeMillis() - startTime;
//            System.out.println(estimatedTime);
//            averageTime += estimatedTime;
//        }
//        System.out.println("Average = " + averageTime/10);
//    }
    //    7331
    //    9585
    //    1850
    //    3919
    //    5075
    //    1936
    //    6645
    //    4052
    //    4067
    //    3961
    //    Average  = 4842
}
