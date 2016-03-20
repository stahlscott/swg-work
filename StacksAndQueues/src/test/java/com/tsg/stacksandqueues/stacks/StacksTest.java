/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.stacksandqueues.stacks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class StacksTest {

    StackInterface stack;

    public StacksTest() {
    }

    @Before
    public void setUp() {
        stack = new ArrayStack();
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
    public void testPush() {
        System.out.println("push");
        String s1 = "aardvark";
        String s2 = "baardvark";
        String s3 = "caardvark";

        stack.push(s1);
        stack.push(s2);
        stack.push(s3);

        String sX = (String) stack.peek();
        Assert.assertEquals(s3, sX);

    }

    /**
     * Test of pop method, of class ArrayStack.
     */
    @Test
    public void testPop() {
        System.out.println("pop");
        String s1 = "aardvark";
        String s2 = "baardvark";
        String s3 = "caardvark";

        stack.push(s1);
        stack.push(s2);
        stack.push(s3);

        String sX = (String) stack.pop();
        Assert.assertEquals(s3, sX);

    }

    /**
     * Test of size method, of class ArrayStack.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        String s1 = "aardvark";
        String s2 = "baardvark";
        String s3 = "caardvark";

        stack.push(s1);
        stack.push(s2);
        stack.push(s3);

        Integer result = stack.size();
        Integer expected = 3;
        Assert.assertEquals(expected, result);
    }

    /**
     * Test of isEmpty method, of class ArrayStack.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        String s1 = "aardvark";
        String s2 = "baardvark";
        String s3 = "caardvark";

        Assert.assertTrue(stack.isEmpty());

        stack.push(s1);
        stack.push(s2);
        stack.push(s3);

        Assert.assertFalse(stack.isEmpty());

        stack.pop();
        stack.pop();
        stack.pop();

        Assert.assertTrue(stack.isEmpty());
    }

    /**
     * Test of iterator method, of class ArrayStack.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");

        String s1 = "aardvark";
        String s2 = "baardvark";
        String s3 = "caardvark";

        ArrayList<String> expected = new ArrayList<>();
        expected.add(s1);
        expected.add(s2);
        expected.add(s3);

        stack.push(s3);
        stack.push(s2);
        stack.push(s1);

        Iterator iterator = stack.iterator();
        List<String> result = new ArrayList<>();

        while (iterator.hasNext()) {

            result.add((String) iterator.next());

        }

        Assert.assertEquals(expected, result);
    }
}
