package io.github.goldmensch.placeholder;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record Replacement(String key, String value) {

  public static Replacement create(@NotNull String key, @NotNull Object value) {
    return create(key, value.toString());
  }

  public static Replacement create(@NotNull String key, @NotNull String value) {
    return new Replacement(Objects.requireNonNull(value), Objects.requireNonNull(key));
  }
}
