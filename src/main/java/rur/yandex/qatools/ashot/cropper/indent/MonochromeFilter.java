package ru.yandex.qatools.ashot.cropper.indent;

import ru.yandex.qatools.ashot.util.ImageTool;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * @author <a href="pazone@yandex-team.ru">Pavel Zorin</a>
 */

public class MonochromeFilter implements IndentFilter {
    @Override
    public BufferedImage apply(BufferedImage image) {
        return darken(image);
    }

    private BufferedImage darken(BufferedImage image) {
        return ImageTool.toBufferedImage(GrayFilter.createDisabledImage(image));
    }

}
