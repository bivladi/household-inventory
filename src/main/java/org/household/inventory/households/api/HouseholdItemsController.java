package org.household.inventory.households.api;

import org.household.inventory.households.application.HouseholdAddItemApplicationService;
import org.household.inventory.households.dto.CreateItemRequest;
import org.household.inventory.households.dto.CreateItemResponse;
import org.household.inventory.households.mappers.HouseholdsMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household/{householdId}/items")
public class HouseholdItemsController {

  private final HouseholdAddItemApplicationService service;
  private final HouseholdsMapper mapper;

  public HouseholdItemsController(
      HouseholdAddItemApplicationService service, HouseholdsMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @PostMapping
  public CreateItemResponse createItem(
      @PathVariable(value = "householdId") String householdId,
      @RequestBody CreateItemRequest requestBody) {
    return mapper.toCreateItemResponse(service.createItem(householdId, requestBody));
  }
}
