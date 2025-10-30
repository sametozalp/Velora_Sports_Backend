package com.ozalp.Velora.Sports.controllers;

import com.ozalp.Velora.Sports.business.abstracts.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/feed")
@AllArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/getHomeFeed")
    ResponseEntity<?> getHomeFeed(UUID athleteId) {
        return ResponseEntity.ok(feedService.getHomeFeed(athleteId));
    }
}
