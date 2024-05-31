package com.github.danyeu.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {
    @GetMapping("/**")
    public RedirectView redirect() {
        return new RedirectView("/home");
    }
}
