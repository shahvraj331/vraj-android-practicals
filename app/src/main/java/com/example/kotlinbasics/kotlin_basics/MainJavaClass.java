package com.example.kotlinbasics.kotlin_basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainJavaClass {
    public static void main(String[] args) {
        /*  Data types  */
        boolean isChecked = true;          //true or false
        char givenCharacter = 'a';        //can be any character
        byte givenByte = 10;                //range is [-128, 127]
        short givenShort = 1000;           //range is [-32768, 32767]
        int givenInt = 100000;              //range is [-2^31, 2^31-1]
        long givenLong = 1000L;             //range is [-2^63, 2^63-1]
        float givenFloat = 234.5f;          //range is unlimited and default value if 0.0F
        double givenDouble = 12.13;         //range is unlimited and default value is 0.0d
        System.out.println("\n" + isChecked + " " + givenCharacter + " " + givenByte + " " + givenShort + " " + givenInt + " " +
                givenLong + " " + givenFloat + " " + givenDouble + "\n");

        /*  Type Conversion */
        givenDouble = givenFloat;           //implicit conversion
        givenInt = (int)givenLong;          //explicit conversion
        System.out.println(givenDouble + " " + givenInt + "\n");

        /*  Operator  */
        givenLong = givenShort;                           //Assignment operator (=,+=,-=,*=,/=)
        System.out.print(givenLong + " ");
        System.out.print(++givenInt + " ");               //Unary operator (a++,++a,+a,-a,~,!)
        System.out.print(givenByte + givenShort + " ");   //Arithmetic operator (+,-,&,/,%)
        System.out.print((givenByte < givenShort) + " "); //Comparison operator (<,>,<=,>=,==,!=)
        System.out.print((isChecked && true) + " ");      //Logical operator (&&, \\)
        int getMaxValue = givenInt > givenByte ? givenInt : givenByte;
        System.out.println(getMaxValue + "\n");           //Ternary operator

        /*  if expression  */
        if (givenInt > givenByte)
            System.out.println(givenInt + " is greater than " + givenByte + "\n");

        /*  if-else expression  */
        if (givenInt > givenByte)
            System.out.println(givenInt + " is greater than " + givenByte + "\n");
        else
            System.out.println(givenByte + " is greater than " + givenInt + "\n");

        /* if-else if-else ladder   */
        if (givenInt >= 100 && givenInt <= 500) {
            System.out.println(givenInt + " lies between 100 and 500" + "\n");
        } else if (givenInt > 500 && givenInt <= 1000) {
            System.out.println(givenInt + " lies between 500 and 1000" + "\n");
        } else {
            System.out.println(givenInt + " is greater than 1000" + "\n");
        }

        /*  switch  */
        int currentDay = 4;
        switch (currentDay) {
            case 1:
                System.out.println("Monday\n");
                break;
            case 2:
                System.out.println("Tuesday\n");
                break;
            case 3:
                System.out.println("Wednesday\n");
                break;
            case 4:
                System.out.println("Thursday\n");
                break;
            case 5:
                System.out.println("Friday\n");
                break;
            case 6:
                System.out.println("Saturday\n");
                break;
            case 7:
                System.out.println("Sunday\n");
                break;
        }

        /*  for loop with break, labelled break, continue  */
        outerMostLoop:
        for (int firstIndex = 0; firstIndex < 5; firstIndex++) {
            if (firstIndex == 1)
                continue;
            for (int secondIndex = 0; secondIndex < 2; secondIndex++) {
                if (firstIndex == 2)
                    break;
                if (firstIndex == 4)
                    break outerMostLoop;
                System.out.println(firstIndex + " " + secondIndex);
            }
        }
        System.out.println();

        /*  function calling  */
        printMaxValue(100,50);

        /*  Lambda expression  */
        Runnable runFunction = () -> System.out.println("Thread2 is running...\n");
        Thread currentThread = new Thread(runFunction);
        currentThread.start();

        /*  Higher order function (A function which takes function as a parameter or returns a function)  */
        List<String> list = new ArrayList<>();
        list.add("BBC");
        list.add("bbc");
        list.add("ACD");
        Collections.sort(list, (String a, String b) -> a.compareTo(b));
        System.out.println(list + "\n");

        /* Class and inheritance  */
        VehicleFunctionality vehicleFunctionality = new VehicleFunctionality();
        vehicleFunctionality.printDetails();
    }

    static void printMaxValue(int firstNumber, int secondNumber) {
        if (firstNumber > secondNumber)
            System.out.println(firstNumber + " is greater than " + secondNumber + "\n");
        else
            System.out.println(secondNumber + " is greater than " + firstNumber + "\n");
    }
}

class Vehicle {
    int modelOfCar;
    boolean isVerified;
}

class VehicleFunctionality extends Vehicle {
    int maxKms;

    public VehicleFunctionality() {
        modelOfCar = 210;
        isVerified = true;
        maxKms = 10000;
    }
    void printDetails() {
        System.out.println("Model no: " + modelOfCar +"\nMaximum kilometers: " + maxKms +
                "\nIs this car verified? " + isVerified + "\n");
    }
}
