package io.github.goldmensch.registry;

import io.github.goldmensch.Localization;
import java.util.Locale;

public interface Registry {

  static UnmodifiableRegistry unmodifiable(Registry registry) {
    return new UnmodifiableRegistry(registry);
  }

  String getTranslation(String key, Locale locale);

  void register(Localization localization, boolean override);

  final class UnmodifiableRegistry implements Registry {

    private final Registry registry;

    private UnmodifiableRegistry(Registry registry) {
      this.registry = registry;
    }

    @Override
    public String getTranslation(String key, Locale locale) {
      return registry.getTranslation(key, locale);
    }

    @Override
    public void register(Localization localization, boolean override) {
      throw new UnsupportedOperationException();
    }

  }
}
