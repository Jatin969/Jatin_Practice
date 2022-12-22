package snapshot;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class snapshotCode {

    public void TakeAScreenShot(){
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = null;
        try {
            capture = new Robot().createScreenCapture(screenRect);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        File imageFile = new File(String.format("pictures/%s.png", NameGenerator()));
        if(!imageFile.mkdirs())  throw new RuntimeException("Directory can't be created.");
        try {
            ImageIO.write(capture, "png", imageFile );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert (imageFile .exists());
    }

    public static String NameGenerator(){
        String systemName = null;
        try {
            systemName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss_SSS");
        return systemName + "_" + simple.format(new Date(System.currentTimeMillis()));
    }

    @Test
    public void test(){
        TakeAScreenShot();
    }
}
