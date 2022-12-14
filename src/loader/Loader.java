package loader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Loader {
    public static BufferedImage load(String filename) throws IOException {
        return ImageIO.read(new File(filename));
    }
}
