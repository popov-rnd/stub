package com.popovrnd.stub.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WaitingController {

    private static final Logger log =
            LoggerFactory.getLogger(WaitingController.class);

    @GetMapping("/waiting")
    public ResponseEntity<Void> waiting() {
        //log.info("Waiting is called!, Thread = {}", Thread.currentThread());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
