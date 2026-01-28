package org.household.inventory.households.application;

import java.util.UUID;
import org.household.inventory.model.Household;
import org.springframework.data.domain.Page;

public interface HouseholdsApplicationService {
  Household createHousehold(Household request);

  Household getHouseholdById(UUID id);

  Page<Household> getAllHouseholds(int page, int size, String sort, String direction);

  Household updateHousehold(UUID id, Household updateRequest);

  void deleteHousehold(UUID id);
}
