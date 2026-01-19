package org.household.inventory.items.repository;

import java.util.UUID;
import org.household.inventory.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<Item, UUID> {}
