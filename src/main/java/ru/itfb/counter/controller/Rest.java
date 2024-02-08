package ru.itfb.counter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itfb.counter.dto.Conter;
import ru.itfb.counter.service.ConterService;

@RestController
@RequestMapping("/api")
public class Rest {

    private static final String template = "Count: %d";
    private Conter conter = new Conter();
    private ConterService service;

    public Rest(ConterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Conter> count() {
        return ResponseEntity.ok(increment());
    }
    @GetMapping("/s")
    public ResponseEntity<Conter> countService() {
        return ResponseEntity.ok(service.getConter());
    }

    private Conter increment() {
        conter.intcrementCount();
        return conter;
    }
}
