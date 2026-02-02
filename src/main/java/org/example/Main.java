package org.example;


import org.example.processor.FileContentFilter;

public class Main {
    public static void main(String[] args) {
        new FileContentFilter().run(args);
    }
}