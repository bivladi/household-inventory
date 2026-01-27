package org.household.inventory.households.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.household.inventory.common.api.model.PageNumber;
import org.household.inventory.common.api.model.SizeNumber;
import org.household.inventory.common.api.model.SortDirection;
import org.household.inventory.common.api.model.SortField;
import org.household.inventory.households.application.HouseholdsApplicationService;
import org.household.inventory.households.dto.CreateHouseholdRequest;
import org.household.inventory.households.dto.CreateHouseholdResponse;
import org.household.inventory.households.dto.HouseholdResponse;
import org.household.inventory.households.dto.PaginatedHouseholdResponse;
import org.household.inventory.households.dto.UpdateHouseholdRequest;
import org.household.inventory.households.dto.UpdateHouseholdResponse;
import org.household.inventory.households.mappers.HouseholdsMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/households")
public class HouseholdsController {

  private final HouseholdsMapper mapper;
  private final HouseholdsApplicationService service;

  public HouseholdsController(HouseholdsMapper mapper, HouseholdsApplicationService service) {
    this.mapper = mapper;
    this.service = service;
  }

  @PostMapping
  public CreateHouseholdResponse create(@RequestBody CreateHouseholdRequest request) {
    return mapper.toCreateResponse(service.createHousehold(mapper.toEntity(request)));
  }

  @GetMapping
  public PaginatedHouseholdResponse findAll(
      @Parameter(schema = @Schema(type = "integer", defaultValue = "0", minimum = "0"))
          @RequestParam(defaultValue = "0")
          PageNumber page,
      @Parameter(schema = @Schema(type = "integer", defaultValue = "10", minimum = "0"))
          @RequestParam(defaultValue = "10")
          SizeNumber size,
      @RequestParam(defaultValue = "CREATED_AT") SortField sort,
      @RequestParam(defaultValue = "ASC") SortDirection direction) {
    return mapper.toPaginatedResponse(
        service.getAllHouseholds(
            page.value(), size.value(), sort.getValue(), direction.getValue()));
  }

  @GetMapping("/{id}")
  public HouseholdResponse getById(@PathVariable(name = "id") String id) {
    return mapper.toResponse(service.getHouseholdById(id));
  }

  @PutMapping("/{id}")
  public UpdateHouseholdResponse update(
      @PathVariable(name = "id") String id, @RequestBody UpdateHouseholdRequest request) {
    return mapper.toUpdateResponse(service.updateHousehold(id, mapper.toUpdateEntity(request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
    service.deleteHousehold(id);
    return ResponseEntity.ok().build();
  }
}
