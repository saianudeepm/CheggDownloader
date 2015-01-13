import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sai
 * Date: 7/16/13
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesLoader {

    public static HashMap<String,String> load(File propertiesFile) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(propertiesFile));
        HashMap<String,String> propertiesMap = new HashMap<String, String>();
        String line=null;
        while((line=br.readLine())!=null){
            if(line.startsWith("#") || line.trim().equals(""))
                continue;
            int valuePosition= line.indexOf("=");
            propertiesMap.put(line.substring(0,valuePosition),line.substring(valuePosition+1,line.length()));
        }
        return propertiesMap;
    }
}
