package org.household.inventory.households.application;

import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;

public interface HouseholdsApplicationService {
  Household createHousehold(Household request);

  Household getHouseholdById(String id);

  Page<Household> getAllHouseholds(int page, int size, String sort, String direction);

  Household updateHousehold(String id, Household updateRequest);

  void deleteHousehold(String id);
}
