package org.household.inventory.api.dto;

import java.time.LocalDateTime;

/**
 * DTO representing the response after creating an item. Contains the created item's data including
 * system-generated fields.
 */
public record CreateItemResponse(String id, LocalDateTime createdAt) {}
