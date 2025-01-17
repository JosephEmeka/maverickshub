package com.semicolon.maverickshub.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semicolon.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateMediaResponse {
    private Long id;
    private String description;
    private Category category;
    @JsonProperty("created_at")
    private LocalDateTime timeCreated;
    @JsonProperty("updated_at")
    private LocalDateTime timeUpdated;
}
