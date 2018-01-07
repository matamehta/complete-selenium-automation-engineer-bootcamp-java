import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by SeleniumGuru.com on 12/10/17.
 */
public class JUnitTestBeforeAfterClass {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Running before Class");
    }

    @Test
    public void test_add_positive_numbers(){
        Addition app1 = new Addition();
        assertEquals(10, app1.add(5, 5));
        assertEquals(50, app1.add(45, 5));
        assertNotEquals(500, app1.add(250, 249));
        System.out.println("Running addition test for positive numbers");
    }

    @Test
    public void test_add_negative_numbers(){
        Addition app1 = new Addition();
        assertEquals(-10, app1.add(-5, -5));
        assertNotEquals(-500, app1.add(-255, -35));
        System.out.println("Running addition test for negative numbers");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("Running after Class");
    }
}
