package com.example.testdd;

/*
* FizzBuzz logic :
* Divisible by 3 then print Fizz
* Divisible by 5 then print Buzz
* Divisible by both 3 and 5 then print FizzBuzz
* Not divisible by either 3 or 5 then print the number
* */

/*
* Instead of passing values manually to test our sceanrios, we can use
* @ParametrizedTest annotation to supply different arguments to our test methods.
* */
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    @Test
    @Order(1)
    void divisbleByThree(){
//        fail("First step of TDD is to write a failing test");
        String expected = "Fizz";
        assertEquals(expected,FizzBuzz.generate(3));
    }

    @Test
    @Order(2)
    void divisibleByFive(){
        String expected = "Buzz";
        assertEquals(expected,FizzBuzz.generate(5));
    }

    @Test
    @Order(3)
    void divisbleByThreeAndFive(){
        String expected = "FizzBuzz";
        assertEquals(expected,FizzBuzz.generate(15));
    }

    @Test
    @Order(4)
    void divisibleByNeitherThreeNorFive(){
        String expected = "13";
        assertEquals(expected,FizzBuzz.generate(13));
    }
}
