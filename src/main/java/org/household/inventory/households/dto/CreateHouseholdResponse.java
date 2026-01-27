package org.household.inventory.households.dto;

import java.time.LocalDateTime;

/**
 * DTO representing the response after creating a household. Contains the created household's data
 * including system-generated fields.
 */
public record CreateHouseholdResponse(String id, LocalDateTime createdAt) {}
