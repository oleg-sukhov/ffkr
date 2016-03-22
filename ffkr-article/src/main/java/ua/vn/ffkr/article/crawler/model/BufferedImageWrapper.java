package ua.vn.ffkr.article.crawler.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImageWrapper {
    private String name;
    private BufferedImage image;

    public BufferedImageWrapper(String name, BufferedImage image) {
        this.name = name;
        this.image = resizeImage(image);
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage resizeImage(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(210, 216, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 210, 216, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }
}
