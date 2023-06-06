package com.example.testdd;
/*
 * FizzBuzz logic :
 * Divisible by 3 then print Fizz
 * Divisible by 5 then print Buzz
 * Divisible by both 3 and 5 then print FizzBuzz
 * Not divisible by either 3 or 5 then print the number
 * */
public class FizzBuzz {

    static String generate(int num){
        if(num % 3 == 0 && num % 5 == 0)
            return "FizzBuzz";
        else if(num % 3 == 0)
            return "Fizz";
        else if(num % 5 == 0)
            return "Buzz";
        else
            return String.valueOf(num);
    }
}
