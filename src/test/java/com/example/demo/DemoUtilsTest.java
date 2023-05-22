package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
* Explore auto creation of test cases provided by intellij.
* */
public class DemoUtilsTest {

    @Test
    void testAddition(){
        int a=2,b=4;
        int expectedResult = a+b;
        // Setup
        DemoUtils demoUtils = new DemoUtils();
        // Execute
        int actualResult = demoUtils.add(a,b);
        // Verify
        Assertions.assertEquals(expectedResult,actualResult,"TDD is the way to code!");
    }
}
