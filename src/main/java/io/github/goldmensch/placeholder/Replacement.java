package io.github.goldmensch.placeholder;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * A record that simply represent a replacement by key and value No null values are allowed in the
 * {@link Replacement}
 */
public record Replacement(String key, String value) {

  /**
   * Creates a new {@link Replacement}
   *
   * @param key   The name of the placeholder
   * @param value The new value
   * @return A new {@link Replacement} with the {@link String} value of the {@param value} {@link
   * Object#toString()}
   */
  public static Replacement create(@NotNull String key, @NotNull Object value) {
    return create(key, value.toString());
  }

  /**
   * Creates a new {@link Replacement}
   *
   * @param key   The name of the placeholder
   * @param value The new value
   * @return A new {@link Replacement}
   */
  public static Replacement create(@NotNull String key, @NotNull String value) {
    return new Replacement(Objects.requireNonNull(key), Objects.requireNonNull(value));
  }
}
