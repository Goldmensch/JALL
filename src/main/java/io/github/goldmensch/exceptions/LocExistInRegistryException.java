package io.github.goldmensch.exceptions;

import java.text.MessageFormat;
import java.util.Locale;

public class LocExistInRegistryException extends RuntimeException {

  public LocExistInRegistryException(Locale locale) {
    super(MessageFormat.format("A localization for Locale: {0} already exist in this Registry.",
        locale));
  }

}
