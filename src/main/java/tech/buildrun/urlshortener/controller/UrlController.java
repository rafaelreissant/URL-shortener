package tech.buildrun.urlshortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.urlshortener.controller.dto.ShorterUrlRequest;

@RestController
public class UrlController {

    @PostMapping(value = "/shorer-url")
    public ResponseEntity<Void> shorterUrl(@RequestBody ShorterUrlRequest request){
        return ResponseEntity.ok().build();
    }
}
