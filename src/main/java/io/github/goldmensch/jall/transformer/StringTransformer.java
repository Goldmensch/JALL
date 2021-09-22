package io.github.goldmensch.jall.transformer;

/**
 * A standard localizer which simply return the same {@link String}
 */
public final class StringTransformer implements Transformer<String> {

  @Override
  public String transform(String value) {
    return value;
  }
}
