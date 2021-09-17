package io.github.goldmensch;

import io.github.goldmensch.placeholder.PlaceholderResolver;
import io.github.goldmensch.placeholder.Replacement;
import io.github.goldmensch.registry.Registry;
import io.github.goldmensch.transformer.Transformer;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class JallImpl<T> implements Jall<T> {

  private final Transformer<T> transformer;
  private final PlaceholderResolver resolver;
  private final Locale fallbackLocale;
  private final RegistryImpl registry = new RegistryImpl();

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
  public T translate(@NotNull String key, @Nullable Locale loc,
                     @NotNull Replacement... replacements) {
    Objects.requireNonNull(key);
    var locale = loc != null
        ? loc
        : fallbackLocale;

    var message = resolvePlaceholders(registry.containsLocale(locale)
        ? registry.getTranslation(key, locale)
        : registry.getTranslation(key, fallbackLocale), Set.of(replacements));

    return transformer.transform(message);
  }

  private String resolvePlaceholders(String message, Set<Replacement> replacements) {
    return resolver.resolve(message, replacements);
  }

  @Override
  public Locale getFallbackLocale() {
    return fallbackLocale;
  }

  @Override
  public Registry getRegistry() {
    return Registry.unmodifiable(registry);
  }
}
