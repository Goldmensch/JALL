package io.github.goldmensch;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

/***
 * Contains all localizations to the corresponding {@link Locale} <br>
 * A {@link Localization} is fully thread safe and immutable
 */
public final class Localization {

  private final Locale locale;
  private final Map<String, String> localizations = new ConcurrentHashMap<>();

  private Localization(Locale locale) {
    this.locale = locale;
  }

  private static Localization create(@NotNull Locale locale) {
    return new Localization(Objects.requireNonNull(locale));
  }

  /**
   * Creates a new {@link Localization} containing the localizations from the Map
   *
   * @param locale        The {@link Locale} which should belong to the {@link Localization}
   * @param localizations A {@link Map} which contains the localizations
   * @return A Localizer which contains the localizations from the map
   */
  public static Localization create(@NotNull Locale locale,
                                    @NotNull Map<String, String> localizations) {
    var loc = create(locale);
    loc.localizations.putAll(Objects.requireNonNull(localizations));
    return loc;
  }

  /**
   * <p>Creates a new {@link Localization} from a {@link ResourceBundle} </p>
   * <p> The {@link Locale} is taken from the ResourceBundle </p>
   *
   * @param bundle The {@link ResourceBundle} containing the localizations
   * @return A localizer which contains the localization from the {@link ResourceBundle}
   */
  public static Localization fromResourceBundle(@NotNull ResourceBundle bundle) {
    Objects.requireNonNull(bundle);
    return create(bundle.getLocale(), Bundles.toMap(bundle));
  }

  /**
   * Gets a localization with the given key
   *
   * @param key The key of the localization
   * @return A {@link Optional} containing the localizations, or if no localization was found, an
   * empty {@link Optional}
   */
  public Optional<String> getLocalization(@NotNull String key) {
    return Optional.ofNullable(localizations.get(key));
  }


  /**
   * @return The {@link Locale} which the {@link Localization} corresponding to
   */
  public Locale getLocale() {
    return locale;
  }

  /**
   * @return An unmodifiable {@link ConcurrentHashMap} containing the localizations
   */
  public Map<String, String> getLocalizationMap() {
    return Collections.unmodifiableMap(localizations);
  }
}
