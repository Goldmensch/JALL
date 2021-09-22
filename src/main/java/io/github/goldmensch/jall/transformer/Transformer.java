package io.github.goldmensch.jall.transformer;

/**
 * The transformer will be used to transform a {@link String} from the localizer to the needed
 * format
 *
 * @param <T> The output format
 */
@FunctionalInterface
public interface Transformer<T> {

  /**
   * Transforms a {@link String} to the needed format
   *
   * @param value The final {@link String}, all placeholders are resolved
   * @return The localization as the needed format
   */
  T transform(String value);
}
