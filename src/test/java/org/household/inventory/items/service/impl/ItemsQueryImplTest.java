package org.household.inventory.items.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.household.inventory.items.repository.ItemsRepository;
import org.household.inventory.model.Item;
import org.junit.jupiter.api.Test;

class ItemsQueryImplTest {

  private final ItemsQueryImpl subject;
  private final ItemsRepository repositoryMock;

  ItemsQueryImplTest() {
    repositoryMock = mock(ItemsRepository.class);
    subject = new ItemsQueryImpl(repositoryMock);
  }

  @Test
  void testFindById() {

    final var value = new Item();
    final var id = UUID.randomUUID();
    value.setId(id);
    when(repositoryMock.findById(eq(id))).thenReturn(Optional.of(value));

    final var item = subject.findById(id);

    assertTrue(item.isPresent(), "item should be present");
    assertEquals(id, item.get().getId(), "item id should match");
  }
}
