package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LitRes_ParamsTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }


    @ValueSource(strings = {"Harry Potter", "Sherlock Holmes"})
    @ParameterizedTest(name = "При вводе {0} в строку поиска в списке присутствует книга с автором {0}")
    void bookSearchCommonTest(String testData) {
        Selenide.open("https://www.litres.ru/luchshie-knigi/?utm_source=yandex&utm_medium=" +
                "cpc&utm_campaign=web_brand_search%20559380598%7C45195153&utm_content=7867776224&utm_term=литрес%" +
                "20руСанкт-Петербург_2&yadclid=2181937&yadordid=27597567&yclid=7935212405447720959");
        $(".Search-module__input").setValue(testData);
        $(".Search-module__button").click();
        $("#page-wrap").shouldHave(text(testData));
    }


//    @CsvFileSource(resources = "test_data/1.csv") - тянем значения из отдельного файла
    @CsvSource(value = {
            "Harry Potter, The Ultimate Harry Potter and Philosophy",
            "Sherlock Holmes, 5 best Sherlock Holmes Stories"
    })
    @ParameterizedTest(name = "При вводе {0} в строку поиска в списке присутствует книга с названием {1}")
    void bookSearchComplexTest(String searchData, String expectedResult) {
        Selenide.open("https://www.litres.ru/luchshie-knigi/?utm_source=yandex&utm_medium=" +
                "cpc&utm_campaign=web_brand_search%20559380598%7C45195153&utm_content=7867776224&utm_term=литрес%" +
                "20руСанкт-Петербург_2&yadclid=2181937&yadordid=27597567&yclid=7935212405447720959");
        $(".Search-module__input").setValue(searchData);
        $(".Search-module__button").click();
        $("#page-wrap").shouldHave(text(expectedResult));
    }


    static Stream<Arguments> bookSearchVeryComplexTest(){
        return Stream.of(
                Arguments.of("Harry Potter", List.of("Harry","Potter")),
                Arguments.of("Sherlock Holmes", List.of("Sherlock","Holmes"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "При вводе {0} в строку поиска в списке присутствует книга с названием {1}")
    void bookSearchVeryComplexTest(String searchData, List<String> expectedResult) {
        Selenide.open("https://www.litres.ru/luchshie-knigi/?utm_source=yandex&utm_medium=" +
                "cpc&utm_campaign=web_brand_search%20559380598%7C45195153&utm_content=7867776224&utm_term=литрес%" +
                "20руСанкт-Петербург_2&yadclid=2181937&yadordid=27597567&yclid=7935212405447720959");
        $(".Search-module__input").setValue(searchData);
        $(".Search-module__button").click();
        $$(".page-wrap").shouldHave(texts(expectedResult)); /* падает тест с ошибкой Texts size mismatch,
         как и у Димы на лекции, не нашли решение*/
    }

    @EnumSource(Seasons_enum.class)
    @ParameterizedTest
    void enumTest(Seasons_enum seasons_enum) {
        Selenide.open("https://www.litres.ru/luchshie-knigi/?utm_source=yandex&utm_medium=" +
                "cpc&utm_campaign=web_brand_search%20559380598%7C45195153&utm_content=7867776224&utm_term=литрес%" +
                "20руСанкт-Петербург_2&yadclid=2181937&yadordid=27597567&yclid=7935212405447720959");
        $(".Search-module__input").setValue(seasons_enum.desc);
        $(".Search-module__button").click();
        $("#page-wrap").shouldHave(text(seasons_enum.desc));
    }
}
