package org.household.inventory.households.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response for a Household. Contains all fields from Household entity
 * including inherited fields from BaseEntity. Designed to avoid nulls by using proper default
 * values.
 */
public record HouseholdResponse(
    UUID id,
    String name,
    String description,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {}
