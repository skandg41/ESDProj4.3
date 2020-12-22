package com.example.project.utils;

public class CheckDir {
    public static void main(String[] argv) throws Exception {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
    }
}
