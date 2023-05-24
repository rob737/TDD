package com.example.demo;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
* Explore auto creation of test cases provided by intellij.
* Note: We can use import static instead of import statements above to directly
* use assertion methods of Assertions class.
*
* We can also use other annotations based on the use case.
* @BeforeEach -- executes the annotated method before executing each test
* @AfterEach -- as the name suggests
* @BeforeAll - Executed only once before any test is executed
* @AfterAll - Executed only once after all tests are executed
*
* Note: method annotated with @BeforeAll and @AfterAll must be static.
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
        assertEquals(expectedResult,actualResult,"TDD is the way to code!");
    }

    @Test
    void testNull(){
        DemoUtils demoUtils = new DemoUtils();
        Object ref = demoUtils.getReference();
        assertNull(ref);
    }
}
