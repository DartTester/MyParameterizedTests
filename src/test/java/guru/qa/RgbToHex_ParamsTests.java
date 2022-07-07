package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;


public class RgbToHex_ParamsTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @CsvSource(value = {
            "22, 94, 109, #165e6d",
            "0, 0, 0, 0, #000000"
    })
    @ParameterizedTest(name = "Конвертируем red {0} green {1} blue {2} в Blumine {3}")
    void blumineColorTest(String data1, String data2, String data3, String result) {
        Selenide.open("https://www.rgbtohex.net/");
        $("#main-segment").shouldHave(text("RGB to HEX Color Converter"));
        $(byName("red")).setValue(data1);
        $(byName("green")).setValue(data2);
        $(byName("blue")).setValue(data3);
        $(byName("Submit")).click();
        $(".col-md-12").shouldHave(text(result));
    }

    }
