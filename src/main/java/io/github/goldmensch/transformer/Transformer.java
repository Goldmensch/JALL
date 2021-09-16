package io.github.goldmensch.transformer;

public interface Transformer<T> {

  T transform(String value);
}
