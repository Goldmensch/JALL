import io.github.goldmensch.Jall;
import io.github.goldmensch.Localization;
import java.util.Collections;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class RegistryTest {

  @Test
  public void registryTest() {
    var jall = Jall.createStandard(Locale.ENGLISH);

    jall.getRegistry().getTranslation("test", Locale.ENGLISH);

    jall.getRegistry().register(
        Localization.create(Locale.ENGLISH, Collections.EMPTY_MAP), false);
  }

}
