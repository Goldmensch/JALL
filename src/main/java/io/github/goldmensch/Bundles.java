package io.github.goldmensch;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

final class Bundles {

  private Bundles() {
  }

  public static Map<String, String> toMap(ResourceBundle bundle) {
    return bundle.keySet()
        .stream()
        .collect(Collectors.toMap(s -> s, bundle::getString));
  }

}
