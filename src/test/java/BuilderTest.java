import io.github.goldmensch.jall.Jall;
import io.github.goldmensch.jall.transformer.StringTransformer;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class BuilderTest {

  @Test
  public void testBuilder() {
    var localizer = Jall.newBuilder(new StringTransformer())
        .setFallback(Locale.ENGLISH)
        .useStandardResolver()
        .register(JallTest.englishBundle, false)
        .register(JallTest.germanBundle, false)
        .build();

    JallTest.testLocalize(localizer);
  }

}
