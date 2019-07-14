package org.hoss.learn.micronaut.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class HelloController {

    @Get(produces = MediaType.APPLICATION_JSON)
    public String getGreeting() {
        return "Hello World!";
    }
}
