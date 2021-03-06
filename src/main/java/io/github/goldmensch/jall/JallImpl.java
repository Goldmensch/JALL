package io.github.goldmensch.jall;

import io.github.goldmensch.jall.placeholder.PlaceholderResolver;
import io.github.goldmensch.jall.placeholder.Replacement;
import io.github.goldmensch.jall.registry.Registry;
import io.github.goldmensch.jall.registry.RegistryImpl;
import io.github.goldmensch.jall.transformer.Transformer;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@link Jall} implementation
 */
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
    registry.register(Objects.requireNonNull(localization), override);
  }

  @Override
  public T localize(@NotNull String key, @Nullable Locale loc,
      @NotNull Replacement... replacements) {
    Objects.requireNonNull(key);
    var locale = loc != null
        ? loc
        : fallbackLocale;

    var message = resolvePlaceholders(registry.containsLocale(locale)
        ? registry.getLocalization(key, locale)
        : registry.getLocalization(key, fallbackLocale), Set.of(replacements));

    return transformer.transform(message);
  }

  private String resolvePlaceholders(String message, Set<Replacement> replacements) {
    return resolver.resolve(message, replacements);
  }

  @NotNull
  @Override
  public Locale getFallbackLocale() {
    return fallbackLocale;
  }

  @NotNull
  @Override
  public Registry getRegistry() {
    return Registry.unmodifiable(registry);
  }
}
