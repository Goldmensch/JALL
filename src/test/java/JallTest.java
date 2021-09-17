import io.github.goldmensch.Jall;
import io.github.goldmensch.Localization;

import java.util.Locale;
import java.util.ResourceBundle;

import io.github.goldmensch.placeholder.Replacement;
import org.junit.jupiter.api.Test;

public class JallTest {

  @Test
  public void fullTest() {
    var englishBundle = ResourceBundle.getBundle("testBundle", Locale.ENGLISH);
    var germanBundle = ResourceBundle.getBundle("testBundle", Locale.GERMAN);

    var translator = Jall.createStandard(Locale.ENGLISH);
    translator.register(Localization.fromResourceBundle(englishBundle), false);
    translator.register(Localization.fromResourceBundle(germanBundle), false);

    System.out.println(translator.translate("hello", Locale.ENGLISH));
    System.out.println(translator.translate("hello", Locale.GERMAN, Replacement.create("name", "Jeff")));
    System.out.println(translator.translate("hello", Locale.CANADA, Replacement.create("name", "Jeff")));
  }

}
