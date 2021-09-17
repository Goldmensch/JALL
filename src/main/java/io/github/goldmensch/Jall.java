package io.github.goldmensch;

import io.github.goldmensch.placeholder.PlaceholderResolver;
import io.github.goldmensch.placeholder.Replacement;
import io.github.goldmensch.placeholder.StandardResolver;
import io.github.goldmensch.transformer.StringTransformer;
import io.github.goldmensch.transformer.Transformer;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public interface Jall<T> {

  static <T> Jall<T> create(@NotNull Locale fallback, @NotNull Transformer<T> transformer,
                            @NotNull PlaceholderResolver resolver) {
    return new JallImpl<>(fallback, transformer, resolver);
  }

  static Jall<String> createStandard(@NotNull Locale fallback) {
    return create(fallback, new StringTransformer(), new StandardResolver());
  }

  void register(@NotNull Localization localization, boolean override);

  T translate(@NotNull String key, @NotNull Locale locale, Replacement... replacements);
}
