package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PropertiesTests {

    @Test
    @Disabled
    public void getPropertyTest(){
        System.out.println("a = " + System.getProperty("a"));
    }
}
