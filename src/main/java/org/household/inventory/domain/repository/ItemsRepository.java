package org.household.inventory.domain.repository;

import java.util.UUID;
import org.household.inventory.domain.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<Item, UUID> {}
