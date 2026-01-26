package org.household.inventory.categories.repository;

import java.util.UUID;
import org.household.inventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, UUID> {}
