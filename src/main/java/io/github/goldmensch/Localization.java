package io.github.goldmensch;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

public final class Localization {

  private final Locale locale;
  private final Map<String, String> localizations = new ConcurrentHashMap<>();

  private Localization(Locale locale) {
    this.locale = locale;
  }

  private static Localization create(@NotNull Locale locale) {
    Objects.requireNonNull(locale);
    return new Localization(locale);
  }

  public static Localization create(@NotNull Locale locale,
                                    @NotNull Map<String, String> localizations) {
    Objects.requireNonNull(localizations);
    var loc = create(locale);
    loc.localizations.putAll(localizations);
    return loc;
  }

  public static Localization fromResourceBundle(@NotNull ResourceBundle bundle) {
    Objects.requireNonNull(bundle);
    return create(bundle.getLocale(), Bundles.toMap(bundle));
  }

  public Optional<String> getLocalization(@NotNull String key) {
    Objects.requireNonNull(key);
    return Optional.ofNullable(localizations.get(key));
  }

  public Locale getLocale() {
    return locale;
  }

  public Map<String, String> getLocalizationMap() {
    return Collections.unmodifiableMap(localizations);
  }
}
