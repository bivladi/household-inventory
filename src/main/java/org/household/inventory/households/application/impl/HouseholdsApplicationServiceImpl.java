package org.household.inventory.households.application.impl;

import java.util.UUID;
import org.household.inventory.common.exception.NotFoundApplicationException;
import org.household.inventory.households.application.HouseholdsApplicationService;
import org.household.inventory.households.repository.HouseholdsRepository;
import org.household.inventory.households.service.HouseholdsQuery;
import org.household.inventory.households.service.impl.HouseholdsQueryImpl;
import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
  public Household getHouseholdById(UUID id) {
    return ((HouseholdsQueryImpl) householdsQuery)
        .findById(id)
        .orElseThrow(() -> new NotFoundApplicationException("household not found with id: " + id));
  }

  @Override
  public Page<Household> getAllHouseholds(int page, int size, String sort, String direction) {
    return householdsQuery.findAllHouseholds(page, size, sort, direction);
  }

  @Override
  public Household updateHousehold(UUID id, Household updateRequest) {
    Household existingHousehold =
        ((HouseholdsQueryImpl) householdsQuery)
            .findById(id)
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
  public void deleteHousehold(UUID id) {
    repository.deleteById(id);
  }
}
