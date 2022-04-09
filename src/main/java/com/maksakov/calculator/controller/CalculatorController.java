package com.maksakov.calculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class CalculatorController {

    @GetMapping("/calculator")
    public ResponseEntity calculator(@RequestParam("arg1") float arg1, @RequestParam("arg2") float arg2, @RequestParam("action") String action) {

        float result = 0;
        String errorText = "";

        switch (action) {
            case "plus":
                result = arg1 + arg2;
                break;
            case "minus":
                result = arg1 - arg2;
                break;
            case "multiplication":
                result = arg1 * arg2;
                break;
            case "division":
                try {
                    if (arg2 == 0) {
                        throw new Exception("Can't divide by zero!");
                    }
                    result = arg1 / arg2;
                } catch (Exception ex) {
                    errorText = ex.getMessage();
                }
                break;
        }

        if (errorText != "") {
            return ResponseEntity.ok("<span style='color:red;'>" + errorText + "</span>");
        } else {
            return ResponseEntity.ok(result);
        }
    }
}