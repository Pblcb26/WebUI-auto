package org.example;

import ch.qos.logback.classic.Logger;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

public class TriangleTest {

    private static Logger logger = (Logger) LoggerFactory.getLogger(TriangleTest.class);

    @Nested
    @DisplayName("Треугольник")
    class triangleTrue {

        Triangle triangle = new Triangle(7, 8, 5);

        @Test
        @DisplayName("NotTriangleException. Фигура является треугольником")
        void isTriangleTest() {
            NotTriangleException thrown = Assertions.assertThrows(NotTriangleException.class, triangle::isTriangle,
                    "Ожидалось NotTriangleException исключение.");
            Assertions.assertEquals("Данная фигура не треугольник. Введите корректные значения.", thrown.getMessage());
        }

        @Test
        @DisplayName("DataFormatException. Корректные значения")
        void dataFormatTest() {
            DataFormatException thrown = Assertions.assertThrows(DataFormatException.class, triangle::dataFormat,
                    "Ожидалось DataFormatException исключение.");
            Assertions.assertEquals("Введены некорректные значения. Введите целое положительное число.", thrown.getMessage());
        }

        @Test
        @DisplayName("Расчет площади треугольника.")
        void triangleAreaTest() {
            Assertions.assertEquals(17.320508075688775, triangle.getArea(), "Расчет некорректен.");
        }
    }

    @Test
    @DisplayName("Исключение NotTriangleException. Фигура не является треугольником")
    void notTriangleTest() {
        Triangle triangle = new Triangle(6, 1, 1);
        NotTriangleException thrown = Assertions.assertThrows(NotTriangleException.class, triangle::isTriangle,
                "Ожидалось NotTriangleException исключение.");
        Assertions.assertEquals("Данная фигура не треугольник. Введите корректные значения.", thrown.getMessage());
    }

    @Test
    @DisplayName("Исключение DataFormatException. Введены некорректные значения.")
    void badDataFormatTest() {
        Triangle triangle = new Triangle(3, -7, 0);
        DataFormatException thrown = Assertions.assertThrows(DataFormatException.class, triangle::dataFormat,
                "Ожидалось DataFormatException исключение.");
        Assertions.assertEquals("Введены некорректные значения. Введите целое положительное число.", thrown.getMessage());
    }
}