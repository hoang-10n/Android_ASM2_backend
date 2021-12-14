package com.android.asm2.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class TestController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String greeting() {
        return "Hello";
    }

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public String weather() {
        return "Today is a good day";
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public String sayName(@PathVariable String name) {
        return "Hello " + name;
    }
}
