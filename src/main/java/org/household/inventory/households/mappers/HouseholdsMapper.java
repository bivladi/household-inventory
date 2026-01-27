package org.household.inventory.households.mappers;

import java.util.List;
import java.util.stream.Collectors;
import org.household.inventory.households.dto.CreateHouseholdRequest;
import org.household.inventory.households.dto.CreateHouseholdResponse;
import org.household.inventory.households.dto.HouseholdResponse;
import org.household.inventory.households.dto.PaginatedHouseholdResponse;
import org.household.inventory.households.dto.UpdateHouseholdRequest;
import org.household.inventory.households.dto.UpdateHouseholdResponse;
import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Household entities and DTOs. Follows single responsibility
 * principle and provides clean separation between API layer and domain layer.
 */
@Component
public final class HouseholdsMapper {

  /**
   * Converts CreateHouseholdRequest DTO to Household entity.
   *
   * @param request the DTO containing household creation data
   * @return the Household entity
   * @throws IllegalArgumentException if request is null
   */
  public Household toEntity(CreateHouseholdRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("CreateHouseholdRequest cannot be null");
    }

    Household household = new Household();
    household.setName(request.getName());
    household.setDescription(request.getDescription());

    return household;
  }

  /**
   * Converts Household entity to CreateHouseholdResponse DTO.
   *
   * @param household the entity to convert
   * @return the DTO representation
   * @throws IllegalArgumentException if household is null
   */
  public CreateHouseholdResponse toCreateResponse(Household household) {
    if (household == null) {
      throw new IllegalArgumentException("Household cannot be null");
    }

    return new CreateHouseholdResponse(household.getId().toString(), household.getCreatedAt());
  }

  /**
   * Converts Household entity to HouseholdResponse DTO.
   *
   * @param household the entity to convert
   * @return the DTO representation
   * @throws IllegalArgumentException if household is null
   */
  public HouseholdResponse toResponse(Household household) {
    if (household == null) {
      throw new IllegalArgumentException("Household cannot be null");
    }

    return new HouseholdResponse(
        household.getId(),
        household.getName(),
        household.getDescription(),
        household.getCreatedAt(),
        household.getCreatedBy(),
        household.getUpdatedAt(),
        household.getUpdatedBy());
  }

  /**
   * Converts UpdateHouseholdRequest DTO to Household entity for partial updates. Only non-null
   * fields will be set on the entity.
   *
   * @param request the DTO containing household update data
   * @return the Household entity with update data
   * @throws IllegalArgumentException if request is null
   */
  public Household toUpdateEntity(UpdateHouseholdRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("UpdateHouseholdRequest cannot be null");
    }

    Household household = new Household();
    household.setName(request.getName());
    household.setDescription(request.getDescription());

    return household;
  }

  /**
   * Converts Household entity to UpdateHouseholdResponse record.
   *
   * @param household the entity to convert
   * @return the UpdateHouseholdResponse record
   * @throws IllegalArgumentException if household is null
   */
  public UpdateHouseholdResponse toUpdateResponse(Household household) {
    if (household == null) {
      throw new IllegalArgumentException("Household cannot be null");
    }

    return new UpdateHouseholdResponse(
        household.getId(),
        household.getName(),
        household.getDescription(),
        household.getCreatedAt(),
        household.getCreatedBy(),
        household.getUpdatedAt(),
        household.getUpdatedBy());
  }

  public List<HouseholdResponse> toResponseList(List<Household> all) {
    return all.stream().map(this::toResponse).collect(Collectors.toList());
  }

  /**
   * Converts Page<Household> to PaginatedHouseholdResponse DTO.
   *
   * @param page the paginated entity data
   * @return the paginated DTO representation
   * @throws IllegalArgumentException if page is null
   */
  public PaginatedHouseholdResponse toPaginatedResponse(Page<Household> page) {
    if (page == null) {
      throw new IllegalArgumentException("Page cannot be null");
    }

    List<HouseholdResponse> households =
        page.getContent().stream().map(this::toResponse).collect(Collectors.toList());

    return new PaginatedHouseholdResponse(
        households,
        page.getNumber(),
        page.getSize(),
        page.getTotalElements(),
        page.getTotalPages(),
        page.isFirst(),
        page.isLast(),
        page.hasNext(),
        page.hasPrevious());
  }
}
