package io.github.goldmensch.placeholder;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>A standard {@link PlaceholderResolver} </p>
 * <p>Placeholders are specified with {name} </p>
 * <p>Ex: 'Hi, i'm {age} years old.' will be: 'Hi, i'm 17 years old.' if a {@link Replacement} with
 * the key 'age' and value '17' is passed. </p>
 * <p>But if no {@link Replacement} with the key 'age' will be
 * passed, the placeholder will simply removed: 'Hi, i'm years old.'</p>
 */
public class StandardResolver implements PlaceholderResolver {

  // Provided by a nice guy on Discord :3
  @Override
  public String resolve(String input, Set<Replacement> rep) {
    var repMap = rep.stream()
        .collect(Collectors.toMap(Replacement::key, Replacement::value));

    var builder = new StringBuilder();
    var escape = -2;
    var mark = 0;
    var valid = false;
    for (var i = 0; i < input.length(); i++) {
      var c = input.charAt(i);
      switch (c) {
        case '\\':
          if (escape == i - 1) {
            builder.append(input, mark, i);
            mark = i + 1; // skip one \
            escape = -1; // reset
          } else {
            escape = i;
          }
          break;
        case '{':
          if (escape != i - 1) { // not escaped, start looking for a key
            builder.append(input, mark, i);
            mark = i;
            valid = true;
          } else { // escaped, strip the \
            builder.append(input, mark, escape);
            mark = i;
          }
          break;
        case '}':
          if (valid) {
            var key = input.substring(mark + 1, i);
            var replace = repMap.getOrDefault(key, "");
            builder.append(replace);
            valid = false;
            mark = i + 1;
          }
          break;
        default:
          if (!Character.isAlphabetic(c)) {
            valid = false;
          }
      }
    }
    if (mark < input.length()) {
      builder.append(input, mark, input.length());
    }
    return builder.toString();
  }
}
