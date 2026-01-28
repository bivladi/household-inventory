package org.household.inventory.households.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response after creating a household. Contains the created household's data
 * including system-generated fields.
 */
public record CreateHouseholdResponse(UUID id, LocalDateTime createdAt) {}
