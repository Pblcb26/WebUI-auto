package org.example;

import ch.qos.logback.classic.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static Logger logger = (Logger) LoggerFactory.getLogger(AppTest.class);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        logger.error("error");
        logger.debug("123564");
        assertTrue(true);
        //Assertions.assertDoesNotThrow() - проверяет что нет эксепшена
        //Assertions.assertThrows() - ожидаем ошибку
        /*Assertions.assertAll(()-> { //можно использовать лямбда-выражения или использовать все проверки, несмотря на то, что часть из них упадет
            logger.error("error");
            logger.error("error");
            logger.error("error");
        });*/
    }

    @Test
    @Disabled //не запускать
    @Order(1) //задать порядок тестов, но каждый тест должен быть самодостаточен
    @RepeatedTest(value = 3) //кол-во повторов теста
    void TestMy() {
        System.out.println("My");
    }

    @DisplayName("Слово является палиндромом") //отображение имени теста
    @ParameterizedTest //тест с параметрами
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    public void isPalindromTest(String word) {
        //Assertions.assertTrue(functions.isPalindrome(word));
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
    void testWithEnumSource(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    @ParameterizedTest
    @CsvSource({"Foo, 1", "bar, 2", "dnq, 3"})
        //или @CsvFileSource(resources = "/test.csv")
    void testWithCsv(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    @Nested // группирует в своем сабклассе
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewBox() {
            //box = new Box
        }

        @Test
        @DisplayName("is empty")
        @Timeout(value = 10) //время на выполнение теста
        void isEmptyTest() {
            //assertThat(box.isEmpty());
        }
    }
}
