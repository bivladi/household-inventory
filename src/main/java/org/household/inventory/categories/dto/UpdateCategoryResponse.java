package org.household.inventory.categories.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response after updating a category. Contains the updated category's data
 * including all fields from the entity.
 */
public record UpdateCategoryResponse(
    UUID id,
    String name,
    String description,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {}
