package org.household.inventory.categories.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response for a Category. Contains all fields from Category entity including
 * inherited fields from BaseEntity. Designed to avoid nulls by using proper default values.
 */
public record CategoryResponse(
    UUID id,
    String name,
    String description,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {}
