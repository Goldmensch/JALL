import io.github.goldmensch.jall.placeholder.Replacement;
import io.github.goldmensch.jall.placeholder.StandardResolver;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class PlaceholderResolveTest {

  @Test
  public void testPlaceholderResolve() {
    var resolver = new StandardResolver();

    var inputs = List.of(
        "\\{test}",
        "\\\\{test}",
        "{test}",
        "{test}test 1234{test}",
        "\\{test}, {test}, \\  \\ {  {test}",
        "\\\\\\ test {points} \\\\\\");
    //var inputs = List.of("You reached {points} Point\\{s}!");
    for (String string : inputs) {
      var msg = resolver.resolve(string,
          Set.of(Replacement.create("test", "smart"), Replacement.create("points", 1)));
      System.out.println(msg);
    }
  }


}
