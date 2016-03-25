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
public class ConsoleIOImpl implements ConsoleIOInterface {

    private Scanner sc = new Scanner(System.in);

    public ConsoleIOImpl() {

    }

    /**
     * Returns user input as an integer after given prompt.
     *
     * @param promptValue
     * @return user input value as integer
     */
    @Override
    public int getInteger(String promptValue) {

        int inputValue = 0;

        boolean exceptionCheck = true;

        while (exceptionCheck) {

            System.out.println(promptValue);
            String inputString = sc.nextLine();
            try {
                inputValue = Integer.parseInt(inputString);
                exceptionCheck = false;
            } catch (Exception ex) {
                System.out.println("Invalid input. Please type an integer.");
                exceptionCheck = true;
            }
        }

        return inputValue;
    }

    /**
     * Returns user input as integer within min max range.
     *
     * @param promptValue
     * @param minValue
     * @param maxValue
     * @return user input value as integer
     */
    @Override
    public int getInteger(String promptValue, int minValue, int maxValue) {
        int inputValue = 0;
        boolean exceptionCheck = true;

        while (exceptionCheck) {

            System.out.println(promptValue);
            String inputString = sc.nextLine();

            try {
                inputValue = Integer.parseInt(inputString);

                try {
                    checkMinMaxInt(inputValue, minValue, maxValue);
                    exceptionCheck = false;
                } catch (Exception ex) {
                    System.out.println("Invalid input. Integer must be between " + minValue + " and " + maxValue + ".");
                    exceptionCheck = true;
                }

            } catch (Exception ex) {
                System.out.println("Invalid input. Please type an integer.");
                exceptionCheck = true;
            }
        }

        return inputValue;

    }

    /**
     * Throws exception if user input is out of range.
     *
     * @param inputValue
     * @param minValue
     * @param maxValue
     * @throws Exception
     */
    @Override
    public void checkMinMaxInt(int inputValue, int minValue, int maxValue) throws Exception {
        if (inputValue < minValue || inputValue > maxValue) {
            throw new Exception("Integer is outside of range.");
        }
    }

    /**
     * Returns user input as a String
     *
     * @param promptValue
     * @return user input as a String
     */
    @Override
    public String getString(String promptValue) {

        System.out.println(promptValue);
        return sc.nextLine();
       
    }

    /**
     * Returns user input as a float after printing promptValue
     *
     * @param promptValue
     * @return user input as a float
     */
    @Override
    public float getFloat(String promptValue) {
        float inputValue = 0;

        boolean exceptionCheck = true;

        while (exceptionCheck) {
            System.out.println(promptValue);
            String inputString;
            inputString = sc.nextLine();

            try {
                inputValue = Float.parseFloat(inputString);
                exceptionCheck = false;
            } catch (Exception ex) {
                System.out.println("Invalid type, please type in a float.");
                exceptionCheck = true;

            }
        }

        return inputValue;

    }

    /**
     * Returns input as a float after printing promptValue and verifying it is between minValue and maxValue
     *
     * @param promptValue
     * @param minValue
     * @param maxValue
     * @return user input as a float
     */
    @Override
    public float getFloat(String promptValue, float minValue, float maxValue) {
        float inputValue = 0;

        boolean exceptionCheck = true;

        while (exceptionCheck) {
            System.out.println(promptValue);
            String inputString;
            inputString = sc.nextLine();

            try {
                inputValue = Float.parseFloat(inputString);

                try {
                    checkMinMaxFloat(inputValue, minValue, maxValue);
                    exceptionCheck = false;
                } catch (Exception ex) {
                    System.out.println("Invalid input. Float must be between " + minValue + " and " + maxValue + ".");
                }
            } catch (Exception ex) {
                System.out.println("Invalid type, please type in a float.");
                exceptionCheck = true;

            }
        }
        return inputValue;
    }

    /**
     * Throws exception if user input is out of range.
     *
     * @param inputValue
     * @param minValue
     * @param maxValue
     * @throws Exception
     */
    @Override
    public void checkMinMaxFloat(float inputValue, float minValue, float maxValue) throws Exception {
        if (inputValue < minValue || inputValue > maxValue) {
            throw new Exception("Float is outside of range.");
        }
    }

    /**
     * Returns user input as a double after printing promptValue
     *
     * @param promptValue
     * @return user input as a double
     */
    @Override
    public double getDouble(String promptValue) {

        double inputValue = 0;

        boolean exceptionCheck = true;

        while (exceptionCheck) {

            System.out.println(promptValue);
            String inputString = sc.nextLine();
            try {
                inputValue = Double.parseDouble(inputString);
                exceptionCheck = false;
            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter value of double data type.");
                exceptionCheck = true;
            }
        }

        return inputValue;

    }

    /**
     * Returns user input as a double after printing promptValue and ensuring it is between minValue and maxValue
     *
     * @param promptValue
     * @param minValue
     * @param maxValue
     * @return user input as a double
     */
    @Override
    public double getDouble(String promptValue, double minValue, double maxValue) {

        double inputValue = 0;
        boolean exceptionCheck = true, valueCheck = false;

        while (exceptionCheck) {

            try {
                System.out.println(promptValue);
                String inputString = sc.nextLine();
                inputValue = Double.parseDouble(inputString);

                if (inputValue < minValue || inputValue > maxValue) {
                    System.out.println("Invalid input. Double value must be between " + minValue + " and " + maxValue + ".");
                    exceptionCheck = true;
                } else {
                    exceptionCheck = false;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter value of double data type.");
                exceptionCheck = true;
            }
        }

        return inputValue;
    }

    /**
     * Prints a given promptValue to the console
     *
     * @param promptValue
     */
    @Override
    public void print(String promptValue) {
        System.out.println(promptValue);
    }

}
