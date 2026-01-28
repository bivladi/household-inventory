package org.household.inventory.items.repository;

import java.util.UUID;
import org.household.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, UUID> {
  boolean existsByName(String name);

  boolean existsItemByCategoriesId(UUID categoryId);
}
