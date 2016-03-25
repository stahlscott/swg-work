/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class App {

    public static void main(String[] args) {
        
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = ctx.getBean("controller", FlooringController.class);
        
//        FlooringController flooringController = new FlooringController();
        controller.run();
    }
}
