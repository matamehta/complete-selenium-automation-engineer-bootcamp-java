import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by SeleniumGuru.com on 12/10/17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Provide all the test that you wanted to run
        JUnitTest.class,
        JUnitTestBeforeAfterClass.class
})

public class JUnitTestSuite {

}
