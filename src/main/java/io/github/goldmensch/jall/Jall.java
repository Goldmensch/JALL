package io.github.goldmensch.jall;

import io.github.goldmensch.jall.placeholder.PlaceholderResolver;
import io.github.goldmensch.jall.placeholder.Replacement;
import io.github.goldmensch.jall.placeholder.StandardResolver;
import io.github.goldmensch.jall.registry.Registry;
import io.github.goldmensch.jall.transformer.StringTransformer;
import io.github.goldmensch.jall.transformer.Transformer;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
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
   * Creates a new {@link Builder}
   *
   * @param transformer The {@link Transformer} that should use
   * @return A new {@link Builder} with the passed {@link Transformer}
   */
  static <T> Builder<T> newBuilder(@NotNull Transformer<T> transformer) {
    return new Builder<>(transformer);
  }

  /**
   * Registers a {@link Localization}, only one {@link Localization} can be registered per {@link
   * Locale}
   *
   * @param localization The {@link Localization} to be registered
   * @param override     Controls if the {@link Localization} should override the registered one
   * @throws io.github.goldmensch.jall.exceptions.LocExistInRegistryException if the {@link Locale}
   *                                                                          is already registered
   *                                                                          and {@param override}
   *                                                                          is false
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
   * @return A {@link io.github.goldmensch.jall.registry.Registry.UnmodifiableRegistry} of the
   * registry that belongs to this {@link Jall}
   */
  @NotNull
  Registry getRegistry();


  class Builder<T> {

    private final Transformer<T> transformer;
    private final Set<RegisterEntry> entries = new HashSet<>();
    private Locale fallback;
    private PlaceholderResolver resolver;

    private Builder(@NotNull Transformer<T> transformer) {
      this.transformer = transformer;
    }

    /**
     * Sets the fallback {@link Locale}
     *
     * @param fallback The fallback {@link Locale}
     */
    public Builder<T> setFallback(@NotNull Locale fallback) {
      this.fallback = fallback;
      return this;
    }

    /**
     * Sets the {@link PlaceholderResolver}
     *
     * @param resolver The {@link PlaceholderResolver}
     */
    public Builder<T> setPlaceholderResolver(@NotNull PlaceholderResolver resolver) {
      this.resolver = resolver;
      return this;
    }

    /**
     * Sets the {@link StandardResolver} as the {@link PlaceholderResolver}
     */
    public Builder<T> useStandardResolver() {
      setPlaceholderResolver(new StandardResolver());
      return this;
    }

    /**
     * Equals to {@link Jall#register(Localization, boolean)}
     *
     * @param localization The {@link Localization}
     * @param override     Controls if the {@link Localization} should override the registered one
     */
    public Builder<T> register(@NotNull Localization localization, boolean override) {
      entries.add(new RegisterEntry(localization, override));
      return this;
    }

    /**
     * Registers a {@link Localization} equals to {@link Localization#fromResourceBundle(ResourceBundle)}
     *
     * @param bundle   The {@link ResourceBundle}
     * @param override Controls if the {@link Localization} should override the registered one
     */
    public Builder<T> register(@NotNull ResourceBundle bundle, boolean override) {
      entries.add(new RegisterEntry(Localization.fromResourceBundle(bundle), override));
      return this;
    }

    /**
     * Builds a new {@link Jall}
     *
     * @return The new {@link Jall} with the properties of the builder
     */
    public Jall<T> build() {
      var localizer = Jall.create(fallback, transformer, resolver);
      entries.forEach(
          registerEntry -> localizer.register(registerEntry.localization, registerEntry.override));
      return localizer;
    }

    record RegisterEntry(Localization localization, boolean override) {

    }
  }
}
