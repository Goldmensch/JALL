package io.github.goldmensch;

import io.github.goldmensch.placeholder.PlaceholderResolver;
import io.github.goldmensch.placeholder.Replacement;
import io.github.goldmensch.placeholder.StandardResolver;
import io.github.goldmensch.registry.Registry;
import io.github.goldmensch.transformer.StringTransformer;
import io.github.goldmensch.transformer.Transformer;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

/**
 * The main entry point of the localizer A {@link Jall} localizer contains a {@link Localization}
 * for each registerd {@link Locale}
 *
 * @param <T> The output format of the localized message, normally determined by the {@link
 *            Transformer}
 */
public interface Jall<T> {

  /**
   * Creates a new {@link JallImpl}
   *
   * @param fallback    The fallback {@link Locale} which is used if no {@link Localization} is
   *                    found for the needed {@link Locale}
   * @param transformer A {@link Transformer} which transform the String from the Localizer to the
   *                    needed format
   * @param resolver    A {@link PlaceholderResolver} which resolves the placeholders in a
   *                    localization
   * @return A new {@link Jall}
   */
  static <T> Jall<T> create(@NotNull Locale fallback, @NotNull Transformer<T> transformer,
                            @NotNull PlaceholderResolver resolver) {
    return new JallImpl<>(fallback, transformer, resolver);
  }

  /**
   * Creates a standard {@link JallImpl} with:
   * <ul>
   *   <li>{@link StringTransformer} as the {@link Transformer}</li>
   *   <li>{@link StringTransformer} as the {@link PlaceholderResolver}</li>
   * </ul>
   *
   * @param fallback The fallback {@link Locale} which is used if no {@link Localization} is found
   *                 for the needed {@link Locale}
   * @return A new {@link Jall}
   */
  static Jall<String> createStandard(@NotNull Locale fallback) {
    return create(fallback, new StringTransformer(), new StandardResolver());
  }

  /**
   * Registers a {@link Localization}, only one {@link Localization} can be registered per {@link
   * Locale}
   *
   * @param localization The {@link Localization} to be registered
   * @param override     Controls if the {@link Localization} should override the registered one
   * @throws io.github.goldmensch.exceptions.LocExistInRegistryException if the {@link Locale} is
   *                                                                     already registered and
   *                                                                     {@param override} is false
   */
  void register(@NotNull Localization localization, boolean override);

  /**
   * Localize a message using the {@param key} and the {@param locale}
   *
   * @param key          The key which belongs to the localization
   * @param locale       The {@link Locale} which should be used to localize, if no {@link
   *                     Localization} is found for the {@link Locale} the fallback {@link Locale}
   *                     will use
   * @param replacements The optional {@link Replacement} which should be used in the {@link
   *                     PlaceholderResolver}
   * @return The localization transformed to the needed format by the {@link Transformer}
   */
  T localize(@NotNull String key, @NotNull Locale locale, Replacement... replacements);

  /**
   * @return The fallback {@link Locale}
   */
  @NotNull
  Locale getFallbackLocale();

  /**
   * @return A {@link io.github.goldmensch.registry.Registry.UnmodifiableRegistry} of the registry
   * that belongs to this {@link Jall}
   */
  @NotNull
  Registry getRegistry();
}
