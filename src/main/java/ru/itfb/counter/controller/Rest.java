/*
 * Copyright (C) 2024 ITFB Group. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 */

package ru.itfb.counter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itfb.counter.dto.Conter;
import ru.itfb.counter.service.ConterService;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class Rest {
    public int counter_2 = 0;
    public volatile int counter_3 = 0;
    public AtomicInteger count_2_a = new AtomicInteger(0);

    public int counter_4 = 0;
    public volatile int counter_5 = 0;
    public AtomicInteger count_4_a = new AtomicInteger(0);
    private final Conter conter = new Conter();
    private final ConterService service;

    public Rest(ConterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Conter> count() {
//        return ResponseEntity.ok(increment());
        testCnt();
        return ResponseEntity.ok(conter);
    }

    @GetMapping("/s")
    public ResponseEntity<Conter> countService() {
        return ResponseEntity.ok(service.getConter());
    }

    @GetMapping("/c")
    public ResponseEntity<String> clearCount() {
        conter.setCount(0);
        counter_2 = 0;
        counter_3 = 0;
        counter_4 = 0;
        counter_5 = 0;
        count_2_a.set(0);
        count_4_a.set(0);
        return ResponseEntity.ok("NonSync: " + counter_2 + "   vol: " + counter_3 + ";\n" +
                "Sync: " + counter_4 + "   vol: " + counter_5 + " \n" +
                "NSyncNvRead: " + conter.getCount());
    }

    @GetMapping("/cnt")
    public ResponseEntity<String> getCountService() {
        return ResponseEntity.ok("NonSync: " + counter_2 + "   vol: " + counter_3 + " AtomicNV: " + count_2_a + ";\n" +
                "Sync: " + counter_4 + "   vol: " + counter_5 + " AtomicNV: " + count_4_a + " \n" +
                "NSyncNvRead: " + conter.getCount());
    }

    private void incrementCnt() {
        try {
            Thread.sleep(41);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter_2++;
        volatileIncrementCounter_3();
        count_2_a.getAndIncrement();
    }

    private synchronized void volatileIncrementCounter_3() {
        counter_3++;
    }

    private synchronized void syncIncrementCnt() {
        try {
            Thread.sleep(33);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//  Операция инкремента составляет не одну операцию, а три: запрос на получение текущего значения count,
//  потом увелечение ее на 1 и запись снова в count.
        count_4_a.getAndIncrement();
        counter_4++;
        counter_5++;
    }

    private Conter increment() {
        try {
            Thread.sleep(71);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        conter.intcrementCount();
//        System.out.println(conter.getCount());
        return conter;
    }

    private void testCnt() {
        increment();
        incrementCnt();
        syncIncrementCnt();
    }
}
