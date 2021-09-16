package io.github.goldmensch;

import io.github.goldmensch.transformer.Transformer;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public final class JallImpl<T> implements Jall<T> {

  private final Transformer<T> transformer;
  private final Locale fallbackLocale;
  private final Registry registry = new Registry();

  JallImpl(Transformer<T> transformer, Locale fallbackLocale) {
    this.transformer = transformer;
    this.fallbackLocale = fallbackLocale;
  }

  @Override
  public void register(@NotNull Localization localization, boolean override) {
    registry.register(localization, override);
  }

  @Override
  public T translate(@NotNull String key, @NotNull Locale locale) {
    return transformer.transform(registry.containsLocale(locale)
        ? registry.getTranslation(key, locale)
        : registry.getTranslation(key, fallbackLocale));
  }
}
