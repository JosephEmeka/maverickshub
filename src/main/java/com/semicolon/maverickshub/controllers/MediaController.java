package com.semicolon.maverickshub.controllers;

import com.semicolon.maverickshub.dtos.requests.UploadMediaRequest;
import com.semicolon.maverickshub.dtos.response.UploadMediaResponse;
import com.semicolon.maverickshub.services.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor

public class MediaController {
    private final MediaService mediaService;

    @PostMapping(consumes ={MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UploadMediaResponse> uploadMedia(@ModelAttribute UploadMediaRequest  uploadMediaRequest) {
        return ResponseEntity.status(CREATED)
                .body(mediaService.upload(uploadMediaRequest));
    }

    @GetMapping
    public ResponseEntity<?> getMediaForUser(@RequestParam Long userId) {
        return ResponseEntity.ok(mediaService.getMediaFor(userId));
    }

}
