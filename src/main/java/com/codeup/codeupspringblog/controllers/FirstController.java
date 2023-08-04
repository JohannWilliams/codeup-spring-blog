package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FirstController {

    @GetMapping("/hello")
    @ResponseBody
    public String returnHelloWorld(){
        return "Hello World!";
    }

    @GetMapping("/another")
    @ResponseBody
    public String returnAnother(){
        return "Hello again!";
    }

    //text response
    @GetMapping(path = "/text", produces = "text/text")
    @ResponseBody
    public String showText(){
        return "Here is some plain text";
    }

    //html response
    @GetMapping(path = "/html", produces = "text/html")
    @ResponseBody
    public String showHTML(){
        return "<h1>Howdy, Dragon!</h1>";
    }

    //record to create simple POJOs in memory
    public record Person(String name, String address){}

    //json response
    //text responce
    @GetMapping(path = "/json", produces = "application/json")
    @ResponseBody
    public List<Person> showPeople(){
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("David", "123 Place Lane"),
                new Person("justin", "321 Somewhere Pkwy")
        ));
        return people;
    }

    //text responce
    @GetMapping(path = "/cohorts", produces = "text/html")
    @ResponseBody
    public String showCohorts(){
        return "<h1>Cohort Form</h1><form action='/cohorts' method='POST'><input type='hidden' value='dragon' name='cohort' id='cohort'><button>Submit</button></form>";
    }

    @PostMapping("/cohorts")
    public String processRequests(@RequestParam String cohort){
        System.out.println("The cohort is: " + cohort);
        return "redirect:/cohorts";
    }


//    @PostMapping("/register")
//    public void register(
//            @RequestParam String cohort,
//            @RequestParam String email,
//            @RequestParam String password){
//       //register the user through code here
//    }


    // ======== PATH VARIABLES =========

    //localhost:8080/greeting/hello
    //localhost:8080/greeting/howdy
    //localhost:8080/greeting/good%20day
    @GetMapping("/greeting/{greeting}")
    @ResponseBody
    public String returnGreeting(@PathVariable String greeting){
        return "The greeting is " + greeting;
    }

    @GetMapping("/namegreeting/{firstName}/{lastName}")
    @ResponseBody
    public String returnGreeting(@PathVariable String firstName, @PathVariable String lastName){
        return "Hello, " + firstName + " " + lastName;
    }

}
