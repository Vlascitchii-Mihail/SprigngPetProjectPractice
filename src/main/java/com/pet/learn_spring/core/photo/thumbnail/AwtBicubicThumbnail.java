package com.pet.learn_spring.core.photo.thumbnail;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
@ThumbnailRendering(ThumbnailRendering.RenderQuality.QUALITY)
public class AwtBicubicThumbnail implements Thumbnail{
    private static BufferedImage create(BufferedImage source, int width, int height) {
        double thunbRatio = (double) width / height;
        double imageRatio = (double) source.getWidth() / source.getHeight();

        if (thunbRatio < imageRatio) height = (int) (width / imageRatio);
        else width = (int) (height * imageRatio);

        BufferedImage thumb = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_3BYTE_BGR
        );

        Graphics2D g2 = thumb.createGraphics();
        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        g2.drawImage(source, 0, 0, width, height, null);
        g2.dispose();
        return thumb;
    }

    @Override
    public byte[] thumbnail(byte[] imageBytes) {
        try {
            InputStream is = new ByteArrayInputStream(imageBytes);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage thumbnail = create(ImageIO.read(is), 200, 200);
            ImageIO.write(thumbnail, "jpg", baos);
            return baos.toByteArray();
        } catch(IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
