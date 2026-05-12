package org.example.fizzbuzz;

public class FizzBuzz implements NumberRenderer {

    @Override
    public String renderInput(int input) {
        String output = "";
        if(input % 3 == 0) {
            output = "fizz";
        }
        if(input % 5 == 0) {
            output += "buzz";
        }
        if(output.isBlank()){
            output = input + "";
        }

        return output;
    }
}
