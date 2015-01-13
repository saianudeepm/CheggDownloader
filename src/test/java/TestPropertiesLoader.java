import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: sai
 * Date: 7/16/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestPropertiesLoader {

    PropertiesLoader loader;


    @Test
    public void testLoad() throws Exception {

     loader.load(new File("src/main/resources/SiteDetails.properties"));


    }
}
