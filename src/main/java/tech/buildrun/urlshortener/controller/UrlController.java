package tech.buildrun.urlshortener.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.urlshortener.controller.dto.ShorterUrlRequest;
import tech.buildrun.urlshortener.entities.UrlEntity;
import tech.buildrun.urlshortener.repository.UrlRepository;

import java.time.LocalDateTime;

@RestController
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(value = "/shorer-url")
    public ResponseEntity<Void> shorterUrl(@RequestBody ShorterUrlRequest request){

        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5,10);
        }while(urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));



        return ResponseEntity.ok().build();
    }
}
