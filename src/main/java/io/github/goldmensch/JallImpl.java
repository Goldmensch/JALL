package io.github.goldmensch;

import io.github.goldmensch.placeholder.PlaceholderResolver;
import io.github.goldmensch.placeholder.Replacement;
import io.github.goldmensch.transformer.Transformer;

import java.util.*;

import org.jetbrains.annotations.NotNull;

public final class JallImpl<T> implements Jall<T> {

  private final Transformer<T> transformer;
  private final PlaceholderResolver resolver;
  private final Locale fallbackLocale;
  private final Registry registry = new Registry();

  JallImpl(Locale fallbackLocale, Transformer<T> transformer, PlaceholderResolver resolver) {
    this.transformer = transformer;
    this.fallbackLocale = fallbackLocale;
    this.resolver = resolver;
  }

  @Override
  public void register(@NotNull Localization localization, boolean override) {
    registry.register(localization, override);
  }

  @Override
  public T translate(@NotNull String key, @NotNull Locale locale, @NotNull Replacement... replacements) {
    Objects.requireNonNull(key);
    Objects.requireNonNull(locale);

    var message = resolvePlaceholders(registry.containsLocale(locale)
            ? registry.getTranslation(key, locale)
            : registry.getTranslation(key, fallbackLocale), Set.of(replacements));

    return transformer.transform(message);
  }

  private String resolvePlaceholders(String message, Set<Replacement> replacements) {
    return resolver.resolve(message, replacements);
  }
}
