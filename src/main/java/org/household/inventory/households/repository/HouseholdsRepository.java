package org.household.inventory.households.repository;

import java.util.UUID;
import org.household.inventory.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseholdsRepository extends JpaRepository<Household, UUID> {}
