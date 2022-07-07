package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnnotationExamples {

    @Disabled("TICKET ALT-2109")
    @Test
    void firstTest () {
        Assertions.assertTrue(3>2);
    }

    @DisplayName("Проверям, что 3 меньше 5")
    @Test
    void secondTest () {
        Assertions.assertTrue(3<5);
    }

}
