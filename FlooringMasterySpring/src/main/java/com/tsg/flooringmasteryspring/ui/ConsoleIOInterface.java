/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.flooringmasteryspring.ui;

import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public interface ConsoleIOInterface {

    public int getInteger(String promptValue);

    public int getInteger(String promptValue, int minValue, int maxValue);

    public void checkMinMaxInt(int inputValue, int minValue, int maxValue) throws Exception;

    public String getString(String promptValue);

    public float getFloat(String promptValue);

    public float getFloat(String promptValue, float minValue, float maxValue);

    public void checkMinMaxFloat(float inputValue, float minValue, float maxValue) throws Exception;

    public double getDouble(String promptValue);

    public double getDouble(String promptValue, double minValue, double maxValue);

    public void print(String promptValue);

}
