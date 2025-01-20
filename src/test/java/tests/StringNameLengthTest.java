package tests;

import org.junit.jupiter.api.Test;
import lib.Assertions;

public class StringNameLengthTest {

    @Test
    public void stringNameLongTest() {
        String hello = "Hello, world";
        Assertions.assertNameByLength(hello);
    }
}
