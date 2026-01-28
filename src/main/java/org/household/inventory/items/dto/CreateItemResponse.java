package org.household.inventory.items.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing the response after creating an item. Contains the created item's data including
 * system-generated fields.
 */
public record CreateItemResponse(UUID id, LocalDateTime createdAt) {}
