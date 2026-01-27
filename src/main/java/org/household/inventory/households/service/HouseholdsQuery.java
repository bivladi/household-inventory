package org.household.inventory.households.service;

import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface HouseholdsQuery {
  Page<Household> findAllHouseholds(int page, int size, String sort, String direction);
}
