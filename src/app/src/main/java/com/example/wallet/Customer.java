package com.example.wallet;

public class Customer {

    private String name;
    private int age;
    private String email;
    private String passwort;

    private static Customer instance;

    public static synchronized Customer getInstance(String name, int age, String email, String passwort){
        if(null == instance){
            instance = new Customer(name,age,email,passwort);
            return instance;
        }else{
            return instance;
        }
    }

    private Customer(String name, int age, String email, String passwort){
        this.name = name;
        this.age = age;
        this.email = email;
        this.passwort = passwort;
    }

}
