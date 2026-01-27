package org.household.inventory.households.application.impl;

import java.util.UUID;
import org.household.inventory.common.exception.BadArgumentApplicationException;
import org.household.inventory.common.exception.NotFoundApplicationException;
import org.household.inventory.households.application.HouseholdsApplicationService;
import org.household.inventory.households.repository.HouseholdsRepository;
import org.household.inventory.households.service.HouseholdsQuery;
import org.household.inventory.households.service.impl.HouseholdsQueryImpl;
import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HouseholdsApplicationServiceImpl implements HouseholdsApplicationService {

  private final HouseholdsQuery householdsQuery;
  private final HouseholdsRepository repository;

  public HouseholdsApplicationServiceImpl(
      HouseholdsQuery householdsQuery, HouseholdsRepository repository) {
    this.householdsQuery = householdsQuery;
    this.repository = repository;
  }

  @Override
  public Household createHousehold(Household household) {
    return repository.save(household);
  }

  @Override
  public Household getHouseholdById(String id) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("household id cannot be null or empty");
    }
    return ((HouseholdsQueryImpl) householdsQuery)
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundApplicationException("household not found with id: " + id));
  }

  @Override
  public Page<Household> getAllHouseholds(int page, int size, String sort, String direction) {
    return householdsQuery.findAllHouseholds(page, size, sort, direction);
  }

  @Override
  public Household updateHousehold(String id, Household updateRequest) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("household id cannot be null or empty");
    }

    Household existingHousehold =
        ((HouseholdsQueryImpl) householdsQuery)
            .findById(UUID.fromString(id))
            .orElseThrow(
                () -> new NotFoundApplicationException("household not found with id: " + id));

    // Apply partial updates - only update non-null fields
    if (updateRequest.getName() != null) {
      existingHousehold.setName(updateRequest.getName());
    }
    if (updateRequest.getDescription() != null) {
      existingHousehold.setDescription(updateRequest.getDescription());
    }

    return repository.save(existingHousehold);
  }

  @Override
  public void deleteHousehold(String id) {
    repository.deleteById(UUID.fromString(id));
  }
}
