package io.github.goldmensch.placeholder;

import java.util.Set;

/**
 * The {@link PlaceholderResolver} will be used to resolve the placeholders in the localization
 */
@FunctionalInterface
public interface PlaceholderResolver {

  /**
   * @param message      The raw localization
   * @param replacements An unmodifiable {@link Set} of {@link Replacement}s, which were passed in
   *                     the method
   * @return The new {@link String} with resolved Placeholders
   */
  String resolve(String message, Set<Replacement> replacements);
}
