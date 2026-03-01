package com.example.plugin;

import org.pf4j.Plugin;

public class MyPlugin extends Plugin {

    @Override
    public void start() {
        System.out.println("My Plugin started");
    }

    @Override
    public void stop() {
        System.out.println("My Plugin stopped");
    }
}