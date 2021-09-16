import io.github.goldmensch.Bundles;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

public class BundlesTest {

  @Test
  public void testBundleToMap() {
    var map = Bundles.toMap(ResourceBundle.getBundle("testBundle", Locale.ENGLISH));
    map.forEach((key, value) -> System.out.printf("key: %s -- value: %s %n", key, value));
  }
}
