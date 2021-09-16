package io.github.goldmensch.exceptions;

import java.text.MessageFormat;
import java.util.Locale;

public class NoTranslationFoundException extends RuntimeException {

  public NoTranslationFoundException(String key, Locale locale) {
    super(MessageFormat.format("No translation found for key {0} and Locale: {1}",
        key, locale));
  }

}
