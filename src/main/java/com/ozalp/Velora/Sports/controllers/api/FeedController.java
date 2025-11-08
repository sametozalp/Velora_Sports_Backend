package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/feed")
@AllArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PreAuthorize("hasRole('ATHLETE')")
    @PostMapping("/getHomeFeed/{athleteId}")
    ResponseEntity<?> getHomeFeed(@PathVariable UUID athleteId) {
        return ResponseEntity.ok(feedService.getHomeFeed(athleteId));
    }

    @PreAuthorize("hasRole('ATHLETE')")
    @PostMapping("/getTaskFeed/{athleteId}")
    ResponseEntity<?> getTaskFeed(@PathVariable UUID athleteId) {
        return ResponseEntity.ok(feedService.getTaskFeed(athleteId));
    }

    @PreAuthorize("hasRole('ATHLETE')")
    @PostMapping("/getProfileFeed/{athleteId}")
    ResponseEntity<?> getProfileFeed(@PathVariable UUID athleteId) {
        return ResponseEntity.ok(feedService.getProfileFeed(athleteId));
    }

    @PreAuthorize("hasRole('ATHLETE')")
    @PostMapping("/getStaticsFeed/{athleteId}/{organizationId}")
    ResponseEntity<?> getStaticsFeed(@PathVariable UUID athleteId, @PathVariable UUID organizationId) {
        return ResponseEntity.ok(feedService.getStaticsFeed(athleteId, organizationId));
    }
}
