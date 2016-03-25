/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class TimingAspect {
    public Object timingMethod(ProceedingJoinPoint jp) throws Throwable {
        Object ret = null;

        try {
            long start = System.nanoTime();
            ret = jp.proceed();
            long end = System.nanoTime();
            System.out.println("[INFO] " + jp.getSignature().getName() + " took " + (end - start) + "ns");
        } catch (Throwable ex) {
            System.out.println("Exception in SimpleTimerMethodAspect.timeMethod");
            throw ex;
        }
        return ret;
    }
}
