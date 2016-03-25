/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class AuditAspect {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("[MM-dd-yyyy] [HH:mm:ss]");
    private final String AUDIT_FILE = ".audit_trail.txt";
    
    public void audit(JoinPoint jp) {
        
        Calendar cal = Calendar.getInstance();
        String currentDateTime = DATE_FORMAT.format(cal.getTime());
        
        PrintWriter out;
        try {
            out = new PrintWriter(new FileOutputStream(new File(AUDIT_FILE), true));
            out.append("\n" + currentDateTime + " " + jp.getSignature().getName());

            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuditAspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
