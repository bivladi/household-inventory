package org.household.inventory.items.api.converter;

import org.household.inventory.items.api.model.SizeNumber;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SizeNumberConverter implements Converter<String, SizeNumber> {
  @Override
  public @Nullable SizeNumber convert(String source) {
    return new SizeNumber(Integer.parseInt(source));
  }
}
