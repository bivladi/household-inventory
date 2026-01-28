package org.household.inventory.households.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response after updating a household. Contains the updated household's data
 * including all fields from the entity.
 */
public record UpdateHouseholdResponse(
    UUID id,
    String name,
    String description,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {}
