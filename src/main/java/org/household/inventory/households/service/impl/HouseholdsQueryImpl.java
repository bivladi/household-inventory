package org.household.inventory.households.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.household.inventory.households.repository.HouseholdsRepository;
import org.household.inventory.households.service.HouseholdsQuery;
import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HouseholdsQueryImpl implements HouseholdsQuery {
  private final HouseholdsRepository repository;

  public HouseholdsQueryImpl(HouseholdsRepository repository) {
    this.repository = repository;
  }

  public Optional<Household> findById(UUID id) {
    return repository.findById(id);
  }

  public List<Household> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
  }

  @Override
  public Page<Household> findAllHouseholds(int page, int size, String sort, String direction) {
    Sort.Direction directionValue = Sort.Direction.fromString(direction);
    Pageable pageable = PageRequest.of(page, size, Sort.by(directionValue, sort));
    return repository.findAll(pageable);
  }
}
