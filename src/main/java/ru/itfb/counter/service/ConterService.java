package ru.itfb.counter.service;

import org.springframework.stereotype.Service;
import ru.itfb.counter.dto.Conter;

@Service
public class ConterService {

    private Conter conter = new Conter();

    public Conter getConter() {
        conter.intcrementCount();
        return conter;
    }
}
