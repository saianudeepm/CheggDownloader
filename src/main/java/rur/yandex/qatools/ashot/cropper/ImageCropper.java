package ru.yandex.qatools.ashot.cropper;

import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.Coords;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Set;

/**
 * @author <a href="pazone@yandex-team.ru">Pavel Zorin</a>
 */

public abstract class ImageCropper implements Serializable {

    public Screenshot crop(BufferedImage image, Set<Coords> cropArea) {
        return cropArea.isEmpty()
                ? new Screenshot(image)
                : cropScreenshot(image, cropArea);
    }

    protected abstract Screenshot cropScreenshot(BufferedImage image, Set<Coords> coordsToCompare);

}
