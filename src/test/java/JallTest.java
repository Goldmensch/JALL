import io.github.goldmensch.jall.Jall;
import io.github.goldmensch.jall.Localization;
import io.github.goldmensch.jall.placeholder.Replacement;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

public class JallTest {

  public static ResourceBundle englishBundle = ResourceBundle.getBundle("testBundle",
      Locale.ENGLISH);
  public static ResourceBundle germanBundle = ResourceBundle.getBundle("testBundle", Locale.GERMAN);

  public static void testLocalize(Jall<String> localizer) {
    System.out.println(
        localizer.localize("hello", Locale.ENGLISH, Replacement.create("name", "Jeff")));
    System.out.println(
        localizer.localize("hello", Locale.GERMAN, Replacement.create("name", "Jeff")));
  }

  @Test
  public void fullTest() {
    var translator = Jall.createStandard(Locale.ENGLISH);
    translator.register(Localization.fromResourceBundle(englishBundle), false);
    translator.register(Localization.fromResourceBundle(germanBundle), false);

    testLocalize(translator);
  }

}
