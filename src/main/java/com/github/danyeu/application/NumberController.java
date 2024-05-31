package com.github.danyeu.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/home")
    public String home() {
        return numberService.weclome();
    }

    @GetMapping("/update")
    public String update() {
        Integer old = numberService.getCurrentNumber();
        if (numberService.updateNumber()) {
            return String.format("The number was updated from %d to %d.", old, numberService.getCurrentNumber());
        }
        return String.format("The number %d could not be updated.", old);
    }

    @GetMapping("/even")
    public String even() {
        return numberService.even();
    }

    @GetMapping("/prime")
    public String prime() {
        return numberService.prime();
    }

    @GetMapping("/seven")
    public String seven() {
        // check if the first three numbers contain a seven
        return numberService.seven();
    }

    @GetMapping("/add/{n}")
    public String contains(@PathVariable Long n) {
        return numberService.add(n);
    }


}
