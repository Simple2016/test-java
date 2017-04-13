package lqw.test.test_img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Hello world!
 *
 */
public class Img2character {

    public static void main(String[] args) {
        String path = Thread.currentThread().getContextClassLoader().getResource("dola.jpg").getPath();

        System.err.println(path.substring(1));
        String a = _BitmapConvert(path.substring(1));
        System.out.println(a);
    }

    public static String _BitmapConvert(String imgpath) {
        StringBuffer _sb = new StringBuffer();
        File imgfile = new File(imgpath);
        char[] charset = { '*', '0', '8', '6', '4', '3', '2', '1', '.', ' ' };
        try {
            BufferedImage bufferedimg = ImageIO.read(imgfile);
            int bitmapH = bufferedimg.getHeight() / 5;
            int bitmapW = bufferedimg.getWidth() / 5 * 2;
            // buff = toBufferedImage(buff.getScaledInstance(bitmapW, bitmapH, Image.SCALE_SMOOTH));
            BufferedImage buff = new BufferedImage(bitmapW, bitmapH, BufferedImage.TYPE_INT_RGB);
            buff.getGraphics().drawImage(bufferedimg.getScaledInstance(bitmapW, bitmapH, java.awt.Image.SCALE_SMOOTH),
                            0, 0, null);
            // ImageIO.write(buff, "png", new File("f:\\test.png"));//保存图片
            for (int y = 0; y < bitmapH; y++) {
                for (int x = 0; x < bitmapW; x++) {
                    int rgb = buff.getRGB(x, y);
                    Color c = new Color(rgb);
                    int cc = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                    _sb.append(charset[(cc * 10 - 1) / 255]);
                }
                _sb.append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _sb.toString();
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        // boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            /*
             * if (hasAlpha) { transparency = Transparency.BITMASK; }
             */

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            // int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
            /*
             * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
             */
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }
}
