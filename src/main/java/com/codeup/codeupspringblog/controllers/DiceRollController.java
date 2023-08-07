package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceRollController {
    private Random random = new Random();

    @GetMapping("/roll-dice/{guess}")
    public String diceGuess(@PathVariable int guess, Model model){
        int roll = random.nextInt(1,7);
        String message;
        if(guess == roll){
            message = "Good Job! You guessed the number.";
        } else {
            message = "Sorry, better luck next time.";
        }
        model.addAttribute("guess", guess);
        model.addAttribute("roll", roll);
        model.addAttribute("message", message);
        return"roll-dice";
    }

    @GetMapping("/roll-dice")
    public String diceGuess(Model model){
        model.addAttribute("roll", "Not Rolled.");
        model.addAttribute("guess", "No Guess Yet.");
        model.addAttribute("message", "Guess the dice roll bellow.");
        return"roll-dice";
    }


}
