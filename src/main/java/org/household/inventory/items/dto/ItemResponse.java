package org.household.inventory.items.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO representing the response for an Item. Contains all fields from Item entity including
 * inherited fields from BaseEntity. Designed to avoid nulls by using empty collections and proper
 * default values.
 */
public record ItemResponse(
    UUID id,
    String name,
    String description,
    Integer amount,
    Double price,
    List<String> categories,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {}
