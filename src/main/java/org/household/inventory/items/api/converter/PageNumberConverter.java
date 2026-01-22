package org.household.inventory.items.api.converter;

import org.household.inventory.items.api.model.PageNumber;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PageNumberConverter implements Converter<String, PageNumber> {
  @Override
  public @Nullable PageNumber convert(String source) {
    return new PageNumber(Integer.parseInt(source));
  }
}
