import io.github.goldmensch.jall.Jall;
import io.github.goldmensch.jall.Localization;
import io.github.goldmensch.jall.placeholder.Replacement;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

public class JallTest {

  @Test
  public void fullTest() {
    var englishBundle = ResourceBundle.getBundle("testBundle", Locale.ENGLISH);
    var germanBundle = ResourceBundle.getBundle("testBundle", Locale.GERMAN);

    var translator = Jall.createStandard(Locale.ENGLISH);
    translator.register(Localization.fromResourceBundle(englishBundle), false);
    translator.register(Localization.fromResourceBundle(germanBundle), false);

    System.out.println(translator.localize("hello", Locale.ENGLISH));
    System.out.println(
        translator.localize("hello", Locale.GERMAN, Replacement.create("name", "Jeff")));
  }

}
