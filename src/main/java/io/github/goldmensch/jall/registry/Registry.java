package io.github.goldmensch.jall.registry;

import io.github.goldmensch.jall.Localization;
import java.util.Locale;

public interface Registry {

  static UnmodifiableRegistry unmodifiable(Registry registry) {
    return new UnmodifiableRegistry(registry);
  }

  String getLocalization(String key, Locale locale);

  void register(Localization localization, boolean override);

  final class UnmodifiableRegistry implements Registry {

    private final Registry registry;

    private UnmodifiableRegistry(Registry registry) {
      this.registry = registry;
    }

    /**
     * Gets the localization which belongs to the {@param key} and {@param Locale}
     *
     * @param key    The key the localization belongs to
     * @param locale The locale which should use
     * @return The message
     * @throws io.github.goldmensch.jall.exceptions.NoLocalizationFoundException if the {@link Locale} is
     *                                                                      not registered or no
     *                                                                      localization was found
     *                                                                      with the given {@param
     *                                                                      key}
     */
    @Override
    public String getLocalization(String key, Locale locale) {
      return registry.getLocalization(key, locale);
    }

    /**
     * @throws UnsupportedOperationException Operation not supported
     */
    @Override
    public void register(Localization localization, boolean override) {
      throw new UnsupportedOperationException();
    }

  }
}
