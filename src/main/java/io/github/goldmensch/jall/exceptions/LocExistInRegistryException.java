package io.github.goldmensch.jall.exceptions;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Thrown if a localization with this {@link Locale} already exist
 */
public class LocExistInRegistryException extends RuntimeException {

  public LocExistInRegistryException(Locale locale) {
    super(MessageFormat.format("A localization for Locale: {0} already exist in this Registry.",
        locale));
  }

}
