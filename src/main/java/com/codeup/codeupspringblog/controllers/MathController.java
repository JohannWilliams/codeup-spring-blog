package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    // Controller should perform simple operations with the following paths.
    // Paths            // results
//    /add/3/and/4	        7
//    /subtract/3/from/10	7
//    /multiply/4/and/5	    20
//    /divide/6/by/3	    2

    // Add 2 numbers
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int num1, @PathVariable int num2){
        return num1 + num2;
    }

    // Subtract 2 numbers
    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public int sub(@PathVariable int num1, @PathVariable int num2){
        return num2 - num1;
    }

    // Multiple 2 numbers
    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public int mul(@PathVariable int num1, @PathVariable int num2){
        return num1 * num2;
    }

    // Divide 2 numbers
    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public double div(@PathVariable int num1, @PathVariable int num2){ return (double) num1 / num2;
    }

}
