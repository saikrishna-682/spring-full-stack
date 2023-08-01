package com.blakhat.JPA;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongCntroller {

    private static int Counter = 0;

    record PingPong(String result){}

    @GetMapping("/ping")
    public PingPong getPingPong(){
        return new PingPong("Pong: %s".formatted(++Counter));
    }


}
