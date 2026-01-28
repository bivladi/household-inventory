package org.household.inventory.items.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/** Record DTO for update item response. Contains the complete updated state of the item. */
public record UpdateItemResponse(
    UUID id,
    String name,
    String description,
    Double price,
    List<String> categories,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {}
