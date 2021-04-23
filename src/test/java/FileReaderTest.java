import com.jobsity.utilities.FileReader;
import org.junit.Test;
import org.junit.Assert;

public class FileReaderTest {

    @Test
    public void canReadText(){
        String path = "src/main/resources/data.txt";

        FileReader fr = new FileReader();

        Assert.assertEquals("juan    3", fr.getData(path));

    }

}
