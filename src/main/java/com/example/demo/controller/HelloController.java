package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greet")
public class HelloController {
  @Value("${greeting.phrase}")
  String phrase;

  @GetMapping("/hello")
  public ResponseEntity<?> sayHello(@RequestParam String name) {
    System.out.println("doing something");
    String responseString = "Good evening My person. " + name + ", How are you? ok then.";
    responseString += " " + phrase;
    return ResponseEntity.ok(responseString);
  }

  private int counter;

  @GetMapping
  public String demo() {
    counter++;
    return String.format("<h1>Response from hello controller: Response number: %d<h1/>", counter);
  }

}
