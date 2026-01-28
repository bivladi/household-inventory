package org.household.inventory.categories.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response after creating a category. Contains the created category's data
 * including system-generated fields.
 */
public record CreateCategoryResponse(UUID id, LocalDateTime createdAt) {}
