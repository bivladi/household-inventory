package org.household.inventory.households.repository;

import java.util.UUID;
import org.household.inventory.model.HouseholdItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseholdItemRepository extends JpaRepository<HouseholdItem, UUID> {}
