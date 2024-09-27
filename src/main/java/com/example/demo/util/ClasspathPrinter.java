package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ClasspathPrinter {

    private final ResourceLoader resourceLoader;

    @Autowired
    public ClasspathPrinter(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        printClasspath();
    }

    public void printClasspath() {
        Resource resource = resourceLoader.getResource("classpath:");
        System.out.println("Classpath: " + resource);
    }
}
