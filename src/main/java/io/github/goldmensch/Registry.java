package io.github.goldmensch;

import io.github.goldmensch.exceptions.LocExistInRegistryException;
import io.github.goldmensch.exceptions.NoTranslationFoundException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Registry {

  private final Map<Locale, Localization> registry = new ConcurrentHashMap<>();

  public void register(Localization localization, boolean override) {
    var locale = localization.getLocale();

    if (!override && registry.containsKey(locale)) {
      throw new LocExistInRegistryException(locale);
    }
    registry.put(locale, localization);
  }

  public String getTranslation(String key, Locale locale) {
    if (registry.containsKey(locale)) {
      return registry.get(locale).getLocalization(key).orElseThrow(() ->
          new NoTranslationFoundException(key, locale));
    }
    throw new NoTranslationFoundException(key, locale);
  }

  public boolean containsLocale(Locale locale) {
    return registry.containsKey(locale);
  }

}
