package com.example.demo;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.List;

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
* @DisplayName - Annotation that can be used to give names to our test cases,
* helpful for documentation and knowledge sharing.
* Note: method annotated with @BeforeAll and @AfterAll must be static.
*
* Note: we can also hint junit to generate names for us using generators.
* @DisplayNameGeneration(DisplayNameGenerator.Simple.class) can be decorated
* at class level and each @DisplayName annotation can be removed to get the work done.
*
* assertSame(): Test for equal reference
* assertNotSame(): Test for unequal reference
*
* assertTrue() and assertFalse(): As the name suggests.
*
* Note : At class level, we can put annotation @TestMethodOrder(MethodOrder.MathodName.class) to
* display test cases in alphabetical order by method name.
* ------- Important --------------
* assertArrayEquals() : asserts that both object arrays are deeply equal.
* assertIterableEquals() : asserts that both object iterables are deeply equal. (can be used to test
* any class that implements Iterable interface.)
* assertLinesMatch() : asserts that both lists are equal
* assertThrows() : Asserts on exception
* assertNotThrows() : Asserts that exception was not thrown
* assertTimeoutPreemptively() : To test if method was interrupted
*
*
* @Disabled : To disable a test case, it can be applied at a class level or method level
*
* @EnableIfSystemProperty : Executes a test only if corresponding system property is set.
* @EnableIfEnvironmentVariable : As the name suggests
* To set System and environment variable, click on edit run configuration
* and then -D flag for system variable and environment variable section for environment variable.
* */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
public class DemoUtilsTest {
    DemoUtils demoUtils;

    /*@BeforeAll
    static void generateMessage(){
        System.out.println("Before all demonstration ...");
    }*/

    @BeforeEach
    void demonstrateBeforeEach(){
//        System.out.println("Before each test case ...");
        demoUtils = new DemoUtils();
    }

    /*@AfterEach
    //@DisplayName("After each demonstration ...")
    void demonstrateAfterEach(){
        System.out.println("After each test case ...");
    }*/

    @Test
    @DisplayName("Addition of two numbers.")
    void testAddition(){
        int a=2,b=4;
        int expectedResult = a+b;
        // Setup
      //  DemoUtils demoUtils = new DemoUtils();
        // Execute
        int actualResult = demoUtils.add(a,b);
        // Verify
        assertEquals(expectedResult,actualResult,"TDD is the way to code!");
    }

    @Test
//    @DisplayName("Testing null cases behaviour.")
    void testNull(){

        Object ref = demoUtils.getReference();
        assertNull(ref);
    }

    @Disabled
    @Order(1) // Lowest number has highest priority of execution.
    @Test
    void checkReference(){
        String firstRef = demoUtils.getFirstReference();
        String secondRef = demoUtils.getSecondReference();
        String testRef = "RobinS";

        // It is coming as same because of JVM optimisation as String value is same.
        assertSame(firstRef,secondRef);
        assertNotSame(testRef,secondRef);
    }

    @Test
    void testArrays(){
        String[] arr = {"Robin","Ruchika","Foster"};
        assertArrayEquals(arr,demoUtils.getFamilyMembers());
    }

    @Test
    void testIterable(){
        List<String> expectedList = List.of("Robin","Ruchika","Foster");
        assertIterableEquals(expectedList,demoUtils.getFamilyMembersList());
        assertLinesMatch(expectedList,demoUtils.getFamilyMembersList());
    }

   /* @AfterAll
    static void demoAfterAll(){
        System.out.println("After all demonstration ...");
    }*/

    @Test
    void testException(){
        assertThrows(Exception.class,() -> demoUtils.throwsException(-9),"Exception for -ve input");
        assertDoesNotThrow(() -> demoUtils.throwsException(9),"No Exception raised for +ve input");
    }
}
