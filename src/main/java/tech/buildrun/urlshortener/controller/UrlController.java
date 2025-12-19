package tech.buildrun.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.urlshortener.controller.dto.ShorterUrlRequest;
import tech.buildrun.urlshortener.controller.dto.ShorterUrlResponse;
import tech.buildrun.urlshortener.entities.UrlEntity;
import tech.buildrun.urlshortener.repository.UrlRepository;

import java.time.LocalDateTime;

@RestController
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShorterUrlResponse> shorterUrl(@RequestBody ShorterUrlRequest request,
                                           HttpServletRequest servletRequest){

        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5,10);
        }while(urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new ShorterUrlResponse(redirectUrl));
    }
}