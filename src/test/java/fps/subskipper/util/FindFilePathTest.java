package fps.subskipper.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class FindFilePathTest {

 @Test @Ignore
 public void findFilePathTest() {

     FindFilePath findFilePath = new FindFilePath();
     String path = findFilePath.findFilepathRecursively("D:\\002-git\\SubSkipper", "Torpedo.java");
     System.out.println(path);
     Assert.assertNotNull(path);

 }

}