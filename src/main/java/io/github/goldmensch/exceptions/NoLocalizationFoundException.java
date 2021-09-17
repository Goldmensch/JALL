package io.github.goldmensch.exceptions;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Thrown if no localization was found
 */
public class NoLocalizationFoundException extends RuntimeException {

  public NoLocalizationFoundException(String key, Locale locale) {
    super(MessageFormat.format("No translation found for key {0} and Locale: {1}",
        key, locale));
  }

}
