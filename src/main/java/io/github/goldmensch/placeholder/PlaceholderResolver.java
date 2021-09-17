package io.github.goldmensch.placeholder;

import java.util.Set;

@FunctionalInterface
public interface PlaceholderResolver {

  String resolve(String message, Set<Replacement> replacements);
}
