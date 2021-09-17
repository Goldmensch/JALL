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
    return new Localization(Objects.requireNonNull(locale));
  }

  public static Localization create(@NotNull Locale locale,
                                    @NotNull Map<String, String> localizations) {
    var loc = create(locale);
    loc.localizations.putAll(Objects.requireNonNull(localizations));
    return loc;
  }

  public static Localization fromResourceBundle(@NotNull ResourceBundle bundle) {
    Objects.requireNonNull(bundle);
    return create(bundle.getLocale(), Bundles.toMap(bundle));
  }

  public Optional<String> getLocalization(@NotNull String key) {
    return Optional.ofNullable(localizations.get(key));
  }

  public Locale getLocale() {
    return locale;
  }

  public Map<String, String> getLocalizationMap() {
    return Collections.unmodifiableMap(localizations);
  }
}
